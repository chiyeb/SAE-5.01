import React from 'react';
import { TouchableOpacity, Text, StyleSheet, ViewStyle, TextStyle } from 'react-native';
import LinearGradient from 'react-native-linear-gradient';

interface ThemedButtonProps {
  title: string;
  onPress: () => void;
  style?: ViewStyle; // Optionnel : pour personnaliser le style externe
  textStyle?: TextStyle; // Optionnel : pour personnaliser le style du texte
}

const ThemedButton: React.FC<ThemedButtonProps> = ({ title, onPress, style, textStyle }) => {
  return (
    <TouchableOpacity 
      style={[styles.buttonContainer, style]} 
      onPress={onPress} 
      activeOpacity={0.8} // Effet visuel lors du clic
      accessible={true} // Pour l'accessibilité
      accessibilityLabel={title} // Pour l'accessibilité, utiliser le texte comme étiquette
    >
      <LinearGradient
        colors={['#43CBFF', '#9708CC']} // Dégradé de couleur
        start={{ x: 0, y: 0 }} // Départ du dégradé (en haut à gauche)
        end={{ x: 1, y: 1 }} // Fin du dégradé (en bas à droite)
        style={styles.gradient}
      >
        <Text style={[styles.buttonText, textStyle]}>{title}</Text>
      </LinearGradient>
    </TouchableOpacity>
  );
};

export default ThemedButton;

const styles = StyleSheet.create({
  buttonContainer: {
    borderRadius: 8, // Coins arrondis pour le bouton
    overflow: 'hidden', // Empêche le débordement du dégradé
    shadowColor: '#000', // Ombre noire
    shadowOffset: { width: 0, height: 4 }, // Ombre sous le bouton
    shadowOpacity: 0.1, // Opacité de l'ombre
    shadowRadius: 6, // Rayon de l'ombre
    elevation: 5, // Pour Android (ajoute de la profondeur)
  },
  gradient: {
    paddingVertical: 12, // Hauteur du bouton
    paddingHorizontal: 20, // Largeur intérieure
    justifyContent: 'center',
    alignItems: 'center',
    borderRadius: 8, // Assure la forme arrondie du dégradé
  },
  buttonText: {
    color: '#fff', // Texte blanc
    fontSize: 16,
    fontWeight: '600', // Texte semi-gras
  },
});
