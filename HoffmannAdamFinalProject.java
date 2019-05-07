/*
 * Project: 
 * YuGiOh Card Display
 * 
 * Description: 
 * Displays information about a random card given the type requested when you click
 * on the button. (i.e. monster button return a monster, spell returns spell, etc.)
 * Gives description of effect, as well as about the card type.
 * Includes text and binary serialization of data.
 * Enhancements I would have like to made would be to include an image of the card when 
 * the information is presented in the frame
 * 
 * Name: Adam Hoffmann
 */

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import javax.swing.Timer;
import java.awt.event.*;
import java.awt.color.*;
import java.io.*;
class YuGiOh implements Serializable
{
	private String type;
	private String attribute;
	private String effect;
	private String name;
	public void setType(String type) {
		this.type=type;
	}
	public void setAttribute(String eff){
		attribute=eff;
	}
	public void setEffect(String effect) {
		this.effect=effect;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public String getAttribute() {
		return attribute;
	}
	public String getEffect() {
		return effect;
	}
	public String getName() {
		return name;
	}
	public YuGiOh(String name, String type, String attribute, String effect) {
		setName(name);
		setType(type);
		setAttribute(attribute);
		setEffect(effect);
	}
	public YuGiOh() {
		
	}
	public String toString()
	{
		return String.format("%s %s \n %s", getType(), getAttribute(), getEffect());
	}
	
}
class CardReader {
	@SuppressWarnings({ "unchecked" })
	public static YuGiOh ReadTrap(YuGiOh ygo, String[] traps) {
		Random rand = new Random();
		String name, type, att, eff;
		int rnd = rand.nextInt(3);
		String fname = traps[rnd];
		name = fname;
		YuGiOh result;
		File f = new File(fname.replaceAll("\\s", "")+".bin");
		try {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
		result = (YuGiOh)ois.readObject();
		ygo = result;
		ois.close();
		return ygo;
		} catch(Exception ex) {
			return null;
		}
		

		
	}
	
	
	public static YuGiOh ReadMonster(YuGiOh ygo, String[] monsters){
		try {
			Random rand = new Random();
			String type, att, eff, name;
			int rnd = rand.nextInt(3);
			String fname = monsters[rnd];
			name = fname;
			File f = new File(fname.replaceAll("\\s", "")+".txt");
			Scanner fsc = new Scanner(f);
			type = fsc.next();
			att = fsc.nextLine();
			eff = fsc.nextLine();
			ygo = new YuGiOh(name, type, att, eff);
			
		}catch(Exception e) {
			
		}
		return ygo;
	}


	public static YuGiOh ReadSpell(YuGiOh ygo, String[] spells) {
		Random rand = new Random();
		String name, type, att, eff;
		int rnd = rand.nextInt(3);
		String fname = spells[rnd];
		name = fname;
		YuGiOh result;
		File f = new File(fname.replaceAll("\\s", "")+".bin");
		try {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
		result = (YuGiOh)ois.readObject();
		ygo = result;
		ois.close();
		return ygo;
		} catch(Exception ex) {
			return null;
		}
	}
}

public class HoffmannAdamFinalProject implements ActionListener {
	static YuGiOh ygo = new YuGiOh();
	static String[] Spells = {"Raigeki", "Zombie World", "Crusadia Power"};
	static String[] Traps = {"Gozen Match","Rivalry Of Warlords","Mirror Force"};
	static String[] Monsters = {"Sky Striker Ace Raye", "Blue Eyes Alternative White Dragon", "Exodia The Forbidden One"};
	JLabel MI, TaL, effect, mE;
	public static void main(String[] args) {
		setupUI();
		
	}

	private static void setupUI() {
		JFrame frame = new JFrame();
		Container one = new Container();
		Container two = new Container();
		Container three = new Container();
		one.setLayout(new BorderLayout());
		two.setLayout(new BorderLayout());
		three.setLayout(new GridLayout(1,3));
		frame.setSize(600,300);
		frame.setVisible(true);
		frame.setTitle("YuiGiOh");
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		JButton Enter = new JButton("Enter");
		JButton Monster = new JButton("Monster");
		JButton Spell = new JButton("Spell");
		JButton Trap = new JButton("Trap");
		JLabel MI= new JLabel("Type/Attribute");
		JLabel TaL = new JLabel(" ");
		JLabel name = new JLabel("");
		JLabel effect = new JLabel("Effect");
		JLabel mE = new JLabel(" ");
		three.add(Monster);
		three.add(Spell);
		three.add(Trap);
		//three.add(Enter,BorderLayout.SOUTH);
		one.add(MI, BorderLayout.NORTH);
		one.add(name, BorderLayout.EAST);
		one.add(TaL,BorderLayout.CENTER);
		two.add(effect, BorderLayout.NORTH);
		two.add(mE,BorderLayout.CENTER);
		frame.add(one, BorderLayout.NORTH);
		frame.add(two, BorderLayout.CENTER);
		frame.add(three, BorderLayout.SOUTH);
		Monster.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ygo=CardReader.ReadMonster(ygo, Monsters);
        		name.setText(ygo.getName()+" ");
        		TaL.setText(ygo.getType()+"/"+ygo.getAttribute());
        		mE.setText("<html>"+ygo.getEffect()+"</html>");
        		
        	}
        });
		Spell.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ygo=CardReader.ReadSpell(ygo, Spells);
        		name.setText(ygo.getName()+" ");
        		TaL.setText(ygo.getType()+"/"+ygo.getAttribute());
        		mE.setText("<html>"+ygo.getEffect()+"</html>");
        	}
        });
		Trap.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ygo=CardReader.ReadTrap(ygo, Traps);
        		name.setText(ygo.getName()+" ");
        		TaL.setText(ygo.getType()+"/"+ygo.getAttribute());
        		mE.setText("<html>"+ygo.getEffect()+"</html>");
        	}
        });
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JComboBox cb = (JComboBox)e.getSource();
        String petName = (String)cb.getSelectedItem();
        updateLabel(petName);
	}

	private void updateLabel(String petName) {
		// TODO Auto-generated method stub
		
	}
}


