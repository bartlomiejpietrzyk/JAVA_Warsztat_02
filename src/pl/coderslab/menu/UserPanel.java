package pl.coderslab.menu;

import pl.coderslab.dao.ExerciseDao;
import pl.coderslab.dao.SolutionDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.plain.Solution;

import java.util.Arrays;
import java.util.Scanner;

public class UserPanel {
    public void main(int id) {
        System.out.println("Add - dodaj rozwiazanie");
        System.out.println("View - przeglądaj swoje rozwiązania");
        UserPanel userPanel = new UserPanel();
        MenuMain menuMain = new MenuMain();
        MenuSolution menuSolution = new MenuSolution();
        Scanner scanner = new Scanner(System.in);
        SolutionDao solution = new SolutionDao();
        int choiceMenu = 3;

        while (choiceMenu >= 1 || choiceMenu <= 3) {
            System.out.println("***************************************");
            System.out.println("***********  User  Solution: **********");
            System.out.println("***************************************");
            System.out.println("*** 1. Add  - dodaj rozwiązanie  ******");
            System.out.println("************  do zadania         ******");
            System.out.println("*** 2. View - przegląj swoje    *******");
            System.out.println("************* rozwiązania       *******");
            System.out.println("*** 3. Quit - Main menu         *******");
            System.out.println("***************************************");
            int choice = scanner.nextInt();
            if (choice == 1) {
                userPanel.add(id);
            } else if (choice == 2) {
                System.out.println(Arrays.toString(solution.findAll()));
                userPanel.view(id);
            } else if (choice == 3) {
                menuMain.main();
            } else {
                System.out.println("***************************************");
                System.out.println("******** Podano błędną wartość! *******");
                continue;
            }
        }
    }

    public Solution add(int id) {
        SolutionDao solutionDao = new SolutionDao();
        UserDao userDao = new UserDao();
        ExerciseDao exerciseDao = new ExerciseDao();
        Scanner scanner = new Scanner(System.in);
        System.out.println("**********************************");
        System.out.println("****** Dodaj  rozwiązanie: *******");
        System.out.println("*********   do zadania   *********");
        System.out.println("*********** 0 = Wróć *************");
        System.out.println("******** Wpisz ID zadania: *******");
        System.out.println("**********************************");
        System.out.println("*********   Lista zadań:  ********");
        System.out.println("**********************************");
        System.out.println(Arrays.toString(exerciseDao.findAll()));
        int exerciseId = scanner.nextInt();
        if (exerciseId == 0) {
            MenuSolution.main();
        }
        System.out.println("**********************************");
        System.out.println("*** Wpisz rozwiązanie zadania: ***");
        System.out.println("**********************************");
        String description = scanner.nextLine();
        Solution solution = new Solution();
        solution.setUserId(id);
        solution.setExerciseId(exerciseId);
        solution.setSolutionDescription(description);
        solutionDao.create(solution);
        if (solutionDao.created(solution.getId())) {
            System.out.println("**********************************");
            System.out.println("*****   Solution  create:   ******");
            System.out.println("*******      Success     *********");
            System.out.println("**********************************");
            return solution;
        } else {
            System.out.println("**********************************");
            System.out.println("*****   Solution  create:   ******");
            System.out.println("*******      Failed      *********");
            System.out.println("**********************************");
            return null;
        }
    }

    public String view(int id) {
        SolutionDao solutionDao = new SolutionDao();
        UserDao userDao = new UserDao();
        Scanner scanner = new Scanner(System.in);
        System.out.println("**********************************");
        System.out.println("** Przeglądaj swoje rozwiązania **");
        System.out.println("************ do zadań ************");
        System.out.println("*********** 0 = Wróć *************");
        System.out.println("**********************************");
        solutionDao.findAllByUserId(id);
        if (id == 0) {
            MenuSolution.main();
        }
        return Arrays.toString(solutionDao.findAllByUserId(id));

    }
}
