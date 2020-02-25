package com.st.service;

import com.st.entity.Message;

import java.util.List;


public interface MessageService {

    boolean createMessage(Message message);

    boolean removeMessage(Long id);

    List<Message> getAllMessages();
}

