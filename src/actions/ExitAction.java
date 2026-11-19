package actions;

import java.util.Scanner;

public class ExitAction extends MenuAction {
    private Scanner scanner;

    public ExitAction(Scanner scanner) {
        super("0: Exit");
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println("Please enter file name to save before exit:");
        String enterSaveFilePathOnExit = scanner.nextLine();
        if (!enterSaveFilePathOnExit.isBlank()) {
            try {
                // we need storage here
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}