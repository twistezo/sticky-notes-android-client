package com.twistezo.stickyAndroid;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
class Note {
    private Long id;
    private String title;
    private String body;
    private Date date;
    private boolean noteChecked;
    private boolean done;

    public Note() {
    }

    Long getId() {
        return id;
    }

    String getTitle() {
        return title;
    }

    String getBody() {
        return body;
    }

    Date getDate() {
        return date;
    }

    boolean isNoteChecked() {
        return noteChecked;
    }

    boolean isDone() {
        return done;
    }
}
