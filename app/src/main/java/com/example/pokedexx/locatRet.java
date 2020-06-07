package com.example.pokedexx;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface locatRet {
    @GET("location?limit=100")
    Call<locations> getLocation();
}
