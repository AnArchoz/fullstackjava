import java.util.Scanner;

public class Main {
	private static Scanner user;

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

			// (re)start program
			start();
		} else {
			// Check if string contains numbers and operators
			String[] splitstring = input.split("\\s+");
			boolean pemdas = false;
			DynArr divMultLocations = new DynArr();

			try {
				// Check if numbers are numbers, (in even indexes, or odd elements)
				for (int i = 0; i < splitstring.length; i += 2) {
					Integer.parseInt(splitstring[i]);
				}
			} catch (NumberFormatException e) {
				System.out.println("You did not enter numbers correctly");

				// (re)start program
				start();
			}

			// Checks if the operation being used actually is an operation,
			// as */-+ (in odd indexes, or even elements)
			for (int i = 1; i < splitstring.length; i += 2) {
				String operation = splitstring[i];

				if (splitstring[i].equals("/") || splitstring[i].equals("*")) {
					pemdas = true;
					divMultLocations.append(i);
					continue;
				}

				if (!(operation.equals("-") | operation.equals("+"))) {
					System.out.println("Please use only /*+- operators, one per operation");

					// (re)start program
					start();
				}
			}

			double result = calculate(splitstring, divMultLocations, pemdas);
			System.out.println("The result is: " + result);

			// (re)start program
			start();
		}

	}

	/**
	 * CALCULATES THE int NUMBERS AND RETURNS A double FOR NUMBERS WITH DECIMAL POINTS
	 * Parses numbers from the string statement and applies the correct calculation based on
	 * if PEMDAS is relevant, and if so where in the string statement the divider or multiplier is located
	 */

	public double calculate(String[] statement, DynArr locations, boolean pemdas) {
		double result = 0, firstNum, secondNum;
		String operator;
		boolean first = true;
		// Iterate through the statement only on operators, which are located in odd indexes.
		for (int i = 1; i < statement.length; i += 2) {

			// If there are dividers or multipliers, these must be calculated first
			if (pemdas) {
				for (int j = 0; j < locations.getSize(); j++) {
					int loc = locations.getElement(j);
					operator = statement[loc];

					// Numbers to be calculated that are located in front of and behind the operator,
					// and then added to the result
					firstNum = Integer.parseInt(statement[loc - 1]);
					secondNum = Integer.parseInt(statement[loc + 1]);

					// If any of the numbes are 0 just return 0, because
					// A: can't divide by 0
					// B: any multiplication becomes 0 anyway
					if (firstNum == 0 || secondNum == 0) {
						result = 0;
						break;
					}

					if (operator.equals("/")) {
						if (first) {
							result = (firstNum / secondNum);
							first = false;
						} else {
							result /= secondNum;
						}
					} else if (operator.equals("*")) {
						if (first) {
							result = (firstNum * secondNum);
							first = false;
						} else {
							result *= secondNum;
						}
					}
				}
			}
			operator = statement[i];
			// Assign numbers to be calculated, located in front of and behind the operator.

			firstNum = Integer.parseInt(statement[i - 1]);
			secondNum = Integer.parseInt(statement[i + 1]);

			switch (operator) {
				case "+":
					if (first) {
						result += (firstNum + secondNum);
					} else {
						result += secondNum;
					}
					break;
				case "-":
					if (first) {
						result += (firstNum - secondNum);
					} else {
						result -= secondNum;
					}
					break;
			}

		}
		return result;
	}
}
