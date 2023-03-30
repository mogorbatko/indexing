package org.gorbatko.indexing;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class IndexingApplication {
    private static final String REGEX = "^(\\d+|\\d+(,|-)\\d+)*$";
    private static final String PORT = "port";
    private static final String EXIT = "exit";

    public static void main(String[] args) {
        int n = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Команда port - для формирования объекта Port");
        System.out.println("Команда exit - завершение работы программы");
        while (true) {
            String command = scanner.nextLine();
            if (PORT.equals(command)) {
                System.out.println("Укажите количество строк в массиве indexes:");
                String enterString = scanner.nextLine();
                while (enterString.isEmpty() || !enterString.matches("[1-9]\\d*")) {
                    System.out.println("Вводимое значение должно быть числом больше нуля!");
                    enterString = scanner.nextLine();
                }
                n = Integer.parseInt(enterString);
                String[] indexes = new String[n];
                System.out.println("Введите n = " + n + " строк последовательности чисел");
                if (indexes.length == 1) {
                    String string = scanner.nextLine();
                    while (!string.matches(REGEX)) {
                        System.out.println("Вводимая строка должна содержать положительные числа, запятые и дефисы");
                        string = scanner.nextLine();
                    }
                    indexes[0] = string;
                } else {
                    for (int i = 0; i < indexes.length; i++) {
                        String string = scanner.nextLine();
                        while (!string.matches(REGEX)) {
                            System.out.println("Вводимая строка должна содержать положительные числа, запятые и дефисы");
                            string = scanner.nextLine();
                        }
                        indexes[i] = string;
                    }
                }
                Port port = new Port(indexes);
                System.out.println("Элементы массива indexes:");
                Arrays.stream(port.getIndexes()).forEach(System.out::println);
                System.out.println("Преобразование в массив последовательностей чисел:");
                List<List<Integer>> listOfSequences = port.convertIndexes();
                listOfSequences.forEach(System.out::print);
                System.out.println("");
                System.out.println("Всевозможные уникальные упорядоченные группы элементов полученных массивов чисел:");
                List<List<Integer>> listOfCombinations = port.generateCombinations(listOfSequences);
                listOfCombinations.forEach(System.out::print);
                System.out.println("");
            }
            if (EXIT.equals(command)) {
                System.out.println("Завершение работы программы.");
                break;
            }
        }
    }
}
