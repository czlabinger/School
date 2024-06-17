package at.czlabinger.warehouse;

import at.czlabinger.warehouse.model.WarehouseData;

public class WarehouseService {

    public WarehouseData getWarehouseData(String inID ) {

        return new WarehouseSimulation().getData(inID);

    }

}