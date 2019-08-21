package com.alphamplyer.website.main.controllers;

import com.alphamplyer.website.main.beans.News;
import com.alphamplyer.website.main.proxies.MicroserviceNewsProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.List;

@Controller
public class HomeController {

    MicroserviceNewsProxy microserviceNewsProxy;

    @Autowired
    public void setMicroserviceNewsProxy(MicroserviceNewsProxy microserviceNewsProxy) {
        this.microserviceNewsProxy = microserviceNewsProxy;
    }

    @RequestMapping(value = {"/", "/index", "/home"})
    public String index (Model model) {

        List<News> newsList = null;

        try {
            newsList = microserviceNewsProxy.getNews(0, 1, true);
        } catch (HttpStatusCodeException e) {
            return "error";
        }

        News news = null;

        if (newsList != null && !newsList.isEmpty())
             news = newsList.get(0);

        if (news == null)
            return "error";

        model.addAttribute("lastNews", news);

        return "index";
    }
}
