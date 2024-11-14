import React, { useState, useEffect } from 'react';
import { View, ScrollView, StyleSheet, Modal, Text, TouchableOpacity, Alert , } from 'react-native';
import Bien from '@/components/Bien';
import { ThemedText } from '@/components/ThemedText';
import Buttons from '@/components/navigation/Buttons';
import Profil from '@/components/Profil';
import DetailBien from '@/components/DetailBien';
import { getAllRentals, createRental, updateRental, deleteRental } from '@/components/Api';


export default function HomeScreen() {
  const [biens, setBiens] = useState<any[]>([]); // Initialiser biens avec un tableau vide
  const [isModalVisible, setIsModalVisible] = useState(false);
  const [selectedBien, setSelectedBien] = useState<any | null>(null);

  // Effect pour charger les biens via l'API
  useEffect(() => {
    const fetchBiens = async () => {
      try {
        const data = await getAllRentals();

        // Vérification que les données reçues sont bien un tableau
        if (Array.isArray(data)) {
          setBiens(data);  // Mettre à jour biens seulement si data est un tableau
        } else {
          console.error('Données invalides reçues:', data);
          setBiens([]); // Si ce n'est pas un tableau, réinitialiser biens
        }
      } catch (error) {
        console.error("Erreur lors de la récupération des biens :", error);
      }
    };
    
    fetchBiens();
  }, []);

  // Fonction pour ajouter un bien
  const handleAddBien = async () => {
    const newBien = {
      type: "APARTMENT",
      title: "Nouvel appartement",
      description: "Un appartement spacieux avec 2 chambres, cuisine équipée et balcon donnant sur la ville.",
      location: {
        address: "12 rue de l'eau",
        city: "Paris",
        postalCode: "70123",
        country: "France",
        latitude: 48.866667,
        longitude: 2.333333
      },
      images: [],
      price: 200000.0,
      subscriptionFrequency: "MONTHLY",
      livingArea: 75.0,
      landArea: 0.0,
      rooms: [
        { roomType: "Cuisine", count: 1 },
        { roomType: "Salle de bain", count: 1 },
        { roomType: "Chambre", count: 2 }
      ],
      orientation: "Sud",
      energyClass: "B",
      climateClass: "A",
      view: "Vue sur la ville",
      estimationCostEnergy: 120.0
    };

    setSelectedBien(newBien);
    console.log(selectedBien);
    setIsModalVisible(true);
  };
  
  // Fermer le modal
  const closeModal = () => {
    setIsModalVisible(false);
    setSelectedBien(null);  // Remise à zéro de selectedBien
  };

  const handleSaveBien = async (bienData) => {
    try {
      const createdBien = await createRental(bienData);
      Alert.alert('Succès', 'Le bien a été ajouté avec succès.');
      setBiens([...biens, createdBien]);  // Ajouter le bien à la liste locale
      closeModal();
      
    } catch (error) {
      console.error("Erreur lors de l'ajout du bien :", error);
      Alert.alert('Erreur', 'Impossible d\'ajouter le bien.');
    }
  };
  // Fonction de suppression d'un bien
  const handleDeleteBien = async (id: string) => {
    try {
      await deleteRental(id);
      setBiens(biens.filter(bien => bien.id !== id));
      Alert.alert('Succès', 'Le bien a été supprimé.');
    } catch (error) {
      console.error("Erreur lors de la suppression du bien :", error);
      Alert.alert('Erreur', 'Impossible de supprimer le bien.');
    }
  };

  return (
    <View style={styles.container}>
      <View style={styles.profil}>
        <Profil />
      </View>
      <ScrollView showsHorizontalScrollIndicator={false}>
        <View style={styles.titleContainer}>
          <ThemedText type="title">Administrateur</ThemedText>
        </View>
        <View style={styles.stepContainer}>
          <ThemedText type="h2">Mes annonces</ThemedText>
          {biens.length > 0 ? (
            biens.map((bien) => (
              <View key={bien.id}>
                <Bien {...bien} onPress={() => setSelectedBien(bien)} />
                <TouchableOpacity onPress={() => handleDeleteBien(bien.id)} style={styles.deleteButton}>
                  <Text style={styles.buttonText}>Supprimer</Text>
                </TouchableOpacity>
              </View>
            ))
          ) : (
            <Text>Aucun bien trouvé</Text> // Affiche ce message si biens est vide
          )}
          <Buttons onPress={handleAddBien} />
        </View>
      </ScrollView>

      {/* Modal pour afficher les détails du bien sélectionné */}
      <Modal
        visible={isModalVisible}
        animationType="slide"
        transparent={true}
        onRequestClose={closeModal}
      >
        <View style={styles.modalContainer}>
          <View style={styles.modalContent}>
            {selectedBien ? (
             <DetailBien 
             title={selectedBien.title} 
             description={selectedBien.description} 
             price={selectedBien.price} 
             location={selectedBien.location}
             livingArea={selectedBien.livingArea} 
             landArea={selectedBien.landArea} 
             orientation={selectedBien.orientation} 
             view={selectedBien.view} 
             estimationCostEnergy={selectedBien.estimationCostEnergy} 
             onSaveBien={handleSaveBien}
             onDelete={closeModal}
           />
            ) : (
              <Text>Aucun bien sélectionné</Text> // Message d'erreur si selectedBien est null
            )}
          </View>
        </View>
      </Modal>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    paddingHorizontal: 16,
    backgroundColor: '#fff',
    flexDirection: 'row',
  },
  titleContainer: {
    alignItems: 'center',
    margin: 16,
  },
  stepContainer: {
    alignItems: 'center',
    marginBottom: 16,
  },
  profil: {
    width: '15%',
  },
  modalContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: 'rgba(0, 0, 0, 0.5)', // Semi-transparent background
  },
  modalContent: {
    backgroundColor: 'white',
    borderRadius: 10,
    padding: 20,
    width: '90%',
    height: '80%',
    alignItems: 'center',
  },

  buttonText: {
    color: '#fff',
    fontSize: 16,
    textAlign:'center',
  },
  deleteButton: {
    backgroundColor: '#dc3545',
    padding: 10,
    borderRadius: 5,
    width: '30%',
    alignSelf: 'center'
  },
});
