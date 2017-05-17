package jm.provaksoap;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class MainActivity extends Activity implements View.OnClickListener {

    EditText ed_input;
    RadioGroup rg_temp;
    RadioButton radioTemp;
    Button btn_convert;
    String tempValue;
    ProgressDialog pdialog;
    SoapObject request;
    SoapPrimitive fahtocel, celtofah;

    String METHOD_NAME1 = "CelsiusToFahrenheit";
    String METHOD_NAME2 = "FahrenheitToCelsius";
    String SOAP_ACTION1 = "http://tempuri.org/CelsiusToFahrenheit";
    String SOAP_ACTION2 = "http://tempuri.org/FahrenheitToCelsius";

    String NAMESPACE = "https://www.w3schools.com/xml/";
    String SOAP_URL = "https://www.w3schools.com/xml/tempconvert.asmx";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed_input = (EditText) findViewById(R.id.ed_input);
        rg_temp = (RadioGroup) findViewById(R.id.rg_temp);
        btn_convert = (Button) findViewById(R.id.btn_convert);
        btn_convert.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {


        int temp = rg_temp.getCheckedRadioButtonId();
        radioTemp = (RadioButton) findViewById(temp);
        tempValue = ed_input.getText().toString();
        String tempchoose = radioTemp.getText().toString();

        switch (v.getId()) {
            case R.id.btn_convert:


                if (isEmpty(ed_input)) {
                    Toast.makeText(getApplicationContext(), "Enter degress to convert!", Toast.LENGTH_SHORT).show();
                } else if (tempchoose.equals("Celsius")) {

                    // invoking the CelsiusAsync AsyncTask
                    CelsiusAsync celsiustofahr = new CelsiusAsync();
                    celsiustofahr.execute();
                } else if (tempchoose.equals("Fahrenheit")) {

                    // invoking the FahrenheitAsync AsyncTask
                    FahrenheitAsync fahrtocelsius = new FahrenheitAsync();
                    fahrtocelsius.execute();
                }


                break;

            default:
                Toast.makeText(getApplicationContext(), "Unable to convert", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public Boolean isEmpty(EditText ed_input) {

        String noValue = ed_input.getText().toString().trim();
        if (noValue.length() == 0) {
            return true;
        } else {
            return false;
        }
    }


    private class CelsiusAsync extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            request = new SoapObject(NAMESPACE, METHOD_NAME1);
            request.addProperty("Celsius", tempValue);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_URL);
            try {
                httpTransport.call(SOAP_ACTION1, envelope);
                fahtocel = (SoapPrimitive) envelope.getResponse();
                Log.wtf("ang fahrenheit", fahtocel.toString());
            } catch (Exception e) {
                e.getMessage();
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pdialog.dismiss();
            Toast.makeText(getApplicationContext(), fahtocel.toString() + " Fahrenheit", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pdialog = new ProgressDialog(MainActivity.this);
            pdialog.setMessage("Converting...");
            pdialog.show();
        }
    }

    private class FahrenheitAsync extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            request = new SoapObject(NAMESPACE, METHOD_NAME2);
            request.addProperty("Fahrenheit", tempValue);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_URL);
            try {
                httpTransport.call(SOAP_ACTION2, envelope);
                celtofah = (SoapPrimitive) envelope.getResponse();

                Log.wtf("ang celsius", celtofah.toString());
            } catch (Exception e) {
                e.getMessage();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pdialog.dismiss();
            Toast.makeText(getApplicationContext(), celtofah.toString() + " Celsius", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pdialog = new ProgressDialog(MainActivity.this);
            pdialog.setMessage("Converting...");
            pdialog.show();
        }
    }
}