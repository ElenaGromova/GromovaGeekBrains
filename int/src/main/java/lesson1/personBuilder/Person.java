package lesson1.personBuilder;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Person {
    private String firstName;
    private String lastName;
    private String middleName;
    private String country;
    private String address;
    private String phone;
    private int age;
    private String gender;

    public static class Builder{
        private String firstName;
        private String lastName;
        private String middleName;
        private String country;
        private String address;
        private String phone;
        private int age;
        private String gender;

        public Builder addFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder addLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder addMiddleName(String middleName) {
            this.middleName = middleName;
            return this;
        }

        public Builder addCountry(String country) {
            this.country = country;
            return this;
        }

        public Builder addAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder addPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder addAge(int age) {
            this.age = age;
            return this;
        }

        public Builder addGender(String gender) {
            this.gender = gender;
            return this;
        }

        public Person getPerson(){
            System.out.println("Создана Персона: Имя: " + this.firstName + " Фамилия: " + this.lastName + " Отчество: " + this.middleName +
                    " Страна: " + this.country + " Адрес: " + this.address + " Телефон: " + this.phone +
                    " Возраст: " + this.age + " Пол: " + this.gender);
            return new Person(this);
        }
    }

    private Person(Builder builder){
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.middleName = builder.middleName;
        this.country = builder.country;
        this.address = builder.address;
        this.age = builder.age;
        this.gender = builder.gender;
    }

    public String toString(){
        return "Создана Персона: Имя: " + this.firstName + " Фамилия: " + this.lastName + " Отчество: " + this.middleName +
                " Страна: " + this.country + " Адрес: " + this.address + " Телефон: " + this.phone +
                " Возраст: " + this.age + " Пол: " + this.gender;
    }

}
