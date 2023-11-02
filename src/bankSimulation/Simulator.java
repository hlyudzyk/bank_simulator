package bankSimulation;

import bank.Account;
import bank.Bank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Simulator {
    Bank continentalBank = new Bank();

    Seller market;//Tony stark is owner of market and he earns money
    List<Product> products;
    List<Person> people;
    public Simulator(){
        people = new ArrayList<>();
        people.add(new Person("421523", "John Wick", LocalDate.now(), "Wall street", "0111324509"));
        people.add(new Person("452128", "Winston", LocalDate.now(), "Wall street", "0334567809"));
        people.add(new Person("942125", "Tony Stark", LocalDate.now(), "New York", "0889765234"));
        people.add(new Person("643203", "Peter Parker", LocalDate.now(), "New York", "0998765432"));

        Person tonyStark = people.get(2);
        continentalBank.applyForNewAccount(tonyStark).createNewCard("Market card",
            tonyStark.getPersonId(),tonyStark.getFullName());


        market = new Market("ATB",tonyStark.getBankAccount());
        products = new ArrayList<>();
        products.add(new Product("Milk",5,market));
        products.add(new Product("Chocolate",7.35, market));
        products.add(new Product("Bread",3.40,market));
        products.add(new Product("Apple Juice",6.20,market));
        products.add( new Product("Orange Juice",6.20,market));
    }

    public void launchSimulation(){
        Scanner scanner = new Scanner(System.in);
        Person person = Authorize(scanner);
        if(person==null){
            return;
        }
        continentalBank.applyForNewAccount(person);
        person.getBankAccount().createNewCard("primary", person.getPersonId(), person.getFullName());
        person.getBankAccount().getOperatingCard().deposit(100);
        shop(person,scanner);
        System.out.println("Transaction history:");
        continentalBank.showTransactionHistory();
        scanner.close();
    }

    private void shop(Person person,Scanner scanner){
       do {
            System.out.println("Choose the product by entering index or [exit]\n");
            for (Product product:products){
                System.out.println(product.toString());
            }

            Product product = getProduct(scanner);
            if(product==null) break;
            if(!person.purchase(product, message -> System.out.println(message))) return;
        }
       while(!"exit".equalsIgnoreCase(scanner.nextLine()));
    }

    private Product getProduct(Scanner scanner){
        int n;
        do {
            System.out.print("Enter the index or [exit]: ");
            while (!scanner.hasNextInt()) {
                if("exit".equalsIgnoreCase(scanner.nextLine())){
                    return null;
                }
                System.out.println("Input error. Enter the number > 0");
                System.out.print("Enter the index or [exit]: ");
                scanner.next();
            }
            n = scanner.nextInt();
            if (n < 0 || n >= products.size()) {
                System.out.println("Input error. Try again");
            }
        } while (n < 0 || n >= products.size());

        return products.get(n);
    }
    private Person Authorize(Scanner scanner) {

        Person foundPerson = null;

        while (foundPerson == null) {
            System.out.println("Enter a name (or 'exit' to quit): ");
            String input = scanner.nextLine();

            if ("exit".equalsIgnoreCase(input)) {
                break;
            }

            for (Person person : people) {
                if (person.getFullName().equals(input)) {
                    foundPerson = person;
                    break;
                }
            }

            if (foundPerson == null) {
                System.out.println("No person with that name found. Try again.");
            }
        }

        return foundPerson;
    }


}
