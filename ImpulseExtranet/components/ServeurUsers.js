const express = require('express');
const cors = require('cors');
const bodyParser = require('body-parser');
const app = express();
const PORT = 3000;

app.use(cors());
app.use(bodyParser.json());

// Liste d'utilisateurs en mémoire (base de données simulée)
let users = [ {
  "id": "17ceb566-98db-46c6-9ba0-5c8b11882642",
  "name": "John",
  "lastname": "Doe",
  "email": "chihebprivate@gmail.com",
  "age": 25,
  "phoneNumber": 123456789,
  "moreInformations": "I'm a real estate agent",
},
{
  "id": "10ceb567-98db-46c6-9ba0-5c8b11882642",
  "name": "Richard",
  "lastname": "Jacque",
  "email": "raoul@gmail.com",
  "age": 21,
  "phoneNumber": 123456789,
  "moreInformations": "I'm a real estate agent",
}];

// Route par défaut pour la racine "/"
app.get('/', (req, res) => {
  res.json(users); // Affiche tous les users en JSON directement sur la page
});

// Endpoint pour obtenir tous les users
app.get('/user/get/all', (req, res) => {
  res.json(users);
});

// Endpoint pour créer un user
app.post('/user/create', (req, res) => {
  const newUser = { id: Date.now().toString(), ...req.body };
  users.push(newUser);
  res.json(newUser);
});

// Endpoint pour mettre à jour un user
app.put('/user/update/:id', (req, res) => {
  const { id } = req.params;
  users = users.map(user => (user.id === id ? { ...user, ...req.body } : user));
  res.json(users.find(user => user.id === id));
});

// Endpoint pour supprimer un user
app.delete('/user/delete/:id', (req, res) => {
  const { id } = req.params;
  users = users.filter(user => user.id !== id);
  res.json({ message: 'user supprimé' });
});

// Lancer le serveur sur le port 3000
app.listen(PORT, () => {
  console.log(`Serveur lancé sur http://localhost:${PORT}`);
});
