package Attend;

import java.awt.Component;
import java.awt.Image;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

public class ControlPerson {
    String date;
    Connectdb conecta = new Connectdb();

    public void insert(ModelPerson mod) throws ClassNotFoundException {
        date = new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis()));

        try {
            conecta.connect();
            PreparedStatement pst = conecta.conn.prepareStatement("insert into student(id,firstname,lastname,email,dob,contact,address,branch,year)values(?,?,?,?,?,?,?,?,?)");
            pst.setInt(1, mod.getId());
            pst.setString(2, mod.getFirst_name());
            pst.setString(3, mod.getLast_name());
            pst.setString(4, mod.getEmail());
            pst.setString(5, mod.getDob());
            pst.setString(6, mod.getContactNum());
            pst.setString(7, mod.getAddress());
            pst.setString(8, mod.getBranch());
            pst.setString(9, mod.getYear());

            pst.executeUpdate();
            System.out.println("Data for: " + mod.getFirst_name() + " registered");
            conecta.disconnect();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
    }

    public void update(ModelPerson mod, int id) throws ClassNotFoundException {
    	conecta.connect();
        try {
            PreparedStatement pst = conecta.conn.prepareStatement("UPDATE persons SET firstname= ?, lastname= ?,office= ?,registerdate=? WHERE id=?");
            pst.setString(1, mod.getFirst_name());
            pst.setString(2, mod.getLast_name());
            //pst.setString(3, mod.getDob());
            pst.setString(3, mod.getOffice());
            pst.setString(4, date);
            //pst.setString(5, mod.getFacebook());
           // pst.setString(6, mod.getInstagram());
            //pst.setString(7, mod.getLinkedin());
           // pst.setString(8, mod.getGithub());
            pst.setInt(5, id);
            pst.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar dados\n ERRO: " + ex);
        }
        conecta.disconnect();
    }

    public void delete(int id) throws ClassNotFoundException {
    	conecta.connect();
        try {
            PreparedStatement pst = conecta.conn.prepareStatement("DELETE FROM persons WHERE id= '" + id + "'");
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluído com Sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir apartamento. Tente Novamente!");
        } finally {
            conecta.disconnect();
        }
    }

  /*  public void preencherTabela(String SQL, JTable tabela) {
        String id = null;

        conecta.connect();
        ArrayList dados = new ArrayList();
        String[] Colunas = new String[]{"", "ID", "Name","Office"};
        conecta.executeSQL(SQL);
        try {
            conecta.rs.first();
            do 
            {
                dados.add(new Object[]{
                    "",conecta.rs.getString("id"),conecta.rs.getString("first_name") + " " + conecta.rs.getString("last_name"),conecta.rs.getString("office")
                   
                 
               
            } 
                while (conecta.rs.next());
        } 
            catch (SQLException ex) {
            //JOptionPane.showMessageDialog(rootPane, "Lista de Cadastro Vazia!");
        } 
            finally 
            {
            conecta.disconnect();
        }
        

        ModeloTabela modelo = new ModeloTabela(dados, Colunas);
        tabela.setModel((TableModel) modelo);
        tabela.getColumnModel().getColumn(0).setCellRenderer(new ControlPerson.ImageRenderer());
        tabela.getColumnModel().getColumn(1).setMaxWidth(0);
        tabela.getColumnModel().getColumn(1).setMinWidth(0);
        tabela.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0);
        tabela.getTableHeader().getColumnModel().getColumn(1).setMinWidth(0);
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    class ImageRenderer implements TableCellRenderer {

        public JLabel lbl = new JLabel();

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            try {
                Object text = table.getValueAt(row, 1);
                File image = new File("C:\\photos\\person." + text + ".1.jpg");
                String path = image.getAbsolutePath();
                ImageIcon i = new ImageIcon(new ImageIcon(String.valueOf(path)).getImage().getScaledInstance(lbl.getWidth() + 50, lbl.getHeight() + 50, Image.SCALE_SMOOTH));
                lbl.setIcon(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return lbl;
        }
    }

    public void editar(ModelPerson mod, int id) throws ClassNotFoundException {
    	conecta.connect();
        try {
            PreparedStatement pst = conecta.conn.prepareStatement("UPDATE apto SET apto= ? WHERE id=? ");
            pst.setString(1, mod.getFirst_name());
            pst.setInt(2, id);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Alterado com Sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar apartamento. Tente Novamente!");
        } finally {
            conecta.disconnect();
        }
    } */

}
