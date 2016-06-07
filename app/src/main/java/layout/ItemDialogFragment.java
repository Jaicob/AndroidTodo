package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jaicob.simpletodo.R;

import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ItemFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ItemDialogFragment extends DialogFragment implements View.OnClickListener {
    private static final String ITEM_DESCRIPTION = "description";
    private static final String POSITION = "position";
//    private static final String DUE_DATE = "dueDate";

    private String itemDescription;
    private int position;
//    private Date dueDate;

    private Button btnSave;
    private Button btnCancel;
    private TextView tvTitleLabel;
    private EditText etDescription;

    private OnFragmentInteractionListener mListener;

    public ItemDialogFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param taskDescription Parameter 1.
     * @return A new instance of fragment ItemFragment.
     */
    public static ItemDialogFragment newInstance(String taskDescription, int position) {
        ItemDialogFragment fragment = new ItemDialogFragment();
        Bundle args = new Bundle();
        args.putString("description", taskDescription);
        args.putInt("position", position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            itemDescription = getArguments().getString(ITEM_DESCRIPTION);
            position = getArguments().getInt(POSITION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item, container, false);
        btnSave = (Button) view.findViewById(R.id.save);
        btnCancel = (Button) view.findViewById(R.id.btnCancel);
        tvTitleLabel = (TextView) view.findViewById(R.id.tvTitleLabel);
        etDescription = (EditText) view.findViewById(R.id.etDescription);

        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        tvTitleLabel.setText(itemDescription);
        etDescription.setHint(itemDescription);
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnCancel) {
            this.dismiss();
        } else {
            System.out.println("Clicked the save button");
            OnFragmentInteractionListener listener = (OnFragmentInteractionListener) getActivity();
            String newDescription = etDescription.getText().toString();
            listener.onFragmentInteraction(newDescription, position);
            this.dismiss();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        // Get existing layout params for the window
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        //Assign window properties to fill the parent
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
        super.onResume();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String description, int position);
    }
}
