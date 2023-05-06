package sirup.service.log.rpc;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDB {

    private MongoDB() {}

    private static MongoDB instance;

    public static MongoDB getInstance() {
        return instance == null ? instance = new MongoDB() : instance;
    }

    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;

    public boolean connect() {
        try {
            this.mongoClient = MongoClients.create("mongodb://localhost:27017");
            this.mongoDatabase = mongoClient.getDatabase("sirupLog");
            return true;
        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }
    }

    public MongoDatabase getMongoDatabase() {
        return this.mongoDatabase;
    }
}
