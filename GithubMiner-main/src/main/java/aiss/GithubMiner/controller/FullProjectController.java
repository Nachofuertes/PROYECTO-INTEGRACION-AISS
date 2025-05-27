package aiss.GithubMiner.controller;

import aiss.GithubMiner.parse.model.ProjectDTO;
import aiss.GithubMiner.service.FullProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/github")
@Tag(name = "GitHub Miner", description = "Operaciones para obtener y guardar datos desde repositorios de GitHub")
public class FullProjectController {

    @Autowired
    private FullProjectService fullProjectService;

    @Operation(
            summary = "Obtener datos completos de un repositorio de GitHub",
            description = "Obtiene todos los datos relevantes del repositorio (commits, issues, usuarios...) y los adapta al modelo GitMiner"
    )
    @GetMapping("/{owner}/{repo}")
    public ResponseEntity<ProjectDTO> getFullProject(
            @Parameter(description = "Propietario del repositorio") @PathVariable String owner,
            @Parameter(description = "Nombre del repositorio") @PathVariable String repo,
            @Parameter(description = "Token de autenticación de GitHub en formato 'Bearer <token>'", required = true)
            @RequestHeader("Authorization") String authorizationHeader) {

        String token = authorizationHeader.replace("Bearer ", "").trim();
        ProjectDTO project = fullProjectService.getFullProject(owner, repo, token);
        return ResponseEntity.ok(project);
    }

    @Operation(
            summary = "Guardar datos de un repositorio de GitHub",
            description = "Obtiene y guarda la información de un proyecto desde GitHub transformada al modelo GitMiner"
    )
    @PostMapping("/{owner}/{repo}")
    public ResponseEntity<ProjectDTO> createRepo(
            @Parameter(description = "Propietario del repositorio") @PathVariable String owner,
            @Parameter(description = "Nombre del repositorio") @PathVariable String repo,
            @Parameter(description = "Token de autenticación de GitHub en formato 'Bearer <token>'", required = true)
            @RequestHeader("Authorization") String authorizationHeader) {

        String token = authorizationHeader.replace("Bearer ", "").trim();
        ProjectDTO project = fullProjectService.getFullProject(owner, repo, token);
        fullProjectService.postProject(project);
        return ResponseEntity.ok(project);
    }
}
