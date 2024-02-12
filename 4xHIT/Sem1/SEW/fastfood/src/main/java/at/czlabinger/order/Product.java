package at.czlabinger.order;

public class Product {
  
  private double price;

  public Product(double price) {
    this.price = price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public double getPrice() {
    return this.price;
  }

}
