import java.util.Scanner;

public class Main {
    private static Scanner user;
    private final String[] operations = {"+", "-", "/", "*"};

    public static void main(String[] args) {
        Main app = new Main();
        user = new Scanner(System.in);
        app.start();
    }

    public void start() {
        System.out.println("Please enter two numbers and an operation with spaces in between, or type 'quit' to end the program.");
        String input = user.nextLine().toLowerCase();

        // End program if input = quit
        if (input.equals("quit")) {
            System.out.println("Good bye!");
            System.exit(0);

        // FINALLY WORKING REGEX: Checks that string only contains numbers or the chosen operators
        } else if (!input.matches("^[\\s0-9*/+-]+\\d$")) {
            System.out.println("Enter only numbers and operations");
            start();
        } else {
            // Check if string contains numbers and operators
            String[] splitstring = input.split("\\s+");
            boolean found = false;
            int number1 = 0, number2 = 0;
            String operator;
            // Check if string contains exactly two numbers and operators
            // TODO: CHANGE FOR ADDITION AND SUBTRACTION
            if (splitstring.length != 3) {
                System.out.println("Please enter only two numbers and an operator");
                start();
            }

            try {
                // Check if numbers are numbers and not text
                number1 = Integer.parseInt(splitstring[0]);
                number2 = Integer.parseInt(splitstring[2]);
            } catch (NumberFormatException e) {
                System.out.println("You did not enter correct numbers");
                start();
            }

            for (String operation : operations) {
                // Check if operator exists
                if (splitstring[1].equals(operation)) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Please use only /*'- operators");
                start();
            }

            // Assign numbers and operators properly and send to calculation
            // TODO: Maybe probably move to calculate method
            operator = splitstring[1];
            double result = calculate(operator, number1, number2);
            System.out.println("The result is: " + result);

            // Lets user calculate another number
            start();
        }
    }

    /**
     *
     * CALCULATES THE int NUMBERS AND RETURNS A DOUBLE for numbers with decimal points
     */


    public double calculate(String operator, int number1, int number2) {

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
