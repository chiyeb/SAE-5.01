import AsyncStorage from '@react-native-async-storage/async-storage';

const API_BASE_URL = 'http://127.0.0.1:8080'; // Ou http://localhost:3000 selon votre configuration

// Fonction de login (authentification)
export const login = async (email: string, password: string): Promise<string | null> => {
  // Validation de l'email et du mot de passe avant de tenter une connexion
  if (!email || !password) {
    console.error('Veuillez entrer un email et un mot de passe.');
    return null;
  }

  try {
    const response = await fetch(`${API_BASE_URL}/login`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Basic ${btoa(`${email}:${password}`)}`,
      },
    });

    if (!response.ok) {
      // Améliorer l'affichage d'erreur pour l'utilisateur
      console.error('Erreur lors de la connexion :', response.status, response.statusText);
      return null;
    }

    // Récupération du token depuis la réponse
    const token = await response.text();

    // Stockage du token dans AsyncStorage pour une utilisation ultérieure
    await AsyncStorage.setItem('auth_token', token);

    return token;
  } catch (error) {
    console.error('Erreur lors de la connexion :', error);
    return null;
  }
};

// Fonction pour récupérer le token depuis AsyncStorage
export const getStoredToken = async (): Promise<string | null> => {
  try {
    const token = await AsyncStorage.getItem('auth_token');
    return token;
  } catch (error) {
    console.error('Erreur lors de la récupération du token depuis AsyncStorage :', error);
    return null;
  }
};

// Fonction pour vérifier si l'utilisateur est connecté (en vérifiant la présence d'un token)
export const isLoggedIn = async (): Promise<boolean> => {
  const token = await getStoredToken();
  return token !== null;
};

// Fonction pour récupérer les informations utilisateur
export const getUserInfo = async (): Promise<any | null> => {
  try {
    // Récupérer le token stocké dans AsyncStorage
    const token = await AsyncStorage.getItem('auth_token');
    if (!token) {
      console.error('Token non trouvé.');
      return null;
    }

    // Effectuer la requête GET pour récupérer les informations de l'utilisateur
    const response = await fetch(`${API_BASE_URL}/user/current`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${token}`, // Ajouter le token dans les headers
      },
    });

    // Vérifier si la réponse est correcte
    if (!response.ok) {
      console.error('Erreur lors de la récupération des informations utilisateur:', response.status, response.statusText);
      return null;
    }

    // Retourner les informations utilisateur sous forme de JSON
    const userInfo = await response.json();
    return userInfo;

  } catch (error) {
    console.error('Erreur lors de la récupération des informations utilisateur :', error);
    return null;
  }
};
