package com.st.controller;

import com.st.entity.Message;
import com.st.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/st-line")
@RequiredArgsConstructor
@Slf4j
public class MessageController {

    private final MessageService service;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public boolean sendMessage(@RequestBody Message message){
        log.info("send message"+ message.toString());
        return service.createMessage(message);
    }

    @DeleteMapping("/{id}")
    public boolean removeMessage(@PathVariable("id") long id){
        return service.removeMessage(id);
    }

    @GetMapping
    public List getAllMessages(){
        return service.getAllMessages();
    }
}