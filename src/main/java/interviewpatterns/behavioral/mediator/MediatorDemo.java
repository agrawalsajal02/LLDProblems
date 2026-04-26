package interviewpatterns.behavioral.mediator;

public final class MediatorDemo {
    public static void main(String[] args) {
        ChatMediator mediator = new ChatRoomMediator();

        ChatUser alice = new ChatUser("Alice", mediator);
        ChatUser bob = new ChatUser("Bob", mediator);
        ChatUser charlie = new ChatUser("Charlie", mediator);

        mediator.addUser(alice);
        mediator.addUser(bob);
        mediator.addUser(charlie);

        alice.send("Deploy complete");
        bob.send("I am checking logs");
    }
}
