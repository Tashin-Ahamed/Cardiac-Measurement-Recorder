package com.example.cardiacmeasurementmanager;

import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.app.ApplicationErrorReport;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {
    private static CustomClickListener customClickListener;
    private final ArrayList<ModelClass> arrayList;
    private ModelClass modelClass;
    private final Context context;
    private final int greenColor;
    private final int redColor;

    /**
     *
     * @param context
     * @param list
     * @param greenC
     * @param redC
     */
    public RecyclerAdapter(Context context,  ArrayList<ModelClass> list,int greenC,int redC) {
        this.arrayList=list;
        this.context = context;
        this.greenColor =greenC;
        this.redColor = redC;
    }

    /**
     *
     * @param customClickListener
     */
    public void setCustomClickListener(CustomClickListener customClickListener)
    {
        this.customClickListener = customClickListener;
    }

    /**
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_view, parent, false);
        return new RecyclerViewHolder(view);

    }

    /**
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.RecyclerViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (!arrayList.isEmpty()) modelClass = arrayList.get(position);
        holder.dateTextView.setText(""+modelClass.getDate());
        holder.systolicTextView.setText(""+modelClass.getSystolic());
        holder.diastolicTextView.setText(""+modelClass.getDiastolic());
        holder.heartTextView.setText(""+modelClass.getHeartRate());
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param v
             */
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (customClickListener != null) {
                    if (position != RecyclerView.NO_POSITION) {
                        customClickListener.onDeleteClick(position);
                    }
                }


            }

        });
        if (modelClass.getDiastolic()<=80 && modelClass.getSystolic()<=120 && modelClass.getHeartRate()>=60 && modelClass.getHeartRate()<=100) {
            Drawable buttonDrawable = holder.containerCardView.getBackground();
            buttonDrawable = DrawableCompat.wrap(buttonDrawable);
            DrawableCompat.setTint(buttonDrawable, greenColor);
            holder.containerCardView.setBackground(buttonDrawable);
            holder.containerCardView.setCardBackgroundColor(greenColor);
            // Toast.makeText(context,"color",Toast.LENGTH_SHORT).show();
        }
        else if(!(modelClass.getDiastolic()<90&& modelClass.getSystolic()<139&&modelClass.getHeartRate()>=40&&modelClass.getHeartRate()<110)){
            Drawable buttonDrawable = holder.containerCardView.getBackground();
            buttonDrawable = DrawableCompat.wrap(buttonDrawable);
            DrawableCompat.setTint(buttonDrawable, redColor);
            holder.containerCardView.setBackground(buttonDrawable);
            holder.containerCardView.setCardBackgroundColor(redColor);
            //  Toast.makeText(context,"color",Toast.LENGTH_SHORT).show();
        }




    }

    /**
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public interface CustomClickListener {
        void cusOnClick(int position, View v);
        void onDeleteClick(int position);
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView systolicTextView;
        TextView heartTextView;
        TextView diastolicTextView;
        TextView dateTextView;
        CardView containerCardView;
        ImageView deleteButton;

        /**
         * 
         * @param itemView
         */
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            systolicTextView = itemView.findViewById(R.id.tvSystolic);
            diastolicTextView = itemView.findViewById(R.id.tvDiastolic);
            dateTextView = itemView.findViewById(R.id.tvDate);
            heartTextView = itemView.findViewById(R.id.tvHeartRate);
            containerCardView = itemView.findViewById(R.id.llContainerCardView);
            deleteButton = itemView.findViewById(R.id.deleteIm);
            itemView.setOnClickListener(this);
        }

        /**
         *
         * @param view
         */
        @Override
        public void onClick(View view) {
            customClickListener.cusOnClick(getAdapterPosition(), view);  //position and view setting to provide to mainactivity
        }

    }


}


