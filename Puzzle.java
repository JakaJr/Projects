import java.util.HashMap;

public class Puzzle {

    private String name;
    private HashMap<Integer, gridSquare> locator;

    public Puzzle(int[] valuesToParse) {
        this.locator = new HashMap<Integer, gridSquare>(valuesToParse.length, 1.01f);
        for (int i = 0; i < valuesToParse.length; i++) {
            this.locator.put(i, new gridSquare(valuesToParse[i]));
        }

        for (int i = 0; i < valuesToParse.length; i++) {
            this.updateGridSquare(i);
        }
    }

    public static boolean exists(int[] list, int find) {
        // a helper .exist() function
        // will return false if int find is not in int[] list, will return true if it is
        for (int num : list) {
            if (num == find) {
                return true;
            }
        }
        return false;
    }
    public static int[] insertNum(int[] list, int toAdd) {
        // a helper .insertNum() function, will insert number in order, hopefully
        // assumed that toAdd is not present in the int[] list
        int[] toReturn = list.clone(); 
        for (int i = 0; i < list.length; i++) {
            //working with int[9] so be careful 
            if (toReturn[i] > toAdd) {
                toReturn[i] = toAdd;
                toAdd = list[i];
            }  
            if (toReturn[i] == 0) {
                toReturn[i] = toAdd;
                break;
            }
        }
        return toReturn;

    }
    public void setName(String name) {this.name = name;}
    public gridSquare getSquareAtCoords(int column, int row) {
        return locator.get((row*9) + column);
    }
    public int getValueAtCoords(int column, int row) {
        return locator.get((row*9) + column).getValue();
    }
    public boolean isSolved() {
        // a broken isSolved function, will output true if there is no more 0 values, but will not check correctness
        for (int i = 0; i < locator.size(); i++) {
            if (locator.get(i).getValue() == 0) {
                return false;
            }
        }
        return true;
    }
    
