package no.trymv.fantj.data.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Item {
    private String title;
    private String description;
    private String itemId;
    private String createdDate;
    private String priceNok;

    public Item(JSONObject jo) throws JSONException {
        setItemId(jo.getString("id"));
        if (jo.has("description")) {
            setDescription(jo.getString("description"));
            System.out.println("Description is: " + this.description + "\n");
        }
        if (jo.has("title")) {
            setDescription(jo.getString("title"));
            System.out.println("Title is: " + this.title + "\n");
        }
        if (jo.has("createdDate")) {
            setDescription(jo.getString("createdDate"));
            System.out.println("CreatedDate is: " + this.createdDate + "\n");
        }
        if (jo.has("priceNok")) {
            setDescription(jo.getString("priceNok"));
            System.out.println("Price is: " + this.priceNok + "\n");
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getPriceNok() {
        return priceNok;
    }

    public void setPriceNok(String priceNok) {
        this.priceNok = priceNok;
    }
}
