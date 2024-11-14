import React, { useState } from 'react';
import { View, Text, StyleSheet, TouchableOpacity, FlatList } from 'react-native';
import RNPickerSelect from 'react-native-picker-select';
import { ThemedText } from './ThemedText';

interface Piece {
  label: string;
  value: string;
}

export default function NbPiece() {
  // Types de données pour les états
  const [pieces, setPieces] = useState<Piece[]>([]); // Liste des pièces ajoutées
  const [selectedPiece, setSelectedPiece] = useState<string | null>(null); // Pièce actuellement sélectionnée

  // Fonction pour ajouter une pièce sélectionnée
  const addPiece = () => {
    if (selectedPiece) {
      // Ajoute la pièce sélectionnée à la liste
      const pieceLabel = piecesOptions.find(option => option.value === selectedPiece)?.label || selectedPiece;
      setPieces([...pieces, { label: pieceLabel, value: selectedPiece }]);
      setSelectedPiece(null); // Réinitialise la sélection
    }
  };

  // Options pour les pièces disponibles dans le picker
  const piecesOptions: Piece[] = [
    { label: 'Chambre', value: 'chambre' },
    { label: 'Salle de Bain', value: 'salle_de_bain' },
    { label: 'Cuisine', value: 'cuisine' },
    { label: 'Salon', value: 'salon' },
  ];

  return (
    <View style={styles.container}>
      <ThemedText type="defaultSemiBold">Nombre de Pièces</ThemedText>

      {/* Picker pour sélectionner le type de pièce */}
      <RNPickerSelect
        onValueChange={(value) => setSelectedPiece(value)}
        value={selectedPiece}
        items={piecesOptions}
        placeholder={{ label: 'Sélectionnez une pièce...', value: null }}
      />

      {/* Bouton pour ajouter la pièce sélectionnée */}
      <TouchableOpacity onPress={addPiece} style={styles.addButton}>
        <Text style={styles.addButtonText}>Ajouter une pièce</Text>
      </TouchableOpacity>

      {/* Affichage du nombre total de pièces */}
      <Text style={styles.pieceCount}>Nombre total de pièces : {pieces.length}</Text>

      {/* Affichage de la liste des pièces ajoutées */}
      <FlatList
        data={pieces}
        keyExtractor={(item, index) => index.toString()}
        renderItem={({ item}) => (
          <Text style={styles.pieceItem}> - {item.label}</Text>
        )}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    padding: 20,
  },
  addButton: {
    backgroundColor: '#007BFF',
    padding: 10,
    borderRadius: 5,
    marginVertical: 15,
    alignItems: 'center',
    width:'50%',
    alignSelf:'center',
  },
  addButtonText: {
    color: '#fff',
    fontSize: 16,
  },
  pieceCount: {
    fontSize: 18,
    fontWeight: 'bold',
    marginVertical: 10,
  },
  pieceItem: {
    fontSize: 16,
    paddingVertical: 5,
  },
});
