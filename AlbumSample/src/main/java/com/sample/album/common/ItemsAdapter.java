package com.sample.album.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sample.album.R;

import java.util.ArrayList;

public class ItemsAdapter extends BaseAdapter {
    private ArrayList<Item> items;
    private Context mContext;

    DrawableManager drawableManager;

    public ItemsAdapter(ArrayList<Item> items, Context mContext) {
        this.items = items;
        this.mContext = mContext;

        drawableManager = new DrawableManager();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowView = view;
        // reuse views
        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.list_item, null);
            // configure view holder
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.textMain = (TextView) rowView.findViewById(R.id.main_header);
            viewHolder.textSecondary = (TextView) rowView.findViewById(R.id.secondary_header);
            viewHolder.imgView = (ImageView) rowView.findViewById(R.id.icon);
            rowView.setTag(viewHolder);
        }

        // fill data
        ViewHolder holder = (ViewHolder) rowView.getTag();
        Item item = (Item) getItem(i);
        holder.textMain.setText(item.getMainHeader());
        holder.textSecondary.setText(item.getSecondaryHeader());
        ImageView imgView = holder.imgView;
        // download images and set it into imageview
        drawableManager.fetchDrawableOnThread(ImageUrl.mStrings[i], imgView);
        return rowView;
    }

    static class ViewHolder {
        public TextView textMain;
        public TextView textSecondary;
        public ImageView imgView;
    }
}
