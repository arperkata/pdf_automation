package xfapdf.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfEncryptor;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;



public class testpdf 

{
	
	static String filepath = System.getProperty("user.dir")+ "\\PDF_file\\testpdf.pdf";
	static String resultfilepath = System.getProperty("user.dir")+ "\\PDF_file\\result.pdf";
	
	public static void manipulatePdf(String src, String dest) throws DocumentException, IOException 
	
	{
	    PdfReader reader = new PdfReader(src);
	    PdfStamper stamper = new PdfStamper(reader,
	            new FileOutputStream(dest));
	    
	    //stamper = new PdfStamper(reader, new FileOutputStream(dest), '\0', true);
	    
	    AcroFields fields = stamper.getAcroFields();
	    fields.setField("Given Name Text Box", "GivenName");
	    fields.setField("Family Name Text Box", "FamilyName");
	    fields.setField("Address 1 Text Box", "Address1");
	    fields.setField("Language 2 Check Box", "Yes");
	    fields.setField("Favourite Colour List Box", "Black");
	    fields.setField("Driving License Check Box", "Yes");
		stamper.close();
	    reader.close();
	}
	
	 public static void keysetfield(String filepath) throws IOException {
		// TODO grab all field from the pdf
		 PdfReader reader = new PdfReader(filepath);
		 
			AcroFields fields = reader.getAcroFields();

			Set<String> fldNames = fields.getFields().keySet();

			for (String fldName : fldNames) {
			  System.out.println( fldName + ": " + fields.getField( fldName ) );
			}

	}
	 
	 public static void getEncryptionInformation(String filename, String ownerpassword)
		      throws Exception {
		    PdfReader reader;
		    if (ownerpassword == null)
		      reader = new PdfReader(filename);
		    else
		      reader = new PdfReader(filename, ownerpassword.getBytes());
		    System.out.println("Encrypted? " + reader.isEncrypted());
		    if (reader.isEncrypted()) {
		      System.out.println("Permissions: "
		          + PdfEncryptor.getPermissionsVerbose((int) reader.getPermissions()));
		      System.out.println("128 bit? " + reader.is128Key());
		    }
		  }
	public static void main(String[] args) throws IOException, InterruptedException {
		
		  try 
		  { 
			 manipulatePdf(filepath, resultfilepath); 
			 Thread.sleep(5000);
			  getEncryptionInformation(filepath, null);
			  keysetfield(filepath);
			  
		  } 
		  catch(Exception e) 
		  { 
		  e.printStackTrace(); 
		  }
		Thread.sleep(4000);
		
		
	}

}
