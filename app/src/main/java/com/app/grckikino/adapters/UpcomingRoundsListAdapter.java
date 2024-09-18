package com.app.grckikino.adapters;

import static com.app.grckikino.utils.KeysAndConstants.TEXT_BECOME_RED;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.grckikino.R;
import com.app.grckikino.databinding.ItemUpcomingDrawsBinding;
import com.app.grckikino.callbacks.UpcomingRoundAdapterCallback;
import com.app.grckikino.models.RoundsModel;
import com.app.grckikino.utils.Utils;

import java.util.ArrayList;

public class UpcomingRoundsListAdapter extends RecyclerView.Adapter<UpcomingRoundsListAdapter.ItemViewHolder> {

    /*Za odbrojavanje u ovom adapteru postoji vise mogucnosti i resenja, navescu 3:
     1)koriscenje globalnog hendlera koji odbrojava... ja sam se odlucio
         za ovaj nacin(najlepsi je Vizuelno jer se sve brojke istovremeno odbrojavaju,
         ali nije bas dobro za performanse uredjaja, medjutim danasnji uredjaji imaju poprilicno
         memorije i jake procesore pa ovo nece predstavljati ni najmanji problem, pogotovo sto se
          ovde radi o desetinama itema u listi a ne hiljadama, tako da je ovo moj izbor.
      2)koriscenje custom  klase tj ui elementa CountdownTextView, textview koji u sebi sadrzi
       brojac i automatski se na zadato vreme odbrojava, malo je losiji od prvog resenja u
       vizuelnom smislu jer na vecem broju itema se primeti malo kasnjenje izmedju  itema kod
        odbrojavnja, ima efekat vodopada
      3) koriscenje CountDownTimer unutar ItemViewHolder, slicno kao drugo resenje, vizuelni efekat
      vodopada

      Napomena: izbor jednog od ovih resenja ili nekog drugog zavisi od konkretnih zahteva, sta je
      prioritet, ja sam se odlucio za vizuelno u ovom slucaju :)                                */


    private final Context context;
    private static final int TYPE_ITEM = 1;
    private final ArrayList<RoundsModel> upcomingDrawsList;
    private UpcomingRoundAdapterCallback drawCallback;
    private Handler handler = new Handler(Looper.getMainLooper());
    private long updateInterval = 1000L; // 1 sekunda

    public UpcomingRoundsListAdapter(Context context, ArrayList<RoundsModel> confirmationsArrayList, UpcomingRoundAdapterCallback drawCallback) {
        this.context = context;
        this.upcomingDrawsList = confirmationsArrayList;
        this.drawCallback = drawCallback;
        startGlobalTimer();
    }

    public void updateList(ArrayList<RoundsModel> confirmationsArrayList) {
        int oldSize = upcomingDrawsList.size();
        int newSize = confirmationsArrayList.size();
        upcomingDrawsList.clear();
        upcomingDrawsList.addAll(confirmationsArrayList);
        handleRefresh(oldSize, newSize);
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            ItemUpcomingDrawsBinding binding = ItemUpcomingDrawsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new ItemViewHolder(binding.getRoot());
        }
        throw new RuntimeException("No match for " + viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder holder, int position) {
        RoundsModel upcomingDrawsModel = getItem(position);
        holder.bind(context, upcomingDrawsModel, drawCallback);
    }

    private RoundsModel getItem(int position) {
        return upcomingDrawsList.get(position);
    }

    @Override
    public int getItemCount() {
        return upcomingDrawsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_ITEM;

    }

    //3) nacin koriscenje countDownTimer-a
//    private void getRemainingTimeString(ItemViewHolder holder, UpcomingDrawsModel upcomingDrawsModel) {
//        long remainingTime = upcomingDrawsModel.getDrawTime() - System.currentTimeMillis();
//
//        holder.countDownTimer = new CountDownTimer(remainingTime, 1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                int hours = (int) (millisUntilFinished / (1000 * 60 * 60));
//                int minutes = (int) ((millisUntilFinished / (1000 * 60)) % 60);
//                int seconds = (int) (millisUntilFinished / 1000) % 60;
//
//                if (hours > 0) {
//                    holder.binding.drawContdownTime.setText(context.getString(R.string.countdown_time_hours_format, hours, minutes, seconds));
//                } else {
//                    holder.binding.drawContdownTime.setText(context.getString(R.string.countdown_time_format, minutes, seconds));
//                }
//                if (hours * 360 + minutes * 60 + seconds < 20) {
//                    holder.binding.drawContdownTime.setTextColor(context.getResources().getColor(R.color.red_800));
//                    holder.binding.drawContdownTime.setTypeface(null, Typeface.BOLD);
//                } else {
//                    holder.binding.drawContdownTime.setTextColor(context.getResources().getColor(R.color.black));
//                    holder.binding.drawContdownTime.setTypeface(null, Typeface.NORMAL);
//                }
//            }
//
//            @Override
//            public void onFinish() {
//                holder.binding.drawContdownTime.setTypeface(null, Typeface.BOLD);
//                holder.binding.drawContdownTime.setTextColor(context.getResources().getColor(R.color.red_800));
//                holder.binding.drawContdownTime.setText(context.getString(R.string.countdown_time_format, 0, 0));
//            }
//        }.start();
//    }

