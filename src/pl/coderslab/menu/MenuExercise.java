package pl.coderslab.menu;

import pl.coderslab.dao.ExerciseDao;
import pl.coderslab.plain.Exercise;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuExercise {

    public static void main() {
        int upperMenu = 3;
        int lowerMenu = 1;
        int properChoose = 0;
        AdminPanel adminPanel = new AdminPanel();
        MenuText menuText = new MenuText();
        MenuExercise menuExercise = new MenuExercise();
        do {
            try {
                System.out.println(menuText.exerciseWelcomeMenu());
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
                        menuExercise.add();
                        break;
                    case 2:
                        menuExercise.edit();
                        break;
                    case 3:
                        menuExercise.delete();
                        break;
                    case 4:
                        adminPanel.main();
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

    public Exercise add() {
        ExerciseDao exerciseDao = new ExerciseDao();
        MenuText menuText = new MenuText();
        Scanner scanner = new Scanner(System.in);
        System.out.println(menuText.exerciseCreateTitle());
        String title = scanner.nextLine();
        if (title.equals("0")) {
            MenuExercise.main();
        }
        System.out.println(menuText.exerciseCreateDesc());
        String description = scanner.nextLine();
        if (description.equals("0")) {
            MenuExercise.main();
        }
        Exercise exercise = new Exercise(title, description);
        exerciseDao.create(exercise);
        if (exerciseDao.exist(exercise.getId())) {
            System.out.println(menuText.exerciseCreateSucc());
            return exercise;
        } else {
            System.err.println(menuText.exerciseCreateFail());
            return null;
        }
    }

    public void edit() {
        Exercise exercise = new Exercise();
        ExerciseDao exerciseDao = new ExerciseDao();
        MenuText menuText = new MenuText();
        Scanner scanner = new Scanner(System.in);
        int id = 0;
        do {
            System.out.println(Arrays.toString(exerciseDao.findAll()));
            System.out.println(menuText.exerciseEdit());
            while (!scanner.hasNextInt()) {
                scanner.nextLine();
                System.err.println(menuText.wrongValue());
                System.out.println(menuText.exerciseEdit());

            }
            id = scanner.nextInt();
            if (id == 0) {
                MenuExercise.main();
            } else if (!exerciseDao.exist(id)) {
                System.err.println(menuText.exerciseNoIdError());
                break;
            }
            scanner.nextLine();
            System.out.println(menuText.exerciseEditTitle());
            String title = scanner.nextLine();
            System.out.println(menuText.exerciseEditDesc());
            String description = scanner.nextLine();
            exercise.setId(id);
            exercise.setTitle(title);
            exercise.setDescription(description);
            exerciseDao.update(exercise);
        } while (!scanner.hasNextLine());
    }

    public void delete() {
        ExerciseDao exerciseDao = new ExerciseDao();
        Scanner scanner = new Scanner(System.in);
        MenuText menuText = new MenuText();
        System.out.println(Arrays.toString(exerciseDao.findAll()));
        System.out.println(menuText.exerciseDeleteId());
        while (scanner.hasNext()) {
            int id = scanner.nextInt();
            if (exerciseDao.read(id) != null) {
                System.out.println(exerciseDao.read(id));
                System.err.println(menuText.exerciseDeleteConfirm());
                int decision = scanner.nextInt();
                while (scanner.hasNextLine()) {
                    if (decision == 1) {
                        exerciseDao.delete(id);
                        if (exerciseDao.read(id) == null) {
                            System.err.println(menuText.exerciseDeleteConfirm());
                            MenuExercise.main();
                        } else {
                            System.err.println(menuText.exerciseDeleteFail());
                            MenuExercise.main();
                        }
                    } else if (decision == 2) {
                        MenuExercise.main();
                    } else {
                        System.err.println(menuText.wrongValue());
                    }
                }

            } else {
                System.err.println(menuText.exerciseNoIdError());
                break;
            }
            if (id == 0) {
                MenuExercise.main();

            }
        }
    }
}

