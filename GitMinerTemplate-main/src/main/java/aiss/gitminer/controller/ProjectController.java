package aiss.gitminer.controller;

import aiss.gitminer.model.Project;
import aiss.gitminer.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gitminer/projects")
public class ProjectController {

    private ProjectRepository projectRepository;

    @Autowired
    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    //Get all projects: http://localhost:8080/gitminer/projects
    @GetMapping
    public List<Project> findAll() {return projectRepository.findAll();}

    // Get a project: http://localhost:8080/gitminer/projects/15717393
    @GetMapping("/{id}")
    public Project findOne(@PathVariable String id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Project " + id + " not found"));
    }

    // POST: http://localhost:8080/gitminer/projects
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Project createProject(@RequestBody  Project project) {
        return projectRepository.save(project);
    }

}
