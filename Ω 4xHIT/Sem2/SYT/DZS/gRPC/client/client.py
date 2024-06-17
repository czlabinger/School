import hello_pb2_grpc
import hello_pb2
import grpc


def main():
    channel = grpc.insecure_channel('localhost:8999')
    stub = hello_pb2_grpc.WarehouseStub(channel)

    # Create a HelloRequest instance and set the name field
    request = hello_pb2.WarehouseRequest(id="001")

    # Pass the request instance to the sayHello method
    response = stub.getData(request)
    print(response)


if __name__ == '__main__':
    main()
