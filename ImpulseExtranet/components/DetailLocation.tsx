import React from 'react';
import { View, Text, StyleSheet, TextInput } from 'react-native';

interface DetailLocationProps {
  loyer: number;
  caution: number;
  charges: number;
  onChangeLoyer: (text: string) => void;
  onChangeCaution: (text: string) => void;
  onChangeCharges: (text: string) => void;
}

const DetailLocation: React.FC<DetailLocationProps> = ({
  loyer,
  caution,
  charges,
  onChangeLoyer,
  onChangeCaution,
  onChangeCharges,
}) => {
  return (
    <View style={styles.container}>
      <Text style={styles.label}>Loyer</Text>
      <TextInput
        style={styles.input}
        value={loyer.toString()}
        onChangeText={onChangeLoyer}
        placeholder="Loyer"
        keyboardType="numeric"
      />

      <Text style={styles.label}>Caution</Text>
      <TextInput
        style={styles.input}
        value={caution.toString()}
        onChangeText={onChangeCaution}
        placeholder="Caution"
        keyboardType="numeric"
      />

      <Text style={styles.label}>Charges</Text>
      <TextInput
        style={styles.input}
        value={charges.toString()}
        onChangeText={onChangeCharges}
        placeholder="Charges"
        keyboardType="numeric"
      />
    </View>
  );
};

export default DetailLocation;

const styles = StyleSheet.create({
  container: {
    padding: 10,
  },
  label: {
    fontSize: 16,
    fontWeight: 'bold',
    marginVertical: 5,
  },
  input: {
    borderWidth: 1,
    borderColor: '#ccc',
    padding: 10,
    borderRadius: 5,
    marginVertical: 8,
  },
});
