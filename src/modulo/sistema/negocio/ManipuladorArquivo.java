/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo.sistema.negocio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author augusto
 */
public class ManipuladorArquivo {

    public static void leitor(String path) throws IOException {
        BufferedReader buffRead = new BufferedReader(new FileReader(path));
        String linha = "";
        while (true) {
            if (linha != null) {
                System.out.println(linha);
            } else {
                break;
            }
            linha = buffRead.readLine();
        }
        buffRead.close();
    }

    public static void escritor(String path, String content, boolean append) throws IOException {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path, append));
        buffWrite.append(content);
        buffWrite.close();
    }
}
