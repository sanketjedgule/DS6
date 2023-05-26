import java.util.Scanner;

public class Bully {
    static boolean[] state = new boolean[5];
    int coordinator;

    public static void up(int up) {
        if (state[up - 1]) {
            System.out.println("Process " + up + " is already up.");
        } else {
            int i;
            Bully.state[up - 1] = true;
            System.out.println("Process " + up + " initiated an election.");
            for (i = up; i < 5; ++i) {
                System.out.println("Election message sent from process " + up + " to process " + (i + 1));
            }
            for (i = up + 1; i <= 5; ++i) {
                if (!state[i - 1])
                    continue;
                System.out.println("Alive message sent from process " + i + " to process " + up);
                break;
            }
        }
    }

    public static void down(int down) {
        if (!state[down - 1]) {
            System.out.println("Process " + down + " is already down.");
        } else {
            Bully.state[down - 1] = false;
        }
    }

    public static void mess(int mess) {
        if (state[mess - 1]) {
            if (state[4]) {
                System.out.println("OK");
            } else if (!state[4]) {
                int i;
                System.out.println("Process " + mess + " initiated an election.");
                for (i = mess; i < 5; ++i) {
                    System.out.println("Election message sent from process " + mess + " to process " + (i + 1));
                }
                for (i = 5; i >= mess; --i) {
                    if (!state[i - 1])
                        continue;
                    System.out.println("Coordinator message sent from process " + i + " to all");
                    break;
                }
            }
        } else {
            System.out.println("Process " + mess + " is down.");
        }
    }

    public static void main(String[] args) {
        int choice;
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 5; ++i) {
            Bully.state[i] = true;
        }
        System.out.println("5 active processes: P1, P2, P3, P4, P5");
        System.out.println("Process 5 is the coordinator.");

        do {
            System.out.println("..........");
            System.out.println("1. Bring up a process.");
            System.out.println("2. Bring down a process.");
            System.out.println("3. Send a message.");
            System.out.println("4. Exit");
            choice = sc.nextInt();
            switch (choice) {
                case 1: {
                    System.out.println("Bring up a process.");
                    int up = sc.nextInt();
                    if (up == 5) {
                        System.out.println("Process 5 is the coordinator.");
                        Bully.state[4] = true;
                        break;
                    }
                    Bully.up(up);
                    break;
                }
                case 2: {
                    System.out.println("Bring down a process.");
                    int down = sc.nextInt();
                    Bully.down(down);
                    break;
                }
                case 3: {
                    System.out.println("Which process will send a message?");
                    int mess = sc.nextInt();
                    Bully.mess(mess);
                    break;
                }
            }
        } while (choice != 4);
        sc.close();
    }
}
