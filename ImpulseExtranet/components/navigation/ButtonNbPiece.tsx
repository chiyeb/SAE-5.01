import React, { useState } from 'react';
import { View, Text, TouchableOpacity, StyleSheet, FlatList } from 'react-native';
import RNPickerSelect from 'react-native-picker-select';
import { ThemedText } from '../ThemedText'; // Import the ThemedText component if available

interface NbPieceProps {
  rooms: { roomType: string; count: number }[]; // Prop to hold room data
  addRoom: (roomType: string, count: number) => void; // Function to add room
  removeRoom: (index: number) => void; // Function to remove room
}

const NbPiece: React.FC<NbPieceProps> = ({ rooms, addRoom, removeRoom }) => {
  const [selectedRoomType, setSelectedRoomType] = useState<string | null>(null); // Room type selected in the Picker
  const [roomCount, setRoomCount] = useState<number>(1); // Number of rooms to add
  
  const handleAddRoom = () => {
    if (!selectedRoomType) return; // Exit if no room type is selected
    
    // Increment count for the selected room type
    addRoom(selectedRoomType, roomCount); // Call the addRoom function passed as prop
    
  };

  const roomOptions: { label: string; value: string }[] = [
    { label: 'Chambre', value: 'chambre' },
    { label: 'Salle de Bain', value: 'salle_de_bain' },
    { label: 'Cuisine', value: 'cuisine' },
    { label: 'Salon', value: 'salon' },
  ];

  return (
    <View style={styles.container}>
      <ThemedText type="defaultSemiBold">Nombre de Pièces</ThemedText>

      {/* Picker for selecting room type */}
      <RNPickerSelect
        onValueChange={(value) => setSelectedRoomType(value)}
        value={selectedRoomType}
        items={roomOptions}
        placeholder={{ label: 'Sélectionnez une pièce...', value: null }}
      />

      {/* Control for selecting the number of rooms */}
      <View style={styles.countContainer}>
        <Text style={styles.label}>Nombre de pièces</Text>
        <TouchableOpacity
          onPress={() => setRoomCount(roomCount + 1)}
          style={styles.incrementButton}
        >
          <Text style={styles.buttonText}>+</Text>
        </TouchableOpacity>
        <Text style={styles.count}>{roomCount}</Text>
        <TouchableOpacity
          onPress={() => setRoomCount(roomCount > 1 ? roomCount - 1 : 1)}
          style={styles.decrementButton}
        >
          <Text style={styles.buttonText}>-</Text>
        </TouchableOpacity>
      </View>

      {/* Button to add room */}
      <TouchableOpacity onPress={handleAddRoom} style={styles.addButton}>
        <Text style={styles.addButtonText}>Ajouter une pièce</Text>
      </TouchableOpacity>

      {/* Display total number of rooms */}
      <Text style={styles.pieceCount}>
        Nombre total de pièces : {rooms.reduce((total, room) => total + room.count, 0)}
      </Text>

      {/* Display the list of added rooms */}
      <FlatList
        data={rooms}
        keyExtractor={(item, index) => index.toString()}
        renderItem={({ item, index }) => (
          <View style={styles.roomItem}>
            <Text>
            <Text>
              {item.roomType.charAt(0).toUpperCase() + item.roomType.slice(1)} : {item.count} {item.count > 1 ? 'pièces' : 'pièce'}
            </Text>

            </Text>
            <TouchableOpacity onPress={() => removeRoom(index)} style={styles.button}>
              <Text style={styles.buttonText}>Supprimer</Text>
            </TouchableOpacity>
          </View>
        )}
      />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    marginVertical: 20,
    paddingHorizontal: 10,
  },
  countContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    marginVertical: 10,
  },
  label: {
    fontSize: 16,
    fontWeight: 'bold',
    marginRight: 10,
  },
  incrementButton: {
    backgroundColor: '#007BFF',
    padding: 10,
    borderRadius: 5,
    marginRight: 10,
  },
  decrementButton: {
    backgroundColor: '#007BFF',
    padding: 10,
    borderRadius: 5,
    marginLeft: 10,
  },
  count: {
    fontSize: 18,
    fontWeight: 'bold',
  },
  addButton: {
    backgroundColor: '#007BFF',
    padding: 10,
    borderRadius: 5,
    marginVertical: 15,
    alignItems: 'center',
    width: '50%',
    alignSelf: 'center',
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
  roomItem: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    marginVertical: 5,
  },
  button: {
    backgroundColor: '#FF5733',
    padding: 5,
    borderRadius: 5,
    marginVertical: 5,
  },
  buttonText: {
    color: '#fff',
    textAlign: 'center',
  },
});

export default NbPiece;
