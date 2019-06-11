package com.example.sqlitetest.DatabaseClasses;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sqlitetest.R;
import com.example.sqlitetest.RecyclerClasses.ModelClass;

import java.util.ArrayList;

public class DatabaseRecyclerAdapter extends RecyclerView.Adapter<DatabaseRecyclerAdapter.DatabaseViewHolder> {

    ArrayList<ModelClass> objModelClassArrayList;

    public DatabaseRecyclerAdapter(ArrayList<ModelClass> objModelClassArrayList) {
        this.objModelClassArrayList = objModelClassArrayList;
    }

    public static class DatabaseViewHolder extends RecyclerView.ViewHolder{
        TextView idTV,userNameTV,emailTV,phoneTV;
        public DatabaseViewHolder(@NonNull View itemView) {
            super(itemView);
            idTV = itemView.findViewById(R.id.idTV);
            userNameTV = itemView.findViewById(R.id.nameTV);
            emailTV = itemView.findViewById(R.id.emailTV);
            phoneTV = itemView.findViewById(R.id.phoneTV);

        }
    }

    @NonNull
    @Override
    public DatabaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View singleRow = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_row,viewGroup,false);
        return new DatabaseViewHolder(singleRow);
    }

    @Override
    public void onBindViewHolder(@NonNull DatabaseViewHolder databaseViewHolder, int i) {
        ModelClass objModelClass = objModelClassArrayList.get(i);
        databaseViewHolder.idTV.setText(Integer.toString(objModelClass.getID()));
        databaseViewHolder.userNameTV.setText(objModelClass.getUserName());
        databaseViewHolder.emailTV.setText(objModelClass.getUserEmail());
        databaseViewHolder.phoneTV.setText(Integer.toString(objModelClass.getPhone()));
    }

    @Override
    public int getItemCount() {
        return objModelClassArrayList.size();
    }

}
