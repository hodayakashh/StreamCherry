package com.example.androidapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.androidapp.R;
import com.example.androidapp.entities.Category;

import java.util.List;

public class CategoriesListAdapter extends RecyclerView.Adapter<CategoriesListAdapter.CategoriesViewHolder> {

    // Define the ViewHolder class
    public static class CategoriesViewHolder extends RecyclerView.ViewHolder {

        private final TextView category_name;

        // Define the views in the ViewHolder
        private CategoriesViewHolder(View itemView) {
            super(itemView);
            // Initialize the views here
            category_name = itemView.findViewById(R.id.category_name);

        }
    }

    private final LayoutInflater mInflater;
    private List<Category> mCategories; // Cached copy of categories

    public CategoriesListAdapter(Context context) {

        // Initialize the LayoutInflater
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public CategoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the layout for each item and create a ViewHolder
        View itemView = mInflater.inflate(R.layout.category_item, parent, false);
        // Create a new ViewHolder instance
        return new CategoriesViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(CategoriesViewHolder holder, int position) {
        if (mCategories != null) {
            final Category current = mCategories.get(position);
            holder.category_name.setText(current.getName());
        }
    }

    public void setCategories(List<Category> categories) {
        mCategories = categories;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mCategories != null)
            return mCategories.size();
        else
            return 0;
    }
}
