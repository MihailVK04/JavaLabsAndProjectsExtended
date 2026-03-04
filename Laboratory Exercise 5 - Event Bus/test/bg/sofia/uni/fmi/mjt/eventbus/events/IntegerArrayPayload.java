package bg.sofia.uni.fmi.mjt.eventbus.events;

public class IntegerArrayPayload implements Payload<int[]> {

    private final int[] integerArray;

    /**
     * Constructor for payloads of array of integers.
     *
     * @param array the integer array payload.
     * @throws IllegalArgumentException if the array is null.
     * */
    public IntegerArrayPayload(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Argument is null");
        }
        integerArray = array;
    }

    @Override
    public int getSize() {
        return integerArray.length * Integer.BYTES;
    }

    @Override
    public int[] getPayload() {
        return integerArray;
    }
}
