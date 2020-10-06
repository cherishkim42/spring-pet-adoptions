package com.launchacademy.petSurrender.controllers;

import com.launchacademy.petSurrender.models.Pet;
import com.launchacademy.petSurrender.repositories.PetRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class PetsRestController {

  private PetRepository petRepository;

  @Autowired
  public PetsRestController(PetRepository petRepository) { this.petRepository = petRepository; }

  @GetMapping("/pets")
  public Iterable<Pet> displayPets() { return petRepository.findAll();}

  @NoArgsConstructor
  private class PetNotFoundException extends RuntimeException {
    @ResponseBody
    @ExceptionHandler(PetNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String petNotFoundHandler(PetNotFoundException ex) { return ex.getMessage(); }
  }

}
