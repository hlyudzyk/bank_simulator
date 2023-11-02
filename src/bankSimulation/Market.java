package bankSimulation;

import bank.Account;

public class Market implements Seller{
    private String name;
    private Account accountToPay;

    public Market(String name, Account accountToPay) {
        this.name = name;
        this.accountToPay = accountToPay;
    }

    @Override
    public Account getAccountToPay() {
        return accountToPay;
    }

    @Override
    public String toString() {
        return "Market{" +
            "name='" + name + '\'' +
            ", accountToPay=" + accountToPay.getAccountId() +
            '}';
    }
}
