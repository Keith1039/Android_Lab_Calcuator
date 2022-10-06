package com.example.simplecalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigDecimal;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button button0,button1,button2,button3,button4,button5,button6,button7,button8,button9,button_plus,button_minus,button_divide,button_multiply,button_equal,button_dot,button_clear;
    TextView text_display;

    //this is to evaluate the math expression
    ScriptEngine engine ;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        engine = (ScriptEngine) new ScriptEngineManager().getEngineByName("rhino");


        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        button_plus = (Button) findViewById(R.id.button_plus);
        button_minus = (Button) findViewById(R.id.button_minus);
        button_divide = (Button) findViewById(R.id.button_divide);
        button_multiply = (Button) findViewById(R.id.button_multiply);
        button_equal = (Button) findViewById(R.id.button_equal);
        button_dot = (Button) findViewById(R.id.button_dot);
        button_clear = (Button) findViewById(R.id.button_clear);
        text_display = (TextView) findViewById(R.id.text_dispaly);
        setClickListners ();
    }
    private void setClickListners (){
        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        button_multiply.setOnClickListener(this);
        button_divide.setOnClickListener(this);
        button_plus.setOnClickListener(this);
        button_minus.setOnClickListener(this);
        button_dot.setOnClickListener(this);
        button_clear.setOnClickListener(this);
        button_equal.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button0:
                addNumber("0");
                break;
            case R.id.button1:
                addNumber("1");
                break;
            case R.id.button2:
                addNumber("2");
                break;
            case R.id.button3:
                addNumber("3");
                break;
            case R.id.button4:
                addNumber("4");
                break;
            case R.id.button5:
                addNumber("5");
                break;
            case R.id.button6:
                addNumber("6");
                break;
            case R.id.button7:
                addNumber("7");
                break;
            case R.id.button8:
                addNumber("8");
                break;
            case R.id.button9:
                addNumber("9");
                break;
            case R.id.button_divide:
                addNumber("/");
                break;
            case R.id.button_multiply:
                addNumber("*");
                break;
            case R.id.button_plus:
                addNumber("+");
                break;
            case R.id.button_minus:
                addNumber("-");
                break;
            case R.id.button_dot:
                addNumber(".");
                break;
            case R.id.button_equal:
                String result = null;
                try {
                    result = evaluate(text_display.getText().toString());
                }catch (ScriptException e){
                    text_display.setText("Error");
                }
                text_display.setText(result);
                break;
            case R.id.button_clear:
                clear_display();
                break;

        }
    }

    private String evaluate (String expression) throws ScriptException {
        String result = engine.eval(expression).toString();
        BigDecimal decimal =new BigDecimal(result);
        return decimal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();

    }

    private void addNumber (String s){
        text_display.setText(text_display.getText()+s);

    }
    private void  clear_display (){
        text_display.setText("");
    }
}