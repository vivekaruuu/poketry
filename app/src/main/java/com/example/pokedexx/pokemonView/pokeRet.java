package com.example.pokedexx.pokemonView;

import com.example.pokedexx.pokemonView.cards;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface pokeRet {
    @GET("pokemon/{no}")
    Call<cards> getCards(@Path("no")int id);
    @GET("pokemon/{no}")
    Call<cards> getCards(@Path("no")String id);
    @GET("pokemon/{no}")
    Call<ResponseBody>getBody(@Path("no")int id);
    @GET("pokemon-species/{no}")
    Call<ResponseBody>getChain(@Path("no")int id);

    @GET("evolution-chain/{no}")
    Call<ResponseBody>getPokes(@Path("no")int id);


}
