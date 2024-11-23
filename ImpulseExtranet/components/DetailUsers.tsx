import React, { useState } from 'react';
import { View, Text, TextInput, StyleSheet, ScrollView, TouchableOpacity } from 'react-native';
import { ThemedText } from './ThemedText';
import ButtonSaveCancel from './navigation/ButtonSaveCancel';

interface UserProps {
  id:string
  name: string;
  lastname: string;
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
  lastname: initialLastName,
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
  const [lastname, setLastname] = useState<string>(initialLastName);
  const [email, setEmail] = useState<string>(initialEmail);
  const [phoneNumber, setPhoneNumber] = useState<string>(initialPhoneNumber?.toString());
  const [age, setAge] = useState<string>(initialAge?.toString());
  const [moreInformations, setMoreInformations] = useState<string>(initialMoreInformations);

  const getUserData = () => ({
    id,
    name,
    lastname,
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
          <TextInput style={styles.input} value={name} onChangeText={setName} placeholder="Entrez votre  nom" />

          <ThemedText type="defaultSemiBold">Prénom</ThemedText>
          <TextInput style={styles.input} value={lastname} onChangeText={setLastname} placeholder="Entrez votre prénom" />

          <ThemedText type="defaultSemiBold">Âge</ThemedText>
          <TextInput
            style={styles.input}
            value={age}
            onChangeText={setAge}
            placeholder="Entrez votre âge"
            keyboardType="numeric"
          />

          <ThemedText type="defaultSemiBold">Email</ThemedText>
          <TextInput style={styles.input} value={email} onChangeText={setEmail} placeholder="Entrez votre  Email" />

          <ThemedText type="defaultSemiBold">Numéro de téléphone</ThemedText>
          <TextInput style={styles.input} value={phoneNumber} onChangeText={setPhoneNumber} placeholder="Entrez votre numéro de téléphone" keyboardType="phone-pad" />

          <ThemedText type="defaultSemiBold">Plus d'information</ThemedText>
          <TextInput style={styles.input} value={moreInformations} onChangeText={setMoreInformations} placeholder="Entrez vos informations" />

        </View>

        <ButtonSaveCancel onSave={handleSave} onCancel={handleDelete}/>
      </ScrollView>
    </View>
  );
};

export default UserDetail;
const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 20,
    borderRadius: 15,
    backgroundColor: '#f4f4f9', // Un fond doux et légèrement texturé
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 4 },
    shadowOpacity: 0.1,
    shadowRadius: 6,
    elevation: 10,
    width: '95%',
    marginVertical: 20,
    marginHorizontal: '5%',
  },

  textContainer: {
    marginTop: 15,
    marginBottom: 10,
    width: '80%',
    alignSelf:'center'
  },

  input: {
    borderWidth: 1, // Bord plus visible
    borderColor: '#dcdde1', // Gris clair
    borderRadius: 8,
    padding: 12,
    fontSize: 16,
    marginBottom: 20,
    color: '#2c3e50', // Texte gris foncé
    backgroundColor: '#fff', // Fond blanc
    fontFamily: 'Roboto',
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.05,
    shadowRadius: 3,
  },

  // Ajout d'un style pour le titre
  title: {
    fontSize: 22,
    fontWeight: '700',
    color: '#34495e', // Gris bleu foncé
    marginBottom: 20,
    textAlign: 'center',
  },

  // Style optionnel pour ajouter un séparateur visuel
  divider: {
    height: 1,
    backgroundColor: '#dcdde1',
    marginVertical: 15,
  },
});
