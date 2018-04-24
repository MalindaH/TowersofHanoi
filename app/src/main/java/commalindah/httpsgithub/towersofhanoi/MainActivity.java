package commalindah.httpsgithub.towersofhanoi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText mNumberInput;
    private TextView mHanoiSolution;
    private TextView mErrorMessage;

    @Override
    /**
     * onCreate is the method that is run when the Activity is created
     *
     * @param savedInstanceState is a Bundle of data that can be used to restore a previous
     *                           instance of this Activity
     * @return "" Nothing is returned
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNumberInput = (EditText) findViewById(R.id.et_number_of_disks);
        mHanoiSolution = (TextView) findViewById(R.id.tv_solution);
        mErrorMessage = (TextView) findViewById(R.id.tv_error_message);

        mNumberInput.addTextChangedListener( numberInputWatcher );
    }

    private final TextWatcher numberInputWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        /**
         * after TextChanged is the method that is called once the text has changed in an Editable object
         *
         * @param editable is the object that has text changed within it
         * @return "" Nothing is returned
         */
        public void afterTextChanged(Editable editable)
        {
            int numberOfDisks;

            if(editable.length() == 0 )
            {
                mHanoiSolution.setText("");
                mErrorMessage.setText("You must enter a number!");
            }
            else if( editable.toString().contains(".") )
            {
                mErrorMessage.setText("The number must be an integer!\n\nNo decimals are allowed.");
                mHanoiSolution.setText("");
            }
            else
            {
                numberOfDisks = Integer.parseInt( mNumberInput.getText().toString() );

                mHanoiSolution.setText("");
                mErrorMessage.setText("");

                solveHanoi( numberOfDisks, 'A', 'C', 'B' );
            }
        }
    };

    /**
     * solveHanoi is a recursive method that solves the Towers of Hanoi logic problem
     *
     * @param diskNumber is the current disk we are moving
     * @param source is the peg we are moving the disk from
     * @param dest is the destination peg for the disk
     * @param spare is the spare peg from the Towers of Hanoi
     * @return "" Nothing is returned
     */
    private void solveHanoi( int diskNumber, char source, char dest, char spare )
    {
        if( diskNumber == 1 )
        {
            mHanoiSolution.append( String.format("\n\nMove Disk 1 from peg %c to peg %c", source, dest ) );
        }
        else
        {
            solveHanoi( diskNumber-1, source, spare, dest );
            mHanoiSolution.append( String.format("\n\nMove Disk %d from peg %c to peg %c", diskNumber, source, dest) );
            solveHanoi( diskNumber-1, spare, dest, source );
        }
    }
}
























