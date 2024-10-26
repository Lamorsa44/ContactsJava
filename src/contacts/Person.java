package contacts;

public class Person extends Contact {
    private String name;
    private String surname;
    private String birthday;
    private String gender;

    public Person() {super();}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        if (!birthday.isEmpty()) {
            this.birthday = birthday;
        } else {
            System.out.println("Bad birth date!");
            this.birthday = "[no data]";
        }
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if (gender.matches("([MF])")) {
            this.gender = gender;
        } else {
            System.out.println("Bad gender!");
            this.gender = "[no data]";
        }
    }
}
