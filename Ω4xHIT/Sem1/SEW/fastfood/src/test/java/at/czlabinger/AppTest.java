package at.czlabinger;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import at.czlabinger.displays.CheckoutDisplay;
import at.czlabinger.displays.KitchenDisplay;
import at.czlabinger.displays.OrderDisplay;
import at.czlabinger.displays.POS;
import at.czlabinger.order.Order;
import at.czlabinger.order.Product;
import at.czlabinger.restaurant.Restaurant;
import at.czlabinger.displays.Display;

/**
 * Unit test for simple App.
 */
public class AppTest {

  Restaurant r;
  Order o1;
  Order o2;
  Product p1;
  Product p2;
  Product p3;
  
  POS pos;
  OrderDisplay od;
  KitchenDisplay kd;
  CheckoutDisplay cd;

  Set<Order> orders;
  ArrayList<Display> displays;
  ArrayList<Product> products1;
  ArrayList<Product> products2;

  @BeforeEach
  void beforeEach() {
    p1 = new Product(10.5);
    p2 = new Product(15);
    p3 = new Product(4.7);
    
    products1 = new ArrayList<>();
    products1.add(p1);
    products1.add(p2);

    o1 = new Order(products1);

    products2 = new ArrayList<>();
    products2.add(p3);

    o2 = new Order(products2);
    
    orders = new TreeSet<>();

    orders.add(o1);
    orders.add(o2);

    r = new Restaurant(orders);

    pos = new POS(orders);
    od = new OrderDisplay();
    kd = new KitchenDisplay(orders, r);
    cd = new CheckoutDisplay(orders, r);

    displays = new ArrayList<>();

    displays.add(pos);
    displays.add(od);
    displays.add(kd);
    displays.add(cd);

    r.setDisplays(displays);
  }
  
  @Test
  void startSameOrderFinishSameOrder() {
  
    kd.startOrder(o1);
    assertEquals("1\n", kd.display());

    kd.startOrder(o2);
    assertEquals("1\n2", kd.display());

    kd.finishOrder(o1);
    assertEquals("2\n", kd.display());
    assertEquals("1\n", cd.display());

    kd.finishOrder(o2);
    assertEquals("", kd.display());

    assertEquals("1\n2", cd.display());

    cd.collectOrder(o1);
    cd.collectOrder(o2);

    assertEquals("", cd.display());
  }

  @Test
  void sameOrderReadyDifferenOrderCollected() {
    kd.startOrder(o1);
    assertEquals("3\n", kd.display());

    kd.startOrder(o2);
    assertEquals("3\n4", kd.display());
      
    kd.finishOrder(o1);
    assertEquals("4\n", kd.display());
    assertEquals("3\n", cd.display());

    kd.finishOrder(o2);
    assertEquals("", kd.display());
    assertEquals("3\n4", cd.display());

    cd.collectOrder(o2);
    assertEquals("3\n", cd.display());

    cd.collectOrder(o1);
    assertEquals("", cd.display());
  }
  
  @Test
  void startSameOrderFinishDifferentOrder() {
    kd.startOrder(o1);
    assertEquals("5\n", kd.display());

    kd.startOrder(o2);
    assertEquals("5\n6", kd.display());

    kd.finishOrder(o2);
    assertEquals("5\n", kd.display());
    assertEquals("6\n", cd.display());

    kd.finishOrder(o1);
    assertEquals("", kd.display());
    assertEquals("5\n6", cd.display());
  }
}
