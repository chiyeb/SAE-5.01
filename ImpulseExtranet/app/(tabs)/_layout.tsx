import { Stack } from 'expo-router';
import React from 'react';
import MenuHamburger from '@/components/navigation/Menu'; // Assurez-vous que le chemin est correct
import { Colors } from '@/constants/Colors';
import { useColorScheme } from '@/hooks/useColorScheme';

export default function AppLayout() {
  const colorScheme = useColorScheme();

  return (
    <Stack
      screenOptions={{
        headerShown: true, // Activer l'en-tête
        headerRight: () => <MenuHamburger />, // Placer uniquement le menu hamburger dans l'en-tête
        headerStyle: {
          backgroundColor: 'white',
        },
        headerTintColor: Colors[colorScheme ?? 'light'].text,
      }}
    >
      <Stack.Screen
        name="index"
        options={{
          title: 'Accueil', // Titre de l'en-tête
        }}
      />
    </Stack>
  );
}
