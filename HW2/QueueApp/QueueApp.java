// Programmer: Nate Sobol
// Title HW2 listInsert


public class QueueApp {

    public static void main(String[] args) {
        MyNameQueue myQueue = new MyNameQueue();

        System.out.println("Welcome to my Queue.\n");
        
        //populating the queue
        myQueue.push("Jack", "Haley");
        myQueue.push("A", "A");
        myQueue.push("John", "Smith");
        myQueue.push("Winston", "Churchill");
        myQueue.push("Nelson", "Mandela");
        myQueue.push("Mary", "Jones");

        System.out.println("before popping:");
        myQueue.printQueue();
        System.out.println("");

        System.out.println("do a pop:");
        myQueue.pop();
        System.out.println("");

        System.out.println("after popping:");
        myQueue.printQueue();
        System.out.println("");

        System.out.println("count the queue:");
        myQueue.countQueue();
        System.out.println("");
        System.out.println("");

        System.out.println("after a listInsert (Walt Disney) :");
        myQueue.listInsert("Walt", "Disney");
        myQueue.printQueue();
        System.out.println("");
		
		// Delete Churchill
		System.out.println("Deleting Winston:");
        myQueue.listDelete("Winston", "Churchill");
        myQueue.printQueue();
        System.out.println("");
		
		// Another Delete Mandela
		System.out.println("Another listDelete Nelson:");
        myQueue.listDelete("Nelson", "Mandela");
        myQueue.printQueue();
        System.out.println("");
		
		// List Insert James Garfield
		System.out.println("after a listInsert (3, A A) :");
        myQueue.listInsert(5, "James", "Garfield");
        myQueue.printQueue();
        System.out.println("");
    }
}

class Entry {

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

class NODE {

    Entry info;
    NODE link;

    public NODE(Entry info) {
        this.info = info;
    }

    public NODE() {
        info = new Entry("\0", "\0");
    }
}

class MyNameQueue {

    NODE top;
    NODE last;

    public MyNameQueue() {
        top = null;

    }

    public void push(String firstName, String lastName) {
        Entry newEntry = new Entry(firstName, lastName);

        NODE newRecord = new NODE();
        newRecord.info = newEntry;
        newRecord.link = null;

        if (top == null) {
            top = last = newRecord;
        } else {
            last.link = newRecord; //why?
            last = newRecord;
        }

    }

    public void printQueue() {
        NODE newTop = top;

        while (newTop != null) {
            System.out.println(newTop.info.toString());
            newTop = newTop.link;

        }

    }

    public NODE pop() {

        if (top == null) {
            System.out.println("the queue is empty.");
            return new NODE(); //return null;
        } else {
            NODE p = top;
            top = top.link;
            System.out.println(p.info.toString());
			//p.link = null;  //is this necessary????
            return p;
        }

    }




   public void countQueue() {
        int i = 0;
        NODE p = top;

        while (p != null) {
            i++;
            p = p.link;
        }

        System.out.printf("Total items in queue: %d", i);
    }

    public void listInsert(String firstName, String lastName) {

        Entry newEntry = new Entry(firstName, lastName);
        NODE last, next;
        next = top;
        last = null; // is this necessary???? Yes, you might insert a new NODE before the top

        while ((newEntry.last.compareTo(next.info.last) > 0) && (next.link != null)) {
            last = next;
            next = next.link;
            System.out.println("here");
        }

        if (newEntry.last.compareTo(next.info.last) == 0) {
            next.info = newEntry; //update
			
			
        } 
		else
		if (newEntry.last.compareTo(next.info.last) < 0) {
            if (last == null) { //if the new entry should be insert before the top
                NODE p = new NODE(newEntry);
                p.link = top;
                top = p;

            } else {
                last.link = new NODE();
                last.link.info = newEntry;
                last.link.link = next;
            }
        } 
else {
            next.link = new NODE();
            next.link.info = newEntry;
			next.link.link = null;
        }

    }
		
		// List Delete Method
		public void listDelete(String firstName, String lastName) {
		
		// Declarations
		// Nodes
		NODE next;
        NODE last;
		
        next = top;
        last = null;
	
		while(next.link != null) {
			
			int current = firstName.compareTo(next.info.first);
			int previous = lastName.compareTo(next.info.last);
			
			if (current == 0 && previous == 0) {
				last.link = next.link;
				last = next;
				next = next.link;
			} 
			// if node is larger
			else if (previous > 0) {
				last = next;
				next = next.link;
			}
			// if node is smaller			
			else {
				break;
			}
		}
	}
// the listinsert method
	public void listInsert(int index, String firstName, String lastName){
		Entry newEntry = new Entry(firstName, lastName);
		NODE p = new NODE(newEntry);
		NODE last;
		NODE next;
		next = top;
		last = null;
		
		while(next.link!=null){
			if(index<=0){
				p.link=last.link;
				last.link = p;
				break;
			}
			last = next;
			next= next.link; index-=1;
			}
	}
}
