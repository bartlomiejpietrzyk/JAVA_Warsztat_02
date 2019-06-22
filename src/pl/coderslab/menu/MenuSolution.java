package pl.coderslab.menu;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import pl.coderslab.dao.ExerciseDao;
import pl.coderslab.dao.SolutionDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.plain.Solution;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuSolution {

    public static void main() {
        int upperMenu = 3;
        int lowerMenu = 1;
        int properChoose = 0;
        AdminPanel adminPanel = new AdminPanel();
        MenuText menuText = new MenuText();
        MenuSolution menuSolution = new MenuSolution();
        SolutionDao solutionDao = new SolutionDao();
        UserDao userDao = new UserDao();
        do {
            try {
                System.out.println(menuText.solutionWelcomeMenu());
                Scanner scanner = new Scanner(System.in);
                while (!scanner.hasNextInt()) {
                    scanner.nextLine();
                    System.err.println(menuText.wrongValue());
                    System.out.println(menuText.solutionWelcomeMenu());
                    continue;
                }
                properChoose = scanner.nextInt();
                if (properChoose < lowerMenu || properChoose > upperMenu) {
                    System.err.println(menuText.wrongMenu());
                    continue;
                }
                switch (properChoose) {
                    case 1:
                        try {
                            menuSolution.add();
                        } catch (MySQLIntegrityConstraintViolationException e) {
                            System.err.println(menuText.wrongValue());
                        }
                        break;
                    case 2:
                        //todo
                        System.out.println(Arrays.toString(userDao.findAll()));
                        System.out.println(menuText.solutionView());
                        int userId = scanner.nextInt();
                        if (userId == 0) {
                            MenuSolution.main();
                        } else if (userDao.exist(userId)) {
                            System.out.println(Arrays.toString(solutionDao.findAllByUserId(userId)));
                        }
                        break;
                    case 3:
                        adminPanel.main();
                    default:
                        System.err.println(menuText.wrongMenu());
                        break;
                }
                scanner.nextInt();
            } catch (InputMismatchException ime) {
                System.err.println(menuText.wrongValue());
                continue;
            }

        } while (properChoose > lowerMenu || properChoose < upperMenu);
    }

    public Solution add() throws MySQLIntegrityConstraintViolationException {
        SolutionDao solutionDao = new SolutionDao();
        UserDao userDao = new UserDao();
        Scanner scanner = new Scanner(System.in);
        MenuText menuText = new MenuText();
        ExerciseDao exerciseDao = new ExerciseDao();

        System.out.println(menuText.solutionToUser());
        System.out.println(Arrays.toString(userDao.findAll()));
        System.out.println(menuText.solutionAddToUserId());

        int userId = scanner.nextInt();
        if (userId == 0) {
            MenuSolution.main();
        }
        System.out.println(menuText.solutionExerciseList());
        System.out.println(Arrays.toString(exerciseDao.findAll()));
        System.out.println(menuText.solutionGetExerciseId());

        int exerciseId = scanner.nextInt();
        if (userId == 0) {
            MenuSolution.main();
        }
        System.out.println(menuText.userPanelGetSolutionDesc());
        String description = scanner.nextLine();
        if (description.equals("0")) {
            MenuSolution.main();
        }
        Solution solution = new Solution(userId, exerciseId, description);
        solutionDao.create(solution);
        if (solutionDao.exist(solution.getId())) {
            System.out.println(menuText.solutionCreateSucc());
            return solution;
        } else {
            System.err.println(menuText.solutionCreateFail());
            return null;
        }

    }

    public String view() {
        SolutionDao solutionDao = new SolutionDao();
        Scanner scanner = new Scanner(System.in);
        MenuText menuText = new MenuText();
        System.out.println(menuText.solutionView());
        int id = scanner.nextInt();
        if (id == 0) {
            MenuSolution.main();
        }
        return String.valueOf(solutionDao.findAllByUserId(id));

    }
}
