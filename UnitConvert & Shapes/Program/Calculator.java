import javax.swing.*; 
import java.awt.*; 
import java.io.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {
	Icon arrow = new ImageIcon(new File(System.getProperty("user.dir")).getParent()+"\\Files\\arrow.png");

	JLabel l[]=new JLabel[2];
	JFrame frame=new JFrame("Calculator");
	JPanel p[]=new JPanel[3];
	JPanel conv[]=new JPanel[3];
	JPanel geo[]=new JPanel[5];
	JPanel cardPane=new JPanel();
	CardLayout card= new CardLayout();
	JButton []b=new JButton[7];
	JRadioButton rb[]=new JRadioButton[3];
	JComboBox convlist[]=new JComboBox[3];
	JTextField tf[]=new JTextField[4];
	JTextArea ta = new JTextArea(3,22);

	String length[]={"MM","CM","M","INCH","FOOT","KM","MILE"};
	String temp[]={"Celsius","Fahrenheit"};
	String volume[]={"Liter","MiliLiter","Gallon"};
	String shape[]={"Circle","Triangle","Square","Pentagon","Hexagon","Heptagon","Octagon","Rectangle","Other"};

	int type=-1;

	Convert con = new Convert();
	Shape sh = new Shape();
	Write wr = new Write();
	Runtime rs = Runtime.getRuntime();

	Calculator(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setSize(300,75);
		frame.setResizable(false);
		p[0]=new JPanel();//main panel
		p[1]=new JPanel(new GridLayout(2,0,10,10));
		p[2]=new JPanel(new GridLayout(3,0,10,10));

		p[1].setBackground(Color.GRAY);
		p[2].setBackground(Color.GRAY);
		 
		makeb(0,"Geometry",Color.GREEN);
		makeb(1,"Unit Convert",Color.CYAN);
		makeb(2,"Log",Color.YELLOW);

		p[0].add(b[0]);
		p[0].add(b[1]);
		p[0].add(b[2]);

		cardPane.setLayout(card);
		cardPane.add(p[0],"Main");
		cardPane.add(p[1],"Geometry");
		cardPane.add(p[2],"Unit Convert");
		 
		frame.add(cardPane);
		frame.setVisible(true);

		convpanel();
		p[2].add(conv[0]);
		p[2].add(conv[1]);
		p[2].add(conv[2]);

		geopanel();
		p[1].add(geo[0]);
		p[1].add(geo[1]);
	}

	void defsize(int a){
		if(a==1)
			frame.setSize(500, 400);//convert
		else if(a==2)
			frame.setSize(400,300);//geometry
		else
			frame.setSize(300,80);//main
	}

	void convpanel(){
		//first panel
		conv[0]=new JPanel();
		conv[0].setBorder(BorderFactory.createEmptyBorder(20,100,0,100));

		rb[0]=new JRadioButton("Length");
		rb[0].addActionListener(this);
		conv[0].add(rb[0]);

		rb[1]=new JRadioButton("Temperature");
		rb[1].addActionListener(this);
		conv[0].add(rb[1]);

		rb[2]=new JRadioButton("Volume");
		rb[2].addActionListener(this);
		conv[0].add(rb[2]);

		l[0]=new JLabel("Choose The Conversion You Want");
		conv[0].add(l[0]);

		
		//2nd panel
		conv[1]=new JPanel();
		conv[1].setBorder(BorderFactory.createEmptyBorder(20,130,0,130));
		convlist[0]=new JComboBox();
		convlist[0].addActionListener(this);
		conv[1].add(convlist[0]);

		l[1]= new JLabel(arrow);
		conv[1].add(l[1]);

		convlist[1]=new JComboBox();
		convlist[1].addActionListener(this);
		conv[1].add(convlist[1]);

		tf[0]=new JTextField("Enter Number");
		tf[0].setColumns(15);
		tf[0].addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent e){
				tf[0].setText("");
			}
			public void focusLost(FocusEvent e){
				if(tf[0].getText().isEmpty())
					tf[0].setText("Enter Number");
				}
			});
		
		conv[1].add(tf[0]);


		//panel 3rd
		conv[2]=new JPanel();
		conv[2].setBorder(BorderFactory.createEmptyBorder(20,130,0,130));
		makeb(3,"Convert",Color.GREEN);
		makeb(4,"Back",Color.RED);
		tf[1]=new JTextField("Answer");
		tf[1].setColumns(15);
		tf[1].setEditable(false);
		tf[1].addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent e){
				tf[1].setText("");
			}
			public void focusLost(FocusEvent e){
				if(tf[1].getText().isEmpty())
					tf[1].setText("Answer");
				}
			});

		conv[2].add(b[3]);
		conv[2].add(b[4]);
		conv[2].add(tf[1]);
	}

	void convbox(int i){
		String[]a;
		if(i==0)
			a=length;
		else if(i==1)
			a=temp;
		else
			a=volume;
		convlist[0].removeAllItems();
		convlist[0].setModel(new DefaultComboBoxModel(a));

		convlist[1].removeAllItems();
		convlist[1].setModel(new DefaultComboBoxModel(a));
	}

	void geopanel(){
		geo[0]=new JPanel();
		geo[0].setBorder(BorderFactory.createEmptyBorder(30,80,0,80));
		convlist[2]=new JComboBox(shape);
		convlist[2].addActionListener(this);
		geo[0].add(convlist[2]);

		tf[2]=new JTextField("Number Of Sides");
		tf[2].setColumns(10);
		tf[2].addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent e){
				tf[2].setText("");
			}
			public void focusLost(FocusEvent e){
				if(tf[2].getText().isEmpty())
					tf[2].setText("Number Of Sides");
				}
			});

		tf[3]=new JTextField("Length of Side");
		tf[3].setColumns(10);
		tf[3].addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent e){
				tf[3].setText("");
			}
			public void focusLost(FocusEvent e){
				if(tf[3].getText().isEmpty())
					tf[3].setText("Length of Side");
				}
			});
		geo[0].add(tf[3]);


		//2nd panel
		geo[1]=new JPanel();
		geo[1].setBorder(BorderFactory.createEmptyBorder(20,80,0,80));
		makeb(5,"Calculate",Color.GREEN);
		makeb(6,"Back",Color.RED);
		ta.setEditable(false);
		geo[1].add(b[5]);
		geo[1].add(b[6]);
		geo[1].add(ta);
	}


	void makeb(int a,String s,Color c){
		b[a]=new JButton(s);
		b[a].addActionListener(this);
		b[a].setBackground(c);
		b[a].setBorderPainted(false);
		b[a].setFocusPainted(false);
	}

	public void actionPerformed(ActionEvent e){
		if(e.getSource()==b[0]){
			defsize(2);
			card.show(cardPane,"Geometry");
		}

		if(e.getSource()==b[1]){
			defsize(1);
			card.show(cardPane,"Unit Convert");
		}

		if(e.getSource()==b[2]){
			wr.Write(null,null);
			try{
				Desktop.getDesktop().edit(new File(wr.pt));
			}
			catch(IOException i){
				JOptionPane.showMessageDialog(null,"Sorry problem in Log.txt file","Alert",JOptionPane.WARNING_MESSAGE);
				System.exit(0);
			}
		}

		if(e.getSource()==b[4]){
			tf[0].setText("Enter Number");
			tf[1].setText("Answer");
			defsize(3);
			card.show(cardPane,"Main");
		}

		if(e.getSource()==b[6]){
			defsize(3);
			geo[0].remove(tf[2]);
			tf[3].setText("Length of Side");
			ta.setText(null);
			card.show(cardPane,"Main");
		}

		//tk nk bagi radio button yg lain selected
		for(int i=0;i<3;i++){
			if(e.getSource()==rb[i]){
				for(int a=0;a<3;a++){
					if(a!=i)
						rb[a].setSelected(false);
				}
				convbox(i);
				type=i;
			}
		}

		if(e.getSource()==b[3] && type!=-1){
			double u=0.0;
			Integer b0 = convlist[0].getSelectedIndex();
			Integer b1 = convlist[1].getSelectedIndex();
			try{
				u = Double.parseDouble(tf[0].getText());
			}
			catch(Exception f){
				tf[0].setText("Enter Number");
				return;
			}

			if(type==0){
				tf[1].setText(Double.toString(con.getlength(u,b0,b1)));
				wr.Write("Convert","Length");
			}
			else if(type==1){
				tf[1].setText(Double.toString(con.gettemp(u,b0,b1)));
				wr.Write("Convert","Temperature");
			}
			else if(type==2){
				tf[1].setText(Double.toString(con.getvol(u,b0,b1)));
				wr.Write("Convert","Volume");
			}
		}

		Integer b2 = convlist[2].getSelectedIndex();

		if(e.getSource()==b[5]){
			Double u=0.0,w=0.0;
			try{
				u = Double.parseDouble(tf[3].getText());
			}
			catch(Exception f){
				tf[3].setText("Length of Side");
				return;
			}

			if(b2==0){
				sh.round((u));
				ta.setText("Circumference: "+sh.cir+"\nDiameter: "+sh.dia+"\nArea: "+sh.area);
				wr.Write("Geometry","Circle");
			}
			else if(b2==7){
				try{
					w = Double.parseDouble(tf[2].getText());
				}
				catch(Exception f){
					tf[2].setText("Length of Width");
					return;
				}
				sh.rec(u,w);
				ta.setText("Perimeter: "+sh.cir+"\nArea: "+sh.area);
				wr.Write("Geometry","Rectangle");
				return;

			}
			else if(b2==8){
				try{
					w = Double.parseDouble(tf[2].getText());
				}
				catch(Exception f){
					tf[2].setText("Number Of Sides");
					return;
				}
				sh.poly(u,w);
				ta.setText("Perimeter: "+sh.cir+"\nArea: "+sh.area);
				wr.Write("Geometry",w+" Sides");
				return;
			}
			else{
				sh.poly(u,b2+2);
				ta.setText("Perimeter: "+sh.cir+"\nArea: "+sh.area);
				wr.Write("Geometry",convlist[2].getSelectedItem().toString());
			}
		}

		geo[0].remove(tf[2]);
		geo[0].revalidate();
		geo[0].repaint();
		tf[3].setText("Length Of Sides");
		if(b2>6){
			geo[0].add(tf[2]);
			if(b2==7)
				tf[2].setText("Length Of Width");
			else
				tf[2].setText("Number Of Sides");
			geo[0].revalidate();
		}
	}
	public static void main(String args[]) {
		new Calculator();
	}
}