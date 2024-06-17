package at.czlabinger.warehouse.model;

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
                {"Zahnpasta", "Hygiene", "80g"},
                {"Apfelsaft", "Getränk", "1l"},
                {"Salami", "Nahrung", "150g"},
                {"Shampoo", "Hygiene", "250ml"},
                {"Orangensaft", "Getränk", "500ml"},
                {"Vollkornbrot", "Nahrung", "500g"},
                {"Zahnbürste", "Hygiene", "1 Stück"},
                {"Mineralwasser", "Getränk", "1.5l"},
                {"Joghurt", "Nahrung", "200g"},
                {"Handseife", "Hygiene", "150ml"},
                {"Cola", "Getränk", "2l"},
                {"Hähnchenbrust", "Nahrung", "300g"},
                {"Duschgel", "Hygiene", "200ml"}
        };

        String r = String.valueOf(new Random().nextInt(9999999 - 1000000) + 1 + 1000000);
        this.id = r.substring(0, 2) + "-" + r.substring(2, r.length() - 1);

        int rInt = new Random().nextInt(10);
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

    public String toString() {
        return "ID: " + this.id + "; Name: " + this.name + "; Category: " + this.category + "; Amount: " + this.amount + "; Unit: " + this.unit;
    }
}