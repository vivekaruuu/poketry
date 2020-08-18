package com.example.pokedexx.locationView;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class areas {

    @SerializedName("areas")
    @Expose
    public List<Area> areas = null;

    public class Area {

        @SerializedName("name")
        @Expose
        public String name;


    }
}
