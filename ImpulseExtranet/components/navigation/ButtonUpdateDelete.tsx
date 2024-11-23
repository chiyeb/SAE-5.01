import React from 'react';
import { TouchableOpacity, Text, StyleSheet, View } from 'react-native';
import { MaterialIcons, AntDesign } from '@expo/vector-icons';

interface ButtonUpdateDeleteProps {
  onUpdate: () => void;
  onDelete: () => void;
  updateText?: string; // Texte personnalisable pour le bouton de mise à jour
  deleteText?: string; // Texte personnalisable pour le bouton de suppression
}

const ButtonUpdateDelete: React.FC<ButtonUpdateDeleteProps> = ({
  onUpdate,
  onDelete,
  updateText = 'Modifier', // Texte par défaut
  deleteText = 'Supprimer',
}) => {
  return (
    <View style={styles.buttonSupAndUpdate}>
      {/* Bouton Modifier */}
      <TouchableOpacity onPress={onUpdate} style={styles.updateButton}>
        <MaterialIcons name="update" size={24} color="white" />
        <Text style={styles.buttonText}>{updateText}</Text>
      </TouchableOpacity>

      {/* Bouton Supprimer */}
      <TouchableOpacity onPress={onDelete} style={styles.deleteButton}>
        <AntDesign name="delete" size={24} color="white" />
        <Text style={styles.buttonText}>{deleteText}</Text>
      </TouchableOpacity>
    </View>
  );
};

export default ButtonUpdateDelete;

// Styles
const styles = StyleSheet.create({
  buttonSupAndUpdate: {
    flexDirection: 'row', // Les boutons sont alignés côte à côte
    justifyContent: 'center', // Centrer les boutons
    flexWrap: 'wrap', // Gestion de l'espace pour plusieurs lignes si nécessaire
  },
  buttonText: {
    color: '#fff',
    fontSize: 16,
    textAlign: 'center',
    marginLeft: 8, // Espacement entre le texte et l'icône
  },
  deleteButton: {
    backgroundColor: '#dc3545', // Couleur rouge pour le bouton Supprimer
    padding: 10,
    marginHorizontal: 10,
    borderRadius: 5,
    width: '30%',
    alignItems: 'center',
    justifyContent: 'center',
    flexDirection: 'row',
  },
  updateButton: {
    backgroundColor: '#9CCC65', // Couleur verte pour le bouton Modifier
    padding: 10,
    marginHorizontal: 10,
    borderRadius: 5,
    width: '30%',
    alignItems: 'center',
    justifyContent: 'center',
    flexDirection: 'row',
  },
});
