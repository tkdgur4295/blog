package casting;

public class N1 {

		public static void main(String[] args) {
		Dog dog = new Dog();
		Cat cat = new Cat();
		Animal animal = new Animal();

		dog.sound();// 멍멍
		cat.sound();// 냐옹
		animal.sound();// 동물소리


		Animal animalup = dog;//업캐스팅
		animalup.sound(); // 멍멍  동적바인딩 
		System.out.println("up.name"+animalup.name); //동물


		Dog dogdown = (Dog) animalup; // 다운캐스팅(업캐스팅되었던것만 가능)
		dogdown.sound();// 멍멍

		Cat catdown = (Cat) animal; // 자식에다 부모를 생성할순없다 에러
		catdown.sound();

		}

		}

