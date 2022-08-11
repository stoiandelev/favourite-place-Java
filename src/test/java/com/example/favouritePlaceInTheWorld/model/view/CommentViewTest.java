package com.example.favouritePlaceInTheWorld.model.view;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
public class CommentViewTest {

    @Test
    void setId() {
        CommentView commentView = new CommentView();
        commentView.setId(1L);
        Assertions.assertEquals(commentView.getId(), 1);
    }

    @Test
    void setTextContent() {
        CommentView commentView = new CommentView();
        commentView.setTextContent("text");
        Assertions.assertEquals(commentView.getTextContent(), "text");
    }

    @Test
    void setAuthor() {
        CommentView commentView = new CommentView();
        commentView.setAuthor("Author");
        Assertions.assertEquals(commentView.getAuthor(), "Author");
    }

    @Test
    void setCreated() {
        CommentView commentView = new CommentView();
        LocalDateTime time = LocalDateTime.of(2007,8,31,12,11);
        commentView.setCreated(time);
        Assertions.assertEquals(commentView.getCreated(), time);
    }

    @Test
    void isApprove() {
        CommentView commentView = new CommentView();
        commentView.setApproved(true);
        Assertions.assertTrue(commentView.isApproved());
    }

    @Test
    void setCanDelete() {
        CommentView commentView = new CommentView();
        commentView.setCanDelete(true);
        Assertions.assertTrue(commentView.isCanDelete());
    }
}
