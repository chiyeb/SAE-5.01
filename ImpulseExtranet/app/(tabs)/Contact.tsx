import React from 'react';
import { View,  StyleSheet, } from 'react-native';
import Profil from '@/components/Profil';
import ContactForm from '@/components/ContactForm';
import { ScrollView } from 'react-native-gesture-handler';
import { ThemedText } from '@/components/ThemedText';
import email from 'react-native-email';

// Fonction pour envoyer un email
const sendEmail = (contactData: { name: any; subject :any; message: any; }) => {
  const { name, subject,message } = contactData;
  const to = ['example@example.com']; // Email du destinataire
  email(to, {
    subject: `${subject}`,
    body: `Nom: ${name}\nMessage: ${message}`,
  }).catch(console.error);
};

export default function Contact() {

  return (
    <View style={styles.container}>
      
      <ScrollView showsHorizontalScrollIndicator={false}>

      <View style={styles.formContainer}>
          <ThemedText type="title" style={styles.title}>Contacter mon agence</ThemedText>
          <ThemedText type="defaultSemiBold" style={styles.contactInfo}>
            Numero : 06 06 06 06 06
          </ThemedText>
          <ThemedText type="defaultSemiBold" style={styles.contactInfo}>
            Mail de agence : example@example.com
          </ThemedText>
        <ContactForm onSubmit={(sendEmail)} />
      </View>
      </ScrollView>
      <Profil/>
      
    </View>
  );
}


const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    flexDirection:'row',
    justifyContent:'space-between',
  },
  formContainer: {
    width: '50%',
    height: '80%',
    marginLeft:'20%',
    alignSelf: 'center', // This will center the ContactForm if there is space
  },
  title: {
    marginBottom: 10, // Space between title and other text
  },
  contactInfo: {
    textAlign: 'center', // Center contact information text
    marginBottom: 5, // Space between lines of text
  },
});
