import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

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
        System.out.println(typeCommand(remaining));
      } else {
        Optional<Path> executable = findExecutable(command);
        if (executable.isPresent()) {
          runCommand(executable.get(), input);
        } else {
          System.out.println(command + ": command not found");
        }
      }
    }
  }

  private static boolean isShellCommand(String command) {
    return shellCommands.contains(command);
  }

  private static Optional<Path> findExecutable(String command) {
    String[] dirs = System.getenv("PATH").split(":");
    for (String dir : dirs) {
      Path candidate = Paths.get(dir, command);
      if (Files.isExecutable(candidate)) {
        return Optional.of(candidate);
      }
    }

    return Optional.empty();
  }

  private static String typeCommand(String command) {
    if (isShellCommand(command)) {
      return command + " is a shell builtin";
    }

    Optional<Path> path = findExecutable(command);
    return path.map(value -> command + " is " + value).orElseGet(() -> command + ": not found");
  }

  private static void runCommand(Path executable, String input) {
    String[] parts = input.split(" ");
    //    parts[0] = executable.toString();
    ProcessBuilder processBuilder = new ProcessBuilder(parts);
    processBuilder.inheritIO();

    try (Process process = processBuilder.start()) {
      process.waitFor();
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
