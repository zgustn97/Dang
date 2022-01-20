package com.example.makerenew;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    ArrayList<Food> items = new ArrayList<Food>();

    public void addItem(Food item) {
        items.add(item);
    }

    public void setItems(ArrayList<Food> items) {
        this.items = items;
    }

    public Food getItem(int position) {
        return items.get(position);
    }

    public void setItem(int position, Food item) {
        items.set(position, item);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.food_layout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Food item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView classification;
        TextView meal_tan;
        TextView meal_dan;
        TextView meal_ge;
        TextView meal_cal;
        TextView meal_food1;
        TextView meal_food2;
        TextView meal_food3;
        TextView meal_food4;
        TextView meal_food5;
        TextView district1;
        TextView district2;
        TextView district3;
        TextView district4;
        TextView district5;
        TextView district6;

        public ViewHolder(View itemView) {
            super(itemView);
            classification = itemView.findViewById(R.id.classification);
            meal_tan = itemView.findViewById(R.id.meal_tan);
            meal_dan = itemView.findViewById(R.id.meal_dan);
            meal_ge = itemView.findViewById(R.id.meal_ge);
            meal_cal = itemView.findViewById(R.id.meal_cal);
            meal_food1 = itemView.findViewById(R.id.meal_food1);
            meal_food2 = itemView.findViewById(R.id.meal_food2);
            meal_food3 = itemView.findViewById(R.id.meal_food3);
            meal_food4 = itemView.findViewById(R.id.meal_food4);
            meal_food5 = itemView.findViewById(R.id.meal_food5);
            district1 = itemView.findViewById(R.id.district1);
            district2 = itemView.findViewById(R.id.district2);
            district3 = itemView.findViewById(R.id.district3);
            district4 = itemView.findViewById(R.id.district4);
            district5 = itemView.findViewById(R.id.district5);
            district6 = itemView.findViewById(R.id.district6);
        }

        public void setItem(Food item) {
            classification.setText(item.getClassification());
            meal_tan.setText(Integer.toString(item.getMeal_tan()));
            meal_dan.setText(Integer.toString(item.getMeal_dan()));
            meal_ge.setText(Integer.toString(item.getMeal_ge()));
            meal_cal.setText(Integer.toString(item.getMeal_cal()));
            meal_food1.setText(item.getMeal_food1());
            meal_food2.setText(item.getMeal_food2());
            meal_food3.setText(item.getMeal_food3());
            meal_food4.setText(item.getMeal_food4());
            meal_food5.setText(item.getMeal_food5());
            district1.setText(item.getDistrict1());
            district2.setText(item.getDistrict2());
            district3.setText(item.getDistrict3());
            district4.setText(item.getDistrict4());
            district5.setText(item.getDistrict5());
            district6.setText(item.getDistrict6());
        }
    }

}
