package jm_tapschool.taptenance;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;


// Uso di AsyncTask https://androidresearch.wordpress.com/2012/03/17/understanding-asynctask-once-and-forever/

//  AsyncTask is a generic class, it uses 3 types: AsyncTask<Params, Progress, Result>.
//        Params – the input. what you pass to the AsyncTask
//        Progress – if you have any updates, passed to onProgressUpdate()
//        Result – the output. what returns doInBackground()
public class CallWebService extends AsyncTask<String, String, String> {

    // URL corrispondente al web service
    public final static String URL = "http://10.0.2.2:8080/NB_WSfornaro/authenticator";

    // target name space
    public final static String NAMESPACE = "http://fornaro.org/";

    // nome dell'operazione
    public final static String METHOD_NAME_LOGIN = "login";

    // nome dei parametri
    private static final String PARAMETER_NAME_LOGIN1 = "username";
    private static final String PARAMETER_NAME_LOGIN2 = "password";

    // valore per il parametro
    private static final String PARAMETER_VALUE_LOGIN1 = "";

    // variabile per memorizzazione id utente
    private static int IdUtente;

    // TextView dove visualizzare il risultato
    private TextView textViewRisultato;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Things to be done before execution of long running operation. For
        // example showing ProgessDialog

        // creazione integer per memorizzare il valore ricevuto dal web service
        IdUtente = -1;
    }


    @Override
    protected String doInBackground(String... params) {
        String SOAP_ACTION = NAMESPACE + METHOD_NAME_LOGIN;

        SoapObject soapObject = new SoapObject(NAMESPACE, METHOD_NAME_LOGIN);

        soapObject.addProperty(PARAMETER_NAME_LOGIN1, params[1]);   // parametro username
        soapObject.addProperty(PARAMETER_NAME_LOGIN2, params[2]);   // parametro password

        Log.i("com.example.samplews", "Request Value -> " + soapObject.toString());
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soapObject);

        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);

        // http://stackoverflow.com/questions/11029205/ksoap2-android-receive-array-of-objects
        try {
            httpTransportSE.call(SOAP_ACTION, envelope);
            SoapObject primitive = (SoapObject) envelope.bodyIn;
            for (int i = 0; i < primitive.getPropertyCount(); i++) {
                String str = primitive.getProperty(i).toString();    // Qui il WS returna l'IDutente
                IdUtente = Integer.parseInt(str);   // Salvo l'idutente nella variabile apposita
            }
            return "ok";    // il WS ha risposto e il return viene mandato a onPostExecute

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

        if (esito != null) {
            textViewRisultato.setText(Integer.toString(IdUtente));
        } else {
            textViewRisultato.setText("Errore.");
        }
    }

    public void setTextViewRisultato(TextView tv) {
        textViewRisultato = tv;
    }

    public int getIdUtente(){
        return IdUtente;
    }
}