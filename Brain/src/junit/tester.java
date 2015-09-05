package junit;

import java.awt.Color;

import org.junit.Test;

public class tester {

	@Test
	public void test(){
		Color color=new Color(-11831902);
		System.out.println(color.getBlue());
		System.out.println(color.getRed());
		System.out.println(color.getGreen());
	}
	
}
