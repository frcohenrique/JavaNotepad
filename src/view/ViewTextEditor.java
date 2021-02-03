package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;


public class ViewTextEditor extends Component {
    private JPanel notePanel;
    private JTextArea textArea1;
    private JMenuBar MenuBar;
    private JMenu MyMenu;
    private JMenu MyMenu2;
    private JMenuItem MOpen;
    private JMenuItem MSave;
    private JMenuItem MNewPage;
    private JMenuItem MCorTexto;
    private JMenuItem MCorBackground;

    public ViewTextEditor() {


        //Variavel JTextArea
        String jTextArea;
        jTextArea = textArea1.getText();

        /**
         * Botão ABRIR arquivo
         */
        MOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Abrir();
                // javax.swing.JOptionPane.showMessageDialog(null, "Open");
            }
        });


        /**
         * Botão SALVAR arquivo.
         */
        MSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Salvar();
            }
        });


        /**
         * Botão adicionar nova pagina
         */
        MNewPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                NewPagePanel();

            }
        });


        /**
         * Escolher cor de texto nova
         */
        MCorTexto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TextColor();
            }
        });


        /**
         * Botao Alterar cor de fundo
         */
        MCorBackground.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BackgroundColor();
            }
        });
    }


    /**
     * Método Chamar PANEL
     */
    public static void CallPanel() {

        JFrame frame = new JFrame();

        frame.setContentPane(new ViewTextEditor().notePanel);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setTitle("Editor de texto");
        frame.setVisible(true);



    }


    /**
     * Método abrir nova pagina
      */
    public static void NewPagePanel(){
        JFrame frame = new JFrame();


        frame.setContentPane(new ViewTextEditor().notePanel);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Nova Página");
        frame.setVisible(true);
    }


    /**
     * Metodo Salvar arquivo (exportar)
     */
    public void Salvar() {
/**
 String tempTexto = textArea1.getText();
 try {
 //editar depois.
 String finalTxt = ".txt";
 String arquivoText = "Arquivo";
 String nomeArquivo = arquivoText + finalTxt;
 PrintWriter salvarArq = new PrintWriter(nomeArquivo);

 // salvarArq.print(textArea1.getText());
 // salvarArq.close();
 File file = new File(nomeArquivo);
 if (file.exists()){
 int optionPane = JOptionPane.showConfirmDialog(
 null, "Voce gostaria de substituir o arquivo?",
 "AVISO!!!!!",
 JOptionPane.YES_NO_OPTION);
 if (optionPane == JOptionPane.YES_OPTION){
 salvarArq.print(textArea1.getText());
 salvarArq.close();
 }
 else{

 }
 }
 } catch (FileNotFoundException ex) {
 Logger.getLogger(ViewTextEditor.class.getName()).log(Level.SEVERE, null, ex);
 }
 */
        JFileChooser chooser = new JFileChooser(); // abre a interface de escolher arquivo
        int option = chooser.showSaveDialog(this); // mostra a opção de salvar e tals
        if (option == JFileChooser.APPROVE_OPTION) {   //se voce apertou o botao de salvar/ok
            try {
                BufferedWriter buf = new BufferedWriter(new FileWriter(chooser.getSelectedFile().getAbsolutePath()));
                buf.write(textArea1.getText());
                buf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Método Abrir arquivo (txt)
     */
    public void Abrir(){
        JFileChooser chooser = new JFileChooser(); // File chooser (escolher arquivo)
        chooser.setFileFilter(new Filter());
        int option = chooser.showOpenDialog(this); // abre as opções (pasta)
        if(option == JFileChooser.APPROVE_OPTION) {
            try {
                Scanner scanner = new Scanner(chooser.getSelectedFile()); // escolher o arquivo
                while(scanner.hasNext()) { // When the scanner still has stuff to read, do something
                    String data = scanner.nextLine(); // ler o que tem no arquivo
                    textArea1.setText(data); // pega tudo que tem no arquivo (texto) e joga no notepad
                }
                scanner.close(); // (fecha o scanner)

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * filtra para aceitar apenas .txt ou .java
     */
    class Filter extends javax.swing.filechooser.FileFilter implements FileFilter {
        public boolean accept(File file) {
            return file.getName().endsWith(".txt") || file.getName().endsWith(".java");
        }

        @Override
        public String getDescription() {
            return "Text File (.txt)";
        }
    }


    /**
     * Método mudar cor do texto
     */
    public void TextColor(){
        Color textColor = JColorChooser.showDialog(null, "Escolha uma cor", Color.white);
       // textArea1.setBackground(c);
        textArea1.setForeground(textColor);
    }


    /**
     * Método mudar cor de fundo
     */
    public void BackgroundColor(){
        Color backGround = JColorChooser.showDialog(null, "Escolha a cor de fundo", Color.white);
        textArea1.setBackground(backGround);
    }


}

