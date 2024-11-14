import React, { useState, useEffect } from 'react';
import { View, ScrollView, StyleSheet, Modal, Text, TouchableOpacity, Alert } from 'react-native';
import Bien from '@/components/Bien';
import { ThemedText } from '@/components/ThemedText';
import Buttons from '@/components/navigation/Buttons';
import Profil from '@/components/Profil';
import DetailBien from '@/components/DetailBien';
import { getAllRentals, createRental, updateRental, deleteRental } from '@/components/Api';

export default function HomeScreen() {
  const [biens, setBiens] = useState([]); // Initialiser biens avec un tableau vide
  const [isModalVisible, setIsModalVisible] = useState(false);
  const [selectedBien, setSelectedBien] = useState(null);

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

  // Fonction pour ajouter un bien et afficher la popup
  const handleAddBien = async () => {
    const newBien = {
      title: `Bien ${biens.length + 1}`,
      description: "Description pour un nouveau bien.",
      prix: 1200,
      address: "564 Avenue Gaston Berger",
    };

    try {
      const createdBien = await createRental(newBien);
      setBiens([...biens, createdBien]);  // Ajouter le bien à la liste locale
      setIsModalVisible(false);
      Alert.alert('Succès', 'Le bien a été ajouté.');
    } catch (error) {
      console.error("Erreur lors de l'ajout du bien :", error);
      Alert.alert('Erreur', 'Impossible d\'ajouter le bien.');
    }
  };

  // Fermer le modal
  const closeModal = () => {
    setIsModalVisible(false);
    setSelectedBien(null);  // Remise à zéro de selectedBien
  };

  // Fonction de sauvegarde des modifications du bien sélectionné
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
                prix={selectedBien.prix} 
                pays={selectedBien.pays} 
                ville={selectedBien.ville} 
                codePostal={selectedBien.codePostal} 
                surfaceHabitable={selectedBien.surfaceHabitable} 
                surfaceTerrain={selectedBien.surfaceTerrain} 
                orientation={selectedBien.orientation} 
                vue={selectedBien.vue} 
                estimate={selectedBien.estimate} 
              />
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
