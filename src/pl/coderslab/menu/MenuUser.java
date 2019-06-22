package pl.coderslab.menu;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import pl.coderslab.dao.UserDao;
import pl.coderslab.dao.UserGroupDao;
import pl.coderslab.plain.User;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuUser {

    public static void main() {
        int upperMenu = 4;
        int lowerMenu = 1;
        int properChoose = 0;
        AdminPanel adminPanel = new AdminPanel();
        MenuText menuText = new MenuText();
        MenuUser menuUser = new MenuUser();
        UserDao user = new UserDao();
        do {
            try {
                System.out.println(menuText.userWelcomeMenu());
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
                        menuUser.add();
                        break;
                    case 2:
                        System.out.println(Arrays.toString(user.findAll()));
                        menuUser.edit();
                        break;
                    case 3:
                        System.out.println(Arrays.toString(user.findAll()));
                        menuUser.delete();
                        break;
                    case 4:
                        adminPanel.main();
                        ;
                    default:
                        System.err.println(menuText.wrongMenu());
                        break;
                }
            } catch (InputMismatchException | MySQLIntegrityConstraintViolationException ime) {
                System.err.println(menuText.wrongValue());
                continue;
            }
        } while (properChoose > lowerMenu || properChoose < upperMenu);
    }

    public User add() throws MySQLIntegrityConstraintViolationException {
        UserDao userDao = new UserDao();
        UserGroupDao userGroupDao = new UserGroupDao();
        Scanner scanner = new Scanner(System.in);
        MenuText menuText = new MenuText();
        int userGroupId = 0;
        System.out.println(menuText.userCreateName());
        String name = scanner.nextLine();
        if (name.equals("0")) {
            MenuUser.main();
        }
        System.out.println(menuText.userCreateMail());

        String email = scanner.nextLine();
        if (email.equals("0")) {
            MenuUser.main();
        }

        System.out.println(menuText.userCreatePass());
        String password = scanner.nextLine();
        if (password.equals("0")) {
            MenuUser.main();
        }
        //todo
        do {
            System.out.println(menuText.userCreateGroup());
            userGroupId = scanner.nextInt();
            if (userGroupId == 0) {
                MenuUser.main();
            } else if (!userGroupDao.exist(userGroupId)) {
                System.out.println(menuText.userGroupIdError());
            }
        } while (!scanner.hasNextInt());

        User user = new User(name, email, password, userGroupId);
        userDao.create(user);
        if (userDao.exist(user.getId())) {
            System.out.println(menuText.userCreateSucc());
            return user;
        } else {
            System.out.println(menuText.userCreateFail());
            return null;
        }

    }

    public void edit() {
        MenuText menuText = new MenuText();
        User user = new User();
        UserDao userDao = new UserDao();
        Scanner scanner = new Scanner(System.in);
        int id = 0;
        do {
            System.out.println(userDao.findAll());
            System.out.println(menuText.userEditId());
            while (!scanner.hasNextInt()) {
                scanner.nextLine();
                System.err.println(menuText.wrongValue());
                System.out.println(menuText.userEditId());
            }
            id = scanner.nextInt();
            if (id == 0) {
                MenuUser.main();
            } else if (!userDao.exist(id)) {
                System.err.println(menuText.userNoIdError());
                break;
            }
            scanner.nextLine();
            System.out.println(userDao.read(id));
            System.out.println(menuText.userEditName());
            String name = scanner.nextLine();
            System.out.println(menuText.userEditMail());
            String email = scanner.nextLine();
            if (email.equals("0")) {
                MenuUser.main();
            }
//            else if (userDao.emailExist(email)) {
//                System.err.println(menuText.userEmailExist());
//                break;;
//            }
            System.out.println(menuText.userEditPass());
            String password = scanner.nextLine();
            if (password.equals("0")) {
                MenuUser.main();
            }
            System.out.println(menuText.userCreateGroup());
            int userGroupId = scanner.nextInt();
            if (userGroupId == 0) {
                MenuUser.main();
            }
            user.setId(id);
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            user.setUserGroupId(userGroupId);
            userDao.update(user);
        } while (!scanner.hasNextLine());
    }

    public void delete() {
        UserDao userDao = new UserDao();
        MenuText menuText = new MenuText();
        Scanner scanner = new Scanner(System.in);
        System.out.println(menuText.userDeleteId());
        while (scanner.hasNext()) {
            int id = scanner.nextInt();
            if (userDao.read(id) != null) {
                System.out.println(userDao.read(id));
                System.out.println(menuText.userDeleteConfirm());
                int decision = scanner.nextInt();
                while (scanner.hasNextLine()) {
                    if (decision == 1) {
                        userDao.delete(id);
                        if (userDao.read(id) == null) {
                            System.out.println(menuText.userDeleteSucc());
                            MenuUser.main();
                        } else {
                            System.err.println(menuText.userDeleteFail());
                            MenuUser.main();
                        }
                    } else if (decision == 2) {
                        MenuUser.main();
                    } else if (decision == 0) {
                        break;
                    } else {
                        System.err.println(menuText.wrongValue());
                    }
                }

            } else {
                System.err.println(menuText.userNoIdError());
                continue;
            }
            if (id == 0) {
                MenuUser.main();
            } else {
                continue;
            }
        }
    }
}
