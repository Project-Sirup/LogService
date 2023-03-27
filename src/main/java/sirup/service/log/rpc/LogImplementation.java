package sirup.service.log.rpc;

import io.grpc.stub.StreamObserver;
import sirup.service.log.rpc.client.ColorUtil;
import sirup.service.log.rpc.proto.LogRequest;
import sirup.service.log.rpc.proto.LogResponse;
import sirup.service.log.rpc.proto.SirupLogGrpc;

public class LogImplementation extends SirupLogGrpc.SirupLogImplBase {

    @Override
    public void debug(LogRequest request, StreamObserver<LogResponse> responseObserver) {
        //TODO: make better logging
        System.out.println(ColorUtil.BLUE + request.getSenderService() + ColorUtil.RESET + " -> " + request.getMessage());
        LogResponse logResponse = LogResponse.newBuilder()
                .setCode(request.getSenderService().isEmpty() ? 400 : 200)
                .build();
        responseObserver.onNext(logResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void info(LogRequest request, StreamObserver<LogResponse> responseObserver) {
        //TODO: make better logging
        System.out.println(ColorUtil.GREEN + request.getSenderService() + ColorUtil.RESET + " -> " + request.getMessage());
        LogResponse logResponse = LogResponse.newBuilder()
                .setCode(request.getSenderService().isEmpty() ? 400 : 200)
                .build();
        responseObserver.onNext(logResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void warn(LogRequest request, StreamObserver<LogResponse> responseObserver) {
        //TODO: make better logging
        System.out.println(ColorUtil.YELLOW + request.getSenderService() + ColorUtil.RESET + " -> " + request.getMessage());
        LogResponse logResponse = LogResponse.newBuilder()
                .setCode(request.getSenderService().isEmpty() ? 400 : 200)
                .build();
        responseObserver.onNext(logResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void error(LogRequest request, StreamObserver<LogResponse> responseObserver) {
        //TODO: make better logging
        System.out.println(ColorUtil.RED + request.getSenderService() + ColorUtil.RESET + " -> " + request.getMessage());
        LogResponse logResponse = LogResponse.newBuilder()
                .setCode(request.getSenderService().isEmpty() ? 400 : 200)
                .build();
        responseObserver.onNext(logResponse);
        responseObserver.onCompleted();
    }
}
