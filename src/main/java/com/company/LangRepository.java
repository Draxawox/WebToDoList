package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LangRepository {

    private List<Lang> languages;

    LangRepository() {
        languages = new ArrayList<>();
        languages.add(new Lang(1L, "Hello", "en"));
        languages.add(new Lang(1L, "Siema", "pl"));
    }

    Optional<Lang> findById(Long id) {
        return languages.stream()
                .filter(l -> l.getId().equals(id))
                .findFirst();
    }


}
