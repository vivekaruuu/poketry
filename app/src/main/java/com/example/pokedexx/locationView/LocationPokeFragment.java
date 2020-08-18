package com.example.pokedexx.locationView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokedexx.R;
import com.example.pokedexx.adapters.RecyclerLocationAdapter;

import static com.example.pokedexx.locationView.LocationFragment.mRegionsPoke;
import static com.example.pokedexx.locationView.LocationFragment.mTypesPoke;
import static com.example.pokedexx.locationView.LocationFragment.mlocationsPoke;

public class LocationPokeFragment extends Fragment implements RecyclerLocationAdapter.onCardListener {

    RecyclerView mRecyclerView;
    RecyclerLocationAdapter mRecyclerAdapter;
    String myValue;
    private static final String TAG = "LocationPokeFragment";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.location_view, container, false);
        mRecyclerView=view.findViewById(R.id.recyclerViewLocation);
        Bundle bundle=this.getArguments();
        if (bundle != null) {
            myValue = this.getArguments().getString("selected");
            initRecyclerView(myValue);
        }

        return view;
    }


    void initRecyclerView(String string) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        switch (string) {
            case "1":
                mRecyclerAdapter = new RecyclerLocationAdapter(mlocationsPoke, this);
                Log.d(TAG, "initRecyclerView: 1");
                break;
            case "3":
                mRecyclerAdapter = new RecyclerLocationAdapter(mTypesPoke, this);
                Log.d(TAG, "initRecyclerView: 2");
                break;
            case "2":
                mRecyclerAdapter = new RecyclerLocationAdapter(mRegionsPoke, this);
                break;
        }
        mRecyclerView.setAdapter(mRecyclerAdapter);
    }

    @Override
    public void onNoteClick(int position) {

    }
}
