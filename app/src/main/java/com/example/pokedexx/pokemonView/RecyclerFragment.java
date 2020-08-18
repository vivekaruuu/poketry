package com.example.pokedexx.pokemonView;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokedexx.MainActivity;
import com.example.pokedexx.R;
import com.example.pokedexx.adapters.RecyclerAdapter;
import com.example.pokedexx.bioFragment;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecyclerFragment extends Fragment implements RecyclerAdapter.onCardListener {
    static RecyclerAdapter mRecyclerAdapter;
    static RecyclerView mRecyclerView;
    static ArrayList<cards> mCards=new ArrayList<>();
//    HashMap<Integer,cards> mCards
//            = new HashMap<>();
   // static ArrayList<cards> mCards=new ArrayList<cards>Collections.nCopies(60, 0)
    //static ArrayList
    private static final String TAG = "RecyclerFragment";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.activity_main,container,false);
        mRecyclerView=view.findViewById(R.id.recyclerView);
        initRecyclerView();

        if(mCards.isEmpty()) {
            values values=new values();
            values.execute();
        }
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

//    public void val(){
//        Retrofit retrofit=new Retrofit.Builder()
//                .baseUrl("https://pokeapi.co/api/v2/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        pokeRet dummyapi;
//        dummyapi = retrofit.create(pokeRet.class);
//        for (int k=1;k<23;k++) {
//            Call<cards> calls = dummyapi.getCards(k);
//            try {
//                int finalK = k;
//                calls.enqueue(new Callback<com.example.pokedexx.pokemonView.cards>() {
//                    @Override
//                    public void onResponse(Call<com.example.pokedexx.pokemonView.cards> call, Response<com.example.pokedexx.pokemonView.cards> response) {
//                        cards cards = response.body();
//                        int v = finalK - 1;
//                        Log.d(TAG, "onResponse: "+v);
//                        mCards.put(v, cards);
//                        if(v==22){
//                            dor();
//                        }
//                        // mCards.add(finalK-1,cards);
//                    }
//
//                    @Override
//                    public void onFailure(Call<cards> call, Throwable t) {
//
//                    }
//
//                });
//
//            }
//            catch (Exception e){
//                e.printStackTrace();
//            }
//
//        }
//
//
//
//
//
//    }
//    void dor(){
//        for(int k=1;k<23;k++){
//            int finalK = k;
//            Picasso.get().load(mCards.get(k-1).getPicture()).into(new Target() {
//                @Override
//                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
//                    // cards cardsNew = new cards(cards.name, cards.sprites,bitmap);
//                    mCards.get(finalK -1).bitmap = bitmap;
//
//                    mRecyclerAdapter.notifyDataSetChanged();
//
//
//
//                }
//
//                @Override
//                public void onBitmapFailed(Exception e, Drawable errorDrawable) {
//
//                }
//
//                @Override
//                public void onPrepareLoad(Drawable placeHolderDrawable) {
//
//                }
//            });
//        }
//    }


    public class values extends AsyncTask<String, Void, ArrayList<cards>>{



        @Override
        protected ArrayList<cards> doInBackground(String... strings) {
            Retrofit retrofit=new Retrofit.Builder()
                    .baseUrl("https://pokeapi.co/api/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            pokeRet dummyapi;
            dummyapi = retrofit.create(pokeRet.class);
            for (int k=1;k<900;k++) {
                Call<cards> calls = dummyapi.getCards(k);
                try {
                    cards cards = calls.execute().body();
                    mCards.add(cards);
                    if(getActivity()!=null) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mRecyclerAdapter.notifyDataSetChanged();
                            }
                        });
                    }


                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<cards> aBoolean) {
         //   mRecyclerAdapter.notifyDataSetChanged();
//            for(int k=0;k<29;k++) {
//
//                int finalK = k;
//                Picasso.get().load(mCards.get(k).getPicture()).into(new Target() {
//                    @Override
//                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
//                        // cards cardsNew = new cards(cards.name, cards.sprites,bitmap);
//                        mCards.get(finalK).bitmap = bitmap;
//                        mRecyclerAdapter.notifyDataSetChanged();
//
//                    }
//
//                    @Override
//                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {
//
//                    }
//
//                    @Override
//                    public void onPrepareLoad(Drawable placeHolderDrawable) {
//
//                    }
//                });
            //}
        }
    }

//    void getValues(){
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Retrofit retrofit=new Retrofit.Builder()
//                        .baseUrl("https://pokeapi.co/api/v2/")
//                        .addConverterFactory(GsonConverterFactory.create())
//                        .build();
//                pokeRet dummyapi;
//                dummyapi = retrofit.create(pokeRet.class);
//                for (int k=1;k<30;k++) {
//                    Call<cards> calls = dummyapi.getCards(k);
//                    try {
//                        cards cards = calls.execute().body();
//                        getActivity().runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                Picasso.get().load(cards.getPicture()).into(new Target() {
//                                    @Override
//                                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
//                                        cards cardsNew = new cards(cards.name, cards.sprites,bitmap);
//                                      //  mCards.add(cardsNew);
//                                        mRecyclerAdapter.notifyDataSetChanged();
//
//                                    }
//
//                                    @Override
//                                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {
//
//                                    }
//
//                                    @Override
//                                    public void onPrepareLoad(Drawable placeHolderDrawable) {
//
//                                    }
//                                });
//                            }
//                        });
//                        getActivity().runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                Picasso.get().load(cards.getPicture()).into(new Target() {
//                                    @Override
//                                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
//                                        cards cardsNew = new cards(cards.name, cards.sprites,bitmap);
//
//                                        mCards.add(cardsNew);
//                                        mRecyclerAdapter.notifyDataSetChanged();
//                                    }
//
//                                    @Override
//                                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {
//
//                                    }
//
//                                    @Override
//                                    public void onPrepareLoad(Drawable placeHolderDrawable) {
//
//                                    }
//                                });
//
//
//                            }
//                        });


//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//
//            }
//
//        }).start();
////        Log.d(TAG, "getValues: vgjvlgjvlgb vj");
////        Intent intent=new Intent(MainActivity.this,BackgroundService.class);
////        startService(intent);
//    }


    @Override
    public void onNoteClick(int position) {
        Bundle bundle=new Bundle();
        bundle.putInt("card",position);
        ((MainActivity)getActivity()).bioFragment.setArguments(bundle);
        ((MainActivity)getActivity()).mViewPager.getAdapter().notifyDataSetChanged();
        ((MainActivity)getActivity()).setViewPage(1);
      //  getActivity().getSupportFragmentManager().beginTransaction().add(R.layout.poke_bio,new bioFragment(), "fragment_tag").commit();
      //  fragment.getParentFragmentManager().beginTransaction().replace((R.id.fragmentContainer),fragment).commit();
//        fragmentManager.beginTransaction()?.add(R.id.container, ListFragment(), LIST_FRAG_TAG)?.commit();
//        getActivity().getSupportFragmentManager().beginTransaction().replace((R.id.fragmentContainer),fragment).commit();
    }



}
