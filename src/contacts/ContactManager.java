package contacts;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactManager {
    private static Scanner sc = new Scanner(System.in);
    private static ContactManager INSTANCE;
    private static List<Contact> contacts = new ArrayList<Contact>();
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");


    private ContactManager() {}

    public static ContactManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ContactManager();
        }
        return INSTANCE;
    }

    public void crateContact() {
        System.out.println("Enter the type (person, organization):");

        switch (sc.nextLine()) {
            case "person" -> {
                Person person = new Person();
                System.out.println("Enter the name:");
                person.setName(sc.nextLine());

                System.out.println("Enter the surname:");
                person.setSurname(sc.nextLine());

                System.out.println("Enter the birth date:");
                person.setBirthday(sc.nextLine());

                System.out.println("Enter the gender (M, F):");
                person.setGender(sc.nextLine());

                System.out.println("Enter the number:");
                person.setPhone(sc.nextLine());

                contacts.add(person);
            }
            case "organization" -> {
                Organization organization = new Organization();
                System.out.println("Enter the organization name:");
                organization.setOrganizationName(sc.nextLine());

                System.out.println("Enter the address:");
                organization.setOrganizationAddress(sc.nextLine());

                System.out.println("Enter the number:");
                organization.setPhone(sc.nextLine());

                contacts.add(organization);
            }
        }
        System.out.println("The record added.");
    }

    public void deleteContact() {
        if (contacts.isEmpty()) {
            System.out.println("No records to remove!");
        }
        else {
            listContacts();
            System.out.println("Select a record:");
            contacts.remove(sc.nextInt() - 1);
            sc.nextLine();
        }
    }

    public void editContact() {
        if (contacts.isEmpty()) {
            System.out.println("No records to edit!");
        }
        else {
            listContacts();
            System.out.println("Select a record:");
            int record = sc.nextInt() - 1;
            sc.nextLine();
            Contact contact = contacts.get(record);
            if (contact instanceof Person person) {
                System.out.println("Select a field (name, surname, birth, gender, number):");
                String field = sc.nextLine();
                switch (field) {
                    case "name" -> {
                        System.out.println("Enter the name:");
                        person.setName(sc.nextLine());
                    }
                    case "surname" -> {
                        System.out.println("Enter the surname:");
                        person.setSurname(sc.nextLine());
                    }
                    case "birth" -> {
                        System.out.println("Enter the birth date:");
                        person.setBirthday(sc.nextLine());
                    }
                    case "gender" -> {
                        System.out.println("Enter the gender (M, F):");
                        person.setGender(sc.nextLine());
                    }
                    case "number" -> {
                        System.out.println("Enter the number:");
                        person.setPhone(sc.nextLine());
                    }
                    default -> {
                        System.out.println("Enter a valid field!");
                    }
                }
                person.setLastUpdated(LocalDateTime.now());
            } else if (contact instanceof Organization organization) {
                System.out.println("Select a field (address, number):");
                String field = sc.nextLine();
                switch (field) {
                    case "address" -> {
                        System.out.println("Enter address:");
                        organization.setOrganizationAddress(sc.nextLine());
                    }
                    case "number" -> {
                        System.out.println("Enter the number:");
                        organization.setPhone(sc.nextLine());
                    }
                    default -> {
                        System.out.println("Enter a valid field!");
                    }
                }
                organization.setLastUpdated(LocalDateTime.now());
            }
            System.out.println("The record updated!");
        }
    }

    public void infoContacts() {
        if (!listContacts()) {
            return;
        }
        System.out.println("Enter index to show info:");
        int index = sc.nextInt() - 1;
        sc.nextLine();
        Contact contact = contacts.get(index);

        if (contact instanceof Person person) {
            System.out.printf("""
                    Name: %s
                    Surname: %s
                    Birth date: %s
                    Gender: %s
                    Number: %s
                    Time created: %s
                    Time last edit: %s
                    """, person.getName(),
                    person.getSurname(),
                    person.getBirthday(),
                    person.getGender(),
                    person.getPhone(),
                    person.getTimeCreated().format(format),
                    person.getLastUpdated().format(format));
        }
        else if (contact instanceof Organization organization) {
            System.out.printf("""
                    Organization name: %s
                    Address: %s
                    Number: %s
                    Time created: %s
                    Time last edit: %s
                    """, organization.getOrganizationName(),
                    organization.getOrganizationAddress(),
                    organization.getPhone(),
                    organization.getTimeCreated().format(format),
                    organization.getLastUpdated().format(format));
        }
    }

    public void countContacts() {
        System.out.printf("The Phone Book has %d records\n", contacts.size());
    }

    public boolean listContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No records to list!");
            return false;
        }
        else {
            int counter = 0;
            for (Contact contact : contacts) {
                if (contact instanceof Person person) {
                    System.out.printf("%d. %s %s\n",
                            ++counter, person.getName(), person.getSurname());
                } else if (contact instanceof Organization organization) {
                    System.out.printf("%d. %s\n",
                            ++counter, organization.getOrganizationName());
                }
            }
            return true;
        }
    }
}