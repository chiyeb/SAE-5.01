import React, { useState, useEffect } from 'react';
import { View, ScrollView, StyleSheet, Modal, Text, TouchableOpacity, Alert, Animated } from 'react-native';
import Bien from '@/components/Bien';
import { ThemedText } from '@/components/ThemedText';
import Buttons from '@/components/navigation/Buttons';
import TabSelector from '@/components/navigation/ButtonVenteLocation'; 
import DetailBien from '@/components/DetailBien';
import { createProperty, getAllProperties, updateProperty, deleteProperty } from '@/components/Api';
import AsyncStorage from '@react-native-async-storage/async-storage';

export default function HomeScreen() {
  const [biens, setBiens] = useState<any[]>([]);
  const [isModalVisible, setIsModalVisible] = useState(false);
  const [isModalUpdate, setIsModalUpdate] = useState(false);
  const [selectedBien, setSelectedBien] = useState<any | null>(null);
  const [selectedTab, setSelectedTab] = useState('purchasable');
  const [token, setToken] = useState<string | null>(null);  // Stocker le token ici
  const scrollY = new Animated.Value(0); // Suivi du défilement

  useEffect(() => {
    // Récupérer le token depuis AsyncStorage
    const fetchToken = async () => {
      const storedToken = await AsyncStorage.getItem('auth_token');
      setToken(storedToken);
    };

    fetchToken();
  }, []);

  useEffect(() => {
    if (token) {
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
    }
  }, [selectedTab]);

  const handleAddBien = async () => {
    if (!token) {
      Alert.alert('Erreur', 'Token d\'authentification manquant.');
      return;
    }
    
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
      ...(selectedTab === 'rental' && { subscriptionFrequency: "WEEKLY" }),
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
    if (!token) {
      Alert.alert('Erreur', 'Token d\'authentification manquant.');
      return;
    }

    try {
      const createdBien = await createProperty(bienData, selectedTab, token);
      setBiens([...biens, createdBien]);
      Alert.alert('Succès', 'Le bien a été ajouté.');
      closeModal();
    } catch (error) {
      console.error('Erreur lors de l\'ajout du bien :', error);
      Alert.alert('Erreur', 'Impossible d\'ajouter le bien.');
    }
  };

  const openModalForUpdate = (id: string, bienData: any) => {
    setSelectedBien(bienData);
    setIsModalUpdate(true);
  };

  const handleUpdateBien = async (bienData: any) => {
    if (!bienData || !bienData.id) {
      console.error('ID du bien est manquant', bienData);
      return;
    }
    if (!token) {
      Alert.alert('Erreur', 'Token d\'authentification manquant.');
      return;
    }

    try {
      const updatedBien = await updateProperty(bienData.id, bienData, selectedTab, token);
      setBiens(prevBiens =>
        prevBiens.map(bien => (bien.id === bienData.id ? updatedBien : bien))
      );
      Alert.alert('Succès', 'Le bien a été mis à jour.');
    } catch (error) {
      console.error('Erreur lors de la mise à jour', error);
      Alert.alert('Erreur', 'Impossible de mettre à jour le bien.');
    }
    closeModal();
  };

  const handleDeleteBien = async (id: string) => {
    if (!token) {
      Alert.alert('Erreur', 'Token d\'authentification manquant.');
      return;
    }
    try {
      await deleteProperty(id, selectedTab, token);
      setBiens(biens.filter((bien) => bien.id !== id));
      Alert.alert('Succès', 'Le bien a été supprimé.');
    } catch (error) {
      console.error('Erreur lors de la suppression :', error);
      Alert.alert('Erreur', 'Impossible de supprimer le bien.');
    }
  };
  // Couleur d'arrière-plan qui change en fonction du défilement
  const getBackgroundColor = scrollY.interpolate({
    inputRange: [0, 200],  // Plage de défilement
    outputRange: ['#f5e6ab', '#dba617'],  // Couleurs de fond
    extrapolate: 'clamp',  // Empêche l'extrapolation
  });

  return (
    <View style={styles.container}>
      <Animated.View style={[styles.background, { backgroundColor: getBackgroundColor }]}>
        <ScrollView
          contentContainerStyle={{ paddingBottom: 50 }}
          onScroll={Animated.event(
            [{ nativeEvent: { contentOffset: { y: scrollY } } }],
            { useNativeDriver: false }
          )}
          scrollEventThrottle={16}
        >
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
                  <View style={styles.buttonSupAndUpdate}>
                  <TouchableOpacity onPress={() => openModalForUpdate(bien.id, bien)} style={styles.updateButton}>
                    <Text style={styles.buttonText}>Modifier</Text>
                  </TouchableOpacity>

                  <TouchableOpacity onPress={() => handleDeleteBien(bien.id)} style={styles.deleteButton}>
                    <Text style={styles.buttonText}>Supprimer</Text>
                  </TouchableOpacity>
                  </View>
                </View>
              ))
            ) : (
              <Text>Aucun bien trouvé</Text>
            )}
            <Buttons onPress={handleAddBien} />
          </View>
        </ScrollView>
      </Animated.View>

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
                onSaveBien={handleUpdateBien}
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
    alignItems: 'center',
  },
  titleContainer: {
    alignItems: 'center',
    margin: 16,
  },
  stepContainer: {
    alignItems: 'center',
    marginBottom: 16,
  },
  modalContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: 'rgba(0, 0, 0, 0.5)',
  },
  modalContent: {
    backgroundColor: 'white',
    borderRadius: 10,
    padding: 20,
    width: '90%',
    height: '90%',
    alignItems: 'center',
  },
  background: {
    flex: 1,
    width: '100%',
  },
  buttonText: {
    color: '#fff',
    fontSize: 16,
    textAlign: 'center',
  },
  buttonSupAndUpdate:{
    flexDirection:'row',
    justifyContent:'center',
    flexWrap:'wrap'
  },
  deleteButton: {
    backgroundColor: '#dc3545',
    padding: 10,
    marginHorizontal:10,
    borderRadius: 5,
    width: '30%',
    alignSelf: 'center',
  },
  updateButton: {
    backgroundColor: '#9CCC65',
    padding: 10,
    marginHorizontal:10,
    borderRadius: 5,
    width: '30%',
    alignSelf: 'center',
  },
});
