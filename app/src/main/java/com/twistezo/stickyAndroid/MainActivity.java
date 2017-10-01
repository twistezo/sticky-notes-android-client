package com.twistezo.stickyAndroid;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static List<Note> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new HttpRequestListOfNotes().execute();
        ListView listView = (ListView) findViewById(R.id.list_of_notes_view);
        NotesCustomAdapter adapter = new NotesCustomAdapter(notes, getApplicationContext());
        listView.setAdapter(adapter);

        AdapterView.OnItemClickListener adapterViewListener = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Note note = notes.get(position);
                Intent intent = new Intent(MainActivity.this, OneNoteActivity.class);
                intent.putExtra("list_of_notes_id", note.getId());
                intent.putExtra("list_of_notes_title", note.getTitle());
                intent.putExtra("list_of_notes_body", note.getBody());
                intent.putExtra("list_of_notes_date", note.getDate());
                intent.putExtra("list_of_notes_done_image", note.isDone());
                startActivity(intent);
            }
        };
        listView.setOnItemClickListener(adapterViewListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            new HttpRequestListOfNotes().execute();
            finish();
            startActivity(getIntent());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class HttpRequestListOfNotes extends AsyncTask<Object, Object, List<Note>> {
        @Override
        protected List<Note> doInBackground(Object... params) {
            try {
                final String url = "http://192.168.0.12:8080/rest/notes";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Note[] notesTemp = restTemplate.getForObject(url, Note[].class);
                notes = Arrays.asList(notesTemp);
                return notes;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }
            return null;
        }
    }
}
