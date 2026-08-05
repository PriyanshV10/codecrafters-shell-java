import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
  public static void main(String[] args) throws Exception {
    Scanner scanner = new Scanner(System.in);
    while(true) {
      System.out.print("$ ");
      String input = scanner.next();

      if(input.equals("exit")) {
        break;
      }

      if(!isValidCommand(input)) {
        System.out.println(input + ": command not found");
      }
    }
  }

  private static boolean isValidCommand(String command) {
      return false;
  }
}
