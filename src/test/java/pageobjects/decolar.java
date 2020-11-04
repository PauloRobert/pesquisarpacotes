package pageobjects;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class decolar {

	DateTimeFormatter dataFormato = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm");
	LocalDateTime now = LocalDateTime.now();

	private WebDriver driver;

	@FindBy(how = How.CSS, using = "i.tooltip-close.eva-3-icon-close.-eva-3-m-sm")
	private WebElement TooltipResumoViagem;

	@FindBy(how = How.CSS, using = "a.upatracker.-eva-3-tc-gray-0.-eva-3-bold")
	private List<WebElement> TitulosHoteis;

	@FindBy(how = How.CSS, using = "li.-lg.hf-pricebox-price.hf-robot-price.-final-price-v5")
	private List<WebElement> PrecoPessoa;

	@FindBy(how = How.CSS, using = "li.pkg-final-price span.-eva-3-bold")
	private List<WebElement> ValorTotal;

	@FindBy(how = How.CLASS_NAME, using = "flight-text")
	private List<WebElement> InfoVoo;

	@FindBy(how = How.ID, using = "iata-origin")
	private List<WebElement> aeroportoOrigem;

	@FindBy(how = How.ID, using = "iata-dest")
	private List<WebElement> aeroportoDestino;

	@FindBy(how = How.CLASS_NAME, using = "eva-3-icon-arrow-right.pagination-icon")
	private WebElement nextIcon;

	@FindBy(how = How.CSS, using = "i.eva-3-icon-arrow-right.pagination-icon")
	private WebElement nextIconCss;

	@FindBy(how = How.CSS, using = "span.-eva-3-tc-gray-2")
	private List<WebElement> enderecoCompleto;

	@FindBy(how = How.CSS, using = "span.-eva-3-tc-white.rating-text.-eva-3-bold")
	private WebElement notaHotel;

	@FindBy(how = How.CSS, using = "li.hf-cluster-stars.-eva-3-tc-orange-3.hf-robot-stars")
	private List<WebElement> qtdEstrelas;

	@FindBy(how = How.CSS, using = "ul.hf-cluster-user-action.-pkg")
	private List<WebElement> mordomias;

	@FindBy(how = How.ID, using = "sorting")
	private WebElement selectOrdenarValor;

	@FindBy(how = How.CSS, using = "div.hf-cluster.eva-3-cluster-gallery.pkgf-featured-container.analytics.ha-itemResult.hf-robot-cluster.-cluster-gallery-md")
	private List<WebElement> linkOferta;
	
	
	@FindBy(how = How.CSS, using = "input.input-tag.sbox-main-focus.sbox-origin.sbox-primary.sbox-places-first.sbox-origin-container.places-inline")
	private WebElement InputOrigem;
	
	@FindBy(how = How.CSS, using = "input.input-tag.sbox-main-focus.sbox-destination.sbox-secondary.sbox-places-second.places-inline")
	private WebElement InputDestino;

	// Criando o construtor do Driver
	// Criamos o construtor com o mesmo nome da classe

	public decolar(WebDriver driver) {
		// inicializando o findBy
		PageFactory.initElements(driver, this);
		this.driver = driver;

	}

	public void pesquisarPacotes() throws IOException, InterruptedException {

//		if (TooltipResumoViagem.isDisplayed() == true) {
//			System.out.println(TooltipResumoViagem.getText());
//			TooltipResumoViagem.click();
//		} else {
//			System.out.println("O tooltip não apareceu\n");
//		}
		
		Thread.sleep(3000);
		InputOrigem.clear();
		Thread.sleep(3000);
		InputOrigem.sendKeys("São Paulo, São Paulo, Brasil", Keys.TAB);
		Thread.sleep(3000);
		
		InputDestino.sendKeys("Gramado, Rio Grande do Sul, Brasil");
		
		
		

		List<WebElement> resultados = TitulosHoteis;
		System.out.println("Pacotes encontrados: \n" + resultados.size());

		File arquivo = new File("tools/resultado_da_pesquisa.txt");

		FileWriter fw = new FileWriter(arquivo.getAbsoluteFile(), true);
		BufferedWriter bw = new BufferedWriter(fw);
		// Começa a escrever no arquivo
		// bw.write("###################### " + dataFormato.format(now) +
		// " \n");

		for (int i = 0; i < resultados.size(); i++) {

			System.out.println(resultados.get(i).getText() + ";" + ValorTotal.get(i).getText().substring(18) + ";"
					+ InfoVoo.get(i).getText() + ";" + aeroportoOrigem.get(i).getText() + ";"
					+ aeroportoDestino.get(i).getText() + ";" + enderecoCompleto.get(i).getText() + ";"
					+ notaHotel.getText());

			// Pula uma linha
			bw.write(resultados.get(i).getText() + ";" + ValorTotal.get(i).getText().substring(18) + ";"
					+ InfoVoo.get(i).getText() + ";" + aeroportoOrigem.get(i).getText() + ";"
					+ aeroportoDestino.get(i).getText() + ";" + enderecoCompleto.get(i).getText() + ";"
					+ notaHotel.getText());
			// Pula uma linha
			bw.write("\n");

		}
		bw.close();

	}

	@SuppressWarnings("deprecation")
	public void ordenarValores() throws InterruptedException {

		WebDriverWait listaOrdenacaoValores = new WebDriverWait(driver, 30);
		listaOrdenacaoValores.until(ExpectedConditions.elementToBeClickable(By.id("sorting")));

		selectOrdenarValor.sendKeys("Preço: Menor a maior");

		// System.out.println("Ordenando por menor valor! \n");

		Thread.sleep(2000);

	}

	public void proximaPagina() throws InterruptedException {

		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextIcon);
			Thread.sleep(300);
			nextIcon.click();

		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextIconCss);
			Thread.sleep(300);
			nextIconCss.click();
			Thread.sleep(3000);
		}

	}

