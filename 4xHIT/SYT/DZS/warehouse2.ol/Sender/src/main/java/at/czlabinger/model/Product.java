package at.czlabinger.model;

import java.io.Serializable;
import java.util.Random;

public class Product implements Serializable {

    private String id;
    private String name;
    private String category;
    private String amount;
    private String unit;

    public Product() {
        String[][] products = {
                {"Bio Orangensaft", "Getränk", "1L Pakung"},
                {"Bio Apfelsaft", "Getränk", "1L Pakung"},
                {"Ariel Waschmittel Color", "Waschmittel", "Packung 3Kg"},
                {"Persil Discs Color", "Waschmittel", "Packung 700g"}
        };

        String r = String.valueOf(new Random().nextInt(1000000, 9999999));
        this.id = r.substring(0, 2) + "-" + r.substring(2, r.length() - 1);

        int rInt = new Random().nextInt(3);
        this.name = products[rInt][0];
        this.category = products[rInt][1];
        this.amount = String.valueOf(new Random().nextInt(5000));
        this.unit = products[rInt][2];
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getCategory() {
        return this.category;
    }

    public String getAmount() {
        return this.amount;
    }

    public String getUnit() {
        return this.unit;
    }
}
