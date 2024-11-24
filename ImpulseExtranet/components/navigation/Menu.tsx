import React, {useEffect, useState} from 'react';
import { View, Text, TouchableOpacity, StyleSheet, Modal } from 'react-native';
import { Feather,FontAwesome, Ionicons,MaterialCommunityIcons,AntDesign } from '@expo/vector-icons';
import { logout } from '@/components/LoginRequest'; // Assurez-vous du chemin correct
import AsyncStorage from "@react-native-async-storage/async-storage";




interface MenuHamburgerProps {
  setCurrentScreen: (screen:  'YourProperties' | 'Profile' |'Contact' | 'Logout') => void;
}

const MenuHamburger: React.FC<MenuHamburgerProps> = ({ setCurrentScreen }) => {
  const [isVisible, setIsVisible] = useState(false);

  const toggleMenu = () => {
    setIsVisible(!isVisible);
  };

  const handleNavigation = async (screen:  'YourProperties' | 'Profile' | 'Contact' | 'Logout') => {
    setIsVisible(false);

    if (screen === 'Logout') {
      // Appeler la fonction logout
      await logout();
      console.log('Utilisateur déconnecté avec succès');
      // Rediriger ou mettre à jour l'état en conséquence
    }

    setCurrentScreen(screen);
  };


  return (
    <View style={styles.container}>
      <TouchableOpacity onPress={toggleMenu}>
        <Ionicons name="menu" size={28} color="black" />
      </TouchableOpacity>
      <Modal visible={isVisible} transparent={true} onRequestClose={toggleMenu}>
        <TouchableOpacity style={styles.overlay} onPress={toggleMenu} />
        <View style={styles.menu}>

          <TouchableOpacity style={styles.menuItem} onPress={() => handleNavigation('YourProperties')}>
            <Text style={styles.menuText}> <Feather name="home" size={24} color="black" /> Vos biens</Text>
          </TouchableOpacity>
          <TouchableOpacity style={styles.menuItem} onPress={() => handleNavigation('Profile')}>
            <Text style={styles.menuText}><FontAwesome name="user" size={24} color="black" /> Profil</Text>
          </TouchableOpacity>
          <TouchableOpacity style={styles.menuItem} onPress={() => handleNavigation('Contact')}>
            <Text style={styles.menuText}><AntDesign name="contacts" size={24} color="black" /> Contact</Text>
          </TouchableOpacity>
          <TouchableOpacity style={styles.menuItem} onPress={() => handleNavigation('Logout')}>
            <Text style={styles.menuText}><MaterialCommunityIcons name="logout" size={24} color="black" />Déconnexion</Text>
          </TouchableOpacity>
        </View>
      </Modal>
    </View>
  );
};

export default MenuHamburger;
const styles = StyleSheet.create({
  container: {
    position: 'absolute',
    top: 10,
    right: 10,
    zIndex: 100,
  },
  overlay: {
    flex: 1,
    backgroundColor: 'rgba(0, 0, 0, 0.5)',
  },
  menu: {
    position: 'absolute',
    top: 50,
    right: 10,
    backgroundColor: '#fff',
    borderRadius: 12,
    paddingVertical: 10,
    width: 180,
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 4 },
    shadowOpacity: 0.25,
    shadowRadius: 4,
    elevation: 5, // Pour les ombres sur Android
  },
  menuItem: {
    paddingVertical: 12,
    paddingHorizontal: 20,
    alignItems: 'flex-start',
    borderBottomWidth: 1,
    borderBottomColor: '#f0f0f0',
  },
  menuText: {
    color: '#333',
    fontSize: 16,
    fontWeight: '500',
  },
  menuItemLast: {
    borderBottomWidth: 0, // Pas de ligne pour le dernier élément
  },
});
