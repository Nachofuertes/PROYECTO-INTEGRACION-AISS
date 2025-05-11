package aiss.GithubMiner.controller;

import aiss.GithubMiner.modelDTO.FullProjectDTO;
import aiss.GithubMiner.service.FullProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/github") //establecemos la ruta base
public class GithubController {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    FullProjectService fullProjectService;

    @GetMapping("/{owner}/{repo}")


    public ResponseEntity<FullProjectDTO> getFullProjectDTO(
            @PathVariable String owner,
            @PathVariable String repo,
            @RequestHeader("Authorization") String token) {

        FullProjectDTO fullProjectDTO = fullProjectService.getFullProjectSiguiendoElModeloDeDatos(owner, repo, token);
        return ResponseEntity.ok(fullProjectDTO);
    }

    /*
    public responseEntity<FullProjectDTO> getFullProjectDTOd(@PathVariable String owner, @PathVariable String repo, @RequestHeader("Authorization") String token) {
        FullProjectDTO e =  fullProjectService.getFullProjectSiguiendoElModeloDeDatos(owner, repo, token);
        return ResponseEntity.ok(e);
    }

     */


}
