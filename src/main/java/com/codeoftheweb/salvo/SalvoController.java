package com.codeoftheweb.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SalvoController {
    @Autowired
    private GameRepository gameRepository;

    @RequestMapping("/games")
    public List<Object> getAll() {
        return gameRepository.findAll()
                .stream()
                .map(x -> x.makeGameDTO())
                .collect(Collectors.toList());
    }

}
