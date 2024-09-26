package facts;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                FactsProject factsProject = new FactsProject();
                factsProject.setVisible(true);
            }
        });
    }
}
