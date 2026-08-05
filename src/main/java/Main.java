import java.util.Scanner;

public class Main {
  public static void main(String[] args) throws Exception {
    Scanner scanner = new Scanner(System.in);
    while (true) {
      System.out.print("$ ");
      String input = scanner.nextLine();

      if (input.equals("exit")) {
        break;
      } else if (input.startsWith("echo ")) {
        System.out.println(input.substring(input.indexOf(' ')));
      } else if (!isValidCommand(input)) {
        System.out.println(input + ": command not found");
      }
    }
  }

  private static boolean isValidCommand(String command) {
    return false;
  }
}
