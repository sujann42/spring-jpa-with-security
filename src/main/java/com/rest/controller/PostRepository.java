package com.rest.controller;

import com.rest.model.Post;
import com.rest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
