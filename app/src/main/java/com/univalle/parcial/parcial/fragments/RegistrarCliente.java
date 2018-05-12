package com.univalle.parcial.parcial.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.univalle.parcial.parcial.R;
import com.univalle.parcial.parcial.modelo.Cliente;
import com.univalle.parcial.parcial.conexion.ClienteBD;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegistrarCliente.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegistrarCliente#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistrarCliente extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText txtnombre, txtemail, txtapellido, txtCedula;
    private Button btnlimpiar, btnregistrar;
    private Spinner spinner;
    private static Cliente usuario;


    private OnFragmentInteractionListener mListener;


    public RegistrarCliente() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment {@link RegistrarCliente}.
     */
    // TODO: Rename and change types and number of parameters
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_registrar_cliente, container, false);

        txtnombre = (EditText) v.findViewById(R.id.tfNombre);
        txtapellido = (EditText) v.findViewById(R.id.tfApellido);
        txtemail = (EditText) v.findViewById(R.id.tfEmail);
        /*spinner = (Spinner) v.findViewById(R.id.spinner); */
        btnlimpiar = (Button) v.findViewById(R.id.btnlimpiar);
        btnregistrar = (Button) v.findViewById(R.id.btnregistrar);
        txtCedula=(EditText)v.findViewById(R.id.tfCedula);

        // Inflate the layout for this fragment
        btnlimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtnombre.setText("");
                txtapellido.setText("");
                txtemail.setText("");
                txtCedula.setText("");
            }
        });

        btnregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = txtnombre.getText().toString().trim();
                String apellido = txtapellido.getText().toString().trim();
                String email = txtemail.getText().toString().trim();
                String cedula=txtCedula.getText().toString().trim();

                if (nombre.equals("") || email.equals("") || apellido.equals("") || cedula.equals("")) {
                    String message = "Hay campos vacios";
                    AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                    alertDialog.setMessage(message);
                    alertDialog.show();
                } else {
                    Cliente u = new Cliente(Integer.parseInt(cedula), nombre, apellido, email);
                    ClienteBD cbd = new ClienteBD(v.getContext(), "Parcial", null, 1);
                    boolean query = cbd.insertarCliente(u);
                    if (query) {
                        String message = "Registro con exito";
                        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                        alertDialog.setMessage(message);
                        alertDialog.show();
                        txtnombre.setText("");
                        txtapellido.setText("");
                        txtemail.setText("");
                        txtCedula.setText("");
                    }
                }

            }
        });
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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

    public static RegistrarCliente newInstance(String s, String s1) {
        RegistrarCliente fragment = new RegistrarCliente();
        return fragment;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}