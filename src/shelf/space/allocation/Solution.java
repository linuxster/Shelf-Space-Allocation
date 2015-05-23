package shelf.space.allocation;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Collections;
import java.util.Random;

// Para Fazer:  
// Mudanças:    

public class Solution {
    List<Shelf> Shelves = new ArrayList<Shelf>();
    double profit;
    
    // Retorna o Lucro gerado por uma Solução
    public static double getProfit (List<Product> P, Solution s) {
        int numberFacings=0;
        
        if(s.profit != 0)
            s.profit = 0;
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
    // Apenas imprime uma solução, Prateleira - Produtos - Lucro
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
    // Pega numa "Solution" e passa-a para nossa representação do problema
    public static int[][] problemRepresentation(Solution s, List<Product> p) {
        int output[][] = new int[p.size()][s.Shelves.size()];
        for (int i = 0; i < p.size(); i++) {
            for (int j = 0; j < s.Shelves.size(); j++) {
                output[i][j]=Shelf.getFacings(s.Shelves.get(j), p.get(i));
            }
        }
        return output;
    }
    // Imprime a nossa representação para a solução ao problema
    public static void printRepresentation(int[][] rep) {
        System.out.println("Solução: ");
        for (int i = 0; i < rep.length /*nProducts*/ ; i++) {
            for (int j = 0; j < rep[i].length /*nShelves*/ ; j++) {
                System.out.print("X"+ (i+1) + (j+1) +": "+ rep[i][j] +"\t");
            }
            System.out.println("");
        }
    }
}
