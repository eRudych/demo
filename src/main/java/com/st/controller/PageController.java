package com.st.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping({"/{pageName}"})
@Slf4j
public class PageController {

    private Map<String,String> mapping;

    @PostConstruct
    private void init() {
        mapping = new HashMap<>();

        mapping.put("language", "index");

        mapping.put("store", "v3/store");
        mapping.put("news", "v3/posts");
        mapping.put("shop", "v3/shop");

        mapping.put("product", "v3/product/product");

        mapping.put("store1", "v3/store1");
        mapping.put("news1", "post/posts");

        mapping.put("impl/header", "v3/impl/header");

        mapping.put("main", "main");
        mapping.put("about", "about");
        mapping.put("products", "main/products");
//        mapping.put("news", "post/posts");

        mapping.put("/header", "import/header");
        mapping.put("/footer", "import/footer");
        mapping.put("/appStyle", "import/appStyle");
        mapping.put("/messageForm", "import/messageForm");

        mapping.put("createPost", "post/create");
        mapping.put("test", "post/test");
        mapping.put("editPost", "post/edit");
        mapping.put("post", "post/get");

        mapping.put("send", "message/sendMessage");
        mapping.put("messages", "message/list");

        mapping.put("login","user/access");
        mapping.put("logout","user/access");

        mapping.put("createProduct","product/create");
        mapping.put("getProducts","product/list");
        mapping.put("editProduct","product/edit");

        mapping.put("createCategory","product/category/create");
        mapping.put("getCategories","product/category/list");
        mapping.put("createMark","product/mark/create");
        mapping.put("getMarks","product/mark/list");
    }

    @GetMapping
    public String getPage(@PathVariable("pageName") String pageName){
        log.info("return page "+pageName);
        return mapping.getOrDefault(pageName,"error");
    }

}
