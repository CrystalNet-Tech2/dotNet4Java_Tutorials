package Windows.Form;

import com.dotNet4Java.api.EClrError;
import system.EventArgs;
import system.EventHandler;
import system.drawing.Font;
import system.windows.forms.Button;
import system.windows.forms.HorizontalAlignment;
import system.windows.forms.TextBox;

public class CalculatorForm extends system.windows.forms.Form {

    private Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button0;
    private Button buttonAdd, buttonSum, buttonC;
    private TextBox tbDisplay;
    private Integer result = 0;
    private String operation = "";

    public CalculatorForm() throws Exception {
        super();
        InitializeComponents();
    }

    private void InitializeComponents() throws Exception {
        this.set_Text("dotNet4Java Calculator");
        this.set_Width(266);

        tbDisplay = new TextBox();
        tbDisplay.set_Width(250);
        tbDisplay.set_Height(100);
        tbDisplay.set_Text("0");
        tbDisplay.set_TextAlign(HorizontalAlignment.Right);
        Font font = new Font("Verdana", 24F);
        tbDisplay.set_Font(font);

        button1 = new Button();
        button1.set_Height(50);
        button1.set_Width(50);
        button1.set_Top(50);
        button1.set_Text("1");
        button1.add_Click(new EventHandler() {
            @Override
            public void invoke(Object o, EventArgs eventArgs) throws Exception {
                button1_Click(o, eventArgs);
            }
        });

        button2 = new Button();
        button2.set_Height(50);
        button2.set_Width(50);
        button2.set_Top(50);
        button2.set_Left(50);
        button2.set_Text("2");
        button2.add_Click(new EventHandler() {
            @Override
            public void invoke(Object o, EventArgs eventArgs) throws Exception {
                button2_Click(o, eventArgs);
            }
        });

        button3 = new Button();
        button3.set_Height(50);
        button3.set_Width(50);
        button3.set_Top(50);
        button3.set_Left(100);
        button3.set_Text("3");
        button3.add_Click(new EventHandler() {
            @Override
            public void invoke(Object o, EventArgs eventArgs) throws Exception {
                button3_Click(o, eventArgs);
            }
        });

        button4 = new Button();
        button4.set_Height(50);
        button4.set_Width(50);
        button4.set_Top(100);
        button4.set_Text("4");
        button4.add_Click(new EventHandler() {
            @Override
            public void invoke(Object o, EventArgs eventArgs) throws Exception {
                button4_Click(o, eventArgs);
            }
        });

        button5 = new Button();
        button5.set_Height(50);
        button5.set_Width(50);
        button5.set_Top(100);
        button5.set_Left(50);
        button5.set_Text("5");
        button5.add_Click(new EventHandler() {
            @Override
            public void invoke(Object o, EventArgs eventArgs) throws Exception {
                button5_Click(o, eventArgs);
            }
        });

        button6 = new Button();
        button6.set_Height(50);
        button6.set_Width(50);
        button6.set_Top(100);
        button6.set_Left(100);
        button6.set_Text("6");
        button6.add_Click(new EventHandler() {
            @Override
            public void invoke(Object o, EventArgs eventArgs) throws Exception {
                button5_Click(o, eventArgs);
            }
        });

        button7 = new Button();
        button7.set_Height(50);
        button7.set_Width(50);
        button7.set_Top(150);
        button7.set_Text("7");
        button7.add_Click(new EventHandler() {
            @Override
            public void invoke(Object o, EventArgs eventArgs) throws Exception {
                button7_Click(o, eventArgs);
            }
        });

        button8 = new Button();
        button8.set_Height(50);
        button8.set_Width(50);
        button8.set_Top(150);
        button8.set_Left(50);
        button8.set_Text("8");
        button8.add_Click(new EventHandler() {
            @Override
            public void invoke(Object o, EventArgs eventArgs) throws Exception {
                button8_Click(o, eventArgs);
            }
        });

        button9 = new Button();
        button9.set_Height(50);
        button9.set_Width(50);
        button9.set_Top(150);
        button9.set_Left(100);
        button9.set_Text("9");
        button9.add_Click(new EventHandler() {
            @Override
            public void invoke(Object o, EventArgs eventArgs) throws Exception {
                button9_Click(o, eventArgs);
            }
        });

        button0 = new Button();
        button0.set_Height(50);
        button0.set_Width(50);
        button0.set_Top(200);
        button0.set_Text("0");
        button0.add_Click(new EventHandler() {
            @Override
            public void invoke(Object o, EventArgs eventArgs) {
                button0_Click(o, eventArgs);
            }
        });

        buttonAdd = new Button();
        buttonAdd.set_Height(50);
        buttonAdd.set_Width(50);
        buttonAdd.set_Top(50);
        buttonAdd.set_Left(150);
        buttonAdd.set_Text("+");
        buttonAdd.add_Click(new EventHandler() {
            @Override
            public void invoke(Object o, EventArgs eventArgs) {
                try {
                    buttonAdd_Click(o, eventArgs);
                } catch (Exception ex) {
                }
            }
        });

        buttonC = new Button();
        buttonC.set_Height(50);
        buttonC.set_Width(50);
        buttonC.set_Top(100);
        buttonC.set_Left(150);
        buttonC.set_Text("C");
        buttonC.add_Click(new EventHandler() {
            @Override
            public void invoke(Object o, EventArgs eventArgs) {
                try {
                    buttonC_Click(o, eventArgs);
                } catch (Exception ex) {
                }
            }
        });

        buttonSum = new Button();
        buttonSum.set_Height(50);
        buttonSum.set_Width(50);
        buttonSum.set_Top(50);
        buttonSum.set_Left(200);
        buttonSum.set_Text("=");
        buttonSum.add_Click(new EventHandler() {
            @Override
            public void invoke(Object o, EventArgs eventArgs) {
                try {
                    buttonSum_Click(o, eventArgs);
                } catch (Exception ex) {
                }
            }
        });

        this.get_Controls().Add(button1);
        this.get_Controls().Add(button2);
        this.get_Controls().Add(button3);
        this.get_Controls().Add(button4);
        this.get_Controls().Add(button5);
        this.get_Controls().Add(button6);
        this.get_Controls().Add(button7);
        this.get_Controls().Add(button8);
        this.get_Controls().Add(button9);
        this.get_Controls().Add(button0);
        this.get_Controls().Add(buttonAdd);
        this.get_Controls().Add(buttonC);
        this.get_Controls().Add(buttonSum);
        this.get_Controls().Add(tbDisplay);
    }

