package com.launchacademy.petSurrender.seeders;

import com.launchacademy.petSurrender.models.Pet;
import com.launchacademy.petSurrender.repositories.PetRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PetsSeeder {
  private PetRepository petRepository;

  @Autowired
  public void setPetRepository(PetRepository petRepository) {
    this.petRepository = petRepository;
  }

  public void seed() {
    List<Pet> petList = new ArrayList();

    Pet petOne = new Pet();
    petOne.setType("dog");
    petOne.setDescription("cute as heck");
    petList.add(petOne);

    Pet petTwo = new Pet();
    petTwo.setType("cat");
    petTwo.setDescription("sassy lil tux");
    petList.add(petTwo);

    if (petRepository.count() == 0) {
      for (Pet pet : petList) {
        petRepository.save(pet);
      }
    }
  }

}
