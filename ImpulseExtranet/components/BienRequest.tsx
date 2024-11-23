const API_BASE_URL = 'http://127.0.0.1:8080'; // url de l'API

// Récupérer tous les biens (vente/location)
export const getAllProperties = async (selectedTab: string) => {
  try {
    console.log(selectedTab);
    const response = await fetch(`${API_BASE_URL}/property/get/${selectedTab}`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
      },
    });
    return await response.json();
  } catch (error) {
    console.error('Erreur lors de la récupération des biens :', error);
  }
};

// Créer un bien
export const createProperty = async (bien: any, selectedTab: string, token: string) => {
  try {
    const endpoint = selectedTab === 'purchasable' ? 'purchasable' : 'rental';
    const response = await fetch(`${API_BASE_URL}/property/create/${endpoint}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`, // Ajouter le token dans les headers
      },
      body: JSON.stringify(bien),
    });
    return response.ok ? await response.json() : console.error('Erreur HTTP', response.status);
  } catch (error) {
    console.error('Erreur lors de la création du bien :', error);
  }
};

// Mettre à jour un bien
export const updateProperty = async (id: string, updatedBien: any, selectedTab: string, token: string) => {
  try {
    const endpoint = selectedTab === 'purchasable' ? 'purchasable' : 'rental';
    const response = await fetch(`${API_BASE_URL}/property/update/${endpoint}/${id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`, // Ajouter le token dans les headers
      },
      body: JSON.stringify(updatedBien),
    });
    return await response.json();
  } catch (error) {
    console.error('Erreur lors de la mise à jour du bien :', error);
  }
};

// Supprimer un bien
export const deleteProperty = async (id: string, selectedTab: string, token: string) => {
  try {
    const endpoint = selectedTab === 'purchasable' ? 'purchasable' : 'rental';
    const response = await fetch(`${API_BASE_URL}/property/delete/${endpoint}/${id}`, {
      method: 'DELETE',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`, // Ajouter le token dans les headers
      },
    });

    if (!response.ok) {
      throw new Error(`Erreur lors de la suppression du bien : ${response.statusText}`);
    }

    console.log('Bien supprimé avec succès');
  } catch (error) {
    console.error('Erreur lors de la suppression du bien :', error);
  }
};
