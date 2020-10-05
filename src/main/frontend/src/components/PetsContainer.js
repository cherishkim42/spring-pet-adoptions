import React, { useState, useEffect } from 'react';
import _ from "lodash"
import { Link } from "react-router-dom"

const PetsContainer = (props) => {
  const [petTypes, setPetTypes] = useState([]);
  useEffect(() => {
    fetch('/api/v1/pet_types')
    .then(result => result.json())
    .then(petTypes => {
      setPetTypes(petTypes)
    })
  }, []);

  let mappedPetTypes
  if(petTypes) {
    mappedPetTypes = petTypes.map((petType, index) => {
     return(
      <div className="pet-type-item" key={index}>
        <div className="card-divider">
          <Link to={`/pets/${petType.type}`} >{petType.type[0].toUpperCase() + petType.type.slice(1)}</Link>
        </div>
        <p>Description: {petType.description}</p>
      </div>
     )
    });
  }

  return (
    <div className="pet-type-container card">
      <h1>Pet Types</h1>
      {mappedPetTypes}
    </div>
  );
}

export default PetsContainer;
