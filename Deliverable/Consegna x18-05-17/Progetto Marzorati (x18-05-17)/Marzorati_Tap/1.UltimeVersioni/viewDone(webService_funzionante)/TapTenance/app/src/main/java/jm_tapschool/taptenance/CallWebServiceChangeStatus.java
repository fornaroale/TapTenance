package jm_tapschool.taptenance;

/**
 * Created by Marzorati Stefano on 18/05/2017.
 */
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;


public class CallWebServiceChangeStatus extends AsyncTask<String, String, String> {
    public final static String URL = "http://10.0.2.2:8080/viewDoneWS/modificaStato";

    // target name space
    public final static String NAMESPACE = "http://marzorati.org/";

    // nome dell'operazione
    public final static String METHOD_NAME_CHANGESTATUS = "changeStatus";

    // nome dei parametri
    private static final String PARAMETER_NAME_REG1 = "id";
    private static final String PARAMETER_NAME_REG2 = "stato";


    // valore per il parametro
    private static final String PARAMETER_VALUE_LOGIN1 = "";

    // variabile per memorizzazione id utente
    private static boolean fatto;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Things to be done before execution of long running operation. For
        // example showing ProgessDialog


        fatto = false;
    }


    @Override
    protected String doInBackground(String... params) {
        String SOAP_ACTION = NAMESPACE + METHOD_NAME_CHANGESTATUS;

        SoapObject soapObject = new SoapObject(NAMESPACE, METHOD_NAME_CHANGESTATUS);

        soapObject.addProperty(PARAMETER_NAME_REG1, params[1]);   // parametro username
        soapObject.addProperty(PARAMETER_NAME_REG2, params[2]);   // parametro password

        Log.i("com.example.samplews", "Request Value -> " + soapObject.toString());
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soapObject);

        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);

        // http://stackoverflow.com/questions/11029205/ksoap2-android-receive-array-of-objects
        String strFatto = "";
        try {
            httpTransportSE.call(SOAP_ACTION, envelope);
            SoapObject primitive = (SoapObject) envelope.bodyIn;
            for (int i = 0; i < primitive.getPropertyCount(); i++) {
                strFatto = primitive.getProperty(i).toString();    // Qui il WS returna l'IDutente
                fatto = Boolean.parseBoolean(strFatto);   // Salvo l'idutente nella variabile apposita
            }
            return strFatto;    // il WS ha risposto e il return viene mandato a onPostExecute

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

    public Boolean getFatto(){
        return fatto;
    }
}
