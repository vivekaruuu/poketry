package com.example.pokedexx;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class cards {
    String name;

    items sprites;

    public items getSprites() {
        return sprites;
    }

    public void setSprites(items sprites) {
        this.sprites = sprites;
    }




    public cards(String name, items picture) {
        this.name = name;
        sprites= picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return sprites.back_default;
    }

    public void setPicture(String picture) {
        this.sprites.back_default = picture;
    }
}
