package pl.coderslab.menu;

import pl.coderslab.dao.UserDao;
import pl.coderslab.plain.User;

import java.util.Arrays;
import java.util.Scanner;

public class MenuUser {

    public static void main() {
        MenuMain menuMain = new MenuMain();
        pl.coderslab.menu.MenuUser menuUser = new pl.coderslab.menu.MenuUser();
        Scanner scanner = new Scanner(System.in);
        UserDao user = new UserDao();
        int choiceMenu = 4;

        while (choiceMenu >= 1 || choiceMenu <= 5) {
            System.out.println("**************************************");
            System.out.println("************   Menu  User:  **********");
            System.out.println("**************************************");
            System.out.println("**** 1. Add - dodanie użytkownika    *");
            System.out.println("**** 2. Edit - edycja użytkownika    *");
            System.out.println("**** 3. Delete - kasacja użytkownika *");
            System.out.println("**** 4. Quit - Main menu             *");
            System.out.println("**************************************");

            int choice = scanner.nextInt();
            if (choice == 1) {
                menuUser.add();
            } else if (choice == 2) {
                System.out.println(Arrays.toString(user.findAll()));
                menuUser.edit();
            } else if (choice == 3) {
                System.out.println(Arrays.toString(user.findAll()));
                menuUser.delete();
            } else if (choice == 4) {
                menuMain.main();
            } else {
                System.out.println("**********************************");
                System.out.println("***** Podano błędną wartość! *****");
                scanner.nextInt();
                continue;
            }
        }
    }

    public User add() {
        UserDao userDao = new UserDao();
        Scanner scanner = new Scanner(System.in);
        System.out.println("**************************************");
        System.out.println("******* Tworzenie użytkownika: *******");
        System.out.println("**************************************");
        System.out.println("************* 0 = Wróć ***************");
        System.out.println("****** Wpisz imię i użytkownika: *****");
        System.out.println("**************************************");
        String name = scanner.nextLine();
        if (name.equals("0")) {
            MenuUser.main();
        }
        System.out.println("**************************************");
        System.out.println("*****  Wpisz email użytkownika:  *****");
        System.out.println("**************************************");
        String email = scanner.nextLine();
        if (email.equals("0")) {
            pl.coderslab.menu.MenuUser.main();
        }
        System.out.println("**************************************");
        System.out.println("****  Wpisz password użytkownika: ****");
        System.out.println("**************************************");
        String password = scanner.nextLine();
        if (password.equals("0")) {
            pl.coderslab.menu.MenuUser.main();
        }
        System.out.println("***************************************");
        System.out.println("*** Wpisz userGroupId użytkownika: ****");
        System.out.println("***************************************");
        int userGroupId = scanner.nextInt();
        if (userGroupId == 0) {
            pl.coderslab.menu.MenuUser.main();
        }
        User user = new User(name, email, password, userGroupId);
        userDao.create(user);
        if (userDao.created(user.getId())) {
            System.out.println("**********************************");
            System.out.println("*******   User  create:   ********");
            System.out.println("*******      Success     *********");
            System.out.println("**********************************");
            return user;
        } else {
            System.out.println("**********************************");
            System.out.println("*******   User  create:   ********");
            System.out.println("*******      Failed      *********");
            System.out.println("**********************************");
            return null;
        }
    }

    public void edit() {
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);
        User user = new User();
        System.out.println("**************************************");
        System.out.println("*******   Edycja użytkownika   *******");
        System.out.println("*******  Wpisz ID użytkownika: *******");
        System.out.println("**************************************");
        System.out.println("************* 0 = Wróć ***************");
        System.out.println("**************************************");

        int id = scanner.nextInt();
        if (id == 0) {
            pl.coderslab.menu.MenuUser.main();
        }
        UserDao userDao = new UserDao();
        System.out.println(userDao.read(id));
        scanner.nextLine();
        System.out.println("**************************************");
        System.out.println("****** Edytuj imię użytkownika: ******");
        System.out.println("**************************************");
        String name = scanner.nextLine();
        System.out.println("**************************************");
        System.out.println("****** Edytuj email użytkownika: *****");
        System.out.println("**************************************");
        String email = scanner.nextLine();
        if (email.equals("0")) {
            pl.coderslab.menu.MenuUser.main();
        }
        System.out.println("**************************************");
        System.out.println("*****  Edytuj hasło użytkownika: *****");
        System.out.println("**************************************");
        String password = scanner.nextLine();
        if (password.equals("0")) {
            pl.coderslab.menu.MenuUser.main();
        }
        System.out.println("***************************************");
        System.out.println("*** Edytuj userGroupId użytkownika: ***");
        System.out.println("***************************************");
        int userGroupId = scanner.nextInt();
        if (userGroupId == 0) {
            pl.coderslab.menu.MenuUser.main();
        }
        user.setId(id);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setUserGroupId(userGroupId);
        userDao.update(user);
    }

    public void delete() {
        UserDao userDao = new UserDao();
        Scanner scanner = new Scanner(System.in);
        System.out.println("*************************************");
        System.out.println("*******  Usuwanie użytkownika *******");
        System.out.println("* Podaj ID użytkownika do usunięcia *");
        System.out.println("*************************************");
        System.out.println("*********** 0 = Wróć ****************");
        System.out.println("*************************************");
        while (scanner.hasNext()) {
            int id = scanner.nextInt();
            if (userDao.read(id) != null) {
                System.out.println(userDao.read(id));
                System.out.println("*******************************************************************");
                 System.out.printf("********** Czy na pewno chcesz usunąć użytkownika o ID: %s ********\n", id);
                System.out.println("********************** 1 = Tak ************************************");
                System.out.println("********************** 2 = Nie ************************************");
                System.out.println("*******************************************************************");
                int decision = scanner.nextInt();
                while (scanner.hasNextLine()) {
                    if (decision == 1) {
                        userDao.delete(id);
                        if (userDao.read(id) == null) {
                            System.out.println("**********************************");
                            System.out.println("*******   User  delete:   ********");
                            System.out.println("*******      Success     *********");
                            System.out.println("**********************************");
                            pl.coderslab.menu.MenuUser.main();
                        } else {
                            System.out.println("**********************************");
                            System.out.println("*******   User  delete:   ********");
                            System.out.println("*******      Failed      *********");
                            System.out.println("**********************************");
                            pl.coderslab.menu.MenuUser.main();
                        }
                    } else if (decision == 2) {
                        pl.coderslab.menu.MenuUser.main();
                    } else {
                        System.out.println("**********************************");
                        System.out.println("*******    Wrong Value:   ********");
                        System.out.println("********     Insert      *********");
                        System.out.println("**********************************");
                    }
                }

            } else {
                System.out.println("***********************************");
                System.out.println("*********    User Error:   ********");
                System.out.println("*********    No User ID    ********");
                System.out.println("*********  Write ID again  ********");
                System.out.println("*********  Press 0 to back ********");
                System.out.println("***********************************");
                continue;
            }
            if (id == 0) {
                pl.coderslab.menu.MenuUser.main();
            } else {
                continue;
            }
        }
    }
}
