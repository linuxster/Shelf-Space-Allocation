
package shelf.space.allocation;

// TODO: em removeProduct falta verificar se o produto é usado em qualquer outro lado e, se não, fazer p.used=0

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Collections;


public class Shelf {
    int id;
    double freeWidth, usedWidth, worth;
    List<Product> products = new ArrayList<Product>();
    
    public static Shelf addProduct(Shelf s, Product p) {
        s.products.add(p);
        s.usedWidth += p.width;
        s.freeWidth -= p.width;
        if(p.used == 0)
            p.used = 1;
        return s;
    }
    public static Shelf removeProduct(Shelf s, Product p) {
        s.products.remove(p);
        s.usedWidth -= p.width;
        s.freeWidth += p.width;
        return s;
    }
    public static int getFacings(Shelf s, Product p) {
        int n=0;
        for (int j = 0; j <s.products.size(); j++) {
            if(s.products.get(j).id == p.id) 
                n++;
        } 
        return n;
    }
    
    
}
