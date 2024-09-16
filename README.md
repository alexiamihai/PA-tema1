# TEMA 1 - PA

## PROBLEMA 1 - SERVERE

### Timp de implementare:
	-> 3 ore

Pentru rezolvarea problemei, am folosit o abordare bazata pe cautarea binara. Am determinat
limita inferioara si superioara pentru numarul de unitati de curent pe care il putem folosi
pentru a alimenta serverele. Apoi, am folosit o functie pentru a calcula puterea de calcul
maxima a sistemului pentru un anumit numar de unitati de curent. Aplicand metoda cautarii
binare, am gasit numarul optim de unitati de curent pentru alimentare, care produce puterea
de calcul maxima a sistemului.

### Complexitate:
Complexitatea timp a algoritmului este O(N * log(MAX)), unde N este numarul de servere,
iar MAX este valoarea maxima a pragurilor de alimentare optime. Acest lucru se datoreaza
faptului ca algoritmul implica o cautare binara intr-un interval de valori.

## PROBLEMA 2 - COLORARE

### Timp de implementare:
	-> 3 ore

Pentru rezolvarea problemei, am implementat urmatorii pasi:

### Calculul numarului total de moduri de colorare:
	Am folosit o abordare dinamica pentru a calcula numarul total de moduri de colorare.
	Am folosit un vector DP pentru a retine numarul de moduri de colorare pentru fiecare zona.

### Calculul puterilor:
	Am implementat o functie pentru a calcula rapid puterile necesare in mod eficient.
	Am folosit algoritmul de ridicare la putere in timp logaritmic.

### Determinarea modurilor de colorare pentru fiecare zona:
	Pentru fiecare zona, am calculat numarul de moduri de colorare tinand cont de tipul zonei
	si de modurile de colorare ale zonei anterioare.

### Complexitate:
Algoritmul are o complexitate de timp eficienta de O(K), unde K este numarul total de zone,
datorita utilizarii unei abordari dinamice eficiente.


## PROBLEMA 3 - COMPRESIE

### Timp de implementare:
	-> 2 ore

Pentru rezolvarea problemei, am urmay urmatorii pasi:

Am parcurs simultan cei doi vectori, comparand elementele lor. Daca un element din primul
vector era mai mare decat un element din al doilea vector, am cautat o subsecventa in al
doilea vector astfel incat suma sa devina egala sau mai mare decat elementul din primul vector.
Daca am gasit o subsecventa care indeplineste aceasta conditie, am crescut lungimea rezultatului
si am trecut la urmatorul element din primul vector. Daca nu am gasit o astfel de subsecventa
si am ajuns la finalul celui de-al doilea vector, atunci compresia nu este posibila.

### Complexitate:
Algoritmul are o complexitate de timp eficienta de O(N + M), unde N si M sunt lungimile
celor doi vectori, deoarece parcurgerea vectorilor si operatiile de compresie se realizeaza
in timp liniar.


## PROBLEMA 4 - CRIPTAT

### Timp de implementare:
	-> 2 zile

Pentru rezolvarea problemei, am urmat urmatorii pasi:

### Extragerea literelor unice din cuvinte:
	Am creat o metoda pentru a extrage toate literele unice din cuvintele date. Pentru aceasta,
	am folosit o structura de date Set pentru a retine caracterele unice.
### Sortarea cuvintelor:
	Am sortat cuvintele in functie de procentul de aparitie al literei curente si de lungimea
	lor. Pentru sortare, am folosit clasa Arrays si un Comparator personalizat.
### Alcatuirea parolei:
	Pentru fiecare litera unica, am alcatuit o parola pe baza cuvintelor sortate. Am verificat
	daca adaugarea unui cuvant la parola mentine procentul de aparitie al literei curente mai
	mare de 0.5.
### Actualizarea lungimii maxime a parolei:
	Am actualizat lungimea maxima a parolei criptate daca parola obtinuta pentru o litera
	unica este mai lunga decat lungimea maxima anterioara.

### Complexitate:
Complexitatea totala a algoritmului este dominata de construirea parolei si este in mare
parte O(N^2 log N), deoarece timpul de sortare domina, cu o contributie aditionala de O(S)
pentru extragerea literelor unice, unde S este numarul total de caractere unice.

## PROBLEMA 5 - OFERTA

### Timp de implementare:
	-> 2 ore - 5. a
	-> o zi - 5. b

Am folosit un vector dp pentru a stoca pretul minim pentru achizitionarea primelor i produse.
Pentru cazul de baza, am determinat pretul minim pentru achizitionarea primului si al doilea
produs. Apoi, pentru fiecare produs, am evaluat trei optiuni:

### Optiunea 1:
	Pastrarea pretului gruparii anterioare si adaugarea pretului noului produs.
### Optiunea 2:
	Aplicarea unei oferte care implica ultimele doua produse. Am calculat
	pretul aplicand oferta pentru cele doua produse si adaugand pretul gruparii de acum doi pasi.
### Optiunea 3:
	Aplicarea unei oferte care implica ultimele trei produse. Am determinat pretul
	aplicand oferta pentru cele trei produse si adaugand pretul gruparii de acum trei pasi,
	ajustat cu cel mai mic pret dintre cele trei produse.

Apoi, am ales minimul dintre cele trei optiuni si l-am inregistrat in vectorul dp.

Pentru punctul b, am folosit o matrice dp pentru a stoca primele k preturi minime obtinute
achizitionand i produse. Apoi in functie de preturile obtinute precedent si folosind
o implementare bazata pe interclasare, am stabilit care ar fi cele mai mici k preturi.

### Complexitate:
Complexitatea programului este determinata de numarul de produse si de numarul ofertelor
disponibile. Pentru prima cerinta, unde trebuie sa gasim cel mai mic pret posibil,
complexitatea este in general de ordinul O(N), unde N reprezinta numarul de produse.
Pentru a doua cerinta, unde trebuie sa gasim al K-lea cel mai mic pret unic, complexitatea
poate fi mai mare, fiind de ordinul O(N*K), unde K este numarul specificat in cerinta.