package interviewpatterns.structural.facade;

public final class FacadeDemo {
    public static void main(String[] args) {
        BookingFacade facade = new BookingFacade();
        System.out.println(facade.bookTicket("u1", "m1", "show1", "A5", 500));
    }
}
