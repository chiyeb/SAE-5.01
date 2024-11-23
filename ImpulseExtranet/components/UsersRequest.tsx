const API_BASE_URL = 'http://127.0.0.1:8080'; // Remplacez l'URL avec celle de votre serveur local

// Récupérer tous les utilisateurs
export const getAllUsers = async () => {
  try {
    const response = await fetch(`${API_BASE_URL}/user/get/all`);
    if (!response.ok) {
      throw new Error('Erreur lors de la récupération des utilisateurs');
    }
    return await response.json();
  } catch (error) {
    console.error('Erreur lors de la récupération des utilisateurs :', error);
  }
};

// Créer un utilisateur
export const createUser = async (user: any, token: string) => {
  try {
    const response = await fetch(`${API_BASE_URL}/user/create`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`, // Ajouter le token dans les headers
      },
      body: JSON.stringify(user),
    });
    
    if (!response.ok) {
      throw new Error('Erreur HTTP lors de la création de l\'utilisateur');
    }
    
    return await response.json();
  } catch (error) {
    console.error('Erreur lors de la création de l\'utilisateur :', error);
  }
};

// Mettre à jour un utilisateur
export const updateUser = async (id: string, updatedUser: any, token: string) => {
  if (!id) {
    console.error('ID manquant lors de la mise à jour de l\'utilisateur');
    return;
  }
  try {
    const response = await fetch(`${API_BASE_URL}/user/update/${id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`, // Ajouter le token dans les headers
      },
      body: JSON.stringify(updatedUser),
      
    });
    console.log(response);
    // Vérifier si la réponse est OK (status 200-299)
    if (!response.ok) {
      console.error('Erreur du serveur :', response.statusText);
      throw new Error(`Erreur lors de la mise à jour: ${response.statusText}`);
    }

    // Vérifier si la réponse n'est pas vide
    const responseData = await response.text();
    if (responseData === '') {
      console.error('La réponse du serveur est vide');
      throw new Error('La réponse du serveur est vide');
    }

    // Si la réponse n'est pas vide, la parser en JSON
    return JSON.parse(responseData);
  } catch (error) {
    console.error('Erreur lors de la mise à jour de l\'utilisateur :', error);
    throw error;
  }
};


// Supprimer un utilisateur
export const deleteUser = async (id: string, token: string) => {
  try {
    const response = await fetch(`${API_BASE_URL}/user/delete/${id}`, {
      method: 'DELETE',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`, // Ajouter le token dans les headers
      },
    });

    if (!response.ok) {
      throw new Error('Erreur HTTP lors de la suppression de l\'utilisateur');
    }

    return await response.json();
  } catch (error) {
    console.error('Erreur lors de la suppression de l\'utilisateur :', error);
  }
};

