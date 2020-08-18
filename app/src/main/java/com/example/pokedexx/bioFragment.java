package com.example.pokedexx;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pokedexx.pokemonView.RecyclerFragment;
import com.example.pokedexx.pokemonView.cards;
import com.example.pokedexx.pokemonView.pokeRet;
import com.google.gson.JsonArray;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class bioFragment extends Fragment  {
    private static final String TAG = "bioFragment";
    int mPosition;
    ImageView pokeImage;
    ImageView evoImage1;
    ImageView evoImage2;
    ImageView evoImage3;
    TextView name;
    TextView type1;
    TextView type2;
    ProgressBar hp;
    ProgressBar attack;
    ProgressBar defense;
    ProgressBar spDef;
    ProgressBar spAtt;
    ProgressBar speed;
    String type2a;
    String urlEnd;
    String urlMid;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.poke_bio, container, false);
        Bundle bundle = this.getArguments();
        if(bundle!=null)
        {
              mPosition = bundle.getInt("card");

            pokeImage = view.findViewById(R.id.image_poke);
            evoImage1 = view.findViewById(R.id.image_evo1);
            evoImage2 = view.findViewById(R.id.image_ev02);
            evoImage3 = view.findViewById(R.id.image_evo3);
            name = view.findViewById(R.id.poke_name);
            type1 = view.findViewById(R.id.type1);
            type2 = view.findViewById(R.id.type2);
            hp = view.findViewById(R.id.progressBarHp);
            attack = view.findViewById(R.id.progressBarAttack);
            defense = view.findViewById(R.id.progressBarDefense);
            spAtt = view.findViewById(R.id.progressBarSpAtt);
            spDef = view.findViewById(R.id.progressBarSpDeff);
            speed = view.findViewById(R.id.progressSpeed);
            getValues();

        }
        return  view;
    }




    void getValues() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://pokeapi.co/api/v2/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                pokeRet dummyapi;
                    dummyapi = retrofit.create(pokeRet.class);
                    Call<ResponseBody> calls = dummyapi.getBody(mPosition+1);
                    Call<ResponseBody> calls1=dummyapi.getChain(mPosition+1);
                    Call<cards> callMid;
                    Call<cards> callEnd;
                    cards cardsMid;
                    cards cardsEnd;
                try {
                        ResponseBody responseBody = calls.execute().body();
                        ResponseBody responseBody1 = calls1.execute().body();
                        JSONObject jsonObject1=new JSONObject(responseBody1.string());
                        String url1=jsonObject1.getJSONObject("evolution_chain").getString("url");
                        String temp=url1.substring(42,url1.length()-1);
                    Call<ResponseBody> calls2=dummyapi.getPokes(Integer.parseInt(temp));
                        ResponseBody responseBody2 = calls2.execute().body();
                        JSONObject jsonObject2=new JSONObject(responseBody2.string());
                        JSONObject urlBase=jsonObject2.getJSONObject("chain");
                    dummyapi = retrofit.create(pokeRet.class);
                    String urlStart=urlBase.getJSONObject("species").getString("name");
                        if(!urlBase.getJSONArray("evolves_to").isNull(0)) {
                            urlMid = urlBase.getJSONArray("evolves_to").getJSONObject(0).getJSONObject("species").getString("name");
                            callMid = dummyapi.getCards(urlMid);
                            cardsMid = callMid.execute().body();
                            urlMid=cardsMid.getPicture();
                            if (!urlBase.getJSONArray("evolves_to").getJSONObject(0).getJSONArray("evolves_to").isNull(0)) {
                                urlEnd = urlBase.getJSONArray("evolves_to").getJSONObject(0).getJSONArray("evolves_to").getJSONObject(0).getJSONObject("species").getString("name");
                                callEnd = dummyapi.getCards(urlEnd);
                                cardsEnd = callEnd.execute().body();
                                urlEnd=cardsEnd.getPicture();

                            }
                        }



                        Call<cards> callStart = dummyapi.getCards(urlStart);
                        cards cardsStart = callStart.execute().body();

                        urlStart=cardsStart.getPicture();



                        JSONObject jsonObject=new JSONObject(responseBody.string());
                        String name1=jsonObject.getString("name");
                        String url=jsonObject.getJSONObject("sprites").getString("front_default");
                        String type1a=jsonObject.getJSONArray("types").getJSONObject(0).getJSONObject("type").getString("name");
                        if(!jsonObject.getJSONArray("types").isNull(1)) {
                             type2a = jsonObject.getJSONArray("types").getJSONObject(1).getJSONObject("type").getString("name");
                        }
                        JSONArray p=jsonObject.getJSONArray("stats");
                        String hp1=p.getJSONObject(0).getString("base_stat");
                        String attack1=p.getJSONObject(1).getString("base_stat");
                        String defense1=p.getJSONObject(2).getString("base_stat");
                        String spDef1=p.getJSONObject(4).getString("base_stat");
                        String spAtt1=p.getJSONObject(3).getString("base_stat");
                        String speed1=p.getJSONObject(5).getString("base_stat");


                        String finalUrlStart = urlStart;
                        String finalUrlMid = urlMid;
                        String finalUrlEnd = urlEnd;
                        if(((MainActivity)getActivity()!=null)) {
                            ((MainActivity) getActivity()).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    name.setText(name1);
                                    type1.setText(type1a);
                                    type2.setText(type2a);
//                                Drawable draw=getResources().getDrawable(R.drawable.progress);
//                                hp.setProgressDrawable(draw);
                                    hp.setProgress(Integer.parseInt(hp1));
                                    attack.setProgress(Integer.parseInt(attack1));
                                    defense.setProgress(Integer.parseInt(defense1));
                                    spAtt.setProgress(Integer.parseInt(spAtt1));
                                    spDef.setProgress(Integer.parseInt(spDef1));
                                    speed.setProgress(Integer.parseInt(speed1));
                                    evoImage1.setImageDrawable(null);
                                    evoImage2.setImageDrawable(null);
                                    evoImage3.setImageDrawable(null);

                                    Picasso.get().load(url).fit().centerInside().into(pokeImage);
                                    Picasso.get().load(finalUrlStart).fit().centerInside().into(evoImage1);
                                    Picasso.get().load(finalUrlMid).fit().centerInside().into(evoImage2);
                                    Picasso.get().load(finalUrlEnd).fit().centerInside().into(evoImage3);


                                }
                            });
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }



        }).start();
    }

//    @Override
//    public void onBackPressed() {
//        if (mViewPager.getCurrentItem() == 0) {
//            // If the user is currently looking at the first step, allow the system to handle the
//            // Back button. This calls finish() on this activity and pops the back stack.
//            super.onBackPressed();
//        } else {
//            // Otherwise, select the previous step.
//            mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);
//        }
//    }
}
