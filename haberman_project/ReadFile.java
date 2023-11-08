 /*
 *
 * Description: Analyzes data from Haberman’s Survival dataset and gives averages with results accordingly
 * Date: October 12th 2023
 * Authors: Eduardo Perez Rocha & Jack Neumann
 * 
 */

import java.io.*;
import java.util.Scanner;

 public class ReadFile {
    public static void main(String[] args) {

        /*Asks the user if it wants to run the program again */
        Scanner inputScanner = new Scanner(System.in);
        String decision = "";

        do {
            runProgram();
            System.out.print("Do you want to run the program again (y for yes and n for no): ");wh
            decision = inputScanner.next();
        } while (decision.equalsIgnoreCase("y"));

        inputScanner.close();
        System.out.println("Program terminated.");
    }
        
    public static void runProgram() {
        /*Asks for user input for the file name. */
        System.out.print("Please enter the name of the output file that saves the result from the program: ");
        Scanner inputScanner = new Scanner(System.in);
        String filePath = inputScanner.next();
        
        /* Makes sures is readable */
        if (!filePath.endsWith(".txt")) {
            filePath += ".txt";
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {

            double total_age = 0;
            double total_average_age = 0;
            int counter_age = 0;

            double total_age_dead = 0;
            double total_average_age_dead = 0;
            int counter_age_dead = 0;

            int survival_year_constant = 0;

            int positive_auxillary_alive = 0;
            int positive_auxillary_death = 0;

            double auxilary_alive_avg = 0;
            double auxilary_death_avg = 0;

            int year_highest_survival_rate = 0;
            int year_lowest_survival_rate = 0;
            double highest_survival_rate = 0;
            double lowest_survival_rate = 1; //Made it 1
            double year_survival_rate = 0;

            
            /* 2D Storing Array:
             *    First Column = Year
             *    Second Column = Patients who survived - Patients who died
             *    Third Column = Number of Patients Counted
             *    Fourth Column = Survival Rate
             */

            int[][] multiArray = new int[15][3];

            System.out.print("Please enter the name of a dataset in CSV format:");

            Scanner input = new Scanner(System.in);

            File file = new File(input.nextLine());

            input = new Scanner(file);

            while (input.hasNextLine()) {
                String lines = input.nextLine();
                String[] line = lines.split(",");
                
                /* Extract age, retirement, and children values */
                int age = Integer.parseInt(line[0]);
                int surgery_year = Integer.parseInt(line[1]);
                int positive_auxillary = Integer.parseInt(line[2]);
                int survival_status = Integer.parseInt(line[3]);

                for (int i = 0; i < 15; i++) {
                    if (multiArray[i][survival_year_constant] == surgery_year) {
                        if (survival_status == 1) {
                            multiArray[i][1] += 1;
                            multiArray[i][2] += 1;
                         }

                        /* Average age of people who survived within 5 years */
                        if (survival_status == 2) {
                            multiArray[i][1] -= 1;
                            multiArray[i][2] += 1;
                        }
                        break;
                    }
                    else if(multiArray[i][survival_year_constant] == 0) {
                        multiArray[i][survival_year_constant] = surgery_year;
                        if (survival_status == 1) {
                            multiArray[i][1] += 1;
                            multiArray[i][2] += 1;
                         }

                        /* Average age of people who survived within 5 years */
                        if (survival_status == 2) {
                            multiArray[i][1] -= 1;
                            multiArray[i][2] += 1;
                        }
                        break;
                    }
                } 
                /* Average age of people who survived more than 5 years or longer */
                if (survival_status == 1) {
                    total_age = total_age + age;
                    counter_age = counter_age + 1; 
                    positive_auxillary_alive = positive_auxillary_alive + positive_auxillary;
                }

                /* Average age of people who survived within 5 years */
                if (survival_status == 2) {
                    total_age_dead = total_age_dead + age;
                    counter_age_dead = counter_age_dead + 1; 
                    positive_auxillary_death = positive_auxillary_death + positive_auxillary;
                }
        }

            for (int i = 0; i < 15; i++) {
                if (multiArray[i][0] != 0) {
                    year_survival_rate =  ((double)multiArray[i][1] / multiArray[i][2]);
                }
                if (year_survival_rate > highest_survival_rate) {
                    highest_survival_rate = year_survival_rate;
                    year_highest_survival_rate = multiArray[i][0];
                }
                
                if (year_survival_rate < lowest_survival_rate) {
                    lowest_survival_rate = year_survival_rate;
                    year_lowest_survival_rate = multiArray[i][0];
                }
            }

            for (int i = 0; i < multiArray.length; i++) {
                for (int j = 0; j < multiArray[i].length; j++) {
                    System.out.print(multiArray[i][j] + "   |   ");
                }
                System.out.println(); // Move to the next line after printing each row
            }
            
        
            total_average_age = total_age / counter_age;   
            total_average_age = Math.round(total_average_age * 100.0) / 100.0;
            
            /* People who suvived within 5 years */
            total_average_age_dead = total_age_dead / counter_age_dead;
            total_average_age_dead = Math.round(total_average_age_dead * 100.0) / 100.0;

            auxilary_alive_avg = Math.round((positive_auxillary_alive / counter_age_dead) * 100) / 100;
            auxilary_death_avg = Math.round((positive_auxillary_death / counter_age) * 100) / 100;

            bw.write("Total Average Age: " + total_average_age + "\n");
            bw.write("Total Average Age Dead: " + total_average_age_dead + "\n");

            bw.write("Total Average Auxillary Alive: " + auxilary_alive_avg + "\n");
            bw.write("Total Average Auxillary Alive: " + auxilary_alive_avg + "\n");

            bw.write("Year with the highest patient survival rate 19" + year_highest_survival_rate + "\n");
            bw.write("Year with the lowest patient survival rate: 19" + year_lowest_survival_rate + "\n");
                    
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}