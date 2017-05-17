package jm_tapschool.taptenance;

import android.os.AsyncTask;
import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;


// Uso di AsyncTask https://androidresearch.wordpress.com/2012/03/17/understanding-asynctask-once-and-forever/

//  AsyncTask is a generic class, it uses 3 types: AsyncTask<Params, Progress, Result>.
//        Params – the input. what you pass to the AsyncTask
//        Progress – if you have any updates, passed to onProgressUpdate()
//        Result – the output. what returns doInBackground()
public class CallWebServiceHome extends AsyncTask<String, String, ArrayList<String>> {

    // URL corrispondente al web service
    public final static String URL = "http://10.0.2.2:8080/NB_WSfornaro/gestoreSegnalazioni";

    // target name space
    public final static String NAMESPACE = "http://fornaro.org/";

    // nome dell'operazione
    public final static String METHOD_NAME_GETSGNL = "getSegnalazioni";

    // variabile per memorizzazione segnalazioni
    private static ArrayList<String> segnalazioni = new ArrayList<>();

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Things to be done before execution of long running operation. For
        // example showing ProgessDialog
    }


    @Override
    protected ArrayList<String> doInBackground(String... params) {
        String SOAP_ACTION = NAMESPACE + METHOD_NAME_GETSGNL;

        SoapObject soapObject = new SoapObject(NAMESPACE, METHOD_NAME_GETSGNL);

        Log.i("com.example.samplews", "Request Value -> " + soapObject.toString());
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soapObject);

        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);

        // http://stackoverflow.com/questions/11029205/ksoap2-android-receive-array-of-objects
        String strSegnalazioneSOAP = "";
        try {
            httpTransportSE.call(SOAP_ACTION, envelope);
            SoapObject primitive = (SoapObject) envelope.bodyIn;
            for (int i = 0; i < primitive.getPropertyCount(); i++) {
                strSegnalazioneSOAP = primitive.getProperty(i).toString();    // Qui il WS returna la segnalazione
                segnalazioni.add(strSegnalazioneSOAP);
            }
            return segnalazioni;    // il WS ha risposto e il return viene mandato a onPostExecute
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;    // qualcosa è andato male
    }


    // http://stackoverflow.com/questions/12575068/how-to-get-the-result-of-onpostexecute-to-main-activity-because-asynctask-is-a
}