import java.util.*;
public class RingBully {
    
    int n, inactive_count;
    int coordinator;
    boolean[] process_state;
    
    public RingBully(int n) {
        this.n = n;
        this.inactive_count = 0;
        this.coordinator = n;

        process_state = new boolean[n]; 
        for(int i=0;i<n;i++) {
            process_state[i] = true;
        }
        System.out.println((this.coordinator) + " is set as initial coordinator.");
    }

    public void deactivate_process(int ID) {
        if(ID > this.n || ID<0) {
            System.out.println("Invalid ID \n Aborting");
            return;
        }

        if(!process_state[ID - 1]) {
            System.out.println("Process already inactive!");
            return;
        }
        process_state[ID - 1] = false;
        System.out.println(ID + " process deactivared");
        inactive_count += 1;

    }
    
    public void view_Ring() {
        if(this.inactive_count == this.n) {
            System.out.println("All processes are inactive. \nAbort");
            return;
        }
        System.out.println("Active Ring Memebers:");
        for(int i=0;i<this.n;i++) {
            if(process_state[i]) {
                System.out.print((i+1) + " ");
            }
        }
    }

    public void ping_coordinator(int ID) {
        if(!process_state[ID - 1]) {
            System.out.println("Process Inacitve \n Aborting");
            return;
        }
        if(ID == coordinator) {
            if(process_state[ID - 1]) {
                System.out.println("Coordinator active");
            }
            else{
                System.out.println("Coordinator inactive\n Initiate election from another processes");
            }
            return;
        }

        System.out.println("Secnding message from porcess " + ID  + " to " + this.coordinator);
        if(!this.process_state[this.coordinator - 1]) {
            System.out.println("Coordinator process not responding. \n COducting election ");
            this.election(ID);
        }
        else {
            System.out.println("Coordinator alive");
        }
    }

    public void election(int ID) {
        if(this.inactive_count == this.n) {
            System.out.println("All processes reac inactive. \n Abortingg election process");
            this.coordinator = -1;
            return;
        }
        ID = ID - 1;

        int current_coordinator = ID;
        int token = (ID + 1) % n;
        System.out.println("Election initiator : " + (ID + 1));

        while(token != ID) {
            System.out.println("TOken at process: " + (token + 1));
            if(this.process_state[token]) {
                if(token > current_coordinator)
                    current_coordinator = token;
            }

            token = (token + 1) % n;
        }

        System.out.println("Elected coordinator : "+(current_coordinator+1));
        this.coordinator = current_coordinator + 1;
    }
    
    public static void main(String args[]) throws Throwable {

            Scanner scan = new Scanner(System.in);
            System.out.println("ENter number of processes: ");
            int n = scan.nextInt();

            RingBully ring = new RingBully(n);


            while(true) {
                System.out.println("\n\n**************MENU*************\n1. Deactivate process\n2. View Ring\n3. Ping coordinator \n4. Election\n5.Exit");
                System.out.print("ENter your choice:");
                int choice = scan.nextInt();
                int id;

                switch(choice) {
                    case 1: System.out.print("Enter process number to deactive:");
                            id = scan.nextInt();
                            ring.deactivate_process(id);
                            break;

                    case 2: ring.view_Ring();
                            break;

                    case 3: System.out.print("Enter process number to ping to coordinator:");
                            id = scan.nextInt();
                            ring.ping_coordinator(id);
                            break;

                    case 4: System.out.print("ENter porcess Id to initiate election:");
                            id = scan.nextInt();
                            ring.election(id);
                            break;

                    case 5:
                    default: System.out.println("Thank You!");
                             System.exit(0);
                              break;
                }
            }

    }
}