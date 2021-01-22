import javax.swing.*; // Graphical User Interfaces
import java.awt.*; // Using AWT container and component classes
import java.io.*; // File
import java.awt.event.*; // Using AWT event classes and listener interfaces
import java.util.stream.*; // Import stream
// main class
public class Calculator extends JFrame implements ActionListener {

    //  For Graphical User Interfaces
    JLabel l[]=new JLabel[7]; //4
    JFrame frame=new JFrame("Calculator"); // Main frame is calculator
    JPanel p[]=new JPanel[5]; // Construct panels -3
    JPanel matr[]=new JPanel[3];
    JPanel vect[]=new JPanel[7]; //5
    JPanel eul[]=new JPanel[5];
    JPanel gram[]=new JPanel[5];
    JPanel cardPane=new JPanel();
    CardLayout card= new CardLayout();
    JButton []b=new JButton[15];  //7
    JComboBox matrlist[]=new JComboBox[3];
    JTextField tf[]=new JTextField[12]; //7
    JTextArea ta = new JTextArea(3,22); // vector
    JTextArea tb = new JTextArea(3,22); // matric
    JTextArea tc = new JTextArea(3,22); // euler
    JTextArea td = new JTextArea(3,30); // gram

    JScrollPane scroll = new JScrollPane(td);

    // Variable for matric and vector
    String matric[]={"Addition","Subtraction","Multiplication","Determinants","Inverse Matrix","Transpose Matric","Scalar Triple Product"};
    String vector[]={"Magnitude or Length","Unit Vector","Distance between two points","Vector Addition","Scalar Multiplication","Dot Product","Vector Subtraction"};
    String euler[]={"Initial x, Initial y, Step Size, Value of x that need to be approximated"};
    String gramschmidt[]={"v1, v2, v3"};

    int type=-1;

    Matric mat = new Matric(); // Create an object of Matric
    Vector vec = new Vector(); // Create an object of Vector
    Eulers eule = new Eulers(); // Create an object of Eulers
    GramSchmidtOrtho grams = new GramSchmidtOrtho(); // Create an object of Gram

    // Designing a GUI for calculator
    Calculator(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(300,120); // Frame sets initial window size; height 75
        frame.setResizable(false); // Read-only
        p[0]=new JPanel();//main panel
        p[1]=new JPanel(new GridLayout(2,0,10,10)); // vector
        p[2]=new JPanel(new GridLayout(2,0,10,10)); // matric
        p[3]=new JPanel(new GridLayout(2,0,10,10)); // euler's
        p[4]=new JPanel(new GridLayout(2,0,10,10)); // gram

        /*p[1].setBackground(Color.GRAY);
        p[2].setBackground(Color.GRAY);
        p[3].setBackground(Color.GRAY); // euler's
        p[4].setBackground(Color.GRAY); // gram*/

        // Colour for Vector and Matric box
        makeb(0,"Vector",Color.PINK);
        makeb(1,"Matric",Color.CYAN);
        makeb(2,"Euler's Method",Color.YELLOW);
        makeb(12,"Gram Schmidt Orthogonalization",Color.GREEN);

        // Main panel will show Vector and Matric
        p[0].add(b[0]);
        p[0].add(b[1]);
        p[0].add(b[2]); // euler's
        p[0].add(b[12]); // gram

        // set layout for p[0], p[1], p[2]
        cardPane.setLayout(card);
        cardPane.add(p[0],"Main");
        cardPane.add(p[1],"Vector");
        cardPane.add(p[2],"Matric");
        cardPane.add(p[3],"Euler's Method");
        cardPane.add(p[4],"Gram Schmidt Orthogonalization");

        frame.add(cardPane); // All titles for cardPane will include in the frame
        frame.setVisible(true); // Shows frame

        matrpanel(); // Panel for matric
        p[2].add(matr[0]);
        p[2].add(matr[1]);

        vectpanel(); // Panel for vector
        p[1].add(vect[0]);
        p[1].add(vect[1]);

        eulpanel(); // panel for euler's method
        p[3].add(eul[0]);
        p[3].add(eul[1]);

        grampanel(); // panel for gram
        p[4].add(gram[0]);
        p[4].add(gram[1]);
    }

