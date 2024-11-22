import React, { useState, useEffect } from 'react';
import { View, ScrollView, StyleSheet, Modal, Text, TouchableOpacity, Alert , } from 'react-native';
import Bien from '@/components/Bien';
import { ThemedText } from '@/components/ThemedText';
import Buttons from '@/components/navigation/Buttons';
import TabSelector from '@/components//navigation/ButtonVenteLocation'; // Assurez-vous d'avoir ajouté TabSelector
import DetailBien from '@/components/DetailBien';
import { createProperty, getAllProperties,updateProperty, deleteProperty } from '@/components/Api';


export default function HomeScreen() {
  const [biens, setBiens] = useState<any[]>([]);
  const [isModalVisible, setIsModalVisible] = useState(false);
  const [isModalUpdate, setIsModalUpdate] = useState(false);
  const [selectedBien, setSelectedBien] = useState<any | null>(null);
  const [selectedTab, setSelectedTab] = useState('purchasable');

  useEffect(() => {
    const fetchBiens = async () => {
      try {
        const data = await getAllProperties(selectedTab);
        if (Array.isArray(data)) {
          setBiens(data);
        } else {
          console.error('Données invalides reçues:', data);
          setBiens([]);
        }
      } catch (error) {
        console.error('Erreur lors de la récupération des biens :', error);
      }
    };

    fetchBiens();
  }, [selectedTab]);

  const handleAddBien = async () => {
    const newBien = {
      type: 'APARTMENT',
      title: 'Nouvel appartement',
      description: 'Un appartement spacieux...',
      location: {
        address: '12 rue de l\'eau',
        city: 'Paris',
        postalCode: '70123',
        country: 'France',
        latitude: 48.866667,
        longitude: 2.333333,
      },
      images: [],
      price: 200000,
      ...(selectedTab === 'rental' && { subscriptionFrequency: "WEEKLY" }), // Ajoute uniquement pour location
      livingArea: 75,
      landArea: 0,
      rooms: [
        { roomType: 'Cuisine', count: 1 },
        { roomType: 'Chambre', count: 2 },
      ],
      orientation: 'Sud',
      energyClass: 'B',
      climateClass: 'A',
      view: 'Vue sur la ville',
      estimationCostEnergy: 120,
      
    };

    setSelectedBien(newBien);
    setIsModalVisible(true);
  };

  const closeModal = () => {
    setIsModalVisible(false);
    setIsModalUpdate(false);
    setSelectedBien(null);
  };

  const handleSaveBien = async (bienData: any) => {
    if (!bienData.title || !bienData.description || !bienData.price) {
      Alert.alert('Erreur', 'Veuillez remplir tous les champs obligatoires.');
      return;
    }
    try {
      const createdBien = await createProperty(bienData, selectedTab);
      setBiens([...biens, createdBien]);
      Alert.alert('Succès', 'Le bien a été ajouté.');
      closeModal();
    } catch (error) {
      console.error('Erreur lors de l\'ajout du bien :', error);
      Alert.alert('Erreur', 'Impossible d\'ajouter le bien.');
    }
  };

    // Fonction pour afficher le modal avec l'utilisateur sélectionné pour mise à jour
    const openModalForUpdate = (id: string, bienData: any) => {
      setSelectedBien(bienData);
      setIsModalUpdate(true); // Ouvrir le modal pour mise à jour
    };
  
    const handleUpdateBien = async (bienData: any) => {
      if (!bienData || !bienData.id) {
        console.error('ID de l\'utilisateur est  manquant', bienData);
        return;
      }
    
      // Continuer avec la mise à jour
      try {
        const updatedBien = await updateProperty(bienData.id, bienData, selectedTab);
        setBiens(prevBiens =>
          prevBiens.map(bien => (bien.id === bienData.id ? updatedBien : bien))
        );
        console.log('Utilisateur mis à jour', updatedBien);
      } catch (error) {
        console.error('Erreur lors de la mise à jour', error);
      }
      closeModal();
    };

  const handleDeleteBien = async (id: string) => {
    try {
      await deleteProperty(id, selectedTab);
      setBiens(biens.filter((bien) => bien.id !== id));
      Alert.alert('Succès', 'Le bien a été supprimé.');
    } catch (error) {
      console.error('Erreur lors de la suppression :', error);
      Alert.alert('Erreur', 'Impossible de supprimer le bien.');
    }
  };

  return (
    <View style={styles.container}>
      <ScrollView>
        <View style={styles.titleContainer}>
          <ThemedText type="title">Administrateur</ThemedText>
        </View>
        <TabSelector selectedTab={selectedTab} onTabSelect={setSelectedTab} />
        <View style={styles.stepContainer}>
          <ThemedText type="h2">Mes annonces</ThemedText>
          {biens.length > 0 ? (
            biens.map((bien) => (
              <View key={bien.id}>
                <Bien {...bien} onPress={() => setSelectedBien(bien)} />
                <TouchableOpacity onPress={() => handleDeleteBien(bien.id)} style={styles.deleteButton}>
                  <Text style={styles.buttonText}>Supprimer</Text>
                </TouchableOpacity>
                <TouchableOpacity
                  onPress={() => openModalForUpdate(bien.id, bien)}
                  style={styles.updateButton}
                >
                  <Text style={styles.buttonText}>Modifier</Text>
                </TouchableOpacity>
              </View>
            ))
          ) : (
            <Text>Aucun bien trouvé</Text>
          )}
          <Buttons onPress={handleAddBien} />
          
        </View>
      </ScrollView>

      <Modal visible={isModalVisible} animationType="slide" transparent={true} onRequestClose={closeModal}>
        <View style={styles.modalContainer}>
          <View style={styles.modalContent}>
            {selectedBien ? (
              <DetailBien
              selectedTab={selectedTab}
                {...selectedBien}
                onSaveBien={handleSaveBien}
                onDelete={closeModal}
              />
              
            ) : (
              <Text>Aucun bien sélectionné</Text>

            )}
          </View>
        </View>
      </Modal>

      <Modal visible={isModalUpdate} animationType="slide" transparent={true} onRequestClose={closeModal}>
        <View style={styles.modalContainer}>
          <View style={styles.modalContent}>
            {selectedBien ? (
              <DetailBien
              selectedTab={selectedTab}
                {...selectedBien}
                onSaveBien={(bienData) =>handleUpdateBien(bienData)}
                onDelete={closeModal}
              />
              
            ) : (
              <Text>Aucun bien sélectionné</Text>

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
    alignItems:'center',
    backgroundColor: '#fff',
    
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
    height: '90%',
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
  updateButton: {
    backgroundColor: '#9CCC65',
    padding: 10,
    borderRadius: 5,
    width: '30%',
    alignSelf: 'center',
  },
});
