import fs from "fs"
import readline from 'readline';
import path from "path";
import { fileURLToPath } from 'url';

const __filename = fileURLToPath(import.meta.url)
const __dirname = path.dirname(__filename)

const rePath = new RegExp("path=(?:\"|\')(.*)(?:\"|\')")
const reParams = /:\b([A-Za-z]*)\b/g;

const file = readline.createInterface({
  input : fs.createReadStream(path.join(__dirname,'../src/components/NavBar.js')),
  output : process.stdout,
  terminal : false
})
async function getRoutes() {
    let routes = [];
    for await (let line of file) {
      let route = {
        path : "",
        params : []
      }
      let result = line.match(rePath)
      if (result) {
        route.path = result[1]
        if (result[1].includes(":")) {
          let params = result[1].match(reParams);
          if (params) {
            route.params = params.map(param => param.replace(":", ""))
          }
        }
      }
      if (route.path !== "") {
        routes.push(route);
      }
    }
    return routes
}

export default getRoutes;
