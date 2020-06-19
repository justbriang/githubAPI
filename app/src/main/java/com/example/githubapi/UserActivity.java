package com.example.githubapi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.util.Linkify;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.githubapi.Rest.APIClient;
import com.example.githubapi.Rest.GitHubUserEndPoints;
import com.example.githubapi.model.GitHubUser;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserActivity extends AppCompatActivity {
    ImageView avatarImg;
    TextView userName, url;
Toolbar toolbar;
    Bundle extras;
    String newString;
    private static final String TAG = "Logging Example";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        avatarImg = findViewById(R.id.useravatar);
        userName = findViewById(R.id.usersusername);
        url = findViewById(R.id.userurl);
        Intent getI = getIntent();
        String name = getI.getStringExtra("username");
        String img = getI.getStringExtra("image");
        String passedurl = getI.getStringExtra("url");
        Log.e(TAG, "onItemClick: " + url);
        userName.setText(name);

        Picasso.with(this).load(img).into(avatarImg);
        url.setText(passedurl);
        Linkify.addLinks(url, Linkify.ALL);
        ImageButton sharingButton = new ImageButton(this);
        sharingButton.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        sharingButton.setImageResource(R.drawable.ic_attachment_black_24dp);

    }
}
