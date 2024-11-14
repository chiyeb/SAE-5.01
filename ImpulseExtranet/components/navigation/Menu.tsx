// MenuHamburger.js
import React, { useState } from 'react';
import { View, Text, TouchableOpacity, StyleSheet, Modal } from 'react-native';
import { Ionicons } from '@expo/vector-icons';

export default function MenuHamburger() {
  const [isVisible, setIsVisible] = useState(false);

  const toggleMenu = () => {
    setIsVisible(!isVisible);
  };

  return (
    
    //Menu hamburger
    <View style={styles.container}>
      <TouchableOpacity onPress={toggleMenu}>
        <Ionicons name="menu" size={28} color="black" />
      </TouchableOpacity>
      <Modal
      //Menu 
        visible={isVisible}
        transparent={true}
        //animationType="slide"
        onRequestClose={toggleMenu}
      >
        <TouchableOpacity style={styles.overlay} onPress={toggleMenu} />
        <View style={styles.menu}>
          <TouchableOpacity style={styles.menuItem} onPress={toggleMenu}>
            <Text style={styles.menuText}>Accueil</Text>
          </TouchableOpacity>
          <TouchableOpacity style={styles.menuItem} onPress={toggleMenu}>
            <Text style={styles.menuText}>Profil</Text>
          </TouchableOpacity>
          <TouchableOpacity style={styles.menuItem} onPress={toggleMenu}>
            <Text style={styles.menuText}>Paramètre</Text>
          </TouchableOpacity>
          <TouchableOpacity style={styles.menuItem} onPress={toggleMenu}>
            <Text style={styles.menuText}>Déconnexion</Text>
          </TouchableOpacity>
        </View>
      </Modal>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    position: 'absolute',
    right: 20
  },
  overlay: {
    flex: 1,
  },
  menu: {
    position: 'absolute',
    top: 50,
    right: 16,
    backgroundColor: 'black',
    borderRadius: 8,
    paddingVertical: 10,
    width: 120,
  },
  menuItem: {
    paddingVertical: 10,
    paddingHorizontal: 20,
    alignItems:'center',
  },
  menuText: {
    color: 'white',
    fontSize: 16,

  },
});
