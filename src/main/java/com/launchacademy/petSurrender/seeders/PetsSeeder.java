package com.launchacademy.petSurrender.seeders;

import com.launchacademy.petSurrender.models.PetSurrender;
import com.launchacademy.petSurrender.repositories.PetRepository;
import com.launchacademy.petSurrender.repositories.PetTypeRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class PetsSeeder {
  private PetRepository petRepository;
  private PetTypeRepository petTypeRepository;

  @Autowired
  public void setPetRepository(PetRepository petRepository, PetTypeRepository petTypeRepository) {
    this.petRepository = petRepository;
    this.petTypeRepository = petTypeRepository;
  }

  public void seed() {
    List<PetSurrender> petList = new ArrayList();

    PetSurrender petOne = new PetSurrender();
    petOne.setName("Fido");
    petOne.setImgUrl("http://www.google.com");
    petOne.setAge(2);
    petOne.setVaccinationStatus(true);
    petOne.setAdoptionStory("found in a cornfield");
    petOne.setAdoptionStatus("looking for a home");
    petOne.setPetType(petTypeRepository.findByType("dog").get());
    petList.add(petOne);

    PetSurrender petTwo = new PetSurrender();
    petTwo.setName("Mr Meow");
    petTwo.setImgUrl("http://www.google.com");
    petTwo.setAge(1);
    petTwo.setVaccinationStatus(true);
    petTwo.setAdoptionStory("my cat had kittens");
    petTwo.setAdoptionStatus("top tier mouser");
    petTwo.setPetType(petTypeRepository.findByType("cat").get());
    petList.add(petTwo);

    if (petRepository.count() == 0) {
      for (PetSurrender pet : petList) {
        petRepository.save(pet);
      }
    }
  }

}
