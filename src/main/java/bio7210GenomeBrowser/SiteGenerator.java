package bio7210GenomeBrowser;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SiteGenerator {

	List<Item> items() {
		return Arrays.asList(new Item("Item 1", "$19.99", Arrays.asList(new Feature("New!"), new Feature("Awesome!"))),
				new Item("Item 2", "$29.99", Arrays.asList(new Feature("Old."), new Feature("Ugly."))));
	}
	
	List<StrainTableEntry> strains(){
		ArrayList<StrainTableEntry> strains = new ArrayList<StrainTableEntry>();
		
		for(int i = 1; i <= 24; i++){
			String strainName = "OB" + String.format("%04d", i);
			String strainUrl = "http://gbrowse2017a.biology.gatech.edu/hp/JBrowse-1.12.1/index.html?data=chromosomes_data/"+strainName;
			String plasmidUrl = "http://gbrowse2017a.biology.gatech.edu/hp/JBrowse-1.12.1/index.html?data=plasmid_data/"+strainName;
			strains.add(new StrainTableEntry(strainName, strainUrl, plasmidUrl));
		}
		
		return strains;
	}

	static class Item {
		Item(String name, String price, List<Feature> features) {
			this.name = name;
			this.price = price;
			this.features = features;
		}

		String name, price;
		List<Feature> features;
	}

	static class Feature {
		Feature(String description) {
			this.description = description;
		}

		String description;
	}

	public static void generateHomePage() throws IOException {
		MustacheFactory mf = new DefaultMustacheFactory();
		Mustache header = mf.compile("browserHeader.html");
		Mustache body = mf.compile("homeBody.html");
		Mustache footer = mf.compile("browserFooter.mustache");
		File file = new File("./src/main/htmlOutput/index.html");
		file.createNewFile();
		FileWriter fileWriter = new FileWriter(file);
		header.execute(new PrintWriter(fileWriter), new SiteGenerator()).flush();
		body.execute(new PrintWriter(fileWriter), new SiteGenerator()).flush();
		footer.execute(new PrintWriter(fileWriter), new SiteGenerator()).flush();
		fileWriter.flush();
		fileWriter.close();
	}
	
	public static void generateStrainPage() throws IOException {
		MustacheFactory mf = new DefaultMustacheFactory();
		Mustache header = mf.compile("browserHeader.html");
		Mustache body = mf.compile("strainPage.html");
		Mustache footer = mf.compile("browserFooter.mustache");
		File file = new File("./src/main/htmlOutput/strains.html");
		file.createNewFile();
		FileWriter fileWriter = new FileWriter(file);
		header.execute(new PrintWriter(fileWriter), new SiteGenerator()).flush();
		body.execute(new PrintWriter(fileWriter), new SiteGenerator()).flush();
		footer.execute(new PrintWriter(fileWriter), new SiteGenerator()).flush();
		fileWriter.flush();
		fileWriter.close();
	}
	
	public static void generateVFDBPage() throws IOException {
		MustacheFactory mf = new DefaultMustacheFactory();
		Mustache header = mf.compile("browserHeader.html");
		Mustache body = mf.compile("vfdb-query.html");
		Mustache footer = mf.compile("browserFooter.mustache");
		File file = new File("./src/main/htmlOutput/vfdb-query.html");
		file.createNewFile();
		FileWriter fileWriter = new FileWriter(file);
		header.execute(new PrintWriter(fileWriter), new SiteGenerator()).flush();
		body.execute(new PrintWriter(fileWriter), new SiteGenerator()).flush();
		footer.execute(new PrintWriter(fileWriter), new SiteGenerator()).flush();
		fileWriter.flush();
		fileWriter.close();
	}
	
	public static void generateComparativePage() throws IOException {
		MustacheFactory mf = new DefaultMustacheFactory();
		Mustache header = mf.compile("browserHeader.html");
		Mustache body = mf.compile("comparative.html");
		Mustache footer = mf.compile("browserFooter.mustache");
		File file = new File("./src/main/htmlOutput/comparative.html");
		file.createNewFile();
		FileWriter fileWriter = new FileWriter(file);
		header.execute(new PrintWriter(fileWriter), new SiteGenerator()).flush();
		body.execute(new PrintWriter(fileWriter), new SiteGenerator()).flush();
		footer.execute(new PrintWriter(fileWriter), new SiteGenerator()).flush();
		fileWriter.flush();
		fileWriter.close();
	}

	public static void main(String[] args) throws IOException {
		generateHomePage();
		generateStrainPage();
		generateVFDBPage();
		generateComparativePage();
	}
}
