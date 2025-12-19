import java.time.Long;
import java.util.Queue;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Клас BasicDataOperationUsingQueue реалізує роботу з колекціями типу Queue для Long.
 * 
 * <p>Основні функції класу:</p>
 * <ul>
 *   <li>{@link #runDataProcessing()} - Запускає комплекс операцій з даними.</li>
 *   <li>{@link #performArraySorting()} - Упорядковує масив Long.</li>
 *   <li>{@link #findInArray()} - Пошук значення в масиві Long.</li>
 *   <li>{@link #locateMinMaxInArray()} - Знаходить мінімальне і максимальне значення в масиві.</li>
 *   <li>{@link #findInQueue()} - Пошук значення в черзі Long.</li>
 *   <li>{@link #locateMinMaxInQueue()} - Знаходить граничні значення в черзі.</li>
 *   <li>{@link #performQueueOperations()} - Виконує операції peek і poll з чергою.</li>
 * </ul>
 * 
 */
public class BasicDataOperationUsingQueue {
    private Long LongValueToSearch;
    private Long[] LongArray;
    private Queue<Long> LongQueue;

    /**
     * Конструктор, який iнiцiалiзує об'єкт з готовими даними.
     * 
     * @param LongValueToSearch Значення для пошуку
     * @param LongArray Масив Long
     */
    BasicDataOperationUsingQueue(Long LongValueToSearch, Long[] LongArray) {
        this.LongValueToSearch = LongValueToSearch;
        this.LongArray = LongArray;
        this.LongQueue = new PriorityQueue<>(Arrays.asList(LongArray));
    }
    
    /**
     * Запускає комплексну обробку даних з використанням черги.
     * 
     * Метод завантажує дані, виконує операції з чергою та масивом Long.
     */
    public void runDataProcessing() {
        // спочатку обробляємо чергу дати та часу
        findInQueue();
        locateMinMaxInQueue();
        performQueueOperations();

        // потім працюємо з масивом
        findInArray();
        locateMinMaxInArray();

        performArraySorting();

        findInArray();
        locateMinMaxInArray();

        // зберігаємо відсортований масив до файлу
        DataFileHandler.writeArrayToFile(LongArray, BasicDataOperation.PATH_TO_DATA_FILE + ".sorted");
    }

    /**
     * Сортує масив об'єктiв Long та виводить початковий i вiдсортований масиви.
     * Вимiрює та виводить час, витрачений на сортування масиву в наносекундах.
     */
    private void performArraySorting() {
        // вимірюємо тривалість упорядкування масиву дати та часу
        Long timeStart = System.nanoTime();

        Arrays.sort(LongArray);

        PerformanceTracker.displayOperationTime(timeStart, "упорядкування масиву дати i часу");
    }

    /**
     * Здійснює пошук конкретного значення в масиві дати та часу.
     */
    private void findInArray() {
        // відстежуємо час виконання пошуку в масиві
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

        // відстежуємо час на визначення граничних значень
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
     * Здійснює пошук конкретного значення в черзі дати та часу.
     */
    private void findInQueue() {
        // вимірюємо час пошуку в черзі
        Long timeStart = System.nanoTime();

        boolean elementExists = this.LongQueue.contains(LongValueToSearch);

        PerformanceTracker.displayOperationTime(timeStart, "пошук елемента в Queue дати i часу");

        if (elementExists) {
            System.out.println("Елемент '" + LongValueToSearch + "' знайдено в Queue");
        } else {
            System.out.println("Елемент '" + LongValueToSearch + "' відсутній в Queue.");
        }
    }

    /**
     * Визначає найменше та найбільше значення в черзі Long.
     */
    private void locateMinMaxInQueue() {
        if (LongQueue == null || LongQueue.isEmpty()) {
            System.out.println("Черга є пустою або не ініціалізованою.");
            return;
        }

        // відстежуємо час пошуку граничних значень
        Long timeStart = System.nanoTime();

        Long minValue = Collections.min(LongQueue);
        Long maxValue = Collections.max(LongQueue);

        PerformanceTracker.displayOperationTime(timeStart, "визначення мiнiмальної i максимальної дати в Queue");

        System.out.println("Найменше значення в Queue: " + minValue);
        System.out.println("Найбільше значення в Queue: " + maxValue);
    }

    /**
     * Виконує операції peek і poll з чергою Long.
     */
    private void performQueueOperations() {
        if (LongQueue == null || LongQueue.isEmpty()) {
            System.out.println("Черга є пустою або не ініціалізованою.");
            return;
        }

        Long headElement = LongQueue.peek();
        System.out.println("Головний елемент черги (peek): " + headElement);

        headElement = LongQueue.poll();
        System.out.println("Видалений елемент черги (poll): " + headElement);

        headElement = LongQueue.peek();
        System.out.println("Новий головний елемент черги: " + headElement);
    }
}