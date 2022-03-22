package efan.controller;

import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import efan.model.Account;
import efan.util.CurrencyNumber;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Account Number
        String accountNumber = Account.getAccountInstance().getAccountNumber();
        String accountNumberFormatted = "";

        for (String i: accountNumber.split("(?<=\\G.{" + 4 + "})")) {
            accountNumberFormatted += i + " ";
        }

        bankAccountName.setText(Account.getAccountInstance().getFullName());

        balanceLabel.setText(CurrencyNumber.currencyFormat(Account.getAccountInstance().getBalance()));

        bankAccountNumber.setText(accountNumberFormatted);
        
    }
}