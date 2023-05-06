package sirup.service.log.rpc;

import java.util.Date;

public record Log(String serviceName, String level, Date date, String message) {}
