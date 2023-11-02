package bank;

import java.util.ArrayList;
import java.util.List;

//Singleton class
public class TransactionHistory {
    private static TransactionHistory transactionHistory;
    private static List<Transaction> transactions;
    private TransactionHistory(){
        transactions = new ArrayList<>();
    }
    static TransactionHistory getInstance(){
        if(transactionHistory==null){
            transactionHistory = new TransactionHistory();
        }
        return transactionHistory;
    }
    void recordTransaction(Transaction transaction){
        transactions.add(transaction);
    }
    public List<Transaction> getTransactions() {
        return transactions;
    }
}
