package at.czlabinger.displays;

import java.util.Set;


import at.czlabinger.order.Order;
import at.czlabinger.order.State;
import at.czlabinger.restaurant.Restaurant;

public class CheckoutDisplay implements Display {
  
  private Set<Order> orders;
  private Restaurant r;

  public CheckoutDisplay(Set<Order> orders, Restaurant r) {
    this.orders = orders;
    this.r = r;
  }

  public void update(Set<Order> orders) {
    this.orders = orders;
  }

  public void collectOrder(Order o) {
    if(!o.getState().equals(State.Ready)) 
      throw new IllegalArgumentException("Order can only be collected if Ready!");

    this.orders.remove(o);
    o.setState(State.Collected);
    this.orders.add(o);
    r.updateList(orders);
  }

  public void addOrder(Order o) {
    this.orders.add(o);
  }

  public String display() {
    String out = "";
    for(Order o : this.orders) {
      if(o.getState().equals(State.Ready)) {
        out += o.getId() + "\n";
      }
    }
    if(out.length() >= 4) {
      out =  out.substring(0, out.length() - 1);
    }
    return out;
  }
}
