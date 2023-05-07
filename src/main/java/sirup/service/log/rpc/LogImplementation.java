package sirup.service.log.rpc;

import io.grpc.stub.StreamObserver;
import sirup.service.log.rpc.proto.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import static sirup.service.log.rpc.client.ColorUtil.*;

public class LogImplementation extends SirupLogServiceGrpc.SirupLogServiceImplBase {

    private final LogService logger = new LogService(MongoDB.getInstance().getMongoDatabase());

    @Override
    public void health(HealthRequest request, StreamObserver<HealthResponse> responseObserver) {
        HealthResponse healthResponse = HealthResponse.newBuilder().setHealthCode(200).build();
        responseObserver.onNext(healthResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void logList(LogListRequest request, StreamObserver<LogListResponse> responseObserver) {
        //TODO: auth

        LogListResponse logListResponse = LogListResponse.newBuilder()
                .addAllLogList(logger.getLogList())
                .build();
        responseObserver.onNext(logListResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void logFrom(LogFromRequest request, StreamObserver<LogFromResponse> responseObserver) {
        LogFromResponse.Builder logFromResponseBuilder = LogFromResponse.newBuilder();
        try {
            logFromResponseBuilder.addAllLogs(logger.getLogsFor(request.getLogName()));
            logFromResponseBuilder.setFound(true);
        } catch (IllegalArgumentException e) {
            logFromResponseBuilder.setFound(false);
        }

        responseObserver.onNext(logFromResponseBuilder.build());
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
        logger.writeLog(new Log(request.getLogRequest().getSenderService(), "info", new Date(), request.getLogRequest().getMessage()));
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
        logger.writeLog(new Log(request.getLogRequest().getSenderService(), "warn", new Date(), request.getLogRequest().getMessage()));
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
        logger.writeLog(new Log(request.getLogRequest().getSenderService(), "error", new Date(), request.getLogRequest().getMessage()));
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
