import java.util.Arrays;

public class Activity2 {
    //decided to make a simple main fun for testing
    public static void main(String[] args) {
        int[] numbers = { 3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5 }; // Example array
        System.out.println("List before sorting: " + Arrays.toString(numbers));
        double median = sortAndFindMedian(numbers);
        System.out.println("List after bubble sorting: " + Arrays.toString(numbers));
        System.out.println("Median: " + median);
    }

    //the function exactly as given in the pdf file to get median from a sorted list
    public static double sortAndFindMedian(int[] numbers) {
        sort(numbers);
        int n = numbers.length;
        if (n % 2 == 0) {
            return (numbers[n / 2 - 1] + numbers[n / 2]) / 2.0;
        } else {
            return numbers[n / 2];
        }
    }

    //help function to sort using bubblesort that i remember from school and LeetCode
    public static void sort(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length - i - 1; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    // Swap numbers[j] and numbers[j + 1]
                    int temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                }
            }
        }
    }
}
