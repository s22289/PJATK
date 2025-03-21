package GUI.style;


import OnlineBookLibary.Entities.Enums.OrderStatus;
import OnlineBookLibary.UserManager.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class PaymentViewController extends BooksViewController {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button buttonPay;

    @FXML
    private TextField textFieldCodeCVC;

    @FXML
    private TextField textFieldCreditCardNumber;

    @FXML
    private TextField textFieldExpirationDate;

    @FXML
    private Label labelValidation1;

    @FXML
    private Label labelValidation2;

    @FXML
    private Label labelValidation3;

    private Customer customer; // Assuming you have a way to set this in your application

    /**
     * This method handles button "Pay"
     * The method checks if text fields are filled with correct data and changes scene to "Payment Confirmation"
     *
     * @param actionEvent
     * @throws Exception
*/
    public void buttonPayOnAction(ActionEvent actionEvent) throws Exception {
        if (actionEvent.getSource().equals(buttonPay)) {
            boolean isValid = true; // Flag to check overall validation

            // Validate card number
            if (textFieldCreditCardNumber.getText().length() == 16) {
                labelValidation1.setVisible(true);
                labelValidation1.setText("Your card number is correct.");
                labelValidation1.setStyle("-fx-text-fill: green;"); // Set text color to green
            } else {
                labelValidation1.setVisible(true);
                labelValidation1.setText("Card number must have 16 digits");
                labelValidation1.setStyle("-fx-text-fill: red;"); // Set text color to red
                isValid = false;
            }

            // Validate CVC code
            if (textFieldCodeCVC.getText().length() == 3) {
                labelValidation2.setVisible(true);
                labelValidation2.setText("Your CVC code is correct.");
                labelValidation2.setStyle("-fx-text-fill: green;"); // Set text color to green
            } else {
                labelValidation2.setVisible(true);
                labelValidation2.setText("CVC code must have 3 digits");
                labelValidation2.setStyle("-fx-text-fill: red;"); // Set text color to red
                isValid = false;
            }

            // Validate expiration date
            if (textFieldExpirationDate.getText().length() == 5) {
                labelValidation3.setVisible(true);
                labelValidation3.setText("Your expiration date is correct.");
                labelValidation3.setStyle("-fx-text-fill: green;"); // Set text color to green
            } else {
                labelValidation3.setVisible(true);
                labelValidation3.setText("Expiration date must have 5 digits");
                labelValidation3.setStyle("-fx-text-fill: red;"); // Set text color to red
                isValid = false;
            }

            // If all fields are valid, proceed with payment
            if (isValid) {
                Main.order.setOrderStatus(OrderStatus.PAID);
                fadeOut("fxml/paymentConfirmation.fxml", rootPane);
                System.out.println("Payment confirmed");
            }
        }
    }

    /**
     * This method creates payment object
     */
    public void createPayment() {
        // Create payment object logic
    }

    /**
     * This method is called when the scene is initialized
     */
    @FXML
    public void initialize() throws Exception {
        fadeIn(rootPane); // animation, which is played when scene loads

        // Fill text fields with data from customer's credit card, if available
        if (customer != null && customer.hasCreditCard()) {
            textFieldCreditCardNumber.setText(customer.getCreditCardNumber());
            textFieldCodeCVC.setText(Integer.toString(customer.getCreditCardCodeCVC()));
            textFieldExpirationDate.setText(customer.getCreditCardExpirationDate());
        }

        // Hide validation labels initially
        labelValidation1.setVisible(false);
        labelValidation2.setVisible(false);
        labelValidation3.setVisible(false);
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
