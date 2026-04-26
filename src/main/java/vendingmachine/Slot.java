package vendingmachine;

public final class Slot {
    private final int code;
    private Item item;
    private boolean soldOut;

    public Slot(int code) {
        this.code = code;
        this.soldOut = true;
    }

    public int getCode() {
        return code;
    }

    public Item getItem() {
        return item;
    }

    public boolean isSoldOut() {
        return soldOut;
    }

    public void stock(Item item) {
        this.item = item;
        this.soldOut = false;
    }

    public void markSoldOut() {
        this.soldOut = true;
    }
}
