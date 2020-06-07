package com.example.pokedexx;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class locations {
    @SerializedName("results")
    @Expose
    public List<Result> results = null;


    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }


    public class Result {

        @SerializedName("name")
        @Expose
        private String name;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }



    }
}

