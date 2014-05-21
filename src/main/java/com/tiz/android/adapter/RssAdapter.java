package com.tiz.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tiz.android.R;

import java.util.List;

import at.theengine.android.simple_rss2_android.RSSItem;

/**
 * Created by david.hodge on 12/21/13.
 */
public class RssAdapter extends BaseAdapter {

    private List<RSSItem> rssItems;
    private ViewHolder holder;
    private LayoutInflater mInflater;
    private Context context;

    public RssAdapter(Context c) {
        context = c;
        mInflater = LayoutInflater.from(c);
    }

    public void setData(List<RSSItem> mRssItems) {
        rssItems = mRssItems;
    }

    public int getCount() {
        return rssItems.size();
    }

    public RSSItem getItem(int position) {
        return rssItems.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    class ViewHolder {

        TextView mTitle;
        TextView mPubDate;
        TextView mContent;
        TextView mDesc;
        String mLink;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            view = mInflater.inflate(R.layout.rss_item, null);
            holder = new ViewHolder();

            holder.mTitle = (TextView) view.findViewById(R.id.rss_title_text);
//            holder.mDesc = (TextView) view.findViewById(R.id.rss_desc_text);
            holder.mPubDate = (TextView) view.findViewById(R.id.rss_pub_text);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        final RSSItem mItem = rssItems.get(position);

        holder.mTitle.setText(mItem.getTitle());
//        holder.mDesc.setText(mItem.getLink().toString());
        holder.mPubDate.setText(removeLastChar(mItem.getDate()));

        return view;
    }

    private static String removeLastChar(String str) {
        return str.substring(0,str.length()-15);
    }

}
