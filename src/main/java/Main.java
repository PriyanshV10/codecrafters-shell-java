import java.util.Scanner;

public class Main {
  public static void main(String[] args) throws Exception {
    System.out.print("$ ");
    Scanner scanner = new Scanner(System.in);

    String input = scanner.next();
    if(!isValidCommand(input)) {
      System.out.println(input + ": command not found");
    }
  }

  private static boolean isValidCommand(String command) {
      return false;
  }
}
