package layout;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import com.jaicob.simpletodo.R;
import com.jaicob.simpletodo.models.Task;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ItemFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ItemDialogFragment extends DialogFragment implements View.OnClickListener {
    private static final String TASK_ID = "taskId";
    private static final String POSITION = "position";

    private Task task;
    private int position;

    private Button btnSave;
    private Button btnCancel;
    private Button btnDelete;
    private EditText etDescription;
    private DatePicker datePicker;
    private Switch recurrenceSwitch;
    private Spinner sPriority;

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
    public static ItemDialogFragment newInstance(Long taskId, int position) {
        ItemDialogFragment fragment = new ItemDialogFragment();
        Bundle args = new Bundle();
        args.putLong("taskId", taskId);
        args.putInt("position", position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            task = Task.load(Task.class,getArguments().getLong(TASK_ID));
            position = getArguments().getInt(POSITION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item, container, false);
        // Fetch components
        btnSave = (Button) view.findViewById(R.id.btnSave);
        btnCancel = (Button) view.findViewById(R.id.btnCancel);
        btnDelete = (Button) view.findViewById(R.id.btnDelete);
        etDescription = (EditText) view.findViewById(R.id.etDescription);
        datePicker = (DatePicker) view.findViewById(R.id.datePicker);
        recurrenceSwitch = (Switch) view.findViewById(R.id.switchRecurring);
        sPriority = (Spinner) view.findViewById(R.id.sPriority);

        // Add initial data/listiners to components
        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        etDescription.setText(task.description);
        recurrenceSwitch.setChecked(task.recurring);

        String[] priorities = new String[]{"Low", "Med", "High"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, priorities);
        sPriority.setAdapter(adapter);
        sPriority.setSelection(adapter.getPosition(task.priority));

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(task.dueDate);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        datePicker.updateDate(year,month,day);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onClick(View view) {
        OnFragmentInteractionListener listener = (OnFragmentInteractionListener) getActivity();
        switch (view.getId()){
            case R.id.btnCancel:
                this.dismiss();
                break;
            case R.id.btnDelete:
                task.delete();
                listener.onFragmentDelete(position);
                this.dismiss();
                break;
            case R.id.btnSave:
                task.description = etDescription.getText().toString();
                task.recurring = recurrenceSwitch.isChecked();
                task.priority = sPriority.getSelectedItem().toString();
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth();
                int year =  datePicker.getYear();
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);
                task.dueDate = calendar.getTime();
                task.save();
                listener.onFragmentUpdate(task.getId(), position);
                this.dismiss();
                break;
            default:
                this.dismiss();
                break;
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
        // Assign window properties to fill the parent
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
        void onFragmentDelete(int position);
        void onFragmentUpdate(Long taskId, int position);
    }
}
