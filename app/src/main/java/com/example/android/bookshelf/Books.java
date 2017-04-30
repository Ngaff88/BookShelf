package com.example.android.bookshelf;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Nicholas on 4/21/2017.
 */

public class Books {

    private String mTitle;

    private  String mAuthor;




    /*
    * Create a new Books object.
    *
    * @param Title is the name of the Books Title
    * @param Author is the corresponding author of the book
    */
    public Books(String title,String author)
    {
        mTitle = title;
        mAuthor = author;
    }

    /**
     * Get the name of the Android version
     */

    /**
     * Get the book title
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * Get the author of the book
     */
    public String getAuthor() {
        return mAuthor;
    }
}
