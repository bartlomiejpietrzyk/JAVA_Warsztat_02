package pl.coderslab.menu;

import pl.coderslab.dao.UserGroupDao;
import pl.coderslab.plain.UserGroup;

import java.util.Arrays;
import java.util.Scanner;

public class MenuUserGroup {

    public static void main() {
        MenuMain menuMain = new MenuMain();
        pl.coderslab.menu.MenuUserGroup menuUserGroup = new pl.coderslab.menu.MenuUserGroup();
        Scanner scanner = new Scanner(System.in);
        UserGroupDao userGroup = new UserGroupDao();
        int choiceMenu = 4;

        while (choiceMenu >= 1 || choiceMenu <= 5) {
            System.out.println("*********************************************");
            System.out.println("************   Menu  UserGroup:  ************");
            System.out.println("*********************************************");
            System.out.println("**** 1. Add - dodaj grupe użytkowników      *");
            System.out.println("**** 2. Edit - edycja grupy użytkowników    *");
            System.out.println("**** 3. Delete - kasacja grupy użytkowników *");
            System.out.println("**** 4. Quit - Main menu                    *");
            System.out.println("*********************************************");

            int choice = scanner.nextInt();
            if (choice == 1) {
                menuUserGroup.add();
            } else if (choice == 2) {
                System.out.println(Arrays.toString(userGroup.findAll()));
                menuUserGroup.edit();
            } else if (choice == 3) {
                System.out.println(Arrays.toString(userGroup.findAll()));
                menuUserGroup.delete();
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

    public UserGroup add() {
        UserGroupDao userGroupDao = new UserGroupDao();
        Scanner scanner = new Scanner(System.in);
        System.out.println("*********************************************");
        System.out.println("******* Tworzenie grupy użytkowników: *******");
        System.out.println("*********************************************");
        System.out.println("*************** 0 = Wróć ********************");
        System.out.println("****** Wpisz nazwę grupy użytkowników: ******");
        System.out.println("*********************************************");
        String name = scanner.nextLine();
        if (name.equals("0")) {
            pl.coderslab.menu.MenuUserGroup.main();
        }
        UserGroup userGroup = new UserGroup(name);
        userGroupDao.create(userGroup);
        if (userGroupDao.created(userGroup.getId())) {
            System.out.println("**********************************");
            System.out.println("******* UserGroup  create: *******");
            System.out.println("*******      Success     *********");
            System.out.println("**********************************");
            return userGroup;
        } else {
            System.out.println("**********************************");
            System.out.println("******* UserGroup  create: *******");
            System.out.println("*******      Failed      *********");
            System.out.println("**********************************");
            return null;
        }
    }

    public void edit() {
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);
        UserGroup userGroup = new UserGroup();
        System.out.println("**************************************");
        System.out.println("***** Edycja grupy użytkowników   ****");
        System.out.println("***  Wpisz ID grupy użytkowników: ****");
        System.out.println("**************************************");
        System.out.println("************* 0 = Wróć ***************");
        System.out.println("**************************************");

        int id = scanner.nextInt();
        if (id == 0) {
            pl.coderslab.menu.MenuUserGroup.main();
        }
        UserGroupDao userGroupDao = new UserGroupDao();
        System.out.println(userGroupDao.read(id));
        scanner.nextLine();
        System.out.println("**********************************************");
        System.out.println("****** Edytuj nazwę grupy użytkowników: ******");
        System.out.println("**************************************");
        String name = scanner.nextLine();
        userGroup.setId(id);
        userGroup.setName(name);
    }

    public void delete() {
        UserGroupDao userGroupDao = new UserGroupDao();
        Scanner scanner = new Scanner(System.in);
        System.out.println("********************************************");
        System.out.println("*******  Usuwanie grupy użytkowników *******");
        System.out.println("* Podaj ID grupy użytkowników do usunięcia *");
        System.out.println("********************************************");
        System.out.println("************** 0 = Wróć ********************");
        System.out.println("********************************************");
        while (scanner.hasNext()) {
            int id = scanner.nextInt();
            if (userGroupDao.read(id) != null) {
                System.out.println(userGroupDao.read(id));
                System.out.println("*********************************************************************");
                System.out.printf("******* Czy na pewno chcesz usunąć grupy użytkowników o ID: %s ********\n", id);
                System.out.println("************************* 1 = Tak ***********************************");
                System.out.println("************************* 2 = Nie ***********************************");
                System.out.println("*********************************************************************");
                int decision = scanner.nextInt();
                while (scanner.hasNextLine()) {
                    if (decision == 1) {
                        userGroupDao.delete(id);
                        if (userGroupDao.read(id) == null) {
                            System.out.println("***************************************");
                            System.out.println("*******   UserGroup  delete:   ********");
                            System.out.println("***********      Success     **********");
                            System.out.println("***************************************");
                            pl.coderslab.menu.MenuUserGroup.main();
                        } else {
                            System.out.println("***************************************");
                            System.out.println("*********   UserGroup  delete:   ******");
                            System.out.println("***********      Failed      **********");
                            System.out.println("***************************************");
                            pl.coderslab.menu.MenuUserGroup.main();
                        }
                    } else if (decision == 2) {
                        pl.coderslab.menu.MenuUserGroup.main();
                    } else {
                        System.out.println("**********************************");
                        System.out.println("*******    Wrong Value:   ********");
                        System.out.println("********     Insert      *********");
                        System.out.println("**********************************");
                    }
                }

            } else {
                System.out.println("****************************************");
                System.out.println("***********  UserGroup Error:   ********");
                System.out.println("***********  No UserGroup ID    ********");
                System.out.println("***********   Write ID again    ********");
                System.out.println("***********  Press 0 to back    ********");
                System.out.println("****************************************");
                continue;
            }
            if (id == 0) {
                pl.coderslab.menu.MenuUserGroup.main();
            } else {
                continue;
            }
        }
    }
}