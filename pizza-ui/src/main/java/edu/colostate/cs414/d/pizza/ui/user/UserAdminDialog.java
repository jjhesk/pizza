package edu.colostate.cs414.d.pizza.ui.user;

import edu.colostate.cs414.d.pizza.Kiosk;
import edu.colostate.cs414.d.pizza.api.user.User;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class UserAdminDialog extends JDialog {

	private Kiosk kiosk;
	
	/**
	 * Creates new form UserAdminDialog
	 */
	public UserAdminDialog(Frame parent) {
		super(parent, true);
		
		kiosk = Kiosk.getInstance();
		
		initComponents();
		initUsers();
		
		setLocationRelativeTo(parent);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        userListScroll = new JScrollPane();
        userList = new JList();
        closeButton = new JButton();
        createUserButton = new JButton();
        deleteUserButton = new JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Manage Users");

        userList.setModel(new AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        userList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent evt) {
                userListValueChanged(evt);
            }
        });
        userListScroll.setViewportView(userList);

        closeButton.setText("Close");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        createUserButton.setText("Create User");
        createUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                createUserButtonActionPerformed(evt);
            }
        });

        deleteUserButton.setText("Delete User");
        deleteUserButton.setEnabled(false);
        deleteUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                deleteUserButtonActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(userListScroll)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(createUserButton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteUserButton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                        .addComponent(closeButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(userListScroll, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(closeButton)
                    .addComponent(createUserButton)
                    .addComponent(deleteUserButton))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void initUsers() {
		listModel = new UserListModel(kiosk.getUsers());
		userList.setModel(listModel);
		
		userList.clearSelection();
		deleteUserButton.setEnabled(false);
	}
	
    private void closeButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        dispose();
    }//GEN-LAST:event_closeButtonActionPerformed

    private void userListValueChanged(ListSelectionEvent evt) {//GEN-FIRST:event_userListValueChanged
        int index = userList.getSelectedIndex();
		if (index < 0) {
			return;
		}
		
		deleteUserButton.setEnabled(true);
    }//GEN-LAST:event_userListValueChanged

    private void createUserButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_createUserButtonActionPerformed
        UserCreateDialog d = new UserCreateDialog(this);
		d.setVisible(true);
		
		// ... wait and refresh when finished ...
		
		initUsers();
    }//GEN-LAST:event_createUserButtonActionPerformed

    private void deleteUserButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_deleteUserButtonActionPerformed
        int index = userList.getSelectedIndex();
		if (index < 0) {
			error("No user selected!");
			return;
		}
		
		User user = listModel.getUser(index);
		
		if (kiosk.getLoggedInUser().equals(user)) {
			error("You can't delete yourself.");
			return;
		}
		
		int ret = JOptionPane.showOptionDialog(this,
                String.format("Really delete user %s?", user.getUserName()),
                "Confirm",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null, null, null);
        
        if (ret == JOptionPane.YES_OPTION) {
            kiosk.removeUser(user.getUserName());
			
			initUsers();
        }
    }//GEN-LAST:event_deleteUserButtonActionPerformed

	private void error(String message) {
        JOptionPane.showMessageDialog(
                this, message, "Error",
                JOptionPane.ERROR_MESSAGE);
    }
	
	private UserListModel listModel;
	
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton closeButton;
    private JButton createUserButton;
    private JButton deleteUserButton;
    private JList userList;
    private JScrollPane userListScroll;
    // End of variables declaration//GEN-END:variables
}