package no.trymv.fantj.data.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FantMarket {
    List<Item> items = new ArrayList<>();

    public FantMarket(JSONObject jo) throws JSONException {
        System.out.println("FantMarket!\n");
        if(jo != null) {
            System.out.println("FantMarket if was true\n");
            Item itemToAdd = new Item(jo);
                this.items.add(itemToAdd);
        } else {
            System.out.println("FantMarket if was false\n");
        }
    }

    public Item getItem(int itemInList) {
        return items != null && items.size() > 0 ? items.get(itemInList) : null;
    }
}
