package org.example;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.example.Role.ADMIN;
import static org.example.Role.USER;

public class Main {
    public static void main(String[] args) {

        User user1 = new User("KiberSashka@gamil.com", "87654321)Aa", ADMIN);
        User user2 = new User("KiberSashka_@gamil.com", "87654321)}Aa", USER);
        User user3 = new User("KiberSashka@@@gamil.com", "87654321)}]Aa", USER);
        User[][] users = new User[][]{new User[]{user1}, new User[]{user2}, new User[]{user3}};
        boolean flag = true;
        Scanner sc;

        while (flag){
            try{
                sc = new Scanner(System.in);
                System.out.println("Enter your login: ");
                String loginU = sc.nextLine();
                System.out.println("Enter your password: ");
                String passwordU = sc.nextLine();
                if (userChek(passwordU, loginU, users) == ADMIN) {
                    System.out.println("1. File" + "\n" + "2. Create new user" + "\n" + "3. exit");
                    break;
                }
                if (userChek(passwordU, loginU, users) == USER) {
                    System.out.println("1. File" + "\n" + "2. Get play list" + "\n" + "3. exit");
                    break;
                }
                if (userChek(passwordU, loginU, users) == null) {
                    flag = false;
                    break;
                }

            } catch (WrongLoginException e1){
                System.out.println(e1.getMessage());
                flag = true;
            } catch (WrongPasswordException e2) {
                System.out.println(e2.getMessage());
                flag = true;
            } catch (WrongUserException e3) {
                System.out.println(e3.getMessage());
                flag = true;
            }

        }
    }

    public static Role userChek(String password, String login, User[][] users) throws WrongUserException, WrongPasswordException, WrongLoginException {
        Pattern patternPassword = Pattern.compile("[a-zA-z{}\\[\\](),.;&?!_\\-+0-9]{8,}");
        Pattern patternLogin = Pattern.compile("[a-zA-z@._-]{20,}");
        Matcher matcherPass = patternPassword.matcher(password);
        Matcher matcherLog = patternLogin.matcher(login);
        if (login.equals("exit") || password.equals("exit")) {
            return null;
        }
        if (!matcherLog.find()){
            throw new WrongLoginException("WRONG LOGIN" + "\n" +
                    "YOUR PASSWORD DOES NOT MEET THE CONDITIONS" +
                    "\n" + "TRY AGAIN!");

        }else {
            if (!matcherPass.find()) {
                throw new WrongPasswordException("WRONG PASSWORD" + "\n"
                        + "YOUR LOGIN DOES NOT MEET THE CONDITIONS"
                        + "\n" + "TRY AGAIN!");
            }

        }
        for (int i = 0; i < users.length; i++) {
            if ((login.equals(users[i][0].getLogin())) && (password.equals(users[i][0].getPassword()))) {
                return users[i][0].getRole();
            }
        }
        throw new WrongUserException("WRONG USER!");

    }
}

