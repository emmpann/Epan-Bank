package efan.controller;

import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import efan.model.Account;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class ValidAccountController implements Initializable {
    @FXML
    private Label balanceLabel;

    @FXML
    private Label bankAccountName;

    @FXML
    private Label bankAccountNumber;

    Account myAccount = Account.getAccountInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Balance
        String balance = String.valueOf(myAccount.getBalance());

        // Account Number
        String accountNumber = myAccount.getAccountNumber();
        String accountNumberFormatted = "";

        for (String i: accountNumber.split("(?<=\\G.{" + 4 + "})")) {
            accountNumberFormatted += i + " ";
        }

        bankAccountName.setText(myAccount.getFullName());

        balanceLabel.setText("Rp " + balance);

        bankAccountNumber.setText(accountNumberFormatted);
        
    }
}