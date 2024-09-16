import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Colorare {
	public static final int MOD = 1000000007;

	// functie pentru calcularea unui numar ridicat la o putere
	private static long pow(long baza, int exp) {
		long result = 1;
		while (exp > 0) {
			if (exp % 2 == 1) {
				result = (result * baza) % MOD;
			}
			baza = (baza * baza) % MOD;
			exp /= 2;
		}
		return result;
	}
	public static void main(String[] args) {
		try {
			File file = new File("colorare.in");
			Scanner scanner = new Scanner(file);
			int K = scanner.nextInt();
			int[] zone = new int[K];
			char[] tip = new char[K];
			long[] dp = new long[K];
			for (int i = 0; i < K; i++) {
				zone[i] = scanner.nextInt();
				tip[i] = scanner.next().charAt(0);
			}
			scanner.close();
			/* daca incepem cu piese orizontale, vom avea pt primele 2 piese
			6 moduri de alegere a culorilor si pt fiecare 2 piese orizontale
			puse dupa aceasta, cate 3 moduri de alegere */
			if (tip[0] == 'H') {
				dp[0] = 6 * pow(3, (zone[0] - 1)) % MOD;
			} else {
				// in schimb, daca incepem cu o piesa verticala, vom avea 3 moduri
				// de alegere a culorilor si pt urmatoarele piese verticale, cate 2
				dp[0] = 3 * pow(2, zone[0] - 1) % MOD;
			}
			for (int i = 1; i < K; i++) {
				// daca plasam piese orizontale
				if (tip[i] == 'H') {
					long power_3 = pow(3, (zone[i] - 1));
					if (tip[i - 1] == 'H') {
						// daca am plasat inainte tot piese orizontale, inseamna
						// ca trebuie doar sa continuam sa inmultim cu 3
						dp[i] = dp[i - 1] * 3 * power_3 % MOD;
					} else {
						/* daca inainte plasasem piese verticale, sunt doar 2 moduri
						de alegere a culorilor pt prima piesa orizontala, urmand
						ca pt a obtine combinatiile celorlalte sa continuam sa
						inmultim cu 3 */
						dp[i] = dp[i - 1] * 2 * power_3 % MOD;
					}
				} else {
					// daca plasam piese verticale
					long power_2 = pow(2, (zone[i] - 1));
					if (tip[i - 1] == 'H') {
						/* daca inainte aveam piese orizontale, prima piesa verticala
						poate avea o singura culoare, urmand sa existe cate 2 moduri
						de colorare pt urmatoarele piese */
						dp[i] = dp[i - 1] * power_2 % MOD;
					} else {
						/* daca inainte aveam o piesa verticala, toate piesele
						urmatoare pot avea cate 2 culori */
						dp[i] = dp[i - 1] * 2 * power_2 % MOD;
					}
				}
			}
			PrintWriter pw = new PrintWriter(new File("colorare.out"));
			pw.printf("%d\n", dp[K - 1]);
			pw.close();
		} catch (IOException e) {
			System.out.println("File not found.");
			e.printStackTrace();
		}
	}
}
