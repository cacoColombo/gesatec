/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo.sistema.negocio;

import java.awt.Component;
import java.io.File;
import java.net.URI;
import java.sql.Timestamp;
import javax.swing.JOptionPane;
import modulo.administrativo.negocio.UserAccount;

/**
 *
 * @author augusto
 */
public class SOptionPane {
    
    public static void showMessageDialog(Component parentComponent, Object message, String title, int messageType) {
        
        // Somente guardar logs de erros.
        if ( messageType == JOptionPane.ERROR_MESSAGE ) {
            try {
                Throwable erro = (Throwable) message;
                Timestamp dataHora = new Timestamp(System.currentTimeMillis());

                UserAccount usuario = UsuarioLogado.getInstance().getUsuarioLogado();
                String dadosUsuario = (usuario != null) ? usuario.getName() + " - " + usuario.getLogin() : "(Sem usuário definido)";
                String erroMessage = (erro.getMessage() != null) ? erro.getMessage() : erro.toString();                
                message = erroMessage;

                String content = "\nEXCEÇÃO DETECTADA:" +
                                 "\nDATA/HORA: " + dataHora +
                                 "\nUSUÁRIO: " + dadosUsuario +
                                 "\nTIPO DE MESAGEM: erro" +
                                 "\nMENSAGEM: " + erroMessage.replace("\n", " ") +
                                 "\nRASTREAMENTO DE PILHA (Stack Trace):";            

                for (StackTraceElement ste : erro.getStackTrace()) {
                    content += "\n" + ste;
                }

                content += "\n ";
                
                URI uri = SOptionPane.class.getResource("/META-INF/logs").toURI();
                ManipuladorArquivo.escritor(uri.getPath() + "/gesatec.log", content, true);
                System.out.println(content);
                System.out.println("Para mais detalhes, acessar os registros de logs em '" + uri.getPath() + "/gesatec.log'.");
                
            } catch (Exception err) {
                JOptionPane.showMessageDialog(null, "Erro ao salvar log: " + err, "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        JOptionPane.showMessageDialog(parentComponent, message, title, messageType);
    }
    
}
