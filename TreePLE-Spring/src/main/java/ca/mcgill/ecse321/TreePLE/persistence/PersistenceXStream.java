package ca.mcgill.ecse321.TreePLE.persistence;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Repository;
import com.thoughtworks.xstream.XStream;

import ca.mcgill.ecse321.TreePLE.model.Forecast;
import ca.mcgill.ecse321.TreePLE.model.LocalUser;
import ca.mcgill.ecse321.TreePLE.model.Municipality;
import ca.mcgill.ecse321.TreePLE.model.Person;
import ca.mcgill.ecse321.TreePLE.model.Report;
import ca.mcgill.ecse321.TreePLE.model.Role;
import ca.mcgill.ecse321.TreePLE.model.Scientist;
import ca.mcgill.ecse321.TreePLE.model.Status;
import ca.mcgill.ecse321.TreePLE.model.Survey;
import ca.mcgill.ecse321.TreePLE.model.SystemDate;
import ca.mcgill.ecse321.TreePLE.model.Tree;
import ca.mcgill.ecse321.TreePLE.model.TreeManager;

// The first type parameter is the domain type for wich we are creating the repository.
// The second is the key type that is used to look it up. This example will not use it.
@Repository
public class PersistenceXStream {

	private static XStream xstream = new XStream();
	private static String filename = "data.xml";

	// TODO create the TreePLEManager instance here (replace the void return value as well)
	public static TreeManager initializeModelManager(String fileName) {

	TreeManager tm;
	
	setFilename(fileName);
	setAlias("forecast", Forecast.class);
	setAlias("localUser", LocalUser.class);
	setAlias("municipality", Municipality.class);
	setAlias("person", Person.class);
	setAlias("report", Report.class);
	setAlias("role", Role.class);
	setAlias("scientist", Scientist.class);
	setAlias("status", Status.class);
	setAlias("survey", Survey.class);
	setAlias("systemDate", SystemDate.class);
	setAlias("Tree", Tree.class);
	setAlias("TreePLE", TreeManager.class);
	
	// load model if exists, create otherwise
    File file = new File(fileName);
    if (file.exists()) {
        tm = (TreeManager) loadFromXMLwithXStream();
    } else {
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        tm = new TreeManager();
        saveToXMLwithXStream(tm);
    }
    return tm;
	}

	public static boolean saveToXMLwithXStream(Object obj) {
		xstream.setMode(XStream.ID_REFERENCES);
        String xml = xstream.toXML(obj); // save our xml file

        try {
            FileWriter writer = new FileWriter(filename);
            writer.write(xml);
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
	}

	public static Object loadFromXMLwithXStream() {
		xstream.setMode(XStream.ID_REFERENCES);
        try {
            FileReader fileReader = new FileReader(filename); // load our xml file
            return xstream.fromXML(fileReader);
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
	}

	public static void setAlias(String xmlTagName, Class<?> className) {
		xstream.alias(xmlTagName, className);
	}

	public static void setFilename(String fn) {
		filename = fn;
	}

	public static String getFilename() {
		return filename;
	}

	public static void clearData() {
		File myFoo = new File(filename);
		FileWriter fooWriter;
		try {
			fooWriter = new FileWriter(myFoo, false);
			fooWriter.write("");
			fooWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
