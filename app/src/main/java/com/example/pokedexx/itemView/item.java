package com.example.pokedexx.itemView;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class item {

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @SerializedName("sprites")
    public Sprites sprites;

    public String getPicture() {
        return sprites._default;
    }

    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }

    public item(String name,Sprites sprites) {
        this.sprites = sprites;
        this.name=name;
    }

    public class Sprites {

        @SerializedName("default")
        private String _default;

        public String getDefault() {
            return _default;
        }

        public void setDefault(String _default) {
            this._default = _default;
        }
    }
}