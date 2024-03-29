package exam;

import java.util.Scanner;

public class BigIntegerSumm {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Введите первое число");
		String s1 = scan.next();
		System.out.println("Введите второе число");
		String s2 = scan.next();
		int k1 = s1.length();
		int k2 = s2.length();
		// заводим два массива для хранения слагаемых
		int[] a1 = new int[100];
		int[] a2 = new int[100];
		// заводим массив для хранения суммы
		int[] a3 = new int[100];
		// в нулевых ячейках массивов будем хранить количество знаков в числе
		a1[0] = k1;
		a2[0] = k2;
		/*
		 * Заносим по одной цифре в каждую ячейку массива. Будем хранить цифры в
		 * обратном порядке, чтобы было проще осущестлять перенос при сложении.
		 */
		for (int i = 1; i <= k1; i++) {
			a1[i] = (int) s1.charAt(k1 - i) - 48;
		}
		for (int i = 1; i <= k2; i++) {
			a2[i] = (int) s2.charAt(k2 - i) - 48;
		}
		/*
		 * Задаем длину суммы равной максимальной из длин двух слагаемых. В
		 * случае необходимости увеличим ее.
		 */
		int max = k1 > k2 ? k1 : k2;
		a3[0] = max;
		/*
		 * Складываем два числа аналогично сложению столбиком. Сложение
		 * происходит с конца(мы храним числа задом наперед)
		 */
		for (int i = 1; i <= max; i++) {
			/*
			 * Переносим в следующую ячейку остаток. Для этого складываем
			 * текущие цифры слагаемых и остаток с предыдущего шага и делим на
			 * 10(основание системы счисления)
			 */
			a3[i + 1] = (a1[i] + a2[i] + a3[i]) / 10;
			// в текущую ячейку записываем остаток от деления на 10, поскольку в
			// ячейке должно быть однозначное число
			a3[i] = (a3[i] + a2[i] + a1[i]) % 10;
		}
		// если на последнем шаге был перенос, то увеличиваем длину суммы
		if (a3[a3[0] + 1] != 0) {
			a3[0]++;
		}
		// распечатываем полученную сумму
		for (int i = a3[0]; i > 0; i--) {
			System.out.print(a3[i]);
		}
	}
}
