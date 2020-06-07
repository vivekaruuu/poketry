package com.example.pokedexx;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface itemRet {
    @GET("item/{no}")
    Call<item> getItem(@Path("no")int id);
}