    private void startGlobalTimer() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                notifyDataSetChanged(); // ovde se moze implementirati osvezavanje samo vidljivih elemenata, da bi se dobilo na performansama, mada ni ovo nije ni najmanji problem za listu od 20 itema
                handler.postDelayed(this, updateInterval);
            }
        });
    }

//    private void updateRemainingTime(ItemViewHolder holder, UpcomingDrawsModel upcomingDrawsModel) {
//        long remainingTime = upcomingDrawsModel.getDrawTime() - System.currentTimeMillis();
//        if (remainingTime > 0) {
//            int hours = (int) (remainingTime / (1000 * 60 * 60));
//            int minutes = (int) ((remainingTime / (1000 * 60)) % 60);
//            int seconds = (int) (remainingTime / 1000) % 60;
//
//            if (hours > 0) {
//                holder.binding.drawContdownTime.setText(context.getString(R.string.countdown_time_hours_format, hours, minutes, seconds));
//            } else {
//                holder.binding.drawContdownTime.setText(context.getString(R.string.countdown_time_format, minutes, seconds));
//            }
//            if (hours * 360 + minutes * 60 + seconds < 20) {
//                setTextStyleBoldRed(context, holder);
//            } else {
//                setTextStyleNormal(holder);
//            }
//        } else {
//            holder.binding.drawContdownTime.setText(context.getString(R.string.countdown_time_format, 0, 0));
//            setTextStyleBoldRed(context, holder);
//            new Handler().post(() -> {
//                drawCallback.onUpcomingDrawExpired(upcomingDrawsModel.getDrawId(), holder.getAdapterPosition());
//            });
//        }
//    }

    public void stopHandler() {
        handler.removeCallbacksAndMessages(null);
    }

    public void handleRefresh(int oldSize, int newSize) {
        if (oldSize - newSize == 1) {
            new Handler().post(() -> notifyItemRemoved(0));
        } else {
            new Handler().post(() -> notifyDataSetChanged());
        }
    }

    public static void setTextStyleNormal(ItemViewHolder holder) {
        holder.binding.drawContdownTime.setTextColor(holder.defaultTextColor);
        holder.binding.drawContdownTime.setTypeface(null, Typeface.NORMAL);
    }

    public static void setTextStyleBoldRed(Context context, ItemViewHolder holder) {
        holder.binding.drawContdownTime.setTextColor(context.getResources().getColor(R.color.red_800));
        holder.binding.drawContdownTime.setTypeface(null, Typeface.BOLD);
    }


    static class ItemViewHolder extends RecyclerView.ViewHolder {

        private ItemUpcomingDrawsBinding binding;
        private final ColorStateList defaultTextColor;

        private ItemViewHolder(View itemView) {
            super(itemView);
            binding = ItemUpcomingDrawsBinding.bind(itemView);
            defaultTextColor = binding.drawContdownTime.getTextColors();
        }

        // Metoda za bindovanje podataka
        public void bind(Context context, RoundsModel upcomingDrawsModel, UpcomingRoundAdapterCallback drawCallback) {
            binding.upcomingItemView.setOnClickListener(view -> drawCallback.onUpcomingDrawClicked(upcomingDrawsModel));
            binding.drawTime.setText(Utils.getTimeString(upcomingDrawsModel.getDrawTime()));
            long remainingTime = upcomingDrawsModel.getDrawTime() - System.currentTimeMillis();
            if (remainingTime > 0) {
                int hours = (int) (remainingTime / (1000 * 60 * 60));
                int minutes = (int) ((remainingTime / (1000 * 60)) % 60);
                int seconds = (int) (remainingTime / 1000) % 60;

                if (hours > 0) {
                    this.binding.drawContdownTime.setText(context.getString(R.string.countdown_time_hours_format, hours, minutes, seconds));
                } else {
                    this.binding.drawContdownTime.setText(context.getString(R.string.countdown_time_format, minutes, seconds));
                }
                if (hours * 360 + minutes * 60 + seconds < TEXT_BECOME_RED) {
                    setTextStyleBoldRed(context, this);
                } else {
                    setTextStyleNormal(this);
                }
            } else {
                new Handler().post(() -> drawCallback.onUpcomingDrawExpired(upcomingDrawsModel.getDrawId(), getAdapterPosition()));
                setTextStyleBoldRed(context, this);
                this.binding.drawContdownTime.setText(context.getString(R.string.countdown_time_format, 0, 0));
            }
        }
    }
}