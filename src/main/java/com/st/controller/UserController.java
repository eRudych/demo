package com.st.controller;

import com.st.entity.User;
import com.st.service.OrderService;
import com.st.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("st-line/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService service;
    private final OrderService orderService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public UserDetails create(@RequestBody User user) {
        log.info("create " + user.toString());
        return service.create(user);
    }

    @GetMapping("/{id}")
    public UserDetails getById(@PathVariable("id") long id) {
        return service.getById(id);
    }

    @GetMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public UserDetails getByNick(@RequestBody String nick) {
        return service.getByNick(nick);
    }

    @PostMapping(value = "/searchIdByName",consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Long searchUserIdByUserName(@RequestBody String nick){
        if(nick.endsWith("\""))
            nick = nick.replaceAll("\"", "");
        log.info("searchUserIdByUserNick "+nick);
        Long id =service.searchUserIdByUserNick(nick);
        orderService.createOrderForUser(id);
        log.info(id.toString());
        return id;
    }
}
