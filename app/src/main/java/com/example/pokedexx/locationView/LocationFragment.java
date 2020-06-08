package com.example.pokedexx.locationView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokedexx.R;
import com.example.pokedexx.adapters.RecyclerLocationAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LocationFragment extends Fragment implements RecyclerLocationAdapter.onCardListener {
    static RecyclerLocationAdapter mRecyclerAdapter;
    static RecyclerView mRecyclerView;
    static ArrayList<String> mlocations=new ArrayList<>();
    private static final String TAG = "LocationFragment";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.location_view, container, false);
        mRecyclerView=view.findViewById(R.id.recyclerViewLocation);
        initRecyclerView();
        getValues();
        return view;
    }

    void initRecyclerView(){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerAdapter=new RecyclerLocationAdapter(mlocations,this);
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
                locatRet dummyapi;

                    dummyapi = retrofit.create(locatRet.class);
                   // Call<ResponseBody> responseBodyCall=dummyapi.getloc();
                    Call<locations> calls = dummyapi.getLocation();
                    try {
//                        ResponseBody responseBody=responseBodyCall.execute().body();
//                        JSONObject jsonArray=new JSONObject(responseBody.string());
//                        for(int k=0;k<10;k++) {
//                            String string = jsonArray.getJSONArray("results").getJSONObject(k).getString("name");
//                            Log.d(TAG, "run: "+string);
//                        }
                        locations cards = calls.execute().body();
                        for(locations.Result r:cards.getResults()){
                            mlocations.add(r.getName());
                        }
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

        }).start();
    }


    @Override
    public void onNoteClick(int position) {

    }
}

