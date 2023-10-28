package com.example.bootstudy.controller;

import com.example.bootstudy.data.entity.ListenerEntity;
import com.example.bootstudy.service.ListenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/listener")
public class ListenerController {

    private ListenerService listenerService;

    @Autowired
    public ListenerController(ListenerService listenerService){
        this.listenerService = listenerService;
    }

    @GetMapping
    public String getListenr(Long id){
        listenerService.getEntity(id);
        return "OK";
    }

    @PostMapping
    public void saveListener(String name){
        ListenerEntity listenerEntity = new ListenerEntity();
        listenerEntity.setName(name);

        listenerService.saveEntity(listenerEntity);
    }

    @PutMapping
    public void updateListener(Long id, String name){
        ListenerEntity listenerEntity = new ListenerEntity();
        listenerEntity.setId(id);
        listenerEntity.setName(name);

        listenerService.updateEntity(listenerEntity);
    }

    @DeleteMapping
    public void deleteListener(Long id){
        ListenerEntity listenerEntity = listenerService.getEntity(id);

        listenerService.removeEntity(listenerEntity);
    }

}
