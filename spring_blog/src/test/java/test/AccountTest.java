package test;

import junit.framework.TestCase;

public class AccountTest extends TestCase {

	Account account;

	protected void setUp() throws Exception {
		account = new Account(10000);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testAccount() {
		//fail("Not yet implemented");
	}

	public void testGetBalance() {
		assertEquals(10000, account.getBalance());
	}

	public void testWithdraw() {
		//fail("Not yet implemented");
		account.withdraw(7000);
		assertEquals(3000, account.getBalance());
	}

	public void testDeposit() {
		account.deposit(5000);
		assertEquals(15000, account.getBalance());
	}

}
