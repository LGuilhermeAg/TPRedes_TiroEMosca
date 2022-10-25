import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Insira o número palpite:");
        Scanner scan = new Scanner(System.in);
        int palpite = scan.nextInt();
        // Math.floor(Math.random()*(max-min+1)+min) 
        System.out.println("Voce digitou:"+palpite);
        System.out.println("Insira o número palpite:");
        palpite = scan.nextInt();
        System.out.println("Voce digitou:"+palpite);
    }
}
