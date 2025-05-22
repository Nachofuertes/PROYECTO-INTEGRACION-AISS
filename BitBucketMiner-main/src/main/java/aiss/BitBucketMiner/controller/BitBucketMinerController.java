package aiss.BitBucketMiner.controller;

import aiss.BitBucketMiner.dto.ProjectDTO;
import aiss.BitBucketMiner.service.BitBucketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bitbucket")
public class BitBucketMinerController {

    private final BitBucketService service;

    public BitBucketMinerController(BitBucketService service) {
        this.service = service;
    }

    @GetMapping("/{workspace}/{repo_slug}")
    public ResponseEntity<ProjectDTO> getRepositoryData(
            @PathVariable String workspace,
            @PathVariable("repo_slug") String repoSlug,
            @RequestParam(required = false) Integer nCommits,
            @RequestParam(required = false) Integer nIssues,
            @RequestParam(required = false) Integer maxPages) {

        ProjectDTO project = service.fetchAndConvertToDTO(workspace, repoSlug, nCommits, nIssues, maxPages);
        return ResponseEntity.ok(project);
    }

    @PostMapping("/{workspace}/{repo_slug}")
    public ResponseEntity<Void> postRepositoryData(
            @PathVariable String workspace,
            @PathVariable("repo_slug") String repoSlug,
            @RequestParam(required = false) Integer nCommits,
            @RequestParam(required = false) Integer nIssues,
            @RequestParam(required = false) Integer maxPages) {

        ProjectDTO project = service.fetchAndConvertToDTO(workspace, repoSlug, nCommits, nIssues, maxPages);
        service.postProject(project);
        return ResponseEntity.ok().build();
    }
}
