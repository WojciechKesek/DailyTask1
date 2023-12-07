package advent.day2.part2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Path path = Path.of("src/advent/day2/part2/data");
        Stream<String> data = readFile(path);
        System.out.println(getMultiplicationData(data));

    }

    private static int getMultiplicationData(Stream<String> data){
       return data.map(line -> {
            int red = 0;
            int green = 0;
            int blue = 0;
            int num;
            for (int i = 0; i < line.length(); i++) {
                if(line.charAt(i) > 47 && line.charAt(i) < 58){
                    num = line.charAt(i) - 48;
                    if(line.charAt(i+1) > 47 && line.charAt(i+1) < 58){
                        num = (line.charAt(i) - 48)*10 + (line.charAt(i+1) - 48);
                        i++;
                    }
                    switch (line.charAt(i+2)){
                        case 'r' : red = Math.max(num, red); break;
                        case 'g' : green = Math.max(num, green); break;
                        case 'b' : blue = Math.max(num, blue); break;
                    }
                }
            }
                   System.out.println(red + " " + green + " " + blue);
                   System.out.println(red*green*blue);
            return red * green * blue;
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
