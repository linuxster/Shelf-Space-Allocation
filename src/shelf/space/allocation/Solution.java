//TODO acabar a printSolution
package shelf.space.allocation;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Collections;
import java.util.Random;


public class Solution {
    List<Shelf> S = new ArrayList<Shelf>();
    double profit;
    
    public static double getProfit (Product P[], Solution s) {
        int numberFacings=0;
        
        if(s.profit != 0)
            s.profit = 0;
        for (int i = 0; i < s.S.size(); i++) {
            for (int j = 0; j < P.length; j++) {
                numberFacings = Shelf.getFacings(s.S.get(i), P[j]);
                if(numberFacings == 0)
                    continue;
                s.profit += s.S.get(j).worth*P[i].price*Product.valueFacing(P[i], numberFacings);
            }
        }
        return s.profit;
    }
    public static int printSolution (Solution s) {
           for (int j = 0; j < s.S.size(); j++) {
                System.out.println("Prateleira " + j+1 + ":");
                for (int i = 0; i < s.S.get(j).products.size(); i++) {
                    System.out.println("Produto "+ i+1 + ":" + s.S.get(j).products.get(i).id);
               }
                System.out.println("Espaço livre sobrante: " + s.S.get(j).freeWidth);
            }
            System.out.println("Profit: " + s.profit);
        return 1;
    }
}
