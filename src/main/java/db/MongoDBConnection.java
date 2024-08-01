package db;



import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnection {
    private static final String CONNECTION_STRING = "mongodb://localhost:27017";
    private MongoClient client;

    public MongoDBConnection() {
        client = MongoClients.create(CONNECTION_STRING);
    }

    public MongoDatabase getDatabase(String databaseName) {
        return client.getDatabase(databaseName);
    }

    public MongoClient getClient() {
        return client;
    }
}
