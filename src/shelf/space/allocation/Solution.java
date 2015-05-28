package shelf.space.allocation;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Collections;
import java.util.Random;



public class Solution {
    List<Shelf> Shelves = new ArrayList<Shelf>();
    double profit;
    
    // retorna o Lucro gerado por uma Solução
    public static double getProfit (List<Product> P, Solution s) {
        int numberFacings=0;
        
        if(s.profit != 0)
            s.profit = 0;
        
        // lucro total da prateleira
        for (int i = 0; i < s.Shelves.size(); i++) {
            for (int j = 0; j < P.size(); j++) {
                numberFacings = Shelf.getFacings(s.Shelves.get(i), P.get(j));
                if(numberFacings == 0)
                    continue;
                s.profit += s.Shelves.get(i).worth*Product.valueFacing(P.get(j), numberFacings);
            }
        }        
        return s.profit;
    }
    // de uma lista de soluções, retorna o índice daquela que for mais lucrativa
    public static int mostLucrative(List<Solution> sList) {
        int index=-1;
        double maxProfit=0;
        for (int i = 0; i < sList.size(); i++) {
            if(sList.get(i).profit > maxProfit) {
                maxProfit = sList.get(i).profit;
                index = i;
            }
        }
        
        return index;
    }
    // apenas imprime uma solução, Prateleira - Produtos - Lucro
    public static int printSolution (Solution s) {
        for (int j = 0; j < s.Shelves.size(); j++) {
            System.out.println("Prateleira " + (j+1) + ":");
            for (int i = 0; i < s.Shelves.get(j).products.size(); i++) {
                if(i==(s.Shelves.get(j).products.size()-1))
                    System.out.print(s.Shelves.get(j).products.get(i).id);
                else
                    System.out.print(s.Shelves.get(j).products.get(i).id+"-");
            }
            System.out.println("");
            System.out.print("Espaço livre: ");
            System.out.printf("%.1f\n", s.Shelves.get(j).freeWidth);
        }
        System.out.print("Lucro: "); System.out.printf("%.2f\n", s.profit);
        return 1;
    }
    // pega numa "Solution" e passa-a para nossa representação do problema
    public static int[][] problemRepresentation(Solution s, List<Product> p) {
        int output[][] = new int[p.size()][s.Shelves.size()];
        for (int i = 0; i < p.size(); i++) {
            for (int j = 0; j < s.Shelves.size(); j++) {
                output[i][j]=Shelf.getFacings(s.Shelves.get(j), p.get(i));
            }
        }
        return output;
    }
    // imprime a nossa representação para a solução ao problema
    public static void printRepresentation(int[][] rep, double profit) {
        System.out.print("Solução: (lucro "); System.out.printf("%.2f)\n", profit);
        for (int i = 0; i < rep.length /*nProducts*/ ; i++) {
            for (int j = 0; j < rep[i].length /*nShelves*/ ; j++) {
                System.out.print("X"+ (i+1) + (j+1) +": "+ rep[i][j] +"\t");
            }
            System.out.println("");
        }
    }
    // copia uma solução para outra , s2 --> s1
    public static void copySolution(Solution s1, Solution s2) {
        
        for (int i = 0; i < s2.Shelves.size(); i++) {
            Shelf aux = new Shelf();
            s1.Shelves.add(aux);
        }
        s1.profit = s2.profit;
        for (int i = 0; i < s2.Shelves.size(); i++) {
            s1.Shelves.get(i).id = s2.Shelves.get(i).id;
            s1.Shelves.get(i).freeWidth = s2.Shelves.get(i).freeWidth;
            s1.Shelves.get(i).usedWidth = s2.Shelves.get(i).usedWidth;
            s1.Shelves.get(i).worth = s2.Shelves.get(i).worth;
            for (int j = 0; j < s2.Shelves.get(i).products.size(); j++) {
                s1.Shelves.get(i).products.add(s2.Shelves.get(i).products.get(j));
            }
        }
    }
   
}
