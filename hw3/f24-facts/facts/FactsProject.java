package facts;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FactsProject extends JFrame{
    private JPanel contentPane;
    private JLabel robertLGlassFactsLabel;
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
    private JTextArea yourSearchHistoryTestArea;
    private JPanel searchResultPanel;
    private JTextField searchResult;
    private ButtonGroup modeRadioButtonGroup;

    private static final String xmlFile = "f24-facts/facts/data/facts.xml";
    private final FactList list;
    Parser parser = new Parser(xmlFile);
    private ArrayList<String> searchHistoryList;

    public FactsProject() {

        // Overall Window settings and setup
        setTitle("Facts Project");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(contentPane);
        setSize(1080,800);
        list = parser.getFactList();
        searchHistoryList = new ArrayList<>();
        searchResults.getCaret().setVisible(false);
        displayRandomQuote();

        // Listener for the "Search" button
        // Upon press, it will print out the search results and update the search history
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
                searchHistoryList.add(searchText);
                printSearchHistory(searchHistoryList);
            }
        });

        // Listener for the "Reset" button
        // Default selected radio button is "All"
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                allModeRadioButton.setSelected(true);
                searchResults.setText("");
                searchBoxTextField.setText("");
            }
        });

        // Listener for the "Next" button
        nextRandomQuoteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayRandomQuote();
            }
        });
    }


    /**
     * Gets a random quote from the FactList and display it on the main big quote.
     */
    private void displayRandomQuote(){
        Fact randFact = list.getRandom();
        String sb = randFact.getText() +
                "\n   —" +
                randFact.getAuthor() +
                "\n   —" +
                randFact.getType();
        bigQuoteLabel.setText(sb);
        bigQuoteLabel.getCaret().setVisible(false);//useful
    }


    /**
     * Prints out the search query result from user's input to the screen.
     * Adapted from the original FactServlet.java code.
     *
     * @param searchText The string to search for in within the FactList elements
     * @param searchMode Defines if all attributes in the Fact list are compared against
     *                   the searchText or if only a particular attribute is desired.
     */
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
                searchResults.setText("Not Found!");
            }else{ // Build the string that will be shown to the user
                String searchResultStr = "";
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < results.getSize() ; i++){
                    temp = results.get(i);
                    sb.append(temp.getText());
                    sb.append("\n   —");
                    sb.append(temp.getAuthor());
                    sb.append("\n   —");
                    sb.append(temp.getType());
                    sb.append("\n");
                    sb.append("\n");
                }
                searchResultStr = sb.toString();
                searchResults.setText(searchResultStr);

            }
        }
    }


    /**
     * Prints out user's current search history to the search history box.
     * Adapted from the original FactServlet.java code.
     *
     * @param searchList ArrayList of a user's previous searches
     *
     */
    private void printSearchHistory(ArrayList<String> searchList) {
        String searchTmp = "";
        yourSearchHistoryTestArea.setText("");

        for (int i = 0; i < searchList.size(); i++){  // The ith search string
            searchTmp = searchList.get(i) + '\n';
            yourSearchHistoryTestArea.setText(yourSearchHistoryTestArea.getText() + searchTmp);
        }
    }









}
