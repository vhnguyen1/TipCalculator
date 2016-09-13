package edu.orangecoastcollege.vnguyen629cs273.tipcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * Controller for Tip Calculator
 * @author Vincent Nguyen
 */
public class MainActivity extends AppCompatActivity {

    //import java.text.NumberFormat;
    //private static NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.US);
    private static NumberFormat currency = NumberFormat.getCurrencyInstance();
    private static NumberFormat percent = NumberFormat.getPercentInstance();

    // Associate the controller with the needed views
    private EditText amountEditText;
    private TextView amountTextView;
    private TextView tipTextView;
    private TextView tipAmountTextView;
    private TextView totalAmountTextView;
    private SeekBar tipSeekBar;

    // Associate the controller with the needed model
    RestaurantBill currentBill = new RestaurantBill();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connect the controller with the widgets in our app
        amountEditText = (EditText) findViewById(R.id.amountEditText);
        amountTextView = (TextView) findViewById(R.id.amountTextView);
        tipTextView = (TextView) findViewById(R.id.tipPercentTextView);
        tipAmountTextView = (TextView) findViewById(R.id.tipAmountTextView);
        totalAmountTextView = (TextView) findViewById(R.id.totalAmountTextView);
        tipSeekBar = (SeekBar) findViewById(R.id.tipPercentSeekBar);

        // Define a listener for the amountEditText (onTextChange)
        // Adds a listener to listen for text changes, a variable that handles an event
        amountEditText.addTextChangedListener(amountTextChangedListener);

        //Define a listener for the tipSeekBar
        tipSeekBar.setOnSeekBarChangeListener(percentChangedListener);
    }

    private TextWatcher amountTextChangedListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            // Do nothing
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            // Try to get the amount from amountEditText:
            try {
                double amount;
                if(charSequence.length() > 0) {
                    amount = Double.parseDouble(charSequence.toString()) / 100.00;
                    currentBill.setAmount(amount);
                }
                else {
                    amount = 0.00;
                    currentBill.setAmount(amount);
                }
            }
            catch (NumberFormatException err) {
                amountEditText.setText("");
            }

            // No exception, input is valid:
            // Set the bill amount (amountTextView)
            amountTextView.setText(currency.format(currentBill.getAmount()));
            updateViews();
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // Do nothing
        }
    };

    private SeekBar.OnSeekBarChangeListener percentChangedListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            // Update the model with a new tip amount
            currentBill.setTipPercent(i / 100.00);

            // Update the percentTextView
            tipTextView.setText(percent.format(currentBill.getTipPercent()));

            // Update the views
            updateViews();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // Do nothing
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // Do nothing
        }
    };

    private void updateViews() {
        // 1.) Set the tip amount (tipTextView)
        tipAmountTextView.setText(currency.format(currentBill.getTipAmount()));
        // 2.) Set the total amount (totalAmountTextView)
        totalAmountTextView.setText(currency.format(currentBill.getTotalAmount()));
    }
}
