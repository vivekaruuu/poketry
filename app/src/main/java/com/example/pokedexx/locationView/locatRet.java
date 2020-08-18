package com.example.pokedexx.locationView;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface locatRet {
    @GET("location?limit=100")
    Call<locations> getLocation();

    @GET("region")
    Call<ResponseBody> getRegions();

    @GET("type")
    Call<ResponseBody> getTypes();

    @GET("type/{no}")
    Call<PokeType> getPokeTypes(@Path("no")int id);

    @GET("location/{no}")
    Call<areas> getAreaPokes(@Path("no")int id);
    @GET("location/{no}")
    Call<areas> getAreaPokes(@Path("no")String id);

    @GET("location-area/{no}")
    Call<ResponseBody> getLocationPokeLIst(@Path("no")String id);

    @GET("region/{no}")
    Call<ResponseBody> getRegionPokeLIst(@Path("no")int id);


}