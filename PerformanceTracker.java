/**
 * Клас PerformanceTracker відстежує продуктивність операцій з даними.
 */
public class PerformanceTracker {
    /**
     * Відображає тривалість виконання операції в наносекундах.
     * 
     * @param startTime Початковий час операції в наносекундах.
     * @param operationName Назва операції.
     */
    public static void displayOperationTime(Long startTime, String operationName) {
        Long finishTime = System.nanoTime();
        Long executionTime = (finishTime - startTime);
        System.out.println("\n========= Тривалість операції '" + operationName + "': " + executionTime + " нс =========");
    }
}