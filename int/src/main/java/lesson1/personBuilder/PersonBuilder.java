package lesson1.personBuilder;

public class PersonBuilder {
    public static void main(String[] args) {
        Person person1 = new Person.Builder().addFirstName("Елена").addLastName("Громова").addMiddleName("Анатольевна").addCountry("Россия").addAddress("Омск").addPhone("+79507975783").addAge(34).addGender("женский").getPerson();
        Person person2 = new Person.Builder().addFirstName("Андрей").addLastName("Прокопьев").addMiddleName("Владимирович").addAddress("Омск").addAge(33).getPerson();
        Person person3= new Person.Builder().addFirstName("Александр").addAddress("Ростов-на-Дону").addAge(31).getPerson();
        person1.toString();
        person2.toString();
        person3.toString();
    }
}
