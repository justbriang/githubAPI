package com.example.githubapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.githubapi.adapters.RecyclerItemClickListener;
import com.example.githubapi.adapters.UsersAdapter;
import com.example.githubapi.model.GitHubUser;
import com.example.githubapi.viewmodels.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MainActivityViewModel mainActivityViewModel;
    private RecyclerView recyclerView;
    private UsersAdapter usersAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<GitHubUser> users = new ArrayList<>();
    GitHubUser gitHubUser;
    private static final String TAG = "Logging Example";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        mainActivityViewModel.getTasks().observe(this, new Observer<List<GitHubUser>>() {
            @Override
            public void onChanged(List<GitHubUser> gitHubUsers) {
              users=gitHubUsers;
                initRecyclerView(gitHubUsers);
                Log.e("mainact", "" + gitHubUsers.toString());
                usersAdapter.notifyDataSetChanged();
            }
        });
        initRecyclerView(users);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(MainActivity.this,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                                gitHubUser = users.get(position);
                                Intent dintent = new Intent(MainActivity.this, UserActivity.class);

                                dintent.putExtra("username", gitHubUser.getLogin());
                                dintent.putExtra("image", gitHubUser.getAvatar());
                                dintent.putExtra("url", gitHubUser.getUrl());
//                                Log.e(TAG, "onItemClick: "+ gitHubUser.getUrl());
                                startActivity(dintent);

                            }
                        }));
    }

    public void initRecyclerView(List<GitHubUser> users) {
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


        usersAdapter = new UsersAdapter(MainActivity.this, users);
        recyclerView.setAdapter(usersAdapter);

    }
}
