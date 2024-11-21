import React from 'react';
import { View, TouchableOpacity, Text, StyleSheet } from 'react-native';

interface TabSelectorProps {
  selectedTab: string;
  onTabSelect: (tab: string) => void;
}

const TabSelector: React.FC<TabSelectorProps> = ({ selectedTab, onTabSelect }) => {
  return (
    <View style={styles.tabs}>
      <TouchableOpacity
        onPress={() => onTabSelect('purchasable')}
        style={[styles.tab, selectedTab === 'purchasable' && styles.activeTab]}>
        <Text style={[styles.tabText, selectedTab === 'purchasable' && styles.activeTabText]}>
          Vente
        </Text>
      </TouchableOpacity>
      <TouchableOpacity
        onPress={() => onTabSelect('rental')}
        style={[styles.tab, selectedTab === 'rental' && styles.activeTab]}>
        <Text style={[styles.tabText, selectedTab === 'rental' && styles.activeTabText]}>
          Location
        </Text>
      </TouchableOpacity>
    </View>
  );
};

const styles = StyleSheet.create({
  tabs: {
    flexDirection: 'row',
    justifyContent: 'center',
    marginVertical: 20,
  },
  tab: {
    padding: 12,
    borderRadius: 8,
    backgroundColor: '#f0f0f0',
    marginHorizontal: 5,
  },
  activeTab: {
    backgroundColor: '#007BFF',
  },
  tabText: {
    fontSize: 16,
    color: '#555',
  },
  activeTabText: {
    color: 'white',
  },
});

export default TabSelector;
