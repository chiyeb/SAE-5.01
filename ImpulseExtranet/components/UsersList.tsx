import React from 'react';
import { View, Text, StyleSheet, } from 'react-native';
import { ThemedText } from './ThemedText';

interface UserProps {
  name: string;
  lastname :string;
  email: string;
  phoneNumber: number;
  age: number;
  moreInformations: string;
}

export default function User({
  name,
  lastname,
  email,
  phoneNumber,
  age,
  moreInformations,
}: UserProps) {
  

  return (
    <View  style={styles.container}>
      <View style={styles.textContainer}>
      
        <Text style={styles.name}>{name} {lastname}</Text>
        <Text style={styles.text}><ThemedText type="defaultSemiBold">Âge: </ThemedText>{age}</Text>
        <Text style={styles.text}><ThemedText type="defaultSemiBold">Email: </ThemedText>{email}</Text>
        <Text style={styles.text}><ThemedText type="defaultSemiBold">Téléphone: </ThemedText>{phoneNumber}</Text>
        <Text style={styles.description}>{moreInformations}</Text>
      </View>
      {/* Modal pour afficher les détails du user */}
    </View>
  );
}

const styles = StyleSheet.create({
  // Styles du conteneur principal
  container: {
    flexDirection: 'row',
    alignItems: 'center',
    padding: 10,
    borderRadius: 10,
    backgroundColor: '#fff',
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.3,
    shadowRadius: 4,
    elevation: 5,
    width: 400,
    margin: 10,
    overflow: 'hidden',
  },

  // Styles du conteneur de texte
  textContainer: {
    flex: 1,
    paddingLeft: 10,
  },
  name: {
    fontSize: 20,
    fontWeight: 'bold',
    color: 'black',
    textAlign: 'center',
    paddingBottom: 10,

  },
  description: {
    fontSize: 16,
    color: 'black',
    textAlign: 'left',
    paddingVertical: 5,
  },
  text: {
    fontSize: 16,
    lineHeight: 24,
    color: '#333', // Texte sombre pour une bonne lisibilité
    marginBottom: 10,
  },
});

