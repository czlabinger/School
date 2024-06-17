package at.czlabinger.warehouse_dom.warehouse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WarehouseSimulation {

    public WarehouseData getData(int id) {

        String[][] cities = {
                {"Wuernitz", "2112", "Robert Stolz Gasse 1", "Wuernitz Lager", "Österreich"},
                {"Salzburg", "5020", "Hauptstraße 5", "Salzburg Lager", "Österreich"},
                {"Wien", "1010", "Bruno Kreisky Platz 1", "Wien Lager", "Österreich"}
        };

        int r = new Random().nextInt(2);

        WarehouseData data = new WarehouseData();
        data.setId(id);
        data.setName(cities[r][3]);
        data.setStreet(cities[r][2]);
        data.setPlz(cities[r][1]);
        data.setCity(cities[r][0]);
        data.setCountry(cities[r][4]);
        data.setTimestamp(LocalDateTime.now().toString());

        List<Product> products = new ArrayList<>();

        for(int i = 0; i < 30; i++) {
            Product p = new Product();
            products.add(p);
        }

        data.setProductData(products);

        return data;
    }
}