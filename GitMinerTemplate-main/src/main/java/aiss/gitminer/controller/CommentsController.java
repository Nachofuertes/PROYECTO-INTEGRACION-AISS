package aiss.gitminer.controller;

import aiss.gitminer.model.Comment;
import aiss.gitminer.repository.CommentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/gitminer/comments")
public class CommentsController {
    private CommentRepository commentRepository;

    public CommentsController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @GetMapping
    public List<Comment> findAll(){
        return commentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Comment findOne(@PathVariable @Valid String id){
        return commentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Comment " + id + " not found"));
    }
}
