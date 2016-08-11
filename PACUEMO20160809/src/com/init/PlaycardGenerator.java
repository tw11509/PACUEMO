package com.init;

import java.util.HashMap;
import java.util.Random;

public class PlaycardGenerator {

	static Random random = new Random();
	static String[] position = { "'C'", "'PF'", "'SF'", "'SG'", "'PG'" };
	static String[] location = { "新北市", "台北市", "桃園市", "基隆市" };

	public static void main(String[] args) {

		for (int i = 1; i <= 144; i++) {
			int gender = random.nextInt(2);
			int hand = random.nextInt(2);
			double height = 150 + (Math.random() * 60);
			double weight = 40 + (Math.random() * 70);
			String note = "";

			int totalPoint = 20;

			int a = random.nextInt(21);
			int b = random.nextInt(21 - a);
			int c = random.nextInt(21 - a - b);
			int d = random.nextInt(21 - a - b - c);
			int e = random.nextInt(21 - a - b - c - d);
			int f = random.nextInt(21 - a - b - c - d - e);

			String str = String.format("INSERT INTO PLAYERCARD SELECT %d, %d, %.2f, %.2f, %2s, %d," + " '%s', '%s', %d, %d, %d, %d, %d, %d",
					i,gender, height, weight, position[random.nextInt(5)], hand, note, location[random.nextInt(4)], a, b,
					c, d, e, f);
			
			System.out.println(str);

		}

	}
}
