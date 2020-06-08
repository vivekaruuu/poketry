package com.example.pokedexx.itemView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokedexx.R;
import com.example.pokedexx.adapters.RecyclerAdapterItem;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ItemFragment extends Fragment implements RecyclerAdapterItem.onCardListener {
    static RecyclerAdapterItem mRecyclerAdapter;
    static RecyclerView mRecyclerView;
    static ArrayList<item> mItems=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.item_view, container, false);
        mRecyclerView=view.findViewById(R.id.recyclerViewItem);
        initRecyclerView();
        getValues();
        return view;
    }
    void initRecyclerView(){
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),4)
        {
            @Override
            public boolean checkLayoutParams(RecyclerView.LayoutParams lp) {
                // force height of viewHolder here, this will override layout_height from xml
                lp.height = ((int) (getHeight() / 5.7));
                lp.width= ((int) (getWidth() / (4.1)));
                return true;
            }
        });
        mRecyclerAdapter=new RecyclerAdapterItem(mItems,this);
        mRecyclerView.setAdapter(mRecyclerAdapter);
    }
    void getValues() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://pokeapi.co/api/v2/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                itemRet dummyapi;
                for (int k = 1; k < 100; k++) {
                    dummyapi = retrofit.create(itemRet.class);
                    Call<item> calls = dummyapi.getItem(k);
                    try {
                        item cards = calls.execute().body();
                        item cardsNew = new item(cards.name, cards.sprites);
                        mItems.add(cardsNew);
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
    }

        @Override
        public void onNoteClick ( int position){
        }

}
