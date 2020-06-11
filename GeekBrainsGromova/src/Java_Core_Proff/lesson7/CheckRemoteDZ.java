package Java_Core_Proff.lesson7;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class CheckRemoteDZ {
    public static void main(String[] args) throws Exception {
        File file = new File("C:/123");
        String[] str = file.list();

        for (String o : str) {
            String[] mass = o.split("\\.");
            if (!mass[1].equalsIgnoreCase("class")) {
                throw new RuntimeException(o, new Exception());
            }

            //Class ch = URLClassLoader.newInstance(new URL[]{file.toURL()}).loadClass(mass[0]);
            Class ch = Class.forName(mass[0],true, URLClassLoader.newInstance(new URL[]{file.toURL()}));
            Constructor constructor = ch.getConstructor(String.class, String.class, int.class);

            Object test1 = constructor.newInstance("Lucia", "black and white", 5);
            Method m = ch.getDeclaredMethod("info");
            m.invoke(test1);

            Method m2 = ch.getDeclaredMethod("getName");
            m2.invoke(test1);
            Method m3 = ch.getDeclaredMethod("getColor");
            m3.invoke(test1);
            Method m4 = ch.getDeclaredMethod("getAge");
            m4.invoke(test1);

            Class[] paramTypes = new Class[] {String.class, int.class};
            Method m5 = ch.getDeclaredMethod("setName", paramTypes[0]);
            m5.invoke(test1, "Barsik");
            Method m6 = ch.getDeclaredMethod("setColor", paramTypes[0]);
            m6.invoke(test1, "brown");
            Method m7 = ch.getDeclaredMethod("setAge", paramTypes[1]);
            m7.invoke(test1, 8);

        }
    }
}
