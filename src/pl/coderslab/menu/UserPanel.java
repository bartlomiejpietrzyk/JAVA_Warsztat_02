package pl.coderslab.menu;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import pl.coderslab.dao.ExerciseDao;
import pl.coderslab.dao.SolutionDao;
import pl.coderslab.main.Main;
import pl.coderslab.plain.Solution;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserPanel {
    public void main(int userId) {
        int upperMenu = 3;
        int lowerMenu = 1;
        int properChoose = 0;
        UserPanel userPanel = new UserPanel();
        SolutionDao solutionDao = new SolutionDao();
        MenuText menuText = new MenuText();
        do {
            try {
                System.out.println(menuText.userPanelWelcomeMenu());
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
                        userPanel.add(userId);
                        break;
                    case 2:
                        System.out.println(solutionDao.findAllByUserId(userId));
                        break;
                    case 3:
                        Main.main(null);
                    default:
                        System.err.println(menuText.wrongMenu());
                        continue;
                }
            } catch (MySQLIntegrityConstraintViolationException e) {
                e.printStackTrace();
            } catch (InputMismatchException ime) {
                System.err.println(menuText.wrongValue());
                continue;
            }
        } while (properChoose > lowerMenu || properChoose < upperMenu);
    }


    public Solution add(int userId) throws MySQLIntegrityConstraintViolationException {
        UserPanel userPanel = new UserPanel();
        MenuText menuText = new MenuText();
        ExerciseDao exerciseDao = new ExerciseDao();
        SolutionDao solutionDao = new SolutionDao();
        Scanner scanner = new Scanner(System.in);
        Solution solution = new Solution();
        int exerciseId = 0;
        do {
            System.out.println(Arrays.toString(exerciseDao.findAll()));
            System.out.println(menuText.solutionGetExerciseId());
            exerciseId = scanner.nextInt();
            if (exerciseId == 0) {
                userPanel.main(userId);
            }
            scanner.nextLine();
            if (exerciseDao.exist(exerciseId)) {
                System.out.println(menuText.userPanelGetSolutionDesc());
                String description = scanner.nextLine();
                solution.setId(userId);
                solution.setExerciseId(exerciseId);
                solution.setSolutionDescription(description);
                solutionDao.create(solution);
                int id = solution.getId();
                if (solutionDao.exist(id)) {
                    System.out.println(menuText.userPanelSolutionSucc());
                    return solution;
                } else {
                    System.err.println(menuText.userPanelSolutionFail());
                    return null;
                }
            } else {
                System.err.println(menuText.userPanelExerciseError());
                continue;
            }
        } while (exerciseId == 0);
        return null;
    }
}