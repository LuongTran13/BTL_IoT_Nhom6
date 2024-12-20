package com.example.quanlithietbi.home

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quanlithietbi.R

// Data model cho thiết bị
data class Device(val name: String, val status: String)

// Adapter để hiển thị danh sách
class DeviceAdapter(private val deviceList: List<Device>) :
    RecyclerView.Adapter<DeviceAdapter.DeviceViewHolder>() {

    inner class DeviceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val deviceName: TextView = view.findViewById(R.id.deviceName)
        val deviceStatus: TextView = view.findViewById(R.id.deviceStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_device, parent, false)
        return DeviceViewHolder(view)
    }

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        val device = deviceList[position]
        holder.deviceName.text = device.name
        holder.deviceStatus.text = device.status

        // Đổi màu nền dựa trên trạng thái
        val statusColor = when (device.status) {
            "Bình thường" -> ContextCompat.getColor(holder.itemView.context, R.color.status_normal)
            "Hỏng" -> ContextCompat.getColor(holder.itemView.context, R.color.status_error)
            "Tắt" -> ContextCompat.getColor(holder.itemView.context, R.color.status_off)
            else -> ContextCompat.getColor(holder.itemView.context, R.color.white)
        }

        // Áp dụng màu cho nền với bo góc
        holder.deviceStatus.setBackgroundResource(R.drawable.status_background)
        holder.deviceStatus.backgroundTintList = ColorStateList.valueOf(statusColor)
    }

    override fun getItemCount(): Int = deviceList.size
}
class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)

        // Tạo danh sách giả lập
        val devices = listOf(
            Device("Thiết bị 1", "Bình thường"),
        )

        // Cài đặt RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = DeviceAdapter(devices)

        return view
    }
}
