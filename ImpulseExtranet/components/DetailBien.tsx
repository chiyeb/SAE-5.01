import React, { useState } from 'react';
import { View, Text, TextInput, StyleSheet, Image, ScrollView, TouchableOpacity } from 'react-native';
import RNPickerSelect from 'react-native-picker-select';
import NbPiece from './ButtonNbPiece';
import { pickImage } from './SelectImage';
import { ThemedText } from './ThemedText';
import TabSelector from '@/components/navigation/ButtonVenteLocation';  // Import du composant d'onglet

// Définition du type pour les props
interface DetailProps {
  title: string;
  description: string;
  prix: number;
  pays: string;
  ville: string;
  codePostal: number;
  surfaceHabitable: number;
  surfaceTerrain: number;
  orientation: string;
  vue: string;
  estimate: string;
}

const Detail: React.FC<DetailProps> = ({
  title: initialTitle,
  description: initialDescription,
  prix: initialPrix,
  pays: initialPays,
  ville: initialVille,
  codePostal: initialCodePostal,
  surfaceHabitable: initialSurfaceHabitable,
  surfaceTerrain: initialSurfaceTerrain,
  orientation: initialOrientation,
  vue: initialVue,
  estimate: initialEstimate,
}) => {
  // États pour chaque variable
  const [title, setTitle] = useState<string>(initialTitle);
  const [description, setDescription] = useState<string>(initialDescription);
  const [prix, setPrix] = useState<string>(initialPrix.toString());
  const [pays, setPays] = useState<string>(initialPays);
  const [ville, setVille] = useState<string>(initialVille);
  const [codePostal, setCodePostal] = useState<string>(initialCodePostal?.toString() ?? "");
  const [photo, setPhoto] = useState<string>("");
  const [surfaceHabitable, setSurfaceHabitable] = useState<string>(initialSurfaceHabitable?.toString() ?? "");
  const [surfaceTerrain, setSurfaceTerrain] = useState<string>(initialSurfaceTerrain.toString());
  const [orientation, setOrientation] = useState<string>(initialOrientation);
  const [vue, setVue] = useState<string>(initialVue);
  const [performance, setPerformance] = useState<string>(""); // Classe de performance énergétique
  const [estimate, setEstimate] = useState<string>(initialEstimate);
  const [proprietaire, setProprietaire] = useState<string>("Selectionne un proprietaire"); // Propriétaire
  // État pour gérer l'onglet sélectionné dans la modal
  const [selectedTab, setSelectedTab] = useState('details');

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
          <TextInput style={styles.input} value={prix} onChangeText={setPrix} placeholder="Prix" keyboardType="numeric" />

          <ThemedText type="defaultSemiBold">Pays</ThemedText>
          <TextInput style={styles.input} value={pays} onChangeText={setPays} placeholder="Pays" />

          <ThemedText type="defaultSemiBold">Ville</ThemedText>
          <TextInput style={styles.input} value={ville} onChangeText={setVille} placeholder="Ville" />

          <ThemedText type="defaultSemiBold">Code Postal</ThemedText>
          <TextInput style={styles.input} value={codePostal} onChangeText={setCodePostal} placeholder="Code Postal" keyboardType="numeric" />

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
          <TextInput style={styles.input} value={surfaceHabitable} onChangeText={setSurfaceHabitable} placeholder="Surface Habitable (m²)" keyboardType="numeric" />

          <ThemedText type="defaultSemiBold">Surface Terrain (m²)</ThemedText>
          <TextInput style={styles.input} value={surfaceTerrain} onChangeText={setSurfaceTerrain} placeholder="Surface Terrain (m²)" keyboardType="numeric" />

          <ThemedText type="defaultSemiBold">Nombre de Pièces</ThemedText>
          <NbPiece />

          <ThemedText type="defaultSemiBold">Orientation</ThemedText>
          <TextInput style={styles.input} value={orientation} onChangeText={setOrientation} placeholder="Orientation" />

          <ThemedText type="defaultSemiBold">Vue</ThemedText>
          <TextInput style={styles.input} value={vue} onChangeText={setVue} placeholder="Vue" />

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
          <TextInput style={styles.input} value={estimate} onChangeText={setEstimate} placeholder="Estimation des coûts" />

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
  tabs: {
    flexDirection: 'row',
    marginBottom: 20,
    alignSelf:'center'
  },
  tab: {
    padding: 10,
    marginHorizontal: 5,
    
  },
  activeTab: {
    backgroundColor: '#ddd',
    borderRadius: 5,
  },
  tabText: {
    fontSize: 16,
    color: '#333',
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
