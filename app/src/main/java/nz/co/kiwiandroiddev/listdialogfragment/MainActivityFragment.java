package nz.co.kiwiandroiddev.listdialogfragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import hugo.weaving.DebugLog;
import timber.log.Timber;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements CustomListDialogFragment.CustomListDialogFragmentListener {

    private Button mButton;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mButton = (Button) view.findViewById(R.id.show_dialog_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        return view;
    }

    private void showDialog() {
        ArrayList<CharSequence> testItems = new ArrayList<>();
        testItems.add("test 1");
        testItems.add("test 2");
        testItems.add("test 3");

        CustomListDialogFragment.createAndShow(this, 0, testItems);
    }

    @DebugLog
    @Override
    public void onListItemSelected(int requestCode, int itemIndex) {
        // TODO
    }
}
