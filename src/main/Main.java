package main;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL30;

public class Main {
	static boolean invOpen = false;
	static LinkedList<String> inventory = new LinkedList<String>();

	public static void main(String[] args) {
		glfwInit();
		long window = glfwCreateWindow(300,300,"Inventory Test",NULL,NULL);
		//glfwWindowHint(GLFW_VISIBLE, 1);
		glfwMakeContextCurrent(window);
		glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_DISABLED);
		
		GL.createCapabilities();
		
		while(!(glfwGetKey(window, GLFW_KEY_ESCAPE) == GLFW_PRESS)) {
			GL30.glClearColor(0.0f, 0.0f, 1.0f, 1.0f);
			GL30.glClear(GL30.GL_COLOR_BUFFER_BIT | GL30.GL_DEPTH_BUFFER_BIT);
			
			if(glfwGetKey(window,GLFW_KEY_F) == GLFW_PRESS && !invOpen) {
				invOpen = true;
				JFrame newWindow = new JFrame();
				newWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				newWindow.setPreferredSize(new Dimension(300,300));
				JPanel mainFrame = new JPanel();
				
				JLabel test = new JLabel("Apple");
				mainFrame.add(test);
				JRadioButton appleButton = new JRadioButton();
				mainFrame.add(appleButton);
				JLabel test1 = new JLabel("Sword");
				mainFrame.add(test1);
				JRadioButton swordButton = new JRadioButton();
				mainFrame.add(swordButton);
				
				newWindow.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						if(swordButton.isSelected()) {
							inventory.add("Sword");
						}
						else if(appleButton.isSelected()) {
							inventory.add("Apple");
						}
						invOpen = false;
					}
				});
				
				newWindow.add(mainFrame);
				newWindow.pack();
				newWindow.setVisible(true);
			}
			if(glfwGetKey(window,GLFW_KEY_P) == GLFW_PRESS) {
				System.out.println(inventory.size());
				for (String string : inventory) {
					System.out.println(string);
				}
				System.out.println("END");
			}
			
			glfwSwapBuffers(window);
			glfwPollEvents();
		}

	}

}
