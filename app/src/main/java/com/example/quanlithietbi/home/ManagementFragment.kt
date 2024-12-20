package com.example.quanlithietbi.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quanlithietbi.R

data class DeviceManage(val name: String, var isOn: Boolean)
class ManagementFragment : Fragment() {

    // Adapter cho RecyclerView
    inner class DeviceManagementAdapter(private val deviceList: List<DeviceManage>) : RecyclerView.Adapter<DeviceManagementAdapter.DeviceViewHolder>() {

        inner class DeviceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val deviceName: TextView = view.findViewById(R.id.deviceName)
            val deviceSwitch: Switch = view.findViewById(R.id.deviceSwitch)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_device_management, parent, false)
            return DeviceViewHolder(view)
        }

        override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
            val device = deviceList[position]
            holder.deviceName.text = device.name
            holder.deviceSwitch.isChecked = device.isOn

            // Lắng nghe sự thay đổi trạng thái của Switch
            holder.deviceSwitch.setOnCheckedChangeListener { _, isChecked ->
                device.isOn = isChecked // Cập nhật trạng thái thiết bị khi bật/tắt
            }
        }

        override fun getItemCount(): Int = deviceList.size
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_management, container, false)

        // Tạo danh sách thiết bị giả lập
        val devices = listOf(
            DeviceManage("Thiết bị 1", false),
        )

        // Cài đặt RecyclerView
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = DeviceManagementAdapter(devices)

        return view
    }
}
