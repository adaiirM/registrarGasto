package com.example.registrargasto.view.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.registrargasto.DAOS.DAOGastoImp;
import com.example.registrargasto.DAOS.DAOPresupuestoIm;
import com.example.registrargasto.DAOS.DAOTipoGastoImp;
import com.example.registrargasto.DAOS.IDAOS.IDAOGasto;
import com.example.registrargasto.DAOS.IDAOS.IDAOTipoGasto;
import com.example.registrargasto.MainActivity;
import com.example.registrargasto.R;
import com.example.registrargasto.adapter.AdapterListaGastos;
import com.example.registrargasto.entidades.GastoDTO;
import com.example.registrargasto.entidades.PresupuestoDTO;
import com.example.registrargasto.entidades.TipoGastoDTO;
import com.example.registrargasto.view.activity.IActivity.IGastoActivityView;
import com.example.registrargasto.view.activity.IActivity.IPresupuestoactivityGasto;
import com.example.registrargasto.view.dialog.DatePickerFragment;

import java.util.ArrayList;

public class ActivityGastos extends AppCompatActivity implements IDAOGasto, IDAOTipoGasto, IGastoActivityView, IPresupuestoactivityGasto {

    private EditText mfechaGasto;
    private static Spinner mTipoGasto;
    private EditText mNombreGasto;
    private EditText mPrecio;
    private EditText mLugar;
    private EditText mCantidad;
    private EditText mTotal;
    private Button mButonGuardar;
    private Button mButonCancelar;
    private EditText itemTipoGasto;
    private AdapterListaGastos adapterListaGastos;

    private ArrayList<String>listTipoGastos;
    private ArrayList<TipoGastoDTO> listaTipoGasto;

