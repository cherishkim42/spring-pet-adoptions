package com.launchacademy.petSurrender.repositories;

import com.launchacademy.petSurrender.models.PetSurrender;
import com.launchacademy.petSurrender.models.PetType;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<PetSurrender, Integer> {

  public List<PetSurrender> findAll();

  List<PetSurrender> findByPetTypeType(String pet_type);
}
