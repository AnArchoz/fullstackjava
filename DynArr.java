public class DynArr {
    private int[] values = new int[0];
    private int arraySize;
    private int lastElement;

    // Constructor for creating a dynamic array with the contents of a normal array
    // First checks the size of the original array, and accordingly creates a larger dynamic array.
    DynArr(int[] operators) {
        int arrSize = operators.length;

        while (this.values.length <= operators.length) {
            grow();
        }

    }

    private void grow() {
        int[] tempArray = new int[values.length + values.length / 2];

        for (int i = 0; i < values.length; i++) {
            tempArray[i] = values[i];
        }

        values = tempArray;

    }

    private int getSize() {
        return values.length;
    }

    private void append(int value) {

    }

    private void append(int[] values) {

    }

}
