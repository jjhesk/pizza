package edu.colostate.cs414.d.pizza.ui;

import edu.colostate.cs414.d.pizza.Kiosk;
import edu.colostate.cs414.d.pizza.api.user.User;
import edu.colostate.cs414.d.pizza.client.WebServiceException;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.WindowConstants;

/**
 *
 * @author Rawlin
 */
public class LoginDialog extends JDialog {
    
    private final Kiosk kiosk;

    /**
     * Creates new form LoginDialog
     */
    public LoginDialog(Frame parent, Kiosk kiosk) {
        super(parent, true);
        this.kiosk = kiosk;
        initComponents();
        
        setLocationRelativeTo(parent);
    }
    
    private void error(String message) {
        JOptionPane.showMessageDialog(
                this, message, "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loginLabel = new JLabel();
        loginPanel = new JPanel();
        usernameLabel = new JLabel();
        passwordLabel = new JLabel();
        usernameTextfield = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("User Login");

        loginLabel.setText("Login");

        loginPanel.setBorder(BorderFactory.createEtchedBorder());

        usernameLabel.setText("Username: ");

        passwordLabel.setText("Password: ");

        GroupLayout loginPanelLayout = new GroupLayout(loginPanel);
        loginPanel.setLayout(loginPanelLayout);
        loginPanelLayout.setHorizontalGroup(
            loginPanelLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(loginPanelLayout.createParallelGroup(Alignment.LEADING)
                    .addComponent(passwordLabel)
                    .addComponent(usernameLabel))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(loginPanelLayout.createParallelGroup(Alignment.LEADING, false)
                    .addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(usernameTextfield))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        loginPanelLayout.setVerticalGroup(
            loginPanelLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(loginPanelLayout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(usernameLabel)
                    .addComponent(usernameTextfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(loginPanelLayout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(passwordLabel)
                    .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        loginButton.setText("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(Alignment.TRAILING)
                    .addComponent(loginButton)
                    .addGroup(layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(loginPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(loginLabel)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(loginLabel)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(loginPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(loginButton)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        if (usernameTextfield.getText().isEmpty()) {
            error("You must enter a username.");
            return;
        }
        
        if (passwordField.getPassword().length == 0) {
            error("You must enter a password.");
            return;
        }
        
        User loggedInUser = null;
        try {
            loggedInUser = kiosk.authenticateUser(
                usernameTextfield.getText(),
                new String(passwordField.getPassword()));
        } catch(WebServiceException e) {
            // do nothing
        }
        if (loggedInUser == null) {
            error("Login failed. Please try again.");
            return;
        }
        
        kiosk.loginUser(loggedInUser);
        
        dispose();
    }//GEN-LAST:event_loginButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton loginButton;
    private JLabel loginLabel;
    private JPanel loginPanel;
    private JPasswordField passwordField;
    private JLabel passwordLabel;
    private JLabel usernameLabel;
    private JTextField usernameTextfield;
    // End of variables declaration//GEN-END:variables
}
