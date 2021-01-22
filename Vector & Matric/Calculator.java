import javax.swing.*; 
import java.awt.*; 
import java.io.*;
import java.awt.event.*;
import java.util.stream.*;
public class Calculator extends JFrame implements ActionListener {

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
	JTextField tf[]=new JTextField[7];
	JTextArea ta = new JTextArea(3,22);
	JTextArea tb = new JTextArea(3,22);

	String matric[]={"Addition","Subtraction","Multiplication","Determinants","Inverse Matrix","Transpose Matric","Scalar Triple Product"};
	String vector[]={"Magnitude or Length","Unit Vector","Distance between two points","Vector Addition","Scalar Multiplication","Dot Product"};

	int type=-1;

	Matric con = new Matric();
	Vector sh = new Vector();

	Calculator(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setSize(300,75);
		frame.setResizable(false);
		p[0]=new JPanel();//main panel
		p[1]=new JPanel(new GridLayout(2,0,10,10));
		p[2]=new JPanel(new GridLayout(2,0,10,10));

		p[1].setBackground(Color.GRAY);
		p[2].setBackground(Color.GRAY);
		 
		makeb(0,"Vector",Color.GREEN);
		makeb(1,"Matric",Color.CYAN);
		//makeb(2,"Log",Color.YELLOW);

		p[0].add(b[0]);
		p[0].add(b[1]);
		//p[0].add(b[2]);

		cardPane.setLayout(card);
		cardPane.add(p[0],"Main");
		cardPane.add(p[1],"Vector");
		cardPane.add(p[2],"Matric");
		 
		frame.add(cardPane);
		frame.setVisible(true);

		convpanel();
		p[2].add(geo[2]);
		p[2].add(geo[3]);
		//p[2].add(conv[2]);

		geopanel();
		p[1].add(geo[0]);
		p[1].add(geo[1]);
	}

	void defsize(int a){
		if(a==1)
			frame.setSize(400, 300);//convert
		else if(a==2)
			frame.setSize(400,300);//geometry
		else
			frame.setSize(300,80);//main
	}

