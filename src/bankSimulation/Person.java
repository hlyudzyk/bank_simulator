package bankSimulation;

import bank.Account;
import java.time.LocalDate;

public class Person implements Purchaser{
    private final String personId;
    private final String fullName;
    private final LocalDate dateOfBirth;
    private String currentAddress;
    private String phoneNumber;
    private Account bankAccount;

    public Person(String personId, String fullName, LocalDate dateOfBirth, String currentAddress,
        String phoneNumber) {
        this.personId = personId;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.currentAddress = currentAddress;
        this.phoneNumber = phoneNumber;
    }

    public void setBankAccount(Account bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Account getBankAccount() {
        return bankAccount;
    }

    public String getPersonId() {
        return personId;
    }

    public String getFullName() {
        return fullName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public boolean purchase(Purchasable purchasable, Logger check) {
        if(this.bankAccount.transfer(purchasable.getSeller().getAccountToPay(),purchasable.getPrice())){
           check.log(
                        "<-----" +
                        fullName + " "
                        + bankAccount.getOperatingCard().getCardNumber()
                        + "---bought "
                        + purchasable.getName()
                        + " "
                        + purchasable.getPrice()
                        + bankAccount.getOperatingCurrency()
                        + " "
                        + LocalDate.now()
                        + " --- Balance: "
                        + bankAccount.getBalance()
                        + "----->");
           return true;
        }
        else {
            check.log("Failed purchase");
            return false;
        }
    }
}
