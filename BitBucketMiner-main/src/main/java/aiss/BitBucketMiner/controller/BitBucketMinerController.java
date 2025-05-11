package aiss.BitBucketMiner.controller;

import aiss.BitBucketMiner.model.Project;
import aiss.BitBucketMiner.service.BitBucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bitbucket")
public class BitBucketMinerController {

    @Autowired
    private BitBucketService service;

    // Endpoint GET (para consultar datos)
    @GetMapping("/{workspace}/{repo_slug}")
    public ResponseEntity<Project> getRepositoryData(
            @PathVariable String workspace,
            @PathVariable("repo_slug") String repoSlug,
            @RequestParam(required = false, defaultValue = "${bitbucket.api.default.ncommits}") Integer nCommits,
            @RequestParam(required = false, defaultValue = "${bitbucket.api.default.nissues}") Integer nIssues,
            @RequestParam(required = false, defaultValue = "${bitbucket.api.default.maxpages}") Integer maxPages) {

        Project project = service.fetchAndProcessRepository(workspace, repoSlug, nCommits, nIssues, maxPages);
        return ResponseEntity.ok(project);
    }

    // Endpoint POST (para enviar datos a GitMiner)
    @PostMapping("/{workspace}/{repo_slug}")
    public ResponseEntity<Project> postRepositoryData(
            @PathVariable String workspace,
            @PathVariable("repo_slug") String repoSlug,
            @RequestParam(required = false, defaultValue = "${bitbucket.api.default.ncommits}") Integer nCommits,
            @RequestParam(required = false, defaultValue = "${bitbucket.api.default.nissues}") Integer nIssues,
            @RequestParam(required = false, defaultValue = "${bitbucket.api.default.maxpages}") Integer maxPages) {

        Project project = service.fetchAndProcessRepository(workspace, repoSlug, nCommits, nIssues, maxPages);
        service.postProject(project); // Asume que tienes este método en el servicio
        return ResponseEntity.ok(project);
    }
}