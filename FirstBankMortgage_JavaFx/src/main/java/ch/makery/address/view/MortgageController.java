package ch.makery.address.view;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.lang.Number;
import java.text.DecimalFormat;
import java.util.UUID;
import base.RateDAL;
import ch.makery.address.MainApp;
import ch.makery.address.model.Rate;



public class MortgageController {
	@FXML
	private Label IncomeLabel = new Label();
	@FXML
	private Label ExpensesLabel = new Label();
	@FXML
	private Label CreditScoreLabel = new Label();
	@FXML
	private Label HousePriceLabel = new Label();
	@FXML
	private Label MortgagePaymentLabel = new Label();
	@FXML
	private Label termLabel = new Label();
	@FXML
	private Label waitLabel = new Label();
	@FXML
	private ComboBox<String> cmbTerm;
	@FXML
	private Button SubmitButton;
	
	@FXML
	private TextField txtIncome;
	@FXML
	private TextField txtExpenses;
	@FXML
	private TextField txtCreditScore;
	@FXML
	private TextField txtHouseCost;


    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public MortgageController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	IncomeLabel.setText("Annual Income");
    	IncomeLabel.setVisible(true);
    	ExpensesLabel.setText("Monthly Expenses");
    	ExpensesLabel.setVisible(true);
    	CreditScoreLabel.setText("Credit Score");
    	HousePriceLabel.setText("Please enter the expected price of your home");
    	HousePriceLabel.setVisible(true);
    	ObservableList<String> terms = FXCollections.observableArrayList("15 Years","30 Years");
    	cmbTerm.setItems(terms);
    	MortgagePaymentLabel.setVisible(false);
    	termLabel.setText("Do you want a 15 year or 30 year mortgage?");
    	termLabel.setVisible(true);
    	waitLabel.setVisible(false);
    }
    
    private void calculateMort() {
    	 Double income = Double.parseDouble(this.txtIncome.getText());
    	 int intIncome = income.intValue();
         Double expenses = Double.parseDouble(this.txtExpenses.getText());
         int intExpenses = expenses.intValue()*12;
         int creditScor = Integer.parseInt(this.txtCreditScore.getText());
         //int intCreditScor = creditScor.intValue();
         Double houseCost = Double.parseDouble(this.txtHouseCost.getText());
         int intHouseCost = houseCost.intValue();
         int terms = 0;
         if (this.cmbTerm.getSelectionModel().getSelectedIndex() == 0){
         	terms = 15;
         }
         else if(this.cmbTerm.getSelectionModel().getSelectedIndex() == 1){
         	terms = 30;
         }
         else {
        	 waitLabel.setText("Please fill out all the required information");
        	 waitLabel.setVisible(true);
        	 calculateMort();
         }
         double monthlyMort = ((Rate.getPayment(creditScor, intHouseCost, terms)*100))/100;
         DecimalFormat df = new DecimalFormat("#.00");
  		 String fMort = df.format(monthlyMort);
         if((monthlyMort <= ((intIncome * 0.36)*100)/1200) && (monthlyMort <= ((((intIncome + intExpenses) * 0.18)*100)/1200))) {
        	//waitLabel.setVisible(false);
     		MortgagePaymentLabel.setText("Your monthly mortgage payments will be $" + fMort);
     		MortgagePaymentLabel.setVisible(true);
         }
         else {
        	 //waitLabel.setVisible(false);
        	 MortgagePaymentLabel.setText("Your monthly mortgage payments will be $" + fMort + 
        			 ",\nbut unfortunately you do not qualify for this loan.");
        	 MortgagePaymentLabel.setVisible(true);
         }
         }
         
    @FXML
    private void onPress() {
    	calculateMort();
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
   
}