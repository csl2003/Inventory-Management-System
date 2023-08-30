package swings;
import Database.DbConnection;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.Dialog.ModalExclusionType;

public class App {
    DbConnection db=new DbConnection();
    Connection con=db.connect();
    private CustomTableModel tableModel;
    private JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    private JInternalFrame detailsWindow;
    private JTable table;
    private JScrollPane tableScrollPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    App window = new App();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public App() {
        initialize();
    }
    private void setTableCellAlignment(int alignment) {
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(alignment);
        for (int columnIndex = 0; columnIndex < tableModel.getColumnCount(); columnIndex++) {
            table.getColumnModel().getColumn(columnIndex).setCellRenderer(renderer);
        }
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBackground(new Color(253, 244, 208));
        frame.setForeground(new Color(255, 228, 181));
        frame.getContentPane().setBackground(new Color(244, 244, 244));
        frame.setBounds(100, 100, 1159, 802);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        textField = new JTextField("INVENTORY MANAGEMENT SYSTEM");
        textField.setEditable(false);
        textField.setBackground(new Color(216, 203, 220));
        textField.setFont(new Font("Sitka Text", Font.BOLD, 22));
        textField.setBounds(0, 0, 1300, 60);
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        frame.getContentPane().add(textField);
        textField.setColumns(3);

        JButton btnNewButton = new JButton("ADD");
        btnNewButton.setBounds(101, 284, 101, 37);
        frame.getContentPane().add(btnNewButton);

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showDetailsWindow();
//                db.add(con,id1, name,qt,"c1");
            }
        });

        JButton btnNewButton_1 = new JButton("DELETE");
        btnNewButton_1.setBounds(247, 284, 101, 37);
        frame.getContentPane().add(btnNewButton_1);

        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showDetailsWindow1();
        
            }
        });

        JButton btnNewButton_2 = new JButton("UPDATE");
        btnNewButton_2.setBounds(101, 338, 101, 37);
        frame.getContentPane().add(btnNewButton_2);
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showDetailsWindow2();
            }
        });


        JButton btnNewButton_3 = new JButton("DISPLAY");
        btnNewButton_3.setBounds(247, 338, 101, 37);
        frame.getContentPane().add(btnNewButton_3);
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showDetailsWindow3();
            }
        });


        // With the following:
        String[] columnNames = {"PRODUCT ID", "PRODUCT NAME", "QUANTITY", "CATEGORY"};
        Object[][] data = db.displayall(con);
        
        tableModel = new CustomTableModel(columnNames, data);
        table = new JTable(tableModel);
        table.setBackground(new Color(251, 251, 251));
        tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBounds(400, 100, 700, 600);
        frame.getContentPane().add(tableScrollPane);
        tableScrollPane.setVisible(true);
        tableScrollPane.getViewport().setBackground(new Color(244,244,244));
        table.setRowHeight(20);
        
      
        
    }
    


    private void showDetailsWindow() {
        if (detailsWindow == null || !detailsWindow.isVisible()) {
            detailsWindow = new JInternalFrame("Details Window", true, true, true, true);
            detailsWindow.setBounds(100, 100, 300, 250);
            detailsWindow.getContentPane().setLayout(null);

            JLabel lblProductId = new JLabel("Product ID:");
            lblProductId.setBounds(20, 30, 100, 20);
            detailsWindow.getContentPane().add(lblProductId);

            JTextField textFieldProductId = new JTextField();
            textFieldProductId.setBounds(130, 30, 150, 20);
            detailsWindow.getContentPane().add(textFieldProductId);
            textFieldProductId.setColumns(10);

            JLabel lblProductName = new JLabel("Product Name:");
            lblProductName.setBounds(20, 60, 100, 20);
            detailsWindow.getContentPane().add(lblProductName);

            JTextField textFieldProductName = new JTextField();
            textFieldProductName.setBounds(130, 60, 150, 20);
            detailsWindow.getContentPane().add(textFieldProductName);
            textFieldProductName.setColumns(10);

            JLabel lblQuantity = new JLabel("Quantity:");
            lblQuantity.setBounds(20, 90, 100, 20);
            detailsWindow.getContentPane().add(lblQuantity);

            JTextField textFieldQuantity = new JTextField();
            textFieldQuantity.setBounds(130, 90, 150, 20);
            detailsWindow.getContentPane().add(textFieldQuantity);
            textFieldQuantity.setColumns(10);
            
            JLabel lblCategory = new JLabel("Category");
            lblCategory.setBounds(20, 120, 100, 20);
            detailsWindow.getContentPane().add(lblCategory);

            JTextField textFieldCategory = new JTextField();
            textFieldCategory.setBounds(130, 120, 150, 20);
            detailsWindow.getContentPane().add(textFieldCategory);
            textFieldCategory.setColumns(10);


            JButton btnSubmit = new JButton("Submit");
            btnSubmit.setBounds(100, 170, 100, 30);
            detailsWindow.getContentPane().add(btnSubmit);

            btnSubmit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String productId = textFieldProductId.getText();
                    String productName = textFieldProductName.getText();
                    String quantity = textFieldQuantity.getText();
                    String ct= textFieldCategory.getText();

                    // Check if any of the fields are empty
                    if (productId.isEmpty() || productName.isEmpty() || quantity.isEmpty()) {
                        JOptionPane.showMessageDialog(detailsWindow,
                                "Entered value is null. Please fill all the fields.",
                                "Warning", JOptionPane.WARNING_MESSAGE);
                    } else {
                        detailsWindow.setVisible(false);
                        int id1 = Integer.parseInt(productId);
                        int ct1 = Integer.parseInt(quantity);
                        db.add(con, id1, productName, ct1, ct);
                        Object[][] newData = db.displayall(con);
                        tableModel.setData(newData);
                       // Hide the details window after submitting.
                    }
                }
            });

            JLayeredPane layeredPane = frame.getLayeredPane();
            layeredPane.add(detailsWindow, JLayeredPane.PALETTE_LAYER);

            // Center the internal frame within the main frame
            int frameWidth = frame.getContentPane().getWidth();
            int frameHeight = frame.getContentPane().getHeight();
            int internalFrameWidth = detailsWindow.getWidth();
            int internalFrameHeight = detailsWindow.getHeight();
            int x = (frameWidth - internalFrameWidth) / 2;
            int y = (frameHeight - internalFrameHeight) / 2;
            detailsWindow.setLocation(x, y);

            detailsWindow.setVisible(true);
        }
    }


    private void showDetailsWindow1() {
        if (detailsWindow == null || !detailsWindow.isVisible()) {
            detailsWindow = new JInternalFrame("Details Window", true, true, true, true);
            detailsWindow.setBounds(100, 100, 300, 200);
            detailsWindow.getContentPane().setLayout(null);

            JLabel lblProductId = new JLabel("Product ID:");
            lblProductId.setBounds(20, 30, 100, 20);
            detailsWindow.getContentPane().add(lblProductId);

            JTextField textFieldProductId = new JTextField();
            textFieldProductId.setBounds(130, 30, 150, 20);
            detailsWindow.getContentPane().add(textFieldProductId);
            textFieldProductId.setColumns(10);
            

            JLabel lblProductName = new JLabel("Product Name:");
            lblProductName.setBounds(20, 60, 100, 20);
            detailsWindow.getContentPane().add(lblProductName);

            JTextField textFieldProductName = new JTextField();
            textFieldProductName.setBounds(130, 60, 150, 20);
            detailsWindow.getContentPane().add(textFieldProductName);
            textFieldProductName.setColumns(10);
           

            JButton btnSubmit = new JButton("Submit");
            btnSubmit.setBounds(100, 130, 100, 30);
            detailsWindow.getContentPane().add(btnSubmit);
        
            btnSubmit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String productId = textFieldProductId.getText();
                    String productName = textFieldProductName.getText();

                    // Check if any of the fields are empty
                    if (productId.isEmpty() && productName.isEmpty()) {
                        JOptionPane.showMessageDialog(detailsWindow,
                                "Please enter either of the values.",
                                "Warning", JOptionPane.WARNING_MESSAGE);
                    } else {
                        detailsWindow.setVisible(false);
                        int id1=Integer.parseInt(productId);
                        db.delete(con, id1);
                        Object[][] newData = db.displayall(con);
                        tableModel.setData(newData);
                        // Hide the details window after submitting.
                    }
                }
            });

            JLayeredPane layeredPane = frame.getLayeredPane();
            layeredPane.add(detailsWindow, JLayeredPane.PALETTE_LAYER);

            // Center the internal frame within the main frame
            int frameWidth = frame.getContentPane().getWidth();
            int frameHeight = frame.getContentPane().getHeight();
            int internalFrameWidth = detailsWindow.getWidth();
            int internalFrameHeight = detailsWindow.getHeight();
            int x = (frameWidth - internalFrameWidth) / 2;
            int y = (frameHeight - internalFrameHeight) / 2;
            detailsWindow.setLocation(x, y);

            detailsWindow.setVisible(true);
            
        }
    }
    
    private void showDetailsWindow2() {
        if (detailsWindow == null || !detailsWindow.isVisible()) {
            detailsWindow = new JInternalFrame("Details Window", true, true, true, true);
            detailsWindow.setBounds(100, 100, 300, 200);
            detailsWindow.getContentPane().setLayout(null);

            JLabel lblProductId = new JLabel("Product ID:");
            lblProductId.setBounds(20, 30, 100, 20);
            detailsWindow.getContentPane().add(lblProductId);

            JTextField textFieldProductId = new JTextField();
            textFieldProductId.setBounds(130, 30, 150, 20);
            detailsWindow.getContentPane().add(textFieldProductId);
            textFieldProductId.setColumns(10);
           

            JLabel lblProductName = new JLabel("Product Name:");
            lblProductName.setBounds(20, 60, 100, 20);
            detailsWindow.getContentPane().add(lblProductName);

            JTextField textFieldProductName = new JTextField();
            textFieldProductName.setBounds(130, 60, 150, 20);
            detailsWindow.getContentPane().add(textFieldProductName);
            textFieldProductName.setColumns(10);

            JLabel lblQuantity = new JLabel("Quantity:");
            lblQuantity.setBounds(20, 90, 100, 20);
            detailsWindow.getContentPane().add(lblQuantity);

            JTextField textFieldQuantity = new JTextField();
            textFieldQuantity.setBounds(130, 90, 150, 20);
            detailsWindow.getContentPane().add(textFieldQuantity);
            textFieldQuantity.setColumns(10);
           

            JButton btnSubmit = new JButton("Submit");
            btnSubmit.setBounds(100, 130, 100, 30);
            detailsWindow.getContentPane().add(btnSubmit);

            btnSubmit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String productId = textFieldProductId.getText();
                    String productName = textFieldProductName.getText();
                    String quantity = textFieldQuantity.getText();

                    // Check if any of the fields are empty
                    if ((productId.isEmpty() || productName.isEmpty()) || quantity.isEmpty()) {
                        JOptionPane.showMessageDialog(detailsWindow,
                                "Entered value is null. Please fill all the fields.",
                                "Warning", JOptionPane.WARNING_MESSAGE);
                    } else {
                        detailsWindow.setVisible(false);
                        int id1=Integer.parseInt(productId);
                        int qt1=Integer.parseInt(quantity);
                        db.update(con, id1, qt1);
                        Object[][] newData = db.displayall(con);
                        tableModel.setData(newData);// Hide the details window after submitting.
                    }
                }
            });

            JLayeredPane layeredPane = frame.getLayeredPane();
            layeredPane.add(detailsWindow, JLayeredPane.PALETTE_LAYER);

            // Center the internal frame within the main frame
            int frameWidth = frame.getContentPane().getWidth();
            int frameHeight = frame.getContentPane().getHeight();
            int internalFrameWidth = detailsWindow.getWidth();
            int internalFrameHeight = detailsWindow.getHeight();
            int x = (frameWidth - internalFrameWidth) / 2;
            int y = (frameHeight - internalFrameHeight) / 2;
            detailsWindow.setLocation(x, y);

            detailsWindow.setVisible(true);
        }
    }
    
    private void showDetailsWindow3() {
        if (detailsWindow == null || !detailsWindow.isVisible()) {
            detailsWindow = new JInternalFrame("Details Window", true, true, true, true);
            detailsWindow.setBounds(100, 100, 300, 200);
            detailsWindow.getContentPane().setLayout(null);


            JLabel lblProductId = new JLabel("Category:");
            lblProductId.setBounds(20, 30, 100, 20);
            detailsWindow.getContentPane().add(lblProductId);

            JTextField categoryName = new JTextField();
            categoryName.setBounds(130, 30, 150, 20);
            detailsWindow.getContentPane().add(categoryName);
            categoryName.setColumns(10);

            JButton btnSubmit = new JButton("Submit");
            btnSubmit.setBounds(50, 100, 100, 30);
            detailsWindow.getContentPane().add(btnSubmit);

            btnSubmit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String category = categoryName.getText();

                    // Check if any of the fields are empty
                    if (category.isEmpty()) {
                        JOptionPane.showMessageDialog(detailsWindow,
                                "Entered value is null. Please fill all the fields.",
                                "Warning", JOptionPane.WARNING_MESSAGE);
                    } else {
                        detailsWindow.setVisible(false);
                        Object[][] newData =db.display(con, category);
                        tableModel.setData(newData);// Hide the details window after submitting.
                    }
                }
            });

            JLayeredPane layeredPane = frame.getLayeredPane();
            layeredPane.add(detailsWindow, JLayeredPane.PALETTE_LAYER);

            // Center the internal frame within the main frame
            int frameWidth = frame.getContentPane().getWidth();
            int frameHeight = frame.getContentPane().getHeight();
            int internalFrameWidth = detailsWindow.getWidth();
            int internalFrameHeight = detailsWindow.getHeight();
            int x = (frameWidth - internalFrameWidth) / 2;
            int y = (frameHeight - internalFrameHeight) / 2;
            detailsWindow.setLocation(x, y);

            detailsWindow.setVisible(true);
        }
    }
    
    

    private void showTable() {
        // Toggle the visibility of the table
        tableScrollPane.setVisible(tableScrollPane.isVisible());
    }
}
