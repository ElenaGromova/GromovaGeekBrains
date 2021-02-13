package lesson3.counter;

import lombok.Data;

@Data
public class Counter {
    private int counter;

    public Counter(int counter) {
        this.counter = counter;
    }

    public void increment() {
        counter++;
    }
}