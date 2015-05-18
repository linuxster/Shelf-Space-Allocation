
package shelf.space.allocation;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Collections;


public class Shelf {
    int id;
    double freeWidth, usedWidth, worth;
    List<Product> products = new ArrayList<Product>();
    
    public static Shelf getShelf(Shelf s[], int n) {
        return s[n];
    }
    public static int getFacings(Shelf s, Product p) {
        int n=0;
        for (int j = 0; j <s.products.size(); j++) {
            if(s.products.get(j).id == p.id) 
                n++;
        } 
        return n;
    }
    
    public static int addProduct(Shelf s, Product p) {
        return 1;
    }
    
}
