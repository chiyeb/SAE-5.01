import AsyncStorage from '@react-native-async-storage/async-storage';

const API_BASE_URL = 'http://127.0.0.1:8080'; // Ou http://localhost:3000 selon votre configuration

// Fonction de login
export const login = async (
  email: string,
  password: string
): Promise<{ success: boolean; token?: string; error?: string }> => {
  if (!email || !password) {
    return { success: false, error: 'Veuillez entrer un email et un mot de passe.' };
  }

  try {
    const response = await fetch(`${API_BASE_URL}/login`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ email, password }), // JSON envoyé au backend
    });

    if (!response.ok) {
      const errorMessage = await response.text();
      return { success: false, error: errorMessage || 'Erreur inconnue.' };
    }

    const token = await response.text(); // Supposons que le token est renvoyé sous forme de texte
    await AsyncStorage.setItem('auth_token', token); // Stockage du token localement
    return { success: true, token };
  } catch (error) {
    console.error('Erreur réseau ou serveur :', error);
    return { success: false, error: 'Erreur réseau ou serveur.' };
  }
};


// Fonction pour récupérer le token depuis AsyncStorage
export const getStoredToken = async (): Promise<string | null> => {
  try {
    return await AsyncStorage.getItem('auth_token');
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
  const token = await getStoredToken();

  if (!token) {
    console.error('Token non trouvé.');
    return null;
  }

  try {
    const response = await fetch(`${API_BASE_URL}/user/current`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`, // Ajouter le token dans les headers
      },
    });

    if (!response.ok) {
      console.error('Erreur lors de la récupération des informations utilisateur:', response.status, response.statusText);
      return null;
    }

    const userInfo = await response.json();
    return userInfo;

  } catch (error) {
    console.error('Erreur lors de la récupération des informations utilisateur :', error);
    return null;
  }
};

// Fonction pour déconnecter l'utilisateur
export const logout = async (): Promise<void> => {
  try {
    await AsyncStorage.removeItem('auth_token');
    console.log('Utilisateur déconnecté avec succès');
  } catch (error) {
    console.error('Erreur lors de la déconnexion :', error);
  }
};
