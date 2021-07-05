/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsm.rmichat;

import javax.swing.JButton;

public class getNameDialog extends javax.swing.JDialog {
    
    private String name;

    public String getName() {
        return name;
    }

    public getNameDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nameDialog = new javax.swing.JPanel();
        nameInput = new javax.swing.JTextField();
        nameConfirm = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        nameDialog.setBorder(javax.swing.BorderFactory.createTitledBorder("Insira seu Nome:"));

        nameConfirm.setText("Confirm");
        nameConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameConfirmActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout nameDialogLayout = new javax.swing.GroupLayout(nameDialog);
        nameDialog.setLayout(nameDialogLayout);
        nameDialogLayout.setHorizontalGroup(
            nameDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nameDialogLayout.createSequentialGroup()
                .addComponent(nameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameConfirm)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        nameDialogLayout.setVerticalGroup(
            nameDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nameDialogLayout.createSequentialGroup()
                .addGroup(nameDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameConfirm))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(nameDialog, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(nameDialog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nameConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameConfirmActionPerformed
        this.name = nameInput.getText();
        this.dispose();
    }//GEN-LAST:event_nameConfirmActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton nameConfirm;
    private javax.swing.JPanel nameDialog;
    private javax.swing.JTextField nameInput;
    // End of variables declaration//GEN-END:variables
}
