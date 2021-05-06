package store.controllers;

import org.springframework.http.ResponseEntity;
import store.model.Model;

import java.util.List;

public interface ApiController {

    ResponseEntity<List<Model>> getAll();

    ResponseEntity<Model> getById(int id);

    ResponseEntity<?> create();

}
