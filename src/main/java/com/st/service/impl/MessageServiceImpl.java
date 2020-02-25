package com.st.service.impl;

import com.st.entity.Message;
import com.st.repository.MessageRepository;
import com.st.service.MessageService;
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
public class MessageServiceImpl implements MessageService {

    private final MessageRepository repository;

    @Override
    @Transactional
    public boolean createMessage(Message message) {
        Date today = new Date();
        message.setDate(new java.sql.Timestamp(today.getTime()));
        log.info("create message " + message.toString());
        return repository.create(message);
    }

    @Override
    @Transactional
    public boolean removeMessage(Long id) {
        return repository.remove(id);
    }

    @Override
    public List<Message> getAllMessages() {
        return repository.getAll();
    }

}
