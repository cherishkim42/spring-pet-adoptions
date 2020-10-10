package com.launchacademy.petSurrender.controllers;

import com.launchacademy.petSurrender.models.PetSurrender;
import com.launchacademy.petSurrender.models.PetType;
import com.launchacademy.petSurrender.repositories.PetRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
  public Iterable<PetSurrender> displayPets() { return petRepository.findAll();}

  @GetMapping("/pets/{pet_type}")
  public Iterable<PetSurrender> displayPetTypes(@PathVariable String pet_type) { return petRepository.findByPetTypeType(pet_type);}

  @GetMapping("/pets/{pet_type}/{id}")
  public ResponseEntity showOnePet(@PathVariable String pet_type, @PathVariable Integer id) {
    if (petRepository.findById(id).isEmpty()) {
      return new ResponseEntity(HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity(petRepository.findById(id).get(), HttpStatus.OK);
    }
  }

  @NoArgsConstructor
  private class PetNotFoundException extends RuntimeException {
    @ResponseBody
    @ExceptionHandler(PetNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String petNotFoundHandler(PetNotFoundException ex) { return ex.getMessage(); }
  }

}
