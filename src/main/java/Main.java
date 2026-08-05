import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

  static Set<String> shellCommands;

  public static void main(String[] args) throws Exception {
    shellCommands = new HashSet<>();
    shellCommands.addAll(Arrays.asList("echo", "exit", "type"));

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
        if (isShellCommand(remaining)) {
          System.out.println(remaining + " is a shell builtin");
        } else {
          String commandPath = findExecutable(remaining);
          if (commandPath.isEmpty()) {
            System.out.println(remaining + ": not found");
          } else {
            System.out.println(remaining + " is " + commandPath);
          }
        }
      } else if (!isShellCommand(input)) {
        System.out.println(input + ": command not found");
      }
    }
  }

  private static boolean isShellCommand(String command) {
    return shellCommands.contains(command);
  }

  private static String findExecutable(String command) {
    String[] dirs = System.getenv("PATH").split(":");

    for (String dir : dirs) {
      Path candidate = Paths.get(dir, command);
      if (Files.isExecutable(candidate)) {
        return candidate.toString();
      }
    }

    return "";
  }
}
