import java.time.LocalDate;
import java.util.LinkedList;

public class Pedido {
    private static int contadorId;
    private int id;
    private LocalDate data;
    private LinkedList<Pizza> listaPizzas;
    private boolean aberto;

    static {
        contadorId = 0;
    }

    public Pedido(){
        contadorId++;
        id = contadorId;
        listaPizzas = new LinkedList<>();
        data = LocalDate.now();
        aberto = true;
    }

    private boolean podeAdicionar(){
        return aberto;
    }

    public int addPizza(Pizza pizza){
        if(pizza != null && podeAdicionar()){
            listaPizzas.add(pizza);
        }
        return listaPizzas.size();
    }

    public void fecharPedido(){
        aberto = false;
    }

    public double valorPedido(){
        double valor_total = 0;
        for (Pizza pizza: listaPizzas){
            valor_total += pizza.valorFinal();
        }
        return valor_total;
    }

    public StringBuilder gerarRelatorio(){
        StringBuilder relatorio = new StringBuilder();
        relatorio.append(String.format("=== PEDIDO Nº%d ===\n\n",this.id));
        for (Pizza pizza: listaPizzas){
            relatorio.append(pizza.gerarCupom()).append("\n\n");
        }
        relatorio.append(String.format("O valor total do seu pedido é de: R$%.2f", valorPedido()));
        return relatorio;
    }
}
