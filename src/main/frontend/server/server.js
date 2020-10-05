import express from "express"
import path from "path"
import logger from "morgan"
import bodyParser from "body-parser"
import hbsMiddleware from "express-handlebars"
import _ from "lodash"
import pg from 'pg'
import { fileURLToPath } from 'url'
import { helpers } from "./helpers.js";
import getRoutes from "./routeGenerator.js";

const __filename = fileURLToPath(import.meta.url)
const __dirname = path.dirname(__filename)

const app = express()
app.set("views", path.join(__dirname, "../views"))
app.engine(
  "hbs",
  hbsMiddleware({
    defaultLayout: "default",
    extname: ".hbs"
  })
)

app.set("view engine", "hbs")

app.use(logger("dev"))
app.use(express.json())

app.use(express.static(path.join(__dirname, "../public")))
app.use(bodyParser.urlencoded({ extended: true }))
app.use(bodyParser.json())

const pool = new pg.Pool({
  user : 'postgres',
  host : '127.0.0.1',
  database : 'adoptions',
  password : 'postgres',
  port : '5432'
});

// Express routes

getRoutes()
  .then(routes => {
    routes.forEach(route => {
      app.get(route.path, (req,res) => {
        res.render("home")
    })
  })
})

app.get('/', (req, res) => {
  res.redirect("/pets")
})

app.get('/pets', (req, res) => {
  res.render("home")
})

app.post("/api/v1/petadoptions/:id", (req, res) => {
  const petAdoption = req.body
  let queryString = "INSERT INTO adoption_applications (name, phone_number, email, home_status, application_status, pet_id) VALUES ($1, $2, $3, $4, $5, $6)" 
  pool.connect()
    .then(client => {
      client.query(queryString, [petAdoption.name, petAdoption.phone_number, petAdoption.email, petAdoption.home_status, "pending", req.params.pet_id])
        .then(result => {
          client.release()
          res.redirect("/")
        })
        .catch(error => console.log(error))
    })
    .catch(error => console.log(error))
})

app.get('/api/v1/pet_types', (req, res) => {
  helpers.getAllFromTable("pet_types", res, pool)
})

app.get('/api/v1/pets/:pet_type', (req, res) => {
  let queryString = "SELECT * FROM adoptable_pets JOIN pet_types ON adoptable_pets.type_id = pet_types.id WHERE type = $1"
  pool.query(queryString, [req.params.pet_type])
    .then(result => res.json(result.rows))
    .catch(error => console.log(error))
})

app.get("/api/v1/pets/:pet_type/:id", (req, res) => {
  let queryString = "SELECT * FROM adoptable_pets WHERE id = $1"
  pool.query(queryString, [req.params.id])
  .then(result => res.json(result.rows))
  .catch(error => console.log(error))
})

app.post("/api/v1/adoptions/new", (req, res) => {
  const petSurrender = req.body
  let queryString = "INSERT INTO pet_surrender_applications (name, phone_number, email, pet_name, pet_age, pet_type_id, pet_image_url, vaccination_status, application_status) VALUES ($1, $2, $3, $4, $5, $6, $7, $8, $9)"
  let petQueryString = "INSERT INTO adoptable_pets (name, img_url, age, vaccination_status, adoption_story,adoption_status, type_id) VALUES ($1, $2, $3, $4, $5, $6, $7)"
  pool.connect()
    .then(client => {
      client.query(queryString, [petSurrender.name, petSurrender.phone_number, petSurrender.email, petSurrender.pet_name, petSurrender.pet_age, petSurrender.pet_type_id, petSurrender.pet_image_url, petSurrender.vaccination_status, false])
      .then(result => {
        client.query(petQueryString, [petSurrender.pet_name, petSurrender.pet_image_url, petSurrender.pet_age, petSurrender.vaccination_status, "Surrendered pet", false, petSurrender.pet_type_id])
          .then(result => {
            client.release()
            res.json(result)
          })
      })
      .catch(error => console.log(error))
    })
    .catch(error => console.log(error))
})

app.listen(3000, "0.0.0.0", () => {
  console.log("Server is listening on port 3000...")
})

export default app