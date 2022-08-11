package com.example.favouritePlaceInTheWorld.model.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
public class CommentEntityTest {

    @Test
    void isApprove() {
        CommentEntity comment = new CommentEntity();
        comment.setApproved(true);
        Assertions.assertTrue(comment.isApproved());
    }

    @Test
    void created() {
        CommentEntity comment = new CommentEntity();
        LocalDateTime time = LocalDateTime.of(2007,8,31,12,11);
        comment.setCreated(time);
        Assertions.assertEquals(comment.getCreated(), time);
    }

    @Test
    void textContent() {
        CommentEntity comment = new CommentEntity();
        comment.setTextContent("Stoian");
        Assertions.assertEquals(comment.getTextContent(), "Stoian");
    }

    @Test
    void setAuthor() {
        CommentEntity comment = new CommentEntity();
        UserEntity author = new UserEntity();
        author.setUsername("Stoian");
        comment.setAuthor(author);
        Assertions.assertEquals(comment.getAuthor().getUsername(), "Stoian");
    }

    @Test
    void setPlace() {
        CommentEntity comment = new CommentEntity();
        PlaceEntity place = new PlaceEntity();
        place.setName("Sofia");
        comment.setPlace(place);
        Assertions.assertEquals(comment.getPlace().getName(), "Sofia");
    }
}
