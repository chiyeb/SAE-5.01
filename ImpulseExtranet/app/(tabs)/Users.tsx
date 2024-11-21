import React, { useState, useEffect } from 'react';
import { View, ScrollView, StyleSheet, Modal, Text, TouchableOpacity, Alert } from 'react-native';
import User from '@/components/UsersList';
import { ThemedText } from '@/components/ThemedText';
import Buttons from '@/components/navigation/Buttons';
import DetailUser from '@/components/DetailUsers';
import { createUser, getAllUsers, updateUser, deleteUser } from '@/components/UsersRequest';

export default function Users() {
  const [users, setUsers] = useState<any[]>([]);
  const [isModalVisible, setIsModalVisible] = useState(false);
  const [selectedUser, setSelectedUser] = useState<any | null>(null);
  

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
    // Exemple de création d'utilisateur
    const newUser = {
      name: 'John',
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
    setSelectedUser(null);
  };

  const handleSaveUser = async (userData: any) => {
    if (!userData.name || !userData.moreInformations || !userData.age) {
      console.log('Erreur', 'Veuillez remplir tous les champs obligatoires.');
      return;
    }
    try {
      const createdUser = await createUser(userData);
      setUsers([...users, createdUser]); // Ajoute le nouvel utilisateur à la liste
      console.log('Succès', 'Le User a été ajouté.');
      closeModal();
    } catch (error) {
      console.error('Erreur lors de la création du User :', error);
      Alert.alert('Erreur', 'Impossible d\'ajouter le User.');
    }
  };



  const handleDeleteUser = async (id: string) => {
    try {
      await deleteUser(id);
      setUsers(users.filter((user) => user.id !== id));
      Alert.alert('Succès', 'Le User a été supprimé.');
    } catch (error) {
      console.error('Erreur lors de la suppression :', error);
      Alert.alert('Erreur', 'Impossible de supprimer le User.');
    }
  };

  return (
    <View style={styles.container}>
      <ScrollView>
        <View style={styles.titleContainer}>
          <ThemedText type="title">Utilisateurs</ThemedText>
        </View>
        <View style={styles.stepContainer}>
          <ThemedText type="h2">Mes utilisateurs liste</ThemedText>
          {users.length > 0 ? (
            users.map((user) => (
              <View key={user.id}>
                <User {...user} onPress={() => setSelectedUser(user)} />
                <TouchableOpacity
                  onPress={() => handleDeleteUser(user.id)}
                  style={styles.deleteButton}
                >
                  <Text style={styles.buttonText}>Supprimer</Text>
                </TouchableOpacity>
              </View>
            ))
          ) : (
            <Text>Aucun utilisateur trouvé</Text>
          )}
          <Buttons onPress={handleAddUser} />
        </View>
      </ScrollView>

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
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    backgroundColor: '#fff',
  },
  titleContainer: {
    alignItems: 'center',
    margin: 16,
  },
  stepContainer: {
    alignItems: 'center',
    marginBottom: 16,
  },
  profil: {
    width: '15%',
  },
  modalContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: 'rgba(0, 0, 0, 0.5)', // Semi-transparent background
  },
  modalContent: {
    backgroundColor: 'white',
    borderRadius: 10,
    padding: 20,
    width: '90%',
    height: '90%',
    alignItems: 'center',
  },
  buttonText: {
    color: '#fff',
    fontSize: 16,
    textAlign: 'center',
  },
  deleteButton: {
    backgroundColor: '#dc3545',
    padding: 10,
    borderRadius: 5,
    width: '30%',
    alignSelf: 'center',
  },
});
