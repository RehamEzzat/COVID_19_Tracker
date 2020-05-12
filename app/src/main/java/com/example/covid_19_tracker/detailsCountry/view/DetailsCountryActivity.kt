package com.example.covid_19_tracker.detailsCountry.view


import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.covid_19_tracker.R
import com.example.covid_19_tracker.detailsCountry.FModel.CountryStatistic
import com.example.covid_19_tracker.network.RetrofitClient
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.android.synthetic.main.activity_details_country.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailsCountryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_country)

        val ret = RetrofitClient.getInstance().getRetrofitClientInterface()
        val call = ret.getDetailsCountry("Egypt",30)

        var lineChartDownFill = this.lineChartView
        var entryArrayList: ArrayList<Entry> = ArrayList()
        var lineDataSet  = LineDataSet(entryArrayList, "This is y bill")
        var iLineDataSetArrayList: ArrayList<ILineDataSet> = ArrayList()



        lineChartDownFill.setTouchEnabled(false);
        lineChartDownFill.setDragEnabled(true);
        lineChartDownFill.setScaleEnabled(true);
        lineChartDownFill.setPinchZoom(false);
        lineChartDownFill.setDrawGridBackground(false);
        lineChartDownFill.setMaxHighlightDistance(200F);
        lineChartDownFill.setViewPortOffsets(0F, 0F, 0F, 0F);

        val description = Description()
        description.setText("Days Data")

        lineChartDownFill.description = description






        //set the gradiant then the above draw fill color will be replace

        //set the gradiant then the above draw fill color will be replace
        val drawable = ContextCompat.getDrawable(this, R.drawable.gradient)
        lineDataSet.fillDrawable = drawable

        //set legend disable or enable to hide {the left down corner name of graph}

        //set legend disable or enable to hide {the left down corner name of graph}
        val legend = lineChartDownFill.legend
        legend.isEnabled = true

        //to remove the cricle from the graph



        call.enqueue(object : Callback<CountryStatistic>{
            override fun onFailure(call: Call<CountryStatistic>, t: Throwable) {
                Log.e("result",t.message)
            }

            override fun onResponse(
                call: Call<CountryStatistic>,
                response: Response<CountryStatistic>
            ) {
                var x =1F
                var y = 1
                var items = response.body()!!.timeline?.cases?.toSortedMap()
                items?.forEach {
                        k, v ->
                    println("$k = $v")
                   entryArrayList.add(Entry(x, v.toFloat()))
                    x++
                    y++
                    println("$x = $y")

                }

                 lineDataSet = LineDataSet(entryArrayList, "This is y bill")
                //to remove the cricle from the graph
                lineDataSet.setDrawCircles(false)
                iLineDataSetArrayList.add(lineDataSet)
                val lineData = LineData(iLineDataSetArrayList)
                lineData.setValueTextSize(13f)
                lineData.setValueTextColor(Color.BLACK)


                lineDataSet.lineWidth = 5f
                lineDataSet.color = Color.GRAY
                // lineDataSet.setCircleColorHole(Color.GREEN)
                lineDataSet.setCircleColor(R.color.colorAccent)
                lineDataSet.highLightColor = Color.RED
                lineDataSet.setDrawValues(false)
                lineDataSet.circleRadius = 10f
                lineDataSet.setCircleColor(Color.YELLOW)

                //to make the smooth line as the graph is adrapt change so smooth curve

                //to make the smooth line as the graph is adrapt change so smooth curve
                lineDataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
                //to enable the cubic density : if 1 then it will be sharp curve
                //to enable the cubic density : if 1 then it will be sharp curve
                lineDataSet.cubicIntensity = 0.2f

                //to fill the below of smooth line in graph

                //to fill the below of smooth line in graph
                lineDataSet.setDrawFilled(true)
                lineDataSet.fillColor = Color.BLACK
                //set the transparency
                //set the transparency
                lineDataSet.fillAlpha = 80



                lineChartDownFill.data = lineData

                lineChartDownFill.invalidate()

            }

        })




        //lineDataSet.setColor(ColorTemplate.COLORFUL_COLORS);


        //lineDataSet.setColor(ColorTemplate.COLORFUL_COLORS);



        //LineData is the data accord

        //LineData is the data accord

    }
}
