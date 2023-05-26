package sirup.service.log.rpc;

public class Env {
    public static final int LOG_PORT;
    public static final String LOG_DB_ADDRESS;
    public static final int LOG_DB_PORT;
    static{
        LOG_PORT = Integer.parseInt(System.getenv("LOG_PORT"));
        LOG_DB_ADDRESS = System.getenv("LOG_DB_ADDRESS");
        LOG_DB_PORT = Integer.parseInt(System.getenv("LOG_DB_PORT"));
    }
}
