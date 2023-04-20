package bajserowicz.mikolaj.sms;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;



@Route
public class SmsGui extends VerticalLayout {

    private TextField textFieldNumber;
    private TextField textFieldMessage;
    private Button buttonSendSms;
    private ModemConnection modemConnection;

    public SmsGui(ModemConnection modemConnection){
        this.modemConnection = modemConnection;
        textFieldNumber = new TextField();
        textFieldMessage = new TextField();
        buttonSendSms = new Button("send SMS");
        add(textFieldNumber,textFieldMessage,buttonSendSms);

        buttonSendSms.addClickListener(buttonClickEvent -> modemConnection.sendSms(textFieldNumber.getValue(),textFieldMessage.getValue()));

    }
}
