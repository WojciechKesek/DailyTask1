package advent.day1.part2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Path path = Path.of("src/advent/day1/part2/data");
        Stream<String> data = readFile(path);
        System.out.println(getSumOfNumbersStream(data));
    }

    private static long getSumOfNumbersStream(Stream<String> data){
            return data.map(line -> {
            int maxIndex = -1;
            int minIndex = -1;
            int max = 0;
            int min = Integer.MAX_VALUE;
            String[] numbers = {"one","two","three","four","five","six","seven","eight","nine"};
            String[] intNumbers = {"1","2","3","4","5","6","7","8","9"};
            for (int i = 0; i < numbers.length; i++) {
                if(min > line.indexOf(numbers[i]) && line.contains(numbers[i])){
                    minIndex = i;
                    min = line.indexOf(numbers[i]);
                }
                if(max < line.lastIndexOf(numbers[i])){
                    maxIndex = i;
                    max = line.lastIndexOf(numbers[i]);
                }
            }
            if(minIndex != -1){
                line = line.replaceFirst(numbers[minIndex], intNumbers[minIndex]+numbers[minIndex]);
            }
            if(maxIndex != -1){
                line = line.replaceAll(numbers[maxIndex], numbers[maxIndex]+intNumbers[maxIndex]);
            }
                Scanner scanner = new Scanner(line);
                int firstDigit = scanner.useDelimiter("\\D+").nextInt();
                int lastDigit = firstDigit%10;
                while (firstDigit > 9) {
                    firstDigit /= 10;
                }
                while (scanner.useDelimiter("\\D+").hasNextInt()){
                    lastDigit = scanner.useDelimiter("\\D+").nextInt();
                }
                lastDigit = lastDigit%10;
                return firstDigit*10 + lastDigit;
            })
                    .reduce(0, Integer::sum);
    }
    private static Stream<String> readFile(Path path){
        try {
            return Files.lines(Paths.get(path.toUri()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
