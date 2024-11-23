import React from 'react';
import { TouchableOpacity, Text, StyleSheet, View } from 'react-native';
import { Feather, MaterialIcons } from '@expo/vector-icons';

interface ButtonSaveCancelProps {
  onSave: () => void;
  onCancel: () => void;
  saveText?: string; // Texte personnalisable pour le bouton de sauvegarde
  cancelText?: string; // Texte personnalisable pour le bouton d'annulation
}

const ButtonSaveCancel: React.FC<ButtonSaveCancelProps> = ({
  onSave,
  onCancel,
  saveText = 'Enregistrer', // Valeurs par défaut
  cancelText = 'Annuler',
}) => {
  return (
    <View style={styles.buttonSupAndUpdate}>
      {/* Bouton Enregistrer */}
      <TouchableOpacity onPress={onSave} style={styles.button}>
        <Text style={styles.buttonText}>{saveText}</Text>
        <Feather name="save" size={24} color="white" />
      </TouchableOpacity>

      {/* Bouton Annuler */}
      <TouchableOpacity onPress={onCancel} style={styles.button}>
        <Text style={styles.buttonText}>{cancelText}</Text>
        <MaterialIcons name="cancel" size={24} color="white" />
      </TouchableOpacity>
    </View>
  );
};

export default ButtonSaveCancel;

// Styles
const styles = StyleSheet.create({
  button: {
    backgroundColor: '#007BFF',
    paddingVertical: 12,
    borderRadius: 25, // Bord arrondi pour un design moderne
    marginVertical: 15,
    marginHorizontal: 30, // Espacement latéral pour une meilleure mise en page
    width: '30%', // Taille réduite pour un alignement propre
    alignSelf: 'center', // Centrage automatique
    shadowColor: '#007BFF',
    shadowOffset: { width: 0, height: 4 },
    shadowOpacity: 0.3,
    shadowRadius: 6,
    alignItems: 'center',
    justifyContent: 'center',
    flexDirection: 'row', // Ajout de la direction pour inclure des icônes
  },
  buttonSupAndUpdate: {
    flexDirection: 'row', // Affichage côte à côte
    justifyContent: 'center', // Centrer les boutons
    flexWrap: 'wrap', // Gestion de l'espace pour des boutons sur plusieurs lignes si nécessaire
  },
  buttonText: {
    color: '#fff',
    fontSize: 16,
    fontWeight: '600',
    textAlign: 'center',
    letterSpacing: 0.5, // Espacement des lettres pour une meilleure lisibilité
    marginRight: 8, // Espacement entre le texte et les icônes
  },
});
