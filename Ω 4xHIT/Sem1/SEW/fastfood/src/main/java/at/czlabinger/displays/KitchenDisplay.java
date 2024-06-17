package at.czlabinger.displays;

import at.czlabinger.order.State;
import at.czlabinger.restaurant.Restaurant;
import at.czlabinger.order.Order;

import java.util.Set;

public class KitchenDisplay implements Display {

  private Set<Order> orders; 
  private Restaurant r;

  public KitchenDisplay(Set<Order> orders, Restaurant r) {
    this.orders = orders;
    this.r = r;
  }

  public void update(Set<Order> orders) {
    this.orders = orders;
  }

  public void startOrder(Order o) {
    if(!o.getState().equals(State.New))
      throw new IllegalArgumentException("Order can only be started if it is new");

    this.orders.remove(o);
    o.setState(State.InProduction);
    this.orders.add(o);
    r.updateList(orders);
  }

  public void finishOrder(Order o) {
    if(!o.getState().equals(State.InProduction))
      throw new IllegalArgumentException("Order can only be finished if it was started");

    this.orders.remove(o);
    o.setState(State.Ready);
    this.orders.add(o);
    r.updateList(orders);
  }

  public void addOrder(Order o) {
    this.orders.add(o);
  }

  public String display() {
    String out = "";
    for(Order o : this.orders) {
      if(o.getState().equals(State.InProduction)) {
        out += o.getId() + "\n";
      }
    }
    if(out.length() >= 4) {
      out =  out.substring(0, out.length() - 1);
    }
    return out;
  }

}
