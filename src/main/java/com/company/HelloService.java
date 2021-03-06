package com.company;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.Optional;

public class HelloService {
    static final String FALLBACK_NAME = "world";
    static final Lang FALLBACK_LANG = new Lang(1L, "Hello", "en");
    private final Logger logger = LoggerFactory.getLogger(HelloService.class);

    private LangRepository repository;

    HelloService() {
        this(new LangRepository());
    }

    HelloService(LangRepository repository) {
        this.repository = repository;
    }

    String prepareGreeting(String name, String lang) {
        Long langId;
        try {
            langId = Optional.ofNullable(lang).map(Long::valueOf).orElse(FALLBACK_LANG.getId());
        } catch (NumberFormatException e) {
            logger.warn("Non-numeric language id used: " + lang);
            langId = FALLBACK_LANG.getId();
        }
        String welcomeMsg = repository.findById(langId).orElse(FALLBACK_LANG).getWelcomeMsg();
        return welcomeMsg + " " + Optional.ofNullable(name).orElse(FALLBACK_NAME) + "!";
    }
}
