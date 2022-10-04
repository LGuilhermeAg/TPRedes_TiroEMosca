public class Player {
  boolean turn;
  String name;
  int choice;

  public Player(boolean turn, String name, int choice) {
    this.turn = turn;
    this.name = name;
    this.choice = choice;
  }

  public int getChoice() {
    return this.choice;
  }

  public String getName() {
    return this.name;
  }

  public boolean getTurn() {
    return this.turn;
  }

  public void setTurn(boolean turn){
    this.turn=turn;
  }
}