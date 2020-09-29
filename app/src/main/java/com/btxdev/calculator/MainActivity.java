package com.btxdev.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine, btnZero
            , btnEqual, btnSum, btnSubt, btnMult, btnDiv, btnRadic, btnPlusMinus, btnDot, btnBackspace, btnClear;

    private EditText edtScreen;

    private Pattern operationPattern = Pattern.compile("(\\-?[0-9]+(\\.[0-9]*)?(E[0-9]+)?)(([\\+\\−\\×\\÷])(\\-?[0-9]+(\\.[0-9]*)?(E[0-9]+)?))*");
    private Pattern multDivPattern = Pattern.compile("(\\-?[0-9]+(\\.[0-9]*)?(E[0-9]+)?)([\\×\\÷])(\\-?[0-9]+(\\.[0-9]*)?(E[0-9]+)?)");
    private Pattern sumSubtPattern = Pattern.compile("(\\-?[0-9]+(\\.[0-9]*)?(E[0-9]+)?)([\\+\\−])(\\-?[0-9]+(\\.[0-9]*)?(E[0-9]+)?)");
    private Pattern plusMinusPattern = Pattern.compile("(\\-)?([0-9]+(\\.[0-9]*)?(E[0-9]+)?)$");
    private Pattern radicPattern = Pattern.compile("([0-9]+(\\.[0-9]*)?(E[0-9]+)?)$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOne = findViewById(R.id.btnOne);
        btnTwo = findViewById(R.id.btnTwo);
        btnThree = findViewById(R.id.btnThree);
        btnFour = findViewById(R.id.btnFour);
        btnFive = findViewById(R.id.btnFive);
        btnSix = findViewById(R.id.btnSix);
        btnSeven = findViewById(R.id.btnSeven);
        btnEight = findViewById(R.id.btnEight);
        btnNine = findViewById(R.id.btnNine);
        btnZero = findViewById(R.id.btnZero);
        btnDot = findViewById(R.id.btnDot);
        btnSum = findViewById(R.id.btnSum);
        btnSubt = findViewById(R.id.btnSubt);
        btnMult = findViewById(R.id.btnMult);
        btnDiv = findViewById(R.id.btnDiv);
        btnRadic = findViewById(R.id.btnRadic);
        btnPlusMinus = findViewById(R.id.btnPlusMinus);
        btnBackspace = findViewById(R.id.btnBackspace);
        btnClear = findViewById(R.id.btnClear);
        btnEqual = findViewById(R.id.btnEqual);

        edtScreen = findViewById(R.id.edtScreen);

        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnThree.setOnClickListener(this);
        btnFour.setOnClickListener(this);
        btnFive.setOnClickListener(this);
        btnSix.setOnClickListener(this);
        btnSeven.setOnClickListener(this);
        btnEight.setOnClickListener(this);
        btnNine.setOnClickListener(this);
        btnZero.setOnClickListener(this);
        btnDot.setOnClickListener(this);
        btnSum.setOnClickListener(this);
        btnSubt.setOnClickListener(this);
        btnMult.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
        btnRadic.setOnClickListener(this);
        btnPlusMinus.setOnClickListener(this);
        btnBackspace.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnEqual.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnOne:
                edtScreen.append("1");
                break;
            case R.id.btnTwo:
                edtScreen.append("2");
                break;
            case R.id.btnThree:
                edtScreen.append("3");
                break;
            case R.id.btnFour:
                edtScreen.append("4");
                break;
            case R.id.btnFive:
                edtScreen.append("5");
                break;
            case R.id.btnSix:
                edtScreen.append("6");
                break;
            case R.id.btnSeven:
                edtScreen.append("7");
                break;
            case R.id.btnEight:
                edtScreen.append("8");
                break;
            case R.id.btnNine:
                edtScreen.append("9");
                break;
            case R.id.btnZero:
                edtScreen.append("0");
                break;
            case R.id.btnDot:
                edtScreen.append(getString(R.string.dot_sym));
                break;
            case R.id.btnSum:
                if(isOperation(getLastCharacter())){
                    removeLastCharacter();
                }
                edtScreen.append(getString(R.string.sum_sym));
                break;
            case R.id.btnSubt:
                if(isOperation(getLastCharacter())){
                    removeLastCharacter();
                }
                edtScreen.append(getString(R.string.subt_sym));
                break;
            case R.id.btnMult:
                if(isOperation(getLastCharacter())){
                    removeLastCharacter();
                }
                edtScreen.append(getString(R.string.mult_sym));
                break;
            case R.id.btnDiv:
                if(isOperation(getLastCharacter())){
                    removeLastCharacter();
                }
                edtScreen.append(getString(R.string.div_sym));
                break;
            case R.id.btnRadic:
                radic();
                break;
            case R.id.btnPlusMinus:
                plusMinusSwitch();
                break;
            case R.id.btnBackspace:
                removeLastCharacter();
                break;
            case R.id.btnClear:
                edtScreen.setText("");
                break;
            case R.id.btnEqual:
                try {
                    equal();
                } catch (Exception e) {
                    Log.e("Calc",Log.getStackTraceString(e));
                    if(e.getMessage()==null||e.getMessage().length()==0){
                        Toast.makeText(getApplicationContext(),Log.getStackTraceString(e),Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
                break;


        }


    }

    private void radic(){
        Matcher matcher = radicPattern.matcher(edtScreen.getText());
        if(matcher.matches()){
            double value = Double.parseDouble(matcher.group(1));
            edtScreen.setText(getStringResult(Math.sqrt(value)));
        }
    }

    private void removeLastCharacter(){
        int length = edtScreen.getText().length();
        if (length > 0) {
            edtScreen.getText().delete(length - 1, length);
        }
    }

    private String getLastCharacter(){
        CharSequence s = edtScreen.getText();
        if(s.length()>0){
            return s.subSequence(s.length()-1, s.length()).toString();
        }
        return null;
    }

    private boolean isOperation(String characterString){
        if(getString(R.string.sum_sym).equals(characterString)||getString(R.string.subt_sym).equals(characterString)
                ||getString(R.string.mult_sym).equals(characterString)||getString(R.string.div_sym).equals(characterString)){
            return true;
        }
        return false;
    }

    private void plusMinusSwitch(){
        Matcher matcher = plusMinusPattern.matcher(edtScreen.getText());
        if(matcher.find()){
            String value = matcher.group(2);
            String symbol = matcher.group(1);
            if(symbol==null){
                edtScreen.getText().replace(matcher.start(),matcher.end(),getString(R.string.minus_sym)+value);
            }else{
                edtScreen.getText().replace(matcher.start(),matcher.end(),value);
            }
        }
    }

    private void equal() throws Exception {
        Matcher matcher = operationPattern.matcher(edtScreen.getText());
        if(matcher.matches()){

            StringBuilder operationText = new StringBuilder();
            operationText.append(edtScreen.getText());

            Matcher matcherMultDiv = multDivPattern.matcher(operationText);
            while(matcherMultDiv.find()){
                double value1 = Double.parseDouble(matcherMultDiv.group(1));
                double value2 = Double.parseDouble(matcherMultDiv.group(5));
                String operation = matcherMultDiv.group(4);

                double result;

                if(operation.equals(getString(R.string.mult_sym))){
                    result = value1*value2;
                }else{
                    if(value2==0){
                        throw new Exception(getString(R.string.err_divide_by_zero));
                    }
                    result = value1/value2;
                }

                operationText.replace(matcherMultDiv.start(),matcherMultDiv.end(),getStringResult(result));
                matcherMultDiv = multDivPattern.matcher(operationText);
            }

            Matcher matcherSumSubt = sumSubtPattern.matcher(operationText);
            while(matcherSumSubt.find()){
                double value1 = Double.parseDouble(matcherSumSubt.group(1));
                double value2 = Double.parseDouble(matcherSumSubt.group(5));
                String operation = matcherSumSubt.group(4);

                double result;

                if(operation.equals(getString(R.string.sum_sym))){
                    result = value1+value2;
                }else{
                    result = value1-value2;
                }

                operationText.replace(matcherSumSubt.start(),matcherSumSubt.end(),getStringResult(result));
                matcherSumSubt = sumSubtPattern.matcher(operationText);
            }

            edtScreen.setText(operationText);
        }
    }

    private String getStringResult(double result){
        if(isInteger(result)){
            return Double.toString(result).replace(".0","");
        }else{
            return Double.toString(result);
        }
    }

    private boolean isInteger(double number){
        return number == Math.rint(number);
    }

}