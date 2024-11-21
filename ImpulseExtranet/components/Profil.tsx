import React from 'react';
import { View, Text, StyleSheet} from 'react-native';


export default function Profil() {
  return (
    <View style={styles.container}>
        <View style={styles.textContainer}>
          <Text style={styles.title}>Profil</Text>
          <Text style={styles.description}>
            Nom
          </Text>
          <Text style={styles.description}>
            Prenom
          </Text>
          <Text style={styles.description}>
            Mail
          </Text>
          <Text style={styles.description}>
            Tel 
          </Text>
        </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    margin: 16,
    borderRadius: 10,
    overflow: 'hidden',
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.3,
    shadowRadius: 4,
    elevation: 5, // Ombre pour Android
    width:'20%',
    height:'30%',
    backgroundColor: 'rgba(0, 0, 0, 0.6)',
  },
  textContainer: {
    padding: 10,
  },
  title: {
    fontSize: 18,
    fontWeight: 'bold',
    color: 'white',
    marginBottom: 4,
    textAlign:'center'
  },
  description: {
    fontSize: 14,
    color: 'white',
    margin:10,
    textAlign:'center'
  },
});
