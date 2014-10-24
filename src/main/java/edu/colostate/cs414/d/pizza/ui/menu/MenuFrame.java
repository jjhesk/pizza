package edu.colostate.cs414.d.pizza.ui.menu;

import edu.colostate.cs414.d.pizza.Kiosk;
import edu.colostate.cs414.d.pizza.ui.OrderDialog;
import edu.colostate.cs414.d.pizza.ui.PendingOrderFrame;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

public class MenuFrame extends JFrame {

	private final Kiosk kiosk;
	
	/**
	 * Creates new form MainFrame
	 */
	public MenuFrame() {
		kiosk = new Kiosk();
		
		initComponents();
		initMenu();
		
		placeOrderButton.requestFocusInWindow();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuWrapperScroll = new JScrollPane();
        menuWrapper = new JPanel();
        buttonPanel = new JPanel();
        placeOrderButton = new JButton();
        menuBar = new JMenuBar();
        fileMenu = new JMenu();
        exitItem = new JMenuItem();
        adminMenu = new JMenu();
        editMenuItem = new JMenuItem();
        chefMenuItem = new JMenuItem();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        menuWrapperScroll.setBorder(null);
        menuWrapperScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        menuWrapper.setBorder(null);
        menuWrapper.setLayout(new BorderLayout());
        menuWrapperScroll.setViewportView(menuWrapper);

        placeOrderButton.setIcon(new ImageIcon(getClass().getResource("/edu/colostate/cs414/d/pizza/ui/add.png"))); // NOI18N
        placeOrderButton.setText("Place Order");
        placeOrderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                placeOrderButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(placeOrderButton);

        fileMenu.setText("File");

        exitItem.setText("Exit");
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                exitItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);

        adminMenu.setText("Admin");

        editMenuItem.setText("Edit Menu");
        editMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                editMenuItemActionPerformed(evt);
            }
        });
        adminMenu.add(editMenuItem);

        chefMenuItem.setText("Chef Menu");
        chefMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                chefMenuItemActionPerformed(evt);
            }
        });
        adminMenu.add(chefMenuItem);

        menuBar.add(adminMenu);

        setJMenuBar(menuBar);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(buttonPanel, GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE)
            .addComponent(menuWrapperScroll, GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menuWrapperScroll, GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
                .addGap(1, 1, 1)
                .addComponent(buttonPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void initMenu() {
		menuPanel = new MenuPanel(kiosk.viewMenu(), MenuFeature.NONE, 2);
		menuWrapper.add(menuPanel, BorderLayout.CENTER);
		pack();
	}
	
    private void placeOrderButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_placeOrderButtonActionPerformed
        OrderDialog d = new OrderDialog(this, kiosk);
		d.setVisible(true);
    }//GEN-LAST:event_placeOrderButtonActionPerformed

    private void editMenuItemActionPerformed(ActionEvent evt) {//GEN-FIRST:event_editMenuItemActionPerformed
        MenuEditDialog d = new MenuEditDialog(this, kiosk);
		d.setVisible(true);
		
		menuPanel.refreshMenuItems(kiosk.viewMenu());
    }//GEN-LAST:event_editMenuItemActionPerformed

    private void exitItemActionPerformed(ActionEvent evt) {//GEN-FIRST:event_exitItemActionPerformed
        dispose();
    }//GEN-LAST:event_exitItemActionPerformed

    private void chefMenuItemActionPerformed(ActionEvent evt) {//GEN-FIRST:event_chefMenuItemActionPerformed
        PendingOrderFrame f = new PendingOrderFrame(kiosk);
		f.setVisible(true);
    }//GEN-LAST:event_chefMenuItemActionPerformed
	
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
			java.util.logging.Logger.getLogger(MenuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(MenuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(MenuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(MenuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
        //</editor-fold>

		/* Create and display the form */
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MenuFrame().setVisible(true);
			}
		});
	}

	private MenuPanel menuPanel;
	
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JMenu adminMenu;
    private JPanel buttonPanel;
    private JMenuItem chefMenuItem;
    private JMenuItem editMenuItem;
    private JMenuItem exitItem;
    private JMenu fileMenu;
    private JMenuBar menuBar;
    private JPanel menuWrapper;
    private JScrollPane menuWrapperScroll;
    private JButton placeOrderButton;
    // End of variables declaration//GEN-END:variables
}
