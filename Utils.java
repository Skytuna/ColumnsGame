import java.util.Random;

public class Utils {
    public static int getRandomNumber(int min, int max) {
        Random random = new Random();
        int value = random.nextInt(max + min) + min;
        return value;
    }
}
