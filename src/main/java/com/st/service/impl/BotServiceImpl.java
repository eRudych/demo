package com.st.service.impl;

import com.st.bot.executor.DispatcherBot;
import com.st.entity.Post;
import com.st.repository.BotRepository;
import com.st.service.BotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class BotServiceImpl implements BotService {

    private final BotRepository repository;
    private final DispatcherBot dispatcher;

    @Override
    public boolean sendPost(Post post, Long idDb) {
        try {
            Integer messageId = (dispatcher.sendMessageChannel(generateMessage((post))));
            repository.create(messageId, idDb);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean editPost(Post post) {
        try {
            dispatcher.editMessageChannel(generateMessage(post), repository.get(post.getId()));
            return true;
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean removePost(Long idDb) {
        try {
            dispatcher.removeMessageChannel(repository.get(idDb));
            repository.remove(idDb);
            return true;
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return false;
    }

    private String generateMessage(Post post) {
        return post.getTitle() + "\n" + post.getText();
    }
}