package com.example.pokedexx.pokemonView;

import com.example.pokedexx.pokemonView.cards;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface pokeRet {
    @GET("pokemon/{no}")
    Call<cards> getCards(@Path("no")int id);
}
