package com.springboot.Springbootversioning.repository;

import com.springboot.Springbootversioning.entities.ActorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<ActorEntity, Long> {
}
