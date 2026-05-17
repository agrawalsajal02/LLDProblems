package pubsubsystem.pubsubmemorytemplate;

public final class Message {
    private final String msg;

    public Message(String msg) {
        if (msg == null || msg.isBlank()) {
            throw new IllegalArgumentException("message cannot be blank");
        }
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
