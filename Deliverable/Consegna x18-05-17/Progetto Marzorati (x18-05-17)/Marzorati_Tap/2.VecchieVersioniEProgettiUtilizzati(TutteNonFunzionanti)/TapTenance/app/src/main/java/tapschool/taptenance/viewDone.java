package tapschool.taptenance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
public class viewDone extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_done);
        EditText edit=(EditText) findViewById(R.id.textView);
        //final ListView listViewDone=(ListView) findViewById(R.id.listViewDone);
        final callWebServiceDone callWS=new callWebServiceDone();
        Object result = null;
        try {
            result = callWS.execute(callWebServiceDone.METHOD_NAME_VIEWDONE).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
      }

}
