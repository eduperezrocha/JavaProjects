 /*
 *
 * Description: 
 * The program prompts the user for a data file to solve the maximum subsequence sum problem.  
 * It firstly takes you to a menu where you have differnet options from 1 to 8. Avaiable
 * there is to run 4 different maximum subsequence algorithms. It then outputs the results with
 * the max sum and the time it took to run.
 * Date: November 2st 2023
 * Authors: Eduardo Perez Rocha
 * 
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.io.File;

class proj3{
    public static void main(String[] args){
        /*Asks the user if it wants to run the program again */
        Scanner inputScanner = new Scanner(System.in);
        String decision;
        do {
            Menu(inputScanner);
            System.out.print("Do you want to run the program again (y for yes and n for no): ");
            decision = inputScanner.nextLine();
        } while (decision.equalsIgnoreCase("y"));

        System.out.println("Program terminated.");
    }

    public static void Menu(Scanner scanner) {
        scanner = new Scanner(System.in);
        String fileName;
        while (true) {
            System.out.println("Select an option:");
            System.out.println("Enter 1. To Run MSS1");
            System.out.println("Enter 2. To Run MSS2");
            System.out.println("Enter 3. To Run MSS3");
            System.out.println("Enter 4. To Run MSS4");
            System.out.println("Enter 5. To Run All Algorithms");
            System.out.println("Enter 6. To Collect Data");
            System.out.println("Enter 7. To Create Data Set");
            System.out.println("Enter 8. To Exit the Menu");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    fileName = getFileName();
                    readFile("1", fileName);
                    break;
                case 2:
                    fileName = getFileName();
                    readFile("2", fileName);
                    break;
                case 3:
                    fileName = getFileName();
                    readFile("3", fileName);
                    break;
                case 4:
                    fileName = getFileName();
                    readFile("4", fileName);
                    break;
                case 5:
                    fileName = getFileName();
                    readFile("ALL", fileName);
                    break;
                case 6:
                    Scanner userPreference = new Scanner(System.in);
                    System.out.println("Select the size of the data set: ");
                    System.out.println("'S'. Small");
                    System.out.println("'M'. Medium");
                    System.out.println("'L'. Large");
                    String input = userPreference.nextLine();
                    collectData(input);
                    break;
                case 7:
                    writeFile(scanner);
                    break;
                case 8:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please select again.");
            }
        }
    }
    
    public static String getFileName() {
        System.out.print("Please enter the name of the file: ");
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    public static void readFile(String userInput, String fileName) {
        try {
            File file = new File(fileName);
            Scanner input = new Scanner(file);
            String line = input.nextLine();
            
            String[] str = line.split("[,\\s]+");
            int[] ints = Arrays.stream(str).mapToInt(Integer::parseInt).toArray();

            long sumTimeMSS1 = 0, sumTimeMSS2 = 0, sumTimeMSS3 = 0, sumTimeMSS4 = 0;
            String totalTimeMSS1, totalTimeMSS2, totalTimeMSS3, totalTimeMSS4;
            int maxSumMSS1, maxSumMSS2, maxSumMSS3, maxSumMSS4;

            if (userInput.equalsIgnoreCase("1")) {
                System.out.println("Running MSS1...");
                maxSumMSS1 = MSS1(ints);
                sumTimeMSS1 += CalculateTotalTime(ints, "MSS1");
                totalTimeMSS1 = formatTime(sumTimeMSS1, ints.length);
                System.out.println("Total time " + totalTimeMSS1);
                System.out.println("Max Sum MSS1: " + maxSumMSS1 + "\n");
            }
            else if (userInput.equalsIgnoreCase("2")) {
                System.out.println("Running MSS2...");
                maxSumMSS2 = MSS2(ints);
                sumTimeMSS2 += CalculateTotalTime(ints, "MSS2");
                totalTimeMSS2 = formatTime(sumTimeMSS2, ints.length);
                System.out.println("Total time " + totalTimeMSS2);
                System.out.println("Max Sum MSS2: " + maxSumMSS2 + "\n");
            }
            else if (userInput.equalsIgnoreCase("3")) {
                System.out.println("Running MSS3...");
                maxSumMSS3 = MSS3(ints, 0, ints.length - 1);
                sumTimeMSS3 += CalculateTotalTime(ints, "MSS3");
                totalTimeMSS3 = formatTime(sumTimeMSS3, ints.length);
                System.out.println("Total time " + totalTimeMSS3);
                System.out.println("Max Sum MSS3: " + maxSumMSS3 + "\n");
            }
            else if (userInput.equalsIgnoreCase("4")) {
                System.out.println("Running MSS4...");
                maxSumMSS4 = MSS4(ints);
                sumTimeMSS4 += CalculateTotalTime(ints, "MSS4");
                totalTimeMSS4 = formatTime(sumTimeMSS4, ints.length);
                System.out.println("Total time " + totalTimeMSS4);
                System.out.println("Max Sum MSS4: " + maxSumMSS4 + "\n");
            }

            else if (userInput.equalsIgnoreCase("ALL")) {
                readFile("1", fileName);
                readFile("2", fileName);
                readFile("3", fileName);
                readFile("4", fileName);
            }
        } 
        catch (Exception e) {
            System.out.print("Excecption thrown:   " + e);
        }
    }

    public static void writeFile(Scanner inputScanner) {
        try {
            System.out.print("Please enter the name of the file that creates the dataset: ");
            inputScanner = new Scanner(System.in);
            String filePath = inputScanner.next();
            /* Makes sures is readable */
            if (!filePath.endsWith(".txt")) {
                filePath += ".txt";
            }
            for (int i = 0; i < 1; i++) {
               generateRandom(filePath);
            }
        }
        catch (Exception e) {
            System.out.println("Excecption thrown: \n" + e);
        }
    }

    public static int[] generateRandomDataSet(int size) {
        Random random = new Random();
        int[] ar1 = new int[size];
        int min = -100;
        int max = 100;
    
        for (int i = 0; i < ar1.length; i++) {
            int randomInteger = min + random.nextInt(max - min + 1); // Include max in the range
            ar1[i] = randomInteger;
        }
        return ar1;            
    }
    

    public static void generateRandom(String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))){
            int size = 0;
            if (filePath.equalsIgnoreCase("S")) {
                size = 10000;
            }
            if (filePath.equalsIgnoreCase("M")){
                size = 10000;
            }
            if (filePath.equalsIgnoreCase("L")) {
                size = 100000;
            }
            else {
                System.out.print("Please input the size of the array: ");
                Scanner inputScanner = new Scanner(System.in);
                String userInput = inputScanner.next();
                size = Integer.parseInt(userInput);
            }
            Random random = new Random();
            int[] ar1 = new int[size];
            int min = -100;
            int max = 100;
            for (int i = 0; i < ar1.length; i++) {
                int randomInteger = min + random.nextInt(max - min);
                ar1[i] = randomInteger;
                bw.write(ar1[i] + ", ");
            }            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static long CalculateTotalTime(int[] arr, String MSS) {
        if (MSS.equals("MSS1")){
            long startTime = System.nanoTime();
            MSS1(arr);
            long endTime   = System.nanoTime();
            long totalTime = endTime - startTime;
            return totalTime;
        }
        else if (MSS.equals("MSS2")){
            long startTime = System.nanoTime();
            MSS2(arr);
            long endTime   = System.nanoTime();
            long totalTime = endTime - startTime;
            return totalTime;
        }
        else if (MSS.equals("MSS3")){
            long startTime = System.nanoTime();
            MSS3(arr, 0, arr.length - 1);
            long endTime   = System.nanoTime();
            long totalTime = endTime - startTime;
            return totalTime;
        }
        else if(MSS.equals("MSS4")) {
            long startTime = System.nanoTime();
            MSS4(arr);
            long endTime   = System.nanoTime();
            long totalTime = endTime - startTime;
            return totalTime;
        }
        return 0;
    }

    /*  O(n^3)  */
    public static int MSS1(int[] arr) {
        int max_sum = arr[0];
        for (int i = 0; i< arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                int sum = 0;
                for (int k =i; k<j; k++) {
                    sum += arr[k];
                }
                if (sum > max_sum) {
                    max_sum = sum;
                }
            }
        }
        return max_sum;
    }

    /*  O(n^2)  */
    public static int MSS2(int[] arr) {
        int max_sum = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for(int j = i; j < arr.length; j++){
                sum += arr[j];
                if (sum > max_sum) {
                    max_sum = sum;
                }
            }
        }
        
        return max_sum;
    }

    /*  O(n log n)  */
    public static long MSS3_Time(int[] arr) {
        long startTime = System.nanoTime();
        int maxSum = MSS3(arr, 0, arr.length - 1);
        long endTime = System.nanoTime();
        System.out.println("Max sum MSS3: " + maxSum);
        return endTime - startTime;
    }

    public static int MSS3(int[] arr, int low, int high) {
        if (low == high) {
            return arr[low];
        }
    
        int mid = (low + high) / 2;
    
        int maxLeftSum = MSS3(arr, low, mid);
        int maxRightSum = MSS3(arr, mid + 1, high);
    
        int sum = 0;
        int leftSum = Integer.MIN_VALUE;
        for (int i = mid; i >= low; i--) {
            sum += arr[i];
            leftSum = Math.max(leftSum, sum);
        }
    
        sum = 0;
        int rightSum = Integer.MIN_VALUE;
        for (int i = mid + 1; i <= high; i++) {
            sum += arr[i];
            rightSum = Math.max(rightSum, sum);
        }
    
        int maxCrossingSum = leftSum + rightSum;
        
        return Math.max(maxCrossingSum, Math.max(maxLeftSum, maxRightSum));
    }

    /*  O(n)  */
    public static int MSS4(int[] arr) {
        int max_sum = arr[0];
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (sum > max_sum) {
                max_sum = sum;
            }
            else {
                if (sum < 0) {
                    sum = 0;
                }
            }
        }
        return max_sum;
    }

    public static String formatTime(long nanoseconds, int dataSize) {
        String formattedTime;
        if (nanoseconds >= 3_600_000_000_000L) {  // At least an hour
            formattedTime = String.format("%.2f hours", nanoseconds / 3_600_000_000_000.0);
        } else if (nanoseconds >= 60_000_000_000L) {  // At least a minute
            formattedTime = String.format("%.2f minutes", nanoseconds / 60_000_000_000.0);
        } else if (nanoseconds >= 1_000_000_000L) {  // At least a second
            formattedTime = String.format("%.2f seconds", nanoseconds / 1_000_000_000.0);
        } else if (nanoseconds >= 1_000_000L) {  // At least a millisecond
            formattedTime = String.format("%.2f milliseconds", nanoseconds / 1_000_000.0);
        } else if (nanoseconds >= 1_000L) {  // At least a microsecond
            formattedTime = String.format("%.2f microseconds", nanoseconds / 1_000.0);
        } else {
            formattedTime = nanoseconds + " nanoseconds";
        }
        return formattedTime + " <=> Data Size: " + dataSize + " Numbers";
    }
    
    public static void collectData(String userPreference ) {
        final int RUNS = 10;
        int[] Data_sizes = {};

        // Define the array sizes we want to test
        if (userPreference.equalsIgnoreCase("S")){
            System.out.println("Running Small Data Base...");
            Data_sizes = new int[]{10, 50, 100, 200, 400, 500, 600, 800, 900, 1000};
        }
        else if (userPreference.equalsIgnoreCase("M")) {
            System.out.println("Running Medium Data Base...");
            Data_sizes = new int[]{1500, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000};
        }
        else if (userPreference.equalsIgnoreCase("L")) {
            System.out.println("Running Large Data Base...");
            Data_sizes = new int[]{15000, 20000, 30000, 40000, 50000, 60000, 70000, 80000, 90000, 100000};
        }
        else if(userPreference.equalsIgnoreCase("ALL")) {
            collectData("S");
            collectData("M");
            collectData("L");
        }

        // Arrays to store average execution times for each algorithm
        String[] avgTimesMSS1 = new String[Data_sizes.length];
        String[] avgTimesMSS2 = new String[Data_sizes.length];
        String[] avgTimesMSS3 = new String[Data_sizes.length];
        String[] avgTimesMSS4 = new String[Data_sizes.length];
        
        for (int i = 0; i < Data_sizes.length; i++) {
            long sumTimeMSS1 = 0, sumTimeMSS2 = 0, sumTimeMSS3 = 0, sumTimeMSS4 = 0;
        
            for (int run = 0; run < RUNS; run++) {
                int[] randomArray = generateRandomDataSet(Data_sizes[i]);
                sumTimeMSS1 += CalculateTotalTime(randomArray, "MSS1");
                sumTimeMSS2 += CalculateTotalTime(randomArray, "MSS2");
                sumTimeMSS3 += CalculateTotalTime(randomArray, "MSS3");
                sumTimeMSS4 += CalculateTotalTime(randomArray, "MSS4");
            }
        
            avgTimesMSS1[i] = formatTime(sumTimeMSS1 / RUNS, Data_sizes[i]);
            avgTimesMSS2[i] = formatTime(sumTimeMSS2 / RUNS, Data_sizes[i]);
            avgTimesMSS3[i] = formatTime(sumTimeMSS3 / RUNS, Data_sizes[i]);
            avgTimesMSS4[i] = formatTime(sumTimeMSS4 / RUNS, Data_sizes[i]);
        }
        
        System.out.println("| 10 Runs Avg Time of MSS1:");
        for (String time : avgTimesMSS1) {
            System.out.println("| " + time);
        }
        
        System.out.println("\n| 10 Runs Avg Time of MSS2:");
        for (String time : avgTimesMSS2) {
            System.out.println("| " + time);
        }
    
        System.out.println("\n| 10 Runs Avg Time of MSS3:");
        for (String time : avgTimesMSS3) {
            System.out.println("| " + time);
        }
    
        System.out.println("\n| 10 Runs Avg Time of MSS4:");
        for (String time : avgTimesMSS4) {
            System.out.println("| " + time);
        }
    }    
}


