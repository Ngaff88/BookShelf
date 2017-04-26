package com.example.android.bookshelf;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Nicholas on 4/21/2017.
 */

public class Books {

    private String mTitle;

    private  String mAuthor;

    private String mUrl;



    /*
    * Create a new Books object.
    *
    * @param Title is the name of the Books Title
    * @param Author is the corresponding author of the book
    * @param Url is the url picked up from the JSON
    * */
    public Books(String Title,String Author,String Url )
    {
        mTitle = Title;
        mAuthor = Author;
        mUrl = Url;
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

    /**
     * Get the url of the book
     */
    public String getUrl() {
        return mUrl;
    }
}
