package rakiq2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map.Entry;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.google.gson.Gson;


public class Statistika implements Runnable {
	private Rakidjilnica rakidjilnica;

	public Statistika(Rakidjilnica rakidjilnica2) {
		this.rakidjilnica = rakidjilnica2;
	}

	@Override
	public void run() {
		int count = 0;
		File dir = new File("rakii");
		if (!dir.exists()) {
			dir.mkdirs();
		}
		while (true) {
			
			try (OutputStream os = new FileOutputStream(new File(dir, "file" + (count++)))) {
				Document document = DocumentHelper.createDocument();
				Element root = document.addElement("rakidjii");
				for (Entry<Rakidjiq, Integer> p : this.rakidjilnica.getRakidjii().entrySet()) {
					Element pratka = root.addElement("rakidjiq");
					pratka.addElement("ime").addText(p.getKey().getIme());
					pratka.addElement("svarenaRakiq").addText(p.getValue()+"");
				}

				// Pretty print the document to System.out
				OutputFormat format = OutputFormat.createPrettyPrint();
				XMLWriter writer;

				writer = new XMLWriter(os);
				writer.write(document);
				
//				String statistika = rakidjilnica.daiStatistiki();
//				os.write(statistika.getBytes());
				
				Gson gson = new Gson();
				os.write(gson.toJson(this.rakidjilnica.getRakii()).getBytes());

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
