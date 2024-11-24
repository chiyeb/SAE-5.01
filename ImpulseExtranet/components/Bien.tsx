import React, { useEffect, useState } from 'react';
import { View, Text, StyleSheet, ImageBackground, TouchableOpacity, Modal, ScrollView } from 'react-native';
import { ThemedText } from './ThemedText';
import { getUserInfo } from '@/components/LoginRequest'; // Assurez-vous que le chemin est correct

interface BienProps {
  type: string;
  title: string;
  description: string;
  price: number;
  location: {
    address: string;
    city: string;
    postalCode: string;
    country: string;
    latitude: number;
    longitude: number;
  };
  imageUri: string;
  visites: number;
  clics: number;
  appels: number;
  livingArea: number;
  landArea: number;
  orientation: string;
  view: string;
  estimationCostEnergy: string;
  rooms: { roomType: string; count: number }[];
  energyClass: string;
  climateClass: string;
}

export default function Bien({
  type,
  title,
  description,
  price,
  location,
  imageUri,
  visites,
  clics,
  appels,
  livingArea,
  landArea,
  orientation,
  view,
  estimationCostEnergy,
  rooms = [],
  energyClass,
  climateClass,
}: BienProps) {
  const [isModalVisible, setIsModalVisible] = useState(false);
  const [userInfo, setUserInfo] = useState<any>(null); // Stocker les informations utilisateur
  const [error, setError] = useState<string | null>(null); // Stocker les erreurs


  const showBienDetails = () => {
    setIsModalVisible(true);
  };

  const closeModal = () => {
    setIsModalVisible(false);
  };

  const renderRooms = () => {
    if (rooms.length > 0) {
      return rooms.map((room, index) => (
        <Text key={index} style={styles.modalText}>
          {room.roomType.charAt(0).toUpperCase() + room.roomType.slice(1)}: {room.count} {room.count > 1 ? 'pièces' : 'pièce'}
        </Text>
      ));
    } else {
      return <Text style={styles.modalText}>Aucune pièce ajoutée.</Text>;
    }
  };

  // Utiliser useEffect pour récupérer les infos utilisateur dès que le composant est monté
  useEffect(() => {
    const fetchUserData = async () => {
      const data = await getUserInfo(); // Récupérer les infos de l'utilisateur
      if (data) {
        setUserInfo(data); // Stocker les informations utilisateur dans l'état
      } else {
        setError('Erreur lors de la récupération des informations utilisateur. Vous devez être connecté ');
      }
    };

    fetchUserData(); // Appeler la fonction pour récupérer les données utilisateur
  }, []); // Se déclencher uniquement au montage du composant

  if (error) {
    return (
      <View style={styles.container}>
        <Text >{error}</Text>
      </View>
    );
  }

  if (!userInfo) {
    return (
      <View style={styles.container}>
        <Text >Chargement des informations...</Text>
      </View>
    );
  }

  return (
    <TouchableOpacity onPress={showBienDetails} style={styles.container}>
      <ImageBackground
        source={require('@/assets/images/Maison-Tassin-BD-1.jpg')}
        style={styles.image}
        imageStyle={styles.imageStyle}
      >
        <View style={styles.overlay} />
      </ImageBackground>
      <View style={styles.textContainer}>
        <Text style={styles.title}>{title}</Text>
        <Text style={styles.description}>{type}</Text>
        <Text style={styles.location}>{location?.address}</Text>
        <ThemedText type="defaultSemiBold" style={styles.price}>{price} €</ThemedText>
        <ThemedText type="defaultSemiBold" style={styles.description}>{userInfo.name} {userInfo.lastname}</ThemedText>
        
        <Text style={styles.description}>{description}</Text>
      </View>
      <View style={styles.infoContainer}>
        <ThemedText type="subtitle" style={styles.infoTitle}>Informations</ThemedText>
        <ThemedText type="infoText">Visites: {visites}</ThemedText>
        <ThemedText type="infoText">Clics: {clics}</ThemedText>
        <ThemedText type="infoText">Appels: {appels}</ThemedText>
      </View>

      {/* Modal pour afficher les détails du bien */}
      <Modal visible={isModalVisible} animationType="slide" transparent={true} onRequestClose={closeModal}>
        <View style={styles.modalContainer}>
          <ScrollView showsHorizontalScrollIndicator={false}>
            <View style={styles.modalContent}>
              <ThemedText type="title" style={styles.modalTitle}>{title}</ThemedText>
              <Text style={styles.modalText}><ThemedText type="defaultSemiBold">Type: </ThemedText>{type}</Text>
              <Text style={styles.modalText}>{description}</Text>
              <Text style={styles.modalText}><ThemedText type="defaultSemiBold">Prix: </ThemedText>{price} €</Text>
              <Text style={styles.modalText}><ThemedText type="defaultSemiBold">Adresse: </ThemedText>{location?.address}</Text>
              <Text style={styles.modalText}><ThemedText type="defaultSemiBold">Pays: </ThemedText>{location?.country}</Text>
              <Text style={styles.modalText}><ThemedText type="defaultSemiBold">Ville: </ThemedText>{location?.city}</Text>
              <Text style={styles.modalText}><ThemedText type="defaultSemiBold">Code Postal: </ThemedText>{location?.postalCode}</Text>
              <Text style={styles.modalText}><ThemedText type="defaultSemiBold">Surface Habitable: </ThemedText>{livingArea} m²</Text>
              <Text style={styles.modalText}><ThemedText type="defaultSemiBold">Surface Terrain: </ThemedText>{landArea} m²</Text>
              <ThemedText type="defaultSemiBold">Pièces: </ThemedText>{renderRooms()}
              <Text style={styles.modalText}><ThemedText type="defaultSemiBold">Orientation: </ThemedText>{orientation}</Text>
              <Text style={styles.modalText}><ThemedText type="defaultSemiBold">Vue: </ThemedText>{view}</Text>
              <Text style={styles.modalText}><ThemedText type="defaultSemiBold">Estimation des coûts annuels d'énergie: </ThemedText>{estimationCostEnergy} €</Text>
              <Text style={styles.modalText}><ThemedText type="defaultSemiBold">Performance énergétique: </ThemedText>{energyClass}</Text>
              <Text style={styles.modalText}><ThemedText type="defaultSemiBold">Classe climat: </ThemedText>{climateClass}</Text>
              <Text style={styles.modalText}><ThemedText type="defaultSemiBold">Propriétaire: </ThemedText>{userInfo.name} {userInfo.lastname}</Text>
              

              <TouchableOpacity onPress={closeModal} style={styles.buttonClose}>
                <Text style={styles.buttonText}>Fermer</Text>
              </TouchableOpacity>
            </View>
          </ScrollView>
        </View>
      </Modal>
    </TouchableOpacity>
  );
}

