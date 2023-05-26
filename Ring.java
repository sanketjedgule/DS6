import java.util.Scanner;

public class Ring {
    public static void main(String[] args) {
        int temp, i, j;
        char str[] = new char[10];
        Rr proc[] = new Rr[10];
        
        // Object initialization
        for (i = 0; i < proc.length; i++)
            proc[i] = new Rr();
        
        // Scanner used for getting input from console
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number of processes: ");
        int num = in.nextInt();
        
        // Getting input from users
        for (i = 0; i < num; i++) {
            proc[i].index = i;
            System.out.println("Enter the ID of process: ");
            proc[i].id = in.nextInt();
            proc[i].state = "active";
            proc[i].f = 0;
        }

        // Sorting the processes based on ID
        for (i = 0; i < num - 1; i++) {
            for (j = 0; j < num - 1; j++) {
                if (proc[j].id > proc[j + 1].id) {
                    temp = proc[j].id;
                    proc[j].id = proc[j + 1].id;
                    proc[j + 1].id = temp;
                }
            }
        }

        for (i = 0; i < num; i++) {
            System.out.print("[" + i + "] " + proc[i].id + " ");
        }
        
        int init;
        int ch;
        int temp1;
        int temp2;
        int ch1;
        int arr[] = new int[10];
        proc[num - 1].state = "inactive";
        System.out.println("\nProcess " + proc[num - 1].id + " selected as coordinator.");
        
        while (true) {
            System.out.println("\n1. Election\n2. Quit");
            ch = in.nextInt();
            
            for (i = 0; i < num; i++) {
                proc[i].f = 0;
            }
            
            switch (ch) {
                case 1:
                    System.out.println("\nEnter the process number who initiated the election: ");
                    init = in.nextInt();
                    temp2 = init;
                    temp1 = init + 1;
                    i = 0;
                    
                    while (temp2 != temp1) {
                        if ("active".equals(proc[temp1].state) && proc[temp1].f == 0) {
                            System.out.println("\nProcess " + proc[init].id + " sent a message to " + proc[temp1].id);
                            proc[temp1].f = 1;
                            init = temp1;
                            arr[i] = proc[temp1].id;
                            i++;
                        }
                        
                        if (temp1 == num) {
                            temp1 = 0;
                        } else {
                            temp1++;
                        }
                    }
                    
                    System.out.println("\nProcess " + proc[init].id + " sent a message to " + proc[temp1].id);
                    arr[i] = proc[temp1].id;
                    i++;
                    int max = -1;
                    
                    // Finding the maximum ID for coordinator selection
                    for (j = 0; j < i; j++) {
                        if (max < arr[j]) {
                            max = arr[j];
                        }
                    }
                    
                    // Coordinator is found, then printing on the console
                    System.out.println("\nProcess " + max + " selected as coordinator.");
                    
                    for (i = 0; i < num; i++) {
                        if (proc[i].id == max) {
                            proc[i].state = "inactive";
                        }
                    }
                    
                    break;
                case 2:
                    System.out.println("Program terminated.");
                    return;
                default:
                    System.out.println("\nInvalid response.\n");
                    break;
            }
        }
    }
}

class Rr {
    public int index; // To store the index of the process
    public int id; // To store the ID/name of the process
    public int f;
    String state; // Indicates whether the process is in active or inactive state
}
