package com.testapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookModel {
    @SerializedName("results")
    @Expose
    private List<BookResult> bookResults = null;

    public List<BookResult> getBookResults() {
        return bookResults;
    }

    public void setBookResults(List<BookResult> bookResults) {
        this.bookResults = bookResults;
    }
}
