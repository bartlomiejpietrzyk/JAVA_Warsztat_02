package pl.coderslab.menu;

import pl.coderslab.main.Main;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminPanel {
    public void main() {
        int upperMenu = 5;
        int lowerMenu = 1;
        int properChoose = 0;
        MenuText menuText = new MenuText();
        MenuUser menuUser = new MenuUser();
        MenuExercise menuExercise = new MenuExercise();
        MenuUserGroup menuUserGroup = new MenuUserGroup();
        MenuSolution menuSolution = new MenuSolution();
        do {
            try {
                System.out.println(menuText.adminWelcomeMenu());
                Scanner scanner = new Scanner(System.in);
                while (!scanner.hasNextInt()) {
                    scanner.nextLine();
                    System.err.println(menuText.wrongValue());
                    System.out.println(menuText.mainWelcomeMenu());
                    continue;
                }
                properChoose = scanner.nextInt();
                if (properChoose < lowerMenu || properChoose > upperMenu) {
                    System.err.println(menuText.wrongMenu());
                    continue;
                }
                switch (properChoose) {
                    case 1:
                        menuUser.main();
                        break;
                    case 2:
                        menuExercise.main();
                        break;
                    case 3:
                        menuUserGroup.main();
                        break;
                    case 4:
                        menuSolution.main();
                        break;
                    case 5:
                        Main.main(null);
                        break;
                    default:
                        System.err.println(menuText.wrongMenu());
                        break;
                }
            } catch (InputMismatchException ime) {
                System.err.println(menuText.wrongValue());
                continue;
            }
        } while (properChoose > lowerMenu || properChoose < upperMenu);
    }
}