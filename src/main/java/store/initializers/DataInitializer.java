package store.initializers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import store.db.repository.NomenclatureRepository;
import store.db.services.Service;
import store.model.Nomenclature;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DataInitializer {

    @Autowired
    private Service nomenclatureService;
    @Autowired
    private NomenclatureRepository nomenclatureRepository;

    @PostConstruct
    public void initData() {

        List<Nomenclature> nomenclatures = new ArrayList<>();
        nomenclatures.add(new Nomenclature("Sunflower", 10, 100));
        nomenclatures.add(new Nomenclature("Rose", 20, 200));
        nomenclatures.forEach(x -> x.setId(nomenclatureService.create(x)));
        nomenclatureRepository.findAll();
        nomenclatureRepository.findByName("Rose");
    }
}
