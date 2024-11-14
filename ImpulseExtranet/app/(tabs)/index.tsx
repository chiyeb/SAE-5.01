import React, { useState, useEffect } from 'react';
import { View, ScrollView, StyleSheet, Modal, Text, TouchableOpacity, Alert } from 'react-native';
import Bien from '@/components/Bien';
import { ThemedText } from '@/components/ThemedText';
import Buttons from '@/components/navigation/Buttons';
import Profil from '@/components/Profil';
import DetailBien from '@/components/DetailBien';
import { getAllRentals, createRental, updateRental, deleteRental } from '@/components/Api';



export default function HomeScreen() {
  
  const newBien = {
    id: new Date().getTime().toString(),
    title: `Bien `,
    description: "Ceci est une description d'exemple pour un nouveau bien.",
    prix: 11,
    adress: "564 Avenue Gaston Berger",
    pays:'bienDetails.pays}',
    ville:'{bienDetails.ville}',
    codePostal:123312,
    surfaceHabitable:234,
    surfaceTerrain:123,
    orientation:'{bienDetails.orientation',
    vue:'{bienDetails.vue}',
    estimate:'{bienDetails.estimate}'
  };
  const [biens, setBiens] = useState([]);
  const [isModalVisible, setIsModalVisible] = useState(false); 
  const [selectedBien, setSelectedBien] = useState
  (null);
  useEffect(() => {
    const fetchBiens = async () => {
      try {
        const data = await getAllRentals();
        setBiens(data);  // Charger les biens obtenus de l'API
      } catch (error) {
        console.error("Erreur lors de la récupération des biens :", error);
      }
    };
    
    fetchBiens();
  }, []);
  

  // Fonction pour ajouter un bien et afficher la popup
  const handleAddBien = async () => {
    try {
      const newBien = {
        title: `Bien ${biens.length + 1}`,
        description: "Description pour un nouveau bien.",
        prix: 1200,
        address: "564 Avenue Gaston Berger",
        // Ajoutez d'autres propriétés requises par l'API ici
      };
      
      const createdBien = await createRental(newBien);
      setBiens([...biens, createdBien]);  // Ajouter le bien à la liste locale
      setIsModalVisible(false);
      Alert.alert('Succès', 'Le bien a été ajouté.');
    } catch (error) {
      console.error("Erreur lors de l'ajout du bien :", error);
      Alert.alert('Erreur', 'Impossible d\'ajouter le bien.');
    }
  };
  
const closeModal = () => {
  setIsModalVisible(false);
  setSelectedBien(null
  );
};


const handleSave = async () => {
  if (selectedBien) {
    try {
      await updateRental(selectedBien.id, selectedBien);
      setBiens(biens.map(bien => bien.id === selectedBien.id ? selectedBien : bien));
      Alert.alert('Succès', 'Le bien a été mis à jour.');
      closeModal();
    } catch (error) {
      console.error("Erreur lors de la mise à jour du bien :", error);
      Alert.alert('Erreur', 'Impossible de mettre à jour le bien.');
    }
  } else {
    Alert.alert('Erreur', 'Aucun bien sélectionné pour mise à jour.');
  }
};

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
          {biens.map((bien) => (
    <View key={bien.id}>
      <Bien {...bien} />
      <TouchableOpacity onPress={() => handleDeleteBien(bien.id)} style={styles.deleteButton}>
        <Text style={styles.buttonText}>Supprimer</Text>
      </TouchableOpacity>
    </View>
  ))}
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
              <><DetailBien title={''} description={''} prix={0} pays={''} ville={''} codePostal={0} surfaceHabitable={0} surfaceTerrain={0} orientation={''} vue={''} estimate={''} />
            </>
            ) : (
              <Text>Aucun bien sélectionné</Text> // Message d'erreur si selectedBien est null
            )}
            <View style={styles.buttonSaveAndClose}>
              <TouchableOpacity onPress={handleSave} style={styles.saveButton}>
                <Text style={styles.buttonText}>Enregistrer</Text>
              </TouchableOpacity>
              <TouchableOpacity onPress={closeModal} style={styles.closeButton}>
                <Text style={styles.buttonText}>Annuler</Text>
              </TouchableOpacity>
            </View>
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
  buttonSaveAndClose: {
    marginTop: 20,
    flexDirection: 'row',
    justifyContent: 'space-between',
    width: '100%',
    paddingHorizontal: 20,
  },
  saveButton: {
    backgroundColor: '#28a745',
    padding: 10,
    borderRadius: 5,
    width: '48%',
    alignItems: 'center',
  },
  closeButton: {
    backgroundColor: '#dc3545',
    padding: 10,
    borderRadius: 5,
    width: '48%',
    alignItems: 'center',
  },
  buttonText: {
    color: '#fff',
    fontSize: 16,
  },
  deleteButton: {
    backgroundColor: '#dc3545',
    padding: 8,
    borderRadius: 5,
    marginTop: 5,
    alignItems: 'center',
  },
});
