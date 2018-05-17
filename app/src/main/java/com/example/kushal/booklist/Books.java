package com.example.kushal.booklist;

public class Books {
    private String title;
    private String authors;
    public Books(String title1,String authors1){
        title = title1;
        authors = authors1;
    }
    public String getTitle()
    {
        return title;
    }
    public String getAuthors()
    {
        return authors;
    }
}
