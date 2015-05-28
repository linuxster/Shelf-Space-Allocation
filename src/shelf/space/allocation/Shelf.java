package shelf.space.allocation;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Collections;


// Para Fazer:  

public class Shelf {
    int id;
    double freeWidth, usedWidth, worth;
    List<Product> products = new ArrayList<Product>();
    
    // apenas inicializa uma lista de prateleiras com os valores iniciais dados pela instância
    public static void initializeShelfList (List<Shelf> shelfList, Shelf[] s) {
        
        for (int i = 0; i < s.length; i++) {
            shelfList.add(s[i]);
        }
    }
    // retorna o lucro associado a uma prateleira
    public static double getShelfProfit(Shelf s, List<Product> p) {
        double shelfProfit=0;
        
        
        return shelfProfit;
    }
    // adiciona um produto a uma prateleira e realiza operações associadas
    public static void addProduct(Shelf s, Product p) {
        while(s.freeWidth >= p.width) {
            s.products.add(p);
            s.usedWidth += p.width;
            s.freeWidth -= p.width;
        }
    }
    // remove um produto de uma prateleira e realiza operações associadas
    public static void removeProduct(Shelf s, Product p) {
        double totalSize=0;
        while(Shelf.getFacings(s, p) > 0) {
            s.products.remove(p);
            s.usedWidth -= p.width;
            s.freeWidth += p.width;
        }
        
    }
    // retorna o número de Facings do produto numa determinada prateleira
    public static int getFacings(Shelf s, Product p) {
        int n=0;
        for (int j = 0; j <s.products.size(); j++) {
            if(s.products.get(j).id == p.id) 
                n++;
        } 
        return n;
    }
    // retorna "true" se não couber mais nenhum produto na prateleira
    public static boolean isFull(Shelf s, List<Product> p) {
        double min=100;
        for (int i = 0; i < p.size(); i++) {
            if(p.get(i).width < min)
                min = p.get(i).width;
        }
        if(s.freeWidth < min)
            return true;
        else
            return false;
    }
    // retorna "true" se uma prateleira estiver cheia demais
    public static boolean isOverLimit(Shelf s) {
        if(s.freeWidth < 0) {
            return true;
        }
        else   
            return false;
    }
    // SÓ PARA SOLUÇÃO INICIAL, adiciona um produto a uma prateleira e realiza operações associadas
    public static void addInitialProduct(Shelf s, Product p) {
        s.products.add(p);
        s.usedWidth += p.width;
        s.freeWidth -= p.width;
    }
}
