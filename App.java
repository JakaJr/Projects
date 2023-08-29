import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class App {
    protected static int[] VALID_NUMS = {1, 2, 3, 4, 5, 6, 7, 8, 9};

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input puzzle PATH:");
        String input = sc.nextLine();
        sc.close();
        BufferedReader br = new BufferedReader(new FileReader(input));
        int[] squareValues = new int[81];
            for (int i = 0; i < 9; i++) {
                String[] temp = br.readLine().split(" ");
                for (int j = 0; j < 9; j++) {
                    squareValues[(i*9) + j] = Integer.parseInt(temp[j]);
                }
            }
        br.close();
        
        Puzzle sudokuPuzzle = new Puzzle(squareValues);
        sudokuPuzzle.setName(input);

        boolean p = true;
        while (!sudokuPuzzle.isSolved() && p) {
            p = sudokuPuzzle.findSingletons();
            if (!p) {
                p = sudokuPuzzle.findAloneValues();
            }
        }

        System.out.println(sudokuPuzzle);
        System.out.println(sudokuPuzzle.printAllSquareVerbose());
    }
}
