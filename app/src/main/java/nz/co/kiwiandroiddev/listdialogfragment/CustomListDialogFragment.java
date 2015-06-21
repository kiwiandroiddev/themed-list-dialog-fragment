package nz.co.kiwiandroiddev.listdialogfragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;

import java.util.ArrayList;

/**
 * The goal of this is to be an easy-to-use, customisable single- and multi-select list dialog.
 * It is intended to be shown from a fragment rather than an activity. The fragment should
 * implement a callback interface to be notified of selection events.
 *
 * Created by matt on 21/06/15.
 */
public class CustomListDialogFragment extends DialogFragment {

    private static final String KEY_REQUEST_CODE = "requestCode";
    private static final String KEY_ITEMS = "items";

    private ArrayList<CharSequence> mItems;
    private CustomListDialogFragmentListener mParent;
    private int mParentRequestCode;

    public interface CustomListDialogFragmentListener {
        void onListItemSelected(int requestCode, int itemIndex);
    }

    public static CustomListDialogFragment createAndShow(Fragment parentFragment,
                                                         int requestCode,
                                                         ArrayList<CharSequence> items) {
        CustomListDialogFragment frag = new CustomListDialogFragment();
        Bundle args = new Bundle();
        args.putCharSequenceArrayList(KEY_ITEMS, items);
        args.putInt(KEY_REQUEST_CODE, requestCode);
        frag.setArguments(args);
        frag.setTargetFragment(parentFragment, requestCode);
        frag.show(parentFragment.getFragmentManager(), "dialog_tag");
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (!(getTargetFragment() instanceof CustomListDialogFragmentListener)) {
            throw new IllegalArgumentException("Parent fragment must implement CustomListDialogFragmentListener");
        }

        mParentRequestCode = getArguments().getInt(KEY_REQUEST_CODE);
        mParent = (CustomListDialogFragmentListener) getTargetFragment();
        mItems = getArguments().getCharSequenceArrayList("items");

        return new AlertDialog.Builder(getActivity())
                .setSingleChoiceItems(mItems.toArray(new CharSequence[mItems.size()]), 0,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                onListItemSelected(which);
                            }
                        })
                .create();
    }

    private void onListItemSelected(int itemIndex) {
        // tell parent fragment which item was selected
        mParent.onListItemSelected(mParentRequestCode, itemIndex);
    }


}
