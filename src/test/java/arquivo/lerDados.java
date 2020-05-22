package arquivo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class lerDados {
	public static void main(String[] args) throws IOException {
		File localdaPlanilha = new File("C:\\temp\\dados.xlsx");
		FileInputStream Planilha = new FileInputStream(localdaPlanilha);
		XSSFWorkbook PlanilhaSerLida = new XSSFWorkbook(Planilha);
		XSSFSheet sheet = PlanilhaSerLida.getSheetAt(0);
		FileOutputStream SeraGravado = new FileOutputStream(localdaPlanilha);

		System.out.println("Inciando a leitura da planilha...\n");

		Row linha = sheet.createRow(0);
		
		
		for (int i = 0; i < 5; i++) {
			Cell celula = linha.createCell(i);
			celula.setCellValue("teste" + i);

		}
		PlanilhaSerLida.write(SeraGravado);
		Planilha.close();

	}
}
