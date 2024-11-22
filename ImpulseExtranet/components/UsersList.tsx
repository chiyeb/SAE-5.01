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
        <Text style={styles.description}>{email}</Text>
        <Text style={styles.description}>{phoneNumber}</Text>
        <ThemedText type="defaultSemiBold">Age: {age}</ThemedText>
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
    width: 300,
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
    fontSize: 14,
    color: 'black',
    textAlign: 'left',
    paddingVertical: 5,
  },

  // Styles du conteneur modal
  modalContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: 'rgba(0, 0, 0, 0.7)', // Fond semi-transparent pour la modal
    paddingHorizontal: 20,
  },
  
  modalContent: {
    backgroundColor: '#fff', // Fond blanc pour la modal
    borderRadius: 20,
    paddingVertical: 20,
    paddingHorizontal: 30,
    width: '85%',
    height:'95%',
    alignItems: 'flex-start',
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.2,
    shadowRadius: 10,
    elevation: 15,
  },

  modalTitle: {
    alignSelf:'center'
  },

  modalText: {
    fontSize: 16,
    lineHeight: 24,
    color: '#333', // Texte sombre pour une bonne lisibilité
    marginBottom: 10,
  },
  

  // Styles du bouton de fermeture
  buttonClose: {
    backgroundColor: '#007BFF',
    paddingVertical: 12,
    paddingHorizontal: 25,
    borderRadius: 30,
    marginTop: 20,
    alignSelf: 'center',
    elevation: 5,
  },

  buttonText: {
    color: '#fff',
    fontSize: 18,
    fontWeight: 'bold',
    textAlign: 'center',
  },
});

