package at.czlabinger.order;

import java.util.List;

public class Order implements Comparable<Order> {
  
  private static int nextID = 1;
  private int id;
  private State state;
  private List<Product> products;

  public Order(List<Product> products) {
    this.id = nextID;
    nextID++;
    this.state = State.New;
    this.products = products;
  }
  
  public int getId() {
    return this.id;
  }

  public double getPrice() {
    double sum = 0;
    for(Product p : this.products) {
      sum += p.getPrice();
    }
    return sum;
  }

  public void setState(State state) {
    this.state = state;
  }

  public State getState() {
    return this.state;
  }

  @Override
  public int compareTo(Order o) {
    return ((Integer) this.id).compareTo((Integer) o.getId());
  }
}
