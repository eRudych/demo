package com.st.service;

import com.st.entity.Post;

import java.util.List;


public interface PostService {

    Post create(Post post);

    Post update(Post post);

    Post get(Long id);

    List<Post> getAll();

    boolean remove(Long id);
}
