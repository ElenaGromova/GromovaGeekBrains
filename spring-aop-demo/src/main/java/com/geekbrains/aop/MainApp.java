package com.geekbrains.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//        TestBean tb = context.getBean("testBean", TestBean.class);

        UserDAO userDAO = context.getBean("userDAO", UserDAO.class);


        userDAO.addUser();
        System.out.println(userDAO.getAllUsers());

//        AppLoggingAspect aspect = context.getBean("appLoggingAspect", AppLoggingAspect.class);
//        aspect.beforeAddUserInUserDAOClass();
//        userDAO.deleteUser();
//        userDAO.updateUser();
//        userDAO.findAll();
//        userDAO.getAllUsers();
//        userDAO.setX(100);
//        System.out.println("-----\nGET:" + userDAO.getX());
//        userDAO.addUser();


//        System.out.println(userDAO.getClass().getName());

//        userDAO.deleteUser();
//        userDAO.updateUser();
//        System.out.println(userDAO.getAllUsers());
//        userDAO.setX(10);
//        System.out.println(userDAO.getX()); // странное исключение
//        userDAO.longOperation();

//        System.out.println(userDAO.getAllUsers());
//        userDAO.setX(13);
//        userDAO.deleteUser();
//        userDAO.updateUser();

//        ExternalSystemUserDAO externalSystemUserDAO = context.getBean("externalSystemUserDAO", ExternalSystemUserDAO.class);

//        userDAO.addUser();
//        userDAO.updateUser();
//        userDAO.deleteUser();
//        userDAO.findAll();
//        userDAO.findOneById(1L);
//        userDAO.getX();
//        userDAO.setX(10);

//        System.out.println("------------------");
//
//        System.out.println(userDAO.getX());
//        userDAO.setX(10);
//        System.out.println(userDAO.getX());
//
//        System.out.println("from Main: " + userDAO.getAllUsers());
//
//        userDAO.longOperation();
//
//        externalSystemUserDAO.addUser();

        context.close();
    }
}
