package at.czlabinger.displays;

import java.util.Set;

import at.czlabinger.order.Order;
import at.czlabinger.order.State;

public class POS implements Display {
  
  private Set<Order> orders;

  public POS(Set<Order> orders) {
    this.orders = orders;
  }
 
  public void addOrder(Order o) {
    this.orders.add(o);
    this.update(orders);
  }

  public void update(Set<Order> orders) {
    this.orders = orders;
  }

  public String display() {
    String out = "";
    for(Order o : this.orders) {
      if(o.getState().equals(State.New)) {
        out += o.getId() + "\n";
      }
    }
    if(out.length() >= 4) {
      out =  out.substring(0, out.length() - 1);
    }
    return out;
  }
}
