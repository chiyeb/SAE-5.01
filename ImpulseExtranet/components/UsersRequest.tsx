const API_BASE_URL = 'http://localhost:3000'; // Remplacez l'URL avec celle de votre serveur local

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
export const createUser = async (user: any) => {
  try {
    const response = await fetch(`${API_BASE_URL}/user/create`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
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
export const updateUser = async (id: any, updatedUser: any) => {
  try {
    const response = await fetch(`${API_BASE_URL}/user/update/${id}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(updatedUser),
    });
    
    if (!response.ok) {
      throw new Error('Erreur HTTP lors de la mise à jour de l\'utilisateur');
    }
    
    return await response.json();
  } catch (error) {
    console.error('Erreur lors de la mise à jour de l\'utilisateur :', error);
  }
};

// Supprimer un utilisateur
export const deleteUser = async (id: string) => {
  try {
    const response = await fetch(`${API_BASE_URL}/user/delete/${id}`, {
      method: 'DELETE',
    });

    if (!response.ok) {
      throw new Error('Erreur HTTP lors de la suppression de l\'utilisateur');
    }

    return await response.json();
  } catch (error) {
    console.error('Erreur lors de la suppression de l\'utilisateur :', error);
  }
};

// Récupérer un utilisateur par ID
export const getUserById = async (id: any) => {
  try {
    const response = await fetch(`${API_BASE_URL}/user/get/${id}`);
    if (!response.ok) {
      throw new Error('Erreur lors de la récupération de l\'utilisateur');
    }
    return await response.json();
  } catch (error) {
    console.error('Erreur lors de la récupération de l\'utilisateur :', error);
  }
};
