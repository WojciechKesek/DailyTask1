package advent.day2.part1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Path path = Path.of("src/advent/day2/part1/data");
        Stream<String> data = readFile(path);
        System.out.println(getValidData(data));

    }

    private static int getValidData(Stream<String> data){
       return data.map(line -> {
            int red = 12;
            int green = 13;
            int blue = 14;
            int num;
            for (int i = 0; i < line.length(); i++) {
                if(line.charAt(i) == ';'){
                    red = 12;
                    green = 13;
                    blue = 14;
                    continue;
                }
                if(line.charAt(i) > 47 && line.charAt(i) < 58){
                    num = line.charAt(i) - 48;
                    if(line.charAt(i+1) > 47 && line.charAt(i+1) < 58){
                        num = (line.charAt(i) - 48)*10 + (line.charAt(i+1) - 48);
                        i++;
                    }
                    switch (line.charAt(i+2)){
                        case 'r' : red -= num; break;
                        case 'g' : green -= num; break;
                        case 'b' : blue -= num; break;
                    }
                }
                if(red < 0 || green < 0 || blue < 0){
                    line = line.replaceFirst("\\d+", "0");
                    break;
                }
            }
            return line;
        })
                .map(line -> new Scanner(line).useDelimiter("\\D+").nextInt())
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
