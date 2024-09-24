package facts;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;

public class Handler extends DefaultHandler{
	private FactList list = new FactList();
	private Fact temp = null; // temporary fact
	private String currentElement = null; // current element name

	// Node names in XML file
	private final String factListElem   = "fact-list";
	private final String factElem       = "fact";
	private final String factAuthorElem = "author";
	private final String factTextElem   = "fact-text";
	private final String factTypeElem   = "fact-type";

	public Handler(){
		super();
	}

	public FactList getList(){
		return list;
	}

	@Override
	public void startDocument (){
	}

	@Override
	public void endDocument (){
	}

	@Override
	public void startElement (String uri, String name, String qName, Attributes atts){
		if (qName.equalsIgnoreCase (factListElem)){
			currentElement = factListElem;
		}
		else if (qName.equalsIgnoreCase(factElem)){
			currentElement = factElem;
			temp = new Fact();
		}
		else if (qName.equalsIgnoreCase (factAuthorElem)){
			currentElement = factAuthorElem;
		}
		else if (qName.equalsIgnoreCase (factTextElem)){
			currentElement = factTextElem;
		}
		else if (qName.equalsIgnoreCase (factTypeElem)){
			currentElement = factTypeElem;
		}
	}

	@Override
	public void endElement (String uri, String name, String qName){
		if (qName.equalsIgnoreCase (factElem)){
			list.set(temp);
			temp = null;
		}
	}

	@Override
	public void characters (char ch[], int start, int length){
		String value = new String (ch, start, length);

		if (!value.trim().equals("")){
			if (currentElement.equalsIgnoreCase(factTextElem)){
				temp.setText(value);
			}
			else if (currentElement.equalsIgnoreCase(factTypeElem)){
				temp.setType(value);
			}
			else if (currentElement.equalsIgnoreCase(factAuthorElem)){
				temp.setAuthor(value);
			}
		}
	}

}
