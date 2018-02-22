package com.robby.lesson05_android_client;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.robby.lesson05_android_client.adapter.ItemAdapter;
import com.robby.lesson05_android_client.entity.Item;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DataActivity extends AppCompatActivity {

    @BindView(R.id.lv_items)
    ListView itemList;
    private ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        ButterKnife.bind(this);
        itemAdapter = new ItemAdapter(this);
        itemList.setAdapter(itemAdapter);
        ItemListTask itemListTask = new ItemListTask(this);
        itemListTask.execute();
    }

    public void updateListWithData(ArrayList<Item> items) {
        if (null != items) {
            itemAdapter.setItems(items);
        }
    }
}
