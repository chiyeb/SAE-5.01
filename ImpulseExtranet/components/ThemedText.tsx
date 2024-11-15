import { Text, type TextProps, StyleSheet } from 'react-native';

import { useThemeColor } from '@/hooks/useThemeColor';

export type ThemedTextProps = TextProps & {
  lightColor?: string;
  darkColor?: string;
  type?: 'default' | 'title' | 'defaultSemiBold' | 'subtitle' |'h2'|'infoText'|'link';
};

export function ThemedText({
  style,
  lightColor,
  darkColor,
  type = 'default',
  ...rest
}: ThemedTextProps) {
  const color = useThemeColor({ light: lightColor, dark: darkColor }, 'text');

  return (
    <Text
      style={[
        { color },
        type === 'default' ? styles.default : undefined,
        type === 'title' ? styles.title : undefined,
        type === 'defaultSemiBold' ? styles.defaultSemiBold : undefined,
        type === 'h2' ? styles.h2 : undefined,
        type === 'subtitle' ? styles.subtitle : undefined,
        type === 'infoText' ? styles.infoText: undefined,
        type === 'link' ? styles.link : undefined,
        style,
      ]}
      {...rest}
    />
  );
}

const styles = StyleSheet.create({
  default: {
    fontSize: 16,
    lineHeight: 24,
    color:'black',
  },
  defaultSemiBold: {
    fontSize: 16,
    lineHeight: 24,
    fontWeight: '800',
    color:'black',
  },
  title: {
    fontSize: 32,
    fontWeight: 'bold',
    lineHeight: 32,
    color:'black',
    paddingBottom:5,
    textAlign:'center',
    
  },
  subtitle: {
    fontSize: 20,
    fontWeight: 'bold',
    color:'black',
  },
  h2:{
    fontSize: 20,
    fontWeight: 'bold',
    color:'black',
    paddingBottom: 5,
    textAlign:'center',
  },
  link: {
    lineHeight: 30,
    fontSize: 16,
    color: '#0a7ea4',
  },
  infoText:{
    margin: 5,
    textAlign:'center',
    color: 'black',
  }
});
