/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.feemanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import javax.swing.JOptionPane;

/** 
 *
 * @author Shubham Pathak
 */
public class AddFees extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AddFees.class.getName());

    /**
     * Creates new form AddFees
     */
    
    public void displayCashFirst(){
        lbl_dd_num.setVisible(false);
        labl_cheque_num.setVisible(false);
        txt_cheque_num.setVisible(false);
        txt_dd_num.setVisible(false);
        txt_bank_name.setVisible(false);
        lbl_bank_name.setVisible(false);
        toyear.setVisible(true);
        lbl_rollno.setVisible(true);
        
        
    }
    public String insertData(){
        int receiptno = Integer.parseInt(txt_receipt_num.getText());
        String sname = txt_rec_name1.getText();
        int rollno = Integer.parseInt(txt_rollno2.getText());
        String paymentmode = combo_mode_payment.getSelectedItem().toString();
        String chequeno = txt_cheque_num.getText();
        String bankname = txt_bank_name.getText();
        String ddno = txt_dd_num.getText();
        String coursename = jComboBox2.getSelectedItem().toString();
        String gst = lbl_receipt_num1.getText();
        float total = Float.parseFloat(txt_total.getText());
        SimpleDateFormat sd1 = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date d = date_c.getDate();
        String date = sd1.format(d);

        float amount = Float.parseFloat(txt_amount.getText());
        float cgst = Float.parseFloat(txt_cgst.getText());
        float sgst = Float.parseFloat(txt_sgst.getText());
        String totalinwords = txt_total_in_words.getText();
        String remark = jTextArea1.getText();
        int year1 = Integer.parseInt(fromyear.getText());
        int year2 = Integer.parseInt(toyear.getText());
        String status ="";
        
        try{
           Class.forName("com.mysql.cj.jdbc.Driver");
           Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/feemanagementdb", "root", "shubham");
            
           String query =
"INSERT INTO fees_details (" +
"reciept_no, student_name, roll_no, payment_mode, cheque_no, bank_name, dd_no, " +
"course_name, gstin, total_amount, date, amount, cgst, sgst, total_in_words, remark, year1, year2" +
") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

           PreparedStatement st = con.prepareStatement(query);
           st.setInt(1, receiptno);
           st.setString(2, sname);
           st.setInt(3, rollno);
           st.setString(4, paymentmode);
           st.setString(5, chequeno);
           st.setString(6, bankname);
           st.setString(7, ddno);
           st.setString(8, coursename);
           st.setString(9, gst);
           st.setFloat(10, total);
           st.setString(11, date);
           st.setFloat(12, amount);
           st.setFloat(13, cgst);
           st.setFloat(14, sgst);
           st.setString(15, totalinwords);
           st.setString(16, remark);
           st.setInt(17, year1);
           st.setInt(18, year2);
           int count = st.executeUpdate();
           
           if(count == 1){
               status = "Success";
           }
           else{
               status = "Failed";
           }

           
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return status;
    }
    public AddFees() {
        initComponents();
        displayCashFirst();
        fillComboBox();
        int r = getRno();
        r++;
        txt_receipt_num.setText(Integer.toString(r));
    }

public class NumberToWordsConverter {

    static String[] units = {
        "", "One", "Two", "Three", "Four", "Five", "Six",
        "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve",
        "Thirteen", "Fourteen", "Fifteen", "Sixteen",
        "Seventeen", "Eighteen", "Nineteen"
    };

    static String[] tens = {
        "", "", "Twenty", "Thirty", "Forty", "Fifty",
        "Sixty", "Seventy", "Eighty", "Ninety"
    };

    public static String convert(final int n) {

        if (n < 0) {
            return "Minus " + convert(-n);
        }

        if (n < 20) {
            return units[n];
        }

        if (n < 100) {
            return tens[n / 10] +
                   ((n % 10 != 0) ? " " + units[n % 10] : "");
        }

        if (n < 1000) {
            return units[n / 100] + " Hundred" +
                   ((n % 100 != 0) ? " " + convert(n % 100) : "");
        }

        if (n < 100000) {
            return convert(n / 1000) + " Thousand" +
                   ((n % 1000 != 0) ? " " + convert(n % 1000) : "");
        }

        if (n < 10000000) {
            return convert(n / 100000) + " Lakh" +
                   ((n % 100000 != 0) ? " " + convert(n % 100000) : "");
        }

        return convert(n / 10000000) + " Crore" +
               ((n % 10000000 != 0) ? " " + convert(n % 10000000) : "");
    }

    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the Amount: ");
        int n = sc.nextInt();

        System.out.println(convert(n) + " Only");
    }

        private static void setText(String convert) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }


    
    boolean validation(){
        if(txt_rec_name1.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Please Enter Receiver Name");
            return false;
        }
        if(date_c.getDate()== null){
            JOptionPane.showMessageDialog(this, "Please Enter Date");
            return false;
        }
        if(txt_amount.getText().equals("") || txt_amount.getText().matches("[0-9]+") == false){
            JOptionPane.showMessageDialog(this, "Please Enter Amounts in Number");
            return false;
        }
        if(combo_mode_payment.getSelectedItem().toString().equalsIgnoreCase("cheque")){
            if(txt_cheque_num.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Please Enter Cheque Number");
                return false;
            }
            if(txt_bank_name.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Please Enter Bank Details");
                return false;
            }
        }
        if(combo_mode_payment.getSelectedItem().toString().equalsIgnoreCase("dd")){
            if(txt_dd_num.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Please Enter DD Number");
                return false;
            }
            if(txt_bank_name.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Please Enter Bank Name");
                return false;
            }
        }
        
      
        return true;
    }
    
    void fillComboBox (){
        try{
           Class.forName("com.mysql.cj.jdbc.Driver");
           Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/feemanagementdb", "root", "shubham");
            
           String query = "SELECT cname from course";
           PreparedStatement st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery(query);
           while(rs.next()){
               jComboBox2.addItem(rs.getString("cname"));
           }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public int getRno() {
        int rno = 0;
        try{
           Class.forName("com.mysql.cj.jdbc.Driver");
           Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/feemanagementdb", "root", "shubham");
           PreparedStatement st = con.prepareStatement("SELECT max(reciept_no) FROM fees_details");
            ResultSet rs = st.executeQuery();
            
            if(rs.next() == true){
                //rno = rs.getInt("reciept_no");
                rno = rs.getInt(1);
            }
           
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return rno;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lbl_bank_name = new javax.swing.JLabel();
        labl_cheque_num = new javax.swing.JLabel();
        lbl_dd_num = new javax.swing.JLabel();
        txt_receipt_num = new javax.swing.JTextField();
        lbl_date = new javax.swing.JLabel();
        txt_bank_name = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        toyear = new javax.swing.JTextField();
        lbl_rollno = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_amount = new javax.swing.JTextField();
        txt_total_in_words = new javax.swing.JTextField();
        txt_total = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        txt_cgst = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txt_sgst = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        btn_submit = new javax.swing.JButton();
        lbl_rec_name1 = new javax.swing.JLabel();
        txt_rec_name1 = new javax.swing.JTextField();
        lbl_rollno1 = new javax.swing.JLabel();
        lbl_rollno2 = new javax.swing.JLabel();
        fromyear = new javax.swing.JTextField();
        txt_rollno2 = new javax.swing.JTextField();
        lbl_mode_payment = new javax.swing.JLabel();
        combo_mode_payment = new javax.swing.JComboBox<>();
        txt_cheque_num = new javax.swing.JTextField();
        lbl_receipt_num1 = new javax.swing.JLabel();
        date_c = new com.toedter.calendar.JDateChooser();
        lbl_receipt_num = new javax.swing.JLabel();
        lbl_gstin = new javax.swing.JLabel();
        txt_dd_num = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        jButton1.setBackground(new java.awt.Color(204, 204, 204));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setText("HOME");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton1KeyPressed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(204, 204, 204));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton2.setText("SEARCH RECORD");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(204, 204, 204));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton3.setText("EDIT COURSE");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(204, 204, 204));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton4.setText("COURSE LIST");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(204, 204, 204));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton5.setText("VIEW ALL RECORD");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(204, 204, 204));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton6.setText("BACK");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jButton6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton6KeyPressed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(204, 204, 204));
        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton7.setText("LOGOUT");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jButton7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton7KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(95, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 780));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 638, 474, -1));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_bank_name.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_bank_name.setText("BANK NAME");
        jPanel3.add(lbl_bank_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, 120, -1));

        labl_cheque_num.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labl_cheque_num.setText("CHEQUE NUMBER");
        jPanel3.add(labl_cheque_num, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 140, -1));

        lbl_dd_num.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_dd_num.setText("DD NUMBER");
        jPanel3.add(lbl_dd_num, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, 150, -1));

        txt_receipt_num.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_receipt_num.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_receipt_numActionPerformed(evt);
            }
        });
        jPanel3.add(txt_receipt_num, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 230, -1));

        lbl_date.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_date.setText("DATE");
        jPanel3.add(lbl_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 30, 50, -1));

        txt_bank_name.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_bank_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bank_nameActionPerformed(evt);
            }
        });
        jPanel3.add(txt_bank_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 160, 230, -1));

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        toyear.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        toyear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toyearActionPerformed(evt);
            }
        });
        jPanel4.add(toyear, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 60, 130, -1));

        lbl_rollno.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_rollno.setText("TO");
        jPanel4.add(lbl_rollno, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 60, 50, -1));

        jComboBox2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        jPanel4.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 220, -1));

        jSeparator2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel4.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 340, 260, 10));

        jSeparator3.setBackground(new java.awt.Color(153, 153, 153));
        jPanel4.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 147, 1290, 0));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("AMOUNT");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 120, 70, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("REMARK");
        jPanel4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 460, 70, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("DESCRIPTION");
        jPanel4.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 120, 100, 20));

        txt_amount.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_amount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_amountActionPerformed(evt);
            }
        });
        jPanel4.add(txt_amount, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 170, 220, 30));

        txt_total_in_words.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_total_in_words.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_total_in_wordsActionPerformed(evt);
            }
        });
        jPanel4.add(txt_total_in_words, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 400, 610, 30));

        txt_total.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_totalActionPerformed(evt);
            }
        });
        jPanel4.add(txt_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 360, 220, 30));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setText("COURSE");
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 60, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setText("SIGNATURE");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 480, 100, -1));

        jSeparator4.setBackground(new java.awt.Color(153, 153, 153));
        jPanel4.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 1290, 50));

        txt_cgst.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_cgst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cgstActionPerformed(evt);
            }
        });
        jPanel4.add(txt_cgst, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 230, 220, 30));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setText("CGST 7%");
        jPanel4.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 240, 70, -1));

        txt_sgst.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_sgst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_sgstActionPerformed(evt);
            }
        });
        jPanel4.add(txt_sgst, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 290, 220, 30));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setText("SGST 7%");
        jPanel4.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 290, 60, -1));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setText("SERIAL NO.");
        jPanel4.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 90, 20));

        jTextField13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField13ActionPerformed(evt);
            }
        });
        jPanel4.add(jTextField13, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 170, 380, 30));

        jSeparator5.setBackground(new java.awt.Color(153, 153, 153));
        jPanel4.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 467, 240, -1));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setText("TOTAL");
        jPanel4.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 360, 60, -1));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel21.setText("TOTAL IN WORDS");
        jPanel4.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 130, -1));

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 460, 670, -1));

        btn_submit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_submit.setText("SUBMIT");
        btn_submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_submitActionPerformed(evt);
            }
        });
        jPanel4.add(btn_submit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 530, 120, 40));

        lbl_rec_name1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_rec_name1.setText("RECEIVER NAME");
        jPanel4.add(lbl_rec_name1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 120, -1));

        txt_rec_name1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_rec_name1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_rec_name1ActionPerformed(evt);
            }
        });
        jPanel4.add(txt_rec_name1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 220, -1));

        lbl_rollno1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_rollno1.setText("ROLL NO");
        jPanel4.add(lbl_rollno1, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 20, 70, -1));

        lbl_rollno2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_rollno2.setText("FROM");
        jPanel4.add(lbl_rollno2, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 60, 70, -1));

        fromyear.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        fromyear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fromyearActionPerformed(evt);
            }
        });
        jPanel4.add(fromyear, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 60, 120, -1));

        txt_rollno2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_rollno2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_rollno2ActionPerformed(evt);
            }
        });
        jPanel4.add(txt_rollno2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 20, 220, -1));

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 1290, 580));

        lbl_mode_payment.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_mode_payment.setText("MODE OF PAYMENT");
        jPanel3.add(lbl_mode_payment, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 180, -1));

        combo_mode_payment.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        combo_mode_payment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "CASH", "PhonePe", "Paytm", "CHEQUE", "CARD" }));
        combo_mode_payment.setSelectedIndex(1);
        combo_mode_payment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_mode_paymentActionPerformed(evt);
            }
        });
        jPanel3.add(combo_mode_payment, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, 110, -1));

        txt_cheque_num.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_cheque_num.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cheque_numActionPerformed(evt);
            }
        });
        jPanel3.add(txt_cheque_num, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 120, 230, -1));

        lbl_receipt_num1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_receipt_num1.setText("AVC56677GHI");
        jPanel3.add(lbl_receipt_num1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 70, 140, -1));
        jPanel3.add(date_c, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 30, 140, -1));

        lbl_receipt_num.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_receipt_num.setText("RECEIPT NUMBER");
        jPanel3.add(lbl_receipt_num, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 150, -1));

        lbl_gstin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_gstin.setText("GSTIN:");
        jPanel3.add(lbl_gstin, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 70, 50, -1));

        txt_dd_num.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_dd_num.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_dd_numActionPerformed(evt);
            }
        });
        jPanel3.add(txt_dd_num, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 120, 230, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 1420, 800));

        pack();
    }// </editor-fold>                        

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        HomePage hp = new HomePage();
        hp.show();
        this.dispose();
    }                                        

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        HomePage hp = new HomePage();
        hp.show();
        this.dispose();
    }                                        

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {                                         
       Login lg = new Login();
       lg.show();
       this.dispose();
    }                                        

    private void toyearActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
    }                                      

    private void txt_bank_nameActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
    }                                             

    private void txt_receipt_numActionPerformed(java.awt.event.ActionEvent evt) {                                                
        // TODO add your handling code here:
    }                                               

    
    
    private void txt_amountActionPerformed(java.awt.event.ActionEvent evt) {                                           
        String s1 = txt_amount.getText();
        float amt = Float.parseFloat(s1);
        
        float cgst = amt * 0.07f;
        float sgst = amt * 0.07f;
        
        txt_cgst.setText(Float.toString(cgst));
        txt_sgst.setText(Float.toString(sgst));
        float sum = amt + cgst + sgst;
        txt_total.setText(Float.toString(sum));
        
        txt_total_in_words.setText(NumberToWordsConverter.convert((int)sum));
    }                                          

    private void txt_cheque_numActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here:
    }                                              

    private void txt_total_in_wordsActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        // TODO add your handling code here:
    }                                                  

    private void txt_totalActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
    }                                         

    private void txt_cgstActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void txt_sgstActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void jTextField13ActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
    }                                            

    private void txt_dd_numActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    }                                          

    private void combo_mode_paymentActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        if(combo_mode_payment.getSelectedIndex() == 0){
            lbl_dd_num.setVisible(true);
            txt_dd_num.setVisible(true);
            lbl_rollno.setVisible(true);
            toyear.setVisible(true);
            txt_bank_name.setVisible(true);
            lbl_bank_name.setVisible(true);
            
            labl_cheque_num.setVisible(false);
            txt_cheque_num.setVisible(false);
            
            
        }
        if(combo_mode_payment.getSelectedIndex() == 1){
            labl_cheque_num.setVisible(false);
            txt_dd_num.setVisible(false);
            txt_cheque_num.setVisible(false);
            lbl_dd_num.setVisible(false);
            lbl_rollno.setVisible(true);
            toyear.setVisible(true);  
            txt_bank_name.setVisible(false);
            lbl_bank_name.setVisible(false);
        }
        if(combo_mode_payment.getSelectedIndex() == 4){
            labl_cheque_num.setVisible(true);
            txt_dd_num.setVisible(false);
            txt_cheque_num.setVisible(true);
            lbl_dd_num.setVisible(false);
            lbl_rollno.setVisible(true);
            toyear.setVisible(true);  
            txt_bank_name.setVisible(true);
            lbl_bank_name.setVisible(true);
        }
        if(combo_mode_payment.getSelectedIndex() == 2){
            labl_cheque_num.setVisible(false);
            txt_dd_num.setVisible(false);
            txt_cheque_num.setVisible(false);
            lbl_dd_num.setVisible(false);
            lbl_rollno.setVisible(true);
            toyear.setVisible(true);  
            txt_bank_name.setVisible(false);
            lbl_bank_name.setVisible(false);
        }
        if(combo_mode_payment.getSelectedIndex() == 3){
            labl_cheque_num.setVisible(false);
            txt_dd_num.setVisible(false);
            txt_cheque_num.setVisible(false);
            lbl_dd_num.setVisible(false);
            lbl_rollno.setVisible(true);
            toyear.setVisible(true);  
            txt_bank_name.setVisible(false);
            lbl_bank_name.setVisible(false);
        }
    }                                                  

    private void btn_submitActionPerformed(java.awt.event.ActionEvent evt) {                                           
        if(validation() == true){
            String s = insertData(); 
            if(s.equals("Success")){
                JOptionPane.showMessageDialog(this, "Record Inserted Successfully");
            }
            else{
                JOptionPane.showMessageDialog(this, "Record not inserted");
            }
        }

            
    }                                          

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {                                    
        
    }                                   

    private void jButton7KeyPressed(java.awt.event.KeyEvent evt) {                                    
        
    }                                   

    private void jButton6KeyPressed(java.awt.event.KeyEvent evt) {                                    
       
    }                                   

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        jTextField13.setText(jComboBox2.getSelectedItem().toString());
    }                                          

    private void txt_rec_name1ActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
    }                                             

    private void fromyearActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void txt_rollno2ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new AddFees().setVisible(true));
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton btn_submit;
    private javax.swing.JComboBox<String> combo_mode_payment;
    private com.toedter.calendar.JDateChooser date_c;
    private javax.swing.JTextField fromyear;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JLabel labl_cheque_num;
    private javax.swing.JLabel lbl_bank_name;
    private javax.swing.JLabel lbl_date;
    private javax.swing.JLabel lbl_dd_num;
    private javax.swing.JLabel lbl_gstin;
    private javax.swing.JLabel lbl_mode_payment;
    private javax.swing.JLabel lbl_rec_name1;
    private javax.swing.JLabel lbl_receipt_num;
    private javax.swing.JLabel lbl_receipt_num1;
    private javax.swing.JLabel lbl_rollno;
    private javax.swing.JLabel lbl_rollno1;
    private javax.swing.JLabel lbl_rollno2;
    private javax.swing.JTextField toyear;
    private javax.swing.JTextField txt_amount;
    private javax.swing.JTextField txt_bank_name;
    private javax.swing.JTextField txt_cgst;
    private javax.swing.JTextField txt_cheque_num;
    private javax.swing.JTextField txt_dd_num;
    private javax.swing.JTextField txt_rec_name1;
    private javax.swing.JTextField txt_receipt_num;
    private javax.swing.JTextField txt_rollno2;
    private javax.swing.JTextField txt_sgst;
    private javax.swing.JTextField txt_total;
    private javax.swing.JTextField txt_total_in_words;
}
