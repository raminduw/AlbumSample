package com.sample.album;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;

import com.sample.album.common.AlbumItems;
import com.sample.album.common.ArtistItems;
import com.sample.album.common.SongItems;


public class AlbumSampleMain extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private NavigationDrawerFragment mNavigationDrawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_sample_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.navigation_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        FragmentManager fragmentManager = getFragmentManager();

        switch (position) {

            case 0:
                AlbumItems albumItems = new AlbumItems();
                fragmentManager.beginTransaction().replace(R.id.container, albumItems).commit();

                break;
            case 1:
                SongItems songItems = new SongItems();
                fragmentManager.beginTransaction().replace(R.id.container, songItems).commit();
                break;

            case 2:
                ArtistItems artistItems = new ArtistItems();
                fragmentManager.beginTransaction().replace(R.id.container, artistItems).commit();
                break;

            default:
                AlbumItems albumItems1 = new AlbumItems();
                fragmentManager.beginTransaction().replace(R.id.container, albumItems1).commit();
        }

    }
}
