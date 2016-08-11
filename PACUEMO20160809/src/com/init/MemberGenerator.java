package com.init;

import java.io.File;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

import java.util.List;
import java.util.Random;

public class MemberGenerator {

	private static String nameLine = "葉函鈺、丁淳來、王詩福、陳嘉軒、邱佑霖、謝志屏、張金翰、廖建良、巫尚輝、林平名、謝崇迪、吳宜友、曹定淑、林旭群、盧正新、楊珮如、游豪筠、饒雅芳、謝雲鳳、賴心怡、王孟哲、陳芝寶、黃珮恭、"
			+ "林婉均、許奕廷、錢靜火、賴家祥、林慧峰、敖小萍、趙萱隆、宋朝岑、蔡俊良、林雅均、黃千軒、吳佳琪、傅秋萍、曾小昀、葉奕廷、陳志海、陳依婷、黃梅民、倪怡君、曾俊傑、戈俐蓮、徐雅婷、蔡宣希、李怡君"
			+ "、林孟儒、劉睿瑄、陳育萱、周盈秀、陳姿雅、楊羽燕、謝孟芝、許淑人、劉佳靜、許宏然、蔣文豪、彭俊逸、洪佑瑜、吳佩珊、辛俊維、金莉蘋、賴淑娟、黃常恆、王昱妃、王智亞、汪華馨、吳協佳、楊建安、倪志文、"
			+ "汪綺函、王培尹、王佩君、楊旺丞、吳慧苓、張雅文、丁可任、李慧君、陳映冰、林鳳珠、彭怡秀、吳玉華、李佳幸、謝志瑋、詹士瑋、藍欣怡、陳瑋山、曾婉婷、吳紹蓁、丁健安、蘇芷洋、施季其、蔡珮裕、王文旺、白宜潔、林冠依、陳婉麟、黃京能、黃文宏"
			+ "、邱呈方、吳如湖、張文豪、陳財帆、陳冠勇、李辛盈、何旺紋、楊雅惠、李信宏、楊宇馨、葉麗美、吳志薇、柯雅娟、陳信禾、詹晴瑤、林元志、趙思妤、羅致峰、趙燕嘉、舒予龍、杜行心、黃國惟、何燦琪、陳美其、陳逸玉、王建智、王光學、林靜昆、吳孟菱、陳隆龍、"
			+ "鄭馨鑫、王淑玉、鄭嘉琪、郭怡君、吳惠君、蔡翊友、呂凡沛、何淑婷、陳美彬、陳賢慧、陳柏翰、陳良夢、韓易凱、李依菁、蕭志偉";

	private static String[] emailDomain = { "@yahoo.com.tw", "@gmail.com", "@hotmail.com", "@yahoo.com",
			"@pchome.com.tw" };

	public static String[] cutName(String nameLine) {

		String[] names = nameLine.split("、");

		return names;
	}

	public static void main(String[] args) {

		List<String> firstNames = new ArrayList<>();
		List<String> lastNames = new ArrayList<>();

		
		String[] firstName;
		String[] lastName;

		File allFiles = new File("C:\\PaCueMo\\image\\member");
		File[] files = allFiles.listFiles();

		Random random = new Random();

		String[] names = cutName(nameLine);

		for (String name : names) {
			String last = name.substring(0, 1);
			String first = name.substring(1);

			firstNames.add(first);
			lastNames.add(last);
		}

		firstName = firstNames.toArray(new String[144]);
		lastName = lastNames.toArray(new String[144]);

		
		for (int i = 0; i < 144; i++) {

			long timeMillis = (long) (Math.random() * 1104537600000l);
			Date birthDay = new Date(timeMillis);
			String phoneNumber = String.format("09%08d", random.nextInt(100000000));
			long rgTimeMillis = 1467331200000l + (long) (Math.random() * (System.currentTimeMillis() - 1467331200000l));
			Timestamp rgDate = new Timestamp(rgTimeMillis);

			String rgDatetime = rgDate.toString();
			
			StringBuilder sb = new StringBuilder();
			for (int j = 1; j <= 6; j++) {
				char c = (char) (random.nextInt(25) + 97);
				sb.append(c);
			}

			String mail = String.format("'%s%06d%s'", sb.toString(), random.nextInt(1000000),
					emailDomain[random.nextInt(5)]);

			String fileName = "'"+files[random.nextInt(files.length)].getName()+"'";

			String str = String.format(
					"INSERT INTO MEMBER SELECT '%s','%s','123456789','%s','%s',%30s,%20s,1000,1,NULL,0,'%-23s',NULL,NULL,NULL,NULL",
					firstName[i], lastName[i], birthDay.toString(), phoneNumber, mail, fileName,rgDatetime);
			
			System.out.println(str);
		}
	}

}
