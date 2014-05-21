package com.tiz.android.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.tiz.android.R;
import com.tiz.android.fragments.Aboutfragment;
import com.tiz.android.fragments.FacebookFragment;
import com.tiz.android.fragments.InstagramFragment;
import com.tiz.android.fragments.ItemDetailFragment;
import com.tiz.android.fragments.ItemListFragment;
import com.tiz.android.fragments.NavigationDrawerFragment;
import com.tiz.android.fragments.TwitterFragment;

;

public class MainActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, ItemListFragment.Callbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        switch (position) {

            case 0:
                Fragment fragment = new ItemListFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
                transaction.replace(R.id.container, fragment);
                transaction.commit();
                mTitle = "Blog";
                break;
            case 1:
                Fragment facebookFragment = new FacebookFragment();
                FragmentManager fragmentManager1 = getFragmentManager();
                FragmentTransaction transaction1 = fragmentManager1.beginTransaction();
                transaction1.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
                transaction1.replace(R.id.container, facebookFragment);
                transaction1.commit();
                mTitle = "Tumblr";
                break;
            case 2:
                Fragment twitterFragment = new TwitterFragment();
                FragmentManager fragmentManager2 = getFragmentManager();
                FragmentTransaction transaction2 = fragmentManager2.beginTransaction();
                transaction2.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
                transaction2.replace(R.id.container, twitterFragment);
                transaction2.commit();
                mTitle = "Twitter";
                break;
            case 3:
                Fragment instaFragment = new InstagramFragment();
                FragmentManager fragmentManager3 = getFragmentManager();
                FragmentTransaction transaction3 = fragmentManager3.beginTransaction();
                transaction3.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
                transaction3.replace(R.id.container, instaFragment);
                transaction3.commit();
                mTitle = "Instagram";
                break;
            case 4:
                Fragment aboutFragment = new Aboutfragment();
                FragmentManager fragmentManager4 = getFragmentManager();
                FragmentTransaction transaction4 = fragmentManager4.beginTransaction();
                transaction4.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
                transaction4.replace(R.id.container, aboutFragment);
                transaction4.commit();
                mTitle = "About";
                break;
        }
        // update the main content by replacing fragments
//        FragmentManager fragmentManager = getFragmentManager();
//        fragmentManager.beginTransaction()
//                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
//                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                Fragment fragment = new ItemListActivity();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
//                transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
                transaction.replace(R.id.container, fragment);
                transaction.commit();
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.global, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }

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
            Intent detailIntent = new Intent(this, ItemDetailActivity.class);
            detailIntent.putExtra(ItemDetailFragment.ARG_ITEM_ID, id.getString("title"));
            detailIntent.putExtra(ItemDetailFragment.ARG_ITEM_DATE, id.getString("date"));
            detailIntent.putExtra(ItemDetailFragment.ARG_ITEM_CONTENT, id.getString("content"));
            startActivity(detailIntent);
//            overridePendingTransition(android.R.animator.fade_in, android.R.animator.fade_out);
        }
    }
}