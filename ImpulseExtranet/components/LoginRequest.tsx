const API_BASE_URL = 'http://127.0.0.1:8080'; // http://localhost:3000

// Fonction de login (authentification)
export const login = async (email: string, password: string): Promise<string | null> => {
  try {
    const response = await fetch(`${API_BASE_URL}/login`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Basic ${btoa(`${email}:${password}`)}`, // Authentification basique
      },
    });

    if (!response.ok) {
      console.error('Erreur lors de la connexion :', response.status, response.statusText);
      return null;
    }

    return await response.text(); // Retourne le token en tant que texte
  } catch (error) {
    console.error('Erreur lors de la connexion :', error);
    return null;
  }
};

// Fonction pour récupérer les informations utilisateur
export const getUserInfo = async (token: string): Promise<any | null> => {
  try {
    const response = await fetch(`${API_BASE_URL}/user/current`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${token}`, // Ajoute le jeton JWT dans l'en-tête
      },
    });

    if (!response.ok) {
      console.error('Erreur lors de la récupération des informations utilisateur :', response.status, response.statusText);
      return null;
    }

    return await response.json(); // Retourne les informations utilisateur sous forme d'objet
  } catch (error) {
    console.error('Erreur lors de la récupération des informations utilisateur :', error);
    return null;
  }
};
