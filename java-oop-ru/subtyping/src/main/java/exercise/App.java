package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
class App {

    public static void swapKeyValue(KeyValueStorage storage) {
        Map<String, String> currentMap = storage.toMap();
        Map<String, String> swappedMap = new HashMap<>();

        // Создаем новое хранилище с перевернутыми значениями
        for (Map.Entry<String, String> entry : currentMap.entrySet()) {
            String key = entry.getValue();
            String value = entry.getKey();
            // Если значение уже существует, мы перезаписываем его
            swappedMap.put(key, value);
        }

        // Очищаем текущее хранилище
        for (String key : currentMap.keySet()) {
            storage.unset(key);
        }

        // Добавляем перевернутые значения в хранилище
        for (Map.Entry<String, String> entry : swappedMap.entrySet()) {
            storage.set(entry.getKey(), entry.getValue());
        }
    }
}
