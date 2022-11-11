package com.trainee.appinventiv.eccoscreens.ui

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import com.trainee.appinventiv.eccoscreens.R
import com.trainee.appinventiv.eccoscreens.databinding.FragmentProvideBasicDetailsBinding


class ProvideBasicDetailsFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var binding : FragmentProvideBasicDetailsBinding
    private var currentGender = 0

    companion object{
        var selectedCountryCode : String = "EU"
        var selectedFootedSize : String = "-1"

    }


    private val countryList = listOf("EU", "US")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProvideBasicDetailsBinding.inflate(inflater, container, false )
        return  binding.root
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("shubham","viewcreated")
        genderDropDown()
        binding.spinnerGender.onItemSelectedListener = this

        binding.spinnerGender.setOnTouchListener { view, motionEvent ->
            if(motionEvent.action == MotionEvent.ACTION_UP){


            }
            false
        }

        setComposeView()


        binding.tvShowSizeList.setOnClickListener {
            binding.composeView.visibility = View.VISIBLE
            val anim = ObjectAnimator.ofFloat(binding.composeView, "alpha", 0.25f, 1f, 1f)
            anim.setDuration(2000)
            anim.interpolator = AccelerateInterpolator()

            anim.start()
        }


    }


    private fun setComposeView() {
        binding.composeView.setContent {

            val sizeList = remember {
                mutableStateListOf<String>("1","2","3","4")
            }

            val rememberCountryCode = remember {
                mutableStateOf("-1")
            }
            val rememberFootSize = remember {
                mutableStateOf("-1")
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
            ) {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    border = BorderStroke(width = 2.dp, color = Color.Gray)

                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .padding(top = 20.dp, bottom = 20.dp, start = 20.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        CountryNameForSize(countryList = countryList, rememberCountryCode){ countryCode ->
                            selectedCountryCode = countryCode
                            updateList(sizeList, countryCode)
                            rememberCountryCode.value = countryCode
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        SizeCharRow(sizeList, rememberFootSize){ footSize ->
                            selectedFootedSize = footSize
                            rememberFootSize.value = footSize
                        }
                    }
                }
            }
        }
    }

    private fun updateList(sizeList: MutableList<String>, countryCode: String) {
        when(countryCode){
            "EU" -> {
                sizeList.clear()
                sizeList.apply {
                    add("32")
                    add("34")
                    add("36")
                    add("38")
                    add("40")
                }
            }
            "US" -> {
                sizeList.clear()
                sizeList.apply {
                    add("42")
                    add("44")
                    add("46")
                    add("48")
                    add("50")
                }
            }
        }
    }


    private fun genderDropDown() {
        val feelings = resources.getStringArray(R.array.feelings)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.spinner_gender,R.id.textView2, feelings)
        arrayAdapter.setDropDownViewResource(R.layout.spinner_gender)
        binding.spinnerGender.adapter = arrayAdapter
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

        Log.d("spinnerselect",binding.spinnerGender.selectedItemId.toString())

//            val layout:TextView=requireView().findViewById(R.id.textView2)
//            layout.setBackgroundColor(android.graphics.Color.parseColor("#000000"))

        Log.d("shubham","$p2 and ${p0?.selectedView?.isSelected}")
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }




}


@Composable
fun SizeCharRow(sizeList: List<String>, rememberFoot: MutableState<String>, footSize: (String)-> Unit){
    
    LazyRow(contentPadding = PaddingValues(end = 5.dp)){
        items(sizeList) {size->
            SizeViewDisplay(size, rememberFoot,footSize)
        }
    }
    
}

@Composable
fun SizeViewDisplay(size : String, rememberFoot: MutableState<String>, footSize: (String) -> Unit) {

    Card(
        elevation = 4.dp,
        modifier = Modifier
            .size(40.dp),
        backgroundColor = if(rememberFoot.value == size) Color.Black else Color.White,
        shape = RoundedCornerShape(50.dp),
        border = BorderStroke(width = 1.dp, color = Color.Gray),

    ) {
        Text(
            text = size,
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentHeight()
                .clickable { footSize(size) },
            color = if(rememberFoot.value == size) Color.White else Color.Black,
            textAlign = TextAlign.Center,
            fontSize = 12.sp
        )
    }
}



@Composable
fun CountryNameForSize(countryList: List<String>, rememberCountryCode : MutableState<String>, countryCode:(String)->Unit){
    LazyRow(){
        items(countryList){ name->
            CountryName(countryName = name,rememberCountryCode, countryCode)
        }
    }
}



@Composable
fun CountryName(countryName : String, rememberCountryCode: MutableState<String>, countryCode: (String) -> Unit){

    Card(
        modifier = Modifier
            .size(51.dp, 34.dp),
        backgroundColor = if(rememberCountryCode.value == countryName) Color.Black else Color.White,
        shape = RoundedCornerShape(50.dp)
    ) {
        Text(
            text = countryName,
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .clickable {
                    countryCode(countryName)
                }
            ,
            fontSize = 14.sp,
            color = if(rememberCountryCode.value == countryName) Color.White else Color.Black,
            textAlign = TextAlign.Center,
        )
    }


}
