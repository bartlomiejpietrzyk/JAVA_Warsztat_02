package pl.coderslab.menu;

import pl.coderslab.dao.SolutionDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.plain.Solution;

import java.util.Arrays;
import java.util.Scanner;

public class MenuSolution {

    public static void main() {
        MenuMain menuMain = new MenuMain();
        MenuSolution menuSolution = new MenuSolution();
        Scanner scanner = new Scanner(System.in);
        SolutionDao solution = new SolutionDao();
        int choiceMenu = 4;

        while (choiceMenu >= 1 || choiceMenu <= 4) {
            System.out.println("***************************************");
            System.out.println("***********  Menu  Solution: **********");
            System.out.println("***************************************");
            System.out.println("*** 1. Add - przypisz rozwiązanie  ****");
            System.out.println("***          do użytkownika        ****");
            System.out.println("*** 2. View - przegląj rozwiązaia  ****");
            System.out.println("*** 3. Quit - Zakończenie programu ****");
            System.out.println("***************************************");
            int choice = scanner.nextInt();
            if (choice == 1) {
                menuSolution.add();
            } else if (choice == 2) {
                System.out.println(Arrays.toString(solution.findAll()));
                menuSolution.view();
            } else if (choice == 3) {
                menuMain.main();
            } else {
                System.out.println("***************************************");
                System.out.println("******** Podano błędną wartość! *******");
                continue;
            }
        }
    }

    public Solution add() {
        SolutionDao solutionDao = new SolutionDao();
        UserDao userDao = new UserDao();
        Scanner scanner = new Scanner(System.in);
        System.out.println("**********************************");
        System.out.println("****** Przypisz  rozwiązanie: ****");
        System.out.println("********* do użytkownika *********");
        System.out.println("*********** 0 = Wróć *************");
        System.out.println("***** Wpisz ID użytkownika: ******");
        System.out.println("**********************************");
        System.out.println(Arrays.toString(userDao.findAll()));
        int id = scanner.nextInt();
        if (id == 0) {
            MenuSolution.main();
        }

        System.out.println("**********************************");
        System.out.println("*********   Lista zadań:  ********");
        System.out.println("**********************************");
        System.out.println(solutionDao);
        if (id == 0) {
            MenuSolution.main();
        }
        System.out.println("**********************************");
        System.out.println("******* Wpisz ID zadania: ********");
        System.out.println("**********************************");
        int exerciseId = scanner.nextInt();
        Solution solution = new Solution();
        solution.setUserId(id);
        solution.setExerciseId(exerciseId);
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

    public String view() {
        SolutionDao solutionDao = new SolutionDao();
        UserDao userDao = new UserDao();
        Scanner scanner = new Scanner(System.in);
        System.out.println("**********************************");
        System.out.println("**** Przeglądaj  rozwiązanie: ****");
        System.out.println("******* danego użytkownika *******");
        System.out.println("*********** 0 = Wróć *************");
        System.out.println("***** Wpisz ID użytkownika: ******");
        System.out.println("**********************************");
        int id = scanner.nextInt();
        if (id == 0) {
            MenuSolution.main();
        }
        return Arrays.toString(solutionDao.findAllByUserId(id));

    }
}
