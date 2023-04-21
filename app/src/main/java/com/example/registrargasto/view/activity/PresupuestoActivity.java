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
import com.example.registrargasto.entidades.PresupuestoDTO;
import com.example.registrargasto.view.dialog.DatePickerFragment;

public class PresupuestoActivity extends AppCompatActivity implements IDAOPresupuesto {
    private TextView presupuesto;
    private TextView ini_presupuesto;
    private TextView fin_presupuesto;
    private Button guardar;
    private Button cancelar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presupuesto);
        presupuesto=findViewById(R.id.ac_pres_monto);
        ini_presupuesto=findViewById(R.id.ac_pres_fecha_inicio);
        fin_presupuesto=findViewById(R.id.ac_pres_fecha_fin);
        guardar=findViewById(R.id.ac_btn_guardarPresupuesto);
        cancelar=findViewById(R.id.ac_btn_cancelarPresupuesto);
        lanzarDatePicker1();
        lanzarDatePicker2();
        clickGUardar();
        cancelar();
    }

    private void clickGUardar() {

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

                limpiarCampos();
                Intent intent;
                intent = new Intent(PresupuestoActivity.this, MainActivity.class);
                startActivity(intent);
            }

        });
    }

    private void cancelar(){
        cancelar.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  limpiarCampos();
                  Intent intent;
                  intent = new Intent(PresupuestoActivity.this, MainActivity.class);
                  startActivity(intent);
              }
          }
        );
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

    private void limpiarCampos() {
        ini_presupuesto.setText("");
        fin_presupuesto.setText("");
        presupuesto.setText("");
    }

    private void mostrarToast(String mensaje) {
        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
    }


    @Override
    public long registarPresupuesto(PresupuestoDTO presupuestoDTO) {
        IDAOPresupuesto idaoPresupuesto=new DAOPresupuestoIm(PresupuestoActivity.this);
        return idaoPresupuesto.registarPresupuesto(presupuestoDTO);
    }
}