package com.sample.album.common;

/**
 * Created by raminduw on 4/30/14.
 */

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import com.sample.album.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

public class DrawableManager {
    private final Map<String, Drawable> drawableMap;
    private static String TAG = DrawableManager.class.getSimpleName();

    public DrawableManager() {
        drawableMap = new HashMap<String, Drawable>();
    }

    public Drawable fetchDrawable(String urlString) {
        if (drawableMap.containsKey(urlString)) {
            return drawableMap.get(urlString);
        }

        Log.d(TAG, "image url:" + urlString);
        try {
            InputStream is = fetch(urlString);
            Drawable drawable = Drawable.createFromStream(is, "src");

            if (drawable != null) {
                drawableMap.put(urlString, drawable);

            } else {
                Log.d(TAG, "could not get thumbnail");
            }

            return drawable;
        } catch (MalformedURLException e) {
            Log.e(TAG, "fetchDrawable failed", e);
            return null;
        } catch (IOException e) {
            Log.e(TAG, "fetchDrawable failed", e);
            return null;
        }
    }

    public void fetchDrawableOnThread(final String urlString, final ImageView imageView) {
        if (drawableMap.containsKey(urlString)) {
            imageView.setImageDrawable(drawableMap.get(urlString));
        }

        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message message) {

                if ((Drawable) message.obj != null) {
                    imageView.setImageDrawable((Drawable) message.obj);
                } else {
                    imageView.setBackgroundResource(R.drawable.placeholder);
                }
            }
        };

        Thread thread = new Thread() {
            @Override
            public void run() {
                // set default image
                imageView.setBackgroundResource(R.drawable.placeholder);
                Drawable drawable = fetchDrawable(urlString);
                Message message = handler.obtainMessage(1, drawable);
                handler.sendMessage(message);
            }
        };
        thread.start();
    }

    private InputStream fetch(String urlString) throws MalformedURLException, IOException {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet request = new HttpGet(urlString);
        HttpResponse response = httpClient.execute(request);
        return response.getEntity().getContent();
    }

}
