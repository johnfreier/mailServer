package com.johnfreier.mail.storage;

public class TestSMTPStorage implements SMTPStorage {

    private String from = "";
    
    private String to = "";
    
    private String data = "";
    
    boolean recording = false;
    
    @Override
    public void setFrom(String from) {
        this.from = from;
    }
    
    public String getFrom() {
        return this.from;
    }

    @Override
    public void setTo(String to) {
        this.to = to;
    }
    
    public String getTo() {
        return this.to;
    }

    @Override
    public void addData(String data) {
        this.data += data;
    }
    
    public String getData() {
        return data;
    }

    @Override
    public void setRecoring(boolean recording) {
        this.recording = recording;
    }

    @Override
    public boolean isRecording() {
        return recording;
    }

    @Override
    public boolean commit() {
        return true;
    }

}
