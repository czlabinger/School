package at.czlabinger.warehouse_dom;

import at.czlabinger.warehouse_dom.warehouse.WarehouseData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends MongoRepository<WarehouseData, Integer> {

}
