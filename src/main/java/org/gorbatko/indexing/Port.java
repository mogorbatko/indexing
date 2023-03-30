package org.gorbatko.indexing;

import java.util.ArrayList;
import java.util.List;

public class Port {
    private String[] indexes;

    // Метод, преобразующий массив строк indexes в массив последовательностей чисел
    public List<List<Integer>> convertIndexes() {
        String[] indexes = this.indexes;
        List<List<Integer>> result = new ArrayList<>();
        for (String index : indexes) {
            List<Integer> sequence = new ArrayList<>();
            String[] parts = index.split(",");
            for (String part : parts) {
                if (part.contains("-")) {
                    String[] range = part.split("-");
                    int start = Integer.parseInt(range[0]);
                    int end = Integer.parseInt(range[1]);
                    for (int i = start; i <= end; i++) {
                        sequence.add(i);
                    }
                } else {
                    sequence.add(Integer.parseInt(part));
                }
            }
            result.add(sequence);
        }
        return result;
    }

    // Метод, возвращающий всевозможные уникальные упорядоченные группы элементов полученных массивов чисел
    public List<List<Integer>> generateCombinations(List<List<Integer>> sequences) {
        List<List<Integer>> result = new ArrayList<>();
        generateCombinationsHelper(sequences, 0, new ArrayList<>(), result);
        return result;
    }

    // Вспомогательный рекурсивный метод для генерации комбинаций чисел
    private void generateCombinationsHelper(List<List<Integer>> sequences, int index, List<Integer> current, List<List<Integer>> result) {
        if (index == sequences.size()) {
            result.add(new ArrayList<>(current));
            return;
        }
        List<Integer> sequence = sequences.get(index);
        for (Integer integer : sequence) {
            current.add(integer);
            generateCombinationsHelper(sequences, index + 1, current, result);
            current.remove(current.size() - 1);
        }
    }

    public Port(String[] indexes) {
        this.indexes = indexes;
    }

    public String[] getIndexes() {
        return indexes;
    }

    public void setIndexes(String[] indexes) {
        this.indexes = indexes;
    }
}
