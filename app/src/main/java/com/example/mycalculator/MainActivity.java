package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn0,btn1, btn2, btn3, btn4, btn5,btn6, btn7,btn8,btn9, btn_dot, btn_clear, btn_plus, btn_minus, btn_multi, btn_divide, btn_equal;
    TextView text_display;
    Boolean flag = true;

    // This is to evaluate the math expression
    ScriptEngine engine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        engine = new ScriptEngineManager().getEngineByName("rhino");
        btn0 = (Button) findViewById(R.id.btn0);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        btn_plus = (Button) findViewById(R.id.btn_plus);
        btn_minus = (Button) findViewById(R.id.btn_minus);
        btn_multi = (Button) findViewById(R.id.btn_multi);
        btn_divide = (Button) findViewById(R.id.btn_divide);
        btn_equal = (Button) findViewById(R.id.btn_equal);
        btn_clear = (Button) findViewById(R.id.btn_clear);
        btn_dot = (Button)findViewById(R.id.btn_dot);
        text_display = (TextView) findViewById(R.id.text_display);

        setClickListeners();
    }

    private void setClickListeners() {
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn_dot.setOnClickListener(this);
        btn_plus.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_multi.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_equal.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn1:
                addNumber("1");
                flag = false;
                break;
            case R.id.btn2:
                addNumber("2");
                flag = false;
                break;
            case R.id.btn3:
                addNumber("3");
                flag = false;
                break;
            case R.id.btn4:
                addNumber("4");
                flag = false;
                break;
            case R.id.btn5:
                addNumber("5");
                flag = false;
                break;
            case R.id.btn6:
                addNumber("6");
                flag = false;
                break;
            case R.id.btn7:
                addNumber("7");
                flag = false;
                break;
            case R.id.btn8:
                addNumber("8");
                flag = false;
                break;
            case R.id.btn9:
                addNumber("9");
                flag = false;
                break;
            case R.id.btn0:
                addNumber("0");
                flag = false;
                break;
            case R.id.btn_dot:
                addNumber(".");
                break;
            case R.id.btn_plus :
                if(flag == false) {
                    addNumber("+");
                    flag = true;
                }
                break;
            case R.id.btn_minus:
                if(flag == false) {
                    addNumber("-");
                    flag = true;
                }
                break;
            case R.id.btn_multi:
                if(flag == false) {
                    addNumber("*");
                    flag = true;
                }
                break;
            case R.id.btn_divide:
                if(flag == false) {
                    addNumber("/");
                    flag = true;
                }
                break;

            case R.id.btn_equal:
                String result = null;
                flag = false;
                try {
                    result = evaluate(text_display.getText().toString());
                    Double val1 = Double.parseDouble(result);
                    Integer val2 = val1.intValue();
                    if(val1 == val2.doubleValue()){
                        result = val2.toString();
                    }
                    text_display.setText(result);
                } catch (ScriptException e) {
                    text_display.setText("Error");
                } catch(NumberFormatException e){
                    text_display.setText("Error"); 
                }

                break;
            case R.id.btn_clear:
                flag = false;
                clear_display();
                break;
        }
    }

    private String evaluate(String expression) throws ScriptException {
        String result = engine.eval(expression).toString();
        BigDecimal decimal = new BigDecimal(result);
        return decimal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
    }

    private void addNumber(String number) {
        text_display.setText(text_display.getText() + number);
    }

    private void clear_display() {
        text_display.setText("");
    }
}
