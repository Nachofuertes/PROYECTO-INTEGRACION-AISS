package aiss.gitminer.controller;

import aiss.gitminer.model.Comment;
import aiss.gitminer.model.Issue;
import aiss.gitminer.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/gitminer/issues")
public class IssueController {

    private IssueRepository issueRepository;

    @Autowired
    public IssueController(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    @GetMapping
    public List<Issue> findAll(@RequestParam(required = false) String authorId,
                               @RequestParam(required=false) String state){
        List<Issue> issues;

        //  Sin filtros → todos los issues
        if (authorId == null && state == null) {
            issues = issueRepository.findAll();

        //  Doble filtro autor + estado.
        //  FUNCIÓN NUEVA: se puede filtrar por los dos a la vez
        } else if (authorId != null && state != null) {
            issues = issueRepository.findByAuthor_IdAndState(authorId, state);

        //  Solo autor
        } else if (authorId != null) {
            issues = issueRepository.findByAuthor_Id(authorId);

        //  Solo estado
        } else {
            issues = issueRepository.findByState(state);
        }

        // Si no se encontró nada, 404
        if (issues.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No issues found with the provided filters");
        }

        return issues;
    }

    @GetMapping("/{id}")
    public Issue findOne(@PathVariable @Valid String id ){
        return issueRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Issue " + id + " not found"));
    }

    @GetMapping ("/{id}/comments")
    public List<Comment> findComments(@PathVariable @Valid String id){
        Issue issue= issueRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,  "Comments not found"));
        return issue.getComments();
    }



}
