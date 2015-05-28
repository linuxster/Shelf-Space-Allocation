package shelf.space.allocation;
import java.util.Random;
import java.util.List;


// Para Fazer:  Atualizar a função printAction

// Detalhes das ações:
// specs[0]=shelf1, specs[1]=product1, specs[2]=shelf2, specs[3]=product2 
// tipo 0:  trocar uma prateleira por outra (shelf1 <===> shelf2)
// tipo 1:  trocar um produto por outro (product1 <===> product2)

public class Action {
    int type;
    int specs[] = new int[4];
    
    
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