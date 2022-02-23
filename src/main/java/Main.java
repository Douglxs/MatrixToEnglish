import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private List<String[]> matrixLines;
    private int attributeLine = 1;                      //Line in CSV where the attribute titles are located (as index)
    private String[] attributeList;
    private final String DEFAULT_FILENAME = "matrix.csv";

    public static void main(String[] args) {
        Main main = new Main();
        try {
            main.readFile(main.getMatrixDirFromUserInput());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }
    }

    private String getMatrixDirFromUserInput(){
        Scanner scanner = new Scanner(System.in);
        String dir = "";
        System.out.println("File path: ");
        dir = scanner.nextLine();
        dir = dir.replace("\"", "");
        try{
            new FileReader(dir);
            System.out.println("File found");
        }
        catch(Exception e){
            try{
                new FileReader(DEFAULT_FILENAME);
                dir = DEFAULT_FILENAME;
                System.out.println("File found");
            }
            catch(Exception ex){
                System.out.println("No file found");
            }
        }
        scanner.close();
        return dir;
    }

    private void readFile(String dir) throws IOException, CsvException {
        if(dir.equals("")){
            return;
        }
        CSVReader reader = new CSVReader(new FileReader(dir));
        matrixLines = reader.readAll();
        cleanUp();
        TestCase tc = new TestCase(attributeList);

        matrixLines.forEach(x -> System.out.println(tc.buildSentence(x)+ "\n-----------------------------------------"));
    }

    private void cleanUp() {
        attributeList = matrixLines.get(attributeLine);

        matrixLines.removeIf(x -> !isANumber(x[0]));    //Remove lines if they do not have an ID

        for (int i = 0; i < matrixLines.size(); i++){                   //Cycle through list
            for(int j = 1; j < matrixLines.get(i).length; j++){         //Start at element 1 to skip ID
                if(matrixLines.get(i)[j].equals("") || matrixLines.get(i)[j].equals("FALSE")){      //Check if empty or false
                    matrixLines.get(i)[j] = "";
                }
                else{
                    matrixLines.get(i)[j] = j+"";
                }
            }
        }
        matrixLines.forEach(x -> System.out.println(Arrays.toString(x)));

    }

    private boolean isANumber(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }
}
