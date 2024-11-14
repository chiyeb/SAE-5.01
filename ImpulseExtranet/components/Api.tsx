
const API_BASE_URL = 'http://localhost:3000'; // Pour un émulateur iOS ou un navigateur


export const getAllRentals = async () => {
  try {
    const response = await fetch(`${API_BASE_URL}/property/get/rental`);
    return await response.json();
  } catch (error) {
    console.error('Erreur lors de la récupération des biens :', error);
  }
};

export const createRental = async (bien: {
        title: string; description 
            : string; prix: number; address: string;
    }) => {
  try {
    const response = await fetch(`${API_BASE_URL}/property/create/rental`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(bien),
    });
    return await response.json();
  } catch (error) {
    console.error('Erreur lors de la création du bien :', error);
  }
};

export const updateRental = async (id: any, updatedBien: never) => {
  try {
    const response = await fetch(`${API_BASE_URL}/property/update/rental/${id}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(updatedBien),
    });
    return await response.json();
  } catch (error) {
    console.error('Erreur lors de la mise à jour du bien :', error);
  }
};

export const deleteRental = async (id: string) => {
  try {
    await fetch(`${API_BASE_URL}/property/delete/rental/${id}`, {
      method: 'DELETE',
    });
  } catch (error) {
    console.error('Erreur lors de la suppression du bien :', error);
  }
};
