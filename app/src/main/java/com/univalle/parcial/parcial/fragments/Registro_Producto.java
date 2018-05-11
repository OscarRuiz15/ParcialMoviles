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

import com.univalle.parcial.parcial.R;
import com.univalle.parcial.parcial.conexion.ProductoBD;
import com.univalle.parcial.parcial.modelo.Producto;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Registro_Producto.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Registro_Producto#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Registro_Producto extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText txtProducto;
    private EditText txtPrecio;
    private String producto;
    private String valor;
    private Button btnlimpiar, btnregistrar;

    private OnFragmentInteractionListener mListener;

    public Registro_Producto() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Registro_Producto.
     */
    // TODO: Rename and change types and number of parameters
    public static Registro_Producto newInstance(String param1, String param2) {
        Registro_Producto fragment = new Registro_Producto();
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
        View v= inflater.inflate(R.layout.fragment_registro__producto, container, false);

        txtProducto = v.findViewById(R.id.tfProducto);
        txtPrecio = v.findViewById(R.id.tfValorProducto);

        btnlimpiar = (Button) v.findViewById(R.id.btnLimpiar);
        btnregistrar = (Button) v.findViewById(R.id.btnAgregar);

        btnlimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtProducto.setText("");
                txtPrecio.setText("");

            }
        });

        btnregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String producto = txtProducto.getText().toString().trim();
                String valor = txtPrecio.getText().toString().trim();
                ValidarCampos val = new ValidarCampos();
                if (producto.equals("") || valor.equals("")){
                    String message = "Hay campos vacios";
                    AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                    alertDialog.setMessage(message);
                    alertDialog.show();
                }
                else if(!(val.Texto(producto) || !(val.Numero(valor)))) {
                    String mensa="";
                    if (!(val.Texto(producto))) {
                        mensa=" El producto es incorrecto";
                    }
                    if (!(val.Numero(valor))) {
                        mensa=" El valor es incorrecto";
                    }
                    String message = mensa;
                    AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                    alertDialog.setMessage(message);
                    alertDialog.show();
                }
                else{
                    Producto p=new Producto(0,producto,Integer.parseInt(valor));

                    ProductoBD pbd = new ProductoBD(getContext(), "Parcial",null,1);

                    boolean query = pbd.insertarProducto(p);
                    if (query) {
                        String message = "Registro con exito";
                        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                        alertDialog.setMessage(message);
                        alertDialog.show();
                        txtProducto.setText("");
                        txtPrecio.setText("");

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
