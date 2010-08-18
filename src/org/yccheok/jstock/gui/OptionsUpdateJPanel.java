/*
 * OptionsSpeedJPanel.java
 *
 * Created on June 19, 2007, 8:42 PM
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or (at
 * your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 * Copyright (C) 2008 Yan Cheng Cheok <yccheok@yahoo.com>
 */

package org.yccheok.jstock.gui;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.yccheok.jstock.internationalization.MessagesBundle;

/**
 *
 * @author yccheok
 */
public class OptionsUpdateJPanel extends javax.swing.JPanel implements JStockOptionsObserver {

    /** Creates new form OptionsUpdateJPanel */
    public OptionsUpdateJPanel() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jXHeader1 = new org.jdesktop.swingx.JXHeader();
        jPanel1 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/yccheok/jstock/data/gui"); // NOI18N
        jXHeader1.setDescription(bundle.getString("OptionsUpdateJPanel_Description")); // NOI18N
        jXHeader1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/32x32/epiphany-download.png"))); // NOI18N
        jXHeader1.setTitle(bundle.getString("OptionsUpdateJPanel_Update")); // NOI18N
        add(jXHeader1, java.awt.BorderLayout.NORTH);

        jCheckBox1.setText(bundle.getString("OptionsUpdateJPanel_InformMe")); // NOI18N
        jCheckBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox1ItemStateChanged(evt);
            }
        });

        jButton1.setText(bundle.getString("OptionsUpdateJPanel_ShowLastUpdateNews")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/16x16/spinner.gif"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7))
                    .addComponent(jCheckBox1))
                .addContainerGap(145, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addComponent(jLabel7))
                .addContainerGap(179, Short.MAX_VALUE))
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
        // TODO add your handling code here:
        this.updateGUIState();
    }//GEN-LAST:event_jCheckBox1ItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.latestNewsTaskSwingWorker = getLatestNewsTaskSwingWorker();
        this.updateGUIState();
        this.jButton1.requestFocus();
        this.latestNewsTaskSwingWorker.execute();
    }//GEN-LAST:event_jButton1ActionPerformed

    private SwingWorker getLatestNewsTaskSwingWorker() {
        SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {

            @Override
            protected void done() {            
                // The done Method: When you are informed that the SwingWorker
                // is done via a property change or via the SwingWorker object's
                // done method, you need to be aware that the get methods can
                // throw a CancellationException. A CancellationException is a
                // RuntimeException, which means you do not need to declare it
                // thrown and you do not need to catch it. Instead, you should
                // test the SwingWorker using the isCancelled method before you
                // use the get method.
                if (this.isCancelled()) {
                    // Cancelled by user explicitly. Do not perform any GUI update.
                    // No pop-up message.
                    return;
                }

                String status = null;
                try {
                    status = get();
                } catch (InterruptedException ex) {
                    log.error(null, ex);
                } catch (ExecutionException ex) {
                    log.error(null, ex);
                } catch (CancellationException ex) {
                    // Some developers suggest to catch this exception, instead of
                    // checking on isCancelled. As I am not confident by merely
                    // isCancelled check can prevent CancellationException (What
                    // if cancellation is happen just after isCancelled check?),
                    // I will apply both techniques. 
                    log.error(null, ex);
                }

                OptionsUpdateJPanel.this.updateGUIState();

                if (status == null)
                {
                    JOptionPane.showMessageDialog(OptionsUpdateJPanel.this, MessagesBundle.getString("error_message_error_in_getting_latest_news"), MessagesBundle.getString("error_title_error_in_getting_latest_news"), JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    AutoUpdateNewsJDialog dialog = new AutoUpdateNewsJDialog(MainFrame.getInstance(), true);
                    dialog.setNews(status);
                    dialog.setVisible(true);
                }
            }

            @Override
            protected String doInBackground() {
                if (!isCancelled())
                {
                    final java.util.Map<String, String> map = Utils.getUUIDValue(org.yccheok.jstock.engine.Utils.getJStockStaticServer() + "news_information/index.txt");
                    final String location = map.get("news_url");
                    if (location == null) {
                        return null;
                    }
                    final String respond = org.yccheok.jstock.gui.Utils.getResponseBodyAsStringBasedOnProxyAuthOption(location);
                    if (respond == null)
                    {
                        return null;
                    }
                    if (respond.indexOf(Utils.getJStockUUID()) < 0)
                    {
                        return null;
                    }
                    return respond;
                }
                return null;
            }

        };
        return worker;
    }
    private static final Log log = LogFactory.getLog(OptionsUpdateJPanel.class);
    private volatile SwingWorker latestNewsTaskSwingWorker = null;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private org.jdesktop.swingx.JXHeader jXHeader1;
    // End of variables declaration//GEN-END:variables

    private void updateGUIState() {
        final boolean isLatestNewsTaskDone = (this.latestNewsTaskSwingWorker == null || this.latestNewsTaskSwingWorker.isDone());
        this.jLabel7.setVisible(!isLatestNewsTaskDone);
        this.jCheckBox1.setEnabled(isLatestNewsTaskDone);
        this.jButton1.setEnabled(jCheckBox1.isSelected() && isLatestNewsTaskDone);
    }

    public void cancel() {
        if (this.latestNewsTaskSwingWorker != null) {
            this.latestNewsTaskSwingWorker.cancel(true);
        }
    }

    @Override
    public void set(JStockOptions jStockOptions) {
        jCheckBox1.setSelected(jStockOptions.isAutoUpdateNewsEnabled());
        this.updateGUIState();
    }

    @Override
    public boolean apply(JStockOptions jStockOptions) {
        jStockOptions.setAutoUpdateNewsEnabled(jCheckBox1.isSelected());
        // Apply the effect immediately.
        MainFrame.getInstance().initLatestNewsTask();
        return true;
    }

}
