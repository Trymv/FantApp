package no.trymv.fantj.data.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FantMarket {
    List<Item> items;

    public FantMarket(JSONObject jo) throws JSONException {
        System.out.println("FantMarket!\n");
        if(jo.has("items")) {
            System.out.println("FantMarket if was true\n");
            this.items = new ArrayList<>();

            JSONArray jItem = jo.getJSONArray("items");
            for(int i = 0; i < jItem.length(); i++) {
                this.items.add(new Item(jItem.getJSONObject(i)));
            }
        } else {
            System.out.println("FantMarket if was false\n");
        }
    }

    public Item getItem(int itemInList) {
        return items != null && items.size() > 0 ? items.get(itemInList) : null;
    }
}
