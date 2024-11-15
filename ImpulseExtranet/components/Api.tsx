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
  type: string;
  title: string;
  description: string;
  localisation: {
    latitude: number;
    longitude: number;
    address: string;
    city: string;
    postalCode: string;
    country: string;

  };
  images: string[];
  price: number;
  //subscriptionFrequency: string;
  livingArea: number;
  landArea: number;
  rooms: Array<{ roomType: string; count: number }>;
  orientation: string;
  energyClass: string;
  climateClass: string;
  view: string;
  estimationCostEnergy: number;
}) => {
  try {
    console.log(bien.images);
    const response = await fetch(`${API_BASE_URL}/property/create/rental`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(bien),
    });

    // Vérifiez si la réponse est OK, puis retournez-la
    if (response.ok) {
      const result = await response.json();
      return result;
    } else {
      console.error('Erreur HTTP lors de la création du bien', response.status);
    }
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