    private String[] tipoGasto;
    private long id;
    private String tipoGastos;
    private String fechaActual;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gastos);
        inicializarControles();
        lanzarDatePicker();
        adapterTipoGasto();
        asignarTotal();
        clickGUardar();
        cancelar();


    }


    //Se inicializan los controles
    private void inicializarControles() {
        mfechaGasto = findViewById(R.id.edt_fecha_gasto);
        mNombreGasto = findViewById(R.id.edt_nombre_gasto);
        mTipoGasto = findViewById(R.id.spinner_tipo_gasto);
        mLugar = findViewById(R.id.edt_lugar_gasto);
        mCantidad = findViewById(R.id.edt_cantidad_gasto);
        mPrecio = findViewById(R.id.edt_precio);
        mTotal = findViewById(R.id.edt_total_gasto);
        mButonGuardar = findViewById(R.id.btn_guardarAdeudo);
        mButonCancelar = findViewById(R.id.ag_btn_cancelarAdeudo);
    }


    public void adapterTipoGasto() {
        listaTipoGasto = consultarTipoGasto();
        listTipoGastos=new ArrayList<String>();
        listTipoGastos.add("Seleccione");
        for (TipoGastoDTO tipo:listaTipoGasto) {
            listTipoGastos.add(tipo.getNombreTipoGas());
        }

        ArrayAdapter<CharSequence>adapter= new ArrayAdapter(this, R.layout.items_spinner_tipo_gasto, listTipoGastos);
        mTipoGasto.setAdapter(adapter);
    }



    public int idTipoGasto(){
        int idTipo=0;
        String selec=mTipoGasto.getSelectedItem().toString();
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


    public void lanzarDatePicker() {
        mfechaGasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mostrarCalendario();
                showDatePickerDialog();
            }
        });
    }



    private void clickGUardar() {

        mButonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean flag = true;
                if (mfechaGasto.getText().toString().equals("") || mNombreGasto.getText().toString().equals("")
                        || mLugar.getText().toString().equals("") || mCantidad.getText().toString().equals("")
                        || mPrecio.getText().toString().equals("") || idTipoGasto() == 0){
                    mostrarToast("Rellena todos los campos");
                } else{
                    if (!verificarTexto(mNombreGasto.getText().toString())){
                        mostrarToast("Nombre de gasto: Ingresa solo letras");
                        flag = false;
                    }

                    if (Double.parseDouble(mPrecio.getText().toString()) == 0 || Double.parseDouble(mCantidad.getText().toString()) == 0){
                        mostrarToast("Ingresa un valor mayor a cero");
                        flag = false;
                    }

                    if (flag){
                        if(consultarPresupuestoid().getCantidad() - Double.parseDouble(mTotal.getText().toString()) < 0){
                            mostrarToast("Tu presupuesto no es suficiente");
                        }else {
                            String fechaActual = twoDigits(DatePickerFragment.day) + "-" + twoDigits(DatePickerFragment.month + 1) + "-" + DatePickerFragment.year;
                            GastoDTO adeudoDto = new GastoDTO(mNombreGasto.getText().toString(), mfechaGasto.getText().toString(),
                                    mLugar.getText().toString(), Double.valueOf(mPrecio.getText().toString()), Integer.valueOf(mCantidad.getText().toString()),
                                    Double.valueOf(mPrecio.getText().toString()) * Integer.valueOf(mCantidad.getText().toString()), idTipoGasto());
                            // mostrarToast(adeudoDto.toString());
                            long idAdeudo = registrarNuevoAdeudo(adeudoDto);
                            if (idAdeudo > 0) {
                                mostrarToast("El registro ha sido correcto.");
                            } else {
                                mostrarToast("Ha ocurrido algun error, verifique no insertar datos repetidos.");
                            }

                            try {
                                adapterListaGastos = new AdapterListaGastos(consultarGastos());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            adapterListaGastos.notifyItemChanged(consultarGastos().size());

                            if(consultarPresupuestoid().getCantidad()>0.0){
                                restarPresupuesto(Double.valueOf(mTotal.getText().toString()));
                            }

                            limpiarCampos();
                            Intent intent;
                            intent = new Intent(ActivityGastos.this, MainActivity.class);
                            startActivity(intent);
                        }

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
                  intent = new Intent(getApplicationContext(), MainActivity.class);
                  startActivity(intent);
              }
          }
        );
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

    private void verficarCampos(){
        if(!mCantidad.getText().toString().equals("") && !mPrecio.getText().toString().equals("")){
            mTotal.setText(""+Double.parseDouble(mPrecio.getText().toString()) *
                    Double.parseDouble(mCantidad.getText().toString()));
        }else {
            mTotal.setText("0");
        }
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


    //Ingresar los datos del nuevo gastos registrado a la BD ()
    @Override
    public long registrarNuevoAdeudo(GastoDTO gasto) {
        IDAOGasto iDaoGasto = new DAOGastoImp(ActivityGastos.this);
        return iDaoGasto.registrarNuevoAdeudo(gasto);
    }

    @Override
    public ArrayList<TipoGastoDTO> consultarTipoGasto() {
        IDAOTipoGasto idaoTipoGasto= new DAOTipoGastoImp(ActivityGastos.this);
        return idaoTipoGasto.consultarTipoGasto();
    }


    private void limpiarCampos() {
        mfechaGasto.setText("");
        mNombreGasto.setText("");
        mLugar.setText("");
        mCantidad.setText("");
        mPrecio.setText("");
        mTotal.setText("");
    }

    private void mostrarToast(String mensaje) {
        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
    }

    //Metodo para verificar que no se introduzcan caracteres incorrectos

    public static boolean verificarTexto(String cadena){
        String expReg = "[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\\s]+";
        if (cadena.matches(expReg)){
            return true;
        }else
            return false;
    }


    //Metodo para consultar los adeudos existentes en la BD (IGastoActivityView)
    @Override
    public ArrayList<GastoDTO> consultarGastos() {
        IGastoActivityView iGastoActivityView=new DAOGastoImp(ActivityGastos.this);
        return iGastoActivityView.consultarGastos();
    }

    @Override
    public void restarPresupuesto(double gasto) {
        IPresupuestoactivityGasto iPresupuestoactivityGasto=new DAOPresupuestoIm(ActivityGastos.this);
        iPresupuestoactivityGasto.restarPresupuesto(gasto);
    }

    @Override
    public PresupuestoDTO consultarPresupuestoid() {
        IPresupuestoactivityGasto iPresupuestoactivityGasto=new DAOPresupuestoIm(ActivityGastos.this);
        return iPresupuestoactivityGasto.consultarPresupuestoid();
    }
}
