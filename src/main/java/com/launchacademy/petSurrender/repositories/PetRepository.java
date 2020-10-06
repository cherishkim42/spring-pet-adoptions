package com.launchacademy.petSurrender.repositories;

import com.launchacademy.petSurrender.models.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Integer> {

}
