package com.example.pokedexx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.sargunvohra.lib.pokekotlin.client.PokeApi;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;
import me.sargunvohra.lib.pokekotlin.model.Pokemon;
import me.sargunvohra.lib.pokekotlin.model.PokemonForm;
import me.sargunvohra.lib.pokekotlin.model.PokemonFormSprites;
import me.sargunvohra.lib.pokekotlin.model.PokemonSpecies;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.onCardListener {

    RecyclerAdapter mRecyclerAdapter;
    RecyclerView mRecyclerView;
    ArrayList<cards> mCards=new ArrayList<>();
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView=findViewById(R.id.recyclerView);

//        PokeApi pokeApi = new PokeApiClient();
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//
//        StrictMode.setThreadPolicy(policy);
        initRecyclerView();
//        for(int k=1;k<40;k++){
           // PokemonForm aur = pokeApi.getPokemonForm(k);
           // String pokemon=pokeApi.getPokemon(k).getName();
           // String st = aur.getSprites().getBackShiny();
//            cards cards = new cards("viv", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/12.png");
//            mCards.add(cards);
           // mRecyclerAdapter.notifyDataSetChanged();
        //}

    }
    void initRecyclerView(){
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        mRecyclerAdapter=new RecyclerAdapter(mCards,this);
        mRecyclerView.setAdapter(mRecyclerAdapter);
    }
    void getValues(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        pokeRet dummyapi;
        dummyapi = retrofit.create(com.example.pokedexx.pokeRet.class);
        Call<cards> calls=dummyapi.getCards();
        calls.enqueue(new Callback<cards>() {
            @Override
            public void onResponse(Call<cards> call, Response<cards> response) {
                if(!response.isSuccessful()){
                    return;
                }
                cards cards= response.body();
                Log.e(TAG, "onResponse: dsfsdfs",null );
                Log.d(TAG, "onResponse: "+cards.name+"cards.pictureUrl");
            }

            @Override
            public void onFailure(Call<cards> call, Throwable t) {

            }
        });
    }




    @Override
    public void onNoteClick(int position) {
        Log.i("index",String.valueOf(position));

    }
}