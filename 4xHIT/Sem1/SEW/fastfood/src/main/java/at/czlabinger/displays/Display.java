package at.czlabinger.displays;

import java.util.Set;

import at.czlabinger.order.Order;

public interface Display {

  public void update(Set<Order> orders);
  public String display();
  public void addOrder(Order o);
}