	void convpanel(){
		//first panel
		geo[2]=new JPanel();
		geo[2].setBorder(BorderFactory.createEmptyBorder(30,80,0,80));
		convlist[0]=new JComboBox(matric);
		convlist[0].addActionListener(this);
		geo[2].add(convlist[0]);

		tf[0]=new JTextField("X1,X2,X3,Y1,Y2,...");
		tf[0].setColumns(10);
		tf[0].addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent e){
				tf[0].setText("");
			}
			public void focusLost(FocusEvent e){
				if(tf[0].getText().isEmpty())
					tf[0].setText("X1,X2,X3,Y1,Y2,...");
				}
			});

		tf[1]=new JTextField("X1,X2,X3,Y1,Y2,...");
		tf[1].setColumns(10);
		tf[1].addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent e){
				tf[1].setText("");
			}
			public void focusLost(FocusEvent e){
				if(tf[1].getText().isEmpty())
					tf[1].setText("X1,X2,X3,Y1,Y2,...");
				}
			});
		geo[2].add(tf[1]);


		//2nd panel
		geo[3]=new JPanel();
		geo[3].setBorder(BorderFactory.createEmptyBorder(20,80,0,80));
		makeb(3,"Calculate",Color.GREEN);
		makeb(4,"Back",Color.RED);
		tb.setEditable(false);
		geo[3].add(b[3]);
		geo[3].add(b[4]);
		geo[3].add(tb);
	}

	/*void convbox(int i){
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
	}*/

	void geopanel(){
		geo[0]=new JPanel();
		geo[0].setBorder(BorderFactory.createEmptyBorder(30,80,0,80));
		convlist[2]=new JComboBox(vector);
		convlist[2].addActionListener(this);
		geo[0].add(convlist[2]);

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
		Integer bm = convlist[0].getSelectedIndex();

		

		if(e.getSource()==b[0]){
			defsize(2);
			card.show(cardPane,"Vector");
		}

		if(e.getSource()==b[1]){
			defsize(1);
			card.show(cardPane,"Matric");
		}

		/*if(e.getSource()==b[2]){
			//wr.Write(null,null);
			try{
				Desktop.getDesktop().edit(new File(wr.pt));
			}
			catch(IOException i){
				JOptionPane.showMessageDialog(null,"Sorry problem in Log file. Make sure it ends with \".txt\"","Alert",JOptionPane.WARNING_MESSAGE);
				System.exit(0);
			}
		}*/

		if(e.getSource()==b[4]){
			//tf[0].setText("Enter Number");
			//tf[1].setText("Answer");
			defsize(3);
			//System.out.print("dfg");
			card.show(cardPane,"Main");
		}

		if(e.getSource()==b[6]){
			defsize(3);
			geo[0].remove(tf[2]);
			tf[3].setText("X,Y,Z");
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
				//convbox(i);
				type=i;
			}
		}

		if(e.getSource()==b[3]){
			//System.out.print("sdfgs");
			double[] u,w;
			try{
				u = Stream.of(tf[1].getText().split(",")).mapToDouble(Double::parseDouble).toArray();
			}
			catch(Exception f){
				tf[1].setText("X1,X2,X3,Y1,Y2,...");
				return;
			}

			/*if(bm<3||bm>5){
				try{
					z = Stream.of(tf[0].getText().split(",")).mapToDouble(Double::parseDouble).toArray();
				}
				catch(Exception f){
					tf[0].setText("X2,Y2,Z2");
					return;
				}
			}*/

			if(bm==0){
				//System.out.print("Sgsd");
				w = Stream.of(tf[0].getText().split(",")).mapToDouble(Double::parseDouble).toArray();
				tb.setText(con.add(u,w));
			}
			else if(bm==1){
				w = Stream.of(tf[0].getText().split(",")).mapToDouble(Double::parseDouble).toArray();
				tb.setText(con.sub(u,w));
			}
			else if(bm==2){
				w = Stream.of(tf[0].getText().split(",")).mapToDouble(Double::parseDouble).toArray();
				tb.setText(con.mul(u,w));
			}
			else if(bm==3||bm==6){
				tb.setText(String.valueOf(con.det(u)));
			}
			else if(bm==4){
				tb.setText(con.inv(u));
			}
			else if(bm==5){
				tb.setText(con.trans(u));
			}	
		}



		Integer b2 = convlist[2].getSelectedIndex();

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
				//sh.round(b2,u);
				ta.setText(String.valueOf(sh.meg(b2,u)));
				//wr.Write("Geometry","Circle");
			}
			else if(b2==1){
				//sh.round(b2,u);
				if(u.length==2){
					ta.setText(u[0]+"+"+u[1]+"/"+String.valueOf(sh.meg(b2,u)));
					return;
				}
				ta.setText(u[0]+"+"+u[1]+"+"+u[2]+"/"+String.valueOf(sh.meg(b2,u)));
				//wr.Write("Geometry","Circle");
			}
			else if(b2==3){
				try{
					w = Stream.of(tf[2].getText().split(",")).mapToDouble(Double::parseDouble).toArray();
				}
				catch(Exception f){
					tf[2].setText("X2,Y2,Z2");
					return;
				}
				//sh.rec(b2,u,w);
				//ta.setText("Perimeter: "+sh.cir+"\nArea: "+sh.area);
				ta.setText(String.valueOf(sh.add(u,w)));
				//wr.Write("Geometry","Rectangle");
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
				//sh.rec(b2,u,w);
				//ta.setText("Perimeter: "+sh.cir+"\nArea: "+sh.area);
				ta.setText(String.valueOf(sh.scalar(Double.parseDouble(tf[3].getText()),w)));
				//wr.Write("Geometry","Rectangle");
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
				//sh.rec(b2,u,w);
				//ta.setText("Perimeter: "+sh.cir+"\nArea: "+sh.area);
				if(b2==2)
					ta.setText(String.valueOf(sh.rec(b2,u,w)));
				else if(b2==5)
					ta.setText(String.valueOf(sh.rec(b2,u,w)));
				//wr.Write("Geometry","Rectangle");
				return;

			}
		}

		geo[0].remove(tf[2]);
		geo[0].revalidate();
		geo[0].repaint();
		//tf[3].setText("X1,Y1,Z1");
		if(b2>1){
			geo[0].add(tf[2]);
			tf[2].setText("X2,Y2,Z2");
			tf[3].setText("X1,Y1,Z1");
			geo[0].revalidate();
			if(b2==4)
				tf[3].setText("K");
		}
		else
			tf[3].setText("X,Y,Z");


		geo[2].remove(tf[0]);
		geo[2].revalidate();
		geo[2].repaint();
		//tf[3].setText("X1,Y1,Z1");
		if(bm<3){
			geo[2].add(tf[0]);
			tf[0].setText("X1,X2,X3,Y1,Y2,...");
			tf[1].setText("X1,X2,X3,Y1,Y2,...");
			geo[2].revalidate();
		}
		
	}
	public static void main(String args[]){
		new Calculator();
	}
}