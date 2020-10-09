package com.launchacademy.petSurrender.repositories;

import com.launchacademy.petSurrender.models.PetType;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Integer> {

  public List<PetType> findAll();

  Optional<PetType> findByType(String animalType);
//  doesn't know if we're getting a single object from the db.
//  so it returns you an optional, and then if the obj is in there,
//  you can use a .isPresent() function, and then use .get() to get it.
}
