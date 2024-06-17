package at.czlabinger.restaurant;

import at.czlabinger.order.Order;
import at.czlabinger.displays.Display;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;


public class Restaurant extends Subject {
  
  public Restaurant(Set<Order> orders) {
    this.orders = orders;
  } 

  public Restaurant() {
    this.orders = new TreeSet<>();
    this.displays = new ArrayList<>();
  }

  public void setDisplays(List<Display> displays) {
    this.displays = displays;
  }
}
