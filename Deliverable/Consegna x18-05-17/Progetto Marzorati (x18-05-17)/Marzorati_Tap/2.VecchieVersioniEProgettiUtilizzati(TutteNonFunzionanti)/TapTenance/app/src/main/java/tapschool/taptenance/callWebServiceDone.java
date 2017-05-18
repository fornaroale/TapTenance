package tapschool.taptenance;

import android.os.AsyncTask;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;


import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

/**
 * Created by Marzorati Stefano on 17/05/2017.
 */

public class callWebServiceDone extends AsyncTask<String, String, String> {

    // URL corrispondente al web service
    public final static String URL = "http://10.0.2.2:8080/NB_WSApplicationTestDB/WSApplicationTestDB";

    // target name space
    public final static String NAMESPACE = "http://marzorati.org/";

    // nome dell'operazione
    public final static String METHOD_NAME_VIEWDONE = "getList";

    // nome dei parametri
    //private static final String PARAMETER_NAME_LOGIN1 = "username";
    //private static final String PARAMETER_NAME_LOGIN2 = "password";

    // valore per il parametro
    //private static final String PARAMETER_VALUE_LOGIN1 = "";

    // variabile per memorizzazione id utente
    private static ArrayList<String> arrayList;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Things to be done before execution of long running operation. For
        // example showing ProgessDialog

        // creazione integer per memorizzare il valore ricevuto dal web service
        arrayList=new ArrayList<String>();
    }


    @Override
    protected String doInBackground(String... params) {
        String SOAP_ACTION = NAMESPACE + METHOD_NAME_VIEWDONE;

        SoapObject soapObject = new SoapObject(NAMESPACE, METHOD_NAME_VIEWDONE);

        //soapObject.addProperty(PARAMETER_NAME_LOGIN1, params[1]);   // parametro username
        //soapObject.addProperty(PARAMETER_NAME_LOGIN2, params[2]);   // parametro password

        Log.i("com.example.samplews", "Request Value -> " + soapObject.toString());
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soapObject);

        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);

        // http://stackoverflow.com/questions/11029205/ksoap2-android-receive-array-of-objects
        String str = "";
        try {
            httpTransportSE.call(SOAP_ACTION, envelope);
            SoapObject primitive = (SoapObject) envelope.bodyIn;
            for (int i = 0; i < primitive.getPropertyCount(); i++) {
                str = (String) primitive.getProperty(i);
                Log.i("com.example.samplews", "ForLoop -------------- " + str);
                arrayList.add(str);
            }
            return str;    // il WS ha risposto e il return viene mandato a onPostExecute

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;    // qualcosa è andato male
    }


    // http://stackoverflow.com/questions/12575068/how-to-get-the-result-of-onpostexecute-to-main-activity-because-asynctask-is-a

    @Override
    protected void onPostExecute(String esito) {
        super.onPostExecute(esito);

        // richiamato al termine della chiamata al web service
        // il parametro esito è il valore ritornato da doInBackground()

    }
}
