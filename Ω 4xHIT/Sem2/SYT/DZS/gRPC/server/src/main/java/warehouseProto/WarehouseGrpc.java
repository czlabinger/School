package warehouseProto;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: hello.proto")
public final class WarehouseGrpc {

  private WarehouseGrpc() {}

  public static final String SERVICE_NAME = "Warehouse";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<warehouseProto.Hello.WarehouseRequest,
      warehouseProto.Hello.WarehouseData> getGetDataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getData",
      requestType = warehouseProto.Hello.WarehouseRequest.class,
      responseType = warehouseProto.Hello.WarehouseData.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<warehouseProto.Hello.WarehouseRequest,
      warehouseProto.Hello.WarehouseData> getGetDataMethod() {
    io.grpc.MethodDescriptor<warehouseProto.Hello.WarehouseRequest, warehouseProto.Hello.WarehouseData> getGetDataMethod;
    if ((getGetDataMethod = WarehouseGrpc.getGetDataMethod) == null) {
      synchronized (WarehouseGrpc.class) {
        if ((getGetDataMethod = WarehouseGrpc.getGetDataMethod) == null) {
          WarehouseGrpc.getGetDataMethod = getGetDataMethod = 
              io.grpc.MethodDescriptor.<warehouseProto.Hello.WarehouseRequest, warehouseProto.Hello.WarehouseData>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "Warehouse", "getData"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  warehouseProto.Hello.WarehouseRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  warehouseProto.Hello.WarehouseData.getDefaultInstance()))
                  .setSchemaDescriptor(new WarehouseMethodDescriptorSupplier("getData"))
                  .build();
          }
        }
     }
     return getGetDataMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static WarehouseStub newStub(io.grpc.Channel channel) {
    return new WarehouseStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static WarehouseBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new WarehouseBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static WarehouseFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new WarehouseFutureStub(channel);
  }

  /**
   */
  public static abstract class WarehouseImplBase implements io.grpc.BindableService {

    /**
     */
    public void getData(warehouseProto.Hello.WarehouseRequest request,
        io.grpc.stub.StreamObserver<warehouseProto.Hello.WarehouseData> responseObserver) {
      asyncUnimplementedUnaryCall(getGetDataMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetDataMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                warehouseProto.Hello.WarehouseRequest,
                warehouseProto.Hello.WarehouseData>(
                  this, METHODID_GET_DATA)))
          .build();
    }
  }

  /**
   */
  public static final class WarehouseStub extends io.grpc.stub.AbstractStub<WarehouseStub> {
    private WarehouseStub(io.grpc.Channel channel) {
      super(channel);
    }

    private WarehouseStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WarehouseStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new WarehouseStub(channel, callOptions);
    }

    /**
     */
    public void getData(warehouseProto.Hello.WarehouseRequest request,
        io.grpc.stub.StreamObserver<warehouseProto.Hello.WarehouseData> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetDataMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class WarehouseBlockingStub extends io.grpc.stub.AbstractStub<WarehouseBlockingStub> {
    private WarehouseBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private WarehouseBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WarehouseBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new WarehouseBlockingStub(channel, callOptions);
    }

    /**
     */
    public warehouseProto.Hello.WarehouseData getData(warehouseProto.Hello.WarehouseRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetDataMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class WarehouseFutureStub extends io.grpc.stub.AbstractStub<WarehouseFutureStub> {
    private WarehouseFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private WarehouseFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WarehouseFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new WarehouseFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<warehouseProto.Hello.WarehouseData> getData(
        warehouseProto.Hello.WarehouseRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetDataMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_DATA = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final WarehouseImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(WarehouseImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_DATA:
          serviceImpl.getData((warehouseProto.Hello.WarehouseRequest) request,
              (io.grpc.stub.StreamObserver<warehouseProto.Hello.WarehouseData>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class WarehouseBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    WarehouseBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return warehouseProto.Hello.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Warehouse");
    }
  }

  private static final class WarehouseFileDescriptorSupplier
      extends WarehouseBaseDescriptorSupplier {
    WarehouseFileDescriptorSupplier() {}
  }

  private static final class WarehouseMethodDescriptorSupplier
      extends WarehouseBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    WarehouseMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (WarehouseGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new WarehouseFileDescriptorSupplier())
              .addMethod(getGetDataMethod())
              .build();
        }
      }
    }
    return result;
  }
}
