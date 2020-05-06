package com.example.onaapplication.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
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

    ProgressDialog progressDialog;
    private int progressStatus = 0;
    private Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);
        ButterKnife.bind(this);

        progressDialog = new ProgressDialog(this);
        String onaUsers = "Ona Users List";
        String currentlyLoading = "Currently Loading";

        showProgressDialogWithTitle(onaUsers, currentlyLoading);

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
                hideProgressDialogWithTitle();
                mUsersRecyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<List<OnaApiResponse>> call, Throwable t) {
                Log.d("FAILURE: ", "could not fetch data successfully");

            }
        });


    }

    private void showProgressDialogWithTitle(String title,String substring) {
        progressDialog.setTitle(title);
        progressDialog.setMessage(substring);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(false);
        progressDialog.setMax(100);
        progressDialog.show();

        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
                    try{

                        Thread.sleep(600);
                        progressStatus += 5;
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    handler.post(new Runnable() {
                        public void run() {
                            progressDialog.setProgress(progressStatus);
                        }
                    });
                }
            }
        }).start();

    }

    private void hideProgressDialogWithTitle() {
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.dismiss();
    }
}
