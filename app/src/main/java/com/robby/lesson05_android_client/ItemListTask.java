package com.robby.lesson05_android_client;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robby.lesson05_android_client.entity.Item;
import com.robby.lesson05_android_client.entity.UserData;
import com.robby.lesson05_android_client.utilities.MyUtilities;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Robby Tan
 */

public class ItemListTask extends AsyncTask<Void, Void, ArrayList<Item>> {

    private Context context;

    public ItemListTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPostExecute(ArrayList<Item> items) {
        super.onPostExecute(items);
        ((DataActivity) context).updateListWithData(items);
    }

    @Override
    protected ArrayList<Item> doInBackground(Void... voids) {
        ArrayList<Item> items = new ArrayList<>();
        Uri uri = Uri.parse(MyUtilities.ITEM_URL).buildUpon().build();
        HttpURLConnection urlConnection = null;
        UserData user = null;
        try {
            URL url = new URL(uri.toString());
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(6000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            int responseCode = urlConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                ObjectMapper objectMapper = new ObjectMapper();
                items.addAll(Arrays.asList(objectMapper.readValue(url, Item[].class)));
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return items;
    }
}
