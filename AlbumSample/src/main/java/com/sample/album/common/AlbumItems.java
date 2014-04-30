package com.sample.album.common;

/**
 * Created by raminduw on 4/30/14.
 */

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.sample.album.R;

import java.util.ArrayList;

public class AlbumItems extends Fragment {
    private View view;
    private AbsListView absListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list, container, false);
        absListView = (AbsListView) view.findViewById(R.id.list);

        ArrayList<Item> dummies = new ArrayList<Item>();
        for (int i = 0; i < 1000; i++) {
            Item item = new Item();
            item.setMainHeader("Album " + i);
            item.setSecondaryHeader("Gender " + i);
            dummies.add(item);
        }

        ItemsAdapter adapter = new ItemsAdapter(dummies, getActivity());
        absListView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}