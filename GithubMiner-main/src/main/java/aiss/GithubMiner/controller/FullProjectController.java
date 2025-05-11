package aiss.GithubMiner.controller;

import aiss.GithubMiner.modelDTO.FullProjectDTO;
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
    public ResponseEntity<FullProjectDTO> getFullProject(
            @PathVariable String owner,
            @PathVariable String repo,
            @RequestHeader("Authorization") String authorizationHeader) {

        // Extraemos solo el token (sin "Bearer ")
        String token = authorizationHeader.replace("Bearer ", "").trim();

        FullProjectDTO project = fullProjectService.getFullProjectSiguiendoElModeloDeDatos(owner, repo, token);

        return ResponseEntity.ok(project);
    }
}