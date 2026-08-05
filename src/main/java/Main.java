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

      int firstSpace = input.indexOf(" ");
      String command = firstSpace == -1 ? input : input.substring(0, firstSpace);
      String remaining = firstSpace == -1 ? "" : input.substring(firstSpace + 1);

      if (command.equals("exit")) {
        break;
      } else if (command.equals("echo")) {
        System.out.println(remaining);
      } else if (command.equals("type")) {
        if (isValidCommand(remaining)) {
          System.out.println(remaining + " is a shell builtin");
        } else {
          System.out.println(remaining + ": not found");
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
