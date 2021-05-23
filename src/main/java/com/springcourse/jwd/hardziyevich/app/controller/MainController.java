package com.springcourse.jwd.hardziyevich.app.controller;

import com.springcourse.jwd.hardziyevich.app.domain.Message;
import com.springcourse.jwd.hardziyevich.app.domain.User;
import com.springcourse.jwd.hardziyevich.app.repos.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MainController {

    private final MessageRepository messageRepository;

    @Autowired
    public MainController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping("/main")
    public String main(@ModelAttribute("message") Message message,
                       Model model) {
        final Iterable<Message> all = messageRepository.findAll();
        model.addAttribute("messages", all);
        return "main";
    }

    @GetMapping()
    public String greeting(@RequestParam(name = "name", required = false,
            defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @PostMapping("/main")
    public String add(@AuthenticationPrincipal User user,
                      @ModelAttribute("message") @Valid Message message,
                      BindingResult bindingResult,
                      Model model) {
        if (bindingResult.hasErrors()) {
            return "main";
        }
        message.setAuthor(user);
        messageRepository.save(message);
        final Iterable<Message> messages = messageRepository.findAll();
        model.addAttribute("messages", messages);
        return "main";
    }

    @PostMapping("main/filter")
    public String filter(@RequestParam String filter,
                         @ModelAttribute("message") Message message,
                         Model model) {
        if (filter.isEmpty()) {
            final List<Message> messages = (List<Message>) messageRepository.findAll();
            model.addAttribute("messages", messages);
        } else {
            final List<Message> messages = messageRepository.findByTag(filter);
            model.addAttribute("messages", messages);
        }
        return "main";
    }

}
