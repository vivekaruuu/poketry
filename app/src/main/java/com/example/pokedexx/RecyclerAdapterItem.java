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

public class RecyclerAdapterItem extends RecyclerView.Adapter<RecyclerAdapterItem.ViewHolders> {

    private ArrayList<item> mCards=new ArrayList<>();
    private static final String TAG = "RecyclerAdapter";
    private onCardListener onCardListener;

    public RecyclerAdapterItem(ArrayList<item> mNotes, onCardListener onCardListener) {
        this.mCards = mNotes;
        this.onCardListener= onCardListener;
    }


    @NonNull
    @Override
    public ViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);

        return new ViewHolders(view,this.onCardListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolders holder, int position) {
        holder.name.setText(mCards.get(position).getName());
        String imageUrl=mCards.get(position).getPicture();
        Picasso.get().load(imageUrl).fit().centerInside().into(holder.imageView);

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
