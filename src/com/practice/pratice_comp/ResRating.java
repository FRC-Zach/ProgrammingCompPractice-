package com.practice.pratice_comp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ResRating {

    public static void rec(int score, int count, int[] value, Set<List<Integer>> set){
        IntStream.rangeClosed(0, score).forEach(value1 -> {
            value[count - 1] = value1;
            if (count - 1 <= 0) {
                set.add(IntStream.of(value).boxed().collect(Collectors.toList()));
            }
            else {
                rec(score, count - 1, value, set);
            }
        });
    }

    public static void calc(int criticsCount, int[] ratings){
        Set<List<Integer>> set = new HashSet<>();

        int score = IntStream.of(ratings).sum();
        rec(score, criticsCount, new int[criticsCount], set);
        Set<List<Integer>> collect = set.parallelStream()
                .filter(integers -> {
                    int sum = integers.stream().mapToInt(Integer::intValue).sum();
                    if (sum == score){
                        if (integers.equals(IntStream.of(ratings).boxed().collect(Collectors.toList()))){
                            return false;
                        }
                        for (int i = 0; i < criticsCount; i++) {
                            if (integers.get(i) > ratings[i]) return true;
                        }
                    }
                    return sum < score;
                })
                .collect(Collectors.toSet());
        System.out.println(collect.size());
    }

    public static void main(String[] args) {
        calc(2, new int[]{4, 3});
    }

}