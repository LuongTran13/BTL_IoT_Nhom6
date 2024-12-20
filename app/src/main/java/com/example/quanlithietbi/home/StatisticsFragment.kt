package com.example.quanlithietbi.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quanlithietbi.R
import android.widget.TextView
data class DeviceStatics(val name: String, val power: String, val voltage: String, val ample: String)

class DeviceStaticAdapter(private val deviceList: List<DeviceStatics>) :
    RecyclerView.Adapter<DeviceStaticAdapter.DeviceViewHolder>() {

    inner class DeviceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val deviceName: TextView = view.findViewById(R.id.deviceName)
        val devicePower: TextView = view.findViewById(R.id.devicePower)
        val deviceVoltage: TextView = view.findViewById(R.id.deviceVoltage)
        val deviceAmple: TextView = view.findViewById(R.id.deviceAmple)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_device_static, parent, false)
        return DeviceViewHolder(view)
    }

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        val device = deviceList[position]
        holder.deviceName.text = device.name
        holder.devicePower.text = device.power
        holder.deviceVoltage.text = device.voltage
        holder.deviceAmple.text = device.ample
    }
    override fun getItemCount(): Int = deviceList.size
}

class StatisticsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_statistics, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        // Tạo danh sách thiết bị hỏng giả lập
        val devices = listOf(
            DeviceStatics("Thiết bị 1", "100W", "220V", "2A"),
        )
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = DeviceStaticAdapter(devices)
        return view
    }
}
