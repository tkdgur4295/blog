package casting;

class Animal {
	public String name = "동물";

	public void sound() {
		System.out.println("동물소리~~~~~");
	}
}

class Dog extends Animal {
	public String name = "강아지";

	@Override
	public void sound() {
		System.out.println("멍멍~~~~~");
	}
}

class Cat extends Animal {
	String name = "고양이";

	@Override
	public void sound() {

		System.out.println("냐옹~~~~~");
	}
	

}
