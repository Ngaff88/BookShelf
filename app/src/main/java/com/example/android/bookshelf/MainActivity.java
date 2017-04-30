package com.example.android.bookshelf;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;
import static android.support.v4.media.session.MediaButtonReceiver.handleIntent;

public class MainActivity  extends AppCompatActivity implements LoaderCallbacks<List<Books>> {

    private static String USGS_REQUEST_URL ="https://www.googleapis.com/books/v1/volumes?q=android&maxResults=10";

    private static final int BOOK_LOADER_ID = 1;


    public static final String LOG_TAG = MainActivity.class.getName();
    private BookAdapter mAdapter;



    /** TextView that is displayed when the list is empty */
    private TextView mEmptyStateTextView;

    public MainActivity() throws UnsupportedEncodingException {
    }


    @Override
    public Loader<List<Books>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        Log.v(LOG_TAG,"TEST: Create Loader Called");
        return new BookLoader(this, USGS_REQUEST_URL);
    }
    @Override
    public void onLoadFinished(Loader<List<Books>> loader, List<Books> books) {
        View progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);





        // If there is a valid list of {@link Books}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (books != null && !books.isEmpty()) {
            mAdapter.addAll(books);
        }
        // Set empty state text to display "No books found."
        mEmptyStateTextView.setText(R.string.no_books);
        Log.v(LOG_TAG,"TEST: Finished Loader Called");
        // Clear the adapter of previous book data
        books.clear();



    }
    @Override
    public void onLoaderReset(Loader<List<Books>> loader) {
        Log.v(LOG_TAG,"TEST: Reset Loader Called");
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Find a reference to the {@link ListView} in the layout
        final ListView bookListView = (ListView) findViewById(R.id.list);

        // Create a new adapter that takes an empty list of books as input
        mAdapter = new BookAdapter(this, new ArrayList<Books>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        bookListView.setAdapter(mAdapter);

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        // Get details on the currently active default data network
        final NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        Button btn = (Button) findViewById(R.id.search_button);
        final EditText txt = (EditText) findViewById(R.id.search);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAdapter.clear();
                setContentView(R.layout.activity_main);
                // Find a reference to the {@link ListView} in the layout
                final ListView bookListView = (ListView) findViewById(R.id.list);

                // Set the adapter on the {@link ListView}
                // so the list can be populated in the user interface
                bookListView.setAdapter(mAdapter);


                Log.v("EditText", txt.getText().toString());
               String searchWord = txt.getText().toString();
                USGS_REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?q=" + searchWord + "&maxResults=10";

                if (networkInfo != null && networkInfo.isConnected()) {
                    LoaderManager loaderManager = getLoaderManager();
                    loaderManager.initLoader(0, null, MainActivity.this);

                    if (getLoaderManager().getLoader(0).isStarted()) {
                        //restart it if there's one
                        getLoaderManager().restartLoader(0, null, MainActivity.this);
                    }
                } else {
                    View loadingIndicator = findViewById(R.id.progress_bar);
                    loadingIndicator.setVisibility(View.GONE);

                    TextView emptyText = (TextView) findViewById(R.id.empty_view);
                    emptyText.setText("no_internet_connection");

                }

            }
        });


        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        bookListView.setEmptyView(mEmptyStateTextView);


        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();
            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(BOOK_LOADER_ID, null, this);
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = findViewById(R.id.progress_bar);
            loadingIndicator.setVisibility(View.GONE);

            // Update empty state with no connection error message
            mEmptyStateTextView.setText(R.string.no_internet);}



    }


}
