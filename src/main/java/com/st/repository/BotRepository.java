package com.st.repository;

public interface BotRepository {

    boolean create(Integer id, Long postId);

    boolean remove(Long postId);

    Integer get(Long postId);
    
}
