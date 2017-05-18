package com.example.christian.push_taptenance;


/**
 * Created by Christian on 17/05/2017.
 */


import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;



public class CallWebService extends AsyncTask<String, String, String> {


    // URL corrispondente al web service
    public final static String URL = "http://10.0.2.2:8080/PushSegnalazioneWSApplication/PushWS";
    // target name space
    public final static String NAMESPACE = "http://calculator.giodabg.org/";

    // nome dell'operazione
    public final static String METHOD_SEGNALAZIONE_PUSH = "push";

    // nome dei parametri
    private static final String PARAMETER_NAME_TITOLO = "titolo";
    private static final String PARAMETER_NAME_DESCRIZIONE = "descrizione_problema";
    private static final String PARAMETER_NAME_LOTTO = "lotto";
    private static final String PARAMETER_NAME_UTENTE = "utente";




    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Things to be done before execution of long running operation. For
        // example showing ProgessDialog

    }


    @Override
    protected String doInBackground(String... params) {
        String str;

            // impostazione nome esteso dell'operazione
            String SOAP_ACTION = NAMESPACE + METHOD_SEGNALAZIONE_PUSH;

            // creazione dell'oggetto per la comunicazione con il web service
            // impostando il namespace e l'operazione da eseguire
            SoapObject soapObject = new SoapObject(NAMESPACE, METHOD_SEGNALAZIONE_PUSH);

            // preparazione delle proprietà/parametri dell'operazione
            PropertyInfo propertyInfo = new PropertyInfo();


            // FORMA SEMPLIFICATA: aggiunta di un parametro con nome PARAMETER_NAME_ADD2 di tipo base
            soapObject.addProperty(PARAMETER_NAME_TITOLO, params[1]);
            soapObject.addProperty(PARAMETER_NAME_DESCRIZIONE, params[2]);
            soapObject.addProperty(PARAMETER_NAME_LOTTO, params[3]);
            soapObject.addProperty(PARAMETER_NAME_UTENTE, params[4]);

            Log.i("com.example.samplews", "Request Value -> " + soapObject.toString());
            // creazione della busta SOAP da inviare
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

            // inserimento nella busta dell'oggetto SOAP
            envelope.setOutputSoapObject(soapObject);

            // creazione della connessione con protocollo HTTP al web service
            HttpTransportSE httpTransportSE = new HttpTransportSE(URL);

            try {
                // richiesta di esecuzione dell'operazione SOAP_ACTION ssui dati contenuti nella busta SOAP
                httpTransportSE.call(SOAP_ACTION, envelope);

                // ricezione della risposta
                SoapPrimitive soapPrimitive = (SoapPrimitive) envelope.getResponse();

                // FORMA SEMPLIFICATA: estrazione dalla risposta del contenuto
                str = soapPrimitive.toString();

                Log.i("com.example.samplews", "Risultato -------------- " + str);
                return str;
            } catch (Exception e) {
                e.printStackTrace();
            }

        return null;
    }


    // http://stackoverflow.com/questions/12575068/how-to-get-the-result-of-onpostexecute-to-main-activity-because-asynctask-is-a

    @Override
    protected void onPostExecute(String esito) {
        super.onPostExecute(esito);

        // richiamato al termine della chiamata al web service
        // il parametro esito è il valore ritornato da doInBackground()


    }



}
