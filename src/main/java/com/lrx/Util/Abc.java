package com.lrx.Util;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Abc extends Applet implements ActionListener {
	  public Label nameLabel,passwardLabel,addressLabel,titleLabel,sexLabel;
	   public TextField name,passward;
	   public Choice address;
	   public Button button;
	   public TextArea txtArea;
	   public Checkbox chb1,chb2;
	   public CheckboxGroup chb;

	   public void init()
	   {
	      setLayout(null);
	      titleLabel = new Label("file check");
	      titleLabel.setBackground(Color.orange);
	      nameLabel = new Label("fileName锟斤拷");
	      nameLabel.setBackground(Color.green);
	      nameLabel.setForeground(Color.red);
	      passwardLabel = new Label("锟斤拷锟诫：");
	      passwardLabel.setBackground(Color.green);
	      passwardLabel.setForeground(Color.red);
	      addressLabel = new Label("锟斤拷址锟斤拷");
	      addressLabel.setBackground(Color.green);
	      addressLabel.setForeground(Color.red);
	      name = new TextField("");
	      passward = new TextField("");
	      passward.setEchoChar('*');
	      address = new Choice();
	      address.addItem("锟斤拷锟斤拷");
	      address.addItem("锟较猴拷");
	      address.addItem("锟较撅拷");
	      address.addItem("锟较凤拷");
	      address.addItem("锟竭猴拷");
	      button = new Button("锟斤拷锟�");
	      txtArea = new TextArea(3,5);
	      txtArea.setEditable(false);
	      sexLabel = new Label("锟皆憋拷");
	      sexLabel.setBackground(Color.green);
	      sexLabel.setForeground(Color.red);
	      chb = new CheckboxGroup();
	      chb1 = new Checkbox("锟斤拷",chb,true);
	      chb2 = new Checkbox("女",chb,false);  
	                                                                                 
	      titleLabel.setLocation(280,0);titleLabel.setSize(80,20);
	      nameLabel.setLocation(200,100);nameLabel.setSize(80,20);
	      name.setLocation(300,100);name.setSize(200,20);
	      passwardLabel.setLocation(200,60);passwardLabel.setSize(40,20);
	      passward.setLocation(250,60);passward.setSize(200,20);
	      sexLabel.setLocation(200,90);sexLabel.setSize(40,20);
	      chb1.setLocation(250,90);chb1.setSize(30,30);
	      chb2.setLocation(300,90);chb2.setSize(30,30);
	      addressLabel.setLocation(200,120);addressLabel.setSize(40,20);
	      address.setLocation(250,120);address.setSize(70,20); 
	      button.setLocation(250,150);button.setSize(80,30);
	      txtArea.setLocation(200,200);txtArea.setSize(650,380);
	       
	      button.addActionListener(this);
	      
	      add(nameLabel);add(name);
//	      add(titleLabel);add(sexLabel);
//	      add(passwardLabel);add(passward);
//	      add(chb1);add(chb2);
//	      add(addressLabel);add(address);
	      add(button);add(txtArea);
	      
	   }
	   public void actionPerformed(ActionEvent e)
	   {
		   //锟斤拷锟�
		   txtArea.setText("");
	   		
	   		 // txtArea.setText("锟矫伙拷锟斤拷"+name.getText()+'\n'+"锟斤拷锟诫："+passward.getText()+'\n'+"锟皆憋拷:"+chb.getSelectedCheckbox()+'\n'+"锟斤拷址锟斤拷"+address.getSelectedItem());
	   	   String fileName=name.getText();
		   //String fileContext=readTextFile("I:\\锟斤拷压锟斤拷锟斤拷锟斤拷锟侥匡拷锟绞斤拷锟絓\2.1锟斤拷缘锟斤拷锟斤拷.txt");
	   	   String fileContext=readTextFile(fileName);
			String[] andDim = fileContext.split("&&");
			String outStr = "";
			String pexp2 = "\\$(.*?)\\$";
			Pattern p2 = Pattern.compile(pexp2);
			for (int i = 0; i < andDim.length; i++) {
				//System.out.println(andDim[i]);
				Matcher m2 = p2.matcher(andDim[i]);
				boolean result2 = m2.find();
				if (result2) {
					outStr = outStr + " && (2==2) ";
				} else {
					if (i == 0) {
						outStr = outStr + andDim[i];
					} else {
						outStr = outStr + " && " + andDim[i];
					}
				}
				//System.out.println("outStr:"+outStr);
			}

			if (outStr.length()>0){
				txtArea.setText(outStr);
			}
		   
	   }
	   
	   
	   public  String readTextFile(String file) {
			String fileContent = "";
			File fileName = new File(file);
			
			StringBuffer contents = new StringBuffer();
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new FileReader(fileName));
				String text = null;
				while ((text = reader.readLine()) != null) {
					contents.append(text).append(
							System.getProperty("line.separator"));
				}
			} catch (Exception e) {
				txtArea.setText("锟侥硷拷路锟斤拷锟斤拷锟斤拷确锟斤拷锟斤拷");
				e.printStackTrace();
			}finally{
				try{
					if (reader!=null){
						reader.close();
					}
					}catch(Exception e){
						txtArea.setText("锟侥硷拷路锟斤拷锟斤拷锟斤拷确锟斤拷锟斤拷");
						e.printStackTrace();
					}
				}
			fileContent=contents.toString();
			return fileContent;
		}
}
