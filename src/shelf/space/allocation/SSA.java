package shelf.space.allocation;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Collections;
import java.util.Random;

// Para Fazer:  
// Mudanças:    

public class SSA {
    
    public static void main(String[] args) {
        Solution S0 = new Solution();
        Solution S1 = new Solution();
        
        List<Product> P = SSA.registerProducts();
        Shelf S[] = SSA.registerShelves();
        
        SSA.generateInitialSolution(S0, P, S);
        Solution.getProfit(P, S0);
        Solution.printRepresentation(Solution.problemRepresentation(S0, P), S0.profit);
        
        /*
        Action a1 = new Action();
        S1 = Action.switchShelf(S0, a1, P);
        Solution.printRepresentation(Solution.problemRepresentation(S1, P), S1.profit);
        Action.printAction(a1);
        */
        Action a2 = new Action();
        S1 = Action.switchProduct(S0, a2, P);
        Solution.printRepresentation(Solution.problemRepresentation(S1, P), S1.profit);
        Action.printAction(a2);
        
    }
    
    // gera a nossa solução inicial para o problema
    public static int generateInitialSolution(Solution solution, List<Product> products, Shelf[] shelves) {
        List<Integer> orderProductsProfit = new ArrayList<Integer>();
        int i=0, iterator = 0;
        Shelf.initializeShelfList(solution.Shelves, shelves);
        Product.orderProductsProfit(products, orderProductsProfit);
        
        
        while( !Shelf.isFull(solution.Shelves.get((solution.Shelves.size())-1), products) ) {
            if(Shelf.isFull(solution.Shelves.get(i), products))
                i++;
            if(solution.Shelves.get(i).freeWidth >= Product.getProduct(products, orderProductsProfit.get(iterator)).width) {
                Shelf.addProduct(solution.Shelves.get(i), Product.getProduct(products, orderProductsProfit.get(iterator)));
                iterator++;
            }
            else 
                iterator++;
            if(iterator == orderProductsProfit.size())
                iterator = 0;
        }
        return 1;
    }
    // introduz no sistema, num vetor de Prateleiras, as que são dadas na instância
    public static Shelf[] registerShelves() {
        Shelf S[] = new Shelf[4];
        for (int i = 0; i < S.length; i++) {
            S[i] = new Shelf();
        }
        for (int i = 0; i < 4; i++) {
            Shelf s = new Shelf();
            s.id = i;
            s.products = new ArrayList<Product>();
            s.usedWidth = 0;
            switch(i) {
                case 0:
                    s.freeWidth = 15;
                    s.worth = 1;
                    break;
                case 1:
                    s.freeWidth = 15;
                    s.worth = 1.3;
                    break;
                case 2:
                    s.freeWidth = 15;
                    s.worth = 1.5;
                    break;
                case 3:
                    s.freeWidth = 15;
                    s.worth = 1.2;
                    break;
            }
            S[i] = s;
        }
        return S;
    }
    // introduz no sistema, numa lista de Produtos, o que são dadas na instância
    public static List<Product> registerProducts() {
        List<Product> p = new ArrayList<Product>();
        int productListSize = 6;
        
        for (int i = 0; i < productListSize; i++) {
            Product p0 = new Product();
            p0.id = i+1;
            switch(i) {
                case 0:
                    p0.price = 5.03;
                    p0.salesValue = 28.53;
                    p0.elasticity = 0.1532;
                    p0.width = 2.8;
                    break;
                case 1:
                    p0.price = 9.37;
                    p0.salesValue = 23.62;
                    p0.elasticity = 0.2273;
                    p0.width = 6.1;
                    break;
                case 2:
                    p0.price = 5.1;
                    p0.salesValue = 25.59;
                    p0.elasticity = 0.2089;
                    p0.width = 2.5;
                    break;
                case 3:
                    p0.price = 11.48;
                    p0.salesValue = 22.4;
                    p0.elasticity = 0.2143;
                    p0.width = 6;
                    break;
                case 4:
                    p0.price = 6.74;
                    p0.salesValue = 15.62;
                    p0.elasticity = 0.2955;
                    p0.width = 3.6;
                    break;
                case 5:
                    p0.price = 5.97;
                    p0.salesValue = 10.5;
                    p0.elasticity = 0.3104;
                    p0.width = 3.3;
                    break;
            }
            p.add(p0);
        }
        return p;
    }
    // forma antiga de registar produtos (sem uso atualmente)
    public static Product[] registerProductsOld() {
        Product P[] = new Product[6];
        for (int i = 0; i < P.length; i++) {
            P[i] = new Product();
        }
        for (int i = 0; i < 6; i++) {
            Product p = new Product();
            p.id = i+1;
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

/*
import java.util.Random;

public class MotionUncertainty {

    // after the agent decides the action, this gives us the real action (after applying the uncertainty on movements)
    public static String GenerateTrueAction() {
        String trueAction = new String();
        Random rand = new Random();
        int prob = rand.nextInt(100);
        double p1, p2;
        p1 = 0.8; p2 = 0.1;

        if ( prob <= 100*p1-1 )
            trueAction = "^";
        else if ( prob > (100*p1-1) &&  prob <= (100*(p1+p2)-1) )
            trueAction = "<";
        else if ( prob > (100*(p1+p2)-1) && prob <= 100*(p1+2*p2) )
            trueAction = ">";

        return trueAction;
    }
}
*/

/*   TESTE PARA LISTA FEITA EM EXCEL
        Solution S0 = new Solution();
        List<Shelf> shelf0 = new ArrayList<Shelf>();
        Shelf.initializeShelfList(shelf0, S);
        for (int i = 0; i < shelf0.size(); i++) {
            switch (i) {
                case (0): 
                    Shelf.addProduct(shelf0.get(i), P.get(1));
                    Shelf.addProduct(shelf0.get(i), P.get(0));
                    Shelf.addProduct(shelf0.get(i), P.get(2));
                    Shelf.addProduct(shelf0.get(i), P.get(4));
                   
                    //shelf0.get(i).products.add(P.get(1));
                    //Shelf.addProduct(shelf0.get(i), P[1]);
                    
                    break;
                case (1): 
                    Shelf.addProduct(shelf0.get(i), P.get(2));
                    Shelf.addProduct(shelf0.get(i), P.get(4));
                    Shelf.addProduct(shelf0.get(i), P.get(5));
                    Shelf.addProduct(shelf0.get(i), P.get(0));
                    Shelf.addProduct(shelf0.get(i), P.get(2));
                    break;
                case (2): 
                    Shelf.addProduct(shelf0.get(i), P.get(3));
                    Shelf.addProduct(shelf0.get(i), P.get(1));
                    Shelf.addProduct(shelf0.get(i), P.get(0));
                    break;
                case (3): 
                    Shelf.addProduct(shelf0.get(i), P.get(4));
                    Shelf.addProduct(shelf0.get(i), P.get(5));
                    Shelf.addProduct(shelf0.get(i), P.get(3));
                    break;
            }
        }
        S0.Shelves=shelf0;
        Solution.getProfit(P, S0);
        Solution.printSolution(S0);  
        */