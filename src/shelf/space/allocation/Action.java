package shelf.space.allocation;
import java.util.Random;
import java.util.List;

// Para Fazer:  
// Mudanças: 


// Detalhes das ações:
// specs[0]=shelf1, specs[1]=product1, specs[2]=shelf2, specs[3]=product2 
// tipo 0:  trocar uma prateleira por outra (shelf1 <===> shelf2)
// tipo 1:  trocar um produto por outro (product1 <===> product2)

public class Action {
    int type;
    int specs[] = new int[4];
    
    // ação tipo 0, troca uma prateleira por outra e retorna a nova solução já com o lucro atualizado
    public static Solution switchShelf(Solution s0, Action a, List<Product> p) {
        Shelf aux = new Shelf();
        Solution s1 = s0;
        int shelf1, shelf2;
        double auxWorth;
        a.type = 0;
        
        shelf1 = randomShelf(s0.Shelves.size());
        shelf2 = shelf1;
        while(shelf2 == shelf1) {
            shelf2 = randomShelf(s0.Shelves.size());
        }
        a.specs[0] = shelf1; 
        a.specs[2] = shelf2;
        
        auxWorth = s1.Shelves.get(shelf2).worth;
        s1.Shelves.get(shelf2).worth = s1.Shelves.get(shelf1).worth;
        s1.Shelves.get(shelf1).worth = auxWorth;
        
        aux = s1.Shelves.get(shelf2);
        s1.Shelves.set(shelf2, s1.Shelves.get(shelf1));
        s1.Shelves.set(shelf1, aux);
        Solution.getProfit(p, s1);
        
        return s1;
    }
    // ação tipo 1, troca um produto por outro e retorna a nova solução já com o lucro atualizado 
    public static Solution switchProduct(Solution s0, Action a, List<Product> p) {
        Solution s1 = s0;
        Product aux = new Product();
        boolean ok1 = false, ok2 = false, flag = false;
        double widthDif=0, width1=0, width2=0;
        int shelf1, shelf2;
        int product1=-1, product2=-1;
        int nFacingsP1, nFacingsP2, randFacingP1=0, randFacingP2=0, count=0, index1=-1, index2=-1;
        
        a.type = 1;
        shelf1 = randomShelf(s1.Shelves.size());
        shelf2 = randomShelf(s1.Shelves.size());
        while(!ok1) {
            product1 = randomProduct(p.size());
            nFacingsP1 = Shelf.getFacings(s1.Shelves.get(shelf1), p.get(product1));
            if(nFacingsP1 == 1) {
                ok1 = true;
            }
            else if(nFacingsP1 > 1) {
                randFacingP1 = randomFacing(nFacingsP1)+1;
                ok1 = true;
            }
        }
        if(shelf1 == shelf2) {
            while (!ok2) {
                product2 = randomProduct(p.size());
                nFacingsP2 = Shelf.getFacings(s1.Shelves.get(shelf2), p.get(product2));
                if(product2 == product1) {
                    if(nFacingsP2 > 1) {
                        randFacingP2 = randomFacing(nFacingsP2)+1;
                        while(randFacingP2 == randFacingP1) {
                            randFacingP2 = randomFacing(nFacingsP2)+1;
                        }
                        ok2 = true;
                    }
                }
                else {
                    if(nFacingsP2 == 1) {
                        ok2 = true;
                    }
                    else if(nFacingsP2 > 1) {
                        randFacingP2 = randomFacing(nFacingsP2)+1;
                        ok2 = true;
                    }
                }
            }
        }
        else {
            while(!ok2) {
                product2 = randomProduct(p.size());
                nFacingsP2 = Shelf.getFacings(s1.Shelves.get(shelf2), p.get(product2));
                if(nFacingsP2 == 1) {
                    ok2 = true;
                }
                else if(nFacingsP2 > 1) {
                    randFacingP2 = randomFacing(nFacingsP2)+1;
                    ok2 = true;
                }
            }
        }
        if(randFacingP1 == 0) 
            randFacingP1=1;
        if(randFacingP2 == 0)
            randFacingP2=1;
        
        a.specs[0]= shelf1;
        a.specs[1]= product1;
        a.specs[2]= shelf2;
        a.specs[3]= product2;
        
        // falta recolher os produtos com o facing certo
        for (int i = 0; i < s1.Shelves.get(shelf2).products.size(); i++) {
            if(s1.Shelves.get(shelf2).products.get(i).id == p.get(product2).id) {
                count++;
            }
            if(count == randFacingP2) {
                index2 = i;
                break;
            }
        }
        count = 0;
        aux = s1.Shelves.get(shelf2).products.get(index2);
        
        for (int i = 0; i < s1.Shelves.get(shelf1).products.size(); i++) {
            if(s1.Shelves.get(shelf1).products.get(i).id == p.get(product1).id) {
                count++;
            }
            if(count == randFacingP1) {
                index1 = i;
                break;
            }
        }
        // para atualizar a largura usada nas prateleiras
        width1 = s1.Shelves.get(index1).products.get(index1).width;
        width2 = s1.Shelves.get(index2).products.get(index2).width;
        if(width1 > width2) {
            widthDif = width1 - width2;
            s1.Shelves.get(shelf1).freeWidth += widthDif; 
            s1.Shelves.get(shelf1).usedWidth -= widthDif;
            s1.Shelves.get(shelf2).freeWidth -= widthDif; 
            s1.Shelves.get(shelf2).usedWidth += widthDif;
        }
        else if(width1 < width2) {
            widthDif = width2 - width1;
            s1.Shelves.get(shelf1).freeWidth -= widthDif; 
            s1.Shelves.get(shelf1).usedWidth += widthDif;
            s1.Shelves.get(shelf2).freeWidth += widthDif; 
            s1.Shelves.get(shelf2).usedWidth -= widthDif;
        }
        // fazer a troca final
        s1.Shelves.get(shelf2).products.set(index2, s1.Shelves.get(shelf1).products.get(index1));
        s1.Shelves.get(shelf1).products.set(index1, aux);
        Solution.getProfit(p, s1);
        
        return s1;
    }
    // retorna um número aleatório para uma prateleira
    public static int randomShelf(int nShelves) {
        Random rand = new Random();
        return rand.nextInt(nShelves);
    }
    // retorna um número aleatório para um produto
    public static int randomProduct(int nProducts) {
        Random rand = new Random();
        return rand.nextInt(nProducts);
    }
    // retorna um número aleatório de um facing
    public static int randomFacing(int nFacings) {
        Random rand = new Random();
        return rand.nextInt(nFacings);
    }
    // imprime uma ação, consoante o seu tipo
    public static void printAction (Action a) {
        switch(a.type) {
            case 0:
                System.out.println("Ação tipo "+ a.type +": s"+ (a.specs[0]+1) +" <===> s"+ (a.specs[2]+1));
                break;
            case 1:
                System.out.println("Ação tipo "+ a.type +": s"+ (a.specs[0]+1) +"p"+ (a.specs[1]+1) +" <===> s"+ (a.specs[2]+1) +"p"+ (a.specs[3]+1));
                break;
        }
    }
}