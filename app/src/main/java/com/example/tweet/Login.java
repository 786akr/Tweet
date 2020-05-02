package com.example.tweet;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class Login extends AppCompatActivity implements android.view.View.OnClickListener {
    private EditText edtpassword1, edtEmail1;
    private Button btnLogin1, btnSignUp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Login");
        edtEmail1 = findViewById(R.id.btnEmailLoginActivity);
        edtpassword1 = findViewById(R.id.btnPasswordLoginActivity);
        btnLogin1 = findViewById(R.id.btnLoginLoginActivity);
        btnSignUp1 = findViewById(R.id.btnSignUpLoginActivity);
        btnSignUp1.setOnClickListener(Login.this);
        btnLogin1.setOnClickListener(Login.this);

    }

    @Override
    public void onClick(android.view.View v) {
        switch (v.getId()) {
            case R.id.btnLoginLoginActivity:
                if (edtEmail1.getText().toString().equals("") || edtpassword1.getText().toString().equals("")) {
                    com.shashank.sony.fancytoastlib.FancyToast.makeText(Login.this, "Fill Both to Login ", com.shashank.sony.fancytoastlib.FancyToast.LENGTH_SHORT, com.shashank.sony.fancytoastlib.FancyToast.INFO, true).show();

                } else {
                    com.parse.ParseUser.logInInBackground(edtEmail1.getText().toString(), edtpassword1.getText().toString(), new com.parse.LogInCallback() {
                        @Override
                        public void done(com.parse.ParseUser user, com.parse.ParseException e) {
                            if (user != null && e == null) {
                                trasferToTweet();
                                com.shashank.sony.fancytoastlib.FancyToast.makeText(Login.this,
                                        " is Login", com.shashank.sony.fancytoastlib.FancyToast.LENGTH_SHORT, com.shashank.sony.fancytoastlib.FancyToast.SUCCESS, true)
                                        .show();
                                finish();
                            } else {
                                com.shashank.sony.fancytoastlib.FancyToast.makeText(Login.this,
                                        "Eror", com.shashank.sony.fancytoastlib.FancyToast.LENGTH_SHORT, com.shashank.sony.fancytoastlib.FancyToast.SUCCESS, true)
                                        .show();

                            }

                        }


                    });
                }
                break;
            case R.id.btnSignUpLoginActivity:
                android.content.Intent intent = new android.content.Intent(Login.this, MainActivity.class);
                 startActivity(intent);
                      finish();
                break;

        }
    }
    private void trasferToTweet() {
        android.content.Intent intent = new android.content.Intent(com.example.tweet.Login.this, com.example.tweet.Tweet_Post.class);
        intent.setFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK | android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
