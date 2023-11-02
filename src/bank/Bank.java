package bank;

import bankSimulation.Person;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class Bank {
    List<Account> accounts;
    TransactionHistory transactionHistory;

    public Bank(){
        accounts = new ArrayList<>();
        transactionHistory = TransactionHistory.getInstance();
    }

    boolean proceessTransaction(Account accountFrom, Account accountTo, double amount){
        Transaction transaction = new Transaction(accountFrom,accountTo,amount);
        transactionHistory.recordTransaction(transaction);
        return transaction.isSuccessfull();
    }

    public Account applyForNewAccount(Person person){
        Account account = new Account(this,person.getPersonId(),person.getFullName());
        person.setBankAccount(account);
        return account;
    }


    public void showTransactionHistory(){
        transactionHistory.getTransactions().forEach(System.out::println);
    }

    static class SecuredBankServices{
        private static double uahToUsdRatio = 36.5;
        private static double eurToUsdRatio = 0.91;

        public static double getUahToUsdRatio() {
            return uahToUsdRatio;
        }

        public static double getEurToUsdRatio() {
            return eurToUsdRatio;
        }

        void updateCurrencyRatios(double uahToUsdRatio, double eurToUsdRatio){
            this.eurToUsdRatio = eurToUsdRatio;
            this.uahToUsdRatio = uahToUsdRatio;
        }

        static String generateNDigitCombination(int length) {
            SecureRandom secureRandom = new SecureRandom();
            StringBuilder stringBuilder = new StringBuilder(length);

            for (int i = 0; i < length; i++) {
                int digit = secureRandom.nextInt(10); // Generate a random digit (0-9)
                stringBuilder.append(digit);
            }

            return stringBuilder.toString();
        }
    }
    public enum Currency {
        UAH("UA Hryvnia"),
        USD("USA Dollar"),
        EUR("Euro");
        private final String currencyName;
        Currency(String currencyName) {
            this.currencyName = currencyName;
        }
    }
}



