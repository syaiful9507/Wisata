package com.wisata.wisata.Adapter;

/**
 * Created by syaiful9507 on 1/31/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wisata.wisata.Config.Config;
import com.wisata.wisata.R;

import java.util.ArrayList;
import java.util.HashMap;

public class Adapter extends BaseAdapter {
    Config config = new Config();
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater = null;
    //  public ImageLoader imageLoader;
    public Adapter(Activity a, ArrayList<HashMap<String, String>> d)
    {
        activity = a;

        data = d;
        inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        imageLoader = new ImageLoader(activity.getApplicationContext());
    }

    public int getCount() {
        return data.size();
    }
    public Object getItem(int position) {
        return position;
    }
    public long getItemId(int position) {
        return position;
    }
    public View getView(int position, View convertView, ViewGroup
            parent) {
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.list_row, null);
        TextView id = (TextView) vi.findViewById(R.id.kode);
        TextView judul = (TextView) vi.findViewById(R.id.judul);
        ImageView thumb_image = (ImageView) vi.findViewById(R.id.gambar);

        HashMap<String, String> daftar_wisata = new HashMap<String, String>();
        daftar_wisata = data.get(position);

        id.setText(daftar_wisata.get(config.TAG_ID));
        judul.setText(daftar_wisata.get(config.TAG_JUDUL));
        Picasso.with(activity.getApplicationContext())
                .load(daftar_wisata.get(config.TAG_GAMBAR))
                .error(R.drawable.no_available)
                .into(thumb_image);

        return vi;
    }


}

