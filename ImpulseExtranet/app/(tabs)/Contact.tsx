import React from 'react';
import { View, StyleSheet } from 'react-native';
import Profil from '@/components/Profil';
import ContactForm from '@/components/ContactForm';
import MenuHamburger from '@/components/navigation/Menu';
import { ScrollView } from 'react-native-gesture-handler';
import { ThemedText } from '@/components/ThemedText';
import email from 'react-native-email';

// Fonction pour envoyer un email
const sendEmail = (contactData: { name: any; message: any; }) => {
  const { name, message } = contactData;
  const to = ['example@example.com']; // Email du destinataire
  email(to, {
    subject: `Soumission du formulaire de contact de ${name}`,
    body: `Nom: ${name}\nMessage: ${message}`,
  }).catch(console.error);
};

export default function Contact() {
  return (
    <View style={styles.container}>
      <MenuHamburger />
      <ScrollView showsHorizontalScrollIndicator={false}>
        <View style={styles.formContainer}>
          <ThemedText type="title" style={styles.title}>Contacter mon agence</ThemedText>
          <ThemedText type="defaultSemiBold" style={styles.contactInfo}>
            Numéro : 06 06 06 06 06
          </ThemedText>
          <ThemedText type="defaultSemiBold" style={styles.contactInfo}>
            Email de l'agence : example@example.com
          </ThemedText>

          {/* Formulaire de contact avec soumission */}
          <ContactForm onSubmit={sendEmail} />
        </View>
      </ScrollView>
      <Profil />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center', // Centrer horizontalement
    justifyContent: 'center', // Centrer verticalement
  },
  formContainer: {
    width: '80%', // Taille du formulaire (ajustez selon votre besoin)
    alignItems: 'center', // Centrer les éléments à l'intérieur
  },
  title: {
    marginBottom: 10, // Espacement entre le titre et les autres textes
    textAlign: 'center',
  },
  contactInfo: {
    textAlign: 'center', // Centrer les informations de contact
    marginBottom: 5, // Espacement entre chaque ligne de texte
  },
});
