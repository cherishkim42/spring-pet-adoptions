package com.launchacademy.petSurrender.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Columns;

@Entity
@Table(name="pet_surrender")
@NoArgsConstructor
@Getter
@Setter
public class Pet {
  @Id
  @SequenceGenerator(name="pet_generator", sequenceName = "pet_surrender_id_seq", allocationSize=1)
  @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="pet_generator")
  @Column(name="id", nullable=false, unique=true)
  private Integer id;

  @Column(name="type", nullable=false)
  private String type;

  @Column(name="description", nullable=true)
  private String description;
}
