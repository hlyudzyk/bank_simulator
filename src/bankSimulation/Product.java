package bankSimulation;

public class Product implements Purchasable {
    public String name;
    public double price;

    public Seller seller;
    public Product(String name, double price,Seller seller) {
        this.name = name;
        this.price = price;
        this.seller = seller;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public Seller getSeller() {
        return seller;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Product{" +
            "name='" + name + '\'' +
            ", price=" + price +
            ", seller=" + seller +
            '}';
    }
}