    void defsize(int a){
        if(a==1)
            frame.setSize(400, 300); // matric
        /*else if(a==2)
            frame.setSize(400,300); // vector
        else if(a==3)
            frame.setSize(400,300); // euler's
        else if(a==4)
            frame.setSize(400,300); // gram*/
        else
            frame.setSize(300,120); //main
    }

    void grampanel() {
    // First panel
        gram[0]=new JPanel();
        gram[0].setBorder(BorderFactory.createEmptyBorder(10,80,0,80));
        // label
        l[5]=new JLabel("Gram Schmidt Orthogonalization");
        gram[0].add(l[5]);

        l[6]=new JLabel("Enter 2x2 or 3x3 vector by row");
        gram[0].add(l[6]);

        tf[8]=new JTextField("X1= X, Y, Z");
        tf[8].setColumns(10);
        tf[8].addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                tf[8].setText("");
            }
            @Override
            public void focusLost(FocusEvent e) {
                if(tf[8].getText().isEmpty())
                    tf[8].setText("X1= X, Y, Z");
            }
        });
        gram[0].add(tf[8]);

        tf[9]=new JTextField("X2= X, Y, Z");
        tf[9].setColumns(10);
        tf[9].addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                tf[9].setText("");
            }
            @Override
            public void focusLost(FocusEvent e) {
                if(tf[9].getText().isEmpty())
                    tf[9].setText("X2= X, Y, Z");
            }
        });
        gram[0].add(tf[9]);

        tf[10]=new JTextField("X3= X, Y, Z");
        tf[10].setColumns(10);
        tf[10].addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                tf[10].setText("");
            }
            @Override
            public void focusLost(FocusEvent e) {
                if(tf[10].getText().isEmpty())
                    tf[10].setText("X3= X, Y, Z");
            }
        });
        gram[0].add(tf[10]);

        /*tf[11]=new JTextField("v3");
        tf[11].setColumns(10);
        tf[11].addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                tf[8].setText(" ");
            }
            @Override
            public void focusLost(FocusEvent e) {
                if(tf[11].getText().isEmpty())
                    tf[11].setText("v3");
            }
        });
        gram[0].add(tf[11]);*/

        // Second panel
        gram[1]=new JPanel();
        gram[1].setBorder(BorderFactory.createEmptyBorder(20,80,0,80));
        makeb(9,"Calculate",Color.MAGENTA);
        makeb(10,"Back",Color.YELLOW);
        td.setEditable(false);
        gram[1].add(b[9]);
        gram[1].add(b[10]);
        //gram[1].add(td);
        gram[1].add(scroll);
    }

    void eulpanel(){
        // First panel
        eul[0]=new JPanel();
        eul[0].setBorder(BorderFactory.createEmptyBorder(10,80,0,80));
        // label
        l[0]=new JLabel("Euler's Method         ");
        eul[0].add(l[0]);

        tf[4]=new JTextField("Initital: x , y");
        tf[4].setColumns(10);
        tf[4].addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                tf[4].setText(" ");
            }
            @Override
            public void focusLost(FocusEvent e) {
                if(tf[4].getText().isEmpty())
                    tf[4].setText("Initial: x , y");
            }
        });

        eul[0].add(tf[4]);

        tf[5]=new JTextField("h");
        tf[5].setColumns(10);
        tf[5].addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                tf[5].setText(" ");
            }
            @Override
            public void focusLost(FocusEvent e) {
                if(tf[5].getText().isEmpty())
                    tf[5].setText("h");
            }
        });

        eul[0].add(tf[5]);

        l[1]=new JLabel("Value of x that need to be approximated");
        eul[0].add(l[1]);

        tf[6]=new JTextField("x");
        tf[6].setColumns(10);
        tf[6].addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                tf[6].setText(" ");
            }
            @Override
            public void focusLost(FocusEvent e) {
                if(tf[6].getText().isEmpty())
                    tf[6].setText("Value of x that need to be approximated");
            }
        });
        eul[0].add(tf[6]);

        l[4]=new JLabel("Approximate solution at x is: ");
        eul[0].add(l[4]);

        tf[7]=new JTextField(" ");
        tf[7].setColumns(10);
        tf[7].addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                tf[7].setText(" ");
            }
            @Override
            public void focusLost(FocusEvent e) {
                if(tf[7].getText().isEmpty())
                    tf[7].setText("Approximate solution at x is:");
            }
        });
        eul[0].add(tf[7]);

        // Second panel
        eul[1]=new JPanel();
        eul[1].setBorder(BorderFactory.createEmptyBorder(20,80,0,80));
        makeb(7,"Calculate",Color.MAGENTA);
        makeb(8,"Back",Color.YELLOW);
        tc.setEditable(false);
        eul[1].add(b[7]);
        eul[1].add(b[8]);
        eul[1].add(tc);
    }

    // Call back upon matric panel
    void matrpanel(){
        // First panel
        matr[0]=new JPanel();
        matr[0].setBorder(BorderFactory.createEmptyBorder(10,80,0,80));

        l[2]=new JLabel("Enter 2x2 or 3x3 Matrix by row");
        matr[0].add(l[2]);

        matrlist[0]=new JComboBox(matric);
        matrlist[0].addActionListener(this);
        matr[0].add(matrlist[0]);

        tf[0]=new JTextField("X1,Y1,Z1,X2,Y2,...");
        tf[0].setColumns(10);
        tf[0].addFocusListener(new FocusListener(){
            public void focusGained(FocusEvent e){
                tf[0].setText("");
            }
            public void focusLost(FocusEvent e){
                if(tf[0].getText().isEmpty())
                    tf[0].setText("X1,Y1,Z1,X2,Y2,...");
            }
        });

        tf[1]=new JTextField("X1,Y1,Z1,X2,Y2,...");
        tf[1].setColumns(10);
        tf[1].addFocusListener(new FocusListener(){
            public void focusGained(FocusEvent e){
                tf[1].setText("");
            }
            public void focusLost(FocusEvent e){
                if(tf[1].getText().isEmpty())
                    tf[1].setText("X1,Y1,Z1,X2,Y2,...");
            }
        });
        matr[0].add(tf[1]);

        // Second panel
        matr[1]=new JPanel();
        matr[1].setBorder(BorderFactory.createEmptyBorder(20,80,0,80));
        makeb(3,"Calculate",Color.MAGENTA);
        makeb(4,"Back",Color.YELLOW);
        tb.setEditable(false);
        matr[1].add(b[3]);
        matr[1].add(b[4]);
        matr[1].add(tb);
    }
    // Vector box
    void vectpanel(){
        // First panel
        vect[0]=new JPanel();
        vect[0].setBorder(BorderFactory.createEmptyBorder(10,80,0,80));

        l[3]=new JLabel("Enter X,Y or X,Y,Z Vector");
        vect[0].add(l[3]);

        matrlist[2]=new JComboBox(vector);
        matrlist[2].addActionListener(this);
        vect[0].add(matrlist[2]);

        tf[2]=new JTextField("X2,Y2,Z2");
        tf[2].setColumns(10);
        tf[2].addFocusListener(new FocusListener(){
            public void focusGained(FocusEvent e){
                tf[2].setText("");
            }
            public void focusLost(FocusEvent e){
                if(tf[2].getText().isEmpty())
                    tf[2].setText("X2,Y2,Z2");
            }
        });

        tf[3]=new JTextField("X,Y,Z");
        tf[3].setColumns(10);
        tf[3].addFocusListener(new FocusListener(){
            public void focusGained(FocusEvent e){
                tf[3].setText("");
            }
            public void focusLost(FocusEvent e){
                if(tf[3].getText().isEmpty())
                    tf[3].setText("X,Y,Z");
            }
        });
        vect[0].add(tf[3]);

        // Second panel
        vect[1]=new JPanel();
        vect[1].setBorder(BorderFactory.createEmptyBorder(20,80,0,80));
        makeb(5,"Calculate",Color.PINK);
        makeb(6,"Back",Color.orange);
        ta.setEditable(false);
        vect[1].add(b[5]);
        vect[1].add(b[6]);
        vect[1].add(ta);
    }

    void makeb(int a,String s,Color c){
        b[a]=new JButton(s);
        b[a].addActionListener(this);
        b[a].setBackground(c);
        b[a].setBorderPainted(false);
        b[a].setFocusPainted(false);
    }
    // Action event
    public void actionPerformed(ActionEvent e){
        Integer bm = matrlist[0].getSelectedIndex(); //  from main menu

        if(e.getSource()==b[0]){
            defsize(1);
            card.show(cardPane,"Vector");
        }

        if(e.getSource()==b[1]){
            defsize(1);
            card.show(cardPane,"Matric");
        }
        // eulers
        if(e.getSource()==b[2]){
            defsize(1);
            card.show(cardPane, "Euler's Method");
        }
        // gram
        if(e.getSource()==b[12]){
            defsize(1);
            card.show(cardPane, "Gram Schmidt Orthogonalization");
        }

        if((e.getSource()==b[4]) || e.getSource()==b[6] || e.getSource()==b[8] || e.getSource()==b[10]) { // b[10] gram
            defsize(4);
            card.show(cardPane,"Main");
            if(e.getSource()==b[6]){
                vect[0].remove(tf[2]);
                ta.setText(null);
            }
            else if (e.getSource()==b[8]){
                tf[4].setText("Initial: x , y");
                tf[5].setText("h");
                tf[6].setText("Value of x that need to be approximated");
                tc.setText(null); //tb
            }
            // gram
            else if (e.getSource()==b[10]){
                tf[8].setText("X1= X,Y,Z");
                tf[9].setText("X2= X,Y,Z");
                tf[10].setText("X3= X,Y,Z");
                //tf[11].setText("v2");
                td.setText(null);
            }
        }

        /*if(e.getSource()==b[6]){
            defsize(3);
            vect[0].remove(tf[2]);
            tf[3].setText("X,Y,Z");
            ta.setText(null);
            card.show(cardPane,"Main");
        }*/

        if(e.getSource()==b[3]){
            double[] u,w;
            try{
                u = Stream.of(tf[1].getText().split(",")).mapToDouble(Double::parseDouble).toArray();
            }
            catch(Exception f){
                tf[1].setText("X1,Y1,Z1,X2,Y2,...");
                return;
            }
             // Matric menu
            if(bm==0){
                w = Stream.of(tf[0].getText().split(",")).mapToDouble(Double::parseDouble).toArray();
                tb.setText(mat.add(u,w));
            }
            else if(bm==1){
                w = Stream.of(tf[0].getText().split(",")).mapToDouble(Double::parseDouble).toArray();
                tb.setText(mat.sub(u,w));
            }
            else if(bm==2){
                w = Stream.of(tf[0].getText().split(",")).mapToDouble(Double::parseDouble).toArray();
                tb.setText(mat.mul(u,w));
            }
            else if(bm==3||bm==6){
                tb.setText(String.valueOf(mat.det(u)));
            }
            else if(bm==4){
                tb.setText(mat.inv(u));
            }
            else if(bm==5){
                tb.setText(mat.trans(u));
            }
        }

        Integer b2 = matrlist[2].getSelectedIndex();

        if(e.getSource()==b[5]){
            double[] u,w;
            try{
                u = Stream.of(tf[3].getText().split(",")).mapToDouble(Double::parseDouble).toArray();
            }
            catch(Exception f){
                tf[3].setText("X,Y,Z");
                return;
            }

            if(b2==0){

                ta.setText(String.valueOf(vec.meg(b2,u)));

            }
            else if(b2==1){

                if(u.length==2){
                    ta.setText(u[0]+" + "+u[1]+" / "+String.valueOf(vec.meg(b2,u))); // unit vector = vector/mag
                    return;
                }
                ta.setText(u[0]+" + "+u[1]+" + "+u[2]+" / "+String.valueOf(vec.meg(b2,u)));

            }
            else if(b2==3){
                try{
                    w = Stream.of(tf[2].getText().split(",")).mapToDouble(Double::parseDouble).toArray();
                }
                catch(Exception f){
                    tf[2].setText("X2,Y2,Z2");
                    return;
                }
                ta.setText(String.valueOf(vec.add(u,w)));
                return;
            }
            else if(b2==6){
                try{
                    w = Stream.of(tf[2].getText().split(",")).mapToDouble(Double::parseDouble).toArray();
                }
                catch(Exception f){
                    tf[2].setText("X2,Y2,Z2");
                    return;
                }
                ta.setText(String.valueOf(vec.sub(u,w)));
                return;
            }
            else if(b2==4){
                try{
                    w = Stream.of(tf[2].getText().split(",")).mapToDouble(Double::parseDouble).toArray();
                }
                catch(Exception f){
                    tf[2].setText("X2,Y2,Z2");
                    return;
                }
                ta.setText(String.valueOf(vec.scalar(Double.parseDouble(tf[3].getText()),w)));
                return;
            }
            else if(b2>1){
                try{
                    w = Stream.of(tf[2].getText().split(",")).mapToDouble(Double::parseDouble).toArray();
                }
                catch(Exception f){
                    tf[2].setText("X2,Y2,Z2");
                    return;
                }
                if(b2==2)
                    ta.setText(String.valueOf(vec.rec(b2,u,w)));
                else if(b2==5)
                    ta.setText(String.valueOf(vec.rec(b2,u,w)));
                return;

            }
        }
        // euler's method
        if(e.getSource()==b[7]){
            double[] u;
            double w,z;
            try{
                u = Stream.of(tf[4].getText().split(",")).mapToDouble(Double::parseDouble).toArray();
                w = Double.parseDouble(tf[5].getText());
                z = Double.parseDouble(tf[6].getText());
            }
            catch(Exception f){
                tf[4].setText("Initial: x , y");
                tf[5].setText("h");
                tf[6].setText("Value of x that need to be approximated");
                tc.setText(null);
                return;
            }

            tc.setText(eule.euler(u[0],u[1],w,z));
        }

        // gramschmidt
        if(e.getSource()==b[9]){
            double[] u,w,z;
            //double w,z;
            try{
                u = Stream.of(tf[8].getText().split(",")).mapToDouble(Double::parseDouble).toArray();
                
            }
            catch(Exception f){
                tf[8].setText("X1= X,Y,Z");
                tf[9].setText("X2= X,Y,Z");
                tf[10].setText("X3= X,Y,Z");
                td.setText(null);
                return;
            }

            try{
                w = Stream.of(tf[9].getText().split(",")).mapToDouble(Double::parseDouble).toArray();
            }
            catch(Exception f){
                //tf[8].setText("X1= X,Y,Z");
                tf[9].setText("X2= X,Y,Z");
                tf[10].setText("X3= X,Y,Z");
                //td.setText(null);
                td.setText(grams.ans(u,null,null));
                //w[0]=0.0;
                return;
            }


            try{
                z = Stream.of(tf[10].getText().split(",")).mapToDouble(Double::parseDouble).toArray();
                td.setText(grams.ans(u,w,z));
            }
            catch(Exception f){
                //tf[8].setText("X1= X,Y,Z");
                //tf[9].setText("X2= X,Y,Z");
                tf[10].setText("X3= X,Y,Z");
                //td.setText(null);
                td.setText(grams.ans(u,w,null));
                //z[0]=0.0;
                return;
            }

            
                //w = Stream.of(tf[9].getText().split(",")).mapToDouble(Double::parseDouble).toArray();
                //Double.parseDouble(tf[9].getText());
                //z = Stream.of(tf[10].getText().split(",")).mapToDouble(Double::parseDouble).toArray();
                //Double.parseDouble(tf[10].getText());
                //if()
                //td.setText(grams.ans(tf[8].getText(),tf[9].getText(),tf[10].getText()));

            /*}
            catch(Exception f){
                tf[8].setText("X1= X,Y,Z");
                tf[9].setText("X2= X,Y,Z");
                tf[10].setText("X3= X,Y,Z");
                td.setText(null);
                return;
            }*/

           
        }
        
        vect[0].remove(tf[2]);
        vect[0].revalidate();
        vect[0].repaint();

        if(b2>1){
            vect[0].add(tf[2]);
            tf[2].setText("X2,Y2,Z2");
            tf[3].setText("X1,Y1,Z1");
            vect[0].revalidate();
            if(b2==4)
                tf[3].setText("K"); // for scalar
        }
        else // 0, 1
            tf[3].setText("X,Y,Z");

        matr[0].remove(tf[0]);
        matr[0].revalidate();
        matr[0].repaint();

        if(bm<3){ // matric add, sub, mult
            matr[0].add(tf[0]);
            tf[0].setText("X1,Y1,Z1,X2,Y2,...");
            tf[1].setText("X1,Y1,Z1,X2,Y2,...");
            matr[0].revalidate();
        }
    }
    // Main method
    public static void main(String args[]){
        new Calculator();
    }
}
