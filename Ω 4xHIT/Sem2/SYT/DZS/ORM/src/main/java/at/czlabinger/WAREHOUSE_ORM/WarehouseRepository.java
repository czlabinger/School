package at.czlabinger.WAREHOUSE_ORM;

import at.czlabinger.WAREHOUSE_ORM.warehouse.WarehouseData;
import org.springframework.data.repository.CrudRepository;

public interface WarehouseRepository extends CrudRepository<WarehouseData, Integer> {

}