// Programmer: Nate Sobol
// Title HW3

package entrypackage;

import nodepackage.*;

public class Entry {

    String first;
    String last;

    public Entry(String first, String last) {
        this.first = first;
        this.last = last;
    }

   
    public String toString() {
        return first + " " + last;
    }

}

