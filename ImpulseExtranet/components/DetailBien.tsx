import React, { useState } from 'react';
import { View, Text, TextInput, StyleSheet, Image, ScrollView, TouchableOpacity } from 'react-native';
import RNPickerSelect from 'react-native-picker-select';
import NbPiece from './navigation/ButtonNbPiece';
import { pickImage } from './SelectImage';
import { ThemedText } from './ThemedText';
import ButtonSaveCancel from './navigation/ButtonSaveCancel';
import { launchImageLibrary } from 'react-native-image-picker'; 



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
  id:string;
  type: string;
  title: string;
  description: string;
  price: number;
  subscriptionFrequency:string;
  location: Location;
  livingArea: number;
  landArea: number;
  images: string[],
  orientation: string;
  view: string;
  rooms: { roomType: string; count: number }[];
  energyClass: string;
  climateClass: string;
  estimationCostEnergy: string;
  onSaveBien: (bienData: any) => void;
  onDelete: (bienData: any) => void;
}

const Detail: React.FC<DetailProps> = ({
  selectedTab,
  id,
  type:iniatielType,
  title: initialTitle,
  description: initialDescription,
  price: initialPrice,
  subscriptionFrequency:initialsubscriptionFrequency,
  location: initialLocation,
  livingArea: initiallivingArea,
  landArea: initiallandArea,
  orientation: initialOrientation,
  view: initialView,
  rooms: iniatialRooms,
  energyClass: initialEnergyClass,
  climateClass: initialClimateClass,
  estimationCostEnergy: initialestimationCostEnergy,
  onSaveBien,
  onDelete,
}) => {
  // États pour chaque variable
  const [type, setType] = useState<string>(iniatielType); // type du bien
  const [title, setTitle] = useState<string>(initialTitle);
  const [description, setDescription] = useState<string>(initialDescription);
  const [price, setPrice] = useState<string>(initialPrice?.toString());
  const [subscriptionFrequency, setSubscriptionFrequency] = useState<string | null>(initialsubscriptionFrequency);
  const [location, setLocation] = useState<Location>(initialLocation);  // Utilisation de l'objet localisation
  const [images, setImages] = useState<string[]>([]);
  const [livingArea, setLivingArea] = useState<string>(initiallivingArea?.toString());
  const [landArea, setLandArea] = useState<string>(initiallandArea?.toString());
  const [orientation, setOrientation] = useState<string>(initialOrientation);
  const [view, setView] = useState<string>(initialView);
  const [energyClass, setEnergyClass] = useState<string>(initialEnergyClass); // Classe de performance énergétique
  const [climateClass, setClimateClass] = useState<string>(initialClimateClass); // Classe climat énergétique
  const [estimationCostEnergy, setestimationCostEnergy] = useState<string>(initialestimationCostEnergy);
  
  // In Detail component
const [rooms, setRooms] = useState<{ roomType: string, count: number }[]>(iniatialRooms);

  // État pour gérer l'onglet sélectionné dans la modal

  const getBienData = () => ({
    id,
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
    images,
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
    const pickImage = (setImages: React.Dispatch<React.SetStateAction<string[]>>) => {
      launchImageLibrary({ mediaType: 'photo', quality: 0.5 }, (response) => {
        if (response.didCancel) {
          console.log('User canceled image picker');
        } else if (response.errorCode) {
          console.log('Image picker error: ', response.errorMessage);
        } else {
          // Vérifier si l'URI est défini, sinon, fournir une chaîne vide ou une autre valeur par défaut
          const source = response.assets?.[0].uri ?? '';  // Si undefined, utilise une chaîne vide
          if (source) {
            setImages([source]);  // Mettre à jour l'état avec l'URI de la première image sélectionnée
          } else {
            console.log('Aucune image sélectionnée');
          }
        }
      });
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

            {/* Image */}
            <Text >Images</Text>
      <TouchableOpacity onPress={() => pickImage(setImages)} style={styles.button}>
        <Text style={styles.buttonText}>Choisir une image</Text>
      </TouchableOpacity>
      {images.length > 0 && (
        <Image source={{ uri: images[0] }} style={styles.imagePreview} />
      )}

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
        <ButtonSaveCancel onSave={handleSave} onCancel={handleDelete}/>
      </ScrollView>
    </View>
  );
};

export default Detail;
const styles = StyleSheet.create({
  container: {
    padding: 20,
    borderRadius: 15,
    backgroundColor: '#ffffff', // Fond blanc pour une sensation de propreté
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 5 },
    shadowOpacity: 0.1,
    shadowRadius: 10,
    elevation: 6,
    width: '95%',
    height:'95%',
    alignSelf: 'center',
    marginVertical: 20,
  },
  textContainer: {
    marginTop: 15,
    marginBottom: 10,
    width: '80%',
    alignSelf:'center'
  },
  input: {
    borderWidth: 1,
    borderColor: '#d3d3d3', // Bordures grises douces
    borderRadius: 10,
    paddingVertical: 12,
    paddingHorizontal: 10,
    fontSize: 16,
    backgroundColor: '#f9f9f9', // Légèrement grisé pour un contraste avec le fond blanc
    color: '#333',
    marginBottom: 20,
    fontFamily: 'Roboto', // Police moderne
  },
  imagePreview: {
    width: 120,
    height: 120,
    borderRadius: 12,
    marginTop: 10,
    borderWidth: 1,
    borderColor: '#ddd',
    alignSelf: 'center',
  },
  noImageText: {
    fontSize: 14,
    color: '#999',
    marginTop: 10,
    textAlign: 'center',
  },
  button: {
    backgroundColor: '#007BFF',
    paddingVertical: 12,
    borderRadius: 25, // Plus arrondi pour une esthétique moderne
    marginVertical: 15,
    marginHorizontal:30,
    width: '30%',
    alignSelf: 'center',
    shadowColor: '#007BFF',
    shadowOffset: { width: 0, height: 4 },
    shadowOpacity: 0.3,
    shadowRadius: 6,
    alignItems:'center',
    justifyContent:"center",
    flexDirection:'row',
  },
  buttonText: {
    color: '#fff',
    fontSize: 16,
    fontWeight: '600',
    textAlign: 'center',
  },
  pickerContainer: {
    marginBottom: 20,
  },
  pickerLabel: {
    fontSize: 16,
    color: '#555', // Couleur neutre
    marginBottom: 5,
  },
  pickerSelect: {
    fontSize: 16,
    borderWidth: 1,
    borderColor: '#ddd',
    borderRadius: 10,
    padding: 10,
    backgroundColor: '#f9f9f9',
  },
});

// Styles pour RNPickerSelect
const pickerSelectStyles = {
  inputIOS: {
    fontSize: 16,
    paddingVertical: 12,
    paddingHorizontal: 10,
    borderWidth: 1,
    borderColor: '#d3d3d3',
    borderRadius: 10,
    color: '#333',
    backgroundColor: '#f9f9f9',
    marginBottom: 20,
  },
  inputAndroid: {
    fontSize: 16,
    paddingVertical: 12,
    paddingHorizontal: 10,
    borderWidth: 1,
    borderColor: '#d3d3d3',
    borderRadius: 10,
    color: '#333',
    backgroundColor: '#f9f9f9',
    marginBottom: 20,
  },
};
