// Programmer: Nate Sobol
// Title HW3

package nodepackage;

import entrypackage.*;

public class NODE {

    public Entry info;
    public NODE link;

    public NODE(Entry info) {
        this.info = info;
    }

    public NODE() {
        info = new Entry("\0", "\0");
    }

}
