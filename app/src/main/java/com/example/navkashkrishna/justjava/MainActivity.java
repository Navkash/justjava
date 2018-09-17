package com.example.navkashkrishna.justjava;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.*;


/**

 This app displays an order form to order coffee.
 */
public class MainActivity extends Activity {
    int qunatity=0;
    boolean hascream;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void increment(View view){
        if(qunatity==100)
        {
            Toast.makeText(this,"You cannot order more than 100 cup of coffee",Toast.LENGTH_SHORT).show();
            return;
        }
        qunatity=qunatity+1;
        display(qunatity);
    }
    public void decrement(View view){
        if(qunatity==1)
        {
            Toast.makeText(this,"You cannot order less than 1 cup of coffee",Toast.LENGTH_SHORT).show();
            return;
        }
        qunatity=qunatity-1;
        display(qunatity);
    }
    public void onCheckboxClicked (View view) {
        CheckBox wcream = (CheckBox) findViewById(R.id.chackbox);
        hascream = wcream.isChecked();
    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText name=(EditText) findViewById(R.id.namefield);
        String customername=name.getText().toString();
        int price =getPrice(hascream,qunatity);
        String pricemsg= ordersummary(price,customername,qunatity,hascream);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Order for :\t" + customername);
        intent.putExtra(Intent.EXTRA_TEXT,pricemsg);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        displaymessage(pricemsg);
    }
    private String ordersummary(int price ,String name,int number, boolean cream){
        String pricemssg= "Customer name :\t" + name +"\nClient Name : Navkash"+ "\nwhipped cream :\t" + cream + "\nQuantity :" + number + "\nYour total : Rs." + price +"\nThank You!";
        return pricemssg;
    }
    private int getPrice(boolean cream,int quantity)
    {
        int base=10;
        if(cream==true)
            base=base+3;
        return base*quantity;

    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quan);
        quantityTextView.setText("" + number);
    }
    private void displaymessage(String message){
        TextView priceTextView=(TextView) findViewById(R.id.pay);
        priceTextView.setText(message);
    }
}