package pl.coderslab.menu;

import pl.coderslab.dao.ExerciseDao;
import pl.coderslab.plain.Exercise;

import java.util.Arrays;
import java.util.Scanner;

public class MenuExercise {

    public static void main() {
        MenuMain menuMain = new MenuMain();
        MenuExercise menuExercise = new MenuExercise();
        Scanner scanner = new Scanner(System.in);
        ExerciseDao exercise = new ExerciseDao();
        int choiceMenu = 4;

        while (choiceMenu >= 1 || choiceMenu <= 4) {
            System.out.println("**********************************");
            System.out.println("*******   Menu  Exercise:  *******");
            System.out.println("**********************************");
            System.out.println("**** 1. Add - dodanie zadania ****");
            System.out.println("**** 2. Edit - edycja zadania ****");
            System.out.println("**** 3. Delete - kasacja zadania *");
            System.out.println("**** 4. Exit - Main menu         *");
            System.out.println("* 5. Quit - Zakończenie programu *");
            System.out.println("**********************************");

            int choice = scanner.nextInt();
            if (choice == 1) {
                menuExercise.add();
            } else if (choice == 2) {
                System.out.println(Arrays.toString(exercise.findAll()));
                menuExercise.edit();
            } else if (choice == 3) {
                menuExercise.delete();
            } else if (choice == 4) {
                menuMain.main();
            } else if (choice == 5) {
                break;
            } else {
                System.out.println("**********************************");
                System.out.println("***** Podano błędną wartość! *****");

                continue;
            }
        }
    }

    public Exercise add() {
        ExerciseDao exerciseDao = new ExerciseDao();
        Scanner scanner = new Scanner(System.in);
        String title = "";
        String description = "";
        System.out.println("**********************************");
        System.out.println("******* Tworzenie zadania: *******");
        System.out.println("**********************************");
        System.out.println("*********** 0 = Wróć *************");
        System.out.println("****** Wpisz tytuł zadania: ******");
        System.out.println("**********************************");
        title = scanner.nextLine();
        if (title.equals("0")) {
            MenuExercise.main();
        }
        System.out.println("**********************************");
        System.out.println("*****  Wpisz opis zadania:  ******");
        System.out.println("**********************************");
        description = scanner.nextLine();
        if (description.equals("0")) {
            MenuExercise.main();
        }
        Exercise exercise = new Exercise(title, description);
        exerciseDao.create(exercise);
        if (exerciseDao.created(exercise.getId())) {
            System.out.println("**********************************");
            System.out.println("*****   Exercise  create:   ******");
            System.out.println("*******      Success     *********");
            System.out.println("**********************************");
            return exercise;
        } else {
            System.out.println("**********************************");
            System.out.println("*****   Exercise  create:   ******");
            System.out.println("*******      Failed      *********");
            System.out.println("**********************************");
            return null;
        }
    }

    public void edit() {
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);
        Exercise exercise = new Exercise();
        System.out.println("**********************************");
        System.out.println("*******   Edycja zadania   *******");
        System.out.println("*******  Wpisz ID zadania: *******");
        System.out.println("**********************************");
        System.out.println("*********** 0 = Wróć *************");
        System.out.println("**********************************");

        int id = scanner.nextInt();
        if (id == 0) {
            MenuExercise.main();
        }
        ExerciseDao exerciseDao = new ExerciseDao();
        System.out.println(exerciseDao.read(id));
        scanner.nextLine();
        System.out.println("**********************************");
        System.out.println("****** Edytuj tytuł zadania: *****");
        System.out.println("**********************************");
        String title = scanner.nextLine();
        System.out.println("**********************************");
        System.out.println("****** Edytuj opis zadania: ****** ");
        System.out.println("**********************************");
        String description = scanner.nextLine();
        exercise.setId(id);
        exercise.setTitle(title);
        exercise.setDescription(description);
        exerciseDao.update(exercise);
    }

    public void delete() {
        ExerciseDao exerciseDao = new ExerciseDao();
        Scanner scanner = new Scanner(System.in);
        System.out.println("*********************************");
        System.out.println("*******  Usuwanie zadania *******");
        System.out.println("* Podaj ID zadania do usunięcia *");
        System.out.println("*********************************");
        System.out.println("*********** 0 = Wróć *************");
        System.out.println("**********************************");
        while (scanner.hasNext()) {
            int id = scanner.nextInt();
            if (exerciseDao.read(id) != null) {
                System.out.println(exerciseDao.read(id));
                System.out.println("***************************************************************");
                 System.out.printf("********** Czy na pewno chcesz usunąć zadanie o ID: %s ********\n", id);
                System.out.println("********************** 1 = Tak ********************************");
                System.out.println("********************** 2 = Nie ********************************");
                System.out.println("***************************************************************");
                int decision = scanner.nextInt();
                while (scanner.hasNextLine()) {
                    if (decision == 1) {
                        exerciseDao.delete(id);
                        if (exerciseDao.read(id) == null) {
                            System.out.println("**********************************");
                            System.out.println("*****   Exercise  delete:   ******");
                            System.out.println("*******      Success     *********");
                            System.out.println("**********************************");
                            MenuExercise.main();
                        } else {
                            System.out.println("**********************************");
                            System.out.println("*****   Exercise  delete:   ******");
                            System.out.println("*******      Failed      *********");
                            System.out.println("**********************************");
                            MenuExercise.main();
                        }
                    } else if (decision == 2) {
                        MenuExercise.main();
                    } else {
                        System.out.println("**********************************");
                        System.out.println("*******    Wrong Value:   ********");
                        System.out.println("********     Insert      *********");
                        System.out.println("**********************************");
                    }
                }

            } else {
                System.out.println("**********************************");
                System.out.println("*******   Exercise Error:  *******");
                System.out.println("********  No Exercise ID  ********");
                System.out.println("********  Write ID again  ********");
                System.out.println("********  Press 0 to back ********");
                System.out.println("**********************************");
                continue;
            }
            if (id == 0) {
                MenuExercise.main();
            } else {
                continue;
            }
        }
    }
}
