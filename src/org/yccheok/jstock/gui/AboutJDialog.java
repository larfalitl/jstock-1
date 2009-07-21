/*
 * AboutJDialog.java
 *
 * Created on July 5, 2007, 1:01 AM
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
 * Copyright (C) 2009 Yan Cheng Cheok <yccheok@yahoo.com>
 */

package org.yccheok.jstock.gui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.event.HyperlinkEvent;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.divxdede.swing.busy.JBusyComponent;
import org.yccheok.blobsallad.Controller;

/**
 *
 * @author  yccheok
 */
public class AboutJDialog extends javax.swing.JDialog {
    private final Thread checkForUpdateThread = new Thread(new Runnable() {
        @Override
        public void run() {
            Utils.ApplicationInfo applicationInfo = Utils.getLatestApplicationInfo();

            if (applicationInfo == null) {
                // Unknown.                
                updateCheckForUpdateStatus("<p style=\"margin-top: 0; font-size: 9px; font-family: Tahoma;\"><b>Unknown update status.</b></p>");
            }
            else if (applicationInfo.applicationVersionID <= Utils.getApplicationVersionID()) {
                // Update to date.
                updateCheckForUpdateStatus("<p style=\"margin-top: 0; font-size: 9px; font-family: Tahoma; color: green\"><b>JStock is up to date.</b></p>");
            }
            else {
                // Out date.
                String url = applicationInfo.windowsDownloadLink;
                final String name = System.getProperty("os.name").toLowerCase();
                
                // http://lopica.sourceforge.net/os.html
                if (name.startsWith("linux")) {
                    url = applicationInfo.linuxDownloadLink;
                }
                else if (name.startsWith("mac")) {
                    url = applicationInfo.macDownloadLink;
                }
                else if (name.startsWith("solaris") || name.startsWith("sun")) {
                    url = applicationInfo.solarisDownloadLink;
                }

                // Due to the bug in jbusycomponent, we will not see hand cursor, when we move the mouse over the download link.
                // I had tried to remove the previous jbusycomponent, and add with the new editor pane. However, this will increase
                // the chance of having flicking displayed window.
                updateCheckForUpdateStatus("<p style=\"margin-top: 0; font-size: 9px; font-family: Tahoma;\"><b>JStock is not up-to-date. Click <a href=\"" + url + "\">here</a> to update.</b></p>");
            }
        }
    });

