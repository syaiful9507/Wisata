package com.wisata.wisata.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.wisata.wisata.Adapter.Adapter;
import com.wisata.wisata.Config.Config;
import com.wisata.wisata.Json.JSONParser;
import com.wisata.wisata.R;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainKuliner extends AppCompatActivity {

    private ProgressDialog pDialog;
    JSONParser jParser = new JSONParser();
    ArrayList<HashMap<String, String>> listwisata = new
            ArrayList<HashMap<String, String>>();
    Config config = new Config();
    JSONArray string_json = null;
    ListView list;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_alam);
        listwisata = new ArrayList<HashMap<String, String>>();
        new AmbilData().execute();
        list = (ListView) findViewById(R.id.list);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //action if item click as function
                HashMap<String, String> map = listwisata.get(position);
                // Starting new intent
                Intent in = new Intent(getApplicationContext(), Detail.class);
                in.putExtra(config.TAG_ID, map.get(config.TAG_ID));
                in.putExtra(config.TAG_GAMBAR, map.get(config.TAG_GAMBAR));
                startActivity(in);
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //show button up
        ActionBar actionBar = getSupportActionBar();
        if (actionBar !=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public void SetListViewAdapter(ArrayList<HashMap<String,
            String>> wisata) {
        adapter = new Adapter(this, wisata);
        list.setAdapter(adapter);
    }
    class AmbilData extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainKuliner.this);
            pDialog.setMessage("Mohon tunggu...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
        protected String doInBackground(String... args) {
            List<NameValuePair> params = new
                    ArrayList<NameValuePair>();

            JSONObject json = jParser.makeHttpRequest(config.url_tour_kuliner,
                    "GET", params);
            Log.i("Ini nilai json ", ">" + json);
            try {
                string_json = json.getJSONArray("wisata");
                for (int i = 0; i < string_json.length(); i++) {
                    JSONObject c = string_json.getJSONObject(i);
                    String id_wisata = c.getString(config.TAG_ID);
                    String judul = c.getString(config.TAG_JUDUL);
                    String link_image = c.getString(config.TAG_GAMBAR);
                    HashMap<String, String> map = new HashMap<String,
                            String>();
                    map.put(config.TAG_ID, id_wisata);
                    map.put(config.TAG_JUDUL, judul);
                    map.put(config.TAG_GAMBAR, link_image);
                    listwisata.add(map);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(String file_url) {
            pDialog.dismiss();
            runOnUiThread(new Runnable() {
                public void run() {
                    SetListViewAdapter(listwisata);
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, MainMenu.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
