import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Compresie {
	public static void main(String[] args) {
		try {
			File file = new File("compresie.in");
			Scanner scanner = new Scanner(file);
			int n = scanner.nextInt();
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = scanner.nextInt();
			}
			int m = scanner.nextInt();
			int[] b = new int[m];
			for (int i = 0; i < m; i++) {
				b[i] = scanner.nextInt();
			}
			scanner.close();

			int result = 0, i = 0, j = 0, k;
			// am parcurs cei doi vectori simultan si am comparat elementele lor
			while (i < n && j < m) {
				// daca elementul din sirul a este mai mare, caut o subsecventa in sirul b
				if (a[i] > b[j]) {
					// k va prelua initial valoarea elementului curent din b
					k = b[j++];
					// cat timp elementul din primul vector continua sa fie mai mare decat k,
					// adun la k elemente din b, realizand o compresie
					while (a[i] > k && j < m) {
						k += b[j++];
					}
					// daca am ajuns la 2 elemente egale, pot creste lungimea sirului rezultat
					// si pot trece la urmatorul element din a
					if (a[i] == k) {
						result ++;
						i++;
					} else {
						// daca in schimb am ajuns la finalul sirului b si totusi nu am obtinut
						// un element mai mare sau egal decat a, inseamna ca nu pot realiza
						// compresia
						if (j == m && k < a[i]) {
							result = -1;
							break;
						} else {
							// altfel actualizez k la valoarea elementului curent din b pentru
							// a continua la urmatorul pas din while compresia pt vectorul a
							b[--j] = k;
						}
					}
				} else if (a[i] == b[j]) {
					// daca cele 2 elemente sunt egale, atunci inseamna ca pot creste
					// lungimea sirului obtinut si pot trece peste elementele luate in calcul
					result ++;
					i++;
					j++;
				} else if (a[i] < b[j]) {
					// daca elementul din sirul b este mai mare, caut o subsecventa in sirul a
					// si urmez pasii descrisi la primul caz
					k = a[i++];
					while (b[j] > k && i < n) {
						k += a[i ++];
					}
					if (b[j] == k) {
						result ++;
						j++;
					} else {
						if (i == n && k < b[j]) {
							result = -1;
							break;
						} else {
							a[--i] = k;
						}
					}
				}
			}
			// daca nu am ajuns la capatul unuia dintre vectori, inseamna ca nu
			// s-a realizat compresia
			if (i != n || j != m) {
				result = -1;
			}
			PrintWriter pw = new PrintWriter(new File("compresie.out"));
			pw.printf("%d\n", result);
			pw.close();

		} catch (IOException e) {
			System.out.println("File not found.");
			e.printStackTrace();
		}

	}

}
