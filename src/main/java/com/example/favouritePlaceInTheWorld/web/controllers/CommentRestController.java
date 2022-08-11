package com.example.favouritePlaceInTheWorld.web.controllers;

import com.example.favouritePlaceInTheWorld.model.binding.NewCommentBindingModel;
import com.example.favouritePlaceInTheWorld.model.binding.UserRegisterBindingModel;
import com.example.favouritePlaceInTheWorld.model.service.CommentServiceModel;
import com.example.favouritePlaceInTheWorld.model.validation.ApiError;
import com.example.favouritePlaceInTheWorld.model.view.CommentView;
import com.example.favouritePlaceInTheWorld.service.CommentService;
import com.example.favouritePlaceInTheWorld.web.sla.TrackLatency;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
public class CommentRestController {

    private final CommentService commentService;
    private final ModelMapper modelMapper;

    public CommentRestController(CommentService commentService, ModelMapper modelMapper) {
        this.commentService = commentService;
        this.modelMapper = modelMapper;
    }

    @TrackLatency(latency = "remote_operation")
    @GetMapping("/api/{placeId}/comments")
    public ResponseEntity<List<CommentView>> getComments(
            @PathVariable Long placeId,
            Principal principal) {


        return ResponseEntity.ok(
                commentService.getComments(placeId));
    }

    @PostMapping("/api/{placeId}/comments")
    public ResponseEntity<CommentView> newComment(
            @AuthenticationPrincipal UserDetails principal,
            @PathVariable Long placeId,
            @RequestBody @Valid NewCommentBindingModel newCommentBindingModel) {

        CommentServiceModel commentServiceModel = modelMapper
                .map(newCommentBindingModel, CommentServiceModel.class);

        commentServiceModel.setPlaceId(placeId);
        commentServiceModel.setAuthor(principal.getUsername());

        CommentView newComment =
                commentService.createComment(commentServiceModel);


        URI locationOfNewComment =
                URI.create(String.format("/api/%s/comments/%s",
                        placeId, newComment.getId()));

        return ResponseEntity
                .created(locationOfNewComment)
                .body(newComment);

    }

    //custom error response who send to our client.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> onValidationFailure(MethodArgumentNotValidException exc) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);

        exc.getFieldErrors().forEach(fieldError -> {
            apiError.addFieldWithError(fieldError.getField());
        });

        return ResponseEntity.badRequest().body(apiError);
    }


}
