import React, { useState } from 'react';
import { View, Text, TouchableOpacity, StyleSheet, Modal } from 'react-native';
import { Ionicons } from '@expo/vector-icons';

interface MenuHamburgerProps {
  setCurrentScreen: (screen: 'Home' | 'Profile' | 'Settings' | 'Logout') => void;
}

const MenuHamburger: React.FC<MenuHamburgerProps> = ({ setCurrentScreen }) => {
  const [isVisible, setIsVisible] = useState(false);

  const toggleMenu = () => {
    setIsVisible(!isVisible);
  };

  const handleNavigation = (screen: 'Home' | 'Profile' | 'Settings' | 'Logout') => {
    setIsVisible(false);
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
          <TouchableOpacity style={styles.menuItem} onPress={() => handleNavigation('Home')}>
            <Text style={styles.menuText}>Accueil</Text>
          </TouchableOpacity>
          <TouchableOpacity style={styles.menuItem} onPress={() => handleNavigation('Profile')}>
            <Text style={styles.menuText}>Profil</Text>
          </TouchableOpacity>
          <TouchableOpacity style={styles.menuItem} onPress={() => handleNavigation('Settings')}>
            <Text style={styles.menuText}>Contact</Text>
          </TouchableOpacity>
          <TouchableOpacity style={styles.menuItem} onPress={() => handleNavigation('Logout')}>
            <Text style={styles.menuText}>Déconnexion</Text>
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
    borderRadius: 8,
    paddingVertical: 10,
    width: 150,
  },
  menuItem: {
    paddingVertical: 10,
    paddingHorizontal: 20,
    alignItems: 'center',
  },
  menuText: {
    color: 'white',
    fontSize: 16,
  },
});
