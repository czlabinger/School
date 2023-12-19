package at.czlabinger.zentrale;

import at.czlabinger.model.WarehouseData;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


@RestController
@CrossOrigin
public class WarehouseController {

    @RequestMapping("/zentrale")
    public String warehouseMain() {
        return "Warehouse Zentrale"
                +
                "<a href='http://localhost:8080/zentrale/data'>Daten anzeigen</a><br/>";
    }

    @RequestMapping(value = "/zentrale/data", produces = MediaType.APPLICATION_JSON_VALUE)
    public HashMap<String, ArrayList<WarehouseData>> getWarehouseData() {
        HashMap<String, ArrayList<WarehouseData>> data = new HashMap<>();
        
        Registration.updateRegistrations();
        HashSet<String> keys = new HashSet<String>(Registration.keys());
        for (String key : keys) {
            data.put(key, Registration.get(key).getMessage());
        }
        return data;
    }
}

