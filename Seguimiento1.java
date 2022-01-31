package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class contactTet {
	//estado
	private Contact contact;
	
	//escenarios
	public void setupStage1() {
		contact=null;
	}
	
	public void setupStage2() {
		
	}
	
	// pruebas de casos
	@Test
	void createContact() {
		//fail("Not yet implemented");
		//assertTrue(true);
		//poner escenario
		setupStage1();
		contact= new Contact("Andres Andrade", "aandrade@icesi.edu.co", "3019876543");
		
		//asserts
		assertNotNull(contact);
		assertEquals("Andres Andrade", contact.getName());
		assertEquals("aandrade@icesi.edu.co", contact.getCorreo());
		assertEquals("3019876543", contact.getNumeString());
	}

}
