package aiss.gitminer.controller;

import aiss.gitminer.model.Comment;
import aiss.gitminer.repository.CommentRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/gitminer/comments")
@Tag(name = "Comments", description = "Operaciones sobre comentarios")
public class CommentsController {

    private CommentRepository commentRepository;

    public CommentsController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Operation(summary = "Obtener todos los comentarios")
    @GetMapping
    public List<Comment> findAll(){
        return commentRepository.findAll();
    }

    @Operation(summary = "Obtener un comentario por ID")
    @GetMapping("/{id}")
    public Comment findOne(
            @Parameter(description = "ID del comentario")
            @PathVariable @Valid String id){
        return commentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Comment " + id + " not found"));
    }
}
