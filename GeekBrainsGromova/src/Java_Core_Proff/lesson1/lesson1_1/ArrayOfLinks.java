package Java_Core_Proff.lesson1.lesson1_1;

public class ArrayOfLinks {
    private int size = 3;
    private Object[] arrayOfLinks;

   ArrayOfLinks(Object[] arrayOfLinks){
        this.arrayOfLinks = arrayOfLinks;
    }

    Object[] replaceElements(int i, int j){
        Object elem;
        if (i<size & j<size) {
            elem = arrayOfLinks[i];
            arrayOfLinks[i] = arrayOfLinks[j];
            arrayOfLinks[j] = elem;
            return arrayOfLinks;
        } else {
            System.out.println("Вы пытаетесь переместить несуществующий элемент массива!");
            return null;
        }
    }

    void arrayInfo(){
        for(int i=0; i<size; i++){
            System.out.print(arrayOfLinks[i].toString() + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Object[] objects ={new Person("Lena"), new Person("Andre"), new Person("Nick")};
        ArrayOfLinks arrayOfLinks =  new ArrayOfLinks(objects);
        arrayOfLinks.replaceElements(1,2);
        arrayOfLinks.arrayInfo();
        arrayOfLinks.replaceElements(2,6);
    }
}
