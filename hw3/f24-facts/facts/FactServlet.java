//package facts;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.ArrayList;
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//
///**
// * Servlet implementation class FactServlet
// */
//@WebServlet("/FactServlet")
//
//public class FactServlet extends HttpServlet {
//
//	private static final String jsFile = "src/main/java/com/example/facts/facts/facts.js";
//	private static final String xmlFile = "src/main/java/com/example/facts/facts/data/facts.xml";
//	private static final String thisServlet = "http://localhost:8080/Test/";
//	private FactList list;
//
//	@Override
//	public void init() throws ServletException{
//		super.init();
//		Parser parser = new Parser(xmlFile);
//		list = parser.getFactList();
//	}
//
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	@Override
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
//	}
//
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	@Override
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//		String searchText  = request.getParameter("searchText");
//		String searchMode = request.getParameter("searchMode");
//		HttpSession session = request.getSession();
//		ServletContext servContext = getServletContext();
//		String sessionAttName = "sessionSearchList";
//		String contextAttName = "contextSearchList";
//		ArrayList<String> searchList = (ArrayList<String>) session.getAttribute(sessionAttName);
//		if (searchList == null){
//			searchList = new ArrayList<String>();
//			session.setAttribute(sessionAttName, searchList);
//		}
//		ArrayList<String> searchContextList = (ArrayList<String>) servContext.getAttribute(contextAttName);
//		if (searchContextList == null){  // if the session is new, the searchContextList won't exist.
//			searchContextList = new ArrayList<String>();
//			servContext.setAttribute(contextAttName, searchContextList);
//		}
//		if (searchText != null && searchText.length() > 0){
//			searchList.add(searchText);
//			searchContextList.add(searchText);
//		}
//		// Remove the oldest searches if more than 5
//		if (searchList.size() > 5){
//			searchList.remove(0);
//		}
//		if (searchContextList.size() > 5){
//			searchContextList.remove(0);
//		}
//		// Done with updating the search lists
//
//		/* Print HTML response page */
//		response.setContentType("text/html");
//		PrintWriter out = response.getWriter();
//		printHeader(out);
//		printBody(out);
//		printSearch(out, searchText, searchMode);
//		printSearcheHistory(out, searchList, searchContextList);
//		printFooter(out);
//	}
//
//	private void printHeader(PrintWriter out) {
//		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
//		out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
//		out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">");
//		out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\" />");
//		out.println("<head>");
//		out.println(" <title>Software Engineering</title>");
//		//out.println(" <script type=\"text/javascript\" src=\""+ jsFile +"\"></script>");
//		out.println("</head>");
//
//		out.println("<body onLoad=\"setFocus()\" bgcolor=\"AliceBlue\">");
//
//	}
//
//	private void printBody(PrintWriter out) throws ServletException, IOException{
//
//		out.println("<center><h2>Robert L. Glass: Facts and Fallacies of Software Engineering</h2></center>");
//		out.println("<hr/>");
//
//		Fact temp = list.getRandom();
//		out.println("<p><center>");
//		out.println("<div style=\"width:800px; color:yellow; background-color:gray\">");
//		out.println("<br/>&nbsp;&nbsp;&nbsp;&nbsp;" + temp.getText());
//		out.println("<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&mdash;" + temp.getAuthor());
//		out.println("<br/><br/><b><center>" + temp.getType() + "</b></center>");
//		out.println("</div></center>");
//
//		out.println("<br/>");
//		out.println("<form name=\"random\" method=\"post\" action=\"" + thisServlet + "\" >");
//		out.println("  <input type=\"submit\" value=\"Next\" name=\"submit\"/>");
//		out.println("</form>");
//		out.println("<hr/>");
//
//		// print the main form
//		// outer table: one row, one td for the form, one td for the last 5 searches
//		out.println("<table>");
//		out.println("<tr valign=\"top\"><td>");
//		out.println("<form name=\"FactServlet\" method=\"post\" action=\"" + thisServlet + "\" >");
//		out.println("  <table>");
//
//		out.println("    <tr>");
//		out.println("      <th>Search :</th>");
//		out.println("      <td>");
//		out.println("        <input type=\"text\" id=\"searchText\" name=\"searchText\" value=\"\" size=\"50\" />");
//		out.println("      </td>");
//		out.println("    </tr>");
//
//		out.println("    <tr>");
//		out.println("      <th>Mode :</th>");
//		out.println("      <td>");
//		out.println("        <input type=\"radio\" id=\"text\"  name=\"searchMode\" value=\"text\"/>");
//		out.println("        <label for=\"text\">text</label>");
//		out.println("        <input type=\"radio\" id=\"author\" name=\"searchMode\" value=\"author\"/>");
//		out.println("        <label for=\"author\">author</label>");
//		out.println("        <input type=\"radio\" id=\"type\" name=\"searchMode\" value=\"type\"/>");
//		out.println("        <label for=\"type\">type</label>");
//		out.println("        <input type=\"radio\" id=\"all\"   name=\"searchMode\" value=\"all\" checked=\"checked\"/>");
//		out.println("        <label for=\"all\">all</label>");
//
//		out.println("      </td>");
//		out.println("    </tr>");
//
//		out.println("    <tr>");
//		out.println("      <th></th>");
//		out.println("      <td align=\"center\">");
//		out.println("        <input type=\"submit\" value=\"search\" name=\"submit\"/>");
//		out.println("        <input type=\"reset\"  value=\"reset\"  name=\"reset\"/>");
//		out.println("      </td>");
//		out.println("    </tr>");
//
//		out.println("  </table>");
//		out.println("</form>");
//
//	}
//
//	private void printFooter(PrintWriter out) {
//		out.println("</p>");
//		out.println("</body>");
//		out.println("</html>");
//	}
//
//
//	private void printSearch(PrintWriter out, String searchText, String searchMode) {
//		if (searchText != null && !searchText.equals("")){  // Received a search request
//			int searchModeVal = FactSearchMode.ALL_VAL; // Default
//			if (searchMode != null && !searchMode.equals("")){  // If no parameter value, let it default.
//				if (searchMode.equals("text")){
//					searchModeVal = FactSearchMode.TEXT_VAL;
//				} else if (searchMode.equals("author")){
//					searchModeVal = FactSearchMode.AUTHOR_VAL;
//				} else if (searchMode.equals("type")){
//					searchModeVal = FactSearchMode.TYPE_VAL;
//				}
//			}
//			FactList results = list.search(searchText, searchModeVal);
//			Fact temp;
//			if (results.getSize() == 0){
//				out.println("<p>Not Found! </p>");
//			}else{
//				out.println("<dl>");
//				for (int i = 0; i < results.getSize() ; i++){
//					temp = results.get(i);
//					out.println("<dt>" + temp.getText() + "</dt>");
//					out.println("<dd>&mdash;" + temp.getAuthor() + "</dd>");
//					out.println("<dd>&mdash;" + temp.getType() + "</dd>");
//				}
//				out.println("</dl>");
//			}
//			out.println("</td>");
//		}
//
//	}
//
//
//	private void printSearcheHistory(PrintWriter out, ArrayList<String> searchList, ArrayList<String> searchContextList) {
//		out.println("<td width=100>&nbsp;</td>");
//		out.println("<td style=\"text-align:center\">");
//		out.println("  <span style=\"font-weight:bold;font-size:120%\">Search History</span>");
//		out.println("  <table border=1 style=\"background-color:MistyRose \">");
//		out.println("    <tr>");
//		out.println("    <td style=\"text-align:center\">");
//		out.println("    <span style=\"font-weight:bold;font-size:110%\">Your Searches</span>");
//		out.println("    <td style=\"text-align:center\">");
//		out.println("    <span style=\"font-weight:bold;font-size:110%\">Other Searches</span>");
//		out.println("    <tr>");
//		out.println("    <td style=\"text-align:left\">");
//		out.println("    <ol>");
//		String searchTmp = "";
//
//		for (int i = 0; i < searchList.size(); i++){  // The ith search string
//			searchTmp = searchList.get(i);
//			out.println("      <li><a href=\"" + thisServlet + "?searchText=" + searchTmp + "&searchMode=both\" >"+searchTmp+"</a></li>");
//		}
//
//		out.println("    </ol>");
//		out.println("    <td style=\"text-align:left\">");
//		out.println("    <ol>");
//
//		for (int i = 0; i < searchContextList.size(); i++){  // The ith search string
//			searchTmp = searchContextList.get(i);
//			out.println("      <li><a href=\"" + thisServlet + "?searchText=" + searchTmp + "&searchMode=both\" >"+searchTmp+"</a></li>");
//		}
//
//		out.println("    </ol>");
//		out.println("  </tr></table>");
//		out.println("</td></tr></table>");
//
//	}
//
//
//
//
//
//
//}
