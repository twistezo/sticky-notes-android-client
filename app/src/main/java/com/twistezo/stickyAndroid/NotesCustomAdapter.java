package com.twistezo.stickyAndroid;

import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import static com.twistezo.stickyAndroid.R.drawable.glyphicons_ok;
import static com.twistezo.stickyAndroid.R.drawable.glyphicons_remove;

class NotesCustomAdapter extends ArrayAdapter<Note> {
    private List<Note> notes;
    private Context context;

    NotesCustomAdapter(List<Note> notes, Context context) {
        super(context, R.layout.list_of_notes_row, notes);
        this.notes = notes;
        this.context = context;
    }

    private static class ViewHolder {
        TextView id;
        TextView title;
        TextView body;
        TextView date;
        TextView done;
        ImageView doneImg;
    }

    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        Note note = getItem(position);

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_of_notes_row, parent, false);
            viewHolder.id = (TextView) convertView.findViewById(R.id.list_of_notes_id);
            viewHolder.title = (TextView) convertView.findViewById(R.id.list_of_notes_title);
            viewHolder.body = (TextView) convertView.findViewById(R.id.list_of_notes_body);
            viewHolder.date = (TextView) convertView.findViewById(R.id.list_of_notes_date);
            viewHolder.doneImg = (ImageView) convertView.findViewById(R.id.list_of_notes_done_image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.id.setText(String.valueOf(note.getId() + ", "));
        viewHolder.title.setText(String.valueOf(note.getTitle()));
        viewHolder.body.setText(String.valueOf(note.getBody()));
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy, ");
        String requiredDate = df.format(note.getDate());
        viewHolder.date.setText(requiredDate);

        if (note.isDone()) {
            viewHolder.doneImg.setImageResource(glyphicons_ok);
            ColorFilter filter = new LightingColorFilter(Color.parseColor("#f4511e"), Color.parseColor("#f4511e"));
            viewHolder.doneImg.setColorFilter(filter);
        } else {
            viewHolder.doneImg.setImageResource(glyphicons_remove);
        }
        
        return convertView;
    }
}