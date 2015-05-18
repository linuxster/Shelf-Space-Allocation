package shelf.space.allocation;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author Ruben
 */
public class ShelfSpaceAllocation {
    
    public static void main(String[] args) {
        // TODO code application logic here
    
        Product P[] = ShelfSpaceAllocation.registerProducts();
        Shelf S[] = ShelfSpaceAllocation.registerShelves();
        /*for (int i = 0; i < 4; i++) {
            System.out.println("Prateleira "+S[i].id+ ": W-"+S[i].worth+"\t| U-"+S[i].usedWidth+"\t| F-"+S[i].freeWidth);
        }
        for (int i = 0; i < 6; i++) {
            System.out.println("Produto "+P[i].id+ ": P-"+P[i].price+"\t| S-"+P[i].salesValue+"\t| E-"+P[i].elasticity+"\t| W-"+P[i].width);
        }*/
        
        System.out.println("Iteração 3 produto 1: "+ Product.valueIteration(Product.getProduct(P, 5), 4));    
        
        Solution S0 = new Solution();
        Shelf shelves[] = new Shelf[4];
        
        for (int i = 0; i < S.length; i++) {
            Shelf S1 = S[i];
            
        }
        
        
    }

    public static Shelf[] registerShelves() {
        Shelf S[] = new Shelf[4];
        for (int i = 0; i < S.length; i++) {
            S[i] = new Shelf();
        }
        for (int i = 0; i < 4; i++) {
            Shelf s = new Shelf();
            s.id = i;
            switch(i) {
                case 0:
                    s.freeWidth = 15;
                    s.worth = 1;
                    s.usedWidth = 0;
                    s.products = null;
                    break;
                case 1:
                    s.freeWidth = 15;
                    s.worth = 1.2;
                    s.usedWidth = 0;
                    s.products = null;
                    break;
                case 2:
                    s.freeWidth = 15;
                    s.worth = 1.5;
                    s.usedWidth = 0;
                    s.products = null;
                    break;
                case 3:
                    s.freeWidth = 15;
                    s.worth = 1.2;
                    s.usedWidth = 0;
                    s.products = null;
                    break;
            }
            S[i] = s;
        }
        return S;
    }
    public static Product[] registerProducts() {
        Product P[] = new Product[6];
        for (int i = 0; i < P.length; i++) {
            P[i] = new Product();
        }
        for (int i = 0; i < 6; i++) {
            Product p = new Product();
            p.id = i;
            p.used = 0;
            switch(i) {
                case 0:
                    p.price = 5.03;
                    p.salesValue = 28.53;
                    p.elasticity = 0.1532;
                    p.width = 2.8;
                    break;
                case 1:
                    p.price = 9.37;
                    p.salesValue = 23.62;
                    p.elasticity = 0.2273;
                    p.width = 6.1;
                    break;
                case 2:
                    p.price = 5.1;
                    p.salesValue = 25.59;
                    p.elasticity = 0.2089;
                    p.width = 2.5;
                    break;
                case 3:
                    p.price = 11.48;
                    p.salesValue = 22.4;
                    p.elasticity = 0.2143;
                    p.width = 6;
                    break;
                case 4:
                    p.price = 6.74;
                    p.salesValue = 15.62;
                    p.elasticity = 0.2955;
                    p.width = 3.6;
                    break;
                case 5:
                    p.price = 5.97;
                    p.salesValue = 10.5;
                    p.elasticity = 0.3104;
                    p.width = 3.3;
                    break;
            }
            P[i] = p;
        }
        return P;
    }
    
}
