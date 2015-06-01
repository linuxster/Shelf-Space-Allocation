package shelf.space.allocation;
import java.util.List;  


// Para Fazer:      

public class Product {
    int id;
    double price, salesValue, elasticity, width;
    
    // esta função, recebendo um vetor "output", deverá colocar nele os "id" dos produtos, mas de forma ordenada 
    // (0 mais lucrativo, último índice o menos lucrativo)
    public static void orderProductsProfit(List<Product> products, List<Integer> output) {
            for (int i = 0; i < products.size(); i++) {
            double temp=0, max=0;
            int idMax=0;
            for (int j = 1; j < (1+products.size()); j++) {
                if(output.contains(j))
                    continue;
                temp = Product.valueFacing(products.get(j-1), 1);
                if(temp > max) {
                    max = temp;
                    idMax=products.get(j-1).id;
                }
            }
            output.add(Product.getProduct(products, idMax).id);
            max = 0;
        }
        
    }
    // retorna o valor do lucro associado a ter o produto P com n frentes numa prateleira
    public static double valueFacing(Product p, int n) {
        double iteration[] = new double[6];
        
        for (int i = 0; i < 6; i++) {
            iteration[i] = p.price*p.salesValue*Math.pow(i+1, p.elasticity);
            //System.out.println("Interação "+ i + ": " + iteration[i]);
        }
        
        return iteration[n-1];
    } 
    // retorna o lucro associado a ter o produto P n iterações, quando comparado com já termos n-1 iterações na prateleira
    public static double valueIteration(Product p, int n) {
        int maxFacings = 6;
        double value;
        double iteration[] = new double[maxFacings];
        
        for (int i = 0; i < maxFacings; i++) {
            iteration[i] = p.price*p.salesValue*Math.pow(i+1, p.elasticity);
            //System.out.println("Interação "+ i + ": " + iteration[i]);
        }
        if (n == 1)
            return iteration[n-1];
        else
            return iteration[n-1]-iteration[n-2];
    } 
    // retorna o Produto que corresponde ao ID "n"
    public static Product getProduct(List<Product> products, int n) {
        for (int i = 0; i < products.size(); i++) {
            if(products.get(i).id == n)
                return products.get(i);
        }
        return null;
    }
    //
    public static boolean isUsed(List<Product> P, Solution s, int index) {
        int numberFacings = 0;
        for (int j = 0; j < s.Shelves.size(); j++) {
            numberFacings += Shelf.getFacings(s.Shelves.get(j), P.get(index));
        }
        if(numberFacings == 0)
            return false;
        else 
            return true;
    
    }
}

