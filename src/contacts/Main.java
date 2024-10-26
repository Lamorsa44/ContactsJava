package contacts;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ContactManager contactManager = ContactManager.getInstance();
        boolean ON = true;

        while (ON) {
            System.out.println("Enter action (add, remove, edit, count, info, exit):");

            switch (sc.nextLine()) {
                case "add" -> {
                    contactManager.crateContact();
                }
                case "remove" -> {
                    contactManager.deleteContact();
                }
                case "edit" -> {
                    contactManager.editContact();
                }
                case "count" -> {
                    contactManager.countContacts();
                }
                case "info" -> {
                    contactManager.infoContacts();
                }
                case "exit" -> {
                    ON = false;
                }
            }
            System.out.println();
        }
    }
}
