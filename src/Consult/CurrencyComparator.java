package src.Consult;

import com.google.gson.Gson;
import src.api.ApitTest;
import src.model.TransitionApi;

import java.util.Scanner;

public class CurrencyComparator {
    public static void comparingPrices() {

        while (true) {


            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the currency code: ");

            String currencyCodeScanner = scanner.nextLine();

            while (currencyCodeScanner.matches("-?\\d+(\\.\\d+)?")) {
                System.out.println("Invalid currency code. Please enter letters only.");
                currencyCodeScanner = scanner.nextLine();
            }

            ApitTest.response(currencyCodeScanner);

            Gson gson = new Gson();

            TransitionApi transitionApi = gson.fromJson(ApitTest.response(currencyCodeScanner), TransitionApi.class);
            System.out.println("This is the current currency: \n" + transitionApi.base_code());

            System.out.println("How much you wanna convert? ");
            double amount = scanner.nextDouble();

            while (amount <= 0) {
                System.out.println("Invalid amount. Please enter a positive number.");
                amount = scanner.nextDouble();
            }
            scanner.nextLine();

            System.out.println("This is the amount you want to convert: " + amount);



            System.out.println("Try to check the value of the conversion rates to a new Currency Code: ");
            String newCurrencyCode = scanner.nextLine().toUpperCase();

            while (newCurrencyCode.matches("-?\\d+(\\.\\d+)?")) {
                System.out.println("Invalid currency code. Please enter letters only.");
                newCurrencyCode = scanner.nextLine();
            }

            Double conversionRate = transitionApi.conversion_rates().get(newCurrencyCode);
            Double finalAmount = conversionRate * amount;

            System.out.println("\u001B[33m" + "The conversion rate is: " + conversionRate +
                    "\n" + amount + " " + transitionApi.base_code() + " is equal to " + finalAmount + " " + newCurrencyCode + "\u001B[0m");

            System.out.println("Do you want to continue? (Y/N)");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("N")) {
                System.out.println("\u001B[35m" + "Goodbye!" + "\u001B[0m");
                break;
            }

        }
    }
}