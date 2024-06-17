package at.czlabinger.warehouse_dom;

import at.czlabinger.warehouse_dom.warehouse.WarehouseSimulation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WarehouseDOMController {

    private final WarehouseRepository warehouseRepository;

    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAll() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.writeValueAsString(warehouseRepository.findAll());
        } catch (JsonProcessingException e) {
            return "{\"error\":\"" + e.getMessage() + "\"}";
        }
    }

    @PostMapping(value = "/add")
    public void add(@RequestParam int id) {
        warehouseRepository.save(new WarehouseSimulation().getData(id));
    }

}
