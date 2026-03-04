package bg.sofia.uni.fmi.mjt.eventbus.events;

public class StringPayload implements Payload<String> {

    private final String payload;

    /**
     * The constructor for the wrapper StringPayload.
     *
     * @param payload the string payload.
     * @throws IllegalArgumentException if payload is null or empty.
     * */
    public StringPayload(String payload) {
        if (payload == null) {
            throw new IllegalArgumentException("Payload is null");
        } else if (payload.isEmpty()) {
            throw new IllegalArgumentException("Payload is empty");
        }
        this.payload = payload;
    }

    @Override
    public int getSize() {
        return payload.getBytes().length;
    }

    @Override
    public String getPayload() {
        return payload;
    }
}
