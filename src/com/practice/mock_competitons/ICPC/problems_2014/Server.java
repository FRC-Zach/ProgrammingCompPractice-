package com.practice.mock_competitons.ICPC.problems_2014;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Server {

    public static void main(String[] args) {
        try {
            List<String> strings = Files.readAllLines(new File("input/serverInput.txt").toPath());

            String p1 = null;
            String p2 = null;
            int caseCount = 0;
            for (String string : strings) {
                if (p1 == null){
                    p1 = string;
                }
                else {
                    p2 = string;
                }

               if (p1 != null && p2 != null){
                    String[] split = p1.split(" +");

                    int t = Integer.parseInt(split[1]);

                    int total = 0;
                    List<Integer> collect = Arrays.stream(p2.split(" +")).map(Integer::parseInt).collect(Collectors.toList());
                    int i;
                    for (i = 0; i < collect.size(); i++) {
                        total += collect.get(i);
                        if (total > t){
                            break;
                        }
                    }

                    System.out.println("Case " + (++caseCount) + ": " + i);

                    p1 = null;
                    p2 = null;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
