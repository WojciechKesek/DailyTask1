package advent.day1.part1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Path path = Path.of("src/advent/day1/part1/data");
        Stream<String> data = readFile(path);
        System.out.println(getSumOfNumbersStream(data));
    }

    private static long getSumOfNumbersStream(Stream<String> data){
            return data.map(line -> {
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
