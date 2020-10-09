import React, { useState, useEffect } from 'react'
import _ from 'lodash'

const PetSurrenderForm = (props) => {
  const [petType, setPetType] = useState("")
  const [surrenderForm, setSurrenderForm] = useState({
    name: "",
    phoneNumber: "",
    email: "",
    petName: "",
    petAge: "",
    petImageUrl: "",
    vaccinationStatus: ""
  })
  
  const [inProcess, setInProcess] = useState("");

  let requiredFields = {
    name: "Name",
    phoneNumber: "Phone number",
    email: "Email",
    petName: "Pet name",
    petImageUrl: "Link to image",
    vaccinationStatus: "Is this critter vaccinated?"
  }

  const [errors, setErrors] = useState({})

  const terriblePerson = () => {
    setInProcess("");
  }
  
  const handleChange = (event) => {
    if (event.target.name == "petType") {
      setPetType(event.target.value)
    } else {
      setSurrenderForm({
        ...surrenderForm,
        [event.target.name]: event.target.value
      })
    }

  }

  const handleSubmit = (event) => {
    event.preventDefault()
    let formErrors = {}
    for (let field of Object.keys(requiredFields)) {
      if (surrenderForm[field].trim() === "") {
        formErrors = {
          ...formErrors,
          [field]: `${requiredFields[field]} cannot be blank.`
        }
      }
    }
    setErrors(formErrors)
    if (_.isEmpty(formErrors)) {
      fetch(`/api/v1/adoptions/new/${petType}`, {
        method: "POST",
        body: JSON.stringify(surrenderForm),
        headers: { "Content-Type": "application/json" }
      })
        .then(result => {
          setInProcess(
          <div>
            <p>Your request is in process</p>
            <button className="special-ashley-button" onClick={terriblePerson} >Would you like to surrender another pet you horrible person?</button>
          </div>)
          setSurrenderForm({
            name: "",
            phoneNumber: "",
            email: "",
            petName: "",
            petAge: "",
            petImageUrl: "",
            vaccinationStatus: ""
          })
        })
    }
  }
  let form = "";
  if (inProcess === "") {
    form = (
      <form onChange={handleChange} onSubmit={handleSubmit}>
        <label>Name
          <p className="error">{errors.name}</p>
          <input type="text" name="name" id="name" value={surrenderForm.name} />
        </label>
        <label>Phone Number
          <p className="error">{errors.phoneNumber}</p>
          <input type="text" name="phoneNumber" id="phoneNumber" value={surrenderForm.phoneNumber} />
        </label>
        <label>Email
          <p className="error">{errors.email}</p>
          <input type="text" name="email" id="email" value={surrenderForm.email} />
        </label>
        <label>Pet type
          <p className="error">{errors.petType}</p>
          <select type="text" name="petType" id="pet_type" value={surrenderForm.petType}>
            <option value=""></option>
            <option value="1">Cat</option>
            <option value="2">Dog</option>
            <option value="3">Other</option>
          </select>
        </label>
        <label>Age
          <p className="error">{errors.age}</p>
          <input type="text" name="petAge" id="age" value={surrenderForm.petAge} />
        </label>
        <label>Pet name
          <p className="error">{errors.petName}</p>
          <input type="text" name="petName" id="name" value={surrenderForm.petName} />
        </label>
        <label>Image url
          <p className="error">{errors.image_url}</p>
          <input type="text" name="petImageUrl" id="image_url" value={surrenderForm.petImageUrl} />
        </label>
        <label>Vaccination Status
          <p className="error">{errors.vaccinationStatus}</p>
          <select type="text" name="vaccinationStatus" id="vaccinationStatus" value={surrenderForm.vaccinationStatus}>
            <option value=""></option>
            <option value="true">Yes</option>
            <option value="false">No</option>
          </select>
        </label>
        <input type="submit" value="submit" />
      </form>
    )
  }

  return (
    <div className="surrender">
      <h1>Pet Surrender</h1>
      {inProcess}
      {form}
    </div>
  )
}

export default PetSurrenderForm


