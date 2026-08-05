import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

  static Set<String> commands;

  public static void main(String[] args) throws Exception {
    commands = new HashSet<>();
    commands.addAll(Arrays.asList("echo", "exit", "type"));

    Scanner scanner = new Scanner(System.in);
    while (true) {
      System.out.print("$ ");
      String input = scanner.nextLine();

      if (input.equals("exit")) {
        break;
      } else if (input.startsWith("echo ")) {
        System.out.println(input.substring(input.indexOf(' ') + 1));
      } else if (input.startsWith("type ")) {
        String command = input.substring(input.indexOf(' ') + 1);
        if (isValidCommand(command)) {
          System.out.println(command + " is a shell builtin");
        } else {
          System.out.println(command + ": not found");
        }
      } else if (!isValidCommand(input)) {
        System.out.println(input + ": command not found");
      }
    }
  }

  private static boolean isValidCommand(String command) {
    return commands.contains(command);
  }
}
