package com.example.pokedexx.locationView;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface locatRet {
    @GET("location?limit=100")
    Call<locations> getLocation();

    @GET("location?limit=100")
    Call<ResponseBody> getloc();
}
//JSONArray jsonArray=new JSONArray(ResponseBody.body().String())