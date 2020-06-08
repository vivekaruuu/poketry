package com.example.pokedexx.itemView;

import com.example.pokedexx.itemView.item;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface itemRet {
    @GET("item/{no}")
    Call<item> getItem(@Path("no")int id);
}
