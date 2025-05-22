package aiss.GithubMiner.controller;

import aiss.GithubMiner.parse.model.ProjectDTO;
import aiss.GithubMiner.service.FullProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/github")
public class FullProjectController {

    @Autowired
    private FullProjectService fullProjectService;

    @GetMapping("/{owner}/{repo}")
    public ResponseEntity<ProjectDTO> getFullProject(
            @PathVariable String owner,
            @PathVariable String repo,
            @RequestHeader("Authorization") String authorizationHeader) {

        // Extraemos solo el token (sin "Bearer ")
        String token = authorizationHeader.replace("Bearer ", "").trim();

        ProjectDTO project = fullProjectService.getFullProject(owner, repo, token);

        return ResponseEntity.ok(project);
    }

   @PostMapping("/{owner}/{repo}")
    public ResponseEntity<ProjectDTO> createRepo(
            @PathVariable String owner,
            @PathVariable String repo,
            @RequestHeader("Authorization") String authorizationHeader) {

        String token = authorizationHeader.replace("Bearer ", "").trim();

        ProjectDTO project =  fullProjectService.getFullProject(owner, repo, token);
        fullProjectService.postProject(project);
        return ResponseEntity.ok(project);
    }
}