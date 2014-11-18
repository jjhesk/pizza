package edu.colostate.cs414.d.pizza.ui;

import edu.colostate.cs414.d.pizza.Kiosk;
import edu.colostate.cs414.d.pizza.api.order.Order;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class PendingOrderFrame extends javax.swing.JFrame {

	private Kiosk kiosk;
	
	/**
	 * Creates new form PendingOrderFrame
	 */
	public PendingOrderFrame(Kiosk kiosk) {
		this.kiosk = kiosk;
		
		initComponents();
		initOrders();
		
		orderTable.getSelectionModel().addListSelectionListener(selectionListener);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        markVoidButton = new JButton();
        markFilledButton = new JButton();
        orderScroll = new JScrollPane();
        orderTable = new JTable();
        refreshButton = new JButton();

        markVoidButton.setText("Mark as Void");
        markVoidButton.setEnabled(false);
        markVoidButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                markVoidButtonActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pending Orders");

        markFilledButton.setText("Mark as Filled");
        markFilledButton.setEnabled(false);
        markFilledButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                markFilledButtonActionPerformed(evt);
            }
        });

        orderTable.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        orderTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        orderScroll.setViewportView(orderTable);

        refreshButton.setText("Refresh");
        refreshButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(orderScroll, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(markFilledButton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(refreshButton)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(orderScroll, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(markFilledButton)
                    .addComponent(refreshButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void markFilledButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_markFilledButtonActionPerformed
        int row = orderTable.getSelectedRow();
		if (row < 0) {
			error("No order selected!");
			return;
		}
		
		Order o = orderModel.getOrder(row);
		kiosk.completeOrder(o);
		
		initOrders();
    }//GEN-LAST:event_markFilledButtonActionPerformed

    private void markVoidButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_markVoidButtonActionPerformed
        int row = orderTable.getSelectedRow();
		if (row < 0) {
			error("No order selected!");
			return;
		}
		
		Order o = orderModel.getOrder(row);
		error("Not implemented.");
    }//GEN-LAST:event_markVoidButtonActionPerformed

    private void refreshButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        // TODO add your handling code here:
        initOrders();
    }//GEN-LAST:event_refreshButtonActionPerformed

	private void initOrders() {
		orderTable.getSelectionModel().removeListSelectionListener(selectionListener);
		
		orderModel = new PendingOrderTableModel(kiosk.viewPendingOrders());
		orderTable.setModel(orderModel);
		
		orderTable.getSelectionModel().addListSelectionListener(selectionListener);
	}

	private final ListSelectionListener selectionListener = new ListSelectionListener() {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            markFilledButton.setEnabled(true);
			markVoidButton.setEnabled(true);

			int row = orderTable.getSelectedRow();
			if (row >= 0) {
				// select the whole range for this order
				int[] range = orderModel.getRange(orderTable.getSelectedRow());
				orderTable.getSelectionModel().setSelectionInterval(range[0], range[1]);
			}
        }
        
    };
	
	private void error(String message) {
        JOptionPane.showMessageDialog(
                this, message, "Error",
                JOptionPane.ERROR_MESSAGE);
    }
	
	private PendingOrderTableModel orderModel;
	
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton markFilledButton;
    private JButton markVoidButton;
    private JScrollPane orderScroll;
    private JTable orderTable;
    private JButton refreshButton;
    // End of variables declaration//GEN-END:variables
}
