package com.example.pokedexx;

import com.google.gson.annotations.SerializedName;

public class cards {
    String name;

    @SerializedName("sprites")
    String pictureUrl;

    public cards(String name, String picture) {
        this.name = name;
        this.pictureUrl = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return pictureUrl;
    }

    public void setPicture(String picture) {
        this.pictureUrl = picture;
    }
}
