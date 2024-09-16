import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Criptat {

	// aici am extras toate literele din parole
	public static char[] extractUniqueLetters(String[] words) {
		Set<Character> uniqueChars = new HashSet<>();
		for (String word : words) {
			for (char c : word.toCharArray()) {
				uniqueChars.add(c);
			}
		}
		int length = uniqueChars.size();
		char[] result = new char[length];
		int i = 0;
		for (char c : uniqueChars) {
			result[i] = c;
			i++;
		}
		return result;
	}

	// functie pentru determinarea nr de aparitii al unei litere in cuvant
	static int count_char_in_word(char c, String word) {
		int nr = 0;
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == c) {
				nr++;
			}
		}
		return nr;
	}


	public static void main(String[] args) {

		try {
			File file = new File("criptat.in");
			Scanner scanner = new Scanner(file);
			int N = scanner.nextInt();
			scanner.nextLine();
			String[] words = new String[N];
			for (int i = 0; i < N; i++) {
				words[i] = scanner.nextLine();
			}
			scanner.close();

			// mai intai extrag toate literele diferite din toate cuvintele
			int maxLength = 0, i;
			char[] uniqueLetters = extractUniqueLetters(words);
			for (char c : uniqueLetters) {
				// sortez cuvintele dupa procentul de aparitie al literei
				// curente si dupa lungimea lor
				Arrays.sort(words, new Comparator<String>() {
					@Override
					public int compare(String o1, String o2) {

						// mai intai am comparat dupa procentul de aparitie
						int percent = Float.compare((float) count_char_in_word(c, o2)
							/ o2.length(), (float) count_char_in_word(c, o1) / o1.length());
						// si am returnat rezultatul, daca proporțiile sunt diferite
						if (percent != 0) {
							return percent;
						}
						// altfel, am comparat dupa lungime
						int length = Integer.compare(o2.length(), o1.length());
						if (length != 0) {
							return length;
						}
						return 0;
					}
				});
				// vreau sa lipesc la p un cuvant nou, doar daca litera curenta
				// se va repeta in p de mai mult de jumatate din lungimea lui p
				i = 1;
				// initial parola este alcatuita din primul cuvant
				String p = words[0];
				/* calculez procentul de aparitie al literei curente in cuvant
				si daca este mai mic de 0.5, atunci, cum am sortat inainte
				cuvintele, inseamna ca nu voi putea alcatui o parola care sa
				respecte conditiile, pentru litera curenta */
				if ((float)count_char_in_word(c, p) / p.length() < 0.5) {
					p = "";
				} else {
					// parcurg toate cuvintele din lista
					while (i < N) {
						/* verific daca procentul de aparitie ar ramane mai mare
						decat 0.5 daca asa adauga noul cuvant la parola si
						daca da, il adaug */
						if ((float)(count_char_in_word(c, p) + count_char_in_word(c, words[i]))
							/ (p.length() + words[i].length()) > 0.5) {
							p = p + words[i];
						} else {
							if (i < N - 1) {
								if ((float)(count_char_in_word(c, p)
									+ count_char_in_word(c, words[i])
									+ count_char_in_word(c, words[i + 1])) / (p.length()
									+ words[i].length() + words[i + 1].length()) > 0.5) {
									p = p + words[i] + words[i + 1];
									i++;
								}
							}
						}
						i++;

					}
				}
				// daca parola obtinuta pt litera curenta este mai lunga decat
				// parola maxima, o actualizez
				if (p.length() > maxLength) {
					maxLength = p.length();
				}
			}
			PrintWriter pw = new PrintWriter(new File("criptat.out"));
			pw.printf("%d\n", maxLength);
			pw.close();

		} catch (IOException e) {
			System.out.println("File not found.");
			e.printStackTrace();
		}
	}

}
