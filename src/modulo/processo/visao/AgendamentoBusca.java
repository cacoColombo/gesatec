/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo.processo.visao;

import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import modulo.sistema.visao.Busca;

/**
 *
 * @author augusto
 */
public class AgendamentoBusca extends Busca {
    
    public static AgendamentoFormulario form;
    
    public AgendamentoBusca() {
        this.setName("agenda");
        super.initComponents();
        setTitle("Agendamento");
    }

    @Override
    public DefaultTableModel construirGrid() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void atualizarGrid(int selecionar, List<Object> registros) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void botaoNovoActionPerformed(ActionEvent evt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void botaoEditarActionPerformed(ActionEvent evt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void botaoExcluirActionPerformed(ActionEvent evt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void botaoBuscarActionPerformed(ActionEvent evt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
