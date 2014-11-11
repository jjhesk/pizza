package edu.colostate.cs414.d.pizza.ui.special;

import edu.colostate.cs414.d.pizza.Kiosk;
import edu.colostate.cs414.d.pizza.api.menu.DailySpecial;
import edu.colostate.cs414.d.pizza.api.menu.MenuItem;
import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class DailySpecialAdminDialog extends JDialog {

	private final Kiosk kiosk;
	
	private final Map<DailySpecial, JPanel> panelMap;
	
    /**
     * Creates new form DailySpecialAdminDialog
     */
    public DailySpecialAdminDialog(Dialog parent) {
        super(parent, true);
        
		kiosk = Kiosk.getInstance();
		
		panelMap = new HashMap<>();
		
        initComponents();
		initSpecials();
		
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

        closeButton = new JButton();
        specialScroll = new JScrollPane();
        specialsPanel = new JPanel();
        createSpecialButton = new JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit Daily Specials");

        closeButton.setText("Close");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        specialScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        specialsPanel.setLayout(new GridLayout(0, 1));
        specialScroll.setViewportView(specialsPanel);

        createSpecialButton.setText("Create Special");
        createSpecialButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                createSpecialButtonActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(createSpecialButton)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 262, Short.MAX_VALUE)
                .addComponent(closeButton)
                .addContainerGap())
            .addComponent(specialScroll)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(specialScroll, GroupLayout.PREFERRED_SIZE, 297, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(closeButton)
                    .addComponent(createSpecialButton))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        dispose();
    }//GEN-LAST:event_closeButtonActionPerformed

    private void createSpecialButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_createSpecialButtonActionPerformed
        showEditDialog();
    }//GEN-LAST:event_createSpecialButtonActionPerformed

	private void showEditDialog(DailySpecial special) {
		DailySpecialEditDialog d = new DailySpecialEditDialog(this,	special);
		d.setVisible(true);
		
		DailySpecial newSpecial = d.getReturnedSpecial();
		
		// make sure the user accepted the dialog
		if (newSpecial != null) {
			// meh.
			newSpecial = kiosk.createDailySpecial(newSpecial.getItems(), newSpecial.getPrice());
			
			// invalidate the old special, if any
			if (special != null) {
				kiosk.removeDailySpecial(special);
				
				// remove the old GUI panel
				JPanel panel = panelMap.get(special);
				specialsPanel.remove(panel);
			}
			
			// add the new special
			JPanel newPanel = createSpecialPanel(newSpecial);
			specialsPanel.add(newPanel);
			panelMap.put(newSpecial, newPanel);
			
			specialsPanel.revalidate();
			specialsPanel.repaint();
		}
	}
	
	private void showEditDialog() {
		showEditDialog(null);
	}
	
	private JPanel createSpecialPanel(final DailySpecial special) {
		final JPanel ret = new JPanel(new BorderLayout());
		ret.setBorder(BorderFactory.createEtchedBorder());
		
		JPanel left = new JPanel();
		left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
		
		for (MenuItem item : special.getItems()) {
			JLabel l = new JLabel(String.format(
					"<html><ul><li>%s</li></ul>", item.getName()));
			l.setHorizontalAlignment(SwingConstants.LEFT);
			left.add(l);
		
		}
		ret.add(left, BorderLayout.CENTER);
		
		JPanel buttons = new JPanel(new FlowLayout());
		
		JButton editButton = new JButton("Edit");
		editButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showEditDialog(special);
			}
			
		});
		buttons.add(editButton);
		
		JButton removeButton = new JButton("Remove");
		removeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				kiosk.removeDailySpecial(special);
				specialsPanel.remove(ret);
				
				specialsPanel.revalidate();
				specialsPanel.repaint();
			}
			
		});
		buttons.add(removeButton);
		
		ret.add(buttons, BorderLayout.SOUTH);
		
		JLabel priceLabel = new JLabel(String.format("$%.2f", special.getPrice()));
		priceLabel.setFont(priceLabel.getFont().deriveFont(Font.BOLD, 28));
		priceLabel.setPreferredSize(new Dimension(150, 0));
		priceLabel.setVerticalAlignment(SwingConstants.CENTER);
		priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ret.add(priceLabel, BorderLayout.EAST);
		
		return ret;
	}
	
	private void initSpecials() {
		for (DailySpecial s : kiosk.viewDailySpecials()) {
			JPanel panel = createSpecialPanel(s);
			specialsPanel.add(panel);
			panelMap.put(s, panel);
		}
	}
	
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton closeButton;
    private JButton createSpecialButton;
    private JScrollPane specialScroll;
    private JPanel specialsPanel;
    // End of variables declaration//GEN-END:variables
}