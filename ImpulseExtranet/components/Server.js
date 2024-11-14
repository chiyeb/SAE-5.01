const express = require('express');
const cors = require('cors');
const bodyParser = require('body-parser');
const app = express();
const PORT = 3000;

app.use(cors());
app.use(bodyParser.json());

let biens = [
  {
    "id": "e707560e-0e17-43e4-8747-987cab1afb60",
    "type": "APARTMENT",
    "title": "Appartement moderne en centre-ville",
    "description": "Un appartement spacieux avec 2 chambres, cuisine équipée et balcon donnant sur la ville.",
    "location": {
      "latitude": 48.866667,
      "longitude": 2.333333,
      "address": "12 rue de l'eau",
      "city": "Paris",
      "postalCode": "70123",
      "country": "France"
    },
    "images": [],
    "price": 200000,
    "subscriptionFrequency": "MONTHLY",
    "livingArea": 75,
    "landArea": 0,
    "rooms": [
      {
        "roomType": "bathroom",
        "count": 1
      },
      {
        "roomType": "bedroom",
        "count": 2
      },
      {
        "roomType": "kitchen",
        "count": 1
      }
    ],
    "orientation": "Sud",
    "energyClass": "B",
    "climateClass": "A",
    "view": "Vue sur la ville",
    "estimationCostEnergy": 120
  },{
    "type":"APARTMENT",
    "title":"Nouvel appartement",
    "description":"Un appartement spacieux avec 2 chambres, cuisine équipée et balcon donnant sur la ville.",
    "location":{
       "latitude":48.866667,
       "longitude":2.333333,
       "address":"12 rue de l'eau",
       "city":"Paris",
       "postalCode":"70123",
       "country":"France"
    },
    "images" : [],
    "price":200000.0,
    "subscriptionFrequency":"MONTHLY",
    "livingArea":75.0,
    "landArea":0.0,
    "rooms":[
       {
          "roomType":"kitchen",
          "count":1
       },
       {
          "roomType":"bathroom",
          "count":1
       },
       {
          "roomType":"bedroom",
          "count":2
       }
    ],
    "orientation":"Sud",
    "energyClass":"B",
    "climateClass":"A",
    "view":"Vue sur la ville",
    "estimationCostEnergy":120.0
}

];


// Route par défaut pour la racine "/"
app.get('/', (req, res) => {
  res.json(biens); // Affiche tous les biens en JSON directement sur la page
});

// Endpoint pour obtenir tous les biens
app.get('/property/get/rental', (req, res) => {
  res.json(biens);
});

// Endpoint pour créer un bien
app.post('/property/create/rental', (req, res) => {
  const newBien = { id: Date.now().toString(), ...req.body };
  biens.push(newBien);
  res.json(newBien);
});

// Endpoint pour mettre à jour un bien
app.put('/property/update/rental/:id', (req, res) => {
  const { id } = req.params;
  biens = biens.map(bien => (bien.id === id ? { ...bien, ...req.body } : bien));
  res.json(biens.find(bien => bien.id === id));
});

// Endpoint pour supprimer un bien
app.delete('/property/delete/rental/:id', (req, res) => {
  const { id } = req.params;
  biens = biens.filter(bien => bien.id !== id);
  res.json({ message: 'Bien supprimé' });
});

// Lancer le serveur sur le port 3000
app.listen(PORT, () => {
  console.log(`Serveur lancé sur http://localhost:${PORT}`);
});
