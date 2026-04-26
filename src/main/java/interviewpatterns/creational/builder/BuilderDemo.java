package interviewpatterns.creational.builder;

public final class BuilderDemo {
    public static void main(String[] args) {
        Pizza pizza = new Pizza.PizzaBuilder("Large", "Thin Crust")
            .setExtraCheese(true)
            .setExtraSauce(true)
            .setToppings("Olives, Jalapenos")
            .build();

        System.out.println(pizza);
    }
}
