import java.util.ArrayList;

public class gridSquare {
    
    private int value = 0;
    private ArrayList<Integer> options;

    public gridSquare(int value) {
        this.value = value;
    }

    public int getValue() {return this.value;}

    public int[] getOptionList() {
        if (this.options == null) {
            return null;
        }
        else {
            int[] toReturn = new int[options.size()];
            for (int i = 0; i < options.size(); i++){
                toReturn[i] = options.get(i);
            }
            return toReturn;
        }
    }

    public String getOptionsStr() {
        if (this.options != null) {
            String toReturn = "[";
            int i;
            for(i = 0; i < options.size() - 1; i++) {
                toReturn += options.get(i) + ", ";
            }
            return toReturn + options.get(i) + "]"; 
        }
        else {
            return "[]";
        }
    }

    public int getOnlyOption() {
        if (this.options != null && (this.options.size() == 1)) {
            return options.get(0);
        }
        else {
            return 0;
        }
    }
    public int getNumOptions() {
        if (options == null) {
            return 0;
        }
        else {
            return options.size();
        }
    }
    
    public void setOptionsWithComplement(int[] listComplement, int[] universe) {
        this.options = new ArrayList<Integer>(universe.length - listComplement.length);
        for (int i = 0; i < universe.length; i++) {
            if (!Puzzle.exists(listComplement, universe[i])) {
                this.options.add(universe[i]);
            }
        }
    }

    public void setNoOptions() {
        this.options = null;
    }

    public void setValue(int value) {
        this.value = value;
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
}
