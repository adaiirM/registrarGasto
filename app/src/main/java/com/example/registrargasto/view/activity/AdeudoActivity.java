package com.example.registrargasto.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.registrargasto.DAOS.DAOAdeudoImp;
import com.example.registrargasto.DAOS.DAOTipoGastoImp;
import com.example.registrargasto.DAOS.IDAOS.IDAOAdeudo;
import com.example.registrargasto.DAOS.IDAOS.IDAOTipoGasto;
import com.example.registrargasto.MainActivity;
import com.example.registrargasto.R;
import com.example.registrargasto.Validaciones.ValidacionesFechas;
import com.example.registrargasto.adapter.AdapterListaAdeudos;
import com.example.registrargasto.entidades.AdeudoDTO;
import com.example.registrargasto.entidades.TipoGastoDTO;
import com.example.registrargasto.view.dialog.DatePickerFragment;
import com.example.registrargasto.view.fragment.IFragment.IAdeudoFragmentView;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdeudoActivity extends AppCompatActivity implements IDAOTipoGasto, IDAOAdeudo, IAdeudoFragmentView {

    private EditText mfechaGasto;
    private static Spinner spinnerTipoGasto;
    private EditText mNombreAdeudo;
    private EditText mPrecio;
    private EditText mLugar;
    private EditText mCantidad;
    private EditText mTotal;
    private Button mButonGuardar;
    private Button mButonCancelar;
    private AdapterListaAdeudos adapterListaAdeudos;


    private ArrayList<String> listTipoGastos;
    private ArrayList<TipoGastoDTO> listaTipoGasto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adeudo);
        inicializarControles();
        lanzarDatePicker();
        adapterTipoGasto();
        asignarTotal();
        clickGUardar();
        cancelar();
    }

    public void inicializarControles(){
        mfechaGasto=findViewById(R.id.ac_edt_fecha_gasto_ad);
        spinnerTipoGasto= findViewById(R.id.ac_spinner_tipo_gasto_ad);
        mNombreAdeudo=findViewById(R.id.ac_edt_nombre_gasto_ad);
        mPrecio=findViewById(R.id.ac_edt_precio_ad);
        mLugar=findViewById(R.id.ac_edt_lugar_pago_ad);
        mCantidad=findViewById(R.id.ac_edt_cantidad_gasto_ad);
        mTotal=findViewById(R.id.ac_edt_total_gasto_ad);
        mButonCancelar=findViewById(R.id.ac_btn_cancelarAdeudo);
        mButonGuardar=findViewById(R.id.ac_btn_guardarAdeudo);

    }
    public void lanzarDatePicker() {
        mfechaGasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mostrarCalendario();
                showDatePickerDialog();
            }
        });
    }

    @Override
    public ArrayList<TipoGastoDTO> consultarTipoGasto() {
        IDAOTipoGasto idaoTipoGasto= new DAOTipoGastoImp(AdeudoActivity.this);
        return idaoTipoGasto.consultarTipoGasto();
    }

    public void adapterTipoGasto() {
        listaTipoGasto = consultarTipoGasto();
        listTipoGastos=new ArrayList<String>();
        listTipoGastos.add("Seleccione");
        for (TipoGastoDTO tipo:listaTipoGasto) {
            listTipoGastos.add(tipo.getNombreTipoGas());
        }

        ArrayAdapter<CharSequence> adapter= new ArrayAdapter(this, R.layout.items_spinner_tipo_gasto, listTipoGastos);
        spinnerTipoGasto.setAdapter(adapter);
    }




    private String twoDigits(int n) {
        return (n <= 9) ? ("0" + n) : String.valueOf(n);
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                final String diaSeleccionado = twoDigits(dayOfMonth) + "-" + twoDigits(month + 1) + "-" + year;
                mfechaGasto.setText(diaSeleccionado);
            }
        });
        newFragment.show(this.getSupportFragmentManager(), "datePicker");
    }


    private void mostrarToast(String mensaje) {
        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
    }

    private void clickGUardar() {

        mButonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean flag = true;
                if (mfechaGasto.getText().toString().equals("") || mNombreAdeudo.getText().toString().equals("")
                        || mLugar.getText().toString().equals("") || mCantidad.getText().toString().equals("")
                        || mPrecio.getText().toString().equals("") || idTipoGasto() == 0){
                    mostrarToast("Rellena todos los campos");
                } else{
                    if (!verificarTexto(mNombreAdeudo.getText().toString())){
                        mostrarToast("Nombre de gasto: Ingresa solo letras");
                        flag = false;
                    }
                    if(!verificarFecha()){
                        flag = false;
                    }
                    if (flag){
                        String fechaActual = twoDigits(DatePickerFragment.day) + "-" + twoDigits(DatePickerFragment.month + 1) + "-" + DatePickerFragment.year;
                        AdeudoDTO adeudoDto = new AdeudoDTO(mNombreAdeudo.getText().toString(),mLugar.getText().toString(),Double.valueOf(mPrecio.getText().toString()),
                                Integer.valueOf(mCantidad.getText().toString()), Double.valueOf(mPrecio.getText().toString()) * Integer.valueOf(mCantidad.getText().toString()),
                                mfechaGasto.getText().toString(),idTipoGasto());
                        // mostrarToast(adeudoDto.toString());
                        long idAdeudo = registarNuevoAdeudo(adeudoDto);
                        if (idAdeudo > 0) {
                            mostrarToast("El registro ha sido correcto.");
                        } else {
                            mostrarToast("Ha ocurrido algun error, verifique no insertar datos repetidos.");
                        }


                        adapterListaAdeudos=new AdapterListaAdeudos(consultarAdeudo());
                        adapterListaAdeudos.notifyItemChanged(consultarAdeudo().size());
                        Intent intent;
                        intent = new Intent(AdeudoActivity.this, MainActivity.class);
                        startActivity(intent);
                    }

                }

            }

        });
    }

    private void cancelar(){
        mButonCancelar.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent;
              intent = new Intent(AdeudoActivity.this, MainActivity.class);
              startActivity(intent);
          }
                                          }
        );
    }

    public int idTipoGasto(){
        int idTipo=0;
        String selec=spinnerTipoGasto.getSelectedItem().toString();
        switch (selec){
            case "Producto":
                idTipo=1;
                break;
            case "Servicio":
                idTipo=2;
                break;
            default:
                idTipo=0;
                break;

        }
        return idTipo;
    }

    //Metodo para verificar que no se introduzcan caracteres incorrectos

    public static boolean verificarTexto(String cadena){
        Pattern patron=Pattern.compile("[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s]+");
        Matcher comprobacion = patron.matcher(cadena);
        if (comprobacion.matches()){
            return true;
        }else{
            return false;
        }
    }
    public boolean verificarFecha(){
        ValidacionesFechas validacionesFechas=new ValidacionesFechas();
        boolean estado;
        try {
            if(validacionesFechas.validarActualAterior(mfechaGasto.getText().toString()) || validacionesFechas.validarFechasIguales(mfechaGasto.getText().toString()) ){
                mfechaGasto.setError(" ");
                mostrarToast("Fecha: No puedes ingresar una fecha igual o anterior a hoy");
                estado=false;
            }else{
                estado=true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return estado;
    }

    private void asignarTotal(){
        mCantidad.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                verficarCampos();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mPrecio.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                verficarCampos();
            }

            @Override
            public void afterTextChanged(Editable s) {
                String str = s.toString();
                int decimalCount = 0;
                int length = str.length();

                for (int i = 0; i < length; i++) {
                    char c = str.charAt(i);
                    if (c == '.') {
                        decimalCount++;
                        if (decimalCount > 1) {
                            s.delete(i, i + 1);
                            return;
                        }
                    } else if (decimalCount == 1 && i > (str.indexOf('.') + 2)) {
                        s.delete(i, i + 1);
                        return;
                    }
                }
            }
        });
    }

    private void verficarCampos(){
        if(!mCantidad.getText().toString().equals("") && !mPrecio.getText().toString().equals("")){
            mTotal.setText(""+Double.parseDouble(mPrecio.getText().toString()) *
                    Double.parseDouble(mCantidad.getText().toString()));
        }else {
            mTotal.setText("0");
        }
    }

    @Override
    public long registarNuevoAdeudo(AdeudoDTO adeudoDTO) {
        IDAOAdeudo idaoAdeudo = new DAOAdeudoImp(AdeudoActivity.this);
        return idaoAdeudo.registarNuevoAdeudo(adeudoDTO);
    }

    @Override
    public ArrayList<AdeudoDTO> consultarAdeudo() {
        IAdeudoFragmentView iAdeudoFragmentView = new DAOAdeudoImp(AdeudoActivity.this);
        return iAdeudoFragmentView.consultarAdeudo();
    }

    @Override
    public ArrayList<AdeudoDTO> consultarAdeudoid() {
        return null;
    }

    @Override
    public void eliminarid(long id) {

    }

    @Override
    public ArrayList<AdeudoDTO> consultarAdeudoDatos() {
        return null;
    }
}