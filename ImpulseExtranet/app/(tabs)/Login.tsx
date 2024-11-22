import React, { useState } from 'react';
import { TextInput, Button, Text, StyleSheet, View } from 'react-native';
import { useRouter } from 'expo-router'; // Importer le useRouter de Next.js
import { login, getUserInfo } from '@/components/LoginRequest'; // Remplacez par le bon chemin

const LoginScreen = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [token, setToken] = useState<string | null>(null);
  const [userInfo, setUserInfo] = useState<object | null>(null);
  const [error, setError] = useState<string | null>(null);
  
  const router = useRouter(); // Hook pour gérer la navigation avec Next.js

  const handleLogin = async () => {
    setError(null); // Réinitialiser les erreurs

    const result = await login(email, password);

    if (result) {
      setToken(result);
      setError(null); // Réinitialiser les erreurs
      router.push('/HomeScreen'); // Rediriger vers la page d'accueil après la connexion réussie
    } else {
      setError('Échec de la connexion. Vérifiez vos identifiants.');
    }
  };

  const fetchUserInfo = async () => {
    if (!token) {
      setError('Aucun jeton trouvé. Connectez-vous d\'abord.');
      return;
    }

    const info = await getUserInfo();
    if (info) {
      setUserInfo(info);
    } else {
      setError('Erreur lors de la récupération des informations utilisateur.');
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
          placeholderTextColor="#999"
          keyboardType="email-address"
          autoCapitalize="none"
        />

        <TextInput
          style={styles.inputField}
          value={password}
          onChangeText={setPassword}
          placeholder="Password"
          placeholderTextColor="#999"
          secureTextEntry
        />

        <Button title="Login" onPress={handleLogin} color="#4169e1" />
      </View>

      {token && (
        <View style={styles.infoBox}>
          <Button title="Get User Info" onPress={fetchUserInfo} color="#4169e1" />
        </View>
      )}

      {userInfo && (
        <View style={styles.infoBox}>
          <Text style={styles.infoText}>User Info: {JSON.stringify(userInfo)}</Text>
        </View>
      )}

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
    paddingHorizontal: 15,
    fontSize: 16,
    color: '#fff',
    borderBottomWidth: 1,
    borderBottomColor: '#ffffff',
    backgroundColor: 'transparent',
  },
  infoBox: {
    marginTop: 20,
    padding: 10,
    backgroundColor: 'rgba(255, 255, 255, 0.1)', // Light overlay
    borderRadius: 5,
    width: '90%',
    maxWidth: 400,
  },
  infoText: {
    color: '#fff',
    textAlign: 'center',
  },
  error: {
    marginTop: 20,
    color: 'red',
    textAlign: 'center',
  },
});

export default LoginScreen;
