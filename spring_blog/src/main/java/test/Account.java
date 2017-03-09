package test;

public class Account {
	private int account;

	public Account(int i) {
		account = i;
		System.out.println("생성자 호출 " + account);
	}

	public int getBalance() {
		return account;
	}

	/** 출금 **/
	public void withdraw(int i) {
		account = account - i;
		System.out.println("값 호출 " + account);
	}

	/** 예금 **/
	public void deposit(int i) {
		account = account + i;
		System.out.println("값 호출 " + account);
	}
}