package ctr;

import view.TelaCompraTickets;
import view.TelaComprar;
import view.TelaGerenciarTickets;
import view.TelaVisualizacao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Douglas
 */
public class ControleInterface {

    public void CarregaProxTela(javax.swing.JFrame telaInicial, javax.swing.JFrame telaFinal)
    {
        telaInicial.setVisible(false);
        
    }
public void IniciaCompraTickets(TelaGerenciarTickets telai)
    {
        TelaCompraTickets telinha  = (new TelaCompraTickets());
        telinha.setVisible(true);
        telai.setVisible(false);
    }

    public void IniciaVisTickets(TelaGerenciarTickets telai) {
        TelaVisualizacao telinha  = (new TelaVisualizacao());
        telinha.setVisible(true);
        telai.setVisible(false);
    }
    
    
}
