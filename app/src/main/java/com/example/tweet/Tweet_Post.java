package com.example.tweet;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class Tweet_Post extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView listView;
    private java.util.ArrayList<String> Tuser;
    private android.widget.ArrayAdapter<String> adapter;
 private String follower="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet__post);
        //com.shashank.sony.fancytoastlib.FancyToast.makeText(Tweet_Post.this,, com.shashank.sony.fancytoastlib.FancyToast.LENGTH_LONG, com.shashank.sony.fancytoastlib.FancyToast.INFO)
        com.shashank.sony.fancytoastlib.FancyToast.makeText(this, "   Welcome   " + com.parse.ParseUser.getCurrentUser().getUsername(), com.shashank.sony.fancytoastlib.FancyToast.LENGTH_LONG, com.shashank.sony.fancytoastlib.FancyToast.INFO, true).show();
        listView = findViewById(com.example.tweet.R.id.listView2);
        Tuser = new java.util.ArrayList<>();
        adapter = new android.widget.ArrayAdapter<>(this, android.R.layout.simple_list_item_checked, Tuser);
        listView.setChoiceMode(android.widget.AbsListView.CHOICE_MODE_MULTIPLE);
        listView.setOnItemClickListener(com.example.tweet.Tweet_Post.this);
        com.parse.ParseQuery<com.parse.ParseUser> query = com.parse.ParseUser.getQuery();
        query.whereNotEqualTo("username", com.parse.ParseUser.getCurrentUser().getUsername());
        query.findInBackground(new com.parse.FindCallback<com.parse.ParseUser>() {
            @Override
            public void done(java.util.List<com.parse.ParseUser> objects, com.parse.ParseException e) {
                if (e == null && objects.size() > 0) {
                    for (com.parse.ParseUser tweeterUser : objects) {
                        Tuser.add(tweeterUser.getUsername());
                    }
                    listView.setAdapter(adapter);
                    for(String tweeterUser : Tuser){
                           follower=follower+tweeterUser+"\n";
                        if(com.parse.ParseUser.getCurrentUser().getList("fanof")!=null) {
                            if (com.parse.ParseUser.getCurrentUser().getList("fanof").contains(tweeterUser)) {
                                listView.setItemChecked(Tuser.indexOf(tweeterUser), true);
                                com.shashank.sony.fancytoastlib.FancyToast.makeText(Tweet_Post.this, com.parse.ParseUser.getCurrentUser().getUsername()+"is following :" +follower, com.shashank.sony.fancytoastlib.FancyToast.LENGTH_SHORT, com.shashank.sony.fancytoastlib.FancyToast.INFO, true).show();
                            }

                        }
                    }

                } else {
                    com.shashank.sony.fancytoastlib.FancyToast.makeText(Tweet_Post.this, "Error", com.shashank.sony.fancytoastlib.FancyToast.LENGTH_LONG, com.shashank.sony.fancytoastlib.FancyToast.ERROR, true).show();

                }
            }
        });
    }

    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        android.view.MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@androidx.annotation.NonNull android.view.MenuItem item) {
        switch (item.getItemId()) {
            case com.example.tweet.R.id.Logout_item:
                com.parse.ParseUser.getCurrentUser().logOutInBackground(new com.parse.LogOutCallback() {
                    @Override
                    public void done(com.parse.ParseException e) {
                        android.content.Intent intent = new android.content.Intent(Tweet_Post.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                break;
            case  R.id.send:
                startActivity(new android.content.Intent(Tweet_Post.this,Tweets.class));
                        break;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onItemClick(android.widget.AdapterView<?> parent, android.view.View view,final int position, long id) {
        android.widget.CheckedTextView checkedtextView = (android.widget.CheckedTextView) view;
        com.shashank.sony.fancytoastlib.FancyToast.makeText(Tweet_Post.this, Tuser.get(position) + "Id is followed ", com.shashank.sony.fancytoastlib.FancyToast.LENGTH_LONG, com.shashank.sony.fancytoastlib.FancyToast.INFO, true).show();

        if (checkedtextView.isChecked()) {

            com.parse.ParseUser.getCurrentUser().add("fanof", Tuser.get(position));
        } else {
            com.shashank.sony.fancytoastlib.FancyToast.makeText(Tweet_Post.this, Tuser.get(position) + "Id is followed", com.shashank.sony.fancytoastlib.FancyToast.LENGTH_LONG, com.shashank.sony.fancytoastlib.FancyToast.INFO, true).show();
            com.parse.ParseUser.getCurrentUser().getList("fanof").remove(Tuser.get(position));
            java.util.List  list= com.parse.ParseUser.getCurrentUser().getList("fanof");
            com.parse.ParseUser.getCurrentUser().remove("fanof");
            com.parse.ParseUser.getCurrentUser().put("fanof",list);

        }
        com.parse.ParseUser.getCurrentUser().saveInBackground(new com.parse.SaveCallback() {
            @Override
            public void done(com.parse.ParseException e) {
                if(e==null){
                    com.shashank.sony.fancytoastlib.FancyToast.makeText(Tweet_Post.this,   "CheckList saved", com.shashank.sony.fancytoastlib.FancyToast.LENGTH_LONG, com.shashank.sony.fancytoastlib.FancyToast.INFO, true).show();

                }
            }
        });
    }
}
