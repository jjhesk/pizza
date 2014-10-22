package edu.colostate.cs414.d.pizza.ui.menu;

import edu.colostate.cs414.d.pizza.api.menu.MenuItem;
import java.awt.Dialog;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;

public class MenuItemEditDialog extends JDialog {

	private MenuItem originalItem;
	private MenuItem returnedItem;
	
	/**
	 * Creates new form MenuItemEditDialog
	 */
	public MenuItemEditDialog(Frame parent, MenuItem originalItem) {
		super(parent, true);
		
		this.originalItem = originalItem;
		
		initComponents();
		initDefaultValues();
		
		setLocationRelativeTo(parent);
	}
	
	public MenuItemEditDialog(Dialog parent, MenuItem originalItem) {
		super(parent, true);
		
		this.originalItem = originalItem;
		
		initComponents();
		initDefaultValues();
		
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

        saveButton = new JButton();
        cancelButton = new JButton();
        nameField = new JTextField();
        nameLabel = new JLabel();
        priceLabel = new JLabel();
        priceField = new JSpinner();
        descriptionLabel = new JLabel();
        descriptionScroll = new JScrollPane();
        descriptionField = new JTextArea();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        saveButton.setText("Save");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        nameLabel.setText("Name:");

        priceLabel.setText("Price:");

        priceField.setModel(new SpinnerNumberModel(Double.valueOf(0.0d), Double.valueOf(0.0d), null, Double.valueOf(1.0d)));

        descriptionLabel.setText("Description:");

        descriptionField.setColumns(20);
        descriptionField.setRows(5);
        descriptionScroll.setViewportView(descriptionField);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(saveButton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(descriptionLabel)
                            .addComponent(priceLabel)
                            .addComponent(nameLabel))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(nameField)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(priceField, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(descriptionScroll, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel)
                    .addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(priceLabel)
                    .addComponent(priceField, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(descriptionLabel)
                    .addComponent(descriptionScroll, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(cancelButton)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(saveButton)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void initDefaultValues() {
		if (originalItem == null) {
			return;
		}
		
		nameField.setText(originalItem.getName());
		priceField.setValue(originalItem.getPrice());
		descriptionField.setText(originalItem.getDescription());
	}
	
	private void error(String message) {
		JOptionPane.showMessageDialog(
				this, message, "Error",
				JOptionPane.ERROR_MESSAGE);
	}
	
    private void saveButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        String name = nameField.getText();
		double price = (double) priceField.getValue();
		String description = descriptionField.getText();
		
		if (name.isEmpty()) {
			error("Name must not be empty!");
			return;
		}
		
		if (price < 0) {
			error("Price must not be negative.");
			return;
		}
		
		if (description.isEmpty()) {
			error("Description must not be empty.");
			return;
		}
		
		returnedItem = new MenuItem(name, price, description);
		dispose();
    }//GEN-LAST:event_saveButtonActionPerformed

    private void cancelButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        returnedItem = null;
		
		dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

	public MenuItem getOriginalItem() {
		return originalItem;
	}

	public MenuItem getReturnedItem() {
		return returnedItem;
	}
	
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton cancelButton;
    private JTextArea descriptionField;
    private JLabel descriptionLabel;
    private JScrollPane descriptionScroll;
    private JTextField nameField;
    private JLabel nameLabel;
    private JSpinner priceField;
    private JLabel priceLabel;
    private JButton saveButton;
    // End of variables declaration//GEN-END:variables
}
