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
    BitBucketService service;

    @GetMapping("/{workspace}/{repo_slug}")
    public ResponseEntity<Project> getProject(
            @PathVariable String workspace,
            @PathVariable("repo_slug") String repoSlug,
            @RequestParam(required = false) Integer nCommits,
            @RequestParam(required = false) Integer nIssues,
            @RequestParam(required = false) Integer maxPages) {

        Project project = service.getProject(workspace, repoSlug, nCommits, nIssues, maxPages);
        return ResponseEntity.ok(project);
    }

    @PostMapping("/{workspace}/{repo_slug}")
    public ResponseEntity<Project> postProject(
            @PathVariable String workspace,
            @PathVariable("repo_slug") String repoSlug,
            @RequestParam(required = false) Integer nCommits,
            @RequestParam(required = false) Integer nIssues,
            @RequestParam(required = false) Integer maxPages) {

        Project project = service.getProject(workspace, repoSlug, nCommits, nIssues, maxPages);
        service.postProject(project);
        return ResponseEntity.ok(project);
    }
}