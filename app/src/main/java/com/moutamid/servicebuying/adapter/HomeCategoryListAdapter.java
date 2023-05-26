package com.moutamid.servicebuying.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moutamid.servicebuying.R;
import com.moutamid.servicebuying.SelectedCategoryScreen;
import com.moutamid.servicebuying.model.Category;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collection;

public class HomeCategoryListAdapter extends
        RecyclerView.Adapter<HomeCategoryListAdapter.HomeCategoryViewHolder> implements Filterable {

    private Context mContext;
    ArrayList<Category> categoryArrayList;

    public HomeCategoryListAdapter(Context mContext, ArrayList<Category> categoryArrayList) {
        this.mContext = mContext;
        this.categoryArrayList = categoryArrayList;
    }

    @NonNull
    @Override
    public HomeCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dashboard_custom_layout,parent,false);
        return new HomeCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeCategoryViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Category model = categoryArrayList.get(position);
        holder.nameTxt.setText(model.getName());
        holder.imageView.setImageResource(model.getImage());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, SelectedCategoryScreen.class);
                intent.putExtra("category",model);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryArrayList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }


    Filter filter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<Category> filterList = new ArrayList<>();

            if (charSequence.toString().isEmpty()) {
                filterList.addAll(categoryArrayList);
            } else {
                for (Category data : categoryArrayList) {
                    if (data.getName().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        filterList.add(data);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filterList;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            categoryArrayList.clear();
            categoryArrayList.addAll((Collection<? extends Category>) filterResults.values);
            notifyDataSetChanged();

        }
    };


    public class HomeCategoryViewHolder extends RecyclerView.ViewHolder{

        public TextView nameTxt;
        private ImageView imageView;

        public HomeCategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTxt = itemView.findViewById(R.id.name);
            imageView = itemView.findViewById(R.id.image);
        }
    }
}
