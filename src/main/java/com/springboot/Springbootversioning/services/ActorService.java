package com.springboot.Springbootversioning.services;

import com.springboot.Springbootversioning.entities.ActorEntity;
import com.springboot.Springbootversioning.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActorService {

    private final ActorRepository actorRepository;

    @Autowired
    public ActorService(ActorRepository repository){
        this.actorRepository = repository;
    }

    public ActorEntity getActorData(Long id){
        return  actorRepository.getById(id);
    }

}
