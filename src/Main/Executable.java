package src.Main;

import src.Consult.CurrencyComparator;

public class Executable {
    public static void main(String[] args) {


        CurrencyComparator absolute = new CurrencyComparator();
        absolute.comparingPrices();

        absolute.CheckingHistory();
    }
}