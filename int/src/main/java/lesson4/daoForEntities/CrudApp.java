package lesson4.daoForEntities;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CrudApp {
        public static void main(String[] args) {
            SessionFactory factory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .buildSessionFactory();

            Session session = null;
            try {
                session = factory.getCurrentSession();
                session.beginTransaction();

                User user = session.get(User.class, 1L);
                Dao<User, Long> userDao = new Dao<>(user, 1L);
                userDao.getObj().print();
                userDao.getObj().setName("Bob2");
                session.save(userDao.getObj());
                userDao.getObj().print();
                userDao.getObj().setName("Bob3");
                session.update(userDao.getObj());
                userDao.getObj().print();
                session.remove(userDao.getObj());
                userDao.getObj().print();

                Item itemBox = session.get(Item.class, 1L);
                Dao<Item, Long> itemDao = new Dao<>(itemBox, 1L);
                itemDao.getObj().print();
                itemDao.getObj().setTitle("Milk");
                session.save(itemDao.getObj());
                itemDao.getObj().print();
                itemDao.getObj().setTitle("Bread");
                session.update(itemDao.getObj());
                itemDao.getObj().print();
                session.remove(itemDao.getObj());
                itemDao.getObj().print();

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

//Dao<Item, Long> dao = new Dao<>(Item.class, Long.class);
