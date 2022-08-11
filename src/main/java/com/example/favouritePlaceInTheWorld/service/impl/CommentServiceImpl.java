package com.example.favouritePlaceInTheWorld.service.impl;

import com.example.favouritePlaceInTheWorld.model.entity.CommentEntity;
import com.example.favouritePlaceInTheWorld.model.service.CommentServiceModel;
import com.example.favouritePlaceInTheWorld.model.view.CommentView;
import com.example.favouritePlaceInTheWorld.repository.CommentRepository;
import com.example.favouritePlaceInTheWorld.repository.PlaceRepository;
import com.example.favouritePlaceInTheWorld.repository.UserRepository;
import com.example.favouritePlaceInTheWorld.service.CommentService;
import com.example.favouritePlaceInTheWorld.service.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final PlaceRepository placeRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public CommentServiceImpl(PlaceRepository placeRepository, UserRepository userRepository, CommentRepository commentRepository) {
        this.placeRepository = placeRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    @Transactional
    @Override
    public List<CommentView> getComments(Long placeId) {
        var placeOpt = placeRepository
                .findById(placeId);

        if (placeOpt.isEmpty()) {
            throw new ObjectNotFoundException("Place with id " + placeId + " was not found!");
        }

        return placeOpt
                .get()
                .getComments()
                .stream()
                .map(this::masAsComment)
                .collect(Collectors.toList());

    }

    @Override
    public CommentView createComment(CommentServiceModel commentServiceModel) {
        //TODO: its not created, does it work???? test?;
        var place = placeRepository
                .findById(commentServiceModel.getPlaceId())
                .orElseThrow(() -> new ObjectNotFoundException(
                        "Place with id " + commentServiceModel.getPlaceId() + " not found!"));

        var author = userRepository
                .findByUsername(commentServiceModel.getAuthor())
                .orElseThrow(() -> new ObjectNotFoundException(
                        "User with username " + commentServiceModel.getAuthor() + " not found!"));


        CommentEntity newComment = new CommentEntity();

        newComment.setApproved(true)
                .setTextContent(commentServiceModel.getTextContent())
                .setCreated(LocalDateTime.now())
                .setPlace(place)
                .setAuthor(author);

        CommentEntity saveComment = commentRepository.save(newComment);



        return masAsComment(saveComment);

    }

    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }


    private CommentView masAsComment(CommentEntity commentEntity) {
        CommentView commentView = new CommentView();

        commentView
                .setId(commentEntity.getId())
                .setApproved(true)
                .setCanDelete(true)
                .setCreated(commentEntity.getCreated())
                .setTextContent(commentEntity.getTextContent())
                .setAuthor(commentEntity.getAuthor().getFullName());

        return commentView;
    }
}
