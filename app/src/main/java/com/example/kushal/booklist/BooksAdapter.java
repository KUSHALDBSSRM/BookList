package com.example.kushal.booklist;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BooksAdapter extends ArrayAdapter<Books> {
    public BooksAdapter(Activity context, ArrayList<Books> book) {
        super(context,0,book);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listView;
        listView = convertView;
        if(listView == null){
            listView = LayoutInflater.from(getContext()).inflate(R.layout.custom_list_item, parent,false);
        }
        Books book = getItem(position);
        String title = book.getTitle();
        String author = book.getAuthors();
        TextView auth = (TextView) listView.findViewById(R.id.author);
        auth.setText(author);
        TextView tit = (TextView) listView.findViewById(R.id.tit);
        tit.setText(title);
        return listView;
    }
}
