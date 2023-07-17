package pdv.dominio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import pdv.dominio.excecoes.DescricaoProdutoInexistente;
import pdv.dominio.pagamento.Operadora;

public class Registradora {
    private String id;
    private List<Venda> vendas;
    private CatalogoProdutos catalogo = new CatalogoProdutos();
    @FXML
    private ListView listaVendas;
    @FXML
    private Button botao;
    @FXML
    public void initialize(){
        String venda = "01";
        listaVendas.getItems().add(venda);
    }
    public void novaGuia(){
        
    }
    public Registradora(){

    }

    public Registradora(String id) {
        this.id = id;
        this.vendas = new ArrayList<>();
    }

    public void criarNovaVenda() {
    	Venda venda = new Venda(LocalDateTime.now());
        vendas.add(venda);
    }

    public void entrarItem(String id, int quantidade) throws DescricaoProdutoInexistente {
        Venda venda = null;
        DescricaoProduto descricaoProduto = getCatalogo().getDescricaoProduto(id);
        venda = this.getVendaCorrente();
        venda.criarItemVenda(descricaoProduto, quantidade);
    }

    public void finalizarVenda() {
        this.getVendaCorrente().setEstaCompleta(true);
    }

    public double fazerPagamento(double quantiaFornecida) {
        return this.getVendaCorrente().fazerPagamento(quantiaFornecida); // retorna o troco
    }

    public void fazerPagamento(double quantiaFornecida, String banco) {
        this.getVendaCorrente().fazerPagamento(quantiaFornecida, banco);
    }

    public void fazerPagamento(double quantiaFornecida, Operadora operadora, int quantidadeParcelas, TipoCalculadora tipoCalculadora) {
        this.getVendaCorrente().fazerPagamento(quantiaFornecida, operadora, quantidadeParcelas, tipoCalculadora);
    }

    public Venda getVendaCorrente() {
        return vendas.get(vendas.size() -1);
    }

    public CatalogoProdutos getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(CatalogoProdutos catalogo) {
        this.catalogo = catalogo;
    }

    public String getId() {
        return id;
    }
}
