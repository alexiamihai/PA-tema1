import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Oferta {
	public static void main(String[] args) {
		try {
			//File file = new File("public_tests/oferta/input/23-oferta.in");
			File file = new File("oferta.in");
			Scanner scanner = new Scanner(file);
			int N = scanner.nextInt();
			int K = scanner.nextInt();
			int[] prices = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				prices[i] = scanner.nextInt();
			}
			scanner.close();
			double price = 0, price1, price2, price3;
			int i;
			if (K == 1) {
				double[] dp = new double[N + 1];
				dp[1] = prices[1];
				// verific mai intai daca am mai mult de un element
				if (N > 1) {
					// daca am 2 produse, voi obtine cel mai bun pret folosind
					// oferta 1
					if (prices[1] < prices[2]) {
						dp[2] = prices[1] * 0.5 + prices[2];
					} else {
						dp[2] = prices[2] * 0.5 + prices[1];
					}
					// avem 3 cazuri
					for (i = 3; i <= N; i++) {
						// cazul in care pastram pretul gruparii anterioare
						// si doar adaugam pretul noului produs
						price1 = dp[i - 1] + prices[i];
						// cazul in care pastram pretul gruparii de acum 2 pasi
						// si aplicam oferta 1 pt ultimele 2 produse
						if (prices[i] < prices[i - 1]) {
							price2 = prices[i] * 0.5 + prices[i - 1] + dp[i - 2];
						} else {
							price2 = prices[i - 1] * 0.5 + prices[i] + dp[i - 2];
						}
						// cazul in care pastram pretul gruparii de acum 3 pasi
						// si aplicam oferta 2 pentru ultimele 3 produse
						double free = Math.min(Math.min(prices[i], prices[i - 1]), prices[i - 2]);
						price3 = dp[i - 3] + prices[i] + prices[i - 1] + prices[i - 2] - free;
						// la final, pastram minimul dintre cele 3 preturi
						// obtinute
						dp[i] = Math.min(Math.min(price1, price2), price3);
					}
				}
				price = dp[N];
			} else {
				// am folosit o matrice pentru a calcula calcula si a retine primele k preturi
				// obtinute folosind 1, 2, ..., n produse
				double[][] dp = new double[N + 1][K + 1];
				int j;
				// daca avem un singur obiect, nu putem avea mai mult de un pret
				if (N == 1 && K > 1) {
					price = -1;
				} else {
					// cazurile initiale
					dp[1][1] = prices[1];
					// daca am 2 obiecte, pot aplica oferta 1 sau pot alege sa adun preturile
					dp[2][2] = prices[1] + prices[2];
					if (prices[1] < prices[2]) {
						dp[2][1] = prices[1] * 0.5 + prices[2];
					} else {
						dp[2][1] = prices[2] * 0.5 + prices[1];
					}
					for (i = 3; i <= N; i++) {
						// trebuie sa calculez preturile posibile pentru restul obiectelor
						int a = 1, b = 1, c = 1, slay = 0;
						double ok = - 1;
						price1 = price2 = price3 = Double.POSITIVE_INFINITY;
						j = 1;
						// am nevoie de preturile calculate acum 1, 2, 3 pasi asa ca voi
						// calcula preturile noi concomitent si voi crea vectorul final
						// cu cele mai mici k preturi
						while (j <= K) {
							slay = 0;
							// daca am ajuns la capatul tuturor liniilor anterioare din matrice
							// sau nu mai am preturi calculate pe linii, opresc alcatuirea
							// vectorului
							if (a < K && b < K && c < K && dp[i - 1][a] == 0 && dp[i - 2][b] == 0
								&& dp[i - 3][c] == 0) {
								break;
							}
							// daca am depasit capatul liniei, ii atribui pretului o valoare mare
							// pentru a nu fi adaugat in vectorul nou de preturi
							if (a > K) {
								price1 = Double.POSITIVE_INFINITY;
							}
							if (b > K) {
								price2 = Double.POSITIVE_INFINITY;
							}
							if (c > K) {
								price3 = Double.POSITIVE_INFINITY;
							}
							// cazul in care adaug pretul curent la valoarea calculata la
							// pasul anterior
							if (a <= K && dp[i - 1][a] != 0) {
								price1 = dp[i - 1][a] + prices[i];
							}
							// cazul in care aplic oferta 1 pt ultimele 2 produse si adaug
							// rezultatul la valoarea calculata cu 2 pasi in urma
							if (b <= K && dp[i - 2][b] != 0) {
								if (prices[i] < prices[i - 1]) {
									price2 = prices[i] * 0.5 + prices[i - 1] + dp[i - 2][b];
								} else {
									price2 = prices[i - 1] * 0.5 + prices[i] + dp[i - 2][b];
								}
							}
							// cazul in care aplic oferta 2 pt ultimele 3 produse si adaug
							// rezultatul la valoarea calculata cu 3 pasi in urma
							double free = Math.min(Math.min(prices[i], prices[i - 1]),
								prices[i - 2]);
							if (c <= K && i > 3 && dp[i - 3][c] != 0) {
								price3 = dp[i - 3][c] + prices[i] + prices[i - 1]
									+ prices[i - 2] - free;
							}
							// caz special pt 3 produse
							if (i == 3) {
								price3 = prices[i] + prices[i - 1] + prices[i - 2] - free;
							}
							// avem 3 preturi si calculez minimul pt a-l adauga in matrice
							dp[i][j] = Math.min(Math.min(price1, price2), price3);
							// daca am adaugat o valoare de pe linia i - 1, cresc indicele
							// variabila slay este folosita pt a arata faptul ca inca
							// mai adaug valori in matrice si ca nu trebuie sa opresc calculul
							if (dp[i][j] == price1 && a <= K) {
								a++;
								slay = 1;
							}
							// daca am adaugat o valoare de pe linia i - 2, cresc indicele
							if (dp[i][j] == price2 && b <= K) {
								b++;
								slay = 1;
							}
							// daca am adaugat o valoare de pe linia i - 3, cresc indicele
							if (dp[i][j] == price3 && c <= K) {
								c++;
								slay = 1;
							}
							// daca am adaugat o valoare care deja se afla in matrice,
							// continui sa caut alt pret pt dp[i][j]
							if (dp[i][j] == ok) {
								if (slay == 0) {
									break;
								}
								continue;
							} else {
								// altfel, actualizez valoarea anterioara si ma mut pe pozitia
								// j + 1
								ok = dp[i][j];
								j++;
							}
						}
					}
				}
				// la final retin al k lea pret
				price = dp[N][K];
				if (price == 0) {
					price = -1;
				}
			}
			PrintWriter pw = new PrintWriter(new File("oferta.out"));
			pw.printf("%.1f\n", price);
			pw.close();

		} catch (IOException e) {
			System.out.println("File not found.");
			e.printStackTrace();
		}
	}

}
