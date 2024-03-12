import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;

class Result {

  /*
   * Complete the 'minChairs' function below.
   *
   * The function is expected to return an INTEGER_ARRAY.
   * The function accepts STRING_ARRAY simulations as parameter.
   */

  public static List<Integer> minChairs(List<String> simulations) {
    // Write your code
    List<Integer> answer = new ArrayList<Integer>();
    for (int i = 0; i < simulations.size(); i++) {
      int avail = 0;
      int total = 0;
      for (int j = 0; j < simulations.get(i).length(); j++) {
        if (simulations.get(i).charAt(j) == 'C') {
          if (avail > 0) {
            avail--;
          } else total++;
        } else if (simulations.get(i).charAt(j) == 'R') {
          avail++;
        } else if (simulations.get(i).charAt(j) == 'U') {
          if (avail > 0) {
            avail--;
          } else total++;
        } else avail++;
      }
      answer.add(total);
    }
    return answer;
  }
}

public class Solution {

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(
      new InputStreamReader(System.in)
    );
    BufferedWriter bufferedWriter = new BufferedWriter(
      new FileWriter(System.getenv("OUTPUT_PATH"))
    );

    int simulationsCount = Integer.parseInt(bufferedReader.readLine().trim());

    List<String> simulations = IntStream
      .range(0, simulationsCount)
      .mapToObj(i -> {
        try {
          return bufferedReader.readLine();
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
      })
      .collect(toList());

    List<Integer> result = Result.minChairs(simulations);

    bufferedWriter.write(
      result.stream().map(Object::toString).collect(joining("\n")) + "\n"
    );

    bufferedReader.close();
    bufferedWriter.close();
  }
}
