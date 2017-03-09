package casting;

public class K1 {
	public static void main(String[] args) {

		A a1 = new A();
		a1.show(1);
		a1.prn(1);
		System.out.println();
		B b1 = new B();
		b1.show(1);
		b1.prn("AA");
		b1.src();
		b1.a(2);
		System.out.println();

		A a2 = new B(); // 업캐스팅
		a2.prn(2);

		// a2.prn("A");
		System.out.println();

		B b2 = (B) a2; // 다운캐스팅 업캐스팅 시킨것을 다시 원래형태로
		b2.show(1);

	}
}
