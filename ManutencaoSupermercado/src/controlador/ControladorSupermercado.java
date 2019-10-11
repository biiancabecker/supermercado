/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import tela.manutencao.ManutencaoSupermercado;
import dao.DaoSupermercado;
import javax.swing.JOptionPane;
import modelo.Supermercado;
import tela.manutencao.ManutencaoSupermercado;

import java.time.LocalDate; 
import java.time.format.DateTimeFormatter;

import java.util.List;

import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author bianc
 */
public class ControladorSupermercado {

    public static void inserir(ManutencaoSupermercado man){
        Supermercado objeto = new Supermercado();
        objeto.setNomefantasia(man.jtfNomefantasia.getText());
        objeto.setRazaosocial(man.jtfRazaosocial.getText());
        objeto.setFundacao(LocalDate.parse(man.jtfFundacao.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        objeto.setNrfuncionarios(Integer.parseInt(man.jtfNrfuncionarios.getText()));
        objeto.setValorbolsa(Double.parseDouble(man.jtfValorbolsa.getText()));
        
        boolean resultado = DaoSupermercado.inserir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
            if (man.listagem != null) {
     atualizarTabela(man.listagem.tabela); //atualizar a tabela da listagem
}
man.dispose();//fechar a tela da manutenção
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
}

    public static void alterar(ManutencaoSupermercado man){
        Supermercado objeto = new Supermercado();
        //definir todos os atributos
        objeto.setCodigo(Integer.parseInt(man.jtfCodigo.getText()));
        objeto.setNomefantasia(man.jtfNomefantasia.getText());
        objeto.setRazaosocial(man.jtfRazaosocial.getText());
        objeto.setFundacao(LocalDate.parse(man.jtfFundacao.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        objeto.setNrfuncionarios(Integer.parseInt(man.jtfNrfuncionarios.getText()));
        objeto.setValorbolsa(Double.parseDouble(man.jtfValorbolsa.getText()));
        
        boolean resultado = DaoSupermercado.alterar(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
            if (man.listagem != null) {
     atualizarTabela(man.listagem.tabela); //atualizar a tabela da listagem
}
man.dispose();//fechar a tela da manutenção
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }

    public static void excluir(ManutencaoSupermercado man){
        Supermercado objeto = new Supermercado();
        objeto.setCodigo(Integer.parseInt(man.jtfCodigo.getText())); //só precisa definir a chave primeira
        
        boolean resultado = DaoSupermercado.excluir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
            if (man.listagem != null) {
     atualizarTabela(man.listagem.tabela); //atualizar a tabela da listagem
}
man.dispose();//fechar a tela da manutenção
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }
    
    public static void atualizarTabela(JTable tabela) {
        DefaultTableModel modelo = new DefaultTableModel();
        //definindo o cabeçalho da tabela
        modelo.addColumn("Codigo");
        modelo.addColumn("Nomefantasia");
        modelo.addColumn("Razaosocial");
        modelo.addColumn("Fundacao");
        modelo.addColumn("Nrfuncionarios");
        modelo.addColumn("Valorbolsa");
        List<Supermercado> resultados = DaoSupermercado.consultar();
        for (Supermercado objeto : resultados) {
            Vector linha = new Vector();
            
            //definindo o conteúdo da tabela
            linha.add(objeto.getCodigo());
            linha.add(objeto.getNomefantasia());
            linha.add(objeto.getRazaosocial());
            linha.add(objeto.getFundacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            linha.add(objeto.getNrfuncionarios());
            linha.add(objeto.getValorbolsa());
            
            modelo.addRow(linha); //adicionando a linha na tabela
        }
        tabela.setModel(modelo);
    }
    
    public static void atualizaCampos(ManutencaoSupermercado man, int pk){ 
        Supermercado objeto = DaoSupermercado.consultar(pk);
        //Definindo os valores do campo na tela (um para cada atributo/campo)
        man.jtfCodigo.setText(objeto.getCodigo().toString());
        man.jtfNomefantasia.setText(objeto.getNomefantasia());
        man.jtfRazaosocial.setText(objeto.getRazaosocial());
        man.jtfFundacao.setText(objeto.getFundacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        man.jtfNrfuncionarios.setText(objeto.getNrfuncionarios().toString());
        man.jtfValorbolsa.setText(objeto.getValorbolsa().toString());
        
        man.jtfCodigo.setEnabled(false); //desabilitando o campo código
        man.btnAdicionar.setEnabled(false); //desabilitando o botão adicionar
    }
    
}
