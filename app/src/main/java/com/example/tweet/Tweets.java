package com.example.tweet;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Tweets extends AppCompatActivity implements android.view.View.OnClickListener {
    private android.widget.EditText tweet;
    android.widget.Button btnUpdate;
    android.widget.Button FollowTweet;
    private android.widget.ListView listView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweets);
        tweet = findViewById(com.example.tweet.R.id.tweetHere);
        btnUpdate = findViewById(com.example.tweet.R.id.UpdateTweet);
        FollowTweet = findViewById(R.id.Follow);
        btnUpdate.setOnClickListener(Tweets.this);
        FollowTweet.setOnClickListener(Tweets.this);
        listView2= findViewById(com.example.tweet.R.id.Tweeterlist);
    }

    @Override
    public void onClick(android.view.View v) {
        switch (v.getId()) {
            case com.example.tweet.R.id.UpdateTweet:
                com.parse.ParseObject tweets;
                tweets = new com.parse.ParseObject("My_Tweets");
                tweets.put("tweet", tweet.getText().toString());
                tweets.put("User", com.parse.ParseUser.getCurrentUser().getUsername());
                android.app.ProgressDialog process = new android.app.ProgressDialog(Tweets.this);
                process.setMessage("Please wait.");
                process.show();

                tweets.saveInBackground(new com.parse.SaveCallback() {
                    @Override
                    public void done(com.parse.ParseException e) {
                        if (e == null) {
                            com.shashank.sony.fancytoastlib.FancyToast.makeText(Tweets.this, "Done", com.shashank.sony.fancytoastlib.FancyToast.LENGTH_SHORT, com.shashank.sony.fancytoastlib.FancyToast.SUCCESS, true).show();

                        }
                    }
                });
                process.dismiss();
                break;
            case com.example.tweet.R.id.Follow:
                com.shashank.sony.fancytoastlib.FancyToast.makeText(Tweets.this, "hhi", com.shashank.sony.fancytoastlib.FancyToast.LENGTH_SHORT,
                        com.shashank.sony.fancytoastlib.FancyToast.SUCCESS, true).show();
                final java.util.ArrayList<java.util.HashMap<String, String>> tweetlist = new java.util.ArrayList<>();
                final android.widget.SimpleAdapter simple = new
                        android.widget.SimpleAdapter(Tweets.this, tweetlist, android.R.layout.simple_list_item_2,
                        new String[]{"tweeterUserName", "tweetValue"}, new int[]{android.R.id.text1, android.R.id.text2});
                try {
                    com.parse.ParseQuery<com.parse.ParseObject> query = com.parse.ParseQuery.getQuery("My_Tweets");
                    query.whereContainedIn("User", com.parse.ParseUser.getCurrentUser().getList("fanof"));

                    query.findInBackground(new com.parse.FindCallback<com.parse.ParseObject>() {
                        @Override
                        public void done(java.util.List<com.parse.ParseObject> objects, com.parse.ParseException e) {
                            if (e == null && objects.size() > 0) {
                                for (com.parse.ParseObject tweeterObject : objects) {
                                    java.util.HashMap<String, String> userTweet = new java.util.HashMap<>();
                                    userTweet.put("tweeterUserName", tweeterObject.getString("User"));
                                    userTweet.put("tweetValue", tweeterObject.getString("tweet"));
                                    tweetlist.add(userTweet);
                                    com.shashank.sony.fancytoastlib.FancyToast.makeText(Tweets.this,
                                            "done", com.shashank.sony.fancytoastlib.FancyToast.LENGTH_SHORT,
                                            com.shashank.sony.fancytoastlib.FancyToast.SUCCESS, true).show();
                                }
                                listView2.setAdapter(simple);

                            }
                        }

                    });
                    break;

                } catch (Exception p) {
                    p.printStackTrace();
                }
        }
    }



}