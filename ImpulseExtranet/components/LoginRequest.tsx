import AsyncStorage from '@react-native-async-storage/async-storage';

const API_BASE_URL = 'http://impulseapibackend.alwaysdata.net'; // Ou http://localhost:3000 selon votre configuration

// Fonction de login (authentification)
export const login = async (email: string, password: string): Promise<{ success: boolean; token?: string; error?: string }> => {
  if (!email || !password) {
    return { success: false, error: 'Veuillez entrer un email et un mot de passe.' };
  }

  try {
    const response = await fetch(`${API_BASE_URL}/login/intranet`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'authorization': `Basic ${btoa(`${email}:${password}`)}`,
      },
      body: JSON.stringify({ email, password }), // JSON envoyé au backend
    });
    console.log(response.ok);
    if (!response.ok) {
      const errorMessage = await response.text();
      return { success: false, error: errorMessage || 'Erreur dans le mail ou le mot de passe.' };
    }

    const token = await response.text();
    await AsyncStorage.setItem('auth_token', token);
    return { success: true, token };
  } catch (error) {
    await AsyncStorage.removeItem('auth_token');
    console.error('Erreur lors de la connexion :', error);
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
export const forgotPassword = async (email: string) => {
  try {
    const response = await fetch(`${API_BASE_URL}/user/forgotPassword/${email}`, {
      method: 'POST', // Généralement, les endpoints de ce type utilisent POST
      headers: {
        'Content-Type': 'text/plain',
      },
    });

    // Vérifie si la réponse est réussie
    if (!response.ok) {
      console.error(`Erreur : ${response.statusText} (${response.status})`);
      return { success: false, message: `Erreur : ${response.statusText}` };
    }

    // Lit la réponse en texte brut
    const data = await response.text();
    console.log('Réponse brute de l\'API:', data);

    // Retourne la réponse brute comme donnée
    return { success: true, data };
  } catch (error) {
    console.error('Erreur lors de la demande de réinitialisation de mot de passe :', error);
    return { success: false, message: 'Une erreur est survenue. Veuillez réessayer plus tard.' };
  }
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
