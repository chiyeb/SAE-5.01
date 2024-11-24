import React, { useState } from 'react';
import { TextInput, Button, Text, StyleSheet, View,TouchableOpacity } from 'react-native';
import { useRouter } from 'expo-router'; // Importer le useRouter de Next.js
import { login, forgotPassword } from '@/components/LoginRequest'; // Remplacez par le bon chemin
import AsyncStorage from '@react-native-async-storage/async-storage';
import { ThemedText } from '@/components/ThemedText';

const LoginScreen = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [token, setToken] = useState<string | null>(null);
  const [error, setError] = useState<string | null>(null);
  
  const router = useRouter(); // Hook pour gérer la navigation avec Next.js

const handleLogin = async () => {
  setError(null); // Réinitialiser les erreurs

  try {
    const result = await login(email, password);
    console.log('Résultat de login:', result);

    if (result.success) {
      const token = await AsyncStorage.getItem('auth_token');
      setToken(token);
      router.push('/YourPropertiesScreen'); 
    } else {
      setError(result.error || 'Erreur dans le mail ou le mot de passe.');
    }
  } catch (err) {
    console.error('Erreur réseau ou serveur:', err);
    setError('Impossible de se connecter au serveur.');
  }
};

  const handleForgotPassword = async (email: string) => {
    const result = await forgotPassword(email);
    if (result.success) {
      alert("Réinitialisation envoyée : Un email de réinitialisation de mot de passe a été envoyé à l'adresse fournie. Veuillez vérifier votre boîte de réception.");

    } else {
      alert("Erreur : L'adresse email est invalide ou non inscrite. Veuillez vérifier l'email et réessayer.");

    }
  };
  

  return (
    <View style={styles.container}>
      <View style={styles.loginBox}>
        <Text style={styles.header}>Login</Text>

        <TextInput
          style={styles.inputField}
          value={email}
          onChangeText={setEmail}
          placeholder="Email"
          placeholderTextColor="#111a"
          keyboardType="email-address"
          autoCapitalize="none"
        />

        <TextInput
          style={styles.inputField}
          value={password}
          onChangeText={setPassword}
          placeholder="Password"
          placeholderTextColor="#111a"
          secureTextEntry
        />
            <TouchableOpacity style={styles.buttons} onPress={()=>handleForgotPassword(email)}>
              <ThemedText type='link' >Mot de passe oublié</ThemedText>
            </TouchableOpacity>
        
        <Button title="Login" onPress={handleLogin} color="#4169e1" />
      </View>
      {error && <Text style={styles.error}>{error}</Text>}
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#121212', // Dark theme
  },
  loginBox: {
    width: '80%',
    maxWidth: 400,
    padding: 40,
    backgroundColor: 'rgba(0, 0, 0, 0.8)', // Semi-transparent black background
    borderRadius: 10,
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 4 },
    shadowOpacity: 0.3,
    shadowRadius: 10,
    elevation: 5, // For Android
  },
  header: {
    fontSize: 24,
    fontWeight: 'bold',
    marginBottom: 30,
    color: '#ffffff',
    textAlign: 'center',
  },
  inputField: {
    width: '100%',
    paddingVertical: 10,
    paddingHorizontal: 10,
    marginVertical:20 ,
    fontSize: 16,
    color: 'white',
    borderBottomWidth: 1,
    borderBottomColor: '#4169e1',
    backgroundColor: 'rgba(255, 255, 255, 0.5)',
  },
  infoBox: {
    marginTop: 20,
    padding: 10,
    marginBottom:15 ,
    backgroundColor: 'rgba(255, 255, 255, 0.8)', // Light overlay
    borderRadius: 5,
    width: '90%',
    maxWidth: 400,
  },
  infoText: {
    color: 'black',
    textAlign: 'center',
  },
  error: {
    marginTop: 20,
    color: 'red',
    textAlign: 'center',
  },
  buttons: {
    paddingVertical: 12,
    paddingHorizontal: 20,
    alignItems: 'center',
    marginVertical: 10,
    color:'white',
  },
});

export default LoginScreen;
