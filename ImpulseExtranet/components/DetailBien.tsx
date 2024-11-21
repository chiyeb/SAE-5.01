import React, { useState } from 'react';
import { View, Text, TextInput, StyleSheet, Image, ScrollView, TouchableOpacity } from 'react-native';
import RNPickerSelect from 'react-native-picker-select';
import NbPiece from './navigation/ButtonNbPiece';
import { pickImage } from './SelectImage';
import { ThemedText } from './ThemedText';
import TabSelector from '@/components/navigation/ButtonVenteLocation';  // Import du composant d'onglet


// Définition du type pour les props
interface Location {
  address: string;
  city: string;
  postalCode: string;
  country: string;
  latitude: number; // Utiliser "number" au lieu de "Float"
  longitude: number; // Utiliser "number" au lieu de "Float"
}

interface DetailProps {
  selectedTab:  'rental';
  title: string;
  description: string;
  price: number;
  location: Location;
  livingArea: number;
  landArea: number;
  images: string,
  orientation: string;
  view: string;
  estimationCostEnergy: string;
  onSaveBien: (bienData: any) => void;
  onDelete: (bienData: any) => void;
}

const Detail: React.FC<DetailProps> = ({
  selectedTab,
  title: initialTitle,
  description: initialDescription,
  price: initialPrice,
  location: initialLocation,
  livingArea: initiallivingArea,
  landArea: initiallandArea,
  orientation: initialOrientation,
  view: initialView,
  estimationCostEnergy: initialestimationCostEnergy,
  onSaveBien,
  onDelete,
}) => {
  // États pour chaque variable
  const [type, setType] = useState<string>(""); // type du bien
  const [title, setTitle] = useState<string>(initialTitle);
  const [description, setDescription] = useState<string>(initialDescription);
  const [price, setPrice] = useState<string>(initialPrice?.toString());
  const [subscriptionFrequency, setSubscriptionFrequency] = useState<string | null>(null);
  const [location, setLocation] = useState<Location>(initialLocation);  // Utilisation de l'objet localisation
  const [image, setImage] = useState<string>("");
  const [livingArea, setLivingArea] = useState<string>(initiallivingArea?.toString() ?? "");
  const [landArea, setLandArea] = useState<string>(initiallandArea?.toString());
  const [orientation, setOrientation] = useState<string>(initialOrientation);
  const [view, setView] = useState<string>(initialView);
  const [energyClass, setEnergyClass] = useState<string>(""); // Classe de performance énergétique
  const [climateClass, setClimateClass] = useState<string>(""); // Classe climat énergétique
  const [estimationCostEnergy, setestimationCostEnergy] = useState<string>(initialestimationCostEnergy);
  
  // In Detail component
const [rooms, setRooms] = useState<{ roomType: string, count: number }[]>([]);

  // État pour gérer l'onglet sélectionné dans la modal

  const getBienData = () => ({
    type,
    title,
    description,
    price: parseFloat(price),
    ...(selectedTab === 'rental' && { subscriptionFrequency: "YEARLY" }), // Ajoute uniquement pour location
    location: {
      address: location.address,
      city: location.city,
      postalCode: location.postalCode,
      country: location.country,
      latitude: location.latitude,  // Assurez-vous que latitude et longitude sont envoyés correctement
      longitude: location.longitude,
    },
    livingArea: parseFloat(livingArea),
    landArea: parseFloat(landArea),
    rooms,
    image,
    orientation,
    view,
    energyClass,
    estimationCostEnergy,
    climateClass,
  });

  const handleSave = () => {
    onSaveBien(getBienData());
    
  };
  const handleDelete = () => {
    // Demander une confirmation avant de supprimer le bien
    onDelete(location.longitude);
  };
    // Initialize the rooms state

    // Function to add a room
    const addRoom = (roomType: string, count: number) => {
      setRooms((prevRooms) => [...prevRooms, { roomType, count }]);
    };
  
    // Function to remove a room
    const removeRoom = (index: number) => {
      setRooms((prevRooms) => prevRooms.filter((_, i) => i !== index));
    };
  
  return (
    <View style={styles.container}>
      <ScrollView>
        <ThemedText type="title">Détails du bien</ThemedText>
        <View style={styles.textContainer}>
          {/* Sélecteur d'onglets */}

          <ThemedText type="defaultSemiBold">Type de bien</ThemedText>
          <RNPickerSelect
            style={pickerSelectStyles}
            onValueChange={setType}
            value={type}
            placeholder={{ label: 'Sélectionnez le type du bien', value: null }}
            items={[
              { label: 'Maison', value: 'HOUSE' },
              { label: 'Appartement', value: 'APARTMENT' },
            ]}
          />

          <ThemedText type="defaultSemiBold">Titre du bien</ThemedText>
          <TextInput style={styles.input} value={title} onChangeText={setTitle} placeholder="Titre" />

          <ThemedText type="defaultSemiBold">Description</ThemedText>
          <TextInput style={styles.input} value={description} onChangeText={setDescription} placeholder="Description" multiline/>

          <ThemedText type="defaultSemiBold">Prix</ThemedText>
          <TextInput style={styles.input} value={price} onChangeText={setPrice} placeholder="Prix" keyboardType="numeric" />
          {selectedTab === 'rental' && (
            <>
              <ThemedText type="defaultSemiBold">Fréquence de souscription</ThemedText>
              <RNPickerSelect
                style={pickerSelectStyles}
                onValueChange={setSubscriptionFrequency}
                value={subscriptionFrequency}
                placeholder={{ label: 'Sélectionnez la fréquence', value: null }}
                items={[
                  { label: 'Hebdomadaire', value: 'WEEKLY' },
                  { label: 'Mensuelle', value: 'MONTHLY' },
                  { label: 'Annuellement', value: 'YEARLY' },
                ]}
              />

            </>
          )}

          {/* Mise à jour de l'affichage de localisation */}
          <ThemedText type="defaultSemiBold">Adresse</ThemedText>
          <TextInput
            style={styles.input}
            value={location?.address}
            onChangeText={(text) => setLocation({ ...location, address: text })}
            placeholder="Adresse"
          />

          <ThemedText type="defaultSemiBold">Ville</ThemedText>
          <TextInput
            style={styles.input}
            value={location?.city}
            onChangeText={(text) => setLocation({ ...location, city: text })}
            placeholder="Ville"
          />

          <ThemedText type="defaultSemiBold">Code Postal</ThemedText>
          <TextInput
            style={styles.input}
            value={location?.postalCode.toString()}
            onChangeText={(text) => setLocation({ ...location, postalCode: text })}
            placeholder="Code Postal"
            keyboardType="numeric"
          />

          <ThemedText type="defaultSemiBold">Latitude</ThemedText>
          <TextInput
            style={styles.input}
            value={location?.latitude.toString()}
            onChangeText={(text) => setLocation({ ...location, latitude: parseFloat(text) })}
            placeholder="Latitude"
            keyboardType="numeric"
          />

          <ThemedText type="defaultSemiBold">Longitude</ThemedText>
          <TextInput
            style={styles.input}
            value={location?.longitude.toString()}
            onChangeText={(text) => setLocation({ ...location, longitude:parseFloat(text) })}
            placeholder="Longitude"
            keyboardType="numeric"
          />

          <ThemedText type="defaultSemiBold">Pays</ThemedText>
          <TextInput
            style={styles.input}
            value={location?.country}
            onChangeText={(text) => setLocation({ ...location, country: text })}
            placeholder="Pays"
          />

          <ThemedText type="defaultSemiBold">Photo</ThemedText>
          {image ? (
            <Image source={{ uri: image }} style={styles.imagePreview} />
          ) : (
            <Text style={styles.noImageText}>Aucune photo sélectionnée</Text>
          )}
          <TouchableOpacity onPress={() => pickImage(setImage)} style={styles.button}>
            <Text style={styles.buttonText}>Choisir une image</Text>
          </TouchableOpacity>

          <ThemedText type="defaultSemiBold">Surface Habitable (m²)</ThemedText>
          <TextInput
            style={styles.input}
            value={livingArea}
            onChangeText={setLivingArea}
            placeholder="Surface Habitable (m²)"
            keyboardType="numeric"
          />

          <ThemedText type="defaultSemiBold">Surface Terrain (m²)</ThemedText>
          <TextInput
            style={styles.input}
            value={landArea}
            onChangeText={setLandArea}
            placeholder="Surface Terrain (m²)"
            keyboardType="numeric"
          />

          <ThemedText type="defaultSemiBold">Pièces</ThemedText>
          
            <NbPiece rooms={rooms} addRoom={addRoom} removeRoom={removeRoom} />


          <ThemedText type="defaultSemiBold">Orientation</ThemedText>
          <TextInput style={styles.input} value={orientation} onChangeText={setOrientation} placeholder="Orientation" />

          <ThemedText type="defaultSemiBold">Vue</ThemedText>
          <TextInput style={styles.input} value={view} onChangeText={setView} placeholder="Vue" />

          <ThemedText type="defaultSemiBold">Performance énergétique</ThemedText>
          <RNPickerSelect
            style={pickerSelectStyles}
            onValueChange={setEnergyClass}
            value={energyClass}
            placeholder={{ label: 'Sélectionnez une classe énergétique', value: null }}
            items={[
              { label: 'Classe A', value: 'A' },
              { label: 'Classe B', value: 'B' },
              { label: 'Classe C', value: 'C' },
              { label: 'Classe D', value: 'D' },
              { label: 'Classe E', value: 'E' },
              { label: 'Classe F', value: 'F' },
              { label: 'Classe G', value: 'G' },
            ]}
          />
          {/* Affichage de la classe énergétique sélectionnée */}
          <Text >
            Classe énergétique sélectionnée : {energyClass ? energyClass : 'Aucune classe sélectionnée'}
          </Text>
          <ThemedText type="defaultSemiBold">Propriétaire</ThemedText>
          <RNPickerSelect
            style={pickerSelectStyles}
            onValueChange={setClimateClass}
            value={climateClass}
            placeholder={{ label: 'Sélectionnez une classe climatique...', value: null }}
            items={[
              { label: 'Classe A', value: 'A' },
              { label: 'Classe B', value: 'B' },
              { label: 'Classe C', value: 'C' },
              { label: 'Classe D', value: 'D' },
              { label: 'Classe E', value: 'E' },
              { label: 'Classe F', value: 'F' },
              { label: 'Classe G', value: 'G' },
            ]}
          />
          {/* Affichage de la classe énergétique sélectionnée */}
      <Text >
        Classe énergétique sélectionnée : {climateClass ? climateClass : 'Aucune classe sélectionnée'}
      </Text>
          

          <ThemedText type="defaultSemiBold">Estimation des coûts annuels d'énergie</ThemedText>
          <TextInput style={styles.input} value={estimationCostEnergy} onChangeText={setestimationCostEnergy} placeholder="Estimation des coûts" />

          
        </View>
        {/* Boutons Enregistrer et Supprimer */}
        <TouchableOpacity onPress={handleSave} style={styles.button}>
          <Text style={styles.buttonText}>Enregistrer le bien</Text>
        </TouchableOpacity>

        {/* Nouveau bouton pour supprimer */}
        <TouchableOpacity onPress={handleDelete} style={styles.button}>
          <Text style={styles.buttonText}>Annuler</Text>
        </TouchableOpacity>
      </ScrollView>
    </View>
  );
};

