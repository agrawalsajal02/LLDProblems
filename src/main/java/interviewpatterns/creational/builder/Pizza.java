package interviewpatterns.creational.builder;

public class Pizza {
    private final String size;
    private final String crust;
    private final boolean extraSauce;
    private final boolean extraCheese;
    private final String toppings;

    private Pizza(PizzaBuilder pizzaBuilder) {
        this.size = pizzaBuilder.size;
        this.crust = pizzaBuilder.crust;
        this.extraSauce = pizzaBuilder.extraSauce;
        this.extraCheese = pizzaBuilder.extraCheese;
        this.toppings = pizzaBuilder.toppings;
    }

    @Override
    public String toString() {
        return "Pizza [Size=" + size
            + ", Crust=" + crust
            + ", ExtraCheese=" + extraCheese
            + ", ExtraSauce=" + extraSauce
            + ", Toppings=" + toppings + "]";
    }

    public static class PizzaBuilder {
        private final String size;
        private final String crust;

        private boolean extraSauce = false;
        private boolean extraCheese = false;
        private String toppings = "";

        public PizzaBuilder(String size, String crust) {
            this.size = size;
            this.crust = crust;
        }

        public PizzaBuilder setExtraCheese(boolean extraCheese) {
            this.extraCheese = extraCheese;
            return this;
        }

        public PizzaBuilder setExtraSauce(boolean extraSauce) {
            this.extraSauce = extraSauce;
            return this;
        }

        public PizzaBuilder setToppings(String toppings) {
            this.toppings = toppings;
            return this;
        }

        public Pizza build() {
            // Hinglish: builder ka kaam hai required + optional fields ko ek jagah assemble karke final object dena.
            return new Pizza(this);
        }
    }
}
