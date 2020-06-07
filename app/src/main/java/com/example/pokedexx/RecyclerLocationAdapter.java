package com.example.pokedexx;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerLocationAdapter extends RecyclerView.Adapter<RecyclerLocationAdapter.ViewHolders> {

    private ArrayList<String> mLocations=new ArrayList<>();
    private static final String TAG = "RecyclerAdapter";
    private onCardListener onCardListener;

    public RecyclerLocationAdapter(ArrayList<String> mNotes, onCardListener onCardListener) {
        this.mLocations = mNotes;
        this.onCardListener= onCardListener;
    }


    @NonNull
    @Override
    public ViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.location,parent,false);

        return new ViewHolders(view,this.onCardListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolders holder, int position) {
        holder.name.setText(mLocations.get(position));

    }

    @Override
    public int getItemCount() {
        return mLocations.size();
    }

    public class ViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView name;
        onCardListener onCardListener;
        public ViewHolders(@NonNull View itemView,onCardListener onCardListener) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            this.onCardListener=onCardListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onCardListener.onNoteClick(getAdapterPosition() );
        }
    }
    public interface onCardListener{
        void onNoteClick(int position);

    }

}
