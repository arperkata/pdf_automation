package xfapdf.test;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class testxfapdf {

	static String filepath = System.getProperty("user.dir")+ "\\PDF_file\\UAE.pdf";
	static String resultfilepath = System.getProperty("user.dir")+ "\\PDF_file\\UAE_Result.pdf";
	
	public static void manipulatePdf(String src, String dest) throws DocumentException, IOException 
	{
		PdfReader reader = new PdfReader(src);
		PdfReader.unethicalreading = true;
		PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
		AcroFields fields = stamper.getAcroFields();
		fields.setField("ES_003_TextField", "9911119");
		fields.setField("ES_005_TextField", "111");

		stamper.close();
		reader.close();
	}

	static boolean stauts;
	
	public static boolean elementfind(String file,String fieldname) throws IOException {
		// TODO Auto-generated method stub
		PdfReader reader = new PdfReader(file);
		reader.unethicalreading = true;
		AcroFields fields = reader.getAcroFields();
		String test = fields.getField(fieldname);
		System.err.println(isNull(test));
		stauts = isNull(test);
		return stauts;
	}
	
	public static boolean isNull(String str) {
        if(str != null)
            return false;
        return true;
    }
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws IOException, InterruptedException {
		elementfind(resultfilepath,"ES_004_TextField");
		/*
		 * try { manipulatePdf(filepath, resultfilepath);
		 * 
		 * 
		 * } catch (DocumentException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated catch
		 * e.printStackTrace(); }
		 */
		Thread.sleep(4000);

		PdfReader reader = new PdfReader(filepath);
		reader.unethicalreading = true;

		AcroFields fields = reader.getAcroFields();

		//int fldNames = fields.getTotalRevisions();

		System.out.println(fields.getField("ES_003_TextField"));
	}
}
