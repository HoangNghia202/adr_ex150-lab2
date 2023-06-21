package com.example.ex15_lab2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ArrayList<Post> postList;
    RcvPostAdapter rcvPostAdapter;
    RecyclerView rcvPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPosts();
    }

    private void getPosts() {
        Call<List<Post>> call = RetrofitClient.getInstance().getMyApi().getAllposts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                List<Post> posts = response.body();
                Log.i("posts", "onResponse: "+ posts.get(1).getTitle());
                postList = new ArrayList<>();
                for (int i = 0; i < posts.size(); i++) {
                    postList.add(new Post(posts.get(i).getTitle(), posts.get(i).getBody()));
                }

                setContentView(R.layout.activity_main);
//
                Log.i("Post list", "onCreate: "+ postList);
                rcvPost = findViewById(R.id.rvPosts);
                rcvPostAdapter = new RcvPostAdapter(postList);
                rcvPost.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false));
                rcvPost.setAdapter(rcvPostAdapter);

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured",
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}