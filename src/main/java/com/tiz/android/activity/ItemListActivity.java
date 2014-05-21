package com.tiz.android.activity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tiz.android.R;
import com.tiz.android.fragments.ItemDetailFragment;
import com.tiz.android.fragments.ItemListFragment;


/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link com.tiz.android.activity.ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link com.tiz.android.fragments.ItemListFragment} and the item details
 * (if present) is a {@link com.tiz.android.fragments.ItemDetailFragment}.
 * <p>
 * This activity also implements the required
 * {@link com.tiz.android.fragments.ItemListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class ItemListActivity extends Fragment
        implements ItemListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_item_list, container, false);

        if (view.findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((ItemListFragment) getActivity().getFragmentManager()
                    .findFragmentById(R.id.item_list))
                    .setActivateOnItemClick(true);
        }

        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_item_list);
//
//        if (findViewById(R.id.item_detail_container) != null) {
//            // The detail container view will be present only in the
//            // large-screen layouts (res/values-large and
//            // res/values-sw600dp). If this view is present, then the
//            // activity should be in two-pane mode.
//            mTwoPane = true;
//
//            // In two-pane mode, list items should be given the
//            // 'activated' state when touched.
//            ((ItemListFragment) getSupportFragmentManager()
//                    .findFragmentById(R.id.item_list))
//                    .setActivateOnItemClick(true);
//        }
//
//        // TODO: If exposing deep links into your app, handle intents here.
//    }

    /**
     * Callback method from {@link ItemListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(Bundle id) {
        Log.d("rss_id", id.getString("title"));
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(ItemDetailFragment.ARG_ITEM_ID, id.getString("title"));
            arguments.putString(ItemDetailFragment.ARG_ITEM_DATE, id.getString("date"));
            arguments.putString(ItemDetailFragment.ARG_ITEM_CONTENT, id.getString("content"));
            ItemDetailFragment fragment = new ItemDetailFragment();
            fragment.setArguments(arguments);
            getFragmentManager().beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(getActivity(), ItemDetailActivity.class);
            detailIntent.putExtra(ItemDetailFragment.ARG_ITEM_ID, id.getString("title"));
            detailIntent.putExtra(ItemDetailFragment.ARG_ITEM_DATE, id.getString("date"));
            detailIntent.putExtra(ItemDetailFragment.ARG_ITEM_CONTENT, id.getString("content"));
            startActivity(detailIntent);
        }
    }
}
