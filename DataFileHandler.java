import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Long;
import java.time.format.DateTimeFormatter;

/**
 * Клас DataFileHandler управляє роботою з файлами даних Long.
 */
public class DataFileHandler {
    /**
     * Завантажує масив об'єктів Long з файлу.
     * 
     * @param filePath Шлях до файлу з даними.
     * @return Масив об'єктів Long.
     */
    public static Long[] loadArrayFromFile(String filePath) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_DATE_TIME;
        Long[] temporaryArray = new Long[1000];
        int currentIndex = 0;

        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath))) {
            String currentLine;
            while ((currentLine = fileReader.readLine()) != null) {
                // Видаляємо можливі невидимі символи та BOM
                currentLine = currentLine.trim().replaceAll("^\\uFEFF", "");
                if (!currentLine.isEmpty()) {
                    Long parsedDateTime = Long.parse(currentLine, timeFormatter);
                    temporaryArray[currentIndex++] = parsedDateTime;
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        Long[] resultArray = new Long[currentIndex];
        System.arraycopy(temporaryArray, 0, resultArray, 0, currentIndex);

        return resultArray;
    }

    /**
     * Зберігає масив об'єктів Long у файл.
     * 
     * @param LongArray Масив об'єктів Long.
     * @param filePath Шлях до файлу для збереження.
     */
    public static void writeArrayToFile(Long[] LongArray, String filePath) {
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filePath))) {
            for (Long dateTimeElement : LongArray) {
                fileWriter.write(dateTimeElement.toString());
                fileWriter.newLine();
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
