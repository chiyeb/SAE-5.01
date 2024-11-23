// Buttons.js (bouton pour ajouter un bien)
import React, { useState } from 'react';
import { Button, StyleSheet, View } from 'react-native';
import FontAwesome6 from '@expo/vector-icons/FontAwesome6';
interface ButtonsProps {
  onPress: () => void;
}

export default function Buttons({ onPress }: ButtonsProps) {
  return (
    <View style={styles.buttonContainer}>
      <FontAwesome6 name="add" size={24} color="white" />
      <Button
        title="Ajouter"
        color={"gray"}
        onPress={onPress} // Appelle la fonction passée en prop
        
      />
    </View>
  );
}

const styles = StyleSheet.create({
  buttonContainer: {
    margin: 10,
    paddingHorizontal: 5,
    borderRadius: 5,
    overflow: 'hidden',
    alignItems:'center',
    flexDirection:'row',
    backgroundColor:'gray',
    
  },
});
