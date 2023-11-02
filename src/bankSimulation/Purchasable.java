package bankSimulation;

import bank.Account;

public interface Purchasable {
    double getPrice();
    Seller getSeller();
    String getName();
}