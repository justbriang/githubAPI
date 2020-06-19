package com.example.githubapi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.githubapi.R;
import com.example.githubapi.model.GitHubUser;
import com.squareup.picasso.Picasso;

import java.util.List;


public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyViewHolder> {
    private List<GitHubUser> tasks;
    Context context;

    public UsersAdapter(Context context, List<GitHubUser> tasks) {
        this.context = context;
        this.tasks = tasks;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name;

        public TextView url;
        public ImageView img;


        public MyViewHolder(View v) {
            super(v);
            this.img = v.findViewById(R.id.useravatar);
            this.name = (TextView) v.findViewById(R.id.username);
            this.url = (TextView) v.findViewById(R.id.url);

        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.gituser_list, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UsersAdapter.MyViewHolder holder, int position) {
        final GitHubUser currentUser = tasks.get(position);
        holder.url.setText(currentUser.getUrl());
        holder.name.setText(currentUser.getLogin());
        Picasso.with(context).load(currentUser.getAvatar()).into(holder.img);


    }

    @Override
    public int getItemCount() {
        return tasks.size();

    }
}
