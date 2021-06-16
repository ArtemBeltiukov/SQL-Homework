package store.db.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import store.model.Nomenclature;

import java.util.List;

public interface NomenclatureRepository extends CrudRepository<Nomenclature, Integer> {

    List<Nomenclature> findAll();

    @Query(value = "select * from nomenclature where name =?1", nativeQuery = true)
    Nomenclature findByName(String name);

}
