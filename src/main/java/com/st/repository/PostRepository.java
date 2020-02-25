package com.st.repository;

import com.st.entity.Post;

import java.util.List;

public interface PostRepository {
    Post create(Post post);

    boolean update(Post post);

    Post get(Long id);

    List<Post> getAll();

    boolean remove(Long id);
}
