package com.example.favouritePlaceInTheWorld.repository;

import com.example.favouritePlaceInTheWorld.model.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {


}