//	public void gravarDados() throws IOException {
//
//		File localPlanilha = new File("tools/resultado_da_pesquisa.xlsx");
//
//		FileInputStream planilhaExistente = new FileInputStream(localPlanilha);
//		XSSFWorkbook plan = new XSSFWorkbook(planilhaExistente);
//		XSSFSheet sheetExistente = plan.getSheetAt(0);
//
//		List<WebElement> resultados = TitulosHoteis;
//		int resultado = resultados.size();
//		System.out.println("resultado :" + resultado);
//
//		for (int i = 0; i <= resultado-1; i++) {
//			Row row = sheetExistente.getRow(2 + i);
//			if (row == null)
//				sheetExistente.createRow(2 + i);
//			sheetExistente.getRow(2 + i).createCell(5).setCellValue(TitulosHoteis.get(i).getText());
//			// Gravando dados
//
//			sheetExistente.getRow(2 + i).createCell(6).setCellValue(notaHotel.getText());
//
//			sheetExistente.getRow(2 + i).createCell(7)
//					.setCellValue(PrecoPessoa.get(i).getText().substring(3).replace("\n", ""));
//			// sheetExistente.getRow(2 +
//			// i).createCell(8).setCellFormula(sheetExistente.getcw);
//
//			sheetExistente.getRow(2 + i).createCell(9).setCellValue(aeroportoOrigem.get(i).getText()
//					.replace("GRU", "GUARULHOS").replace("CGH", "CONGONHAS").replace("VCP", "VIRACOPOS"));
//
//			sheetExistente.getRow(2 + i).createCell(10)
//					.setCellValue(aeroportoDestino.get(i).getText().replace("MCO", "ORLANDO"));
//		}
//		FileOutputStream fechandoArquivo = new FileOutputStream(localPlanilha);
//		plan.write(fechandoArquivo);
//
//	}

}
