import React, { useState } from 'react';
import { View, Text, StyleSheet, ImageBackground, TouchableOpacity, Modal } from 'react-native';
import { ThemedText } from './ThemedText';

// Définition du type pour les props, avec les nouvelles variables ajoutées
interface BienProps {
  title: string;
  description: string;
  prix: number;
  adress: string;
  imageUri: string;  // Ajout d'une prop pour l'image dynamique
  visites: number;
  clics: number;
  appels: number;
  pays: string;
  ville: string;
  codePostal: number;
  surfaceHabitable: number;
  surfaceTerrain: number;
  orientation: string;
  vue: string;
  estimate: string;  // Estimation des coûts annuels d'énergie
}

export default function Bien({
  title,
  description,
  prix,
  adress,
  imageUri,
  visites,
  clics,
  appels,
  pays,
  ville,
  codePostal,
  surfaceHabitable,
  surfaceTerrain,
  orientation,
  vue,
  estimate,
}: BienProps) {
  // État pour contrôler la visibilité du Modal
  const [isModalVisible, setIsModalVisible] = useState(false);

  // Fonction pour afficher le Modal
  const showBienDetails = () => {
    setIsModalVisible(true);
  };

  // Fonction pour fermer le Modal
  const closeModal = () => {
    setIsModalVisible(false);
  };

  return (
    <TouchableOpacity onPress={showBienDetails} style={styles.container}>
      <ImageBackground
        source={{uri: ''}}
        style={styles.image}
        imageStyle={styles.imageStyle}
      />
      <View style={styles.textContainer}>
        <Text style={styles.title}>{title}</Text>
        <Text style={styles.description}>{adress}</Text>
        <ThemedText type="defaultSemiBold">{prix} €</ThemedText>
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
            <Text style={styles.modalText}>{description}</Text>
            <Text style={styles.modalText}>Prix: {prix} €</Text>
            <Text style={styles.modalText}>Adresse: {adress}</Text>
            <Text style={styles.modalText}>Pays: {pays}</Text>
            <Text style={styles.modalText}>Ville: {ville}</Text>
            <Text style={styles.modalText}>Code Postal: {codePostal}</Text>
            <Text style={styles.modalText}>Surface Habitable: {surfaceHabitable} m²</Text>
            <Text style={styles.modalText}>Surface Terrain: {surfaceTerrain} m²</Text>
            <Text style={styles.modalText}>Orientation: {orientation}</Text>
            <Text style={styles.modalText}>Vue: {vue}</Text>
            <Text style={styles.modalText}>Estimation des coûts annuels d'énergie: {estimate} €</Text>

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
  // Modal styles
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
