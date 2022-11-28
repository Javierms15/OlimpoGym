/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.aspose.cells.FileFormatType;
import com.aspose.cells.LoadOptions;
import com.aspose.cells.SaveFormat;
import com.aspose.cells.Workbook;
import com.opencsv.CSVWriter;
import java.awt.Component;
import java.awt.FileDialog;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author javie
 * Desarrollado por Javier Martín Sendra.
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("../olimpogym/logo.jpeg")).getImage());
        ImageIcon f=new ImageIcon(getClass().getResource("../olimpogym/OlimpoN.jpeg"));
        Image image=f.getImage();
        Image imagenEscala=image.getScaledInstance(jLabel4.getWidth(), jLabel4.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon result=new ImageIcon(imagenEscala);
        this.jLabel4.setIcon(result);
        jButton1.setIcon(new ImageIcon(getClass().getResource("../olimpogym/lupa.png")));
        jButton4.setIcon(new ImageIcon(getClass().getResource("../olimpogym/papelera.png")));
        jButton5.setIcon(new ImageIcon(getClass().getResource("../olimpogym/excel.png")));
        jButton2.setIcon(new ImageIcon(getClass().getResource("../olimpogym/nuevo.png")));
        jButton3.setIcon(new ImageIcon(getClass().getResource("../olimpogym/editar.png")));
        Connect();
        mostrarAllClientes();
        
    }
    
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    public void Disconnect(){
        try {
            rs.close();
            pst.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void Connect(){
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try  {
                con=DriverManager.getConnection("jdbc:mysql://localhost/pruebaparcial", "root", "root");
            } catch (SQLException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    public void mostrarAllClientes(){
        
        int q;
        try {
            pst=con.prepareStatement("SELECT * FROM cliente");
            rs=pst.executeQuery();
            ResultSetMetaData rss=rs.getMetaData();
            q=rss.getColumnCount();
            
            DefaultTableModel df= (DefaultTableModel)jTable1.getModel();
            df.setRowCount(0);
            SimpleDateFormat f=new SimpleDateFormat("dd-MM-yyyy");
            while(rs.next()){
                Vector v2=new Vector();
                for(int i =1; i<=q;i++){
                    v2.add(rs.getInt("idcliente"));
                    v2.add(rs.getString("Nombre"));
                    v2.add(rs.getString("Apellidos"));
                    v2.add(rs.getString("Estatus"));
                    v2.add(rs.getInt("PrecioMatricula"));
                    v2.add(rs.getInt("Telefono"));
                    v2.add(rs.getString("DNI"));
                    v2.add(rs.getString("Direccion"));
                    Timestamp timestamp = rs.getTimestamp("fechaInicio");   
                    java.util.Date fechaInicio = new java.util.Date(timestamp.getTime());
                    v2.add(f.format(fechaInicio));
                    Timestamp timestamp1 = rs.getTimestamp("fechaFin");   
                    java.util.Date fechaFin = new java.util.Date(timestamp1.getTime());
                    v2.add(f.format(fechaFin));
                   
                }
                df.addRow(v2);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jButton6 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("OLIMPO NGR GYM");
        setBackground(new java.awt.Color(0, 0, 0));

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.black, null));

        jTextField1.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.black, null));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "IdCliente", "Nombre", "Apellidos", "Estatus", "PrecioMatricula", "Telefono", "DNI", "Direccion" }));
        jComboBox1.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.black, null));

        jButton1.setBackground(new java.awt.Color(255, 51, 51));
        jButton1.setText("Buscar");
        jButton1.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.black, null));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jDateChooser1.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.black, null));

        jDateChooser2.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.black, null));

        jButton6.setBackground(new java.awt.Color(255, 51, 51));
        jButton6.setText("Filtrar");
        jButton6.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.black, null));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Fecha de:");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Fecha hasta:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 310, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(110, 110, 110))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(4, 4, 4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), null));

        jButton2.setBackground(new java.awt.Color(255, 51, 51));
        jButton2.setText("Nuevo cliente");
        jButton2.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.black, null));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 51, 51));
        jButton3.setText("Editar cliente");
        jButton3.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.black, null));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 51, 51));
        jButton4.setText("Eliminar cliente");
        jButton4.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.black, null));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(255, 51, 51));
        jButton5.setText("Exportar a Excel");
        jButton5.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.black, null));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(171, 171, 171)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 280, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(150, 150, 150)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jTable1.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.black, null));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "IdCliente", "Nombre", "Apellidos", "Estatus", "PrecioMatricula", "Telefono", "DNI", "Direccion", "fechaInicio", "fechaFin"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel5.setBackground(new java.awt.Color(0, 0, 0));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.black, null));

        jLabel1.setFont(new java.awt.Font("Malgun Gothic Semilight", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("OLIMPO NGR GYM");

        jLabel4.setText("jLabel4");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(452, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(394, 394, 394)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                    .addComponent(jLabel1))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1361, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 769, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:

        String filename = "E:\\Excel exportado.csv";
        /* JFileChooser fc=new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        Component parent = null;
        int returnVal = fc.showSaveDialog(parent);
        if (returnVal == JFileChooser.APPROVE_OPTION) {

            // obtener ruta
            String selectPath = fc.getSelectedFile().getPath();
            System.out.println ("El directorio que elija es:" + selectPath);
        }
        */
        FileDialog dialogoArchivo;
        dialogoArchivo = new FileDialog(this, "Seleccione lugar de guardado",FileDialog.SAVE);
        dialogoArchivo.setVisible(true);
        /* Validar que se haya Seleccionado un Archivo*/
        String directorio = dialogoArchivo.getDirectory();
        String nombreArchivo =dialogoArchivo.getFile();
        String rutatotal = directorio + nombreArchivo;
        filename=rutatotal;

        try {
            FileWriter fw=new FileWriter(filename);
            //CSVWriter writer=new CSVWriter(fw);
            pst=con.prepareStatement("SELECT * FROM cliente");
            rs=pst.executeQuery();
            fw.append("NOMBRE, APELLIDOS, ESTATUS, TELEFONO, FECHAFIN");
            fw.append("\n");
            SimpleDateFormat f=new SimpleDateFormat("dd-MM-yyyy");
            // List<String[]> columnas = new ArrayList<String[]>();
            //String[] c={"NOMBRE","APELLIDOS","ESTATUS","TELEFONO","FECHAFIN"};
            //columnas.add(c);
            // writer.writeAll(columnas);
            // List<String[]> data = new ArrayList<String[]>();

            while(rs.next()){

                fw.append(rs.getString(2));
                fw.append(", ");
                fw.append(rs.getString(3));
                fw.append(", ");
                fw.append(rs.getString(4));
                fw.append(", ");
                fw.append(String.valueOf( rs.getInt(6)));
                fw.append(", ");
                fw.append(String.valueOf(f.format(rs.getDate(10))));
                fw.append("\n");

                //String[] user={rs.getString(2),rs.getString(3),String.valueOf( rs.getInt(6)),String.valueOf(f.format(rs.getDate(10)))};
                //data.add(user);

            }
            //writer.writeAll(data);

            JOptionPane.showMessageDialog(this,"Archivo Excel generado con éxito");
            fw.flush();
            fw.close();
            // Opening CSV Files
            // Creating CSV LoadOptions object
            //LoadOptions loadOptions = new LoadOptions(FileFormatType.CSV);
            // Creating an Workbook object with CSV file path and the loadOptions
            // object
            //Workbook workbook = new Workbook("E:\\Excel exportado.csv", loadOptions);
            //workbook.save("E:\\Excel exportado.xlsx" , SaveFormat.XLSX);

        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        int pregunta=JOptionPane.showConfirmDialog(this, "¿Desea eliminar este cliente?","CONFIRMACIÓN",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
        if(pregunta==0){
            try {
                // TODO add your handling code here:

                DefaultTableModel df= (DefaultTableModel)jTable1.getModel();
                int fila=jTable1.getSelectedRow();
                int id=(int) jTable1.getValueAt(fila, 0);
                pst=con.prepareStatement("DELETE FROM cliente WHERE idcliente =?");
                pst.setInt(1,id);
                int k=pst.executeUpdate();
                df.removeRow(fila);
                if(k==1){
                    JOptionPane.showMessageDialog(this, "Cliente eliminado con éxito!");
                    mostrarAllClientes();

                }else{
                    JOptionPane.showMessageDialog(this, "Fallo al eliminar el cliente!");
                }

            } catch (SQLException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            // TODO add your handling code here:
            EditarCliente datos=new EditarCliente();
            DefaultTableModel df= (DefaultTableModel)jTable1.getModel();
            int fila=jTable1.getSelectedRow();
            int id=(int) jTable1.getValueAt(fila, 0);
            pst=con.prepareStatement("SELECT * FROM cliente WHERE idcliente =?");
            pst.setInt(1,id);
            rs=pst.executeQuery();

            datos.jTextField1.setText(jTable1.getValueAt(fila, 1).toString());
            datos.jTextField2.setText(jTable1.getValueAt(fila, 2).toString());
            datos.jTextField3.setText(jTable1.getValueAt(fila, 3).toString());
            datos.jTextField4.setText(jTable1.getValueAt(fila, 4).toString());
            datos.jTextField5.setText(jTable1.getValueAt(fila, 5).toString());
            datos.jTextField6.setText(jTable1.getValueAt(fila, 6).toString());
            datos.jTextField7.setText(jTable1.getValueAt(fila, 7).toString());
            datos.idcliente=Integer.parseInt(jTable1.getValueAt(fila, 0).toString());
            if(rs.next()){
                Timestamp timestamp = rs.getTimestamp("fechaInicio");
                java.util.Date fechaInicio = new java.util.Date(timestamp.getTime());
                Timestamp timestamp1 = rs.getTimestamp("fechaFin");
                java.util.Date fechaFin = new java.util.Date(timestamp1.getTime());
                datos.jDateChooser1.setDate(fechaInicio);
                datos.jDateChooser2.setDate(fechaFin);
                File carpeta=new File("E:\\ImagenesPrueba\\");
                    File foto=buscar(jTable1.getValueAt(fila, 6).toString()+".jpg",carpeta);
                    Image img=ImageIO.read(foto);
                    ImageIcon f=new ImageIcon(img);
                    datos.jLabel12.setIcon(f);
                    datos.jLabel12.setVisible(true);
                }
                datos.setVisible(true);
                this.setVisible(false);
            } catch (SQLException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_jButton3ActionPerformed

    //añadir nuevo cliente
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        DatosCliente datos=new DatosCliente();
        datos.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    //filtrar
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        try{
            java.util.Date fI=jDateChooser1.getDate();

            java.util.Date fF=jDateChooser2.getDate();

            int q;
            if(fI!=null && fF !=null){
                java.sql.Date fechaDe=new java.sql.Date(fI.getTime());
                java.sql.Date fechaHasta=new java.sql.Date(fF.getTime());
                SimpleDateFormat f1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                f1.format(fechaDe);
                f1.format(fechaHasta);

                pst=con.prepareStatement("SELECT * FROM cliente WHERE  fechaFin >= ? AND fechaFin <= ? ORDER BY fechaFin");
                pst.setDate(1, fechaDe);
                pst.setDate(2, fechaHasta);
                rs=pst.executeQuery();
                ResultSetMetaData rss=rs.getMetaData();
                q=rss.getColumnCount();

                DefaultTableModel df= (DefaultTableModel)jTable1.getModel();
                df.setRowCount(0);
                SimpleDateFormat f=new SimpleDateFormat("dd-MM-yyyy");
                while(rs.next()){
                    Vector v2=new Vector();
                    for(int i =1; i<=q;i++){
                        v2.add(rs.getInt("idcliente"));
                        v2.add(rs.getString("Nombre"));
                        v2.add(rs.getString("Apellidos"));
                        v2.add(rs.getString("Estatus"));
                        v2.add(rs.getInt("PrecioMatricula"));
                        v2.add(rs.getInt("Telefono"));
                        v2.add(rs.getString("DNI"));
                        v2.add(rs.getString("Direccion"));
                        Timestamp timestamp = rs.getTimestamp("fechaInicio");
                        java.util.Date fechaInicio = new java.util.Date(timestamp.getTime());
                        v2.add(f.format(fechaInicio));
                        Timestamp timestamp1 = rs.getTimestamp("fechaFin");
                        java.util.Date fechaFin = new java.util.Date(timestamp1.getTime());
                        v2.add(f.format(fechaFin));

                    }
                    df.addRow(v2);
                }
            }else{
                mostrarAllClientes();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    //buscar
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int q;
        try{
            String info=jComboBox1.getSelectedItem().toString();
            if(info.equals("IdCliente")){
                int id=Integer.parseInt(jTextField1.getText());
                pst=con.prepareStatement("SELECT * FROM cliente WHERE idcliente =?");
                pst.setInt(1,id);
            }else if(info.equals("Nombre")){
                String n= jTextField1.getText();
                pst=con.prepareStatement("SELECT * FROM cliente WHERE Nombre LIKE ?");
                pst.setString(1,n);
            }else if(info.equals("Apellidos")){
                String n= jTextField1.getText();
                pst=con.prepareStatement("SELECT * FROM cliente WHERE Apellidos LIKE ?");
                pst.setString(1,n);
            }else if(info.equals("Estatus")){
                String n= jTextField1.getText();
                pst=con.prepareStatement("SELECT * FROM cliente WHERE Estatus LIKE ?");
                pst.setString(1,n);
            }else if(info.equals("PrecioMatricula")){
                int id=Integer.parseInt(jTextField1.getText());
                pst=con.prepareStatement("SELECT * FROM cliente WHERE PrecioMatricula =?");
                pst.setInt(1,id);
            }else if(info.equals("Telefono")){
                int id=Integer.parseInt(jTextField1.getText());
                pst=con.prepareStatement("SELECT * FROM cliente WHERE Telefono =?");
                pst.setInt(1,id);
            }else if(info.equals("DNI")){
                String n= jTextField1.getText();
                pst=con.prepareStatement("SELECT * FROM cliente WHERE DNI LIKE ?");
                pst.setString(1,n);
            }else if(info.equals("Direccion")){
                String n= jTextField1.getText();
                pst=con.prepareStatement("SELECT * FROM cliente WHERE Direccion LIKE ?");
                pst.setString(1,n);
            }else{
                pst=con.prepareStatement("SELECT * FROM cliente");
            }

            rs=pst.executeQuery();
            ResultSetMetaData rss=rs.getMetaData();
            q=rss.getColumnCount();

            DefaultTableModel df= (DefaultTableModel)jTable1.getModel();
            df.setRowCount(0);
            SimpleDateFormat f=new SimpleDateFormat("dd-MM-yyyy");
            while(rs.next()){
                Vector v2=new Vector();
                for(int i =1; i<=q;i++){
                    v2.add(rs.getInt("idcliente"));
                    v2.add(rs.getString("Nombre"));
                    v2.add(rs.getString("Apellidos"));
                    v2.add(rs.getString("Estatus"));
                    v2.add(rs.getInt("PrecioMatricula"));
                    v2.add(rs.getInt("Telefono"));
                    v2.add(rs.getString("DNI"));
                    v2.add(rs.getString("Direccion"));
                    Timestamp timestamp = rs.getTimestamp("fechaInicio");
                    java.util.Date fechaInicio = new java.util.Date(timestamp.getTime());
                    v2.add(f.format(fechaInicio));
                    Timestamp timestamp1 = rs.getTimestamp("fechaFin");
                    java.util.Date fechaFin = new java.util.Date(timestamp1.getTime());
                    v2.add(f.format(fechaFin));

                }
                df.addRow(v2);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private static File buscar(String archivoABuscar, File directorio) {
    File[] archivos = directorio.listFiles();
    for (File archivo : archivos) {
        if (archivo.getName().equals(archivoABuscar)) {
            return archivo;
        }
        if (archivo.isDirectory()) {
            File resultadoRecursion = buscar(archivoABuscar, archivo);
            if (resultadoRecursion != null) {
                return resultadoRecursion;
            }
        }
    }
    return null;
}
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
