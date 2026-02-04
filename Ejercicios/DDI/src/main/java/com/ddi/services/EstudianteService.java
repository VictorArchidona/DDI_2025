package com.ddi.services;

import com.ddi.exceptions.EstudianteNotFoundException;
import com.ddi.models.Estudiante;
import com.ddi.models.Estudiante;
import com.ddi.repositories.EstudianteRepository;
import com.ddi.repositories.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository repository;

    public List<Estudiante> getAllEstudiantes() {
        return repository.findAll();
    }
    // end::get-aggregate-root[]

    public Estudiante newEstudiante(@RequestBody Estudiante newEstudiante) {
        return repository.save(newEstudiante);
    }

    // Se podria cambiar el optional por return Estudiante si pusiesemos una
    // exception creada por nosotros.
    public Estudiante getEstudiante(@PathVariable Long id) throws EstudianteNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new EstudianteNotFoundException("Estudiante with id = " + id + " not found."));
        // return repository.findById(id)
        //      .orElseThrow(() -> new EstudianteNotFoundException(id));
    }

    public Estudiante replaceEstudiante(@RequestBody Estudiante newEstudiante, @PathVariable Long id) {
        return repository.findById(id)
                .map(Estudiante -> {
                    Estudiante.setNombre(newEstudiante.getNombre());
                    Estudiante.setEmail(newEstudiante.getEmail());
                    return repository.save(Estudiante);
                })
                .orElseGet(() -> {
                    return repository.save(newEstudiante);
                });
    }

    public void deleteEstudiante(@PathVariable Long id) {
        repository.deleteById(id);
    }
}