export default Detail;

const styles = StyleSheet.create({
 // Style principal du conteneur
 container: {
  padding: 20,
  borderRadius: 15,
  backgroundColor: '#fafafa', // Un fond légèrement gris clair pour un look moderne
  shadowColor: '#000',
  shadowOffset: { width: 0, height: 3 },
  shadowOpacity: 0.2,
  shadowRadius: 5,
  elevation: 8, // Ombre plus douce pour un effet plus subtil
  width: '90%',
  height:'100%',
  marginVertical: 15,
  marginHorizontal: '5%',
},

  // Conteneur pour les textes
  textContainer: {
    marginTop: 15,
    marginBottom: 10,
  },

  // Style pour les champs de saisie
  input: {
    borderBottomWidth: 2,
    borderColor: '#ddd',
    paddingVertical: 12,
    fontSize: 18,
    marginBottom: 20,
    color: '#333', // Texte légèrement plus sombre pour un meilleur contraste
    fontFamily: 'Roboto', // Utilisation d'une police moderne et lisible
  },

  // Aperçu de l'image
  imagePreview: {
    width: 100,
    height: 100,
    borderRadius: 10,
    marginTop: 10,
  },

  // Texte affiché quand il n'y a pas d'image
  noImageText: {
    fontSize: 16,
    color: '#888',
    marginTop: 10,
  },

  // Style du bouton principal
  button: {
    backgroundColor: '#007BFF',
    padding: 10,
    borderRadius: 5,
    marginVertical: 15,
    width: '50%',
    alignSelf: 'center',
    justifyContent: 'center',
  },
  buttonText: {
    color: '#fff',
    fontSize: 16,
    textAlign: 'center',
  },
});

// Styles pour les sélecteurs de plateforme
const pickerSelectStyles = {
  inputIOS: {
    fontSize: 18,
    paddingVertical: 12,
    paddingHorizontal: 20,
    borderWidth: 2,
    borderColor: '#ddd',
    borderRadius: 10,
    color: '#333',
    backgroundColor: '#fff', // Fond blanc pour bien faire ressortir le texte
    marginBottom: 20,
  },
  inputAndroid: {
      fontSize: 18,
      paddingVertical: 12,
      paddingHorizontal: 20,
      borderWidth: 2,
      borderColor: '#ddd',
      borderRadius: 10,
      color: '#333',
      backgroundColor: '#fff',
      marginBottom: 20,
  },
};

