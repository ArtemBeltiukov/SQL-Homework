package store.db.services;

import store.model.Model;
import store.model.Nomenclature;

import java.util.List;

public interface Service {
    int create(Model model);

    Model read(Model model);

    void update(Model model);

    void delete(Model model);

    List<Model> getAll();

    List<Nomenclature> getAllByCriteria();
}
