package com.cooperativacafetera.dealmanager.pruebas;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ClasePrueba {

	
	
	
	
	public int pruebaSuma(){
		return 5;
	}
	
	
	@Test
	public void prueba(){
		Assert.assertTrue(pruebaSuma()==5);
	}
	
	
}
