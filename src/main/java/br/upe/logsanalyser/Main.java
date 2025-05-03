package br.upe.logsanalyser;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int menuOption;

        do {
            System.out.println("\n----------------------------------");
            System.out.println("  LOGS ANALYZER");
            System.out.println("----------------------------------");
            System.out.println("1 - Large resources responded");
            System.out.println("2 - Not responded");
            System.out.println("3 - % of requests by OS");
            System.out.println("4 - Average of POST requests");
            System.out.println("0 - Exit");
            System.out.print("Choose an option: ");
            menuOption = sc.nextInt();

            switch (menuOption) {
                case 1:
                    System.out.println("Case 1: Large resources responded");
                    break;
                case 2:
                    System.out.println("Case 2: Not responded");
                    break;
                case 3:
                    System.out.println("Case 3: Percentage of requests by OS");
                    break;
                case 4:
                    System.out.println("Case 4: Average of POST requests");
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }

        } while (menuOption != 0);

        sc.close();
    }
}
