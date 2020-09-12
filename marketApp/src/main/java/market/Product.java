package market;

public class Product {
    private int id;
    private String title;
    private float price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Product() {
    }

    public Product(int id, String title, float price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public Product(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return String.format("Product [id = %d, title = '%s', price = '%.2f", id, title, price);
    }
}
