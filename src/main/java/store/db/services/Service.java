package store.db.services;

import store.model.Model;

public interface Service {
    int create(Model model);
    Model read(Model model);
    void update(Model model);
    void delete(Model model);
}
