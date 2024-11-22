const express = require("express");
const bodyParser = require("body-parser");
const cors = require("cors");
const jwt = require("jsonwebtoken");
const bcrypt = require("bcrypt");

const app = express();

// Récupérer le port de l'environnement ou utiliser 3000
const PORT = process.env.PORT || 3000;

// Clé secrète pour signer les tokens JWT
const JWT_SECRET = "my_secret_key";

// Middleware pour CORS et parsing du corps des requêtes
app.use(cors());
app.use(bodyParser.json());

// Mock utilisateurs (normalement, utilisez une base de données)
const users = [
  { id: 1, email: "test@example.com", password: "$2b$10$abc123hashedpassword" }, // Le mot de passe haché est "password123"
];

// Endpoint pour la connexion
app.post("/login", async (req, res) => {
  const { username, password } = req.body;

  if (!username || !password) {
    return res.status(400).json({ message: "Veuillez fournir un email et un mot de passe." });
  }

  // Recherche de l'utilisateur
  const user = users.find((u) => u.email === username);
  if (!user) {
    return res.status(401).json({ message: "Utilisateur non trouvé." });
  }

  // Vérification du mot de passe
  const isValidPassword = await bcrypt.compare(password, user.password);
  if (!isValidPassword) {
    return res.status(401).json({ message: "Mot de passe incorrect." });
  }

  // Génération d'un token JWT
  const token = jwt.sign({ sub: user.id, email: user.email }, JWT_SECRET, {
    expiresIn: "1h",
  });

  res.json({ token });
});

// Endpoint pour récupérer les informations utilisateur
app.get("/user/current", (req, res) => {
  const authHeader = req.headers.authorization;

  if (!authHeader || !authHeader.startsWith("Bearer ")) {
    return res.status(401).json({ message: "Authorization header manquant ou invalide." });
  }

  const token = authHeader.split(" ")[1];

  try {
    const decoded = jwt.verify(token, JWT_SECRET);
    res.json({ user: decoded });
  } catch (err) {
    res.status(401).json({ message: "Token invalide ou expiré." });
  }
});

// Middleware de gestion des erreurs pour capture globale des erreurs
app.use((err, req, res, next) => {
  console.error(err);
  res.status(500).json({ message: "Une erreur serveur est survenue." });
});

// Lancement du serveur
app.listen(PORT, () => {
  console.log(`Serveur en cours d'exécution sur http://localhost:${PORT}`);
});
