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

// Data model cho thiết bị hỏng
data class BrokenDevice(val name: String, val brokenTime: String)

// Adapter để hiển thị danh sách thiết bị hỏng
class BrokenDeviceAdapter(private val deviceList: List<BrokenDevice>) :
    RecyclerView.Adapter<BrokenDeviceAdapter.BrokenDeviceViewHolder>() {

    inner class BrokenDeviceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val deviceName: TextView = view.findViewById(R.id.deviceName)
        val brokenTime: TextView = view.findViewById(R.id.brokenTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrokenDeviceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_broken_device, parent, false)
        return BrokenDeviceViewHolder(view)
    }

    override fun onBindViewHolder(holder: BrokenDeviceViewHolder, position: Int) {
        val device = deviceList[position]
        holder.deviceName.text = "${device.name} đã hỏng"
        holder.brokenTime.text = "Thời gian hỏng: ${device.brokenTime}"
    }

    override fun getItemCount(): Int = deviceList.size
}

class NotificationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_statistics, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)

        // Tạo danh sách thiết bị hỏng giả lập
        val brokenDevices = listOf(
            BrokenDevice("Thiết bị 1", "11:30 11/11/2023"),
        )

        // Cài đặt RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = BrokenDeviceAdapter(brokenDevices)

        return view
    }
}
