package Executor;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import br.com.webdriver.Driver;
import pageobjects.decolar;

public class Executor {

	private WebDriver driver;
	decolar decolar = new decolar(driver);

	@Before
	public void CriarDriver() {
		Driver wdriver = new Driver();
		driver = wdriver.Webdriver();
	}

	@Test
	public void Pesquisar() throws IOException, InterruptedException {

		for (int i = 1; i < 10; i++) {

			// decolar.ordenarValores();
			decolar.pesquisarPacotes();
			decolar.proximaPagina();

			System.err.println("Pagina: " + i);

		}

	}

	@After
	public void tearDown() {
		driver.close();
		driver.quit();

	}

}