const styles = StyleSheet.create({
  container: {
    flexDirection: 'row',
    alignItems: 'center',
    padding: 15,
    borderRadius: 12,
    backgroundColor: '#f8f9fa',
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 3 },
    shadowOpacity: 0.1,
    shadowRadius: 5,
    elevation: 5,
    margin: 10,
    overflow: 'hidden',
    width: 700,
  },

  image: {
    width: 150,
    height: 100,
    borderRadius: 10,
    justifyContent: 'flex-end',
  },

  imageStyle: {
    borderRadius: 10,
  },

  overlay: {
    position: 'absolute',
    bottom: 0,
    left: 0,
    right: 0,
    height: '50%',
    backgroundColor: 'rgba(0, 0, 0, 0.3)',
    borderRadius: 10,
  },

  textContainer: {
    flex: 1,
    paddingLeft: 15,
  },

  title: {
    fontSize: 24,
    fontWeight: '700',
    color: '#333',
    marginBottom: 5,
  },

  description: {
    fontSize: 14,
    color: '#555',
    marginVertical: 5,
  },

  location: {
    fontSize: 12,
    color: '#666',
    marginVertical: 5,
  },

  price: {
    fontSize: 18,
    fontWeight: '700',
    color: '#007BFF',
  },

  infoContainer: {
    padding: 15,
    marginTop: 10,
    backgroundColor: '#f1f1f1',
    borderRadius: 8,
  },

  infoTitle: {
    fontSize: 16,
    fontWeight: '600',
    color: '#007BFF',
    marginBottom: 10,
  },

  modalContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: 'rgba(0, 0, 0, 0.7)',
  },

  modalContent: {
    backgroundColor: '#fff',
    borderRadius: 20,
    paddingVertical: 20,
    paddingHorizontal: 30,
    marginLeft:100,
    width: '80%',
    height:'95%',
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 3 },
    shadowOpacity: 0.2,
    shadowRadius: 10,
    elevation: 15,
  },

  modalTitle: {
    fontSize: 22,
    fontWeight: '700',
    color: '#333',
    textAlign: 'center',
    marginBottom: 15,
  },

  modalText: {
    fontSize: 16,
    color: '#555',
    marginBottom: 8,
  },

  buttonClose: {
    backgroundColor: '#007BFF',
    paddingVertical: 12,
    paddingHorizontal: 20,
    borderRadius: 30,
    marginTop: 20,
    alignSelf: 'center',
  },

  buttonText: {
    color: '#fff',
    fontSize: 18,
    fontWeight: '600',
    textAlign: 'center',
  },
});
