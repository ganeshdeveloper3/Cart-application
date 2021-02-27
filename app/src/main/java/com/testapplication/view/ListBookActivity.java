package com.testapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.google.gson.JsonObject;
import com.testapplication.R;
import com.testapplication.adapter.BookListAdapter;
import com.testapplication.databinding.ActivityListBookBinding;
import com.testapplication.model.BookResult;
import com.testapplication.viewmodel.BooksViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListBookActivity extends AppCompatActivity {
    ActivityListBookBinding bookBinding;

    ArrayList<BookResult> bookArrayList = new ArrayList<>();
    BookListAdapter bookListAdapter;
    private BookResult bookResult;
    private BooksViewModel booksViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bookBinding = DataBindingUtil.setContentView(ListBookActivity.this,R.layout.activity_list_book);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        bookBinding.recyclerview.setLayoutManager(layoutManager);
        bookListAdapter = new BookListAdapter(this, bookArrayList);

        bookBinding.recyclerview.setAdapter(bookListAdapter);
        booksViewModel = ViewModelProviders.of(this).get(BooksViewModel.class);

        booksViewModel.getBookListOberver().observe(this, newsResponse -> {
            List<BookResult> profilelists = newsResponse.getBookResults();
            bookArrayList.addAll(profilelists);
            bookListAdapter.notifyDataSetChanged();
        });

        booksViewModel.makeApiCall();

    }

}