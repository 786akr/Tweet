package com.example.tweet;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseInstallation;

public class MainActivity extends AppCompatActivity implements android.view.View.OnClickListener {
    private EditText edtUserName,edtpassword,edtEmail;
    Button btnLogin,btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ParseInstallation.getCurrentInstallation().saveInBackground();// build a connection with parse server
        edtEmail=findViewById(R.id.edtEmail);
        edtpassword=findViewById(R.id.edtPassword);
        edtUserName=findViewById(R.id.edtUserName);
        btnLogin=findViewById(R.id.btnLogin);
        btnSignUp=findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(MainActivity.this);
        btnLogin.setOnClickListener(MainActivity.this);

    }

    @Override
    public void onClick(android.view.View v) {
        switch (v.getId()) {
            case R.id.btnSignUp:
                if (edtEmail.getText().toString().equals("")||
                        edtpassword.getText().toString().equals("")||edtUserName.getText().toString().equals("")){
                    com.shashank.sony.fancytoastlib.FancyToast.makeText(MainActivity.this,"NEED ALL REQUIRED TO FILL UP ", com.shashank.sony.fancytoastlib.FancyToast.LENGTH_SHORT, com.shashank.sony.fancytoastlib.FancyToast.INFO,true).show();

                }else {

                    final com.parse.ParseUser AppUser = new com.parse.ParseUser();
                    AppUser.setEmail(edtEmail.getText().toString());
                    AppUser.setPassword(edtpassword.getText().toString());
                    AppUser.setUsername(edtUserName.getText().toString());
                    final android.app.ProgressDialog process = new android.app.ProgressDialog(MainActivity.this);
                    process.setMessage(" Signing Up " + edtUserName.getText().toString());
                    process.show();
                        AppUser.signUpInBackground(new com.parse.SignUpCallback() {
                        @Override
                        public void done(com.parse.ParseException e) {

                            if (e == null) {
                                trasferToTweet();
                                com.shashank.sony.fancytoastlib.FancyToast.makeText(MainActivity.this, AppUser.getUsername() + " is Signed up", com.shashank.sony.fancytoastlib.FancyToast.LENGTH_SHORT, com.shashank.sony.fancytoastlib.FancyToast.SUCCESS, true).show();
                                    finish();
                            } else {
                                com.shashank.sony.fancytoastlib.FancyToast.makeText(MainActivity.this, e.getMessage() + "ERROR", com.shashank.sony.fancytoastlib.FancyToast.LENGTH_LONG, com.shashank.sony.fancytoastlib.FancyToast.ERROR, true).show();

                            }
                            process.dismiss();
                        }
                    });
                }
                break;
            case R.id.btnLogin:
                android.content.Intent intent=new android.content.Intent(MainActivity.this,Login.class);
                startActivity(intent);
               finish();
                break;
        }
    }


    private void trasferToTweet() {
        android.content.Intent intent = new android.content.Intent(com.example.tweet.MainActivity.this, com.example.tweet.Tweet_Post.class);
        intent.setFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK | android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}

