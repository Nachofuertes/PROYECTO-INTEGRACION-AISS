package aiss.gitminer.controller;

import aiss.gitminer.model.Commit;
import aiss.gitminer.repository.CommitRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gitminer/commits")
@Tag(name = "Commits", description = "Operaciones sobre commits")
public class CommitController {

    private CommitRepository commitRepository;

    @Autowired
    public CommitController(CommitRepository commitRepository) {
        this.commitRepository = commitRepository;
    }

    @Operation(summary = "Obtener todos los commits")
    @GetMapping
    public List<Commit> findAll(){
        return commitRepository.findAll();
    }

    @Operation(summary = "Obtener un commit por ID")
    @GetMapping("{id}")
    public Commit findOne(
            @Parameter(description = "ID del commit")
            @PathVariable String id){
        return commitRepository.findById(id).orElse(null);
    }
}
