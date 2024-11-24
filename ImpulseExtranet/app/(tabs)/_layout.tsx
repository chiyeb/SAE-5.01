import { Stack, useRouter } from 'expo-router';
import React from 'react';
import MenuHamburger from '@/components/navigation/Menu'; // Assurez-vous que le chemin est correct
import { Colors } from '@/constants/Colors';
import { useColorScheme } from '@/hooks/useColorScheme';

export default function AppLayout() {
  const colorScheme = useColorScheme();
  const router = useRouter();

  const handleNavigation = (screen: 'Home' | "YourProperties" | 'Profile' | 'Logout') => {
    // Rediriger vers l'écran correspondant
    switch (screen) {
      case 'Home':
        router.push('/HomeScreen');
        break;
      case 'YourProperties':
          // @ts-ignore
          router.push('/YourPropertiesScreen');
        break;
      case 'Profile':
        router.push('/Users');
        break;
      case 'Logout':
        router.push('/Login');
        break;
      default:
        console.warn('Écran non trouvé');
    }
  };

  return (
    <Stack
      screenOptions={{
        headerShown: true,
        headerRight: () => <MenuHamburger setCurrentScreen={handleNavigation} />, // Connecté à la navigation
        headerStyle: {
          backgroundColor: 'white',
        },
        headerTintColor: Colors[colorScheme ?? 'light'].text,
      }}
    >
      <Stack.Screen
        name="Login"
        options={{
          title: 'Connexion',
        }}
      />
    </Stack>
  );
}
