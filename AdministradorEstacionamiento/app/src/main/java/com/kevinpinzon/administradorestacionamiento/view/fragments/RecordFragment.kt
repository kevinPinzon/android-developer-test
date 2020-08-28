package com.kevinpinzon.administradorestacionamiento.view.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.Observer
import com.kevinpinzon.administradorestacionamiento.R
import com.kevinpinzon.administradorestacionamiento.data.model.Car
import com.kevinpinzon.administradorestacionamiento.data.model.Register
import com.kevinpinzon.administradorestacionamiento.viewmodel.CarViewModel
import com.kevinpinzon.administradorestacionamiento.viewmodel.RegisterViewModel
import kotlinx.android.synthetic.main.fragment_cars.*
import kotlinx.android.synthetic.main.fragment_record.*
import java.text.SimpleDateFormat
import java.time.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class RecordFragment : Fragment() {

    lateinit var registerViewModel: RegisterViewModel
    private lateinit var carViewModel: CarViewModel

    val placas: ArrayList<String> = ArrayList()
    val registrosInGlobal: ArrayList<Register> = ArrayList()
    val carsInGlobal: ArrayList<Car> = ArrayList()
    var registroToOut = Register(0,"","","")
    var currentCar = Car("","", 0)

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        registerViewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)
        carViewModel = ViewModelProviders.of(this).get(CarViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_record, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getRegisters()
        getCars()

        var modelDialogIn = AlertDialog.Builder(context)
        val dialogViewIn = layoutInflater.inflate(R.layout.dialog_inregister, null)

        modelDialogIn.setView(dialogViewIn)
        var dialogInRegister = modelDialogIn.create()

        card_view_in.setOnClickListener {
            dialogInRegister.show()

            val saveTextV = dialogViewIn.findViewById<TextView>(R.id.textView_save)
            val cancelTextV = dialogViewIn.findViewById<TextView>(R.id.textView_cancel)

            val placaEditText = dialogViewIn.findViewById<EditText>(R.id.editText_placa)

            cancelTextV.setOnClickListener {
                placaEditText.text.clear()
                dialogInRegister.cancel()
            }

            saveTextV.setOnClickListener {

                if( placaEditText.text.length > 0){
                    registerIn(placaEditText.text.toString(), dialogInRegister)
                    placaEditText.text.clear()
                }else{
                    placaEditText.error = "Debe agregar numero de placa"
                }
            }
        }

        val dialogViewOut = layoutInflater.inflate(R.layout.dialog_outregister, null)

        var modelDialogOut = AlertDialog.Builder(context)
        modelDialogOut.setView(dialogViewOut)
        var dialogOutRegister = modelDialogOut.create()

        card_view_out.setOnClickListener {

            if(registrosInGlobal.isEmpty()){
                var builder = AlertDialog.Builder(context)
                builder.setTitle("Error")
                builder.setMessage("No hay ninguna entrada registrado.")
                builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                    println("TEST- finish register out")
                }
                builder.show()
            }else{
                val saveTextV = dialogViewOut.findViewById<TextView>(R.id.textView_save)
                val cancelTextV = dialogViewOut.findViewById<TextView>(R.id.textView_cancel)
                val placaSpinner = dialogViewOut.findViewById<Spinner>(R.id.spinner_placa)

                var adapter = ArrayAdapter(dialogOutRegister.context,
                    android.R.layout.simple_spinner_item,placas)

                placaSpinner.adapter = adapter

                placaSpinner.onItemSelectedListener = object :
                    AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>,
                                                view: View, position: Int, id: Long) {
                        registroToOut = registrosInGlobal[position]
                        println("TEST-"+registroToOut)
                    }

                    override fun onNothingSelected(parent: AdapterView<*>) {
                        // write code to perform some action
                    }
                }

                cancelTextV.setOnClickListener {
                    dialogOutRegister.cancel()
                }

                saveTextV.setOnClickListener {
                    registerOut(registroToOut, dialogOutRegister)
                }

                dialogOutRegister.show()
            }
        }

        btnStartMonth.setOnClickListener{
            registerViewModel.startMonth()
            carViewModel.startMonthToResident()

            var builder = AlertDialog.Builder(context)
            builder.setTitle("Se ha comenzado el mes correctamente")
            builder.setMessage("Los registros han sido limpiados y el tiempo acumulado de los vehiculos de residentes es 0.")
            builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                println("TEST- finish start month")
            }
            builder.show()

        }

        btnResidentPays.setOnClickListener{

        }
    }

    private fun registerOut(register:Register, dialog:AlertDialog) {

        val date = getCurrentDateTime()
        val dateOutString = date.toString("dd/MM/yyyy HH:mm:ss")
        val dateTimeIn: LocalDateTime = strToDateTime(register.timeIn.toString())
        val dateTimeOut: LocalDateTime = strToDateTime(dateOutString)

        var totalTime: Long = calculateTime(dateTimeIn, dateTimeOut)
        registerViewModel.registerOut(Register(register.id,register.placa, register.timeIn, dateOutString, calculateTotalToPayNoResi(totalTime)))

        println("TEST- total to pay: $"+calculateTotalToPayNoResi(totalTime))
        println("TEST- totalTime: "+totalTime)
        println("TEST- register.placa: "+register.placa)

        carViewModel.updateTotalTime(register.placa, totalTime)

        carViewModel.getCarByPlaca(register.placa).observe(this, Observer{

            dialog.cancel()
            var builder = AlertDialog.Builder(context)
            builder.setTitle("Registro de salida se ha guardado exitosamente")

            if (it != null) {
                currentCar = it
                println("TEST- currentCar: " + currentCar)

                if(currentCar.type.equals("residente")){
                    builder.setMessage("Se ha actualizado el tiempo acumulado de la estancia total del vehiculo residente.")
                }else if(currentCar.type.equals("oficial")){
                    builder.setMessage("Se ha guardado la estancia del vehiculo oficial.")
                }

            }else{
                builder.setMessage("Importe a pagar:" + calculateTotalToPayNoResi(totalTime))
            }

            builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                println("TEST- finish register out")
            }
            builder.show()

        })

    }

    private fun registerIn(placa:String, dialog:AlertDialog) {

        val date = getCurrentDateTime()
        val dateInString = date.toString("dd/MM/yyyy HH:mm:ss")

        registerViewModel.registerIn(Register(0,placa, dateInString, "N"))

        dialog.cancel()

        var builder = AlertDialog.Builder(context)
        builder.setTitle("Registro de entrada se ha guardado exitosamente")
        builder.setMessage("Se ha guardado el registro del vehiculo.")
        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            println("TEST- finish register out")
        }
        builder.show()
    }

    private fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    private fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

    private fun strToDateTime(dateTimeStr: String): LocalDateTime{

        var dateAndTimeIn = dateTimeStr.split(" ")

        var dateInStr = dateAndTimeIn.get(0).split("/")
        var timeInStr = dateAndTimeIn.get(1).split(":")

        val year    = dateInStr[2].toInt()
        val month   = dateInStr[1].toInt()
        val day     = dateInStr[0].toInt()

        val hour    = timeInStr[0].toInt()
        val minute  = timeInStr[1].toInt()
        val seconds = timeInStr[2].toInt()

        val dateTime = LocalDateTime.of(year, month, day, hour, minute, seconds)

        println("TEST-"+dateTimeStr)
        println("TEST-"+dateTime)

        return dateTime
    }

    private fun calculateTime(dateTimeI: LocalDateTime, dateTimeO: LocalDateTime): Long{
        return Duration.between(dateTimeI, dateTimeO).toMinutes()
    }

    private fun calculateTotalToPayNoResi(totalTime: Long): Double{
        return totalTime * 0.5
    }

    private fun getRegisters(){

        var observer = Observer<List<Register>> { registrosIn ->

            placas.clear()
            registrosInGlobal.clear()

            for (register in registrosIn) {
                println("TEST- register:"+register)
                placas.add(register.placa)
                registrosInGlobal.add(register)
            }

        }

        registerViewModel.allRegisters.observe(this, observer)
        //registerViewModel.allPlacasIn.observe(this, observer)
    }

    private fun getCars() {
        val observer = Observer<List<Car>> { cars ->
            carsInGlobal.clear()

            for (car in cars) {
                println("TEST- cars:"+car)
                carsInGlobal.add(car)
            }

        }
        carViewModel.allCars.observe(this, observer)

    }
}
