package com.twistezo.stickyAndroid;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.twistezo.stickyAndroid.R.drawable.glyphicons_ok;
import static com.twistezo.stickyAndroid.R.drawable.glyphicons_remove;

public class OneNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_notes_row);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*
        Find all our view components
         */
        TextView id = (TextView) findViewById(R.id.list_of_notes_id);
        TextView title = (TextView) findViewById(R.id.list_of_notes_title);
        TextView body = (TextView) findViewById(R.id.list_of_notes_body);
        TextView date = (TextView) findViewById(R.id.list_of_notes_date);
        ImageView doneImg = (ImageView) findViewById(R.id.list_of_notes_done_image);

        /*
        Collect our intent and populate our layout
         */
        Intent intent = getIntent();
        Long idIntent = intent.getLongExtra("list_of_notes_id", 0);
        String titleIntent = intent.getStringExtra("list_of_notes_title");
        String bodyIntent = intent.getStringExtra("list_of_notes_body");
        Date dateIntent = (Date)intent.getSerializableExtra("list_of_notes_date");
        boolean doneIntent = intent.getBooleanExtra("list_of_notes_done_image", false);

        /*
        Set elements
         */
        id.setText(String.valueOf(idIntent));
        title.setText(titleIntent);
        body.setText(bodyIntent);
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy, ");
        String requiredDate = df.format(dateIntent);
        date.setText(requiredDate);
        if (doneIntent) {
            doneImg.setImageResource(glyphicons_ok);
            ColorFilter filter = new LightingColorFilter( Color.parseColor("#f4511e"), Color.parseColor("#f4511e") );
            doneImg.setColorFilter(filter);
        } else {
            doneImg.setImageResource(glyphicons_remove);
        }

        /*
        Set the title of this activity to be the street name
         */
        getSupportActionBar().setTitle(titleIntent);
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
            finish();
            startActivity(getIntent());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}