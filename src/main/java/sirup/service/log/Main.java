package sirup.service.log;

import sirup.service.log.rpc.LogServer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        final LogServer server = new LogServer();
        server.start();
        server.blockUntilShutdown();
    }
}
