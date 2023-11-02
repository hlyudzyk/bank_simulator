package bank;


import java.time.LocalDateTime;


class Transaction {
    private final String transactionID;
    private final String accountFromId;
    private final String accountToId;
    private final double amount;
    private final LocalDateTime timestamp;
    private final boolean isSuccessfull;
    Transaction(Account accountFrom, Account accountTo, double amount) {
        this.transactionID = Bank.SecuredBankServices.generateNDigitCombination(8);
        this.accountFromId = accountFrom.getAccountId();
        this.accountToId = accountTo.getAccountId();
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
        this.isSuccessfull = completeTransaction(accountFrom,accountTo,amount);
    }

    public boolean isSuccessfull() {
        return isSuccessfull;
    }

    private boolean completeTransaction(Account accountFrom, Account accountTo,double amount){
        try{
            checkTransactionConditions(accountFrom,accountTo,amount);
            accountFrom.operatingCard.withdraw(amount);
            accountTo.operatingCard.deposit(amount);
        }
        catch (RuntimeException exception){
            return false;
        }
        return true;
    }
    private void checkTransactionConditions(Account accountFrom, Account accountTo,double amount) throws IllegalArgumentException{
        if(amount>accountFrom.getBalance()){
            throw new IllegalArgumentException("Not enough money on Account!");
        }
        else if (amount<0){
            throw new IllegalArgumentException("Invalid amount");
        }
    }

    @Override
    public String toString() {
        return "Transaction{" +
            "transactionID='" + transactionID + '\'' +
            ", accountFromId='" + accountFromId + '\'' +
            ", accountToId='" + accountToId + '\'' +
            ", amount=" + amount +
            ", timestamp=" + timestamp +
            ", isSuccessfull=" + isSuccessfull +
            '}';
    }
}