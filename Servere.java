import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Servere {

	public static final double MIN = Double.POSITIVE_INFINITY;

	// functie pentru a determina minimul dintr-un vector
	static int getMin(int[] vector, int n) {
		int i;
		int ok = vector[0];
		for (i = 1; i < n; i++) {
			ok = Math.min(ok, vector[i]);
		}
		return ok;
	}

	// functie pentru a determina maximul dintr-un vector
	static int getMax(int[] vector, int n) {
		int i;
		int ok = vector[0];
		for (i = 1; i < n; i++) {
			ok = Math.max(ok, vector[i]);
		}
		return ok;
	}

	// functie pentru calcularea puterilor individuale
	static double check_power(double mid, int[] limite, int [] puteri, int N) {
		int i;
		double nr, min = MIN;
		// am aplicat formula din enunt pentru a calcula puterile individuale si
		// pt a o gasi pe cea mai mica, pentru un nr de unitati = mid
		for (i = 0; i < N; i++) {
			nr = puteri[i] - Math.abs(mid - limite[i]);
			if (nr < min) {
				min = nr;
			}
		}
		return min;
	}

	public static void main(String[] args) {
		try {
			File file = new File("servere.in");
			Scanner scanner = new Scanner(file);
			int N = scanner.nextInt();
			int[] puteri = new int[N];
			int[] limite = new int[N];
			for (int i = 0; i < N; i++) {
				puteri[i] = scanner.nextInt();
			}
			for (int i = 0; i < N; i++) {
				limite[i] = scanner.nextInt();
			}
			scanner.close();

			// aplicam metoda cautarii binare, intre minimul si maximul limitelor
			// de alimentare
			double left = getMin(limite, N);
			double right = getMax(limite, N);
			double result = Double.NEGATIVE_INFINITY;

			while (left - right < 0.1) {
				double mid = (left + right) / 2;
				// am calculat puterea sistemului pt un nr de unitati = mid
				double power = check_power(mid, limite, puteri, N);
				if (power > result) {
					result = power;
				}
				// am calculat puterea sistemului pt un nr de unitati = mid + 0.1
				// si pt un nr de unitati = mid - 0.1
				double power_right = check_power(mid + 0.1, limite, puteri, N);
				double power_left = check_power(mid - 0.1, limite, puteri, N);
				// am continuat sa caut in intervalul in care am obtinut o putere
				// a sistemului mai mare
				if (power_right > power_left) {
					left = mid + 0.1;
				} else {
					right = mid - 0.1;
				}
			}
			PrintWriter pw = new PrintWriter(new File("servere.out"));
			pw.println(String.format("%.1f", result));
			pw.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
			e.printStackTrace();
		}
	}
}