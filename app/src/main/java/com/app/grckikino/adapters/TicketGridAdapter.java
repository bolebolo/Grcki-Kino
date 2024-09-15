package com.app.grckikino.adapters;

import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.grckikino.R;
import com.app.grckikino.databinding.ItemTicketNumberBinding;
import com.app.grckikino.callbacks.TicketAdapterCallback;

import com.app.grckikino.models.TicketNumberModel;
import com.app.grckikino.utils.Utils;

import java.util.ArrayList;

public class TicketGridAdapter  extends RecyclerView.Adapter<TicketGridAdapter.ItemViewHolder> {

    private static final int TYPE_ITEM = 1;
    private final ArrayList<TicketNumberModel> ticketNumbersList;
    private TicketAdapterCallback ticketCallback;


    public TicketGridAdapter(ArrayList<TicketNumberModel> ticketNumbersList, TicketAdapterCallback ticketCallback) {
        this.ticketNumbersList = ticketNumbersList;
        this.ticketCallback = ticketCallback;
    }

    public void updateList(ArrayList<TicketNumberModel> newTicketNumbersList) {
        ticketNumbersList.clear();
        ticketNumbersList.addAll(newTicketNumbersList);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public TicketGridAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            ItemTicketNumberBinding binding = ItemTicketNumberBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new TicketGridAdapter.ItemViewHolder(binding.getRoot());
        }
        throw new RuntimeException("No match for " + viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull final TicketGridAdapter.ItemViewHolder holder, int position) {
        holder.bind(ticketNumbersList, ticketCallback);
    }

    private TicketNumberModel getItem(int position) {
        return ticketNumbersList.get(position);
    }

    @Override
    public int getItemCount() {
        return ticketNumbersList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_ITEM;
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        private ItemTicketNumberBinding binding;
        private final ColorStateList defaultTextColor;
        private GradientDrawable ringShape;
        private ItemViewHolder(View itemView) {
            super(itemView);
            binding = ItemTicketNumberBinding.bind(itemView);
            defaultTextColor = binding.numberText.getTextColors();
            ringShape = (GradientDrawable)  binding.circleBackground.getBackground();
        }

        public void bind(ArrayList<TicketNumberModel> ticketNumbersList, TicketAdapterCallback ticketCallback) {
            TicketNumberModel ticketNumberItem = ticketNumbersList.get(getAdapterPosition());
            handleBacground(ticketNumberItem);
            binding.numberText.setText(String.valueOf(ticketNumberItem.getNumber()));
            itemView.setOnClickListener(view -> {
                if (ticketNumberItem.getIsSelected() == 0){
                    if(Utils.getSelectedItemCount(ticketNumbersList)<15){
                        ticketNumberItem.setIsSelected(1);
                        ticketCallback.onNumberClicked();
                    }else {
                        ticketCallback.onSelectedMax(ticketNumberItem.getNumber());
                    }
                }else{
                    ticketNumberItem.setIsSelected(0);
                    ticketCallback.onNumberClicked();
                }
                handleBacground(ticketNumberItem);
            });
        }


        public void handleBacground(TicketNumberModel ticketNumber) {
            if (ticketNumber.getIsSelected() == 1){
                binding.numberText.setTypeface(null, Typeface.BOLD);
                binding.numberText.setTextColor(Utils.getTextColorOnSurface(itemView.getContext()));
                ringShape.setColor(Utils.getColorForNumber(itemView.getContext(), ticketNumber.getNumber()));
                Animation  anim = AnimationUtils.loadAnimation(itemView.getContext(), R.anim.circle_out);
                binding.circleBackground.setVisibility(View.VISIBLE);
                binding.circleBackground.startAnimation(anim);
            }else{
                binding.numberText.setTypeface(null, Typeface.NORMAL);
                binding.numberText.setTextColor(defaultTextColor);
                binding.circleBackground.clearAnimation();
                binding.circleBackground.setVisibility(View.GONE);
            }
        }
    }
}