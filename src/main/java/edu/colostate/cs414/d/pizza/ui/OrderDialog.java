package edu.colostate.cs414.d.pizza.ui;

import edu.colostate.cs414.d.pizza.api.menu.MenuItem;
import edu.colostate.cs414.d.pizza.ui.component.MenuPanel;
import java.awt.BorderLayout;
import java.util.LinkedList;
import java.util.List;

public class OrderDialog extends javax.swing.JDialog {

	/**
	 * Creates new form OrderDialog
	 */
	public OrderDialog(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
		
		initTestMenu();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        orderTypeGroup = new javax.swing.ButtonGroup();
        orderPanel = new javax.swing.JPanel();
        removeItemButton = new javax.swing.JButton();
        orderTableScroll = new javax.swing.JScrollPane();
        orderTable = new javax.swing.JTable();
        subtotalLabel = new javax.swing.JLabel();
        subtotalField = new javax.swing.JLabel();
        taxLabel = new javax.swing.JLabel();
        taxField = new javax.swing.JLabel();
        totalField = new javax.swing.JLabel();
        totalLabel = new javax.swing.JLabel();
        placeOrderButton = new javax.swing.JButton();
        cancelOrderButton = new javax.swing.JButton();
        typeLabel = new javax.swing.JLabel();
        orderTypeTakeout = new javax.swing.JRadioButton();
        orderTypeDelivery = new javax.swing.JRadioButton();
        orderTypeEatIn = new javax.swing.JRadioButton();
        nameLabel = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        addressLabel = new javax.swing.JLabel();
        addressFieldScroll = new javax.swing.JScrollPane();
        addressField = new javax.swing.JTextArea();
        menuWrapper = new javax.swing.JPanel();
        specialsPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(400, 300));

        orderPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("My Order"));

        removeItemButton.setText("Remove");
        removeItemButton.setEnabled(false);

        orderTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"A Pizza",  new Double(10.0),  new Integer(1)}
            },
            new String [] {
                "Item", "Price", "#"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Double.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        orderTable.getTableHeader().setReorderingAllowed(false);
        orderTableScroll.setViewportView(orderTable);

        subtotalLabel.setText("Subtotal:");

        subtotalField.setText("$0.00");

        taxLabel.setText("Tax:");

        taxField.setText("$0.00");

        totalField.setText("$0.00");

        totalLabel.setText("Total:");

        placeOrderButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/colostate/cs414/d/pizza/ui/okay.png"))); // NOI18N
        placeOrderButton.setText("Place Order");

        cancelOrderButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/colostate/cs414/d/pizza/ui/cancel.png"))); // NOI18N
        cancelOrderButton.setText("Cancel Order");

        typeLabel.setText("Type:");

        orderTypeGroup.add(orderTypeTakeout);
        orderTypeTakeout.setSelected(true);
        orderTypeTakeout.setText("Takeout");

        orderTypeGroup.add(orderTypeDelivery);
        orderTypeDelivery.setText("Delivery");

        orderTypeGroup.add(orderTypeEatIn);
        orderTypeEatIn.setText("Eat In");

        nameLabel.setText("Name:");

        addressLabel.setText("Address:");

        addressField.setColumns(20);
        addressField.setRows(5);
        addressFieldScroll.setViewportView(addressField);

        javax.swing.GroupLayout orderPanelLayout = new javax.swing.GroupLayout(orderPanel);
        orderPanel.setLayout(orderPanelLayout);
        orderPanelLayout.setHorizontalGroup(
            orderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(orderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(orderPanelLayout.createSequentialGroup()
                        .addComponent(placeOrderButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cancelOrderButton))
                    .addGroup(orderPanelLayout.createSequentialGroup()
                        .addGroup(orderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(orderPanelLayout.createSequentialGroup()
                                .addComponent(subtotalLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(subtotalField))
                            .addGroup(orderPanelLayout.createSequentialGroup()
                                .addComponent(taxLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(taxField))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, orderPanelLayout.createSequentialGroup()
                                .addComponent(totalLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(totalField)))
                        .addContainerGap())
                    .addGroup(orderPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(removeItemButton))
                    .addGroup(orderPanelLayout.createSequentialGroup()
                        .addGroup(orderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addressLabel)
                            .addComponent(nameLabel)
                            .addComponent(typeLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(orderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(orderPanelLayout.createSequentialGroup()
                                .addComponent(orderTypeTakeout)
                                .addGap(12, 12, 12)
                                .addComponent(orderTypeDelivery)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(orderTypeEatIn)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(nameField)
                            .addComponent(addressFieldScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)))))
            .addComponent(orderTableScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        orderPanelLayout.setVerticalGroup(
            orderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderPanelLayout.createSequentialGroup()
                .addComponent(orderTableScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(removeItemButton)
                .addGap(27, 27, 27)
                .addGroup(orderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(orderTypeTakeout)
                    .addComponent(orderTypeDelivery)
                    .addComponent(orderTypeEatIn)
                    .addComponent(typeLabel))
                .addGap(18, 18, 18)
                .addGroup(orderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(orderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addressLabel)
                    .addComponent(addressFieldScroll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(orderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(subtotalLabel)
                    .addComponent(subtotalField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(orderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(taxLabel)
                    .addComponent(taxField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(orderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalField)
                    .addComponent(totalLabel))
                .addGap(18, 18, 18)
                .addGroup(orderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelOrderButton)
                    .addComponent(placeOrderButton))
                .addContainerGap())
        );

        menuWrapper.setBorder(javax.swing.BorderFactory.createTitledBorder("Menu"));
        menuWrapper.setLayout(new java.awt.BorderLayout());

        specialsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Today's Specials"));

        javax.swing.GroupLayout specialsPanelLayout = new javax.swing.GroupLayout(specialsPanel);
        specialsPanel.setLayout(specialsPanelLayout);
        specialsPanelLayout.setHorizontalGroup(
            specialsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        specialsPanelLayout.setVerticalGroup(
            specialsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menuWrapper, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
                    .addComponent(specialsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(orderPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(orderPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menuWrapper, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(specialsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void initTestMenu() {
		List<MenuItem> items = new LinkedList<>();
		items.add(new MenuItem("Pizza", 9.99, "It's a pizza."));
		items.add(new MenuItem("Drink", 1.25, "Some sort of drink."));
		items.add(new MenuItem("Soylent Green", 4.99, "People."));
		
		MenuPanel panel = new MenuPanel(items, true);
		
		menuWrapper.add(panel, BorderLayout.CENTER);
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
			java.util.logging.Logger.getLogger(OrderDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(OrderDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(OrderDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(OrderDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
        //</editor-fold>

		/* Create and display the dialog */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				OrderDialog dialog = new OrderDialog(new javax.swing.JFrame(), true);
				dialog.addWindowListener(new java.awt.event.WindowAdapter() {
					@Override
					public void windowClosing(java.awt.event.WindowEvent e) {
						System.exit(0);
					}
				});
				dialog.setVisible(true);
			}
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea addressField;
    private javax.swing.JScrollPane addressFieldScroll;
    private javax.swing.JLabel addressLabel;
    private javax.swing.JButton cancelOrderButton;
    private javax.swing.JPanel menuWrapper;
    private javax.swing.JTextField nameField;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JPanel orderPanel;
    private javax.swing.JTable orderTable;
    private javax.swing.JScrollPane orderTableScroll;
    private javax.swing.JRadioButton orderTypeDelivery;
    private javax.swing.JRadioButton orderTypeEatIn;
    private javax.swing.ButtonGroup orderTypeGroup;
    private javax.swing.JRadioButton orderTypeTakeout;
    private javax.swing.JButton placeOrderButton;
    private javax.swing.JButton removeItemButton;
    private javax.swing.JPanel specialsPanel;
    private javax.swing.JLabel subtotalField;
    private javax.swing.JLabel subtotalLabel;
    private javax.swing.JLabel taxField;
    private javax.swing.JLabel taxLabel;
    private javax.swing.JLabel totalField;
    private javax.swing.JLabel totalLabel;
    private javax.swing.JLabel typeLabel;
    // End of variables declaration//GEN-END:variables
}
