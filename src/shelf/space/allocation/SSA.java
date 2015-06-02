package shelf.space.allocation;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Collections;
import java.util.Random;


// Para Fazer:  ACABAR O BIGSWITCH (LINHA 50)
// tirar um produto só de uma prateleira

public class SSA {
    
    public static void main(String[] args) {
        Solution S0 = new Solution();
        Solution S1 = new Solution();
        Solution globalBest = new Solution(), aux = new Solution();
        int bestIteration = 0;
        List<Action> tabuList = new ArrayList<Action>();
        
        
        List<Product> P = SSA.registerProducts();
        Shelf S[] = SSA.registerShelves();
        
        // GERAR A SOLUÇÃO INICIAL
        SSA.generateInitialSolution(S0, P, S);
        //SSA.generateInitialSolution2(S0, P, S);
        Solution.getProfit(P, S0);
        System.out.println("SOLUÇÃO INICIAL:");
        Solution.printRepresentation(Solution.problemRepresentation(S0, P), S0.profit);
        //System.out.printf("%.2f\t%.2f\t%.2f\t%.2f\n", S0.Shelves.get(0).freeWidth, S0.Shelves.get(1).freeWidth, S0.Shelves.get(2).freeWidth, S0.Shelves.get(3).freeWidth);
        
        // INICIAR A PESQUISA TABU
        int maxIterations = 7500, tabuIterationLimitRemove = 5, tabuIterationLimitAdd = 7;
        int bigSwitch=-1, aux1 = 0;
        boolean ok = false;
        
        Solution.copySolution(S1, S0);
        for (int i = 0; i < maxIterations; i++) {
            Action actionIteration = new Action();
            Action.updateTabuList(tabuList);
            aux = SSA.generateProblemIteration(P, S, S1, actionIteration, tabuList);
            
            
            if(aux.profit > globalBest.profit) {
                Solution.copySolution(globalBest, aux);
                Solution.copySolution(S1, aux);
                bestIteration = i;
                actionIteration.tabuCount = tabuIterationLimitAdd;
                tabuList.add(actionIteration);
            }
            else if(aux.profit > S1.profit) {
                Solution.copySolution(S1, aux);
                actionIteration.tabuCount = tabuIterationLimitAdd;
                tabuList.add(actionIteration);
            }
            else {
                while(!ok) {
                    bigSwitch = Action.randomProduct();
                    for (int j = 0; j < tabuList.size(); j++) {
                        if(tabuList.get(j).shelf == -1 && tabuList.get(j).product1 == bigSwitch )
                            aux1++;
                    }
                    if(aux1 > 0) 
                        aux1 = 0;
                    else {
                        if(Product.isUsed(P, aux, bigSwitch) == false)
                            continue;
                        ok = true;
                    }
                }
                ok = false;
                S1 = Solution.switchNeighbourhood(S1, P, bigSwitch);
                actionIteration.shelf = -1;
                actionIteration.product1 = bigSwitch; 
                actionIteration.product2 = bigSwitch; 
                actionIteration.tabuCount = tabuIterationLimitRemove;
            }
            if(Solution.isEmpty(S1)) {
                System.out.println("Chegou-se a uma solução nula.");
                break;
            }
            tabuList.add(actionIteration);
            //System.out.println("\nMELHOR SOLUÇÃO ITERAÇÃO "+ (i+1) +":" );
            //Solution.printRepresentation(Solution.problemRepresentation(S1, P), S1.profit);
            //System.out.printf(" %.2f\t %.2f\t %.2f\t %.2f\t (largura disponível)\n", S1.Shelves.get(0).freeWidth, S1.Shelves.get(1).freeWidth, S1.Shelves.get(2).freeWidth, S1.Shelves.get(3).freeWidth);
            //Action.printAction(actionIteration);            
        }
        System.out.println("\nMELHOR SOLUÇÃO: (iteração " + (bestIteration+1) +")");
        Solution.printRepresentation(Solution.problemRepresentation(globalBest, P), globalBest.profit);
        System.out.printf(" %.2f\t %.2f\t %.2f\t %.2f\t (largura disponível)\n", globalBest.Shelves.get(0).freeWidth, globalBest.Shelves.get(1).freeWidth, globalBest.Shelves.get(2).freeWidth, globalBest.Shelves.get(3).freeWidth);
            
        
               
    }
    
