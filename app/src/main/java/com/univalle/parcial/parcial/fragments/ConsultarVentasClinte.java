package com.univalle.parcial.parcial.fragments;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.univalle.parcial.parcial.R;
import com.univalle.parcial.parcial.conexion.ClienteBD;
import com.univalle.parcial.parcial.conexion.ConexionBD;
import com.univalle.parcial.parcial.conexion.UsuarioBD;
import com.univalle.parcial.parcial.conexion.VentaBD;
import com.univalle.parcial.parcial.modelo.Cliente;
import com.univalle.parcial.parcial.modelo.Venta;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ConsultarVentasClinte.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ConsultarVentasClinte#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConsultarVentasClinte extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ConexionBD conexion;
    private SQLiteDatabase db;

    private TextView txtIdCliente;
    private TextView txtNombre;
    private TextView txtCorreo;
    private TextView txtTotal;
    private EditText listVenta;

    private Button btnConsultar;

    private ArrayAdapter<String> adaptador;
    private ListView productosVentas;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ConsultarVentasClinte() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment ConsultarVentasClinte.
     */
    // TODO: Rename and change types and number of parameters
    public static ConsultarVentasClinte newInstance() {
        ConsultarVentasClinte fragment = new ConsultarVentasClinte();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
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
    //Modificar...
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_consultar_ventas_clinte, container, false);

        txtIdCliente=v.findViewById(R.id.tfIdentificacionCliente);
        txtNombre=v.findViewById(R.id.txtCampoNombre);
        txtCorreo=v.findViewById(R.id.txtCampoCorreo);
        btnConsultar=v.findViewById(R.id.btnConsultar);
        txtTotal=v.findViewById(R.id.txtCampoTotalCompra);
        productosVentas=v.findViewById(R.id.producosVentas);
        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consultarCliente();
            }
        });

//


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

    public void consultarCliente(){
        int identificacion= Integer.parseInt(txtIdCliente.getText().toString().trim());
        int totalVentas=0;
        ClienteBD cbd=new ClienteBD(getContext(),"Parcial", null, 1);
        Cliente client=cbd.consultarId(identificacion);

        List<Venta> venta;
        VentaBD vent=new VentaBD(getContext(), "Parcial", null, 1);

        venta= vent.consultarVentaPorCliente(client, getContext());

        txtNombre.setText(venta.get(0).getCliente().getNombre()+" "+venta.get(0).getCliente().getApellido());
        txtCorreo.setText(venta.get(0).getCliente().getEmail());

        ArrayList<String>datosVenta=new ArrayList<String>();

        for (int i = 0; i < venta.size(); i++) {
            totalVentas+=venta.get(i).getTotal();
            datosVenta.add("Fecha: "+venta.get(i).getFecha()+"\nProducto: "+venta.get(i).getProducto().getItem()+"\nValor Unitario: "+venta.get(i).getProducto().getPrecio()+"\nCantidad: "+venta.get(i).getCantidad()+"\nTotal: "+venta.get(i).getTotal());
        }

        txtTotal.setText(""+totalVentas);

        adaptador=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,datosVenta);

        productosVentas.setAdapter(adaptador);


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
