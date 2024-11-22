import React, { useState } from "react";
import { View, TextInput, Button, Text, StyleSheet } from "react-native";

const LoginScreen = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [token, setToken] = useState<string | null>(null);
  const [userInfo, setUserInfo] = useState<object | null>(null);
  const [error, setError] = useState<string | null>(null);

  const handleLogin = async () => {
    try {
      const response = await fetch("http://localhost:8080/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username: email, password }),
      });

      if (!response.ok) {
        throw new Error("Failed to login. Check your credentials.");
      }

      const token = await response.text();
      setToken(token);
      setError(null);
    } catch (err: any) {
      setError(err.message);
    }
  };

  const fetchUserInfo = async () => {
    try {
      if (!token) return;

      const response = await fetch("http://localhost:8080/user/current", {
        method: "GET",
        headers: { Authorization: `Bearer ${token}` },
      });

      if (!response.ok) {
        throw new Error("Failed to fetch user info.");
      }

      const data = await response.json();
      setUserInfo(data);
      setError(null);
    } catch (err: any) {
      setError(err.message);
    }
  };

  return (
    <View style={styles.container}>
      <View style={styles.loginBox}>
        <Text style={styles.header}>Login</Text>

        <View style={styles.inputWrapper}>
          <TextInput
            style={styles.inputField}
            value={email}
            onChangeText={setEmail}
            placeholder="Email"
            placeholderTextColor="#999"
            keyboardType="email-address"
            autoCapitalize="none"
          />
        </View>

        <View style={styles.inputWrapper}>
          <TextInput
            style={styles.inputField}
            value={password}
            onChangeText={setPassword}
            placeholder="Password"
            placeholderTextColor="#999"
            secureTextEntry
          />
        </View>

        <Button title="Login" onPress={handleLogin} color="#4169e1" />
      </View>

      {token && (
        <View style={styles.infoBox}>
          <Text style={styles.infoText}>Token: {token}</Text>
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

export default LoginScreen;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
    backgroundColor: "#121212", // Background for dark theme
  },
  loginBox: {
    width: "80%",
    maxWidth: 400,
    padding: 40,
    backgroundColor: "rgba(0, 0, 0, 0.8)", // Semi-transparent black background
    borderRadius: 10,
    shadowColor: "#000",
    shadowOffset: { width: 0, height: 4 },
    shadowOpacity: 0.3,
    shadowRadius: 10,
    elevation: 5, // Shadow for Android
  },
  header: {
    fontSize: 24,
    fontWeight: "bold",
    marginBottom: 30,
    color: "#ffffff",
    textAlign: "center",
  },
  inputWrapper: {
    width: "100%",
    marginBottom: 20,
  },
  inputField: {
    width: "100%",
    paddingVertical: 10,
    paddingHorizontal: 15,
    fontSize: 16,
    color: "#fff",
    borderBottomWidth: 1,
    borderBottomColor: "#ffffff",
    backgroundColor: "transparent",
  },
  infoBox: {
    marginTop: 20,
    padding: 10,
    backgroundColor: "rgba(255, 255, 255, 0.1)", // Light overlay
    borderRadius: 5,
    width: "90%",
    maxWidth: 400,
  },
  infoText: {
    color: "#fff",
    textAlign: "center",
  },
  error: {
    marginTop: 20,
    color: "red",
    textAlign: "center",
  },
});
