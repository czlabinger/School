package at.czlabinger.warehouse_dom.warehouse;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Random;


@Setter
@Getter
public class Product implements Serializable {

    @Id
    private int id;

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

        this.id = new Random().nextInt(9999999 - 1000000) + 1 + 1000000;

        int rInt = new Random().nextInt(10);
        this.name = products[rInt][0];
        this.category = products[rInt][1];
        this.amount = String.valueOf(new Random().nextInt(5000));
        this.unit = products[rInt][2];
    }

    public String toString() {
        return "ID: " + this.id + "; Name: " + this.name + "; Category: " + this.category + "; Amount: " + this.amount + "; Unit: " + this.unit;
    }
}
