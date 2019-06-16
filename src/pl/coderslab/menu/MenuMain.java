package pl.coderslab.menu;

import java.util.Scanner;

public class MenuMain {
    public void main() {

        Scanner scanner = new Scanner(System.in);
        MenuUser menuUser = new MenuUser();
        MenuExercise menuExercise = new MenuExercise();
        MenuUserGroup menuUserGroup = new MenuUserGroup();
        MenuSolution menuSolution = new MenuSolution();
        int choiceMenu = 5;

        while (choiceMenu >= 1 || choiceMenu <= 5) {
        System.out.println("***************************************");
        System.out.println("*************  Main Menu: *************");
        System.out.println("***************************************");
        System.out.println("*** 1. Zarządzanie użytkownikami   ****");
        System.out.println("*** 2. Zarządzanie zadaniami       ****");
        System.out.println("*** 3. Zarządzanie grupami         ****");
        System.out.println("*** 4. Przypisywanie zadań         ****");
        System.out.println("*** 5. Quit - Zakończenie programu ****");
        System.out.println("***************************************");
            int choice = scanner.nextInt();

            if (choice == 1) {
                menuUser.main();
            } else if (choice == 2) {
                menuExercise.main();
            } else if (choice == 3) {
                menuUserGroup.main();
            } else if (choice == 4) {
                menuSolution.main();
            } else if (choice == 5) {
                break;
            } else {
                System.out.println("***************************************");
                System.out.println("******** Podano błędną wartość! *******");
                continue;
            }
        }
    }
}