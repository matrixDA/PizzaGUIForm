import org.w3c.dom.Text;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.MessageFormat;

public class PizzaGUIFrame extends JFrame
{
    JPanel mainPnl, secondPnl, crustPnl, sizePnl, toppingPnl, receipt, controlPnl;
    JRadioButton thin, regular, deepDish;
    ButtonGroup group;
    JComboBox comboBox;
    JCheckBox calamariCB, nachoCB, shavedCoconutCB, bananaCB, tunaCB, cicadasCB, anchoviesCB, lettuceCB;
    JButton order, clear, quit;
    JTextArea textArea;
    JScrollPane pane;
    double sizePrice = 0, ingredientPrice = 0, toppingPrice = 0, total, tax = .07;
    public PizzaGUIFrame()
    {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeigh = screenSize.height;
        int screenWidth = screenSize.width;

        setSize(700,650);
        setLocation(screenWidth / 4, (screenHeigh - 650) / 2);

        setTitle("Pizza Order Form");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        createGUI();
        setVisible(true);
    }

    private void createGUI() {
        mainPnl = new JPanel();
        secondPnl = new JPanel();
        crustPnl = new JPanel();
        sizePnl = new JPanel();
        toppingPnl = new JPanel();
        receipt = new JPanel();
        controlPnl = new JPanel();

        mainPnl.setLayout(new BoxLayout(mainPnl, BoxLayout.PAGE_AXIS));
        secondPnl.setLayout(new GridLayout(1,2));
        secondPnl.add(crustPnl);
        secondPnl.add(sizePnl);
        mainPnl.add(secondPnl);
        mainPnl.add(toppingPnl);
        mainPnl.add(receipt);
        mainPnl.add(controlPnl);

        add(mainPnl);
        createCrustPnl();
        createSizePnl();
        createToppingPnl();
        createReceiptPln();
        createControlPnl();



    }

