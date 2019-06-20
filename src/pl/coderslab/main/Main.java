package pl.coderslab.main;

import pl.coderslab.dao.UserDao;
import pl.coderslab.menu.AdminPanel;
import pl.coderslab.menu.MenuText;
import pl.coderslab.menu.UserPanel;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int upperMenu = 3;
        int lowerMenu = 1;
        int properChoose = 0;
        MenuText menuText = new MenuText();
        AdminPanel adminPanel = new AdminPanel();
        UserPanel userPanel = new UserPanel();
        UserDao userDao = new UserDao();
        do {
            try {
                System.out.println(menuText.mainWelcomeMenu());
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
                        System.out.println(menuText.getUserId());
                        int userId = scanner.nextInt();
                        if (userDao.exist((userId))) {
                            userPanel.main(userId);
                        } else if (userId == 0) {
                            break;
                        } else {
                            System.out.println(menuText.userNoIdError());
                            break;
                        }
                    case 2:
                        adminPanel.main();
                        break;
                    case 3:
                        System.err.println(menuText.systemExitConfirm());
                        int quit = scanner.nextInt();
                        if (quit == 1) {
                            System.err.println(menuText.systemExit());
                            return;
                        } else if (quit == 2) {
                            break;
                        }
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
}

//    public static class GetMenuNumber {
//        private boolean myResult;
//        private int upperMenu;
//        private int lowerMenu;
//        private int properChoose;
//        private MenuText menuText;
//        private Scanner scanner;
//
//        public GetMenuNumber(int upperMenu, int lowerMenu, int properChoose, MenuText menuText) {
//            this.upperMenu = upperMenu;
//            this.lowerMenu = lowerMenu;
//            this.properChoose = properChoose;
//            this.menuText = menuText;
//        }
//
//        boolean is() {
//            return myResult;
//        }
//
//        public int getProperChoose() {
//            return properChoose;
//        }
//
//        public Scanner getScanner() {
//            return scanner;
//        }
//
//        public GetMenuNumber invoke() {
//            scanner = new Scanner(System.in);
//            menuText.mainWelcomeMenu();
//            while (!scanner.hasNextInt()) {
//                scanner.nextLine();
//                menuText.wrongValue();
//                continue;
//            }
//            properChoose = scanner.nextInt();
//            if (properChoose < lowerMenu || properChoose > upperMenu) {
//                menuText.wrongMenu();
//                myResult = true;
//                return this;
//            }
//            myResult = false;
//            return this;
//        }
//    }
