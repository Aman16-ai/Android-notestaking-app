package com.example.firenotes;

public class NotesModel {
    private int id;
    private String title;
    private String body;
    private String day,months,year;
    public NotesModel() {

    }
    public NotesModel(String title,String body) {
        this.title = title;
        this.body = body;
    }
    public NotesModel(int id,String title,String body,String day,String months,String year) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.day = day;
        this.months = months;
        this.year = year;
    }
    public NotesModel(String title,String body ,String day,String months,String year) {
        this.title = title;
        this.body = body;
        this.day = day;
        this.months = months;
        this.year = year;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }


    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }


    public void setDay(String day) {
        this.day = day;
    }

    public String getDay() {
        return day;
    }

    public void setMonths(String months) {
        this.months = months;
    }

    public String getMonths() {
        return months;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getYear() {
        return year;
    }
}
