package com.launchacademy.petSurrender.controllers;

import com.launchacademy.petSurrender.models.PetType;
import com.launchacademy.petSurrender.repositories.PetTypeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class PetTypeRestController {

  private PetTypeRepository petTypeRepository;

  public PetTypeRestController(PetTypeRepository petTypeRepository) { this.petTypeRepository =
      petTypeRepository; }

  @GetMapping("/pet_types")
  public Iterable<PetType> displayPetTypes() { return petTypeRepository.findAll(); }
}
