package at.czlabinger.WAREHOUSE_ORM.warehouse;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Entity
public class WarehouseData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String timestamp;

    private String street;

    private String city;

    private String country;

    private String plz;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> productData;


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: " + this.id + "\n");
        sb.append("Name: " + this.name + "\n");
        sb.append("Timestamp: " + this.timestamp + "\n");
        sb.append("Street: " + this.street + "\n");
        sb.append("City: " + this.city + "\n");
        sb.append("Country: " + this.country + "\n");
        sb.append("PLZ: " + this.plz + "\n");
        sb.append("ProductData: \n");
        for (Product p : this.productData) {
            sb.append(p.toString() + "\n");
        }
        return sb.toString();
    }
}