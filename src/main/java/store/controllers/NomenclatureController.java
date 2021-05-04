package store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import store.db.services.NomenclatureService;
import store.model.Model;
import store.model.Nomenclature;

import java.util.List;

@RestController
@RequestMapping("/api/nomenclature")
public class NomenclatureController implements ApiController {

    @Autowired
    NomenclatureService nomenclatureService;

    @Override
    @GetMapping("/")
    public ResponseEntity<List<Model>> getAll() {
        return null;
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Model> getById(int id) {
        Nomenclature nomenclature = new Nomenclature();
        nomenclature.setId(id);
        try{
            Model response = nomenclatureService.read(nomenclature);
            if (response==null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else
                return new ResponseEntity<>(nomenclature,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PostMapping("/new")
    public ResponseEntity<?> create() {
        return null;
    }
}
