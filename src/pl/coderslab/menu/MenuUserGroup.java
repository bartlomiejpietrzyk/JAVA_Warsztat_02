package pl.coderslab.menu;

import pl.coderslab.dao.UserGroupDao;
import pl.coderslab.plain.UserGroup;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuUserGroup {

    public static void main() {
        int upperMenu = 4;
        int lowerMenu = 1;
        int properChoose = 0;
        AdminPanel adminPanel = new AdminPanel();
        MenuUserGroup menuUserGroup = new MenuUserGroup();
        UserGroupDao userGroupDao = new UserGroupDao();
        MenuText menuText = new MenuText();
        do {
            try {
                System.out.println(Arrays.toString(userGroupDao.findAll()));
                System.out.println(menuText.userGroupWelcomeMenu());
                Scanner scanner = new Scanner(System.in);
                while (!scanner.hasNextInt()) {
                    scanner.nextLine();
                    System.err.println(menuText.wrongValue());
                    System.out.println(menuText.userGroupWelcomeMenu());
                    continue;
                }
                properChoose = scanner.nextInt();
                if (properChoose < lowerMenu || properChoose > upperMenu) {
                    System.err.println(menuText.wrongMenu());
                    continue;
                }
                switch (properChoose) {
                    case 1:
                        menuUserGroup.add();
                        break;
                    case 2:
                        System.out.println(Arrays.toString(userGroupDao.findAll()));
                        menuUserGroup.edit();
                        break;
                    case 3:
                        System.out.println(Arrays.toString(userGroupDao.findAll()));
                        menuUserGroup.delete();
                        break;
                    case 4:
                        adminPanel.main();
                        break;
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

    public UserGroup add() {
        UserGroupDao userGroupDao = new UserGroupDao();
        Scanner scanner = new Scanner(System.in);
        MenuText menuText = new MenuText();
        System.out.println(menuText.userGroupCreateName());
        String name = scanner.nextLine();
        if (name.equals("0")) {
            MenuUserGroup.main();
        }
        UserGroup userGroup = new UserGroup(name);
        userGroupDao.create(userGroup);
        if (userGroupDao.created(userGroup.getId())) {
            System.out.println(menuText.userGroupCreateSucc());
            return userGroup;
        } else {
            System.err.println(menuText.userGroupCreateFail());
            return null;
        }
    }

    public void edit() {
        Scanner scanner = new Scanner(System.in);
        UserGroup userGroup = new UserGroup();
        MenuText menuText = new MenuText();
        System.out.println(menuText.userGroupEditId());

        int id = scanner.nextInt();
        if (id == 0) {
            MenuUserGroup.main();
        }
        UserGroupDao userGroupDao = new UserGroupDao();
        System.out.println(userGroupDao.read(id));
        scanner.nextLine();
        System.out.println(menuText.userGroupEditName());
        String name = scanner.nextLine();
        userGroup.setId(id);
        userGroup.setName(name);
        userGroupDao.update(userGroup);
    }

    public void delete() {
        UserGroupDao userGroupDao = new UserGroupDao();
        Scanner scanner = new Scanner(System.in);
        MenuText menuText = new MenuText();
        System.out.println(menuText.userGroupDeleteId());
        while (scanner.hasNext()) {
            int id = scanner.nextInt();
            if (userGroupDao.read(id) != null) {
                System.out.println(userGroupDao.read(id));
                System.out.println(menuText.userGroupDeleteConfirm());
                int decision = scanner.nextInt();
                while (scanner.hasNextLine()) {
                    switch (decision) {
                        case 1:
                            userGroupDao.delete(id);
                            if (userGroupDao.read(id) == null) {
                                System.out.println(menuText.userGroupDeleteSucc());
                                MenuUserGroup.main();
                            } else {
                                System.err.println(menuText.userGroupDeleteFail());
                                MenuUserGroup.main();
                            }
                            break;
                        case 2:
                            MenuUserGroup.main();
                            break;
                        default:
                            System.out.println(menuText.badTypeInsert());
//                    if (decision == 1) {
//                        userGroupDao.delete(id);
//                        if (userGroupDao.read(id) == null) {
//                            System.out.println(menuText.userGroupDeleteSucc());
//                            MenuUserGroup.main();
//                        } else {
//                            System.err.println(menuText.userGroupDeleteFail());
//                            MenuUserGroup.main();
//                        }
//                    } else if (decision == 2) {
//                        MenuUserGroup.main();
//                    } else {
//                        System.out.println(menuText.badTypeInsert());
                    }
                }

            } else if (id == 0) {
                MenuUserGroup.main();
            } else {
                System.err.println(menuText.userGroupIdError());
                continue;
            }
        }
    }
}