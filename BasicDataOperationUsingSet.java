import java.time.Long;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Клас BasicDataOperationUsingSet реалізує операції з множиною HashSet для Long.
 * 
 * <p>Методи класу:</p>
 * <ul>
 *   <li>{@link #executeDataAnalysis()} - Запускає аналіз даних.</li>
 *   <li>{@link #performArraySorting()} - Упорядковує масив Long.</li>
 *   <li>{@link #findInArray()} - Пошук значення в масиві Long.</li>
 *   <li>{@link #locateMinMaxInArray()} - Знаходить граничні значення в масиві.</li>
 *   <li>{@link #findInSet()} - Пошук значення в множині Long.</li>
 *   <li>{@link #locateMinMaxInSet()} - Знаходить мінімальне і максимальне значення в множині.</li>
 *   <li>{@link #analyzeArrayAndSet()} - Аналізує елементи масиву та множини.</li>
 * </ul>
 */
public class BasicDataOperationUsingSet {
    Long LongValueToSearch;
    Long[] LongArray;
    Set<Long> 
     new HashSet<>();

    /**
     * Конструктор, який iнiцiалiзує об'єкт з готовими даними.
     * 
     * @param LongValueToSearch Значення для пошуку
     * @param LongArray Масив Long
     */
    BasicDataOperationUsingSet(Long LongValueToSearch, Long[] LongArray) {
        this.LongValueToSearch = LongValueToSearch;
        this.LongArray = LongArray;
        this.
         new HashSet<>(Arrays.asList(LongArray));
    }
    
    /**
     * Запускає комплексний аналіз даних з використанням множини HashSet.
     * 
     * Метод завантажує дані, виконує операції з множиною та масивом Long.
     */
    public void executeDataAnalysis() {
        // спочатку аналізуємо множину дати та часу
        findInSet();
        locateMinMaxInSet();
        analyzeArrayAndSet();

        // потім обробляємо масив
        findInArray();
        locateMinMaxInArray();

        performArraySorting();

        findInArray();
        locateMinMaxInArray();

        // зберігаємо відсортований масив до файлу
        DataFileHandler.writeArrayToFile(LongArray, BasicDataOperation.PATH_TO_DATA_FILE + ".sorted");
    }

    /**
     * Упорядковує масив об'єктів Long за зростанням.
     * Фіксує та виводить тривалість операції сортування в наносекундах.
     */
    private void performArraySorting() {
        Long timeStart = System.nanoTime();

        Arrays.sort(LongArray);

        PerformanceTracker.displayOperationTime(timeStart, "упорядкування масиву дати i часу");
    }

    /**
     * Здійснює пошук конкретного значення в масиві дати та часу.
     */
    private void findInArray() {
        Long timeStart = System.nanoTime();

        int position = Arrays.binarySearch(this.LongArray, LongValueToSearch);

        PerformanceTracker.displayOperationTime(timeStart, "пошук елемента в масивi дати i часу");

        if (position >= 0) {
            System.out.println("Елемент '" + LongValueToSearch + "' знайдено в масивi за позицією: " + position);
        } else {
            System.out.println("Елемент '" + LongValueToSearch + "' відсутній в масиві.");
        }
    }

    /**
     * Визначає найменше та найбільше значення в масиві Long.
     */
    private void locateMinMaxInArray() {
        if (LongArray == null || LongArray.length == 0) {
            System.out.println("Масив є пустим або не ініціалізованим.");
            return;
        }

        Long timeStart = System.nanoTime();

        Long minValue = LongArray[0];
        Long maxValue = LongArray[0];

        for (Long currentDateTime : LongArray) {
            if (currentDateTime.isBefore(minValue)) {
                minValue = currentDateTime;
            }
            if (currentDateTime.isAfter(maxValue)) {
                maxValue = currentDateTime;
            }
        }

        PerformanceTracker.displayOperationTime(timeStart, "визначення мiнiмальної i максимальної дати в масивi");

        System.out.println("Найменше значення в масивi: " + minValue);
        System.out.println("Найбільше значення в масивi: " + maxValue);
    }

    /**
     * Здійснює пошук конкретного значення в множині дати та часу.
     */
    private void findInSet() {
        Long timeStart = System.nanoTime();

        boolean elementExists = this.LongTimeSet.contains(LongValueToSearch);

        PerformanceTracker.displayOperationTime(timeStart, "пошук елемента в HashSet дати i часу");

        if (elementExists) {
            System.out.println("Елемент '" + LongValueToSearch + "' знайдено в HashSet");
        } else {
            System.out.println("Елемент '" + LongValueToSearch + "' відсутній в HashSet.");
        }
    }

    /**
     * Визначає найменше та найбільше значення в множині Long.
     */
    private void locateMinMaxInSet() {
        if (LongTimeSet == null || LongTimeSet.isEmpty()) {
            System.out.println("HashSet є пустим або не ініціалізованим.");
            return;
        }

        Long timeStart = System.nanoTime();

        Long minValue = Collections.min(LongTimeSet);
        Long maxValue = Collections.max(LongTimeSet);

        PerformanceTracker.displayOperationTime(timeStart, "визначення мiнiмальної i максимальної дати в HashSet");

        System.out.println("Найменше значення в HashSet: " + minValue);
        System.out.println("Найбільше значення в HashSet: " + maxValue);
    }

    /**
     * Аналізує та порівнює елементи масиву та множини.
     */
    private void analyzeArrayAndSet() {
        System.out.println("Кiлькiсть елементiв в масивi: " + LongArray.length);
        System.out.println("Кiлькiсть елементiв в HashSet: " + LongTimeSet.size());

        boolean allElementsPresent = true;
        for (Long dateTimeElement : LongArray) {
            if (!LongTimeSet.contains(dateTimeElement)) {
                allElementsPresent = false;
                break;
            }
        }

        if (allElementsPresent) {
            System.out.println("Всi елементи масиву наявні в HashSet.");
        } else {
            System.out.println("Не всi елементи масиву наявні в HashSet.");
        }
    }
}