package facts;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FactsProject extends JFrame{
    private JPanel contentPane;
    private JLabel robertLGlassFactsLabel;
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
    private JTextArea searchResults;
    private JTextArea bigQuoteLabel;
    private JPanel bigQuotePanel;
    private JPanel searchResultPanel;
    private JTextField searchResult;
    private ButtonGroup modeRadioButtonGroup;

    private static final String xmlFile = "f24-facts/facts/data/facts.xml";
    private FactList list;
    Parser parser = new Parser(xmlFile);

    public FactsProject() {

        // Overall Window settings
        setTitle("Facts Project");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(contentPane);
        setSize(850,400);
        list = parser.getFactList();
        // Main label settings
        displayRandomQuote();

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchBoxTextField.getText();
                String searchMode = "";
                if(textModeRadioButton.isSelected()){
                    searchMode = "text";
                }
                else if(authorModeRadioButton.isSelected()){
                    searchMode = "author";
                }
                else if(typeModeRadioButton.isSelected()){
                    searchMode = "type";
                }
                else if(allModeRadioButton.isSelected()){
                    searchMode = "all";
                }
                printSearch(searchText, searchMode);
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                allModeRadioButton.setSelected(true);
                searchResults.setText("");
                searchBoxTextField.setText("");
            }
        });

        nextRandomQuoteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayRandomQuote();
            }
        });
    }

    private void displayRandomQuote(){
        Fact randFact = list.getRandom();
        StringBuilder sb = new StringBuilder();
        sb.append(randFact.getText());
        sb.append("\n   -");
        sb.append(randFact.getAuthor());
        sb.append("\n   -");
        sb.append(randFact.getType());
        bigQuoteLabel.setText(sb.toString());
        bigQuoteLabel.getCaret().setVisible(false);
    }
    ///
    /// The code beneath has been adapted form FactServlet.java
    ///

    private void printSearch( String searchText, String searchMode) {
        if (searchText != null && !searchText.equals("")){  // Received a search request
            int searchModeVal = FactSearchMode.ALL_VAL; // Default
            if (searchMode != null && !searchMode.equals("")){  // If no parameter value, let it default.
                if (searchMode.equals("text")){
                    searchModeVal = FactSearchMode.TEXT_VAL;
                } else if (searchMode.equals("author")){
                    searchModeVal = FactSearchMode.AUTHOR_VAL;
                } else if (searchMode.equals("type")){
                    searchModeVal = FactSearchMode.TYPE_VAL;
                }
            }
            FactList results = list.search(searchText, searchModeVal);
            Fact temp;
            if (results.getSize() == 0){
                //System.out.println("Not Found!");
                searchResults.setText("Not Found!");
            }else{
                String searchResultStr = "";
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < results.getSize() ; i++){
                    temp = results.get(i);
                    sb.append(temp.getText());
                    sb.append("\n   -");
                    sb.append(temp.getAuthor());
                    sb.append("\n   -");
                    sb.append(temp.getType());
                    sb.append("\n");
                    sb.append("\n");
                    //System.out.println(temp.getText());
                    //System.out.println(temp.getAuthor());
                    //System.out.println(temp.getType());
                }
                searchResultStr = sb.toString();
                searchResults.setText(searchResultStr);
            }
        }
    }

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
