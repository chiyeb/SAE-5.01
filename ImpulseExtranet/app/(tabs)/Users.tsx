import React, { useState, useEffect } from 'react';
import { View, ScrollView, StyleSheet, Modal, Text, TouchableOpacity, Alert, Animated } from 'react-native';
import User from '@/components/UsersList';
import { ThemedText } from '@/components/ThemedText';
import Buttons from '@/components/navigation/Buttons';
import DetailUser from '@/components/DetailUsers';
import { createUser, getAllUsers, updateUser, deleteUser } from '@/components/UsersRequest';
import AsyncStorage from '@react-native-async-storage/async-storage';
import ButtonUpdateDelete from '@/components/navigation/ButtonUpdateDelete';

export default function Users() {
  const [users, setUsers] = useState<any[]>([]);
  const [isModalVisible, setIsModalVisible] = useState(false);
  const [isModalUpdate, setIsModalUpdate] = useState(false);
  const [selectedUser, setSelectedUser] = useState<any | null>(null);
  const [token, setToken] = useState<string | null>(null); // Stocker le token ici
  const scrollY = new Animated.Value(0); // Suivi du défilement

  useEffect(() => {
    const fetchToken = async () => {
      const storedToken = await AsyncStorage.getItem('auth_token');
      setToken(storedToken);
    };

    fetchToken();
  }, []);

  useEffect(() => {
    const fetchUsers = async () => {
      try {
        const data = await getAllUsers();
        if (Array.isArray(data)) {
          setUsers(data);
        } else {
          console.error('Données invalides reçues:', data);
          setUsers([]);
        }
      } catch (error) {
        console.error('Erreur lors de la récupération des utilisateurs :', error);
      }
    };

    fetchUsers();
  }, []);

  const handleAddUser = async () => {
    if (!token) {
      Alert.alert('Erreur', "Token d'authentification manquant.");
      return;
    }
    const newUser = {
      id: '1234325346556',
      name: 'John',
      lastname: 'Doe',
      email: 'john.doe@example.com',
      age: 30,
      phoneNumber: 6123456,
      moreInformations: 'This is a test user.',
    };
    setSelectedUser(newUser);
    setIsModalVisible(true);
  };

  const closeModal = () => {
    setIsModalVisible(false);
    setIsModalUpdate(false);
    setSelectedUser(null);
  };

  const handleSaveUser = async (userData: any) => {
    if (!userData.name || !userData.moreInformations || !userData.age) {
      console.log('Erreur', 'Veuillez remplir tous les champs obligatoires.');
      return;
    }
    if (!token) {
      Alert.alert('Erreur', "Token d'authentification manquant.");
      return;
    }
    try {
      const createdUser = await createUser(userData, token);
      setUsers([...users, createdUser]);
      console.log('Succès', 'Le User a été ajouté.');
      closeModal();
    } catch (error) {
      console.error('Erreur lors de la création du User :', error);
      Alert.alert('Erreur', "Impossible d'ajouter le User.");
    }
  };

  const openModalForUpdate = (id: string, userData: any) => {
    setSelectedUser(userData);
    setIsModalUpdate(true);
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

    try {
      const updatedUser = await updateUser(userData.id, userData, token);
      setUsers((prevUsers) =>
        prevUsers.map((user) => (user.id === userData.id ? updatedUser : user))
      );
      console.log('Utilisateur mis à jour', updatedUser);
    } catch (error) {
      console.error('Erreur lors de la mise à jour', error);
    }
    closeModal();
  };

  const handleDeleteUser = async (id: string) => {
    if (!token) {
      Alert.alert('Erreur', "Token d'authentification manquant.");
      return;
    }
    try {
      await deleteUser(id, token);
      setUsers(users.filter((user) => user.id !== id));
      Alert.alert('Succès', 'Le User a été supprimé.');
    } catch (error) {
      console.error('Erreur lors de la suppression :', error);
      Alert.alert('Erreur', "Impossible de supprimer le User.");
    }
  };
  // Couleur d'arrière-plan qui change en fonction du défilement
  const getBackgroundColor = scrollY.interpolate({
    inputRange: [0, 200],  // Plage de défilement
    outputRange: ['#f5e6ab', '#dba617'],  // Couleurs de fond
    extrapolate: 'clamp',  // Empêche l'extrapolation
  });

  return (
    <View style={styles.container}>
      <Animated.View style={[styles.background, { backgroundColor: getBackgroundColor }]}>
        <ScrollView
          contentContainerStyle={{ paddingBottom: 50 }}
          onScroll={Animated.event(
            [{ nativeEvent: { contentOffset: { y: scrollY } } }],
            { useNativeDriver: false }
          )}
          scrollEventThrottle={16}
        >
        <View style={styles.titleContainer}>
          <ThemedText type="title">Utilisateurs</ThemedText>
        </View>
        <View style={styles.stepContainer}>
          <ThemedText type="h2">Mes utilisateurs liste</ThemedText>
          {users.length > 0 ? (
            users.map((user) => (
              <View key={user.id}>
                <User {...user} onPress={() => setSelectedUser(user)} />
                <ButtonUpdateDelete onUpdate={() => openModalForUpdate(user.id, user)} onDelete={ () => handleDeleteUser(user.id)}/>
              </View>
            ))
          ) : (
            <Text>Aucun utilisateur trouvé</Text>
          )}
          <Buttons onPress={handleAddUser} />
        </View>
      </ScrollView>

      {/* Modal pour ajouter un utilisateur */}
      <Modal visible={isModalVisible} animationType="slide" transparent={true} onRequestClose={closeModal}>
        <View style={styles.modalContainer}>
          <View style={styles.modalContent}>
            {selectedUser ? (
              <DetailUser
                {...selectedUser}
                onSaveUser={handleSaveUser}
                onDeleteUser={closeModal}
              />
            ) : (
              <Text>Aucun utilisateur sélectionné</Text>
            )}
          </View>
        </View>
      </Modal>

      {/* Modal pour mettre à jour un utilisateur */}
      <Modal visible={isModalUpdate} animationType="slide" transparent={true} onRequestClose={closeModal}>
        <View style={styles.modalContainer}>
          <View style={styles.modalContent}>
            {selectedUser ? (
              <DetailUser
                {...selectedUser}
                onSaveUser={(userData) => handleUpdateUser(userData)}
                onDeleteUser={closeModal}
              />
            ) : (
              <Text>Aucun utilisateur sélectionné</Text>
            )}
          </View>
        </View>
      </Modal>
      </Animated.View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
  },
  titleContainer: {
    alignItems: 'center',
    margin: 16,
  },
  stepContainer: {
    alignItems: 'center',
    marginBottom: 16,
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
  background: {
    flex: 1,
    width: '100%',
  },
});
