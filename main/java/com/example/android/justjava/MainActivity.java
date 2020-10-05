package com.example.android.justjava;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



/**
 * This app displays an order form to order coffee.
 */

public class MainActivity extends AppCompatActivity {

    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the + button is clicked.
     */
    public void increment(View view) {
        if (quantity == 100) {
            //error message
            Toast.makeText(this, "100 exceeds the quantity allowed.", Toast.LENGTH_SHORT).show();
            return;
        }

        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the - button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 1) {
            //error message
            Toast.makeText(this, "You cannot order less than 1 coffee.", Toast.LENGTH_SHORT).show();
            return;
        }

        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText nameField = (EditText) findViewById(R.id.name_field);
        String name = nameField.getText().toString();

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipping_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean addChocolate = chocolateCheckBox.isChecked();

        int price = calculatePrice(hasWhippedCream, addChocolate);
        String priceMessage = createOrderSummary(name, price, hasWhippedCream, addChocolate);
        displayMessage(priceMessage);
    }

    /**
     * Create an order summary that returns the quantity, total and message after
     * Order button is selected.
     * @param name of the customer
     * @param price of order
     * @param hasWhippedCream whether whipped cream box is checked
     * @param addChocolate whether chocolate checkbox is checked
     * @return String with quantity, total and message
     */
    private String createOrderSummary(String name, int price, boolean hasWhippedCream, boolean addChocolate) {
        String priceMessage = "Name: " + name;
        priceMessage +="\nAdd Whipped Cream? " + hasWhippedCream;
        priceMessage += "\nAdd Chocolate? " + addChocolate;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage +=  "\nTotal: $" + calculatePrice(hasWhippedCream, addChocolate) + "\n" + "Thank you!";
        return priceMessage;
    }

    /**
     * Calculates the price of the order.
     *
     * @param hasWhippedCream calculated if whipped cream box is checked
     * @param addChocolate calculated if chocolate checkbox is checked
     * @return total price
     */
    private int calculatePrice(boolean hasWhippedCream, boolean addChocolate) {
        int basePrice = 5;

        if (hasWhippedCream) {
            basePrice = basePrice + 1;
        }

        if (addChocolate) {
            basePrice = basePrice + 2;
        }

        return quantity * basePrice;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
        //quantityTextView.setTextSize(56);
        //quantityTextView.setTextColor(Color.BLUE);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * Resets quantity and -- values.
     */
    public void resetValues(View v) {
        int quantity = 1;
        String message = "$0";
        displayQuantity(quantity);
        displayMessage(message);
    }


}