package no.trymv.fantj;

import android.content.Context;

import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import no.trymv.fantj.data.model.FantMarket;
import no.trymv.fantj.data.model.User;

public class FantService implements Response.ErrorListener {
    public static final String BASE_URL = "http://192.168.0.180:8080/";
    static FantService SINGLETON;

    User user;

    String token;
    RequestQueue requestQueue;

    public static FantService initialize(Context context, String token) {
        SINGLETON = new FantService(context, token);
        System.out.println("Token is: " + token + "\n");
        return SINGLETON;
    }

    public static FantService getInstance() {
        return SINGLETON;
    }

    public FantService(Context context, String token) {
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
        requestQueue.add(new SecuredJsonObjectRequest(Request.Method.GET, BASE_URL + "Fant/resources/fant/currentuser", null,
                response -> {
                    System.out.println("Response is: " + response + "\n");
                    try {
                        user = new User(response);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, this));
    }

    class SecuredJsonObjectRequest extends JsonObjectRequest {
        public SecuredJsonObjectRequest(int method, String url, @Nullable JSONObject jsonRequest,
                                        Response.Listener<JSONObject> listener, @Nullable Response.ErrorListener errorListener) {
            super(method, url, jsonRequest, listener, errorListener);
        }

        @Override
        public Map<String, String> getHeaders() {
            return FantService.this.getHeaders();
        }
    }

    public interface Callback<Result> {
        void onPostExecute(Result result);
    }

    protected Map<String,String> getHeaders() {
        HashMap<String,String> result = new HashMap<>();
        result.put("Authorization", "Bearer " + token);
        return result;
    }

    public void loadItems(Callback<List<FantMarket>> onPostExecute, Response.ErrorListener onError) {
        requestQueue.add(new SecuredJsonArrayRequest(Request.Method.GET, BASE_URL + "Fant/resources/fantmarket/allSales", null,
                response -> {
                    List<FantMarket> result = new ArrayList<>();
                    try {
                        for (int i = 0; i < response. length(); i++) {
                            System.out.println("Sale respond " + i + " is: " + response.getJSONObject(i) + "\n");
                            result.add(new FantMarket(response.getJSONObject(i)));
                        }
                    } catch (JSONException e) {
                        onError.onErrorResponse(new VolleyError(e));
                    }
                    onPostExecute.onPostExecute(result);
                }, onError));
    }

    class SecuredJsonArrayRequest extends JsonArrayRequest {
        public SecuredJsonArrayRequest(int method, String url, @Nullable JSONArray jsonRequest,
                                       Response.Listener<JSONArray> listener, @Nullable Response.ErrorListener errorListener) {
            super(method, url, jsonRequest, listener, errorListener);
        }
        @Override
        public Map<String, String> getHeaders() {
            return FantService.this.getHeaders();
        }
    }
}