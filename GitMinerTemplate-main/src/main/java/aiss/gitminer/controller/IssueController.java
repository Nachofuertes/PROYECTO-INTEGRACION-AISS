package aiss.gitminer.controller;

import aiss.gitminer.model.Comment;
import aiss.gitminer.model.Issue;
import aiss.gitminer.repository.IssueRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/gitminer/issues")
@Tag(name = "Issues", description = "Operaciones sobre issues")
public class IssueController {

    private IssueRepository issueRepository;

    @Autowired
    public IssueController(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    @Operation(summary = "Obtener todos los issues, con posibilidad de filtrar por autor o estado")
    @GetMapping
    public List<Issue> findAll(
            @Parameter(description = "ID del autor") @RequestParam(required = false) String authorId,
            @Parameter(description = "Estado del issue") @RequestParam(required = false) String state) {

        List<Issue> issues;

        if (authorId == null && state == null) {
            issues = issueRepository.findAll();
        } else if (authorId != null && state != null) {
            issues = issueRepository.findByAuthor_IdAndState(authorId, state);
        } else if (authorId != null) {
            issues = issueRepository.findByAuthor_Id(authorId);
        } else {
            issues = issueRepository.findByState(state);
        }

        if (issues.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No issues found with the provided filters");
        }

        return issues;
    }

    @Operation(summary = "Obtener un issue por ID")
    @GetMapping("/{id}")
    public Issue findOne(
            @Parameter(description = "ID del issue")
            @PathVariable @Valid String id){
        return issueRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Issue " + id + " not found"));
    }

    @Operation(summary = "Obtener los comentarios de un issue")
    @GetMapping("/{id}/comments")
    public List<Comment> findComments(
            @Parameter(description = "ID del issue")
            @PathVariable @Valid String id){
        Issue issue = issueRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Comments not found"));
        return issue.getComments();
    }
}
