package store;

import store.db.DBService;
import store.initializers.DataInitializer;

public class Main {
    public static void main(String[] args) {
        DBService dbService = new DBService();
        dbService.init();
        DataInitializer dataInitializer = new DataInitializer();
        dataInitializer.init();
    }
}
