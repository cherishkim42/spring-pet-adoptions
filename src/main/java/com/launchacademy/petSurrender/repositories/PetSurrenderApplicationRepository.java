package com.launchacademy.petSurrender.repositories;

import com.launchacademy.petSurrender.models.PetSurrenderApplication;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface PetSurrenderApplicationRepository extends CrudRepository<PetSurrenderApplication
    , Integer> {

  public List<PetSurrenderApplication> findAll();
}
