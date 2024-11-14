import { launchImageLibrary } from 'react-native-image-picker';
import { Alert } from 'react-native';

export const pickImage = (setPhoto: (uri: string) => void) => {
  launchImageLibrary(
    {
      mediaType: 'photo',
      quality: 1,
      includeBase64: false,
    },
    response => {
      if (response.didCancel) {
        console.log("L'utilisateur a annulé la sélection de l'image.");
      } else if (response.errorCode) {
        Alert.alert('Erreur', response.errorMessage || 'Erreur inconnue');
      } else if (response.assets && response.assets.length > 0) {
        const { uri } = response.assets[0];
        if (uri) {
          setPhoto(uri); // Mise à jour de l'image
        }
      } else {
        setPhoto(''); // Réinitialiser si aucune image n'est sélectionnée
      }
    }
  );
};
