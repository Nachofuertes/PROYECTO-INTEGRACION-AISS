package aiss.BitBucketMiner.controller;

import aiss.BitBucketMiner.dto.ProjectDTO;
import aiss.BitBucketMiner.service.BitBucketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bitbucket")
@Tag(name = "Bitbucket Miner", description = "Operaciones para obtener y guardar datos desde repositorios de Bitbucket")
public class BitBucketMinerController {

    private final BitBucketService service;

    public BitBucketMinerController(BitBucketService service) {
        this.service = service;
    }

    @Operation(
            summary = "Obtener datos de un repositorio de Bitbucket",
            description = "Recupera la información del proyecto, sus commits e issues desde un repositorio de Bitbucket y la transforma al modelo GitMiner"
    )
    @GetMapping("/{workspace}/{repo_slug}")
    public ResponseEntity<ProjectDTO> getRepositoryData(
            @Parameter(description = "Nombre del workspace de Bitbucket") @PathVariable String workspace,
            @Parameter(description = "Slug del repositorio") @PathVariable("repo_slug") String repoSlug,
            @Parameter(description = "Número máximo de commits a obtener (opcional)") @RequestParam(required = false) Integer nCommits,
            @Parameter(description = "Número máximo de issues a obtener (opcional)") @RequestParam(required = false) Integer nIssues,
            @Parameter(description = "Número máximo de páginas a procesar (opcional)") @RequestParam(required = false) Integer maxPages) {

        ProjectDTO project = service.fetchAndConvertToDTO(workspace, repoSlug, nCommits, nIssues, maxPages);
        return ResponseEntity.ok(project);
    }

    @Operation(
            summary = "Guardar datos de un repositorio de Bitbucket",
            description = "Obtiene y guarda la información de un proyecto desde Bitbucket transformada al modelo GitMiner"
    )
    @PostMapping("/{workspace}/{repo_slug}")
    public ResponseEntity<ProjectDTO> postRepositoryData(
            @Parameter(description = "Nombre del workspace de Bitbucket") @PathVariable String workspace,
            @Parameter(description = "Slug del repositorio") @PathVariable("repo_slug") String repoSlug,
            @Parameter(description = "Número máximo de commits a obtener (opcional)") @RequestParam(required = false) Integer nCommits,
            @Parameter(description = "Número máximo de issues a obtener (opcional)") @RequestParam(required = false) Integer nIssues,
            @Parameter(description = "Número máximo de páginas a procesar (opcional)") @RequestParam(required = false) Integer maxPages) {

        ProjectDTO project = service.fetchAndConvertToDTO(workspace, repoSlug, nCommits, nIssues, maxPages);
        ProjectDTO saved = service.postProject(project);
        return ResponseEntity.ok(saved);
    }
}
