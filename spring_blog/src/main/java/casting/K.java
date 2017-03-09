package casting;

class A {
	void show(int i) {
		System.out.println("A-show() : " + i);
	}

	void prn(int k) {
		System.out.println("A-prn() : " + k);
	}
}

class B extends A {
	void show(int i) {
		System.out.println("B-show():" + i);
	}

	void prn(String str) {
		System.out.println("B-prn():" + str);
	}

	void src() {
		System.out.println("B-scr()");
	}

	void a(int i) {
		super.show(i);
	}
}