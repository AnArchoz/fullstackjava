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
            DynArr divMultLocations = new DynArr();

            try {
                // Check if numbers are numbers, (as even indexes, or odd elements)
                for (int i = 0; i < splitstring.length; i += 2) {
                    Integer.parseInt(splitstring[i]);
                }
            } catch (NumberFormatException e) {
                System.out.println("You did not enter numbers correctly");
                start();
            }

            // TODO: REVERT AND REBUILD, WORKS FOR ONLY 2 NUMBERS BUT NOW **COMPLETELY** WRONG LOL,
            for (String operator : operations) {
                for (int j = 1; j < splitstring.length; j += 2) {
                    // Check if operator exists (as odd indexes or even elements)
                    operatorsValid = splitstring[j].equals(operator);
                    if (operator.equals("/") | operator.equals("*")) {
                        pemdas = true;
                        // Add index as location in the array to dynamic array
                        // for the Calculate method to know where to look
                        divMultLocations.append(j);
                    }

                    // If operators are incorrect, restart
                    if (!operatorsValid) {
                        System.out.println("Please use only /*'- operators, one per operation");
                        start();
                    }
                }
            }


            double result = calculate(splitstring, divMultLocations, pemdas);
            System.out.println("The result is: " + result);

            // Lets user calculate another number
            start();
        }
    }

    /**
     * CALCULATES THE int NUMBERS AND RETURNS A double FOR NUMBERS WITH DECIMAL POINTS
     * Parses numbers from the string statement and applies the correct calculation based
     * on if PEMDAS is relevant, and if so where in the string statement the divider or multiplier is located
     */

    public double calculate(String[] statement, DynArr locations, boolean pemdas) {
        double result = 0, firstNum, secondNum;
        String operator;

        // Iterate through the statement only on operators, which are located in odd indexes.
        for (int i = 1; i < statement.length; i += 2) {
            operator = statement[i];

            // If there are dividers or multipliers, these must be calculated first
            if (pemdas) {
                for (int j = 0; j < locations.getSize(); j++) {
                    int loc = locations.getElement(j);
                    // Numbers to be calculated that are located in front of and behind the operator,
                    // and then added to the result
                    firstNum = Integer.parseInt(statement[loc - 1]);
                    secondNum = Integer.parseInt(statement[loc + 1]);

                    if (operator.equals("/")) {
                        result += (firstNum / secondNum);
                    } else if (operator.equals("*")) {
                        result += (firstNum * secondNum);

                    }
                }
            }

            // Assign numbers to be calculated, located in front of and behind the operator.
            firstNum = Integer.parseInt(statement[i - 1]);
            secondNum = Integer.parseInt(statement[i + 1]);

            switch (operator) {
                case "+":
                    result += firstNum + secondNum;
                case "-":
                    result -= firstNum - secondNum;
            }
        }

        return result;
    }
}
