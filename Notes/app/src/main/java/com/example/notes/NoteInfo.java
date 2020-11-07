package com.example.notes;

public class NoteInfo {

    private int id;
    private String title;
    private CharSequence content;

    // constructor
    public NoteInfo() {
    }

    public NoteInfo(int id, String title, CharSequence content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    // getter & setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CharSequence getContent() {
        return content;
    }

    public void setContent(CharSequence content) {
        this.content = content;
    }

    // Print information

    @Override
    public String toString() {
        return "NoteInfo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content=" + content +
                '}';
    }
}
