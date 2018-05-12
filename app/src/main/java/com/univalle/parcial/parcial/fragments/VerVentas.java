package com.univalle.parcial.parcial.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.univalle.parcial.parcial.R;
import com.univalle.parcial.parcial.conexion.VentaBD;
import com.univalle.parcial.parcial.modelo.Producto;
import com.univalle.parcial.parcial.modelo.Venta;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link VerVentas.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link VerVentas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VerVentas extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ArrayAdapter<String> adaptador;
    private ListView ventasList;
    List<Venta> ventas;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public VerVentas() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment VerVentas.
     */
    // TODO: Rename and change types and number of parameters
    public static VerVentas newInstance() {
        VerVentas fragment = new VerVentas();
        Bundle args = new Bundle();
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
        View v= inflater.inflate(R.layout.fragment_ver_ventas, container, false);

        VentaBD vd=new VentaBD(getContext(), "Parcial", null, 1);
        ventas=vd.consultarVentas(getContext());

        ArrayList<String> datosVenta=new ArrayList<String>();
        int totalVentas=0;

        for (int i = 0; i < ventas.size(); i++) {
            datosVenta.add("Venta #"+ventas.get(i).getId()+"\nCliente: "+ventas.get(i).getCliente().getNombre()+" "+ventas.get(i).getCliente().getApellido()+"\nTotal: "+ventas.get(i).getTotal()+"\nFecha: "+ventas.get(i).getFecha());
            totalVentas+=ventas.get(i).getTotal();
        }
        datosVenta.add("Total Ventas: "+totalVentas);

        adaptador=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,datosVenta);
        ventasList=v.findViewById(R.id.ventasList);
        ventasList.setAdapter(adaptador);

        ventasList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String componente= ventasList.getAdapter().getItem(position).toString();
                //Toast.makeText(getApplication(),"Hola: "+componente+" "+position, Toast.LENGTH_SHORT).show();

                    String nombreCliente=ventas.get(position).getCliente().getNombre()+" "+ventas.get(position).getCliente().getApellido();
                    Producto pro=ventas.get(position).getProducto();
                    String productos=""+pro.getItem();
                    Toast.makeText(getContext(),"Producto adquirido: "+productos+"\nCantidad: "+ventas.get(position).getCantidad(), Toast.LENGTH_SHORT).show();

                /*DetallesVenta fragment = DetallesVenta.newInstance(ventas);
                android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frameVentas,fragment);
                ft.addToBackStack(null);
                ft.commit();*/
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
