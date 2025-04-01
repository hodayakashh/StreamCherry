package com.example.androidapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.androidapp.R;
import com.example.androidapp.entities.Category;

import java.util.List;

public class CategoriesListAdapter extends RecyclerView.Adapter<CategoriesListAdapter.CategoriesViewHolder> {



    // interface for the delete action
    public interface OnCategoryDeleteListener {
        void onCategoryDelete(Category category);
    }

    public interface OnCategoryEditListener {
        void onCategoryEdit(Category category);
    }



    // Define the ViewHolder class
    public static class CategoriesViewHolder extends RecyclerView.ViewHolder {

        private final TextView category_name;
        private final Button btnDeleteCategory;
        private final Button btnEditCategory;

        // Constructor for the ViewHolder
        private CategoriesViewHolder(View itemView) {
            super(itemView);
            // Initialize the views here
            category_name = itemView.findViewById(R.id.category_name);
            btnDeleteCategory = itemView.findViewById(R.id.btnDeleteCategory);
            btnEditCategory = itemView.findViewById(R.id.btnEditCategory);
        }
    }

    private final LayoutInflater mInflater;
    private List<Category> mCategories; // Cached copy of categories
    private final OnCategoryDeleteListener deleteListener;
    private final OnCategoryEditListener editListener;

    // Update constructor to accept the listener
    public CategoriesListAdapter(Context context, OnCategoryDeleteListener deleteListener ,
                                 OnCategoryEditListener editListener) {
        mInflater = LayoutInflater.from(context);
        this.deleteListener = deleteListener;
        this.editListener = editListener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CategoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the layout for each item and create a ViewHolder
        View itemView = mInflater.inflate(R.layout.category_item, parent, false);
        // Create a new ViewHolder instance
        return new CategoriesViewHolder(itemView);

    }

    // Bind the data to the ViewHolder
    @Override
    public void onBindViewHolder(CategoriesViewHolder holder, int position) {
        if (mCategories != null) {
            final Category current = mCategories.get(position);
            holder.category_name.setText(current.getName());

            holder.btnDeleteCategory.setOnClickListener(v -> {
                // Call the interface method when button is clicked
                if (deleteListener != null) {
                    deleteListener.onCategoryDelete(current);
                }
            });

            holder.btnEditCategory.setOnClickListener(v -> {
                // Call the interface method when button is clicked
                if (editListener != null) {
                    editListener.onCategoryEdit(current);
                }
            });
        }
    }

    // Method to set the categories list
    public void setCategories(List<Category> categories) {
        mCategories = categories;
        notifyDataSetChanged();
    }


    // Method to get the size of the list
    @Override
    public int getItemCount() {
        if (mCategories != null)
            return mCategories.size();
        else
            return 0;
    }
}
