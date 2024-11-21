// Buttons.js (bouton pour ajouter un bien)
import React, { useState } from 'react';
import { Button, StyleSheet, View } from 'react-native';
interface ButtonsProps {
  onPress: () => void;
}

export default function Buttons({ onPress }: ButtonsProps) {
  return (
    <View style={styles.buttonContainer}>
      <Button
        title="Ajouter"
        color="gray"
        onPress={onPress} // Appelle la fonction passée en prop
      />
    </View>
  );
}

const styles = StyleSheet.create({
  buttonContainer: {
    margin: 10,
    borderRadius: 5,
    overflow: 'hidden',
    
  },
});
