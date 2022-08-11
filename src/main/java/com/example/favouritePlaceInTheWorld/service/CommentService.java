package com.example.favouritePlaceInTheWorld.service;

import com.example.favouritePlaceInTheWorld.model.service.CommentServiceModel;
import com.example.favouritePlaceInTheWorld.model.view.CommentView;

import java.util.List;

public interface CommentService {

    List<CommentView> getComments(Long placeId);

    CommentView createComment(CommentServiceModel commentServiceModel);

    void deleteById(Long id);
}
