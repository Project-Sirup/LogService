package sirup.service.log.rpc;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import sirup.service.log.rpc.proto.LogDTO;

import java.util.*;

import static sirup.service.log.rpc.Services.*;

public class LogService {

    private final MongoDatabase database;
    private final Map<String, MongoCollection<Document>> serviceLogs;

    public LogService(final MongoDatabase database) {
        this.database = database;
        this.serviceLogs = new HashMap<>();
    }

    public void writeLog(Log log) {
        MongoCollection<Document> serviceLog;

        if (!serviceLogs.containsKey(log.serviceName())) {
            if (!this.database.listCollectionNames().into(new HashSet<>()).contains(log.serviceName())) {
                this.database.createCollection(log.serviceName());
            }
            serviceLog = this.database.getCollection(log.serviceName());
            this.serviceLogs.put(log.serviceName(), serviceLog);
        }
        else {
            serviceLog = this.serviceLogs.get(log.serviceName());
        }
        Document document = new Document();
        document.put("timestamp", log.date().toString());
        document.put("level", log.level());
        document.put("message", log.message());
        serviceLog.insertOne(document);
    }

    public List<String> getLogList() {
        return this.serviceLogs.keySet().stream().toList();
    }

    public List<LogDTO> getLogsFor(String serviceName) {
        if (!serviceLogs.containsKey(serviceName)) {
            throw new IllegalArgumentException("The specified service [" + serviceName + "] is not logged");
        }
        List<LogDTO> logs = new ArrayList<>();
        serviceLogs.get(serviceName).find().into(new ArrayList<>()).forEach(_log -> {
            LogDTO logDTO = LogDTO.newBuilder()
                    .setLevel((String)_log.get("level"))
                    .setDate((String)_log.get("timestamp"))
                    .setMessage((String)_log.get("message"))
                    .build();
            logs.add(logDTO);
        });
        return logs;
    }
}
