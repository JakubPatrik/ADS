import java.util.*;

class QueueMergeSort {

    public static LibraryQueue<Lawyer> sortingSomeLawyer(LibraryQueue<Lawyer> queue) {
        LibraryQueue<Lawyer> q = copy(queue);
        return sort(q);
    }

    private static LibraryQueue<Lawyer> sort(LibraryQueue<Lawyer> q) {
        if (q == null) return null;
        if (q.size() <= 1) return copy(q);
        
        int size = q.size();

        // copy the left half
        LibraryQueue<Lawyer> left = new LibraryQueue<>();
        while (q.size() > size / 2) left.enqueue(q.dequeue());

        // copy the right half
        LibraryQueue<Lawyer> right = new LibraryQueue<>();
        while (!q.isEmpty()) right.enqueue(q.dequeue());

        // merge the sorted halves
        return merge(sort(left), sort(right));
    } 

    private static LibraryQueue<Lawyer> merge(LibraryQueue<Lawyer> a, LibraryQueue<Lawyer> b) {
        if (a == null && b == null) return null;
        if (a == null) return copy(b);
        if (b == null) return copy(a);

        LibraryQueue<Lawyer> res = new LibraryQueue<>();
        while (!a.isEmpty() && !b.isEmpty()) {
            Lawyer la = (Lawyer) a.front();
            Lawyer lb = (Lawyer) b.front();
            if (la.getHourlyWage() > lb.getHourlyWage()) {
                res.enqueue(a.dequeue());
            } else if (la.getHourlyWage() < lb.getHourlyWage()) {
                res.enqueue(b.dequeue());
            } else {
                if (la.getTotalIncome() > lb.getTotalIncome()) {
                    res.enqueue(a.dequeue());
                } else {
                    res.enqueue(b.dequeue());
                } 
            }
        }

        while (!a.isEmpty()) res.enqueue(a.dequeue());
        while (!b.isEmpty()) res.enqueue(b.dequeue());

        return res;
    }


    private static LibraryQueue<Lawyer> copy(LibraryQueue<Lawyer> q) {
        LibraryQueue<Lawyer> copy = new LibraryQueue<>();
        while(copy.size() != q.size()) {
            Lawyer obj = q.dequeue();
            q.enqueue(obj);
            copy.enqueue(obj);
        }
        return copy;
    }
}

// UTIL CLASSES
class Lawyer {

    private int badgeNumber;

    private int numberOfTrials;

    private double hourlyWage;

    private double totalIncome;

    private int numberOfObjections;

    public Lawyer(
            int badgeNumber,
            int numberOfTrials,
            double hourlyWage,
            double totalIncome,
            int numberOfObjections) {
        this.badgeNumber = badgeNumber;
        this.numberOfTrials = numberOfTrials;
        this.hourlyWage = hourlyWage;
        this.totalIncome = totalIncome;
        this.numberOfObjections = numberOfObjections;
    }

    public int getBadgeNumber() {
        return badgeNumber;
    }

    public int getNumberOfTrials() {
        return numberOfTrials;
    }

    public double getHourlyWage() {
        return hourlyWage;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public int getNumberOfObjections() {
        return numberOfObjections;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lawyer)) return false;
        Lawyer className = (Lawyer) o;
        return badgeNumber == className.badgeNumber
                && numberOfTrials == className.numberOfTrials
                && Double.compare(className.hourlyWage, hourlyWage) == 0
                && Double.compare(className.totalIncome, totalIncome) == 0
                && numberOfObjections == className.numberOfObjections;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                badgeNumber, numberOfTrials, hourlyWage, totalIncome, numberOfObjections);
    }

    @Override
    public String toString() {
        return "Lawyer{"
                + "badgeNumber="
                + badgeNumber
                + ", numberOfTrials="
                + numberOfTrials
                + ", hourlyWage="
                + hourlyWage
                + ", totalIncome="
                + totalIncome
                + ", numberOfObjections="
                + numberOfObjections
                + '}';
    }
}

/**
 * Implements the Queue ADT from the book.
 */
class LibraryQueue<T> {

    private LinkedList<T> q;

    public LibraryQueue() {
        this.q = new LinkedList<>();
    }

    public void enqueue(T e) {
        q.add(e);
    }

    public T dequeue() {
        return q.poll();
    }

    public int size() {
        return q.size();
    }

    public boolean isEmpty() {
        return q.isEmpty();
    }

    public T front() {
        return q.peek();
    }
}
