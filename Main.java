import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    boolean won = false;
    Scanner Read = new Scanner(System.in); // Create a Scanner object
    System.out.println("Insira o nome do primeiro participante:");
    String name = Read.nextLine(); // Read user input
    System.out.println("Insira o número de " + name + ": ");
    int choice = Read.nextInt(); // Read user input
    Player p1 = new Player(true, name, choice);
    /*
     * 5System.out.print("\033[H\033[2J");
     * System.out.flush();
     */
    System.out.println("Insira o nome do segundo participante:");
    Read.next();
    name = Read.nextLine(); // Read user input
    System.out.println("Insira o número de " + name + ": ");
    choice = Read.nextInt(); // Read user input
    Player p2 = new Player(false, name, choice);
    System.out.print("\033[H\033[2J");
    System.out.flush();

    System.out.println(p1.getName() + " versus " + p2.getName());
    if (p1.getTurn()) {
      System.out.println("vez de " + p1.getName());
    } else {
      System.out.println("vez de " + p1.getName());
    }

  }
}