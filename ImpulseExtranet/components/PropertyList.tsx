import React, { useState, useEffect } from 'react';
import { View, FlatList, Text, StyleSheet, ActivityIndicator } from 'react-native';
import TabSelector from '../components/TabSelector';
import { getAllProperties } from '../services/api';

const PropertyList = () => {
  const [selectedTab, setSelectedTab] = useState('purchasable'); // Vente par défaut
  const [properties, setProperties] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    const fetchProperties = async () => {
      setLoading(true);
      const data = await getAllProperties(selectedTab);
      setProperties(data || []);
      setLoading(false);
    };

    fetchProperties();
  }, [selectedTab]);

  const renderProperty = ({ item }) => (
    <View style={styles.propertyItem}>
      <Text style={styles.propertyTitle}>{item.title}</Text>
      <Text>{item.price} €</Text>
    </View>
  );

  return (
    <View style={styles.container}>
      <TabSelector selectedTab={selectedTab} onTabSelect={setSelectedTab} />
      {loading ? (
        <ActivityIndicator size="large" color="#007BFF" />
      ) : (
        <FlatList
          data={properties}
          keyExtractor={(item) => item.id.toString()}
          renderItem={renderProperty}
        />
      )}
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 20,
    backgroundColor: '#f9f9f9',
  },
  propertyItem: {
    padding: 15,
    borderBottomWidth: 1,
    borderBottomColor: '#ddd',
  },
  propertyTitle: {
    fontSize: 18,
    fontWeight: 'bold',
    color: '#333',
  },
});

export default PropertyList;
