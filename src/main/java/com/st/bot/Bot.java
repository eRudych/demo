package com.st.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public abstract class Bot extends TelegramLongPollingBot {

    @Value("${bot.channelChatId}")
    private String channelChatId ;

    @Value("${bot.token}")
    private String token;

    @Value("${bot.username}")
    private String username;


    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    protected String getChannelChatId(){
        return channelChatId;
    }

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage sendMessage= new SendMessage();
        sendMessage.setText("hi");
        sendMessage.setChatId(channelChatId);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public abstract Integer sendMessageChannel(String message) throws TelegramApiException;

    public abstract void editMessageChannel(String message, Integer id) throws TelegramApiException;

    public abstract void removeMessageChannel(Integer id) throws TelegramApiException;
}
