package com.example.pokedexx;

import retrofit2.Call;
import retrofit2.http.GET;

public interface pokeRet {
    @GET("pokemon/132")
    Call<cards> getCards();
}
