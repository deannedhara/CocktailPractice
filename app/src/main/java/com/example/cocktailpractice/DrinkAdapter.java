package com.example.cocktailpractice;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * @author Deanne Dhara
 * DrinkAdapter class provides an adapter for the RecyclerView used in DrinkListActivity
 */
public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.DrinkViewHolder>{
    private ArrayList<DrinkItem> mDrinkList;
    private SelectListener mlistener;

    public DrinkAdapter(ArrayList<DrinkItem> drinkList, SelectListener listener){
        mDrinkList = drinkList;
        mlistener = listener;
    }

    class DrinkViewHolder extends RecyclerView.ViewHolder {
        public TextView mDrinkNameTextView;
        public ImageView mImageView;
       // public TextView mInstructionsView;
        public CardView cardView;

        public DrinkViewHolder(@NonNull View itemView) {
            super(itemView);
            mDrinkNameTextView = itemView.findViewById(R.id.textView_drink);
            mImageView = itemView.findViewById(R.id.image_drink);
            cardView = itemView.findViewById(R.id.card_view_drink_item);
        }
    }

    @NonNull
    @Override
    public DrinkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View drinkView = LayoutInflater.from(parent.getContext()).inflate(R.layout.drink_item, parent, false);
        return new DrinkViewHolder(drinkView);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinkViewHolder holder, @SuppressLint("RecyclerView") int position) {
        DrinkItem currentItem = mDrinkList.get(position);
        String imageUri = currentItem.getImageResource();

        Picasso.get().load(imageUri).into(holder.mImageView);
        holder.mDrinkNameTextView.setText(currentItem.getDrinkName());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mlistener.onItemClicked(mDrinkList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDrinkList.size();
    }
}
