package com.example.onaapplication.network;

import com.example.onaapplication.models.OnaApiResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface OnaApi {

    @GET("users")
    Call<List<OnaApiResponse>> getUsers();
}
