package com.example.android.bookshelf;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by Nicholas on 4/21/2017.
 */

public class BookLoader extends AsyncTaskLoader<List<Books>>{
    /** Tag for log messages */
    private static final String LOG_TAG = BookLoader.class.getName();

    /** Query URL */
    private String mUrl;

    /**
     * Constructs a new {@link BookLoader}.
     *
     * @param context of the activity
     * @param url to load data from
     */
    public BookLoader(Context context, String url) {
        super(context);
        mUrl = url;

    }

    @Override
    protected void onStartLoading() {
        Log.v(LOG_TAG," TEST: onStart Called");
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<Books> loadInBackground() {
        Log.v(LOG_TAG,"TEST: loadInBackground Called");
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of books.
        List<Books> books = QueryUtils.fetchBookData(mUrl);
        return books;
    }
}
