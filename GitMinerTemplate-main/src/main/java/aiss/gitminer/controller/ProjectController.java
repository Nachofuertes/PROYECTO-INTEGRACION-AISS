package aiss.gitminer.controller;

import aiss.gitminer.model.Project;
import aiss.gitminer.repository.ProjectRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/gitminer/projects")
@Tag(name = "Projects", description = "Operaciones sobre proyectos")
public class ProjectController {

    private ProjectRepository projectRepository;

    @Autowired
    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Operation(summary = "Obtener todos los proyectos")
    @GetMapping
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Operation(summary = "Obtener un proyecto por ID")
    @GetMapping("/{id}")
    public Project findOne(
            @Parameter(description = "ID del proyecto")
            @PathVariable String id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Project " + id + " not found"));
    }

    @Operation(summary = "Crear un nuevo proyecto")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Project createProject(
            @Parameter(description = "Objeto del proyecto a crear")
            @RequestBody Project project) {
        return projectRepository.save(project);
    }
}
