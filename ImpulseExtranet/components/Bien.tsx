import React, { useState } from 'react';
import { View, Text, StyleSheet, ImageBackground, TouchableOpacity, Modal, ScrollView } from 'react-native';
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
    latitude: number; // Utiliser "number" au lieu de "Float"
  longitude: number; // Utiliser "number" au lieu de "Float"
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
        source={require('@/assets/images/Maison-Tassin-BD-1.jpg' )}
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
          <ScrollView showsHorizontalScrollIndicator={false}>
          <View style={styles.modalContent}>
            <ThemedText type='title' style={styles.modalTitle}>{title}</ThemedText>
            <Text style={styles.modalText}><ThemedText type='defaultSemiBold'> Type: </ThemedText>{type}</Text>
            <Text style={styles.modalText}>{description}</Text>
            <Text style={styles.modalText}><ThemedText type='defaultSemiBold'> Prix: </ThemedText>{price} €</Text>
            <Text style={styles.modalText}><ThemedText type='defaultSemiBold'>Adresse: </ThemedText>{location?.address}</Text>
            <Text style={styles.modalText}><ThemedText type='defaultSemiBold'>Pays:</ThemedText> {location?.country}</Text>
            <Text style={styles.modalText}><ThemedText type='defaultSemiBold'>Ville: </ThemedText>{location?.city}</Text>
            <Text style={styles.modalText}><ThemedText type='defaultSemiBold'>Code Postal: </ThemedText>{location?.postalCode}</Text>
            <Text style={styles.modalText}><ThemedText type='defaultSemiBold'>Surface Habitable: </ThemedText>{livingArea} m²</Text>
            <Text style={styles.modalText}><ThemedText type='defaultSemiBold'>Surface Terrain:</ThemedText> {landArea} m²</Text>
            {/* Affichage des pièces */}
            
              <ThemedText type="defaultSemiBold">Pièces : </ThemedText>{renderRooms()}  
            
            <Text style={styles.modalText}><ThemedText type='defaultSemiBold'>Orientation:</ThemedText> {orientation}</Text>
            <Text style={styles.modalText}><ThemedText type='defaultSemiBold'>Vue:</ThemedText>{view}</Text>
            <Text style={styles.modalText}><ThemedText type='defaultSemiBold'>Estimation des coûts annuels d'énergie: </ThemedText>{estimationCostEnergy} €</Text>
            <Text style={styles.modalText}><ThemedText type='defaultSemiBold'>Performance énergétique: </ThemedText>{energyClass} </Text>
            <Text style={styles.modalText}><ThemedText type='defaultSemiBold'>Classe climat : </ThemedText>{climateClass} </Text>

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
  // Styles du conteneur principal
  container: {
    flexDirection: 'row',
    alignItems: 'center',
    padding: 10,
    borderRadius: 10,
    backgroundColor: '#fff',
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.3,
    shadowRadius: 4,
    elevation: 5,
    width: 800,
    margin: 10,
    overflow: 'hidden',
  },

  // Styles de l'image
  image: {
    width: 300,
    height: 220,
    borderRadius: 10,
  },
  imageStyle: {
    borderRadius: 10,
  },

  // Styles du conteneur de texte
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
    paddingVertical: 10,
  },

  // Styles du conteneur d'informations
  infoContainer: {
    marginTop: 10,
    paddingLeft: 10,
  },

  // Styles du conteneur modal
  modalContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: 'rgba(0, 0, 0, 0.7)', // Fond semi-transparent pour la modal
    paddingHorizontal: 20,
  },
  
  modalContent: {
    backgroundColor: '#fff', // Fond blanc pour la modal
    borderRadius: 20,
    paddingVertical: 20,
    paddingHorizontal: 30,
    width: 700,
    height:'95%',
    alignItems: 'flex-start',
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.2,
    shadowRadius: 10,
    elevation: 15,
  },

  modalTitle: {
    alignSelf:'center'
  },

  modalSection: {
    marginBottom: 20,
  },

  modalSectionTitle: {
    fontSize: 20,
    fontWeight: '600',
    color: '#007BFF', // Bleu pour les titres de section
    marginBottom: 10,
  },

  modalText: {
    fontSize: 16,
    lineHeight: 24,
    color: '#333', // Texte sombre pour une bonne lisibilité
    marginBottom: 10,
  },

  modalInfo: {
    fontSize: 16,
    color: '#555',
    marginBottom: 5,
  },
// Styles du bouton de fermeture
  buttonClose: {
    backgroundColor: '#007BFF',
    paddingVertical: 12,
    paddingHorizontal: 25,
    borderRadius: 30,
    marginTop: 20,
    alignSelf: 'center',
    elevation: 5,
  },

  buttonText: {
    color: '#fff',
    fontSize: 18,
    fontWeight: 'bold',
    textAlign: 'center',
  },

  modalRow: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    width: '100%',
    marginBottom: 10,
  },

  modalRowText: {
    fontSize: 16,
    color: '#555',
  },

  modalSubText: {
    fontSize: 14,
    color: '#888',
  },

  modalLine: {
    height: 1,
    backgroundColor: '#ddd',
    marginVertical: 15,
    width: '100%',
  },

  
});


