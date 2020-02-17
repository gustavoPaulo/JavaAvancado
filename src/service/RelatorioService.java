package service;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

public class RelatorioService implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String FOLDER_RELATORIOS = "/relatorios";
	private static final String SUBREPORT_DIR = "SUBREPORT_DIR";
	private String SEPARATOR = File.separator;
	private String caminhoArquivoRelatorio = null;
	private JRExporter exporter = null;
	private String caminhoSubReport_Dir = "";
	private File arquivoGerado = null;

	public String gerarRelatorio(List<?> listaDataBeanColletion, HashMap parametrosRelatorio,
			String nomeRelatorioJasper, String nomeRelatorioSaida, ServletContext servletContext) throws Exception {

		// Cria a lsita de CollectionDataSource de benas que carrega os dados o relatorio
		JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(listaDataBeanColletion);

		// Fornece o caminho físico até a pasta que contem os relatórios .jasper
		String caminhoRelatorio = servletContext.getRealPath(FOLDER_RELATORIOS);

		File file = new File(caminhoRelatorio + SEPARATOR + nomeRelatorioJasper + ".jasper");

		if (caminhoRelatorio == null || (caminhoRelatorio != null && caminhoRelatorio.isEmpty()) || file.exists()) {

			caminhoRelatorio = this.getClass().getResource(FOLDER_RELATORIOS).getPath();
			SEPARATOR = "";
		}

		// Caminho para imagnes
		parametrosRelatorio.put("REPORT_PARAMETERS_IMG", caminhoRelatorio);

		// Caminho completo até o relatório compilado indicado
		String caminhoArquivosJasper = caminhoRelatorio + SEPARATOR + nomeRelatorioJasper + ".jasper";

		// Faz o carregamento do relatorio
		JasperReport relatorioJaspar = (JasperReport) JRLoader.loadObjectFromFile(caminhoArquivosJasper);

		// Seta parametros SUBREPORT_DIR com o caminho fisico para subreport
		caminhoSubReport_Dir = caminhoRelatorio + SEPARATOR;
		parametrosRelatorio.put(SUBREPORT_DIR, caminhoSubReport_Dir);

		// Carregar arquivo compilado para memoria
		JasperPrint impressoJasper = JasperFillManager.fillReport(relatorioJaspar, parametrosRelatorio, jrbcds);

		exporter = new JRPdfExporter();

		// Caminho do relatorio exportado
		caminhoArquivoRelatorio = caminhoRelatorio + SEPARATOR + nomeRelatorioSaida + ".pdf";

		//Criar novo arquivos exportado
		arquivoGerado = new File(caminhoArquivoRelatorio);
		
		//Prepara a impressão
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, impressoJasper);
		
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE, arquivoGerado);
		
		//Executa a exportação
		exporter.exportReport();
		
		//Remove o arquivo do servidor após ser feito o download
		arquivoGerado.deleteOnExit();
		
		
		
		return caminhoArquivoRelatorio;
	}

}