    public boolean findSingletons() {
        for (int i = 0; i < locator.size(); i++) {
            gridSquare temp = locator.get(i);
            if (temp.getNumOptions() == 1) {
                temp.setValue(temp.getOnlyOption());
                temp.setNoOptions();

                int column = i%9;
                int row = i/9;

                for(int j = row*9; j < row*9 + 9; j++){
                    this.updateGridSquare(j);
                }
                for(int j = column; j < 81; j = j+9) {
                    this.updateGridSquare(j);
                }
                for (int j = i - (row%3 *9) - column%3, k = 0; k < 9; k++) {
                    this.updateGridSquare(j);
                    if (k%3 == 2) {
                        j += 7;
                    }
                    else {
                        j++;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public boolean findAloneValues() {
        for (int i = 0; i < locator.size(); i++) {
            int columnOne = i%9;
            int rowOne = i/9;

            // Checks Rows
            int x = 0;
            int[] numValues = new int[9];
            for(int j = rowOne*9; j < rowOne*9 + 9; j++){ 
                if (locator.get(j).getValue() == 0) {
                    int[] temp = locator.get(j).getOptionList();
                    for (int l = 0; l < temp.length; l++) {
                        numValues[temp[l] - 1]++;
                    }
                }
            }
            for (int j = 0; j < numValues.length; j++) {
                if (numValues[j] == 1) {
                    x = j + 1;
                    break;
                }
            }
            if (x != 0) {
                for(int k = rowOne*9; k < rowOne*9 + 9; k++) { 
                    gridSquare temp = locator.get(k);
                    if (temp.getOptionList() != null) {
                        if (exists(temp.getOptionList(), x)) {
                            temp.setValue(x);
                            temp.setNoOptions();
                            int columnTwo = k%9;
                            int rowTwo = k/9;
                            for(int j = rowTwo*9; j < rowTwo*9 + 9; j++){
                                this.updateGridSquare(j);
                            }
                            for(int j = columnTwo; j < 81; j = j+9) {
                                this.updateGridSquare(j);
                            }
                            for (int j = k - (rowTwo%3 *9) - columnTwo%3, l = 0; l < 9; l++) {
                                this.updateGridSquare(j);
                                if (l%3 == 2) {
                                    j += 7;
                                }
                                else {
                                    j++;
                                }
                            }
                            return true;
                        }
                    }
                }
            }

            // Checks Columns 
            x = 0;
            numValues = new int[9];
            for(int j = columnOne; j < 81; j = j+9){ 
                if (locator.get(j).getValue() == 0) {
                    int[] temp = locator.get(j).getOptionList();
                    for (int l = 0; l < temp.length; l++) {
                        numValues[temp[l] - 1]++;
                    }
                }
            }
            for (int j = 0; j < numValues.length; j++) {
                if (numValues[j] == 1) {
                    x = j + 1;
                    break;
                }
            }
            if (x != 0) {
                for(int k = columnOne; k < 81; k = k+9) { 
                    gridSquare temp = locator.get(k);
                    if (temp.getOptionList() != null) {
                        if (exists(temp.getOptionList(), x)) {
                            temp.setValue(x);
                            temp.setNoOptions();
                            int columnTwo = k%9;
                            int rowTwo = k/9;
                            for(int j = rowTwo*9; j < rowTwo*9 + 9; j++){
                                this.updateGridSquare(j);
                            }
                            for(int j = columnTwo; j < 81; j = j+9) {
                                this.updateGridSquare(j);
                            }
                            for (int j = k - (rowTwo%3 *9) - columnTwo%3, l = 0; l < 9; l++) {
                                this.updateGridSquare(j);
                                if (l%3 == 2) {
                                    j += 7;
                                }
                                else {
                                    j++;
                                }
                            }
                            return true;
                        }
                    }
                }
            }
            
            // Checks Squares
            x = 0;
            numValues = new int[9];
            for(int j = i - (rowOne%3 *9) - columnOne%3, k = 0; k < 9; k++){ 
                if (locator.get(j).getValue() == 0) {
                    int[] temp = locator.get(j).getOptionList();
                    for (int l = 0; l < temp.length; l++) {
                        numValues[temp[l] - 1]++;
                    }
                }
            }
            for (int j = 0; j < numValues.length; j++) {
                if (numValues[j] == 1) {
                    x = j + 1;
                    break;
                }
            }
            if (x != 0) {
                for(int k = i - (rowOne%3 *9) - columnOne%3, m = 0; m < 9; m++) { 
                    gridSquare temp = locator.get(k);
                    if (temp.getOptionList() != null) {
                        if (exists(temp.getOptionList(), x)) {
                            temp.setValue(x);
                            temp.setNoOptions();
                            int columnTwo = k%9;
                            int rowTwo = k/9;
                            for(int j = rowTwo*9; j < rowTwo*9 + 9; j++){
                                this.updateGridSquare(j);
                            }
                            for(int j = columnTwo; j < 81; j = j+9) {
                                this.updateGridSquare(j);
                            }
                            for (int j = k - (rowTwo%3 *9) - columnTwo%3, l = 0; l < 9; l++) {
                                this.updateGridSquare(j);
                                if (l%3 == 2) {
                                    j += 7;
                                }
                                else {
                                    j++;
                                }
                            }
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public void updateGridSquare(int column, int row) {
        this.updateGridSquare(row*9 + column);
    }

    public void updateGridSquare(int pos) {
        int[] numsPresent = new int[9];
        if (locator.get(pos).getValue() == 0) {
            int column = pos%9;
            int row = pos/9;
            
            for(int j = row*9; j < row*9 + 9; j++){
                // Iterate through the row, checking nums and adding nums where necessary
                int x = locator.get(j).getValue();
                if (!exists(numsPresent, x) && x != 0) {
                    numsPresent = insertNum(numsPresent, x);
                }
            }
            for(int j = column; j < 81; j = j+9) {
                // Itterate through the column, checking nums/adding nums
                int x = locator.get(j).getValue();
                if (!exists(numsPresent, x) && x != 0) {
                    numsPresent = insertNum(numsPresent, x);
                }
            }
            for (int j = pos - (row%3 *9) - column%3, k = 0; k < 9; k++) {
                //This for loop inits j as the to left corner of the 3x3 grid i is in
                int x = locator.get(j).getValue();
                if (!exists(numsPresent, x) && x != 0) {
                    numsPresent = insertNum(numsPresent, x);
                }
                if (k%3 == 2) {
                    j += 7;
                }
                else {
                    j++;
                }
            }
            locator.get(pos).setOptionsWithComplement(numsPresent, App.VALID_NUMS);
        }
        else {
            locator.get(pos).setNoOptions();
        }
    }

    @Override 
    public String toString() {
        String toReturn = name + "\n";
        for (int i = 0; i < 81; i++) {
            toReturn = toReturn + this.locator.get(i).getValue() + " ";
            if (i%9 == 8) {
                toReturn += "\n";
            }
        }
        return toReturn;
    }
    public String printAllSquareVerbose() {
        String toReturn = "";
        for (int i = 0; i < locator.size(); i++) {
            gridSquare temp = locator.get(i);
            toReturn += "Square (" + i%9 + ", " + i/9 + "): Value = " + temp.getValue() + ", Options = " + temp.getOptionsStr() + "\n";
        }
        return toReturn;
    }
}