package gr.ntua.ece.softeng.config;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import gr.ntua.ece.softeng.repositories.EventRepository;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;


public class PDFConfig {

	private static Font TIME_ROMAN = new Font(Font.FontFamily.TIMES_ROMAN, 18,Font.BOLD);
	private static Font TIME_ROMAN_SMALL = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

	private static String font = "./resources/font.ttf";

    private static String firstname;
    private static String lastname;
	private static String eventName;
	private static Integer Price;
	private static Integer tickets;

	public static Document createPDF(String file,String firstName,String lastName, String eventname,Integer Tickets, Integer TotalPrice) {

		Document document = null;
		firstname=firstName;
		lastname=lastName;
		eventName=eventname;
		Price=TotalPrice;
		tickets=Tickets;

		try {
			document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();

			addMetaData(document);

			addTitlePage(document);

			createTable(document);

			document.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return document;
	}


	private static void addMetaData(Document document) {
		document.addTitle("Ticket");
		document.addSubject("Ticket");

	}

	private static void addTitlePage(Document document)
			throws DocumentException {

		Paragraph preface = new Paragraph();
		creteEmptyLine(preface, 1);

		FontFactory.register(font,"Greek-Regular");
		Font f = FontFactory.getFont("Greek-Regular", "Cp1253", true);

		preface.add(new Paragraph("Επιβεβαίωση κράτησης ", f));
		preface.add(new Paragraph("Καλησπέρα "+firstname +" "+ lastname,f));
		preface.add(new Paragraph("Να είστε καλά που επιλέξατε την fsociety για την κράτηση σας στη δραστηριότητα "+eventName,f));
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		preface.add(new Paragraph("Στις "+ simpleDateFormat.format(new Date()) + " κρατήσατε " +tickets.toString() + " εισιτήρια κόστους " +Price.toString()+ " €",f));
		preface.add(new Paragraph(" "));
		preface.add(new Paragraph("Για τη βελτίωση του site και την καλύτερη εξυπηρέτησή σας στείλτε τις παρατηρήσεις σας fsocietysofteng@gmail.com ",f));
		document.add(preface);




	}

	private static void creteEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
	private static void createTable(Document document) throws DocumentException {
		Paragraph paragraph = new Paragraph();
		creteEmptyLine(paragraph, 2);
		document.add(paragraph);
		PdfPTable table = new PdfPTable(5);

		PdfPCell c1 = new PdfPCell(new Phrase("First Name"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Last Name"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);


		c1 = new PdfPCell(new Phrase("Event Name"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);


		c1 = new PdfPCell(new Phrase("Number of Tickets"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Total Price"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
        FontFactory.register(font,"Greek-Regular");
        Font f = FontFactory.getFont("Greek-Regular", "Cp1253", true);

		table.setHeaderRows(1);
		table.setWidthPercentage(100);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(new Phrase(firstname,f));
		table.addCell(new Phrase(lastname, f));
		table.addCell(new Phrase(eventName, f));
		table.addCell(tickets.toString());
		table.addCell(Price.toString());


		//document.add(table);
	}



}
