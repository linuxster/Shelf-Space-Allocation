package shelf.space.allocation;
import java.util.Random;
import java.util.List;


// Para Fazer:  Atualizar a função printAction

public class Action {
    int shelf, product1, product2;
    int tabuCount;
    
    // retorna um número aleatório para uma prateleira
    public static int randomShelf(int nShelves) {
        Random rand = new Random();
        return rand.nextInt(nShelves);
    }
    //
    public static void updateTabuList(List<Action> aList) {
        if (aList.size() > 0) {
            for (int i = (aList.size()-1); i >= 0 ; i--) {
                if(aList.get(i).tabuCount == 0) {
                    aList.remove(i);
                    i = aList.size();
                }
            }
            for (int i = 0; i < aList.size(); i++) {
                aList.get(i).tabuCount--;
            }
        }
    }
    // retorna um número aleatório para um produto
    public static int randomProduct() {
        Random rand = new Random();
        //return rand.nextInt(5);
        int n;
        n = rand.nextInt(100);
        if      (n < 5)
            return 3;
        else if (n < 13)
            return 1;
        else if (n < 29)
            return 0;
        else if (n < 47)
            return 2;
        else if (n < 70)
            return 4;
        else if (n < 100)
            return 5;
        else 
            return -1;
    }
    // retorna um número aleatório de um facing
    public static int randomFacing(int nFacings) {
        Random rand = new Random();
        return rand.nextInt(nFacings);
    }
    // retorna um número aleatório de uma ação
    public static int randomAction() {
        int index=-1;
        Random rand = new Random();
        int r = rand.nextInt(100);
        if(r < 50)
            return 0;
        else if (r >= 50 && r < 100)
            return 1;
        
        return index;
    }
    // copia uma ação para outra
    public static void copyAction(Action a1, Action a2) {
        a1.shelf = a2.shelf;
        a1.product1 = a2.product1;
        a1.product2 = a2.product2;
    }
// imprime uma ação, consoante o seu tipo
    public static void printAction (Action a) {
        if (a.shelf == -1)
            System.out.println("Remover o produto " + (a.product1+1));
        else
            System.out.println("Prateleira "+ (a.shelf +1) + ": trocar produto " + (a.product1+1) +" pelo produto "+ (a.product2+1) +".");
    }
}

/* COMPARAR AO ESCOLHER O PRODUTO 2 SE É IGUAL AO PRODUTO 1
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
*/
/* PERMITIR TROCAS NA MESMA PRATELEIRA
    if(shelf1 == shelf2) {
            while (!ok2) {
                product2 = randomProduct(p.size());
                if ( product2 == product1) 
                    continue;
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
        else {
            while(!ok2) {
                product2 = randomProduct(p.size());
                if (product2 == product1) 
                    continue;
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
*/