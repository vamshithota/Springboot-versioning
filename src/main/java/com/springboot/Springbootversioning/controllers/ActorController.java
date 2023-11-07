package com.springboot.Springbootversioning.controllers;

import com.springboot.Springbootversioning.dto.ActorDTO;
import com.springboot.Springbootversioning.entities.ActorEntity;
import com.springboot.Springbootversioning.services.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/actor")
public class ActorController {
    @Autowired
    ActorService actorService;

    @GetMapping("/getActor/{id}")
    public ActorDTO getActorById(@PathVariable Long id){
        ActorEntity data =   actorService.getActorData(id);
        ActorDTO actorDTO = new ActorDTO(data.getId(), data.getFirst_name(), data.getLast_name(), data.getLast_update());
        return  actorDTO;
    }

    @GetMapping("/getActors")
    public List<ActorEntity> getActors(){
        return  actorService.getActors();
    }
}
