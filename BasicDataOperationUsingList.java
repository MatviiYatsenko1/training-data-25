import java.time.Long;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Клас BasicDataOperationUsingList реалізує операції з колекціями типу ArrayList для даних Long.
 * 
 * <p>Методи класу:</p>
 * <ul>
 *   <li>{@link #executeDataOperations()} - Виконує комплекс операцій з даними.</li>
 *   <li>{@link #performArraySorting()} - Упорядковує масив елементів Long.</li>
 *   <li>{@link #findInArray()} - Здійснює пошук елемента в масиві Long.</li>
 *   <li>{@link #locateMinMaxInArray()} - Визначає найменше і найбільше значення в масиві.</li>
 *   <li>{@link #sortList()} - Сортує колекцію List з Long.</li>
 *   <li>{@link #findInList()} - Пошук конкретного значення в списку.</li>
 *   <li>{@link #locateMinMaxInList()} - Пошук мінімального і максимального значення в списку.</li>
 * </ul>
 */
public class BasicDataOperationUsingList {
    private Long LongValueToSearch;
    private Long[] LongArray;
    private List<Long> dateTimeList;

    /**
     * Конструктор, який iнiцiалiзує об'єкт з готовими даними.
     * 
     * @param LongValueToSearch Значення для пошуку
     * @param LongArray Масив Long
     */
    BasicDataOperationUsingList(Long LongValueToSearch, Long[] LongArray) {
        this.LongValueToSearch = LongValueToSearch;
        this.LongArray = LongArray;
        this.LongTimeList = new ArrayList<>(Arrays.asList(LongArray));
    }
    
    /**
     * Виконує комплексні операції з структурами даних.
     * 
     * Метод завантажує масив і список об'єктів Long, 
     * здійснює сортування та пошукові операції.
     */
    public void executeDataOperations() {
        // спочатку працюємо з колекцією List
        findInList();
        locateMinMaxInList();
        
        sortList();
        
        findInList();
        locateMinMaxInList();

        // потім обробляємо масив дати та часу
        findInArray();
        locateMinMaxInArray();

        performArraySorting();
        
        findInArray();
        locateMinMaxInArray();

        // зберігаємо відсортований масив до окремого файлу
        DataFileHandler.writeArrayToFile(LongArray, BasicDataOperation.PATH_TO_DATA_FILE + ".sorted");
    }

    /**
     * Упорядковує масив об'єктів Long за зростанням.
     * Фіксує та виводить тривалість операції сортування в наносекундах.
     */
    void performArraySorting() {
        Long timeStart = System.nanoTime();

        Arrays.sort(LongArray);

        PerformanceTracker.displayOperationTime(timeStart, "упорядкування масиву дати i часу");
    }

    /**
     * Здійснює пошук конкретного значення в масиві дати та часу.
     */
    void findInArray() {
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
     * Визначає найменше та найбільше значення в масиві дати та часу.
     */
    void locateMinMaxInArray() {
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
     * Шукає конкретне значення дати та часу в колекції ArrayList.
     */
    void findInList() {
        Long timeStart = System.nanoTime();

        int position = Collections.binarySearch(this.dateTimeList, LongValueToSearch);

        PerformanceTracker.displayOperationTime(timeStart, "пошук елемента в List дати i часу");        

        if (position >= 0) {
            System.out.println("Елемент '" + LongValueToSearch + "' знайдено в ArrayList за позицією: " + position);
        } else {
            System.out.println("Елемент '" + LongValueToSearch + "' відсутній в ArrayList.");
        }
    }

    /**
     * Визначає найменше і найбільше значення в колекції ArrayList з датами.
     */
    void locateMinMaxInList() {
        if (LongTimeList == null || dateTimeList.isEmpty()) {
            System.out.println("Колекція ArrayList є пустою або не ініціалізованою.");
            return;
        }

        Long timeStart = System.nanoTime();

        Long minValue = Collections.min(dateTimeList);
        Long maxValue = Collections.max(dateTimeList);

        PerformanceTracker.displayOperationTime(timeStart, "визначення мiнiмальної i максимальної дати в List");

        System.out.println("Найменше значення в List: " + minValue);
        System.out.println("Найбільше значення в List: " + maxValue);
    }

    /**
     * Упорядковує колекцію List з об'єктами Long за зростанням.
     * Відстежує та виводить час виконання операції сортування.
     */
    void sortList() {
        Long timeStart = System.nanoTime();

        Collections.sort(dateTimeList);

        PerformanceTracker.displayOperationTime(timeStart, "упорядкування ArrayList дати i часу");
    }
}