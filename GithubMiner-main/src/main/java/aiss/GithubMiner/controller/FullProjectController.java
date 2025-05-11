package aiss.GithubMiner.controller;

import aiss.GithubMiner.model.FullProject;
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
    public ResponseEntity<FullProject> getFullProject(
            @PathVariable String owner,
            @PathVariable String repo,
            @RequestHeader("Authorization") String authorizationHeader) {

        // Extraemos solo el token (sin "Bearer ")
        String token = authorizationHeader.replace("Bearer ", "").trim();

        FullProject project = fullProjectService.getFullProject(owner, repo, token);

        return ResponseEntity.ok(project);
    }
}