    private void createCrustPnl()
    {
        crustPnl.setLayout(new GridLayout(1, 3));
      //  crustPnl.setBorder(new TitledBorder("Crust Type"));
        crustPnl.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1),"Crust Type"));



        group = new ButtonGroup();

        thin = new JRadioButton("Thin");
        thin.setHorizontalAlignment(JRadioButton.CENTER);
        thin.setVerticalAlignment(JRadioButton.CENTER);
      //  thin.setBorder(new EmptyBorder(-50,310,-50,0));

        regular = new JRadioButton("Regular");
        regular.setHorizontalAlignment(JRadioButton.CENTER);
        regular.setVerticalAlignment(JRadioButton.CENTER);
      //  regular.setBorder(new EmptyBorder(-50,0,-50, 0));


        deepDish = new JRadioButton("Deep-dish");
        deepDish.setHorizontalAlignment(JRadioButton.CENTER);
        deepDish.setVerticalAlignment(JRadioButton.CENTER);
      //  deepDish.setBorder(new EmptyBorder(-50,0,-50, 271));

        group.add(thin);
        group.add(regular);
        group.add(deepDish);

        crustPnl.add(thin);
        crustPnl.add(regular); regular.setSelected(true);
        crustPnl.add(deepDish);

    }
    private void createSizePnl()
    {
      //
        // sizePnl.setBorder(new TitledBorder("Pizza Size"));
        sizePnl.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1),"Pizza Size"));

        comboBox = new JComboBox();
        comboBox.setAlignmentY(JComboBox.CENTER_ALIGNMENT);

        comboBox.addItem("Small");
        comboBox.addItem("Medium"); comboBox.setSelectedIndex(1);
        comboBox.addItem("Large");
        comboBox.addItem("Super");

        // to get option pane for the select box type
          //   String box = (String) comboBox.getSelectedItem();
        sizePnl.add(comboBox);
    }
    private void createToppingPnl()
    {
        toppingPnl.setLayout(new GridLayout(2, 4));
        toppingPnl.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1),"Toppings"));

        calamariCB = new JCheckBox("Calamari");
        nachoCB = new JCheckBox("Nacho");
        shavedCoconutCB = new JCheckBox("Shaved Coconut");
        bananaCB = new JCheckBox("Banana");
        tunaCB = new JCheckBox("Tuna");
        cicadasCB = new JCheckBox("Cicadas");
        anchoviesCB = new JCheckBox("Anchovies");
        lettuceCB = new JCheckBox("Lettuce");

        toppingPnl.add(calamariCB);
        toppingPnl.add(nachoCB);
        toppingPnl.add(shavedCoconutCB);
        toppingPnl.add(bananaCB);
        toppingPnl.add(tunaCB);
        toppingPnl.add(cicadasCB);
        toppingPnl.add(anchoviesCB);
        toppingPnl.add(lettuceCB);
    }

    private void createReceiptPln()
    {
       // receipt.setBorder(new TitledBorder("Receipt"));
        receipt.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1),"Receipt"));

        textArea = new JTextArea(20,25);
        textArea.setEditable(false);
        pane = new JScrollPane(textArea);
        receipt.add(pane);
    }
    private void createControlPnl()
    {
       // controlPnl.setBorder(new TitledBorder("Control Options"));
        controlPnl.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1),"Control Options"));


        clear = new JButton("Clear");
        clear.addActionListener((ActionEvent ae) -> {

            clearOrder();

            enableOrder();

        });

        order = new JButton("Order");
        order.addActionListener((ActionEvent ae) -> {

            DecimalFormat df = new DecimalFormat("$0.00");

            String res = "Order Details:\n";
            res += "=============================\n";

            res += "Crust Type: ";
            if (thin.isSelected()) {
                res += "Thin\n";
            }
            else if (regular.isSelected()) {
                res += "Regular\n";
            }
            else {
                res += "Deep-dish\n";
            }

            String box = (String) comboBox.getSelectedItem();

            res += "Size: ";
            res += comboBox.getSelectedItem() + "\t\t";

            if (box.equals("Small")) {
                sizePrice = 8;
                res += df.format(sizePrice) + "\n";
            }
           else if (box.equals("Medium"))
            {
                sizePrice = 12;
                res += df.format(sizePrice) + "\n";
            }
           else if (box.equals("Large")) {
                sizePrice = 16;
                res += df.format(sizePrice) + "\n";
            }
          else {
                sizePrice = 20;
                res += df.format(sizePrice) + "\n";
          }

            res += "Toppings ($1 each):\n";

            if (calamariCB.isSelected())
            {
                res+= "Calamari\t\t";
                ingredientPrice = 1;
                res += df.format(ingredientPrice) + "\n";
                toppingPrice = toppingPrice + ingredientPrice;

            }

            if (nachoCB.isSelected())
            {
                res+= "Nacho\t\t";
                ingredientPrice = 1;
                res += df.format(ingredientPrice) + "\n";
                toppingPrice = toppingPrice + ingredientPrice;
            }

            if (shavedCoconutCB.isSelected())
            {
                ingredientPrice = 1;
                res += "Shaved Coconut\t" + df.format(ingredientPrice) + "\n";
                toppingPrice = toppingPrice + ingredientPrice;
            }

            if (bananaCB.isSelected())
            {
                res+= "Banana\t\t";
                ingredientPrice = 1;
                res += df.format(ingredientPrice) + "\n";
                toppingPrice = toppingPrice + ingredientPrice;
            }
            if (tunaCB.isSelected())
            {
                res+= "Tuna\t\t";
                ingredientPrice = 1;
                res += df.format(ingredientPrice) + "\n";
                toppingPrice = toppingPrice + ingredientPrice;
            }

            if (cicadasCB.isSelected())
            {
                res+= "Cicadas\t\t";
                ingredientPrice = 1;
                res += df.format(ingredientPrice) + "\n";
                toppingPrice = toppingPrice + ingredientPrice;

            }

            if (anchoviesCB.isSelected())
            {
                res+= "Anchovies\t\t";
                ingredientPrice = 1;
                res += df.format(ingredientPrice) + "\n";
                toppingPrice = toppingPrice + ingredientPrice;
            }

            if (lettuceCB.isSelected())
            {
                res+= "Lettuce\t\t";
                ingredientPrice = 1;
                res += df.format(ingredientPrice) + "\n";
                toppingPrice = toppingPrice + ingredientPrice;
            }


            res+= "\nTopping Price:\t\t" + df.format(toppingPrice);

            res+= "\n";
            res+= "\n";

            total = sizePrice + toppingPrice;

            res += "Sub-total:\t\t";
            res += df.format(total) + "\n";

            tax = total * tax;

            res +=  "Tax:\t\t";
            res += df.format(tax) + "\n";

            total = total + tax;

            res += "------------------------------------\n";

            res += "Total:\t\t";
            res += df.format(total);

            res += "\n=============================\n";

            textArea.append(res);

            disableOrder();
        });

        quit = new JButton("Quit");
        quit.addActionListener((ActionEvent ae) -> {
            int quit;
             quit = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit your order?", "Quit Order", JOptionPane.YES_NO_OPTION);
             if (quit == JOptionPane.YES_OPTION)
             {
                 System.exit(0);
             }
        });

        controlPnl.add(clear);
        controlPnl.add(order);
        controlPnl.add(quit);
    }

     private void clearOrder()
     {
         textArea.setText(" ");
         tax = 0.07;
         sizePrice = 0;
         ingredientPrice = 0;
         total = 0;
         toppingPrice = 0;

         regular.setSelected(true);

         comboBox.setSelectedIndex(1);

         calamariCB.setSelected(false);
         nachoCB.setSelected(false);
         shavedCoconutCB.setSelected(false);
         bananaCB.setSelected(false);
         tunaCB.setSelected(false);
         cicadasCB.setSelected(false);
         anchoviesCB.setSelected(false);
         lettuceCB.setSelected(false);
    }

    private void enableOrder()
    {
        order.setEnabled(true);

        calamariCB.setEnabled(true);
        nachoCB.setEnabled(true);
        shavedCoconutCB.setEnabled(true);
        bananaCB.setEnabled(true);
        tunaCB.setEnabled(true);
        cicadasCB.setEnabled(true);
        anchoviesCB.setEnabled(true);
        lettuceCB.setEnabled(true);

        comboBox.setEnabled(true);

        thin.setEnabled(true);
        regular.setEnabled(true);
        deepDish.setEnabled(true);
    }

    private void disableOrder()
    {
        order.setEnabled(false);

        calamariCB.setEnabled(false);
        nachoCB.setEnabled(false);
        shavedCoconutCB.setEnabled(false);
        bananaCB.setEnabled(false);
        tunaCB.setEnabled(false);
        cicadasCB.setEnabled(false);
        anchoviesCB.setEnabled(false);
        lettuceCB.setEnabled(false);

        comboBox.setEnabled(false);

        thin.setEnabled(false);
        regular.setEnabled(false);
        deepDish.setEnabled(false);



    }



}

