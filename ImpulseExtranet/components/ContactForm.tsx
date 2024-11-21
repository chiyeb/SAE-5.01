import React, { useState } from 'react';
import { View, Text, TextInput, StyleSheet, TouchableOpacity, ScrollView } from 'react-native';
import { ThemedText } from './ThemedText';  // Si vous avez ce composant, vous pouvez l'utiliser



// Définition des props pour le formulaire
interface ContactFormProps {
  onSubmit: (contactData: any) => void;
}

const ContactForm: React.FC<ContactFormProps> = ({ onSubmit }) => {
  // États pour les champs du formulaire
  const [name, setName] = useState<string>('');
  const [email, setEmail] = useState<string>('');
  const [subject, setSubject] = useState<string>('');
  const [message, setMessage] = useState<string>('');

  // Fonction de soumission du formulaire
  const handleSubmit = () => {
    if (name && email && subject && message) {
      const contactData = { name, email, subject, message };
      onSubmit(contactData);
    } else {
      alert("Veuillez remplir tous les champs.");
    }
  };

  return (
    <View style={styles.container}>
        
        <View style={styles.textContainer}>
          <ThemedText type="defaultSemiBold">Nom</ThemedText>
          <TextInput
            style={styles.input}
            value={name}
            onChangeText={setName}
            placeholder="Votre nom"
          />

          <ThemedText type="defaultSemiBold">Email</ThemedText>
          <TextInput
            style={styles.input}
            value={email}
            onChangeText={setEmail}
            placeholder="Votre email"
            keyboardType="email-address"
          />

          <ThemedText type="defaultSemiBold">Sujet</ThemedText>
          <TextInput
            style={styles.input}
            value={subject}
            onChangeText={setSubject}
            placeholder="Sujet de votre message"
          />

          <ThemedText type="defaultSemiBold">Message</ThemedText>
          <TextInput
            style={[styles.input, styles.textArea]}
            value={message}
            onChangeText={setMessage}
            placeholder="Votre message"
            multiline
          />
        </View>

        {/* Bouton d'envoi du formulaire */}
        <TouchableOpacity onPress={handleSubmit} style={styles.button}>
          <Text style={styles.buttonText}>Envoyer le message</Text>
        </TouchableOpacity>
    </View>
  );
};

export default ContactForm;

const styles = StyleSheet.create({
  container: {
    padding: 20,
    borderRadius: 15,
    backgroundColor: '#fafafa', // Fond léger pour un look moderne
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 3 },
    shadowOpacity: 0.2,
    shadowRadius: 5,
    elevation: 8, // Ombre douce pour un effet subtil
    width: '100%',
    height: '100%',
    marginVertical: 15,
    marginHorizontal: '5%',
  },

  textContainer: {
    marginTop: 10,
    marginBottom: 5,
  },

  input: {
    borderBottomWidth: 2,
    borderColor: '#ddd',
    paddingVertical: 12,
    fontSize: 16,
    marginBottom: 20,
    color: '#333',
    fontFamily: 'Arial',
  },

  textArea: {
    height: 100, // Hauteur plus grande pour le message
    textAlignVertical: 'top', // Alignement du texte en haut
  },

  button: {
    backgroundColor: '#007BFF',
    padding: 10,
    borderRadius: 5,
    marginVertical: 15,
    width: '50%',
    alignSelf: 'center',
    justifyContent: 'center',
  },

  buttonText: {
    color: '#fff',
    fontSize: 16,
    textAlign: 'center',
  },
});
