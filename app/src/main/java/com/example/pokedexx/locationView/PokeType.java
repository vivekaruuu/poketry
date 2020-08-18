package com.example.pokedexx.locationView;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PokeType {

    @SerializedName("pokemon")
    @Expose
    public List<Pokemon> pokemon = null;

    public class Pokemon {

        @SerializedName("pokemon")
        @Expose
        public Pokemon_ pokemon;

    }

    public class Pokemon_ {

        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("url")
        @Expose
        public String url;

    }
}
