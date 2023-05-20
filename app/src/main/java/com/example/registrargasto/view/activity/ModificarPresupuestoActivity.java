package com.example.registrargasto.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.registrargasto.DAOS.DAOPresupuestoIm;
import com.example.registrargasto.DAOS.IDAOS.IDAOPresupuesto;
import com.example.registrargasto.MainActivity;
import com.example.registrargasto.R;
import com.example.registrargasto.Validaciones.ValidacionesFechas;
import com.example.registrargasto.entidades.PresupuestoDTO;
import com.example.registrargasto.view.dialog.DatePickerFragment;
import com.example.registrargasto.view.fragment.IFragment.IPresupuestoFragmentView;

import java.util.ArrayList;

public class ModificarPresupuestoActivity extends AppCompatActivity  implements IDAOPresupuesto, IPresupuestoFragmentView {

    private TextView presupuesto;
    private TextView ini_presupuesto;
    private TextView fin_presupuesto;
    private Button guardar;
    private Button cancelar;

    ArrayList<TextView> arrayTextview=new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_presupuesto);
        presupuesto=findViewById(R.id.amp_pres_monto);
        ini_presupuesto=findViewById(R.id.amp_pres_fecha_inicio);
        fin_presupuesto=findViewById(R.id.amp_pres_fecha_fin);
        guardar=findViewById(R.id.amp_btn_guardarPresupuesto);
        cancelar=findViewById(R.id.amp_btn_cancelarPresupuesto);

        inicializar();
        lanzarDatePicker1();
        lanzarDatePicker2();
        inicializarArrayTw();
        clickGUardar();
        cancelar();

    }
    private void inicializar() {
        PresupuestoDTO presupuestoDTO = consultarPresupuestoDatos();
        Double presupuesto1=presupuestoDTO.getCantidad();
        presupuesto.setText(presupuesto1.toString());
        ini_presupuesto.setText(presupuestoDTO.getFechaIni());
        fin_presupuesto.setText(presupuestoDTO.getGetFechaFin());


    }

    private void clickGUardar() {

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validarCamposVacios() && validarFecha() && validarMonto()){
                    String fechaActual1 = twoDigits(DatePickerFragment.day) + "-" + twoDigits(DatePickerFragment.month + 1) + "-" + DatePickerFragment.year;
                    String fechaActual2 = twoDigits(DatePickerFragment.day) + "-" + twoDigits(DatePickerFragment.month + 1) + "-" + DatePickerFragment.year;
                    PresupuestoDTO presupuestoDTO = new PresupuestoDTO(Double.valueOf(presupuesto.getText().toString()),ini_presupuesto.getText().toString(),fin_presupuesto.getText().toString());
                    // mostrarToast(adeudoDto.toString());
                    long idAdeudo = registarPresupuesto(presupuestoDTO);
                    if (idAdeudo > 0) {
                        mostrarToast("El registro ha sido correcto.");
                    } else {
                        mostrarToast("Ha ocurrido algun error, verifique no insertar datos repetidos.");
                    }

                    Intent intent;
                    intent = new Intent(ModificarPresupuestoActivity.this, MainActivity.class);
                    startActivity(intent);
                }

            }

        });
    }

    public void inicializarArrayTw(){
        arrayTextview.add(presupuesto);
        arrayTextview.add(ini_presupuesto);
        arrayTextview.add(fin_presupuesto);
    }

    private void cancelar(){
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(ModificarPresupuestoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }
        );
    }


    public boolean validarCamposVacios(){
        boolean estado = true;
        for ( TextView textview : arrayTextview ) {
            if(textview.getText().toString().isEmpty() || textview.getText().toString().equals(" ") ){
                estado=false;
                textview.setError("Este campo no puede ir vacio");
            }
        }
        return  estado;
    }
    public boolean validarFecha(){
        boolean estado=true;
        ValidacionesFechas validacionesFechas=new ValidacionesFechas();
        if (validacionesFechas.validarFechaLimite(fin_presupuesto.getText().toString())==false){
            fin_presupuesto.setError("La fecha es incorrecta,no puede poer una fecha que ya paso");

            estado=false;
        }else if(validacionesFechas.validarFechasIniFin(ini_presupuesto.getText().toString(),fin_presupuesto.getText().toString())==false){
            fin_presupuesto.setError("La fecha es incorrecta, no puede ser menor a la fecha de inicio");
            estado=false;
        }
        return estado;
    }
    private  boolean validarMonto(){
        boolean estado= true;
        if(Double.valueOf(presupuesto.getText().toString()) == 0.0 || presupuesto.getText().toString() == "0"){
            presupuesto.setError("Monto de presupuesto no valido");
            mostrarToast("Monto de presupuesto no valido");
            estado = false;
        }
        return estado;
    }

    private String twoDigits(int n) {
        return (n <= 9) ? ("0" + n) : String.valueOf(n);
    }

    private void showDatePickerDialog1() {
        DatePickerFragment newFragment1 = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                final String diaSeleccionado = twoDigits(dayOfMonth) + "-" + twoDigits(month + 1) + "-" + year;
                fin_presupuesto.setText(diaSeleccionado);
            }
        });
        newFragment1.show(this.getSupportFragmentManager(), "datePicker");
    }
    private void showDatePickerDialog2() {
        DatePickerFragment newFragment2 = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                final String diaSeleccionado = twoDigits(dayOfMonth) + "-" + twoDigits(month + 1) + "-" + year;
                ini_presupuesto.setText(diaSeleccionado);
            }
        });
        newFragment2.show(this.getSupportFragmentManager(), "datePicker");
    }

    public void lanzarDatePicker1() {
        fin_presupuesto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mostrarCalendario();
                showDatePickerDialog1();
            }
        });
    }
    public void lanzarDatePicker2() {
        ini_presupuesto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mostrarCalendario();
                showDatePickerDialog2();
            }
        });
    }



    private void mostrarToast(String mensaje) {
        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
    }


    @Override
    public long registarPresupuesto(PresupuestoDTO presupuestoDTO) {
        IDAOPresupuesto idaoPresupuesto=new DAOPresupuestoIm(ModificarPresupuestoActivity.this);
        return idaoPresupuesto.registarPresupuesto(presupuestoDTO);
    }

    @Override
    public PresupuestoDTO consultarPresupuestoDatos() {
        IPresupuestoFragmentView iPresupuestoFragmentView=new DAOPresupuestoIm(ModificarPresupuestoActivity.this);
        return iPresupuestoFragmentView.consultarPresupuestoDatos();
    }

    @Override
    public PresupuestoDTO consultarPresupuesto() {
        return null;
    }
}


