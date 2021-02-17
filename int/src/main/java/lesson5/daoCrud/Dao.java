package lesson5.daoCrud;

public class Dao<T, L extends Number>{
    private T obj;
    private Long id;

    public Dao(T obj, Long id) {
        this.obj = obj;
        this.id = id;
    }

    public T getObj() {
        return obj;
    }

    public Long getId() {
        return id;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public void setId(Long id) {
        this.id = id;
    }

}

