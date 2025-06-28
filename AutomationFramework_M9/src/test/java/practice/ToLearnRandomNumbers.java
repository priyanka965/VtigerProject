package practice;

import java.util.Random;

public class ToLearnRandomNumbers {

	public static void main(String[] args) {
		Random r=new Random();
		int randomValue = r.nextInt(1000);
		System.out.println(randomValue);

	}

}
