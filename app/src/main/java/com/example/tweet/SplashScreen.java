package com.example.tweet;

public class SplashScreen extends androidx.appcompat.app.AppCompatActivity {
    @Override
    protected void onCreate(@androidx.annotation.Nullable android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new android.content.Intent(this,MainActivity.class));
finish();
    }
}
