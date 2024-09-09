import { View, Button } from 'react-native';
import { startCollectionService, stopCollectionService } from 'collect-usage';

export default function App() {

  const startService = () => {
    startCollectionService();
  };

  const stopService = () => {
    stopCollectionService();
  };

  return (
    <View style={{ flex: 1, alignItems: 'center', justifyContent: 'center', gap: 20 }}>
      <Button title="Start Collection Service" onPress={startService} />
      <Button title="Stop Collection Service" onPress={stopService} />
    </View>
  );
}
