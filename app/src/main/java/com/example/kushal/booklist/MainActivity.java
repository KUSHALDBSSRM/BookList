package com.example.kushal.booklist;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String Query="";
    BooksAdapter adapter;
    ListView bookView;
    private TextView mEmptyStateTextView;
    ConnectivityManager connMgr;
    NetworkInfo networkInfo;
    EditText ed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        bookView = (ListView) findViewById(R.id.list);
        ed = (EditText) findViewById(R.id.editText);
        //bookView.setEmptyView(mEmptyStateTextView);

    }
    public void process(View v){

        connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.VISIBLE);
            //bookView.setEmptyView(mEmptyStateTextView);
            mEmptyStateTextView.setText("");
            Query = ed.getText().toString();
            //Log.e("Err",Query);
            if (Query.isEmpty() || Query == null || Query.equals("")) {
                Toast.makeText(MainActivity.this, "Please type something", Toast.LENGTH_LONG).show();
            }
            else {
                new BooksAsyncTask().execute(QueryUtils.Json_url + Query);
        }
        }
        else {

            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            bookView.setEmptyView(mEmptyStateTextView);
            mEmptyStateTextView.setText("No Internet Connection");
         }

    }
    public class BooksAsyncTask extends AsyncTask<String,Void,ArrayList<Books>>{

        @Override
        protected ArrayList<Books> doInBackground(String... urls) {
            ArrayList<Books> b = QueryUtils.extractFeatures(urls[0]);
            return b;
        }

        @Override
        protected void onPostExecute(ArrayList<Books> books) {
            //Log.d("Ans",books);
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            updateUi(books);
        }

        public void updateUi(ArrayList<Books> books){

            //ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,books);
            adapter = new BooksAdapter(MainActivity.this,books);
            bookView.setAdapter(adapter);
        }
    }

}
