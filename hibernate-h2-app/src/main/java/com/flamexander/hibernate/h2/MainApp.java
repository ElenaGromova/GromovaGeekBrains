package com.flamexander.hibernate.h2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainApp {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        Session session = null;
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            User user = session.get(User.class, 1L);
            user.print();

            User user2 = new User();
            user2.setName("Bob2");
            session.save(user2);
            user2.print();

            Item itemBox = session.get(Item.class, 1L);
            itemBox.print();

            for (int i = 0; i < 10; i++) {
                Item item = new Item();
                item.setTitle("Sphere" + i);
                session.save(item);
                item.print();
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
            if (session != null) {
                session.close();
            }
        }
    }
}
