import React, { useEffect, useState } from 'react'
import { Link } from "react-router-dom"

const PetTypeContainer = (props) => {
  const [adoptablePets, setAdoptablePets] = useState([])

  let petType = props.match.params.pet_type

  useEffect(() => {
    fetch(`/api/v1/pets/${petType}`)
      .then(result => result.json())
      .then(pets => {
        setAdoptablePets(pets);
      })
  }, [petType]);

  let adoptablePetElements = adoptablePets.map((pet, index) => {
    return(
      <div className="pet-type" key={index}>
        <Link to={`/pets/${petType}/${pet.id}`}>
          <h4>Name: {pet.name}</h4>
        </Link>
        <img className="pet-image" src={pet.img_url} />
        <p>Age: {pet.age}</p>
        <p>Vaccinated: {pet.vaccination_status === false ? "No" : "Yes"}</p>
      </div>
    )
  })

  return (
    <div>
      {adoptablePetElements}
    </div>
  );
}

export default PetTypeContainer