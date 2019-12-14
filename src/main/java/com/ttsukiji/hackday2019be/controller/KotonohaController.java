package com.ttsukiji.hackday2019be.controller;

import com.ttsukiji.hackday2019be.domain.KotonohaResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/categorize")
public class KotonohaController {
    @GetMapping
    public KotonohaResponse categorize() {
        final HashMap<String, Integer> detail = new HashMap<>();
        detail.put("猫", 2);
        detail.put("かわいい", 1);
        detail.put("好き", 1);

        return new KotonohaResponse(1, detail);
    }
}
