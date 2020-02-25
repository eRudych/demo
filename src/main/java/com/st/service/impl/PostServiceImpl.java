package com.st.service.impl;

import com.st.entity.Post;
import com.st.repository.PostRepository;
import com.st.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepository repository;

    @Override
    @Transactional
    public Post create(Post post) {
        Date today = new Date();
        post.setDate(new java.sql.Timestamp(today.getTime()));
        log.info("create post " + post.toString());
        return repository.create(post);
    }

    @Override
    @Transactional
    public Post update(Post post) {
        if (repository.update(post))
            return repository.get(post.getId());
        return null;
    }

    @Override
    public Post get(Long id) {
        return repository.get(id);
    }

    @Override
    public List<Post> getAll() {
        return repository.getAll();
    }

    @Override
    @Transactional
    public boolean remove(Long id) {
        log.info("remove post " + id);
        return repository.remove(id);
    }
}
