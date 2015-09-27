package modulo.sistema.visao;

import com.alee.laf.WebLookAndFeel;
import java.awt.Image;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author augusto
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); // Tema do OS
            UIManager.setLookAndFeel(new WebLookAndFeel ()); // Tema do WebLaf
            
            // Cria as tabelas na base de dados, com base nas entidades de negócio.
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("crudHibernatePU");
            factory.close();            
            
            //Inicia o sistema.
            SistemaVisao sistema = new SistemaVisao();
            sistema.setExtendedState(sistema.MAXIMIZED_BOTH);

            /**
            // Seta ícone principal da aplicação.
            Application application = Application.getApplication();
            Image image = new ImageIcon(sistema.getClass().getResource("/publico/imagens/favicon.ico")).getImage();
            application.setDockIconImage(image);
            */
            
            sistema.setVisible(true);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
