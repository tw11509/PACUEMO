package com.init;

import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SBL {

	public static void main(String[] args) throws Exception {
		Parsing();
	}

	public static void Parsing() throws Exception {

		int countFight = 1;
		int countclub = 1;
		int memberid = 1;

		for (int h = 87; h <= 92; h++) {

			URL url = new URL("http://sbl.basketball-tpe.org/files/902-1002-50" + h + "-10000.php");
			Document xmlDoc = Jsoup.parse(url, 20000);

			// Elements allTable = xmlDoc.getElementsByTag("table");

			Elements allTable = xmlDoc.getElementsByClass("listTB");

			Element team1 = allTable.get(2);
			Element team2 = allTable.get(3);
			Elements team1TRs = team1.getElementsByTag("tr");
			Elements team2TRs = team2.getElementsByTag("tr");

			for (int i = 2; i < 14; i++) {
				Elements team1TDs = team1TRs.get(i).getElementsByTag("td");
				System.out.print(countFight + "|" + countclub + "|" + memberid + "|");
				for (int j = 0; j < team1TDs.size() - 1; j++) {
					if (j == 1) {
						continue;
					}
					System.out.print(team1TDs.get(j).text() + "|");

					System.out.println();
				}
				memberid++;
				System.out.println();

			}

			countclub++;

			for (int i = 2; i < 14; i++) {
				Elements team2TDs = team2TRs.get(i).getElementsByTag("td");
				System.out.print(countFight + "|" + countclub + "|" + memberid + "|");
				for (int j = 0; j < team2TDs.size() - 1; j++) {
					if (j == 1) {
						continue;
					}
					System.out.print(team2TDs.get(j).text() + "|");

				}

				System.out.println();
				memberid++;
			}

			countclub++;
			countFight++;
		}
	}

}