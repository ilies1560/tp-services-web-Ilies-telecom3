package models;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Calculator {
    private int a;
    private int b;
    private int result;

    public Calculator() {
    }

    public Calculator(int a, int b, int result) {
        this.a = a;
        this.b = b;
        this.result = result;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public Calculator add(int chiffre1, int chiffre2) {
        return new Calculator(chiffre1, chiffre2, chiffre1 + chiffre2);
    }

    public Calculator multiply(int chiffre1, int chiffre2) {
        return new Calculator(chiffre1, chiffre2, chiffre1 * chiffre2);
    }

    public Calculator divise(int chiffre1, int chiffre2) {
        return new Calculator(chiffre1, chiffre2, chiffre1 / chiffre2);
    }
}
