public class DynArr {
    private int[] values;
    private int size;
    private int lastElement;

    // Constructor for creating a dynamic array with the contents of a normal array
    // First checks the size of the original array, and accordingly creates a larger dynamic array.
    DynArr(int[] elements) {
        this.values = new int[elements.length + 1];
        this.size = this.values.length;
    }

    // Default constructor for when no values exist
    DynArr() {
        this.values = new int[1];
        this.size = this.values.length;
    }

    private void grow() {
        int[] tempArray = new int[this.values.length + this.values.length / 2];

        for (int i = 0; i < this.values.length; i++) {
            tempArray[i] = this.values[i];
        }

        this.values = tempArray;
        this.size = this.values.length;
        lastElement = this.size;
    }

    public int getSize() {
        return this.size;
    }

    public void append(int value) {
        if (values.length == lastElement) {
            grow();
        }

        this.values[lastElement] = value;
    }

    public void append(int[] elements) {

        while (elements.length >= this.size) {
            grow();
        }

        for (int element : elements) {
            this.values[lastElement++] = element;
        }
    }

    public int getElement(int i) {
        return this.values[i];
    }
}
