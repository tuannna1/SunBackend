package me.sunrise.api;

import me.sunrise.entity.Comment;
import me.sunrise.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/seller/comment/new")
    public ResponseEntity<?> add(@RequestBody Comment comment) {
        try {
            Comment returnedComment = commentService.save(comment);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
