package com.example.githubapi.repositories;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.githubapi.Rest.APIClient;
import com.example.githubapi.Rest.GitHubUserEndPoints;
import com.example.githubapi.interfaces.GithubUsersInterface;
import com.example.githubapi.model.GitHubUser;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

import static android.content.ContentValues.TAG;

public class GithubUsersRepo {
    private List<GitHubUser> arrayofUsers = new ArrayList<>();
    private static GithubUsersRepo instance;
    private Context context;

    public GithubUsersRepo(Context context) {
        this.context = context;
    }

    //singleton
    public static GithubUsersRepo getInstance(Context context) {

        if (instance == null) {
            instance = new GithubUsersRepo(context);

        }
        return instance;
    }
    public MutableLiveData<List<GitHubUser>> getUsers() {
        MutableLiveData<List<GitHubUser>> data = new MutableLiveData<>();
        setUsers(new GithubUsersInterface() {


            @Override
            public void getUsers(List<GitHubUser> Users) {

                data.setValue(arrayofUsers);
                Log.e("data", "" + data);

            }
        });

        return data;



    }
    //data retrieval from api
    public void setUsers(GithubUsersInterface taskscallback) {


        final GitHubUserEndPoints apiService = APIClient.getClient().create(GitHubUserEndPoints.class);


        Call<GitHubUser> call = apiService.getUsers();


        call.enqueue(new Callback<GitHubUser>() {


            @Override
            public void onResponse(Call<GitHubUser> call, retrofit2.Response<GitHubUser> response) {


                if (response.body() != null) {
                    Log.w("thius ", "" + response.body());
                    String data = new Gson().toJson(response.body());

                    JsonArray jsonArray = new JsonParser().parse(data).getAsJsonArray();
                    for (JsonElement e : jsonArray) {
//                        Log.d("MainActvity", String.valueOf(e));
                        JsonObject obj = new JsonParser().parse(String.valueOf(e)).getAsJsonObject();
                        try {
                            JSONObject jo2 = new JSONObject(obj.toString());
//                           Log.w(TAG, "Response: " + jo2);
                            GitHubUser gitHubUser = new GitHubUser();
                            gitHubUser.setLogin(jo2.getString("login"));
                            gitHubUser.setUrl(jo2.getString("url"));
                            gitHubUser.setAvatar(jo2.getString("avatar_url"));

                            arrayofUsers.add(gitHubUser);
//                            Log.e("mainact",""+task.getTitle());
                        } catch (JSONException ex) {
                            ex.printStackTrace();
                        }

                        taskscallback.getUsers(arrayofUsers);
                    }
                }

                 else {
                    System.out.println("log in body empty");

                }
            }
            @Override
            public void onFailure(Call<GitHubUser> call, Throwable t) {
//                System.out.println("log in failed ");

                Log.d(TAG, "login failed");
                t.printStackTrace();
            }
        });


    }
}


