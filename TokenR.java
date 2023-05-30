import java.util.Scanner;

class TokenR {
    public static void main(String ars[]) throws Throwable {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter Number of processes");
        int np = scan.nextInt();

        System.out.println("Initialize ring:");
        for(int i=0;i<np;i++) {
            System.out.print(" " + i);
        } 

        System.out.println("Enter sender:");
        int sender = scan.nextInt();

        System.out.println("Enter Receiver:");
        int receiver = scan.nextInt();

        scan.nextLine();
        System.out.println("Enter message:");
        String message = scan.nextLine();

        int token = 0;
        boolean message_pass = false;

        for(token=0;t
oken<np;token++) {
            System.out.println("Token at process " + token);

            if(token == sender) {
                System.out.println("Sender Found");
                System.out.println("Message attaching");
                message_pass = true;
            }
            else if(token == receiver) {
                System.out.println("Token arrived at " + receiver + " receiver");
                System.out.println("Message : "+message + "received");  
                message_pass = false;
                break;
            }

            if(message_pass) {
                System.out.println(token + "passing message to " + (token+1)%np);
            }
        }

        scan.close();
    }
}