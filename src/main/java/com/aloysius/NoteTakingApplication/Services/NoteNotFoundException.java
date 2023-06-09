package com.aloysius.NoteTakingApplication.Services;

public class NoteNotFoundException extends Exception{
    public NoteNotFoundException(String message) {
        super(message);
    }
}
