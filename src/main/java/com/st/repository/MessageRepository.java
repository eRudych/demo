package com.st.repository;

import com.st.entity.Message;

import java.util.List;

public interface MessageRepository {

    boolean create(Message message);

    boolean remove(Long id);

    List<Message> getAll();
}
