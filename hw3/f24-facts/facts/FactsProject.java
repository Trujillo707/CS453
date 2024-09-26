package facts;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FactsProject extends JFrame{
    private JPanel contentPane;
    private JLabel robertLGlassFactsLabel;
    private JLabel bigQuoteLabel;
    private JSeparator seperator1;
    private JSeparator separator2;
    private JButton nextRandomQuoteButton;
    private JTextField searchBoxTextField;
    private JLabel searchHistoryLabel;
    private JLabel modeTextLabel;
    private JRadioButton textModeRadioButton;
    private JRadioButton authorModeRadioButton;
    private JRadioButton typeModeRadioButton;
    private JRadioButton allModeRadioButton;
    private JButton searchButton;
    private JButton resetButton;
    private JLabel yourSearchesLabel;
    private JLabel otherSearchesLabel;
    private JPanel searchOptionsPanel;
    private JPanel searchHistoryPanel;

    private static final String xmlFile = "src/main/java/com/example/facts/facts/data/facts.xml";
    private FactList list;


    public FactsProject() {

        // Overall Window settings
        setTitle("Facts Project");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(contentPane);
        setSize(800,400);

        // Main label settings


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        nextRandomQuoteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    ///
    /// The code beneath has been adapted form FactServlet.java
    ///

//    private void printSearch( String searchText, String searchMode) {
//        if (searchText != null && !searchText.equals("")){  // Received a search request
//            int searchModeVal = FactSearchMode.ALL_VAL; // Default
//            if (searchMode != null && !searchMode.equals("")){  // If no parameter value, let it default.
//                if (searchMode.equals("text")){
//                    searchModeVal = FactSearchMode.TEXT_VAL;
//                } else if (searchMode.equals("author")){
//                    searchModeVal = FactSearchMode.AUTHOR_VAL;
//                } else if (searchMode.equals("type")){
//                    searchModeVal = FactSearchMode.TYPE_VAL;
//                }
//            }
//            FactList results = list.search(searchText, searchModeVal);
//            Fact temp;
//            if (results.getSize() == 0){
//                out.println("<p>Not Found! </p>");
//            }else{
//                out.println("<dl>");
//                for (int i = 0; i < results.getSize() ; i++){
//                    temp = results.get(i);
//                    out.println("<dt>" + temp.getText() + "</dt>");
//                    out.println("<dd>&mdash;" + temp.getAuthor() + "</dd>");
//                    out.println("<dd>&mdash;" + temp.getType() + "</dd>");
//                }
//                out.println("</dl>");
//            }
//            out.println("</td>");
//        }
//
//    }

//    private void printSearcheHistory(ArrayList<String> searchList, ArrayList<String> searchContextList) {
//        String searchTmp = "";
//
//        for (int i = 0; i < searchList.size(); i++){  // The ith search string
//            searchTmp = searchList.get(i);
//            out.println("      <li><a href=\"" + thisServlet + "?searchText=" + searchTmp + "&searchMode=both\" >"+searchTmp+"</a></li>");
//        }
//
//        for (int i = 0; i < searchContextList.size(); i++){  // The ith search string
//            searchTmp = searchContextList.get(i);
//            out.println("      <li><a href=\"" + thisServlet + "?searchText=" + searchTmp + "&searchMode=both\" >"+searchTmp+"</a></li>");
//        }
//
//    }









}
