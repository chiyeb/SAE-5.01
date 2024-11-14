import React from 'react';
import { View, TouchableOpacity, Text, StyleSheet } from 'react-native';

// Définition du type pour les props
interface TabSelectorProps {
  selectedTab: string;
  onTabSelect: (tab: string) => void;
}

const TabSelector: React.FC<TabSelectorProps> = ({ selectedTab, onTabSelect }) => {
  return (
    <View style={styles.tabs}>
      <TouchableOpacity
        onPress={() => onTabSelect('vente')}
        style={[styles.tab, selectedTab === 'vente' && styles.activeTab]}>
        <Text style={styles.tabText}>Vente</Text>
      </TouchableOpacity>
      <TouchableOpacity
        onPress={() => onTabSelect('location')}
        style={[styles.tab, selectedTab === 'location' && styles.activeTab]}>
        <Text style={styles.tabText}>Location</Text>
      </TouchableOpacity>
    </View>
  );
};

const styles = StyleSheet.create({
  tabs: {
    flexDirection: 'row',
    marginBottom: 20,
    alignSelf: 'center',
  },
  tab: {
    padding: 10,
    marginHorizontal: 5,
    borderRadius: 5,
    
  },
  activeTab: {
    backgroundColor: '#ddd',
  },
  tabText: {
    fontSize: 16,
    color: '#333',
  },
});

export default TabSelector;
