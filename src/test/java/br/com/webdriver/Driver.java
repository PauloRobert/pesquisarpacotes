package br.com.webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Driver {
	private WebDriver driver;

	public WebDriver Webdriver() {

		try {

			WebDriverManager.chromedriver().setup();

		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		options.addArguments("start-maximized");
		options.addArguments("enable-automation");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-infobars");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--disable-browser-side-navigation");
		options.addArguments("--disable-gpu");

		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://tinyurl.com/decolar28");
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		return driver;
	}

}