    private void button1_Click(Object sender, EventArgs e) {
        NumberPressed(1);
    }

    private void button2_Click(Object sender, EventArgs e) {
        NumberPressed(2);
    }

    private void button3_Click(Object sender, EventArgs e) {
        NumberPressed(3);
    }

    private void button4_Click(Object sender, EventArgs e) {
        NumberPressed(4);
    }

    private void button5_Click(Object sender, EventArgs e) {
        NumberPressed(5);
    }

    private void button6_Click(Object sender, EventArgs e) {
        NumberPressed(6);
    }

    private void button7_Click(Object sender, EventArgs e) {
        NumberPressed(7);
    }

    private void button8_Click(Object sender, EventArgs e) {
        NumberPressed(8);
    }

    private void button9_Click(Object sender, EventArgs e) {
        NumberPressed(9);
    }

    private void button0_Click(Object sender, EventArgs e) {
        NumberPressed(0);
    }

    private void buttonAdd_Click(Object sender, EventArgs e) throws EClrError {
        int number = Integer.parseInt(tbDisplay.get_Text());
        result = number;
        operation = "+";
        tbDisplay.set_Text("+");
    }

    private void buttonC_Click(Object sender, EventArgs e) throws EClrError {
        tbDisplay.set_Text("0");
        result = 0;
        operation = "";
    }

    private void buttonSum_Click(Object sender, EventArgs e) throws EClrError {
        int number = Integer.parseInt(tbDisplay.get_Text());
        if (operation.equals("+"))
            result = result + number;
        tbDisplay.set_Text(result.toString());
    }

    private void NumberPressed(Integer number) {
        try {
            String current = tbDisplay.get_Text();

            if (current.equals(operation) || current.equals("0"))
                current = "";
            tbDisplay.set_Text(current + number.toString());
        } catch (EClrError e) {
            e.printStackTrace();
        }
    }
}
