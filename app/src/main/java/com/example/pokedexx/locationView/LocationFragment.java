package com.example.pokedexx.locationView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokedexx.MainActivity;
import com.example.pokedexx.R;
import com.example.pokedexx.adapters.RecyclerLocationAdapter;
import com.example.pokedexx.pokemonView.cards;
import com.example.pokedexx.pokemonView.pokeRet;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LocationFragment extends Fragment implements RecyclerLocationAdapter.onCardListener {
    static RecyclerLocationAdapter mRecyclerAdapter;
    static RecyclerView mRecyclerView = null;
    static ArrayList<String> mlocations = new ArrayList<>();
    static ArrayList<String> mTypes = new ArrayList<>();
    static ArrayList<String> mRegions = new ArrayList<>();
    static ArrayList<String> mlocationsPoke = new ArrayList<>();
    JSONArray jsonArray1=new JSONArray();

    static ArrayList<String> mTypesPoke = new ArrayList<>();
    static ArrayList<String> mRegionsPoke = new ArrayList<>();
    String myValue;
    private static final String TAG = "LocationFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.location_view, container, false);
        mRecyclerView = view.findViewById(R.id.recyclerViewLocation);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            myValue = this.getArguments().getString("selected");
            initRecyclerView(myValue);
            if (mlocations.isEmpty() && mTypes.isEmpty() && mRegions.isEmpty())
                getValues();
        }
        return view;
    }

    void initRecyclerView(String string) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        switch (string) {
            case "1":
                mRecyclerAdapter = new RecyclerLocationAdapter(mlocations, this);
                break;
            case "3":
                mRecyclerAdapter = new RecyclerLocationAdapter(mTypes, this);
                break;
            case "2":
                mRecyclerAdapter = new RecyclerLocationAdapter(mRegions, this);
                break;
        }
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

                Call<locations> calls = dummyapi.getLocation();
                Call<ResponseBody> calls1 = dummyapi.getTypes();
                Call<ResponseBody> calls2 = dummyapi.getRegions();
                try {
                    ResponseBody responseBody = calls1.execute().body();
                    ResponseBody responseBody1 = calls2.execute().body();
                    JSONObject jsonArray = new JSONObject(responseBody.string());
                    JSONObject jsonArray1 = new JSONObject(responseBody1.string());
                    for (int k = 0; k < 20; k++) {
                        if (k < 7) {
                            String string = jsonArray1.getJSONArray("results").getJSONObject(k).getString("name");
                            mRegions.add(string);
                        }
                        String string1 = jsonArray.getJSONArray("results").getJSONObject(k).getString("name");
                        mTypes.add(string1);

                    }
                    locations cards = calls.execute().body();
                    for (locations.Result r : cards.getResults()) {
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
        switch (myValue) {
            case "3": {
                values values = new values();
                values.execute(position + 1);
                break;
            }
            case "1": {
                values1 values = new values1();
                values.execute(position + 1);
                break;

            }
            case "2":{
                values2(position+1);
                break;
            }
        }

    }

    public class values extends AsyncTask<Integer, Void, Boolean> {


        @Override
        protected Boolean doInBackground(Integer... ints) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://pokeapi.co/api/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            locatRet dummyapi;
            dummyapi = retrofit.create(locatRet.class);

            Call<PokeType> calls = dummyapi.getPokeTypes(ints[0]);
            try {
                PokeType cards = calls.execute().body();
                mTypesPoke.clear();
                for (PokeType.Pokemon s : cards.pokemon)
                    mTypesPoke.add(s.pokemon.name);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            Bundle bundle = new Bundle();
            bundle.putString("selected", "3");
            ((MainActivity) getActivity()).locationPokeFragment.setArguments(bundle);

//            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

            //          transaction.addToBackStack(null);
            //        transaction.add(R.id.fragmentContainer, fragment).commit();
//            FragmentManager fragmentManager = getFragmentManager();
//            fragmentManager.beginTransaction()
//                    .replace(R.id.fragmentContainer, fragment)
//                    .commit();
            ((MainActivity) getActivity()).mViewPager.getAdapter().notifyDataSetChanged();
            ((MainActivity) getActivity()).setViewPage(4);
            super.onPostExecute(aBoolean);
        }
    }

    public class values1 extends AsyncTask<Integer, Void, Boolean> {


        @Override
        protected Boolean doInBackground(Integer... ints) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://pokeapi.co/api/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            locatRet dummyapi;
            dummyapi = retrofit.create(locatRet.class);
            Call<areas> calls = dummyapi.getAreaPokes(ints[0]);
            try {
                areas cards = calls.execute().body();
                mlocationsPoke.clear();
                for (areas.Area s : cards.areas) {
                    Call<ResponseBody> responseBodyCall = dummyapi.getLocationPokeLIst(s.name);
                    ResponseBody responseBody = responseBodyCall.execute().body();
                    JSONObject jsonObject = new JSONObject(responseBody.string());
                    JSONArray jsonArray = jsonObject.getJSONArray("pokemon_encounters");
                    for (int k = 0; k < jsonArray.length(); k++) {
                        mlocationsPoke.add(jsonArray.getJSONObject(k).getJSONObject("pokemon").getString("name"));
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            Bundle bundle = new Bundle();
            bundle.putString("selected", "1");
            ((MainActivity) getActivity()).locationPokeFragment.setArguments(bundle);

//            Fragment fragment = new LocationPokeFragment();
//            ((MainActivity)getActivity()).locationPokeFragment.setArguments(bundle);

//            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

            //          transaction.addToBackStack(null);
            //        transaction.add(R.id.fragmentContainer, fragment).commit();
//            FragmentManager fragmentManager = getFragmentManager();
//            fragmentManager.beginTransaction()
//                    .replace(R.id.fragmentContainer, fragment)
//                    .commit();
            ((MainActivity) getActivity()).mViewPager.getAdapter().notifyDataSetChanged();
            ((MainActivity) getActivity()).setViewPage(4);

        }
    }

    public void values2(int a){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        locatRet dummyapi;
        dummyapi =retrofit.create(locatRet .class);
        Call<ResponseBody> call=dummyapi.getRegionPokeLIst(1);
        mRegionsPoke.clear();

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    JSONObject jsonObject1 = null;
                    jsonObject1 = new JSONObject(response.body().string());
                    jsonArray1 = jsonObject1.getJSONArray("locations");
                    for (int k = 0; k < jsonArray1.length(); k++) {
                        Call<areas> calls = null;
                        try {
                            calls = dummyapi.getAreaPokes(jsonArray1.getJSONObject(k).getString("name"));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        int finalK = k;
                        calls.enqueue(new Callback<areas>() {
                            @Override
                            public void onResponse(Call<areas> call, Response<areas> response) {
                                areas areas = response.body();
                                 int o=0;
                                for (areas.Area s : areas.areas) {
                                    o++;
                                    Call<ResponseBody> responseBodyCall = dummyapi.getLocationPokeLIst(s.name);
                                    int finalO = o;
                                    responseBodyCall.enqueue(new Callback<ResponseBody>() {
                                        @Override
                                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                            ResponseBody responseBody = response.body();
                                            try {
                                                JSONObject jsonObject = new JSONObject(responseBody.string());
                                                JSONArray jsonArray = jsonObject.getJSONArray("pokemon_encounters");
                                                for (int ik = 0; ik < jsonArray.length(); ik++) {
                                                    if(!mRegionsPoke.contains(jsonArray.getJSONObject(ik).getJSONObject("pokemon").getString("name")))
                                                        mRegionsPoke.add(jsonArray.getJSONObject(ik).getJSONObject("pokemon").getString("name"));
                                                }
                                                if((finalK ==jsonArray1.length()-1)&& (finalO ==areas.areas.size())){
                                                    Bundle bundle = new Bundle();
                                                    bundle.putString("selected", "2");
                                                    ((MainActivity) getActivity()).locationPokeFragment.setArguments(bundle);
                                                    ((MainActivity) getActivity()).mViewPager.getAdapter().notifyDataSetChanged();
                                                    ((MainActivity) getActivity()).setViewPage(4);

                                                }
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                                        }

                                    });
                                }
                            }

                            @Override
                            public void onFailure(Call<areas> call, Throwable t) {

                            }
                        });


                    }

                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });


    }


}
//    public class values2 {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://pokeapi.co/api/v2/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        locatRet dummyapi;
//        dummyapi =retrofit.create(locatRet .class);
//        Call<ResponseBody> calls = dummyapi.getRegionPokeLIst(ints[0]);
//
//
//        @Override
//        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//            JSONObject jsonObject1 = null;
//            jsonObject1 = new JSONObject(response.body().string());
//            JSONArray jsonArray1 = jsonObject1.getJSONArray("locations");
//            for (int k = 0; k < jsonArray1.length(); k++) {
//                Call<areas> calls = dummyapi.getAreaPokes(jsonArray1.getJSONObject(k).getString("name"));
//                areas cards = calls.execute().body();
//                mRegionsPoke.clear();
//                for (areas.Area s : cards.areas) {
//                    Call<ResponseBody> responseBodyCall = dummyapi.getLocationPokeLIst(s.name);
//                    ResponseBody responseBody = responseBodyCall.execute().body();
//                    JSONObject jsonObject = new JSONObject(responseBody.string());
//                    JSONArray jsonArray = jsonObject.getJSONArray("pokemon_encounters");
//                    for (int k = 0; k < jsonArray.length(); k++) {
//                        mlocationsPoke.add(jsonArray.getJSONObject(k).getJSONObject("pokemon").getString("name"));
//                    }
//                }
//            }
//        });
//    }












