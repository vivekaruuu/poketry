package com.example.pokedexx.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokedexx.R;
import com.example.pokedexx.pokemonView.cards;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolders> {

    private ArrayList<cards> mCards=new ArrayList<>();
//    HashMap<Integer,cards> mCards
//            = new HashMap<>();
    private static final String TAG = "RecyclerAdapter";
    private onCardListener onCardListener;

    public RecyclerAdapter(ArrayList<cards> mNotes,onCardListener onCardListener) {
        this.mCards = mNotes;
        this.onCardListener= onCardListener;
    }


    @NonNull
    @Override
    public ViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,parent,false);

        return new ViewHolders(view,this.onCardListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolders holder, int position) {

        if(mCards.get(position)!=null)
        {
            holder.name.setText(mCards.get(position).getName());
            String imageUrl=mCards.get(position).getPicture();
            Picasso.get().load(imageUrl).fit().centerInside().into(holder.imageView);
//            holder.imageView.setImageBitmap(mCards.get(position).bitmap);
        }
        //Picasso.get().load(imageUrl).fit().centerInside().into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return mCards.size();
    }

    public class ViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView name;
        private ImageView imageView;
        onCardListener onCardListener;
        public ViewHolders(@NonNull View itemView,onCardListener onCardListener) {
            super(itemView);
            name=itemView.findViewById(R.id.PokeName);
            imageView=itemView.findViewById(R.id.PokeImage);
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
