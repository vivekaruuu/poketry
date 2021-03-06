package com.example.pokedexx.pokemonView;

import android.graphics.Bitmap;
import android.media.Image;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class cards {
     String name;

     items sprites;

    public Bitmap bitmap;

    public items getSprites() {
        return sprites;
    }

    public void setSprites(items sprites) {
        this.sprites = sprites;
    }




    public cards(String name, items picture,Bitmap bitmap) {
        this.bitmap=bitmap;
        this.name = name;
        sprites= picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  String getPicture() {
        return sprites.back_default;
    }

    public void setPicture(String picture) {
        this.sprites.back_default = picture;
    }
    public class items{


        @SerializedName("front_default")
        String back_default;

        public String getBack_default() {
            return back_default;
        }

        public void setBack_default(String back_default) {
            this.back_default = back_default;
        }
    }
}
