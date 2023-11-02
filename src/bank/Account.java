package bank;

import bank.Bank.Currency;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private final String accountId;
    private final Bank parentBank;
    private final String holderId;
    private final String holderFullName;
    private double balance;
    private List<Card> cardList;
    Card operatingCard;
    private Currency operatingCurrency;

    Account(Bank parentBank,String holderId,String holderFullName) {
        this.parentBank = parentBank;
        this.cardList = new ArrayList<>();
        this.accountId = Bank.SecuredBankServices.generateNDigitCombination(8);
        this.holderId = holderId;
        this.holderFullName = holderFullName;
        this.balance = 0.0;
        this.operatingCurrency = Currency.USD;
    }

    public String getAccountId() {
        return accountId;
    }

    public double getBalance() {
        switch (operatingCurrency){
            case UAH -> {
                return balance*Bank.SecuredBankServices.getUahToUsdRatio();
            }
            case EUR -> {
                return balance*Bank.SecuredBankServices.getEurToUsdRatio();
            }
        }
        return balance;
    }

    public String getHolderId() {
        return holderId;
    }

    public String getHolderFullName() {
        return holderFullName;
    }

    public Currency getOperatingCurrency() {
        return operatingCurrency;
    }

    public void setOperatingCurrency(Currency operatingCurrency) {
        this.operatingCurrency = operatingCurrency;
    }

    public Card getOperatingCard() {
        return operatingCard;
    }

    public void setOperatingCard(int cardIndex) {
        if (cardList.isEmpty()) {
            return;
        }
        this.operatingCard = cardList.get(cardIndex);
    }

    public Card createNewCard(String cardName,String holderId,String holderName){
        Card newCard = new Card(cardName,holderId,holderName);
        this.cardList.add(newCard);
        if(operatingCard==null){
            operatingCard = newCard;
        }

        return newCard;
    }

   public boolean transfer(Account targetAccount, double amount) {
        return parentBank.proceessTransaction(this,targetAccount,amount);
   }

    public class Card {
        private final String cardNumber;
        private final LocalDate expiryDate;
        private final String cvv;
        private String cardName;
        private String holderId;
        private String holderName;

        Card(String cardName,String holderId,String holderName) {
            this.cardNumber = Bank.SecuredBankServices.generateNDigitCombination(16);
            this.cvv = Bank.SecuredBankServices.generateNDigitCombination(3);
            this.expiryDate = LocalDate.now().plusYears(4);
            this.cardName = cardName;
            this.holderId = holderId;
            this.holderName = holderName;

        }

        public String getCardNumber() {
            return cardNumber;
        }

        public void deposit(double amount) throws IllegalArgumentException{
            // Add money to the balance
            balance += amount;
        }
        public void withdraw(double amount) throws IllegalArgumentException{
            // Deduct money from the balance
            balance -= amount;
        }

        @Override
        public String toString() {
            return "Card{" +
                "cardNumber='" + cardNumber + '\'' +
                ", cardAccountId=" + accountId + '\'' +
                ", expiryDate=" + expiryDate +
                ", cvv='" + cvv + '\'' +
                ", cardName='" + cardName + '\'' +
                ", cardHolderName='" + holderName + '\'' +
                '}';
        }
    }

    @Override
    public String toString() {
        return "Account{" +
            "accountId='" + accountId + '\'' +
            ", holderId='" + holderId + '\'' +
            ", holderFullName='" + holderFullName + '\'' +
            ", balance=" + getBalance() + operatingCurrency.toString() +
            ", cardList=" + cardList +
            '}';
    }
}

