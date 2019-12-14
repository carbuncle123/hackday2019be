package com.ttsukiji.hackday2019be.controller;

import com.ttsukiji.hackday2019be.repository.DictionaryEditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dict")
public class DictionaryEditController {

    @Autowired
    @Qualifier("mySqlEditRepository")
    private DictionaryEditRepository mysqlRepository;

    @GetMapping
    public String get(final Model model) {
        final Map<Integer, List<String>> catWordMap = new HashMap<>();
        for (int i = 1; i < 11; i++) {
            catWordMap.put(i, mysqlRepository.getWords(i));
        }
        model.addAttribute("catWordMap", catWordMap);
        return "index";
    }

    @PostMapping(path = "update")
    public String update(@RequestParam(name = "word") final String word,
                         @RequestParam(name = "category") final int category_id) {
        mysqlRepository.update(word, category_id);
        return "redirect:/dict";
    }
}
