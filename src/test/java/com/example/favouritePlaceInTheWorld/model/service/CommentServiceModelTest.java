package com.example.favouritePlaceInTheWorld.model.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CommentServiceModelTest {

    @Test
    void setPlaceId() {
        CommentServiceModel commentServiceModel = new CommentServiceModel();
        commentServiceModel.setPlaceId(1L);
        Assertions.assertEquals(commentServiceModel.getPlaceId(), 1);
    }

    @Test
    void setTextContent() {
        CommentServiceModel commentServiceModel = new CommentServiceModel();
        commentServiceModel.setTextContent("test");
        Assertions.assertEquals(commentServiceModel.getTextContent(), "test");
    }

    @Test
    void setAuthor() {
        CommentServiceModel commentServiceModel = new CommentServiceModel();
        commentServiceModel.setAuthor("Stoian");
        Assertions.assertNull(commentServiceModel.getTextContent());
    }

    @Test
    void getAuthor() {
        CommentServiceModel commentServiceModel = new CommentServiceModel();
        commentServiceModel.setAuthor("Stoian");
        Assertions.assertEquals(commentServiceModel.getAuthor(), "Stoian");
    }


}