    // 
    public static Solution generateProblemIteration(List<Product> p, Shelf[] s, Solution s0, Action a, List<Action> tabuList) {
        List<Solution> sList = new ArrayList<Solution>();
        List<Action> aList = new ArrayList<Action>();
        boolean skipSolution = false;
        
        for (int i = 0; i < s0.Shelves.size(); i++) {
            for (int j = 0; j < p.size(); j++) {
                if(Shelf.getFacings(s0.Shelves.get(i), p.get(j)) > 0){
                    for (int k = 0; k < p.size(); k++) {
                        for (int m = 0; m < tabuList.size(); m++) {
                            if(tabuList.get(m).shelf == i && tabuList.get(m).product2 == k )
                                skipSolution = true;
                        }
                        if(skipSolution) {
                            skipSolution = false;
                            continue;
                        }
                        Solution newSolution = new Solution();
                        Solution.copySolution(newSolution, s0);
                        Shelf.removeProduct(newSolution.Shelves.get(i), p.get(j));
                        Solution.getProfit(p, newSolution);
                        
                        Action auxAction = new Action();
                        auxAction.shelf = i;
                        auxAction.product1 = j;
                        auxAction.product2 = k;
                        
                        Shelf.addProduct(newSolution.Shelves.get(i), p.get(k));
                        Solution.getProfit(p, newSolution);
                        aList.add(auxAction);
                        sList.add(newSolution);
                    }
                }
                else {
                    for (int k = 0; k < tabuList.size(); k++) {
                        if(tabuList.get(k).shelf == -1 && tabuList.get(k).product1 == j )
                            skipSolution = true;
                    }
                    if(skipSolution) {
                        skipSolution = false;
                        continue;
                    }
                    Solution aux = new Solution();
                    Solution.copySolution(aux, s0);
                    Shelf.addProduct(aux.Shelves.get(i), p.get(j));
                    Solution.getProfit(p, aux);
                    
                    Action auxAction = new Action();
                    auxAction.shelf = i;
                    auxAction.product1 = j;
                    auxAction.product2 = j;
                    
                    aList.add(auxAction);
                    sList.add(aux);
                }
            }
        }
        
        int index = Solution.mostLucrative(sList);
        Action.copyAction(a, aList.get(index));
        return sList.get(index);
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
                Shelf.addInitialProduct(solution.Shelves.get(i), Product.getProduct(products, orderProductsProfit.get(iterator)));
                iterator++;
            }
            else 
                iterator++;
            if(iterator == orderProductsProfit.size())
                iterator = 0;
        }
        return 1;
    }
    // gera a nossa solução inicial para o problema
    public static int generateInitialSolution2(Solution solution, List<Product> products, Shelf[] shelves) {
        Shelf.initializeShelfList(solution.Shelves, shelves);
        
        for (int i = 0; i < solution.Shelves.size(); i++) {
            switch(i) {
                case (0):
                    Shelf.addInitialProduct(solution.Shelves.get(i), Product.getProduct(products, 3));
                    break;
                case (1):
                    Shelf.addInitialProduct(solution.Shelves.get(i), Product.getProduct(products, 2));
                    Shelf.addInitialProduct(solution.Shelves.get(i), Product.getProduct(products, 6));
                    break;
                case (2):
                    Shelf.addInitialProduct(solution.Shelves.get(i), Product.getProduct(products, 4));
                    Shelf.addInitialProduct(solution.Shelves.get(i), Product.getProduct(products, 5));
                    break;
                case (3):
                    Shelf.addInitialProduct(solution.Shelves.get(i), Product.getProduct(products, 1));
                    break;
            }
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
                    s.freeWidth = 15.0;
                    s.worth = 1.00;
                    break;
                case 1:
                    s.freeWidth = 15.00;
                    s.worth = 1.30;
                    break;
                case 2:
                    s.freeWidth = 15.00;
                    s.worth = 1.50;
                    break;
                case 3:
                    s.freeWidth = 15.00;
                    s.worth = 1.20;
                    break;
            }
            S[i] = s;
        }
        return S;
    }
    // introduz no sistema, numa lista de Produtos, os que são dados na instância
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
                    p0.width = 2.80;
                    break;
                case 1:
                    p0.price = 9.37;
                    p0.salesValue = 23.62;
                    p0.elasticity = 0.2273;
                    p0.width = 6.10;
                    break;
                case 2:
                    p0.price = 5.1;
                    p0.salesValue = 25.59;
                    p0.elasticity = 0.2089;
                    p0.width = 2.50;
                    break;
                case 3:
                    p0.price = 11.48;
                    p0.salesValue = 22.40;
                    p0.elasticity = 0.2143;
                    p0.width = 6.00;
                    break;
                case 4:
                    p0.price = 6.74;
                    p0.salesValue = 15.62;
                    p0.elasticity = 0.2955;
                    p0.width = 3.60;
                    break;
                case 5:
                    p0.price = 5.97;
                    p0.salesValue = 10.50;
                    p0.elasticity = 0.3104;
                    p0.width = 3.30;
                    break;
            }
            p.add(p0);
        }
        return p;
    }
    
}
