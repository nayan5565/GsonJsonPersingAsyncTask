package com.example.nayan.myfirstjson;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    TextView txt, txt2;
    MyRecyclerAdapter adapter;
    RecyclerView recyclerView;
    ArrayList<MVideo> videos;
    MVideo mVideo;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = (TextView) findViewById(R.id.txt);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getAudieo(R.raw.a);
                getOnlineData();
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recycler2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerAdapter(this);
        recyclerView.setAdapter(adapter);
        videos = new ArrayList<>();
        mVideo = new MVideo();
        gson = new Gson();
        // makeJson();
        //getOnlineData();
        getDataFromOnline();
    }

    public void makeJson() {
        String country = "Bangladesh", country2 = "India";
        String city = "Khulna", city2 = "kalkata";

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("country", country);
            jsonObject.put("city", city);
            jsonObject.put("country2", country2);
            jsonObject.put("city2", city2);
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(jsonObject);
            //txt.setText(jsonArray.toString());

            JSONObject object = new JSONObject(jsonObject.toString());
            String co = object.getString("country");
            String ci = object.getString("city");
            String co2 = object.getString("country2");
            String ci2 = object.getString("city2");

            //txt2.setText(co + ":" + ci + "\n" + co2 + " : " + ci2);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getOnlineData() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.post("http://www.radhooni.com/content/match_game/v1/game.json", new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        try {
                            JSONObject puzzle = response.getJSONObject("puzzle");
                            JSONArray easy = puzzle.getJSONArray("easy");
                            for (int i = 0; i < easy.length(); i++) {
                                JSONObject jsonObject = easy.getJSONObject(i);
                                String id = jsonObject.getString("id");
                                String level = jsonObject.getString("level");
                                String coinsPrice = jsonObject.getString("coins_price");
                                String noOfCoins = jsonObject.getString("no_of_coins");
                                Log.e("easy 5", ": " + id + ":" + level + ":" + coinsPrice + ":" + noOfCoins);
                                JSONArray image = easy.getJSONArray(i);
                                for (int j = 0; j < image.length(); j++) {

                                }
                            }

                            /*String data = "";*/
                            //Log.e("client", ": " + jsonArray.length());
                            /*for (int i = 0; i < jsonArray.length(); i++) {

                                jsonArray.get(i);
                                Log.e("array", "data is:" + jsonArray.get(i));
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String id = jsonObject.getString("menuId");
                                String title = jsonObject.getString("menuTitle");
                                String video = jsonObject.getString("menuVideo");
                                String name = jsonObject.getString("menuFile_name");
                                data = data + "\nid : " + id + "\nname : " + name + "\ntitle : " + title + "\nvideo : " + video;

                            }
                            txt.setText(data);*/


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                /*try {
                   String id= response.getString("id");
                   String name= response.getString("name");
                  String wicket=  response.getString("wicket");
                    txt.setText(id+"\n"+name+"\n"+wicket);
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/

                    }
                }
        );
    }

    public void getDataFromOnline() {
        AsyncHttpClient httpClient = new AsyncHttpClient();
        httpClient.post("http://www.radhooni.com/content/api/videos.php", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    MVideo[] mVideos = gson.fromJson(response.getJSONArray("videos").toString(), MVideo[].class);
                    Log.e("array", " : " + mVideos[1].getMenuId());
                    videos = new ArrayList<MVideo>(Arrays.asList(mVideos));
                    adapter.setData(videos);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void getAudieo(int file) {
        MediaPlayer mediaPlayer;


        mediaPlayer = MediaPlayer.create(this, file);
        //mediaPlayer.setDataSource(this, Uri.parse("android.resource://com.example.nayan.myfirstjson/res/raw/a"));

        mediaPlayer.start();

    }

}
