package com.st.bot.executor;

import com.st.bot.Bot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@Slf4j
public class DispatcherBot extends Bot {

    @Override
    public Integer sendMessageChannel(String message) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage().setChatId(getChannelChatId()).setText(message);
        return execute(sendMessage).getMessageId();
    }

    @Override
    public void editMessageChannel(String message, Integer messageId) throws TelegramApiException {
        EditMessageText editMessageText = new EditMessageText()
                .setChatId(getChannelChatId())
                .setMessageId(Math.toIntExact(messageId))
                .setText(message);
        execute(editMessageText);
    }

    @Override
    public void removeMessageChannel(Integer messageId) throws TelegramApiException {
        DeleteMessage deleteMessage = new DeleteMessage()
                .setChatId(getChannelChatId())
                .setMessageId(Math.toIntExact(messageId));
        execute(deleteMessage);
    }
}
