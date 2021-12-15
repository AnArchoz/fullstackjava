import java.util.Scanner;

public class Main {
    private static Scanner user;
    private final String[] operations = {"+", "-", "/", "*"};

    public static void main(String[] args) {
        Main app = new Main();
        user = new Scanner(System.in);
        app.start();
    }

    /**
     * VALIDATES THE COMMAND THE USER INPUTS, IN ORDER TO PROPERLY CALCULATE NUMBERS USING ADDITION,
     * SUBTRACTION, DIVISION, AND MULTIPLICATION FOLLOWING THE PEMDAS RULE OF HIERARCHY.
     */
    public void start() {
        System.out.println("Please enter two numbers and an operation with spaces in between, or type 'quit' to end the program.");
        String input = user.nextLine().toLowerCase();

        // End program if input = quit
        if (input.equals("quit")) {
            System.out.println("Good bye!");
            System.exit(0);

            // Checks that string only contains numbers and the chosen operators
        } else if (!input.matches("^[\\s0-9*/+-]+\\d$")) {
            System.out.println("Enter only numbers and operations");
            start();
        } else {
            // Check if string contains numbers and operators
            String[] splitstring = input.split("\\s+");
            boolean operatorsValid = false;
            boolean pemdas = false;
            // TODO: Build dynamicArray class for holding operators
            DynArr divMultLocations = new DynArr(new int[1]);

            // Check if string contains exactly two numbers and operators
            // TODO: CHANGE FOR PEMDAS
            // if (splitstring.length != 3) {
            //  System.out.println("Please enter only two numbers and an operator");
            // start();
            //}

            try {
                // Check if numbers are numbers, (as even indexes or odd elements)
                for (int i = 0; i < splitstring.length; i += 2) {
                    Integer.parseInt(splitstring[i]);
                }
            } catch (NumberFormatException e) {
                System.out.println("You did not enter correct numbers");
                start();
            }

            for (int i = 0; i < operations.length; i++) {
                String operation = operations[i];
                // Check if operator exists (as odd indexes or even elements)
                operatorsValid = i % 2 != 0 | splitstring[i].equals(operation);
                if (operation.equals("/") | operation.equals("*")) {
                    pemdas = true;
                    // TODO: Implement dynamic array
                    // Add index as location in the array for the Calculate method to know where to look
                    divMultLocations.append(i);
                }
            }

            // If operators are incorrect, restart
            if (!operatorsValid) {
                System.out.println("Please use only /*'- operators, one per operation");
                start();
            }

            double result = calculate(splitstring, divMultLocations, pemdas);
            System.out.println("The result is: " + result);

            // Lets user calculate another number
            start();
        }
    }

    /**
     * CALCULATES THE int NUMBERS AND RETURNS A double FOR NUMBERS WITH DECIMAL POINTS
     */

    // TODO: REBUILD COMPLETELY
    public double calculate(String[] statement, DynArr locations, boolean pemdas) {
        double result = 0;
        switch (operator) {
            case "*":
                return number1 * number2;
            case "+":
                return number1 + number2;
            case "-":
                return number1 - number2;
            case "/":
                if (number1 == 0 | number2 == 0) {
                    System.out.println("can't divide with 0!!!");
                    return -1;
                } else {
                    return (double) number1 / number2;
                }

        }

        return -1;
    }
}