    // WILL BE ONLY CALLED ONCE!
    private void updateCheckForUpdateStatus(final String string)
    {
        jEditorPane2.setText("<html><head></head><body></body></html>");
        HTMLDocument htmlDocument = (HTMLDocument)jEditorPane2.getDocument();

        javax.swing.text.Element html = htmlDocument.getRootElements()[0];
        javax.swing.text.Element body = null;
        final int count = html.getElementCount();

        for (int i = 0; i < count; i++) {
            javax.swing.text.Element tmp = html.getElement(i);
            if (tmp.getName().equalsIgnoreCase("body"))
            {
                body = tmp;
                break;
            }
        }

        if (body == null) return;

        try {
            htmlDocument.insertAfterStart(body, string);
        } catch (BadLocationException ex) {
            log.error(null, ex);
        } catch (IOException ex) {
            log.error(null, ex);
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Must be called within GUI event dispatch thread.
                // This is to reduce chance of having flicking displayed window.
                // I am not sure why this happen.
                busyComponent.setBusy(false);
            }
        });
    }

    /** Creates new form AboutJDialog */
    public AboutJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
   }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        busyComponent = new JBusyComponent<JPanel>(jPanel2);
        busyComponent.setBusy(true);
        checkForUpdateThread.start();
        jEditorPane2 = new javax.swing.JEditorPane();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jEditorPane3 = new javax.swing.JEditorPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = controller.getView();
        jPanel8 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        jEditorPane2.setBackground(new java.awt.Color(240, 240, 240));
        jEditorPane2.setContentType("text/html");
        jEditorPane2.setEditable(false);
        jEditorPane2.setText("<html>\n<head>\n</head>\n    <body>\n        <p style=\"margin-top: 0; font-size: 9px; font-family: Tahoma;\"><b>Checking for updates...</b></p>\n    </body>\n</html>");
        jEditorPane2.addHyperlinkListener(new javax.swing.event.HyperlinkListener() {
            public void hyperlinkUpdate(javax.swing.event.HyperlinkEvent evt) {
                jEditorPane1HyperlinkUpdate(evt);
            }
        });
        jPanel2.add(jEditorPane2);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("About JStock");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);

        getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);

        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jPanel3.setLayout(new java.awt.BorderLayout(2, 2));

        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/splash/jstock2-wording.png"))); // NOI18N
        jLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 0, 5));
        jPanel3.add(jLabel1, java.awt.BorderLayout.CENTER);

        jPanel9.setLayout(new java.awt.GridLayout(2, 0));

        jEditorPane3.setBackground(new java.awt.Color(240, 240, 240));
        jEditorPane3.setContentType("text/html");
        jEditorPane3.setEditable(false);
        jEditorPane3.setText("<html>\r\n  <head>\r\n\r\n  </head>\r\n  <body>\r\n        <p style=\"margin-top: 0; font-size: 9px; font-family: Tahoma;\">Homepage : <a href=\"http://jstock.sourceforge.net\">http://jstock.sourceforge.net</a></p>\n  </body>\r\n</html>\r\n");
        jEditorPane3.addHyperlinkListener(new javax.swing.event.HyperlinkListener() {
            public void hyperlinkUpdate(javax.swing.event.HyperlinkEvent evt) {
                jEditorPane1HyperlinkUpdate(evt);
            }
        });
        jPanel10.add(jEditorPane3);

        jPanel9.add(jPanel10);

        jPanel9.add(busyComponent);
        jPanel3.add(jPanel9, java.awt.BorderLayout.SOUTH);

        jTabbedPane1.addTab("General", jPanel3);

        jPanel4.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBorder(null);

        jEditorPane1.setContentType("text/html");
        jEditorPane1.setEditable(false);
        jEditorPane1.setText("<html>\n<head>\n</head>\n    <body>\n        <p style=\"margin-top: 10; font-size: 9px; font-family: Tahoma;\"><u>Team Members of JStock Open Source Project :</u></p>\n        <p style=\"margin-top: 10; font-size: 9px; font-family: Tahoma;\"><b>Yan Cheng Cheok</b>, <a href=\"mailto:yccheok@yahoo.com\">yccheok@yahoo.com</a>, Project admin and lead programmer.</p>\n        <p style=\"margin-top: 10; font-size: 9px; font-family: Tahoma;\"><b><a href=\"http://www.iconblock.com/\">The IconBlock Ltd</a></b>, Interface and icon design.</p>\n        <p style=\"margin-top: 10; font-size: 9px; font-family: Tahoma;\"><b>Peter Gransdorfer</b>, Programmer.</p>\n        <p style=\"margin-top: 10; font-size: 9px; font-family: Tahoma;\"><b>Karl Heinz Putz</b>, Mac packager.</p>\n        <p style=\"margin-top: 10; font-size: 9px; font-family: Tahoma;\"><b>Carlo de Wolf</b>, Code contributor.</p>\n        <p style=\"margin-top: 10; font-size: 9px; font-family: Tahoma;\"><b>sytee</b>, Web programmer.</p>\n        <br>\n        <br>\n        <p style=\"margin-top: 10; font-size: 9px; font-family: Tahoma;\"><u>Used Libraries :</u></p>\n        <p style=\"margin-top: 10; font-size: 9px; font-family: Tahoma;\">\n        <a href=\"http://commons.apache.org/logging/\">Apache Common Logging</a> - Logging.<br>\n        <a href=\"http://hc.apache.org/httpclient-3.x/\">Apache HttpClient</a> - Http communication.<br>\n        <a href=\"http://poi.apache.org/\">Apache POI</a> - Excel file format.<br>\n        <a href=\"http://jstock.sourceforge.net/blobsallad/\">blobsallad</a> - Java ported blob sallad by Yan Cheng Cheok. Original JavaScript version written by Bj�rn Lindberg.<br>\n        <a href=\"http://code.google.com/p/jbusycomponent/\">jbusycomponent</a> - Swing component with busy state.<br>\n        <a href=\"http://www.jfree.org/jfreechart/\">JFreeChart</a> - Charting.<br>\n        <a href=\"https://sourceforge.net/projects/jhotdraw/\">JHotDraw</a> - Indicator editor drawing.<br>\n        <a href=\"http://www.l2fprod.com/\">L2fProd</a> - Flashy looking GUI.<br>\n        <a href=\"http://www.igniterealtime.org/projects/smack/index.jsp\">Jasypt</a> - Encryption.<br>\n        <a href=\"http://nachocalendar.sourceforge.net/\">NachoCalendar</a> - Calendar GUI component.<br>\n        <a href=\"http://opencsv.sourceforge.net/\">opencsv</a> - CSV file format.<br>\n        <a href=\"http://www.igniterealtime.org/projects/smack/index.jsp\">Smack</a> - XMPP messaging.<br>\n        <a href=\"https://swingx.dev.java.net/\">SwingX</a> - Flashy looking GUI.<br>\n        <a href=\"http://xstream.codehaus.org/\">XStream</a> - Serialization.<br>\n        </p>\n        <br>\n        <br>\n        <hr>\n        <p style=\"margin-top: 10; font-size: 8px; font-family: Tahoma;\">Wish to see your name being mentioned here? Feel free to visit <a href=\"http://sourceforge.net/projects/jstock\">http://sourceforge.net/projects/jstock</a> to see how you can contribute to JStock Open Source Project.</p>\n    </body>\n</html>");
        jEditorPane1.addHyperlinkListener(new javax.swing.event.HyperlinkListener() {
            public void hyperlinkUpdate(javax.swing.event.HyperlinkEvent evt) {
                jEditorPane1HyperlinkUpdate(evt);
            }
        });
        jScrollPane1.setViewportView(jEditorPane1);
        jEditorPane1.setCaretPosition(0);

        jPanel4.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Credits", jPanel4);

        jPanel5.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setBorder(null);

        jTextArea1.setColumns(20);
        jTextArea1.setEditable(false);
        jTextArea1.setFont(new java.awt.Font("Monospaced", 0, 12));
        jTextArea1.setRows(5);
        jTextArea1.setText("\t\t    GNU GENERAL PUBLIC LICENSE\n\t\t       Version 2, June 1991\n\n Copyright (C) 1989, 1991 Free Software Foundation, Inc.,\n 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA\n Everyone is permitted to copy and distribute verbatim copies\n of this license document, but changing it is not allowed.\n\n\t\t\t    Preamble\n\n  The licenses for most software are designed to take away your\nfreedom to share and change it.  By contrast, the GNU General Public\nLicense is intended to guarantee your freedom to share and change free\nsoftware--to make sure the software is free for all its users.  This\nGeneral Public License applies to most of the Free Software\nFoundation's software and to any other program whose authors commit to\nusing it.  (Some other Free Software Foundation software is covered by\nthe GNU Lesser General Public License instead.)  You can apply it to\nyour programs, too.\n\n  When we speak of free software, we are referring to freedom, not\nprice.  Our General Public Licenses are designed to make sure that you\nhave the freedom to distribute copies of free software (and charge for\nthis service if you wish), that you receive source code or can get it\nif you want it, that you can change the software or use pieces of it\nin new free programs; and that you know you can do these things.\n\n  To protect your rights, we need to make restrictions that forbid\nanyone to deny you these rights or to ask you to surrender the rights.\nThese restrictions translate to certain responsibilities for you if you\ndistribute copies of the software, or if you modify it.\n\n  For example, if you distribute copies of such a program, whether\ngratis or for a fee, you must give the recipients all the rights that\nyou have.  You must make sure that they, too, receive or can get the\nsource code.  And you must show them these terms so they know their\nrights.\n\n  We protect your rights with two steps: (1) copyright the software, and\n(2) offer you this license which gives you legal permission to copy,\ndistribute and/or modify the software.\n\n  Also, for each author's protection and ours, we want to make certain\nthat everyone understands that there is no warranty for this free\nsoftware.  If the software is modified by someone else and passed on, we\nwant its recipients to know that what they have is not the original, so\nthat any problems introduced by others will not reflect on the original\nauthors' reputations.\n\n  Finally, any free program is threatened constantly by software\npatents.  We wish to avoid the danger that redistributors of a free\nprogram will individually obtain patent licenses, in effect making the\nprogram proprietary.  To prevent this, we have made it clear that any\npatent must be licensed for everyone's free use or not licensed at all.\n\n  The precise terms and conditions for copying, distribution and\nmodification follow.\n\n\t\t    GNU GENERAL PUBLIC LICENSE\n   TERMS AND CONDITIONS FOR COPYING, DISTRIBUTION AND MODIFICATION\n\n  0. This License applies to any program or other work which contains\na notice placed by the copyright holder saying it may be distributed\nunder the terms of this General Public License.  The \"Program\", below,\nrefers to any such program or work, and a \"work based on the Program\"\nmeans either the Program or any derivative work under copyright law:\nthat is to say, a work containing the Program or a portion of it,\neither verbatim or with modifications and/or translated into another\nlanguage.  (Hereinafter, translation is included without limitation in\nthe term \"modification\".)  Each licensee is addressed as \"you\".\n\nActivities other than copying, distribution and modification are not\ncovered by this License; they are outside its scope.  The act of\nrunning the Program is not restricted, and the output from the Program\nis covered only if its contents constitute a work based on the\nProgram (independent of having been made by running the Program).\nWhether that is true depends on what the Program does.\n\n  1. You may copy and distribute verbatim copies of the Program's\nsource code as you receive it, in any medium, provided that you\nconspicuously and appropriately publish on each copy an appropriate\ncopyright notice and disclaimer of warranty; keep intact all the\nnotices that refer to this License and to the absence of any warranty;\nand give any other recipients of the Program a copy of this License\nalong with the Program.\n\nYou may charge a fee for the physical act of transferring a copy, and\nyou may at your option offer warranty protection in exchange for a fee.\n\n  2. You may modify your copy or copies of the Program or any portion\nof it, thus forming a work based on the Program, and copy and\ndistribute such modifications or work under the terms of Section 1\nabove, provided that you also meet all of these conditions:\n\n    a) You must cause the modified files to carry prominent notices\n    stating that you changed the files and the date of any change.\n\n    b) You must cause any work that you distribute or publish, that in\n    whole or in part contains or is derived from the Program or any\n    part thereof, to be licensed as a whole at no charge to all third\n    parties under the terms of this License.\n\n    c) If the modified program normally reads commands interactively\n    when run, you must cause it, when started running for such\n    interactive use in the most ordinary way, to print or display an\n    announcement including an appropriate copyright notice and a\n    notice that there is no warranty (or else, saying that you provide\n    a warranty) and that users may redistribute the program under\n    these conditions, and telling the user how to view a copy of this\n    License.  (Exception: if the Program itself is interactive but\n    does not normally print such an announcement, your work based on\n    the Program is not required to print an announcement.)\n\nThese requirements apply to the modified work as a whole.  If\nidentifiable sections of that work are not derived from the Program,\nand can be reasonably considered independent and separate works in\nthemselves, then this License, and its terms, do not apply to those\nsections when you distribute them as separate works.  But when you\ndistribute the same sections as part of a whole which is a work based\non the Program, the distribution of the whole must be on the terms of\nthis License, whose permissions for other licensees extend to the\nentire whole, and thus to each and every part regardless of who wrote it.\n\nThus, it is not the intent of this section to claim rights or contest\nyour rights to work written entirely by you; rather, the intent is to\nexercise the right to control the distribution of derivative or\ncollective works based on the Program.\n\nIn addition, mere aggregation of another work not based on the Program\nwith the Program (or with a work based on the Program) on a volume of\na storage or distribution medium does not bring the other work under\nthe scope of this License.\n\n  3. You may copy and distribute the Program (or a work based on it,\nunder Section 2) in object code or executable form under the terms of\nSections 1 and 2 above provided that you also do one of the following:\n\n    a) Accompany it with the complete corresponding machine-readable\n    source code, which must be distributed under the terms of Sections\n    1 and 2 above on a medium customarily used for software interchange; or,\n\n    b) Accompany it with a written offer, valid for at least three\n    years, to give any third party, for a charge no more than your\n    cost of physically performing source distribution, a complete\n    machine-readable copy of the corresponding source code, to be\n    distributed under the terms of Sections 1 and 2 above on a medium\n    customarily used for software interchange; or,\n\n    c) Accompany it with the information you received as to the offer\n    to distribute corresponding source code.  (This alternative is\n    allowed only for noncommercial distribution and only if you\n    received the program in object code or executable form with such\n    an offer, in accord with Subsection b above.)\n\nThe source code for a work means the preferred form of the work for\nmaking modifications to it.  For an executable work, complete source\ncode means all the source code for all modules it contains, plus any\nassociated interface definition files, plus the scripts used to\ncontrol compilation and installation of the executable.  However, as a\nspecial exception, the source code distributed need not include\nanything that is normally distributed (in either source or binary\nform) with the major components (compiler, kernel, and so on) of the\noperating system on which the executable runs, unless that component\nitself accompanies the executable.\n\nIf distribution of executable or object code is made by offering\naccess to copy from a designated place, then offering equivalent\naccess to copy the source code from the same place counts as\ndistribution of the source code, even though third parties are not\ncompelled to copy the source along with the object code.\n\n  4. You may not copy, modify, sublicense, or distribute the Program\nexcept as expressly provided under this License.  Any attempt\notherwise to copy, modify, sublicense or distribute the Program is\nvoid, and will automatically terminate your rights under this License.\nHowever, parties who have received copies, or rights, from you under\nthis License will not have their licenses terminated so long as such\nparties remain in full compliance.\n\n  5. You are not required to accept this License, since you have not\nsigned it.  However, nothing else grants you permission to modify or\ndistribute the Program or its derivative works.  These actions are\nprohibited by law if you do not accept this License.  Therefore, by\nmodifying or distributing the Program (or any work based on the\nProgram), you indicate your acceptance of this License to do so, and\nall its terms and conditions for copying, distributing or modifying\nthe Program or works based on it.\n\n  6. Each time you redistribute the Program (or any work based on the\nProgram), the recipient automatically receives a license from the\noriginal licensor to copy, distribute or modify the Program subject to\nthese terms and conditions.  You may not impose any further\nrestrictions on the recipients' exercise of the rights granted herein.\nYou are not responsible for enforcing compliance by third parties to\nthis License.\n\n  7. If, as a consequence of a court judgment or allegation of patent\ninfringement or for any other reason (not limited to patent issues),\nconditions are imposed on you (whether by court order, agreement or\notherwise) that contradict the conditions of this License, they do not\nexcuse you from the conditions of this License.  If you cannot\ndistribute so as to satisfy simultaneously your obligations under this\nLicense and any other pertinent obligations, then as a consequence you\nmay not distribute the Program at all.  For example, if a patent\nlicense would not permit royalty-free redistribution of the Program by\nall those who receive copies directly or indirectly through you, then\nthe only way you could satisfy both it and this License would be to\nrefrain entirely from distribution of the Program.\n\nIf any portion of this section is held invalid or unenforceable under\nany particular circumstance, the balance of the section is intended to\napply and the section as a whole is intended to apply in other\ncircumstances.\n\nIt is not the purpose of this section to induce you to infringe any\npatents or other property right claims or to contest validity of any\nsuch claims; this section has the sole purpose of protecting the\nintegrity of the free software distribution system, which is\nimplemented by public license practices.  Many people have made\ngenerous contributions to the wide range of software distributed\nthrough that system in reliance on consistent application of that\nsystem; it is up to the author/donor to decide if he or she is willing\nto distribute software through any other system and a licensee cannot\nimpose that choice.\n\nThis section is intended to make thoroughly clear what is believed to\nbe a consequence of the rest of this License.\n\n  8. If the distribution and/or use of the Program is restricted in\ncertain countries either by patents or by copyrighted interfaces, the\noriginal copyright holder who places the Program under this License\nmay add an explicit geographical distribution limitation excluding\nthose countries, so that distribution is permitted only in or among\ncountries not thus excluded.  In such case, this License incorporates\nthe limitation as if written in the body of this License.\n\n  9. The Free Software Foundation may publish revised and/or new versions\nof the General Public License from time to time.  Such new versions will\nbe similar in spirit to the present version, but may differ in detail to\naddress new problems or concerns.\n\nEach version is given a distinguishing version number.  If the Program\nspecifies a version number of this License which applies to it and \"any\nlater version\", you have the option of following the terms and conditions\neither of that version or of any later version published by the Free\nSoftware Foundation.  If the Program does not specify a version number of\nthis License, you may choose any version ever published by the Free Software\nFoundation.\n\n  10. If you wish to incorporate parts of the Program into other free\nprograms whose distribution conditions are different, write to the author\nto ask for permission.  For software which is copyrighted by the Free\nSoftware Foundation, write to the Free Software Foundation; we sometimes\nmake exceptions for this.  Our decision will be guided by the two goals\nof preserving the free status of all derivatives of our free software and\nof promoting the sharing and reuse of software generally.\n\n\t\t\t    NO WARRANTY\n\n  11. BECAUSE THE PROGRAM IS LICENSED FREE OF CHARGE, THERE IS NO WARRANTY\nFOR THE PROGRAM, TO THE EXTENT PERMITTED BY APPLICABLE LAW.  EXCEPT WHEN\nOTHERWISE STATED IN WRITING THE COPYRIGHT HOLDERS AND/OR OTHER PARTIES\nPROVIDE THE PROGRAM \"AS IS\" WITHOUT WARRANTY OF ANY KIND, EITHER EXPRESSED\nOR IMPLIED, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF\nMERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE.  THE ENTIRE RISK AS\nTO THE QUALITY AND PERFORMANCE OF THE PROGRAM IS WITH YOU.  SHOULD THE\nPROGRAM PROVE DEFECTIVE, YOU ASSUME THE COST OF ALL NECESSARY SERVICING,\nREPAIR OR CORRECTION.\n\n  12. IN NO EVENT UNLESS REQUIRED BY APPLICABLE LAW OR AGREED TO IN WRITING\nWILL ANY COPYRIGHT HOLDER, OR ANY OTHER PARTY WHO MAY MODIFY AND/OR\nREDISTRIBUTE THE PROGRAM AS PERMITTED ABOVE, BE LIABLE TO YOU FOR DAMAGES,\nINCLUDING ANY GENERAL, SPECIAL, INCIDENTAL OR CONSEQUENTIAL DAMAGES ARISING\nOUT OF THE USE OR INABILITY TO USE THE PROGRAM (INCLUDING BUT NOT LIMITED\nTO LOSS OF DATA OR DATA BEING RENDERED INACCURATE OR LOSSES SUSTAINED BY\nYOU OR THIRD PARTIES OR A FAILURE OF THE PROGRAM TO OPERATE WITH ANY OTHER\nPROGRAMS), EVEN IF SUCH HOLDER OR OTHER PARTY HAS BEEN ADVISED OF THE\nPOSSIBILITY OF SUCH DAMAGES.\n\n\t\t     END OF TERMS AND CONDITIONS\n\n\t    How to Apply These Terms to Your New Programs\n\n  If you develop a new program, and you want it to be of the greatest\npossible use to the public, the best way to achieve this is to make it\nfree software which everyone can redistribute and change under these terms.\n\n  To do so, attach the following notices to the program.  It is safest\nto attach them to the start of each source file to most effectively\nconvey the exclusion of warranty; and each file should have at least\nthe \"copyright\" line and a pointer to where the full notice is found.\n\n    <one line to give the program's name and a brief idea of what it does.>\n    Copyright (C) <year>  <name of author>\n\n    This program is free software; you can redistribute it and/or modify\n    it under the terms of the GNU General Public License as published by\n    the Free Software Foundation; either version 2 of the License, or\n    (at your option) any later version.\n\n    This program is distributed in the hope that it will be useful,\n    but WITHOUT ANY WARRANTY; without even the implied warranty of\n    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the\n    GNU General Public License for more details.\n\n    You should have received a copy of the GNU General Public License along\n    with this program; if not, write to the Free Software Foundation, Inc.,\n    51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.\n\nAlso add information on how to contact you by electronic and paper mail.\n\nIf the program is interactive, make it output a short notice like this\nwhen it starts in an interactive mode:\n\n    Gnomovision version 69, Copyright (C) year name of author\n    Gnomovision comes with ABSOLUTELY NO WARRANTY; for details type `show w'.\n    This is free software, and you are welcome to redistribute it\n    under certain conditions; type `show c' for details.\n\nThe hypothetical commands `show w' and `show c' should show the appropriate\nparts of the General Public License.  Of course, the commands you use may\nbe called something other than `show w' and `show c'; they could even be\nmouse-clicks or menu items--whatever suits your program.\n\nYou should also get your employer (if you work as a programmer) or your\nschool, if any, to sign a \"copyright disclaimer\" for the program, if\nnecessary.  Here is a sample; alter the names:\n\n  Yoyodyne, Inc., hereby disclaims all copyright interest in the program\n  `Gnomovision' (which makes passes at compilers) written by James Hacker.\n\n  <signature of Ty Coon>, 1 April 1989\n  Ty Coon, President of Vice\n\nThis General Public License does not permit incorporating your program into\nproprietary programs.  If your program is a subroutine library, you may\nconsider it more useful to permit linking proprietary applications with the\nlibrary.  If this is what you want to do, use the GNU Lesser General\nPublic License instead of this License.\n");
        jScrollPane2.setViewportView(jTextArea1);
        jTextArea1.setCaretPosition(0);

        jPanel5.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("License", jPanel5);

        jPanel6.setLayout(new java.awt.BorderLayout(2, 2));
        jPanel6.add(jPanel7, java.awt.BorderLayout.CENTER);

        jPanel8.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 2, 2, 2));
        jPanel8.setLayout(new java.awt.GridLayout(2, 0));

        jLabel2.setText("Move around blob with arrow keys or mouse.");
        jPanel8.add(jLabel2);

        jLabel3.setText("Split blob with h. Join blobs with j. Turn gravity on / off with g.");
        jPanel8.add(jLabel3);

        jPanel6.add(jPanel8, java.awt.BorderLayout.SOUTH);

        jTabbedPane1.addTab("Blobsallad", jPanel6);

        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-525)/2, (screenSize.height-534)/2, 525, 534);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jEditorPane1HyperlinkUpdate(javax.swing.event.HyperlinkEvent evt) {//GEN-FIRST:event_jEditorPane1HyperlinkUpdate
        // TODO add your handling code here:
        if (HyperlinkEvent.EventType.ACTIVATED.equals(evt.getEventType())) {
            URL url = evt.getURL();

            if(Desktop.isDesktopSupported())
            {
                Desktop desktop = Desktop.getDesktop();

                if(desktop.isSupported(Desktop.Action.BROWSE))
                {
                    if (url == null) {
						// www.yahoo.com considered an invalid URL. Hence, evt.getURL() returns null.
                        String string = "http://" + evt.getDescription();
                        try {
                            url = new URL(string);
                        } catch (MalformedURLException ex) {
                            return;
                        }
                    }

                    try {
                        desktop.browse(url.toURI());
                    }
                    catch (URISyntaxException ex) {
                    }
                    catch (IOException ex) {
                    }
                }
            }
        }
    }//GEN-LAST:event_jEditorPane1HyperlinkUpdate

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        if (busyComponent != null) {
            // Stop the busy indicator thread.
            busyComponent.setBusy(false);
        }
        controller.stop();
    }//GEN-LAST:event_formWindowClosed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        // TODO add your handling code here:
        JTabbedPane pane = (JTabbedPane)evt.getSource();
        if (pane.getSelectedComponent() == this.jPanel6) {
            // Have blob sallad to grab the focus.
            controller.requestFocus();
        }
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private Controller controller = new Controller();
    private JBusyComponent<JPanel> busyComponent = null;

    private static final Log log = LogFactory.getLog(AboutJDialog.class);

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JEditorPane jEditorPane2;
    private javax.swing.JEditorPane jEditorPane3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
    
}
