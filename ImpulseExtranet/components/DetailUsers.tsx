import React, { useState } from 'react';
import { View, Text, TextInput, StyleSheet, ScrollView, TouchableOpacity } from 'react-native';
import { ThemedText } from './ThemedText';

interface UserProps {
  id:string
  name: string;
  email: string;
  phoneNumber: number;
  age: number;
  moreInformations: string;
  onSaveUser: (userData: any) => void;
  onDeleteUser: (userId: any) => void;
}

const UserDetail: React.FC<UserProps> = ({
  id:initialId,
  name: initialName,
  email: initialEmail,
  phoneNumber: initialPhoneNumber,
  age: initialAge,
  moreInformations: initialMoreInformations,
  onSaveUser,
  onDeleteUser,
}) => {
  // États pour chaque variable utilisateur
  const [id, setID] = useState<string>(initialId);
  const [name, setName] = useState<string>(initialName);
  const [email, setEmail] = useState<string>(initialEmail);
  const [phoneNumber, setPhoneNumber] = useState<string>(initialPhoneNumber?.toString());
  const [age, setAge] = useState<string>(initialAge?.toString());
  const [moreInformations, setMoreInformations] = useState<string>(initialMoreInformations);


  const getUserData = () => ({
    id,
    name,
    email,
    phoneNumber,
    age,
    moreInformations,
  });

  const handleSave = () => {
    onSaveUser(getUserData());
  };

  const handleDelete = () => {
    onDeleteUser(email); // Utilisation de l'email comme identifiant pour la suppression
  };

  return (
    <View style={styles.container}>
      <ScrollView>
        <ThemedText type="title">Détails de l'utilisateur</ThemedText>
        <View style={styles.textContainer}>
          <ThemedText type="defaultSemiBold">Nom</ThemedText>
          <TextInput style={styles.input} value={name} onChangeText={setName} placeholder="Nom" />

          <ThemedText type="defaultSemiBold">Âge</ThemedText>
          <TextInput
            style={styles.input}
            value={age}
            onChangeText={setAge}
            placeholder="Code Postal"
            keyboardType="numeric"
          />

          <ThemedText type="defaultSemiBold">Email</ThemedText>
          <TextInput style={styles.input} value={email} onChangeText={setEmail} placeholder="Email" />

          <ThemedText type="defaultSemiBold">Numéro de téléphone</ThemedText>
          <TextInput style={styles.input} value={phoneNumber} onChangeText={setPhoneNumber} placeholder="Numéro de téléphone" keyboardType="phone-pad" />

          <ThemedText type="defaultSemiBold">Plus d'information</ThemedText>
          <TextInput style={styles.input} value={moreInformations} onChangeText={setMoreInformations} placeholder="Plus d'information" />

        </View>

        {/* Boutons Enregistrer et Supprimer */}
        <TouchableOpacity onPress={handleSave} style={styles.button}>
          <Text style={styles.buttonText}>Enregistrer l'utilisateur</Text>
        </TouchableOpacity>

        <TouchableOpacity onPress={handleDelete} style={styles.button}>
          <Text style={styles.buttonText}>Annuler</Text>
        </TouchableOpacity>
      </ScrollView>
    </View>
  );
};

export default UserDetail;

const styles = StyleSheet.create({
  container: {
    padding: 20,
    borderRadius: 15,
    backgroundColor: '#fafafa',
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 3 },
    shadowOpacity: 0.2,
    shadowRadius: 5,
    elevation: 8,
    width: '90%',
    height: '100%',
    marginVertical: 15,
    marginHorizontal: '5%',
  },

  textContainer: {
    marginTop: 15,
    marginBottom: 10,
  },

  input: {
    borderBottomWidth: 2,
    borderColor: '#ddd',
    paddingVertical: 12,
    fontSize: 18,
    marginBottom: 20,
    color: '#333',
    fontFamily: 'Roboto',
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
