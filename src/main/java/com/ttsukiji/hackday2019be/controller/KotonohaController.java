package com.ttsukiji.hackday2019be.controller;

import com.ttsukiji.hackday2019be.domain.CategorizeResult;
import com.ttsukiji.hackday2019be.service.CategorizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
public class KotonohaController {

    private CategorizeService dictionaryCategorizer;
    private CategorizeService modelCategorizer;

    @Autowired
    public KotonohaController(@Qualifier("dictionaryCategorizeService") final CategorizeService dictionaryCategorizer,
                              @Qualifier("modelCategorizeService") final CategorizeService modelCategorizer) {
        this.dictionaryCategorizer = dictionaryCategorizer;
        this.modelCategorizer = modelCategorizer;
    }

    @CrossOrigin
    @GetMapping
    @RequestMapping("/")
    public String hello() {
        return "Hello World!";
    }

    @CrossOrigin
    @GetMapping
    @RequestMapping("/categorize")
    public CategorizeResult categorize(@RequestParam(name = "query") final String query) {
        return dictionaryCategorizer.categorize(query);
    }

    @CrossOrigin
    @GetMapping
    @RequestMapping("/infer")
    public CategorizeResult infer(@RequestParam(name = "query") final String query) {
        return modelCategorizer.categorize(query);
    }
}
