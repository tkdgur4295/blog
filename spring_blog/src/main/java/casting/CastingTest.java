package casting;

public class CastingTest implements Casting {

	@Override
	public int A() {
		int a = 1;
		return a;
	}

	@Override
	public String B() {
		String b = "금요일";
		return b;
	}

	public static void main(String[] args) {

		Casting a = new CastingTest();
		Casting b = new CastingTest();
		
		System.out.println(a.A());
		System.out.println(b.B());

	}

}
