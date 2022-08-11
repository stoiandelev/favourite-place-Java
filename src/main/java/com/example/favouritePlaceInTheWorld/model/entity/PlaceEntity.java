package com.example.favouritePlaceInTheWorld.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "places")
public class PlaceEntity extends BaseEntity{

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, name = "image_URL")
    private String imageURL;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column
    private LocalDateTime created;


    @ManyToOne
    private UserEntity userCreate;

    @OneToMany(mappedBy = "place", fetch = FetchType.EAGER)
    private List<CommentEntity> comments;



    public PlaceEntity() {
    }

    public String getName() {
        return name;
    }

    public PlaceEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getImageURL() {
        return imageURL;
    }

    public PlaceEntity setImageURL(String imageURL) {
        this.imageURL = imageURL;
        return this;
    }


    public String getDescription() {
        return description;
    }

    public PlaceEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public PlaceEntity setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }


    public UserEntity getUserCreate() {
        return userCreate;
    }

    public PlaceEntity setUserCreate(UserEntity userCreate) {
        this.userCreate = userCreate;
        return this;
    }

    public List<CommentEntity> getComments() {
        return comments;
    }

    public PlaceEntity setComments(List<CommentEntity> comments) {
        this.comments = comments;
        return this;
    }
}
