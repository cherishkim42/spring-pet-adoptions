package com.launchacademy.petSurrender.seeders;

import com.launchacademy.petSurrender.models.PetType;
import com.launchacademy.petSurrender.repositories.PetTypeRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PetTypesSeeder {
  private PetTypeRepository petTypeRepository;

  @Autowired
  public void setPetTypeRepository(PetTypeRepository petTypeRepository) {
    this.petTypeRepository = petTypeRepository;
  }

  public void seed() {
    List<PetType> petTypeList = new ArrayList();

    PetType petTypeOne = new PetType();
    petTypeOne.setType("dog");
    petTypeOne.setDescription("technically the same species as the wolf");
    petTypeOne.setImageUrl("https://i.insider.com/5df126b679d7570ad2044f3e?width=900&format=jpeg&auto=webp");
    petTypeList.add(petTypeOne);

    PetType petTypeTwo = new PetType();
    petTypeTwo.setType("cat");
    petTypeTwo.setDescription("cute and sassy but also a fierce predator");
    petTypeTwo.setImageUrl("https://icatcare.org/app/uploads/2018/07/Thinking-of-getting-a-cat"
        + ".png");
    petTypeList.add(petTypeTwo);

    if (petTypeRepository.count() == 0) {
      for (PetType petType : petTypeList) {
        petTypeRepository.save(petType);
      }
    }
  }
}
