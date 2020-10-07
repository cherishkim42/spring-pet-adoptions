package com.launchacademy.petSurrender.seeders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class MainSeeder implements CommandLineRunner {
  @Autowired
  PetsSeeder petsSeeder;

  @Override
  public void run(String... args) throws Exception {
    petsSeeder.seed();
  }
}
