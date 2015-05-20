package shelf.space.allocation;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Collections;

// Para Fazer:  
//      1. Escrever a função isFull
// Mudanças:    removido o Product.used das funções


public class Shelf {
    int id;
    double freeWidth, usedWidth, worth;
    List<Product> products = new ArrayList<Product>();
    
    // Apenas inicializa uma lista de prateleiras com os valores iniciais dados pela instância
    public static void initializeShelfList (List<Shelf> shelfList, Shelf[] s) {
        
        for (int i = 0; i < s.length; i++) {
            shelfList.add(s[i]);
        }
    }
    // Adiciona um produto a uma prateleira, e realiza operações associadas
    public static void addProduct(Shelf s, Product p) {
        s.products.add(p);
        s.usedWidth += p.width;
        s.freeWidth -= p.width;
    }
    // Remove um produto de uma prateleira, e realiza operações associadas
    public static void removeProduct(Shelf s, Product p) {
        s.products.remove(p);
        s.usedWidth -= p.width;
        s.freeWidth += p.width;
    }
    // Retorna o número de Facings do produto, numa determinada prateleira
    public static int getFacings(Shelf s, Product p) {
        int n=0;
        for (int j = 0; j <s.products.size(); j++) {
            if(s.products.get(j).id == p.id) 
                n++;
        } 
        return n;
    }
    // Retorna "true" se não couber mais nenhum produto na prateleira
    public static boolean isFull(Shelf s) {
        // Retorna "true" se a largura disponível na prateleira for menor que o produto menos largo
        
        return false;
    }
}
