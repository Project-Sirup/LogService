package sirup.service.log.rpc;

import io.grpc.stub.StreamObserver;
import sirup.service.log.rpc.proto.*;

import static sirup.service.log.rpc.client.ColorUtil.*;

public class LogImplementation extends SirupLogServiceGrpc.SirupLogServiceImplBase {

    @Override
    public void health(HealthRequest request, StreamObserver<HealthResponse> responseObserver) {
        HealthResponse healthResponse = HealthResponse.newBuilder().setHealthCode(200).build();
        responseObserver.onNext(healthResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void debug(DebugRequest request, StreamObserver<DebugResponse> responseObserver) {
        //TODO: make better logging
        System.out.println(blue(request.getLogRequest().getSenderService()) + " -> " + request.getLogRequest().getMessage());
        DebugResponse debugResponse = DebugResponse.newBuilder()
                .setLogResponse(makeLogResponse(request.getLogRequest().getSenderService().isEmpty() ? 400 : 200))
                .build();
        responseObserver.onNext(debugResponse);
        responseObserver.onCompleted();
    }



    @Override
    public void info(InfoRequest request, StreamObserver<InfoResponse> responseObserver) {
        //TODO: make better logging
        System.out.println(green(request.getLogRequest().getSenderService()) + " -> " + request.getLogRequest().getMessage());
        InfoResponse infoResponse = InfoResponse.newBuilder()
                .setLogResponse(makeLogResponse(request.getLogRequest().getSenderService().isEmpty() ? 400 : 200))
                .build();
        responseObserver.onNext(infoResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void warn(WarnRequest request, StreamObserver<WarnResponse> responseObserver) {
        //TODO: make better logging
        System.out.println(yellow(request.getLogRequest().getSenderService()) + " -> " + request.getLogRequest().getMessage());
        WarnResponse warnResponse = WarnResponse.newBuilder()
                .setLogResponse(makeLogResponse(request.getLogRequest().getSenderService().isEmpty() ? 400 : 200))
                .build();
        responseObserver.onNext(warnResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void error(ErrorRequest request, StreamObserver<ErrorResponse> responseObserver) {
        //TODO: make better logging
        System.out.println(red(request.getLogRequest().getSenderService()) + " -> " + request.getLogRequest().getMessage());
        ErrorResponse errorResponse = ErrorResponse.newBuilder()
                .setLogResponse(makeLogResponse(request.getLogRequest().getSenderService().isEmpty() ? 400 : 200))
                .build();
        responseObserver.onNext(errorResponse);
        responseObserver.onCompleted();
    }

    private LogResponse makeLogResponse(int code) {
        return LogResponse.newBuilder().setCode(code).build();
    }
}
