import React, { useState } from 'react';
import { View, Text, StyleSheet, ImageBackground, TouchableOpacity, Modal } from 'react-native';
import { ThemedText } from './ThemedText';

interface BienProps {
  type: string;
  title: string;
  description: string;
  price: number;
  location: {  // L'adresse se trouve maintenant dans l'objet location
    address: string;
    city: string;
    postalCode: string;
    country: string;
    latitude: number;
    longitude: number;
  };
  imageUri: string;  // Ajout d'une prop pour l'image dynamique
  visites: number;
  clics: number;
  appels: number;
  livingArea: number;
  landArea: number;
  orientation: string;
  view: string;
  estimationCostEnergy: string;  // Estimation des coûts annuels d'énergie
  rooms: { roomType: string; count: number }[];  // Liste des pièces et leur nombre
  energyClass:string,
  climateClass:string,
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
  rooms = [], // Valeur par défaut pour rooms (vide si non fourni)
  energyClass,
  climateClass
}: BienProps) {
  const [isModalVisible, setIsModalVisible] = useState(false);

  // Fonction pour afficher le Modal
  const showBienDetails = () => {
    setIsModalVisible(true);
  };

  // Fonction pour fermer le Modal
  const closeModal = () => {
    setIsModalVisible(false);
  };

  // Fonction pour afficher les pièces dans une liste
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

  return (
    <TouchableOpacity onPress={showBienDetails} style={styles.container}>
      <ImageBackground
        source={{ uri: imageUri }}
        style={styles.image}
        imageStyle={styles.imageStyle}
      />
      <View style={styles.textContainer}>
        <Text style={styles.title}>{title}</Text>
        <Text style={styles.description}>{type}</Text>
        <Text style={styles.description}>{location?.address}</Text>
        <ThemedText type="defaultSemiBold">{price} €</ThemedText>
        <Text style={styles.description}>{description}</Text>
      </View>
      <View style={styles.infoContainer}>
        <ThemedText type="subtitle">Informations</ThemedText>
        <ThemedText type="infoText">Nombre de visites: {visites}</ThemedText>
        <ThemedText type="infoText">Nombre de clics: {clics}</ThemedText>
        <ThemedText type="infoText">Nombre d'appels: {appels}</ThemedText>
      </View>

      {/* Modal pour afficher les détails du bien */}
      <Modal
        visible={isModalVisible}
        animationType="slide"
        transparent={true}
        onRequestClose={closeModal}
      >
        <View style={styles.modalContainer}>
          <View style={styles.modalContent}>
            <Text style={styles.modalTitle}>{title}</Text>
            <Text style={styles.modalText}>{type}</Text>
            <Text style={styles.modalText}>{description}</Text>
            <Text style={styles.modalText}>Prix: {price} €</Text>
            <Text style={styles.modalText}>Adresse: {location?.address}</Text>
            <Text style={styles.modalText}>Pays: {location?.country}</Text>
            <Text style={styles.modalText}>Ville: {location?.city}</Text>
            <Text style={styles.modalText}>Code Postal: {location?.postalCode}</Text>
            <Text style={styles.modalText}>Surface Habitable: {livingArea} m²</Text>
            <Text style={styles.modalText}>Surface Terrain: {landArea} m²</Text>
            {/* Affichage des pièces */}
            
              <ThemedText type="defaultSemiBold">Pièces : </ThemedText>{renderRooms()}  
            
            <Text style={styles.modalText}>Orientation: {orientation}</Text>
            <Text style={styles.modalText}>Vue: {view}</Text>
            <Text style={styles.modalText}>Estimation des coûts annuels d'énergie: {estimationCostEnergy} €</Text>
            <Text style={styles.modalText}>Performance énergétique: {energyClass} </Text>
            <Text style={styles.modalText}>Classe climat : {climateClass} </Text>

            <TouchableOpacity onPress={closeModal} style={styles.buttonClose}>
              <Text style={styles.buttonText}>Fermer</Text>
            </TouchableOpacity>
          </View>
        </View>
      </Modal>
    </TouchableOpacity>
  );
}

const styles = StyleSheet.create({
  container: {
    flexDirection: 'row',
    alignItems: 'center',
    padding: 10,
    borderRadius: 10,
    overflow: 'hidden',
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.3,
    shadowRadius: 4,
    elevation: 5,
    backgroundColor: '#fff',
    width: '80%',
    margin: 10,
  },
  image: {
    width: 300,
    height: 220,
    borderRadius: 10,
  },
  imageStyle: {
    borderRadius: 10,
  },
  textContainer: {
    flex: 1,
    paddingLeft: 10,
  },
  title: {
    fontSize: 32,
    fontWeight: 'bold',
    color: 'black',
    textAlign: 'left',
    paddingBottom: 10,
  },
  description: {
    fontSize: 14,
    color: 'black',
    textAlign: 'left',
    paddingBottom: 10,
    paddingTop: 10,
  },
  infoContainer: {
    marginTop: 10,
    paddingLeft: 10,
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
    width: '80%',
    alignItems: 'center',
  },
  modalTitle: {
    fontSize: 24,
    fontWeight: 'bold',
    marginBottom: 10,
  },
  modalText: {
    fontSize: 16,
    marginBottom: 10,
  },
  buttonClose: {
    backgroundColor: '#007BFF',
    padding: 10,
    borderRadius: 5,
    marginTop: 20,
  },
  buttonText: {
    color: '#fff',
    fontSize: 16,
  },
});
