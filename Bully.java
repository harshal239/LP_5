import java.util.Scanner;

class Bully {
    int coordinator;
    boolean[] state;
    int n;

    Bully(int n) {
        this.n = n;
        state = new boolean[n];
        coordinator = n;
        System.out.println("Coordinator is set to: "+coordinator);
    }

    public void up(int id) {
        if(state[id-1]) {
            System.out.println("Process is already up.");
            return;
        }
        else {
            state[id-1] = true;
            System.out.println("Process " + id + " held election.");
            for(int i=id;i<n;i++) {
                System.out.println("Process " + i + " is sending message to +" + (id+1));
            }

            for(int i= id+1;i<n;i++) {
                if(state[i])
                    System.out.println("alive message from " + i + " to "+id);

            }

        }

    }

    public void down(int id) {
        if(!state[id-1])
            System.out.println("Process is already won!");
        else    
            state[id-1] = false;
    }

    public void mess(int id) {
        if(state[id-1]) {
            if(state[n-1])
                System.out.println("OK");
            else {
                System.out.println("Process "+id+" helding election");
                for(int i=id;i<n;i++) {
                    System.out.println("Election message sent from "+ id+ " to " + (id+1));
                }

                for(int i=n;i>=id;i--) {
                    if(state[i]) {
                        System.out.println("Coordinator message send from process " + i + " to all.");
                        return;
                    }

                }

                System.out.println("Process "+id+" wins the election, now it is coordinator");
                this.coordinator = id;
            }
        }
        else {
            System.out.println("Process is dwon!");
        }

    }

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter number of processes:");
        int n = scan.nextInt();

        Bully bully = new Bully(n);

        while(true) {
            System.out.println("\n\n*****MENU*****\n1. Process Up \n2. Process Down \n3. Send a message\n4. Exit\nEnter your choice:");
            int choice=scan.nextInt();
            int id;

            switch(choice) {
                case 1: System.out.println("Enter Id to up the process:");
                        id = scan.nextInt();
                        bully.up(id);
                        break;

                case 2: System.out.println("Enter Id to down the process:");
                        id = scan.nextInt();
                        bully.down(id);
                        break;

                case 3: System.out.println("Enter Id to send the message:");
                        id = scan.nextInt();
                        bully.mess(id);
                        break;
                    
                case 4: 
                default:System.exit(0);
                        break;
            }
        }

    }

}