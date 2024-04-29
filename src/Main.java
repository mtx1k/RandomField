import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Field field = new Field();
        for (int i = 0; i < field.getField().length; i++) {
            System.out.println(Arrays.toString(field.getField()[i]));
        }
    }
}