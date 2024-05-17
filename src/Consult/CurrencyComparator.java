package src.Consult;

import com.google.gson.Gson;
import src.api.ApitTest;
import src.model.TransitionApi;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CurrencyComparator {
    private static Register register = new Register();

    public static Register getRegister() {
        return register;
    }


    public void comparingPrices() {

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\u001B[34m============================\u001B[0m");
            System.out.println("\u001B[32mEnter the currency code:\u001B[0m ");
            System.out.println("\u001B[34m============================\u001B[0m");

            String currencyCodeScanner = scanner.nextLine();

            while (currencyCodeScanner.matches("-?\\d+(\\.\\d+)?")) {
                System.out.println("\u001B[31mInvalid currency code. Please enter letters only.\u001B[0m");
                currencyCodeScanner = scanner.nextLine();
            }

            String response = ApitTest.response(currencyCodeScanner);
            Gson gson = new Gson();
            TransitionApi transitionApi = gson.fromJson(response, TransitionApi.class);

            System.out.println("\u001B[34m============================\u001B[0m");
            System.out.println("\u001B[32mThis is the current currency:\u001B[0m \n" + transitionApi.base_code());
            System.out.println("\u001B[34m============================\u001B[0m");

            System.out.println("\u001B[32mHow much you want to convert?\u001B[0m ");
            double amount = scanner.nextDouble();

            while (amount <= 0) {
                System.out.println("\u001B[31mInvalid amount. Please enter a positive number.\u001B[0m");
                amount = scanner.nextDouble();
            }
            scanner.nextLine();

            System.out.println("\u001B[34m============================\u001B[0m");
            System.out.println("\u001B[32mThis is the amount you want to convert:\u001B[0m " + amount);
            System.out.println("\u001B[34m============================\u001B[0m");

            System.out.println("\u001B[32mTry to check the value of the conversion rates to a new Currency Code:\u001B[0m ");
            String newCurrencyCode = scanner.nextLine().toUpperCase();

            while (newCurrencyCode.matches("-?\\d+(\\.\\d+)?")) {
                System.out.println("\u001B[31mInvalid currency code. Please enter letters only.\u001B[0m");
                newCurrencyCode = scanner.nextLine();
            }

            Double conversionRate = transitionApi.conversion_rates().get(newCurrencyCode);
            Double finalAmount = conversionRate * amount;

            String output = "\u001B[33m============================\u001B[0m\n" +
                    "\u001B[32mThe conversion rate is:\u001B[0m " + conversionRate + "\n" +
                    "\u001B[32m" + amount + " " + transitionApi.base_code() + " is equal to " + finalAmount + " " + newCurrencyCode + "\u001B[0m\n" +
                    "\u001B[33m============================\u001B[0m";

            System.out.println(output);

            register.addHistory(output, LocalDateTime.now());

            System.out.println("\u001B[32mDo you want to continue? (Y/N)\u001B[0m");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("N")) {
                System.out.println("\u001B[35m============================\u001B[0m");
                System.out.println("\u001B[35mGoodbye!\u001B[0m");
                System.out.println("\u001B[35m============================\u001B[0m");
                break;
            }

        }

    }

    public void CheckingHistory(){
        System.out.println("\u001B[34mDo you wanna watch the history of your operations?\u001B[0m");
        System.out.println("\u001B[33m1. Yes\u001B[0m");
        System.out.println("\u001B[33m2. No\u001B[0m");

        Scanner scanner = new Scanner(System.in);

        String option;

        option = scanner.nextLine();
        while (!option.equals("1") && !option.equals("2")) {
            System.out.println("\u001B[31mInvalid option. Please enter 1 or 2.\u001B[0m");
            option = scanner.nextLine();
        }

        Integer optionSelected = Integer.parseInt(option);

        if (optionSelected == 1) {
            Register register = CurrencyComparator.getRegister();
            System.out.println("\u001B[32mOperation History:\u001B[0m");
            register.getHistoryList().forEach(System.out::println);

        } else {
            System.out.println("\u001B[34mGoodbye!\u001B[0m");
        }
    }
}

