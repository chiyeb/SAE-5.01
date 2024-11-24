import React, { useEffect, useState } from 'react';
import { View, Text, StyleSheet, Alert, TouchableOpacity, Modal } from 'react-native';
import FontAwesome from '@expo/vector-icons/FontAwesome';
import { MaterialIcons } from '@expo/vector-icons';
import { getUserInfo } from '@/components/LoginRequest'; // Assurez-vous que le chemin est correct
import { ThemedText } from '@/components/ThemedText';
import {  updateUser} from '@/components/UsersRequest';
import DetailUser from '@/components/DetailUsers';

export default function Profil() {
  const [userInfo, setUserInfo] = useState<any | null>(null); // Informations de l'utilisateur
  const [error, setError] = useState<string | null>(null); // Gestion des erreurs
  const [isModalUpdate, setIsModalUpdate] = useState(false); // État pour afficher/masquer le modal
  const [selectedUser, setSelectedUser] = useState<any | null>(null); // Utilisateur sélectionné
  const [token] = useState<string | null>(null); // Simuler un token pour les appels réseau

  // Charger les données utilisateur lors du montage du composant
  useEffect(() => {
    const fetchUserData = async () => {
      try {
        const data = await getUserInfo(); // Récupérer les infos utilisateur
        if (data) {
          setUserInfo(data); // Stocker les informations utilisateur
        } else {
          setError('Erreur lors de la récupération des informations utilisateur. Vous devez être connecté.');
        }
      } catch (err) {
        console.error('Erreur lors de la récupération des données utilisateur', err);
        setError('Une erreur est survenue. Veuillez réessayer.');
      }
    };

    fetchUserData();
  }, []);
  const openModalForUpdate = (id: string, userData: any) => {
    setSelectedUser(userData);
    setIsModalUpdate(true);
  };

  const closeModal = () => {
    setIsModalUpdate(false);
    setSelectedUser(null);
  };

  const handleUpdateUser = async (userData: any) => {
    if (!userData || !userData.id) {
      console.error("ID de l'utilisateur manquant", userData);
      return;
    }
    if (!token) {
      Alert.alert('Erreur', "Token d'authentification manquant.");
      return;
    }
    console.log(userData);
    try {
      const updatedUser = await updateUser(userData.id, userData, token);
      setUsers((prevUsers) =>
        prevUsers.map((userInfo: { id: any; }) => (userInfo.id === userData.id ? updatedUser : userInfo))
      );
      console.log('Utilisateur mis à jour', updatedUser);
    } catch (error) {
      console.error('Erreur lors de la mise à jour', error);
    }
    closeModal();
  };

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
    <View>
      <View style={styles.container}>
        <View style={styles.textContainer}>
          <ThemedText type="title">Profil</ThemedText>
          <View style={styles.avatarContainer}>
            <FontAwesome name="user-circle" size={100} color="black" style={styles.avatar} />
          </View>
          <Text style={styles.description}>
            <ThemedText style={styles.description} type="defaultSemiBold">Nom: </ThemedText>{userInfo.name}
          </Text>
          <Text style={styles.description}>
            <ThemedText style={styles.description} type="defaultSemiBold">Prénom: </ThemedText>{userInfo.lastname}
          </Text>
          <Text style={styles.description}>
            <ThemedText style={styles.description} type="defaultSemiBold">Email: </ThemedText>{userInfo.email}
          </Text>
          <Text style={styles.description}>
            <ThemedText style={styles.description} type="defaultSemiBold">Tel: </ThemedText>{userInfo.phoneNumber}
          </Text>
        </View>
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
    width:'40%',
    height:'90%',
    alignItems:'center',
    justifyContent:'center',
    alignSelf:'center'
  },
  textContainer: {
    padding: 10,
  },

  description: {
    fontSize: 24,
    color: 'black',
    margin:10,
    textAlign:'center'
  },
  error: {
    color: 'red',
    textAlign: 'center',
    marginTop: 20,
  },
  loading: {
    color: 'black',
    textAlign: 'center',
    marginTop: 20,
  },
  avatarContainer: {
    marginBottom: 20,
  },
  avatar: {
    marginTop: 30,
    alignSelf:'center',
  },
  updateButton: {
    backgroundColor: '#9CCC65', // Couleur verte pour le bouton Modifier
    padding: 10,
    marginHorizontal: 10,
    borderRadius: 5,
    width: '20%',
    alignItems: 'center',
    justifyContent: 'center',
    flexDirection: 'row',
    alignSelf:'center'
  },
  buttonText: {
    color: '#fff',
    fontSize: 16,
    textAlign: 'center',
    marginLeft: 8, // Espacement entre le texte et l'icône
  },
  modalContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: 'rgba(0, 0, 0, 0.5)',
  },
  modalContent: {
    backgroundColor: 'white',
    borderRadius: 10,
    padding: 20,
    width: '60%',
    height: '90%',
    alignItems: 'center',
  },
});

function setUsers(arg0: (prevUsers: any) => any) {
  throw new Error('Function not implemented.');
}

