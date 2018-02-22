package com.robby.lesson05_android_client.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.robby.lesson05_android_client.R;
import com.robby.lesson05_android_client.entity.Item;

import java.util.ArrayList;

/**
 * @author Robby Tan
 */

public class ItemAdapter extends BaseAdapter {


    private ArrayList<Item> items;
    private Context context;

    public ItemAdapter(Context context) {
        this.context = context;
        items = new ArrayList<>();
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items.clear();
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.item_row_layout, parent, false);
        }

        Item item = (Item) getItem(position);

        TextView textViewItemName = convertView.findViewById(R.id.tv_item_name);
        textViewItemName.setText(item.getName());
        TextView textViewItemDescription = convertView.findViewById(R.id.tv_item_category);
        textViewItemDescription.setText(item.getCategory().getName());

        return convertView;
    }
}
