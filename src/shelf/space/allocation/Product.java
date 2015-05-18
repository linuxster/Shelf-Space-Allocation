package shelf.space.allocation;


// TODO ATUALIZAR A FÓRMULA DO NÚMERO DE FRENTES

public class Product {
    int id, used;
    double price, salesValue, elasticity, width;
    
    
    public static double valueFacing(Product p, int n) {
        double value;
        double iteration[] = new double[6];
        
        for (int i = 0; i < 6; i++) {
            iteration[i] = p.price*p.salesValue*Math.pow(i+1, p.elasticity);
            //System.out.println("Interação "+ i + ": " + iteration[i]);
        }
        return iteration[n-1];
    } 
    public static double valueIteration(Product p, int n) {
        double value;
        double iteration[] = new double[6];
        
        for (int i = 0; i < 6; i++) {
            iteration[i] = p.price*p.salesValue*Math.pow(i+1, p.elasticity);
            //System.out.println("Interação "+ i + ": " + iteration[i]);
        }
        if (n == 1)
            return iteration[n-1];
        else
            return iteration[n-1]-iteration[n-2];
    }
    
    public static Product getProduct(Product p[], int n){
        return p[n-1];
    }
    public static int getID(Product p){
        return p.id;
    }
    public static int setID(Product p, int n){
        p.id = n;
        return 1;
    }
    public static double getPrice(Product p){
        return p.price;
    }
    public static double setPrice(Product p, double n){
        p.price = n;
        return 1;
    }
    public static double getSales(Product p){
        return p.salesValue;
    }
    public static double setSales(Product p, double n){
        p.salesValue = n;
        return 1;
    }
    public static double getElasticity(Product p){
        return p.elasticity;
    }
    public static double setElasticity(Product p, double n){
        p.elasticity = n;
        return 1;
    }
    public static double getWidth(Product p){
        return p.width;
    }
    public static double setWidth(Product p, double n){
        p.width = n;
        return 1;
    }
}

