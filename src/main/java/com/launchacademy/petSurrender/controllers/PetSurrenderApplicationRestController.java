package com.launchacademy.petSurrender.controllers;

import com.launchacademy.petSurrender.models.PetSurrenderApplication;
import com.launchacademy.petSurrender.models.PetType;
import com.launchacademy.petSurrender.repositories.PetSurrenderApplicationRepository;
import com.launchacademy.petSurrender.repositories.PetTypeRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class PetSurrenderApplicationRestController {

  private PetSurrenderApplicationRepository petSurrenderApplicationRepository;
  private PetTypeRepository petTypeRepository;

  @Autowired
  public PetSurrenderApplicationRestController(
      PetSurrenderApplicationRepository petSurrenderApplicationRepository,
      PetTypeRepository petTypeRepository) {
    this.petSurrenderApplicationRepository = petSurrenderApplicationRepository;
    this.petTypeRepository = petTypeRepository;
  }

  @PostMapping("/adoptions/new/{type_id}")
  public PetSurrenderApplication petSurrenderApplication(@RequestBody PetSurrenderApplication petSurrenderApplication, @PathVariable Integer type_id) {
    Optional<PetType> petType = petTypeRepository.findById(type_id);

    petSurrenderApplication.setPetType(petType.get());

    petSurrenderApplication.setApplicationStatus(false);

    return petSurrenderApplicationRepository.save(petSurrenderApplication);
  }
}
