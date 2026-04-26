package ordermanagementsystem;

public final class User {
    private final String id;
    private final String name;
    private final Address address;
    private final Cart cart = new Cart();

    public User(String id, String name, Address address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public Cart getCart() {
        return cart;
    }
}
