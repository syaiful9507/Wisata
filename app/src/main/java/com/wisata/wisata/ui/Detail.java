package com.wisata.wisata.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.AudioManager;
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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wisata.wisata.Config.Config;
import com.wisata.wisata.ImageLoader;
import com.wisata.wisata.Json.JSONParser;
import com.wisata.wisata.R;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.media.MediaPlayer;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Detail extends AppCompatActivity {
    static MediaPlayer md;
    private Boolean isFabOpen = false;
    Animation fab_open,fab_close,rotate_forward,rotate_backward;
    private FloatingActionButton fab,play,pause;
    Config config = new Config();
    public ImageLoader imageLoader;
    {
        imageLoader = new ImageLoader(null);
    }

    JSONArray string_json = null;
    String idwisata;
    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent i = getIntent();
        idwisata = i.getStringExtra(config.TAG_ID);
        new AmbilDetailWisata().execute();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //show button up
        ActionBar actionBar = getSupportActionBar();
        if (actionBar !=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        play = (FloatingActionButton)findViewById(R.id.play);
        pause = (FloatingActionButton)findViewById(R.id.pause);
        fab = (FloatingActionButton)findViewById(R.id.fab);

        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_backward);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFAB();
            }
        });

    }
    class AmbilDetailWisata extends AsyncTask<String, String,
            String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Detail.this);
            pDialog.setMessage("Mohon Tunggu ... !");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        protected String doInBackground(String... params) {
            try {
                List<NameValuePair> params1 = new
                        ArrayList<NameValuePair>();
                params1.add(new
                        BasicNameValuePair("id_wisata",idwisata));
                JSONObject json = jsonParser.makeHttpRequest(
                        config.url_detail_tour, "GET", params1);
                string_json = json.getJSONArray("wisata");
                runOnUiThread(new Runnable() {
                    public void run() {

                        //load from anim

                        ImageView thumb_image = (ImageView)
                                findViewById(R.id.imageView1);
                        TextView judul = (TextView)findViewById(R.id.judul);
                        //TextView detail = (TextView)

                        findViewById(R.id.detail);
                        FloatingActionButton play = (FloatingActionButton)findViewById(R.id.play);
                        FloatingActionButton pause = (FloatingActionButton)findViewById(R.id.pause);

                        TextView isi = (TextView)findViewById(R.id.content);
                        try {
                            // ambil objek member pertama dari JSON Array
                            JSONObject ar =
                                    string_json.getJSONObject(0);
                            //Penambahan suara
                            //String voice = ar.getString(config.TAG_voice);
                            String judul_d = ar.getString(config.TAG_JUDUL);
                            String isi_d = ar.getString(config.TAG_ISI);
                            String url_detail_image = ar.getString(config.TAG_GAMBAR);
                            final String voice = ar.getString(config.TAG_VOICE);
                            judul.setText(judul_d);
                            isi.setText(isi_d);


                            //Load audio //pi masih gagal
                            //Picasso.with(getApplicationContext()).load(voice);
//                            imageLoader.DisplayImage(ar.getString(TAG_GAMBAR),thumb_image);
                            Picasso.with(getApplicationContext())
                                    .load(url_detail_image)
                                    .error(R.drawable.no_available)
                                    .into(thumb_image);
                            final MediaPlayer md;
                            md = new MediaPlayer();
                            md.setAudioStreamType(AudioManager.STREAM_MUSIC);
                            play.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    try
                                    {
                                        md.setDataSource(voice);
                                        md.prepare();


                                    } catch (IllegalArgumentException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    } catch (SecurityException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    } catch (IllegalStateException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }

                                    md.start();

                                }
                            });
                            pause.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    md.stop();
                                }
                            });



                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(String file_url) {
            pDialog.dismiss();
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

    //method for animate onclick
    public void animateFAB(){

        if(isFabOpen){

            fab.startAnimation(rotate_backward);
            play.startAnimation(fab_close);
            pause.startAnimation(fab_close);
            play.setClickable(false);
            pause.setClickable(false);
            isFabOpen = false;
            Log.d("Raj", "close");

        } else {

            fab.startAnimation(rotate_forward);
            play.startAnimation(fab_open);
            pause.startAnimation(fab_open);
            play.setClickable(true);
            pause.setClickable(true);
            isFabOpen = true;
            Log.d("Raj","open");

        }
    }

}
