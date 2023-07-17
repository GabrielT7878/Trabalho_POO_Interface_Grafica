package pdv.dominio;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import pdv.TestePDV;
import pdv.dominio.excecoes.DescricaoProdutoInexistente;
import pdv.dominio.pagamento.Operadora;

public class Registradora {
    private Tab tabRegistradora;
    private String id;
    private List<Venda> vendas;
    private CatalogoProdutos catalogo = new CatalogoProdutos();
    @FXML
    private ListView listaVendas;
    @FXML
    private Button botao;
    @FXML
    public void janelaNovaVenda() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(TestePDV.class.getResource("janelaNovaVenda.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 200, 200);
        stage.setTitle("Supermercado Preço Bão");
        stage.setScene(scene);
        stage.show();
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
