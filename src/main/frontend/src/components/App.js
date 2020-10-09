import React from "react"
import { Route, BrowserRouter, Redirect } from "react-router-dom"
import NavBar from "./NavBar"

const App = props => {
  return (
  	<BrowserRouter>
      <Route exact path="/">
        <Redirect to="/pets"/>
      </Route>
      <Route exact path="/pets" component={NavBar} />
      <Route exact path="/pets/:pet_type" component={NavBar} />
      <Route exact path="/pets/:pet_type/:id" component={NavBar} />
      <Route exact path="/adoptions/new" component={NavBar} />
    </BrowserRouter>
  )
}

export default App
