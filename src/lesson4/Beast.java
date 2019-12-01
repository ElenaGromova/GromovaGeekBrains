package lesson4;

public class Beast extends Animal {
    protected int pawsNumber ;
    public Beast ( String name , int pawsNumber ) {
        this . name = name ;
        this . pawsNumber = pawsNumber ;
    }
    public Beast (  ) {
          }
    public void beastInfo () {
        System . out . println ( "Beast: " + name + " with " + pawsNumber + " paws" );
    }
}