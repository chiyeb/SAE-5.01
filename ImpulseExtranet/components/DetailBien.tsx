import React, { useState } from 'react';
import { View, Text, TextInput, StyleSheet, Image, ScrollView, TouchableOpacity } from 'react-native';
import RNPickerSelect from 'react-native-picker-select';
import NbPiece from './ButtonNbPiece';
import { pickImage } from './SelectImage';
import { ThemedText } from './ThemedText';
import TabSelector from '@/components/navigation/ButtonVenteLocation';  // Import du composant d'onglet

// Définition du type pour les props
interface Location {
  address: string;
  city: string;
  postalCode: string;
  country: string;
}

interface DetailProps {
  title: string;
  description: string;
  price: number;
  location: Location;
  livingArea: number;
  landArea: number;
  orientation: string;
  view: string;
  estimationCostEnergy: string;
  onSaveBien: (bienData: any) => void;
  onDelete: (bienData: any) => void;
}

const Detail: React.FC<DetailProps> = ({
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
  const [title, setTitle] = useState<string>(initialTitle);
  const [description, setDescription] = useState<string>(initialDescription);
  const [price, setPrice] = useState<string>(initialPrice?.toString());
  const [location, setLocation] = useState<Location>(initialLocation);  // Utilisation de l'objet location
  const [photo, setPhoto] = useState<string>("");
  const [livingArea, setlivingArea] = useState<string>(initiallivingArea?.toString() ?? "");
  const [landArea, setlandArea] = useState<string>(initiallandArea?.toString());
  const [orientation, setOrientation] = useState<string>(initialOrientation);
  const [view, setView] = useState<string>(initialView);
  const [performance, setPerformance] = useState<string>(""); // Classe de performance énergétique
  const [estimationCostEnergy, setestimationCostEnergy] = useState<string>(initialestimationCostEnergy);
  const [proprietaire, setProprietaire] = useState<string>("Sélectionnez un propriétaire"); // Propriétaire
  // In Detail component
const [rooms, setRooms] = useState<{ roomType: string, count: number }[]>([]);

  // État pour gérer l'onglet sélectionné dans la modal
  const [selectedTab, setSelectedTab] = useState('details');
  const getBienData = () => ({
    title,
    description,
    price: parseFloat(price),
    location,
    livingArea: parseFloat(livingArea),
    landArea: parseFloat(landArea),
    rooms,
    orientation,
    view,
    performance,
    estimationCostEnergy,
    proprietaire,
  });

  const handleSave = () => {
    onSaveBien(getBienData());
  };
  const handleDelete = () => {
    // Demander une confirmation avant de supprimer le bien
    onDelete(getBienData());
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
          <TabSelector selectedTab={selectedTab} onTabSelect={setSelectedTab} />

          <ThemedText type="defaultSemiBold">Titre du bien</ThemedText>
          <TextInput style={styles.input} value={title} onChangeText={setTitle} placeholder="Titre" />

          <ThemedText type="defaultSemiBold">Description</ThemedText>
          <TextInput style={styles.input} value={description} onChangeText={setDescription} placeholder="Description" />

          <ThemedText type="defaultSemiBold">Prix</ThemedText>
          <TextInput style={styles.input} value={price} onChangeText={setPrice} placeholder="Prix" keyboardType="numeric" />

          {/* Mise à jour de l'affichage de location */}
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
            value={location?.postalCode}
            onChangeText={(text) => setLocation({ ...location, postalCode: text })}
            placeholder="Code Postal"
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
          {photo ? (
            <Image source={{ uri: photo }} style={styles.imagePreview} />
          ) : (
            <Text style={styles.noImageText}>Aucune photo sélectionnée</Text>
          )}
          <TouchableOpacity onPress={() => pickImage(setPhoto)} style={styles.button}>
            <Text style={styles.buttonText}>Choisir une image</Text>
          </TouchableOpacity>

          <ThemedText type="defaultSemiBold">Surface Habitable (m²)</ThemedText>
          <TextInput
            style={styles.input}
            value={livingArea}
            onChangeText={setlivingArea}
            placeholder="Surface Habitable (m²)"
            keyboardType="numeric"
          />

          <ThemedText type="defaultSemiBold">Surface Terrain (m²)</ThemedText>
          <TextInput
            style={styles.input}
            value={landArea}
            onChangeText={setlandArea}
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
            onValueChange={setPerformance}
            value={performance}
            items={[
              { label: 'Classe A', value: 'A' },
              { label: 'Classe B', value: 'B' },
              { label: 'Classe C', value: 'C' },
            ]}
          />

          <ThemedText type="defaultSemiBold">Estimation des coûts annuels d'énergie</ThemedText>
          <TextInput style={styles.input} value={estimationCostEnergy} onChangeText={setestimationCostEnergy} placeholder="Estimation des coûts" />

          <ThemedText type="defaultSemiBold">Propriétaire</ThemedText>
          <RNPickerSelect
            style={pickerSelectStyles}
            onValueChange={setProprietaire}
            value={proprietaire}
            items={[
              { label: 'Jean Dupont', value: 'jean' },
              { label: 'Marie Durand', value: 'marie' },
            ]}
          />
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
  container: {
    padding: 10,
    borderRadius: 10,
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.3,
    shadowRadius: 4,
    elevation: 5,
    backgroundColor: 'white',
    width: '90%',
    height: '100%',
    margin: 10,
  },
  textContainer: {
    paddingHorizontal: 10,
  },
  input: {
    borderBottomWidth: 1,
    borderColor: '#ccc',
    paddingVertical: 5,
    marginBottom: 15,
    fontSize: 16,
    color: 'black',
  },
  imagePreview: {
    width: 100,
    height: 100,
    borderRadius: 10,
    marginTop: 10,
  },
  noImageText: {
    fontSize: 16,
    color: '#888',
    marginTop: 10,
  },
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

const pickerSelectStyles = {
  inputIOS: {
    fontSize: 16,
    paddingVertical: 8,
    paddingHorizontal: 10,
    borderWidth: 1,
    borderColor: '#ccc',
    borderRadius: 4,
    color: 'black',
  },
  inputAndroid: {
    fontSize: 16,
    paddingVertical: 8,
    paddingHorizontal: 10,
    borderWidth: 1,
    borderColor: '#ccc',
    borderRadius: 4,
    color: 'black',
  },
};
