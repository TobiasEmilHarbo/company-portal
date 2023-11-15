package teh.dev.companyportal.external.database;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class Store<T> {
    private final Map<String, T> store;

    public Store() {
        store = new HashMap<>();
    }

    public T get(String id) {
        return store.get(id);
    }

    public T put(String id, T entity) {
        return store.put(id, entity);
    }
}
