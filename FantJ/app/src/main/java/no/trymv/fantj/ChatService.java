package no.trymv.fantj;

import android.content.Context;

import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ChatService implements Response.ErrorListener {
    public static final String BASE_URL = "http://192.168.0.180:8080/Fant/";
    static ChatService SINGLETON;

    User user;

    String token;
    RequestQueue requestQueue;

    public static ChatService initialize(Context context, String token) {
        SINGLETON = new ChatService(context, token);
        return SINGLETON;
    }

    public static ChatService getInstance() {
        return SINGLETON;
    }

    public ChatService(Context context, String token) {
        this.token = token;
        this.requestQueue = Volley.newRequestQueue(context);
        loadUser();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        System.out.println("Error: " + error);
    }

    public User getUser() {
        return user;
    }

    public void loadUser() {
        requestQueue.add(new SecuredJsonObjectRequest(Request.Method.GET, BASE_URL + "resources/fant/currentuser", null,
                response -> {
                    try {
                        user = new User(response);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, this, token));
    }

    static class SecuredJsonObjectRequest extends JsonObjectRequest {
        String token;

        public SecuredJsonObjectRequest(int method, String url, @Nullable JSONObject jsonRequest,
                                        Response.Listener<JSONObject> listener, @Nullable Response.ErrorListener errorListener, String token) {
            super(method, url, jsonRequest, listener, errorListener);
            this.token = token;
        }

        @Override
        public Map<String, String> getHeaders() {
            HashMap<String,String> result = new HashMap<>();
            result.put("Authorization", "Bearer " + token);
            return result;
        }
    }
}