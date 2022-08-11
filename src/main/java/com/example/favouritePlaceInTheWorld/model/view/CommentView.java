package com.example.favouritePlaceInTheWorld.model.view;

import java.time.LocalDateTime;

public class CommentView {

    private Long id;
    private String textContent;
    private String author;
    private LocalDateTime created;
    private boolean approved;
    private boolean canDelete;

    public CommentView() {
    }

    public Long getId() {
        return id;
    }

    public CommentView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTextContent() {
        return textContent;
    }

    public CommentView setTextContent(String textContent) {
        this.textContent = textContent;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public CommentView setAuthor(String author) {
        this.author = author;
        return this;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public CommentView setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }

    public boolean isApproved() {
        return approved;
    }

    public CommentView setApproved(boolean approved) {
        this.approved = approved;
        return this;
    }

    public boolean isCanDelete() {
        return canDelete;
    }

    public CommentView setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
        return this;
    }
}
