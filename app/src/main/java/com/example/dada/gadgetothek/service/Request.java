package com.example.dada.gadgetothek.service;

import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;

import java.lang.reflect.Type;
import java.net.ConnectException;
import java.util.HashMap;
import java.util.Map;

class Request<T> extends AsyncTask<Void, Void, Pair<String, T>> {

    private static final String TAG = Request.class.getSimpleName();
    private HttpVerb requestKind;
    private final String url;
    private final Type resultType;
    private HashMap<String, String> parameterList;
    private Callback<T> callback;

    public Request(HttpVerb type, String url, Type typeClass, HashMap<String, String> parameterList, Callback<T> callback) {
        this.requestKind = type;
        this.url = url;
        this.resultType = typeClass;
        this.parameterList = parameterList;
        this.callback = callback;
    }

    protected Pair<String, T> doInBackground(Void... unused) {
        return getData(url, resultType);
    }

    private Pair<String, T> getData(String url, Type type) {
        Log.d(TAG, "Requesting " + url);
        AsyncHttpClient c = new AsyncHttpClient();
        String responseBody = "";
        Gson gson = LibraryService.createGsonObject();
        try {

            AsyncHttpClient.BoundRequestBuilder request = null;

            switch (requestKind) {
                case POST:
                    request = c.preparePost(url);
                    for (Map.Entry<String, String> entry : parameterList.entrySet()) {
                        request.addFormParam(entry.getKey(), entry.getValue());
                    }
                    break;
                case GET:
                    request = c.prepareGet(url);
                    for (Map.Entry<String, String> entry : parameterList.entrySet()) {
                        request.addQueryParam(entry.getKey(), entry.getValue());
                        request.addFormParam(entry.getKey(), entry.getValue());
                    }
                    break;
                case DELETE:
                    request = c.prepareDelete(url);
                    for (Map.Entry<String, String> entry : parameterList.entrySet()) {
                        request.addFormParam(entry.getKey(), entry.getValue());
                    }
                    break;
            }

            Response response = request.execute().get();

            responseBody = response.getResponseBody();

            Log.d(this.getClass().getSimpleName(), "Response received: " + responseBody);

            return new Pair<>(null, (T) gson.fromJson(responseBody, type));

        } catch (JsonParseException e) {
            String message = e.getMessage();
            try {
                message = gson.fromJson(responseBody, String.class);
            } catch (JsonParseException _) {
            }
            return new Pair<>(message, null);
        } catch (ConnectException e) {
            Log.e(TAG, "Could not connect", e);
            return new Pair<>("Could not connect to server", null);
        } catch (Exception e) {
            Log.e(TAG, "Could not perform request", e);
            return new Pair<>(e.getMessage(), null);
        } finally {
            c.close();
        }
    }

    protected void onPostExecute(Pair<String, T> result) {
        if (result.first != null) {
            callback.onError(result.first);
        } else {
            callback.onCompletion(result.second);
        }
    }
}
