package com.example.android.bookshelf;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.util.ArrayList;


/**
 * Created by Nicholas on 4/21/2017.
 */

public class BookAdapter extends ArrayAdapter<Books> {
    private static final String LOG_TAG = BookAdapter.class.getSimpleName();

    private static final String LOCATION_SEPARATOR = " of ";

    /**
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context        The current context. Used to inflate the layout file.
     * @param books A List of books objects to display in a list
     */
    BookAdapter(Activity context, ArrayList<Books> books) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value.
        super(context, 0, books);
    }


    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        Books currentBook = getItem(position);


        String author = currentBook.getAuthor();
        TextView authorTextView = (TextView) listItemView.findViewById(R.id.author);
        authorTextView.setText(author);


        String title = currentBook.getTitle();
        TextView titleTextView = (TextView) listItemView.findViewById(R.id.title);
        titleTextView.setText(title);


        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }

}
