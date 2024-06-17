package service;

import at.czlabinger.warehouse.WarehouseSimulation;
import at.czlabinger.warehouse.model.Product;
import at.czlabinger.warehouse.model.WarehouseData;
import io.grpc.stub.StreamObserver;
import warehouseProto.WarehouseGrpc;
import warehouseProto.Hello;

public class WarehouseImpl extends WarehouseGrpc.WarehouseImplBase {

    @Override
    public void getData(Hello.WarehouseRequest request, StreamObserver<Hello.WarehouseData> responseObserver) {
        WarehouseSimulation simulation = new WarehouseSimulation();
        WarehouseData warehouseData = simulation.getData(request.getId());

        Hello.WarehouseData.Builder warehouseDataBuilder = Hello.WarehouseData.newBuilder();
        warehouseDataBuilder.setId(warehouseData.getID());
        warehouseDataBuilder.setName(warehouseData.getName());
        warehouseDataBuilder.setTimestamp(warehouseData.getTimestamp());
        warehouseDataBuilder.setStreet(warehouseData.getStreet());
        warehouseDataBuilder.setCity(warehouseData.getCity());
        warehouseDataBuilder.setCountry(warehouseData.getCountry());
        warehouseDataBuilder.setPlz(warehouseData.getPlz());
        for (Product p : warehouseData.getProductData()) {
            Hello.Product.Builder productBuilder = Hello.Product.newBuilder();
            productBuilder.setId(p.getId());
            productBuilder.setName(p.getName());
            productBuilder.setCategory(p.getCategory());
            productBuilder.setAmount(p.getAmount());
            productBuilder.setUnit(p.getUnit());
            warehouseDataBuilder.addProductData(productBuilder.build());
        }
        Hello.WarehouseData serializedWarehouseData = warehouseDataBuilder.build();

        responseObserver.onNext(serializedWarehouseData);
        responseObserver.onCompleted();
    }
}