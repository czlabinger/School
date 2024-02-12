package at.czlabinger.restaurant;

import at.czlabinger.displays.Display;
import java.util.List;
import java.util.Set;


import at.czlabinger.order.Order;

public abstract class Subject {
  
  Set<Order> orders;
  List<Display> displays;

  public void removeDisplay(Display o) {
    this.displays.remove(o);
  }

  public void addDisplay(Display o) {
    this.displays.add(o);
  }

  public void setDisplays(List<Display> displays) {
    this.displays = displays;
  }

  public void notifyDisplays() {
    for(Display d : this.displays) {
      d.update(orders);
    }
  }
  
  public void updateList(Set<Order> orders) {
    this.orders = orders;
    this.notifyDisplays();
  }
}
