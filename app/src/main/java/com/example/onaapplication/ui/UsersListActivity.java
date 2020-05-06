package com.example.onaapplication.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.onaapplication.R;
import com.example.onaapplication.adapters.UsersListAdapter;
import com.example.onaapplication.models.OnaApiResponse;
import com.example.onaapplication.network.OnaApi;
import com.example.onaapplication.network.OnaClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersListActivity extends AppCompatActivity {
    private UsersListAdapter mUsersListAdapter;
    private List<OnaApiResponse> mUsers;

    @BindView(R.id.userRecyclerView) RecyclerView mUsersRecyclerView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);
        ButterKnife.bind(this);

        OnaApi client = OnaClient.getClient();
        Call<List<OnaApiResponse>> call = client.getUsers();

        call.enqueue(new Callback<List<OnaApiResponse>>() {
            @Override
            public void onResponse(Call<List<OnaApiResponse>> call, Response<List<OnaApiResponse>> response) {
                Log.d("IN THE SUCCESS_____:", "***IN THE SUCCESS***");
                if(response.isSuccessful()){
                    assert response.body() != null;
                    onSuccessAction();
                    mUsers = response.body();
                    System.out.println(mUsers);
                    mUsersListAdapter = new UsersListAdapter(UsersListActivity.this, mUsers);
                    mUsersRecyclerView.setAdapter(mUsersListAdapter);

                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(UsersListActivity.this);
                    mUsersRecyclerView.setLayoutManager(layoutManager);
                    mUsersRecyclerView.setHasFixedSize(true);


                }
            }

            private void onSuccessAction() {
                mProgressBar.setVisibility(View.GONE);
                mUsersRecyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<List<OnaApiResponse>> call, Throwable t) {
                Log.d("FAILURE: ", "could not fetch data successfully");

            }
        });


    }
}
