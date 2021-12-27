package bankacc;

public class testBank {
	public static void main(String[] args) {
		BankAccount account = new BankAccount(1, 500, "Mehrdad", "m@lexicon.java", "02020202");

		System.out.println(account);

		System.out.println(account.withdraw(501));
		account.setAccountNumber(2);
		account.setEmail("l@lelele");
		System.out.println(account);

		System.out.println(account.withdraw(500));
		account.deposit(1000);
		System.out.println(account);

	}
}
