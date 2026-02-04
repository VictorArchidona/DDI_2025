package com.ddi.controllers;

import com.ddi.exceptions.EstudianteNotFoundException;
import com.ddi.models.Estudiante;
import com.ddi.services.EstudianteService;
import com.ddi.services.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class EstudianteController {

    /**
     * Link with Estudiante service
     */
    @Autowired
    private EstudianteService EstudianteService;

    /**
     * Get method to obtain all Estudiantes
     * @return a list of all Estudiantes stored in the database
     */
    @GetMapping("/estudiantes")
    public List<Estudiante> getAllEstudiantes() {
        return EstudianteService.getAllEstudiantes();
    }

    /**
     * Post method to insert a new Estudiante
     * @param json Contains the new Estudiante to be inserted
     * @return the new Estudiante added to the database in json format
     */
    @PostMapping("/estudiantes")
    public Estudiante newEstudiante(@RequestBody Estudiante json) {
        System.out.println("Email recibido: "+json.getEmail());
        return EstudianteService.newEstudiante(json);
    }

    /**
     * Get method to obtain a specific Estudiante
     * @param id identifier of the Estudiante you want to obtain
     * @return Estudiante whose id is {id}
     * @throws EstudianteNotFoundException if address doesn't exist
     */
    @GetMapping("/estudiantes/{id}")
    public Estudiante getEstudiante(@PathVariable Long id) throws EstudianteNotFoundException {
        return EstudianteService.getEstudiante(id);
    }

    /**
     * Put method to replace a specific Estudiante
     * @param newEstudiante new data to insert
     * @param id identifier of the Estudiante you want to replace
     * @return the Estudiante replaced in json format
     */
    @PutMapping("/estudiantes/{id}")
    public Estudiante replaceEstudiante(@RequestBody Estudiante newEstudiante, @PathVariable Long id) {
        return EstudianteService.replaceEstudiante(newEstudiante, id);
    }

    /**
     * Delete method to replace a specific Estudiante
     * @param id identifier of the Estudiante you want to delete
     */
    @DeleteMapping("/estudiantes/{id}")
    public void deleteEstudiante(@PathVariable Long id) {
        EstudianteService.deleteEstudiante(id);
    }
}
