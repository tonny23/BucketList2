package com.example.bucketlist;

/**
 * Created by marmm on 11/11/2017.
 */

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.List;


public class ItemArrayAdapter extends RecyclerView.Adapter<ItemArrayAdapter.ViewHolder> {

    private List<BucketItem> mItems;
    private ItemClickListener mItemClickListener;

    public ItemArrayAdapter(List<BucketItem> items, ItemClickListener itemClickListener) {
        mItems = items;
        mItemClickListener = itemClickListener;
    }

    @Override
    public ItemArrayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View bucketListView = inflater.inflate(R.layout.item_bucket, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(bucketListView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemArrayAdapter.ViewHolder holder, final int position) {
        // Get the data model based on position
        BucketItem bucketItem = mItems.get(position);

        // Set item views based on your views and data model
        CheckBox checkBox = holder.mCheckBox;
        holder.mCheckBox.setOnCheckedChangeListener(null);
        checkBox.setChecked(bucketItem.getChecked());
        checkBox.setText(bucketItem.getItemTitle());

        // if the checked value is true the text will be crossed
        if (checkBox.isChecked()) {
            checkBox.setPaintFlags(checkBox.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            checkBox.setPaintFlags(0);
        }

        // the item will be deleted if it is long pressed
        checkBox.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mItemClickListener.itemOnLongClick(position);
                return true;
            }
        });

        // if a checkbox is checked or unchecked the value will be passed on and the text will be crossed
        // or uncrossed
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mItemClickListener.itemOnClick(position, compoundButton.isChecked());
                if (b) {
                    compoundButton.setPaintFlags(compoundButton.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                } else {
                    compoundButton.setPaintFlags(0);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public interface ItemClickListener {

        void itemOnLongClick(int position);
        void itemOnClick(int position, boolean checked);

    }
    // Provide a reference to the views for each data item
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        public CheckBox mCheckBox;
        public View mView;

        public ViewHolder(View itemView) {
            super(itemView);
            mCheckBox = itemView.findViewById(R.id.check_box);
            mView = itemView;

        }
    }
}
