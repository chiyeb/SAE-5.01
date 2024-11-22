import React, { useEffect, useState } from 'react';
import { View, Text, StyleSheet } from 'react-native';
import { getUserInfo } from '@/components/LoginRequest'; // Assurez-vous que le chemin vers votre fichier est correct
import { ThemedText } from '@/components/ThemedText';

export default function Profil() {
  const [userInfo, setUserInfo] = useState<any>(null); // Stocker les informations utilisateur
  const [error, setError] = useState<string | null>(null); // Stocker les erreurs

  // Utiliser useEffect pour récupérer les infos utilisateur dès que le composant est monté
  useEffect(() => {
    const fetchUserData = async () => {
      const data = await getUserInfo(); // Récupérer les infos de l'utilisateur
      if (data) {
        setUserInfo(data); // Stocker les informations utilisateur dans l'état
      } else {
        setError('Erreur lors de la récupération des informations utilisateur.');
      }
    };

    fetchUserData(); // Appeler la fonction pour récupérer les données utilisateur
  }, []); // Se déclencher uniquement au montage du composant

  if (error) {
    return (
      <View style={styles.container}>
        <Text style={styles.error}>{error}</Text>
      </View>
    );
  }

  if (!userInfo) {
    return (
      <View style={styles.container}>
        <Text style={styles.loading}>Chargement des informations...</Text>
      </View>
    );
  }

  return (
    <View style={styles.container}>
      <View style={styles.textContainer}>
        <Text style={styles.title}>Profil</Text>
        <Text style={styles.description}>
          <ThemedText type="defaultSemiBold">Nom: </ThemedText>{userInfo.name}
        </Text>
        <Text style={styles.description}>
          <ThemedText type="defaultSemiBold">Prénom: </ThemedText>{userInfo.lastname}
        </Text>
        <Text style={styles.description}>
          <ThemedText type="defaultSemiBold">Email: </ThemedText>{userInfo.email}
        </Text>
        <Text style={styles.description}>
          <ThemedText type="defaultSemiBold">Tel: </ThemedText>{userInfo.phoneNumber}
        </Text>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    margin: 16,
    borderRadius: 10,
    overflow: 'hidden',
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.3,
    shadowRadius: 4,
    elevation: 5, // Ombre pour Android
    width:'20%',
    height:'40%',
    backgroundColor: 'rgba(0, 0, 0, 0.6)',
  },
  textContainer: {
    padding: 10,
  },
  title: {
    fontSize: 18,
    fontWeight: 'bold',
    color: 'white',
    marginBottom: 4,
    textAlign:'center'
  },
  description: {
    fontSize: 14,
    color: 'white',
    margin:10,
    textAlign:'center'
  },
  error: {
    color: 'red',
    textAlign: 'center',
    marginTop: 20,
  },
  loading: {
    color: 'white',
    textAlign: 'center',
    marginTop: 20,
  },
});

