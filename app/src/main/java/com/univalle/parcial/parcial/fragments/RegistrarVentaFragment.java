package com.univalle.parcial.parcial.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.univalle.parcial.parcial.R;
import com.univalle.parcial.parcial.conexion.ClienteBD;
import com.univalle.parcial.parcial.modelo.Cliente;
import com.univalle.parcial.parcial.modelo.Venta;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegistrarVentaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistrarVentaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private Button btncrear;
    private AutoCompleteTextView txtid;
    private static List<Venta> ventas = new ArrayList<>();

    private OnFragmentInteractionListener mListener;

    public RegistrarVentaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistrarVentaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistrarVentaFragment newInstance(String param1, String param2) {
        RegistrarVentaFragment fragment = new RegistrarVentaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

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
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_registrar_venta, container, false);
        txtid = (AutoCompleteTextView) v.findViewById(R.id.txtconsulta);
        ClienteBD cbd = new ClienteBD(getContext(), "parcial", null, 1);
        List<Cliente> clientes = cbd.consultarClientes();
        String autocompletar[] = new String[clientes.size()];
        for (int i = 0; i < autocompletar.length; i++) {
            autocompletar[i] = clientes.get(i).getNombre() + " " + clientes.get(i).getApellido();
        }
        ArrayAdapter adapter = new ArrayAdapter(v.getContext(), android.R.layout.simple_list_item_1, autocompletar);
        txtid.setAdapter(adapter);
        //Establecemos el layout main


        //Obtenemos el linear layout donde colocar los botones
        ScrollView llBotonera = (ScrollView) v.findViewById(R.id.scrollventas);

        //Creamos las propiedades de layout que tendrán los botones.
        //Son LinearLayout.LayoutParams porque los botones van a estar en un LinearLayout.
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT );

        //Creamos los botones en bucle
        if (!ventas.isEmpty()) {

            for (int i = 0; i < ventas.size(); i++) {
                Button button = new Button(getContext());
                //Asignamos propiedades de layout al boton
                button.setLayoutParams(lp);
                //Asignamos Texto al botón
                button.setText(ventas.get(i).getProducto().getItem()+"\t" + ventas.get(i).getCantidad()+"\t" + "$"+ventas.get(i).getTotal());
                //Añadimos el botón a la botonera
                button.setOnClickListener(new ButtonsOnClickListener());
                llBotonera.addView(button);
            }
            btncrear = (Button) v.findViewById(R.id.btncrearventa);
            btncrear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

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

    public static RegistrarVentaFragment newInstance(Bundle arguments) {

        RegistrarVentaFragment fragment = new RegistrarVentaFragment();
        if (arguments!=null) {
            Bundle args = new Bundle();

            ventas = arguments.getParcelableArrayList("ventas");


            fragment.setArguments(args);

        }
        return fragment;
    }
    class ButtonsOnClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {

        }

    };
}
