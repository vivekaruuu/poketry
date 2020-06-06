package com.example.pokedexx;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecyclerFragment extends Fragment implements RecyclerAdapter.onCardListener {
    static RecyclerAdapter mRecyclerAdapter;
    static RecyclerView mRecyclerView;
    static ArrayList<cards> mCards=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.activity_main,container,false);
        mRecyclerView=view.findViewById(R.id.recyclerView);
        initRecyclerView();
        getValues();
        return view;
    }
    void initRecyclerView(){
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3)
        {
            @Override
            public boolean checkLayoutParams(RecyclerView.LayoutParams lp) {
                // force height of viewHolder here, this will override layout_height from xml
                lp.height = ((int) (getHeight() / 3.5));
                lp.width= ((int) (getWidth() / (3.1)));
                return true;
            }
        });
        mRecyclerAdapter=new RecyclerAdapter(mCards,this);
        mRecyclerView.setAdapter(mRecyclerAdapter);
    }
    void getValues(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl("https://pokeapi.co/api/v2/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                pokeRet dummyapi;
                for (int k=1;k<30;k++) {
                    dummyapi = retrofit.create(pokeRet.class);
                    Call<cards> calls = dummyapi.getCards(k);
                    try {
                        cards cards = calls.execute().body();
                        cards cardsNew = new cards(cards.name, cards.sprites);
                        mCards.add(cardsNew);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mRecyclerAdapter.notifyDataSetChanged();
                            }
                        });


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }

        }).start();
//        Log.d(TAG, "getValues: vgjvlgjvlgb vj");
//        Intent intent=new Intent(MainActivity.this,BackgroundService.class);
//        startService(intent);
    }


    @Override
    public void onNoteClick(int position) {
        Log.i("index",String.valueOf(position));

    }


}
