package com.robby.lesson05_android_client;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robby.lesson05_android_client.entity.StatusMessage;
import com.robby.lesson05_android_client.utilities.MyUtilities;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Robby Tan
 */

public class UserHelloTask extends AsyncTask<String, Void, StatusMessage> {

    private Context context;


    public UserHelloTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPostExecute(StatusMessage s) {
        super.onPostExecute(s);
        if (null != s) {
            ((MainActivity) context).updateShowBasedOnWs(s);
        }
    }

    @Override
    protected StatusMessage doInBackground(String... strings) {
        Uri uri = Uri.parse(MyUtilities.WEB_SIMPLE_URL).buildUpon()
                .appendQueryParameter(MyUtilities.SIMPLE_KEY1, strings[0])
                .build();
        HttpURLConnection urlConnection = null;
        StatusMessage statusMessage = null;
        try {
            URL url = new URL(uri.toString());
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(6000);
            urlConnection.connect();
            int responseCode = urlConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                ObjectMapper objectMapper = new ObjectMapper();
                statusMessage = objectMapper.readValue(url, StatusMessage.class);
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
        return statusMessage;
    }
}
