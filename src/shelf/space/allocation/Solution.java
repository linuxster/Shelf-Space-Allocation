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
    public static int printSolution () {
        
        return 1;
    }
}
