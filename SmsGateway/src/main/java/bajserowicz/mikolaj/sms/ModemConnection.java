package bajserowicz.mikolaj.sms;

import jssc.SerialPort;
import jssc.SerialPortException;
import org.springframework.stereotype.Component;

@Component
public class ModemConnection {

    byte newLine = 0x0D; //end of the line
    byte endOfLine = 0x1A;


    SerialPort serialPort;


    public ModemConnection() throws SerialPortException, InterruptedException {
        this.serialPort = new SerialPort(("COM3"));
        serialPort.openPort();//port where is connected my routher
        serialPort.setParams(  //command +p for check params
                SerialPort.BAUDRATE_9600, // speed of connection (same as is in puty program
                SerialPort.DATABITS_8,
                SerialPort.STOPBITS_1,
                SerialPort.PARITY_NONE);

    }

    public void sendSms(String telNumber, String message) {
        try {
            serialPort.writeString("AT+CMGF+1"); //set work mood one
            serialPort.writeByte(newLine);
            Thread.sleep(1000);

            serialPort.writeString("AT+CMGS=\"" + telNumber + "\"");
            serialPort.writeByte(newLine);
            Thread.sleep(1000);

            serialPort.writeString(message);
            serialPort.writeByte(newLine);
            Thread.sleep(1000);

            serialPort.writeByte(endOfLine);


        } catch (SerialPortException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
