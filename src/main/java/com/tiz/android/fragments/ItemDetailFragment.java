package com.tiz.android.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tiz.android.R;
import com.tiz.android.dummy.DummyContent;


/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link com.tiz.android.activity.ItemListActivity}
 * in two-pane mode (on tablets) or a {@link com.tiz.android.activity.ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends android.app.Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";
    public static final String ARG_ITEM_DATE = "date";
    public static final String ARG_ITEM_CONTENT = "content";

    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContent.DummyItem mItem;
    TextView articleTitle;
    TextView articleDate;
    TextView articleContent;
    String article;
    String title;
    String date;

    Typeface roboto;
    Typeface robotoThin;
    Typeface robotoLight;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {

    }

    public String stripHtml(String html) {
        return Html.fromHtml(html).toString();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            title = getArguments().getString(ARG_ITEM_ID);
            date = getArguments().getString(ARG_ITEM_DATE);
            article = getArguments().getString(ARG_ITEM_CONTENT);

            Log.d("rss_item", getArguments().getString(ARG_ITEM_ID));
//            Log.d("rss_id", article);
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }else{
            Log.d("rss_item", "missing title");
        }
//        roboto = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Roboto.ttf");
//        robotoLight = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Roboto-Light.ttf");
//        robotoThin = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Roboto-Thin.ttf");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_item_detail, container, false);

        articleTitle = (TextView) rootView.findViewById(R.id.article_title);
        articleDate = (TextView) rootView.findViewById(R.id.article_date);
        articleContent = (TextView) rootView.findViewById(R.id.article_content);

        roboto = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Roboto.ttf");
        robotoLight = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Roboto-Light.ttf");
        robotoThin = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Roboto-Thin.ttf");

        articleTitle.setText(title);
        articleTitle.setTypeface(robotoLight);
        articleDate.setText(removeLastChar(date));
        articleDate.setTypeface(robotoThin);
        articleContent.setText(stripHtml(article));
        articleContent.setTypeface(robotoLight);

        // Show the dummy content as text in a TextView.
//        if (mItem != null) {
////            ((TextView) rootView.findViewById(R.id.item_detail)).setText(mItem.content);
//            ((TextView) rootView.findViewById(R.id.item_detail)).setText(article);
//        }

        return rootView;
    }

    private static String removeLastChar(String str) {
        return str.substring(0,str.length()-15);
    }
}
