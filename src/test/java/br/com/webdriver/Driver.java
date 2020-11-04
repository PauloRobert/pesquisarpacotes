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

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.decolar.com/trip/accommodations/results/PC7bed96723a244643935f79854595dc0b26741624?searchParams=RkgvQ0lUXzY1NzQvQ0lUXzI2MTEvMjAyMC0xMi0wNC8yMDIwLTEyLTA3L0NJVF8yNjExLzIwMjAtMTItMDQvMjAyMC0xMi0wNy8yfEgxOkgsRjA6RixYUzpYUw%3D%3D&flow=FH&tripItem=H1&stepNum=0&from=PSB&searchId=7e1efce7-2058-4682-bbbc-58b49c9cdc54&nw=true&abcv=YWJjdi1mb3JjZWRGaW5hbD1mYWxzZQ");
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		return driver;
	}

}
