import java.util.*;
import java.io.*;
import java.text.*;
import javax.swing.*;
class Write{
	Calendar c = Calendar.getInstance();
	DateFormat dF = new SimpleDateFormat("dd MMMM yyyy 'at' HH:mm "); 
	String pt = new File(System.getProperty("user.dir")).getParent()+"\\Files\\Log.txt";
	void Write(String a,String b){
		try{
			FileWriter wr = new FileWriter(pt,true);
			if(a!=null)
				wr.write(dF.format(c.getTime())+a+" : "+b+"\n");
			wr.close();
		}
		catch(Exception f){
			JOptionPane.showMessageDialog(null,"Previous Log file not found. New file created.","Alert",JOptionPane.WARNING_MESSAGE);
			pt=new File(System.getProperty("user.dir")).getParent()+"\\Log.txt";
			Write(a,b);
		}
	}
	/*
	try{
			FileWriter wr = new FileWriter(pt,true);
			wr.write(dF.format(c.getTime())+a+" : "+b+"\n");
			wr.close();
		}
		catch(FileNotFoundException f){
			System.out.println("File Not Found. Please open Translation Script.");
			j.setDialogTitle("Open Morse Data");
			int r = j.showOpenDialog(null);
			if (r == JFileChooser.APPROVE_OPTION)
				pt=j.getSelectedFile().getAbsolutePath();
			else{
				System.out.println("This Program Cannot run without Translation Script");
				System.exit(0);
			}
		}
	*/	
}