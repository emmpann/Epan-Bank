package efan.util;

import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyNumber {
    
    public static String currencyFormat(double number) {
        
        var indonesia = new Locale("in", "ID");
        var numberFormat = NumberFormat.getCurrencyInstance(indonesia);
        
        return numberFormat.format(number);
    }

}
