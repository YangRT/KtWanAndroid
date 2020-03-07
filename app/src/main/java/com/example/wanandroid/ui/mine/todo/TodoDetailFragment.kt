package com.example.wanandroid.ui.mine.todo

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.wanandroid.R
import com.example.wanandroid.databinding.FragmentTodoDetailBinding
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * @program: WanAndroid
 *
 * @description: todo详情页 ui
 *
 * @author: YangRT
 *
 * @create: 2020-03-07 16:34
 **/

class TodoDetailFragment(val status:String):Fragment(), Observer<Any>,View.OnClickListener {

    private var viewModel:TodoDetailViewModel? = null
    private var eventId:Int? = -1
    private var choseTypeId = -1
    private var event:Event? = null
    private lateinit var binding:FragmentTodoDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_todo_detail,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycle.addObserver(viewModel())
        viewModel().updateResponse.observe(this, Observer { updateResponse(it) })
        viewModel().addResponse.observe(this, Observer { addResponse(it) })
        initView()
    }

    private fun viewModel(): TodoDetailViewModel {
        if (viewModel == null){
            viewModel = ViewModelProviders.of(this).get(TodoDetailViewModel::class.java)
        }
        return viewModel as TodoDetailViewModel
    }

    private fun initView(){
        binding.detailBt.setOnClickListener(this)
        binding.detailType.setOnCheckedChangeListener { _, checkedId -> choseTypeId = checkedId }
        if(status != "add"){
            setUnEditable()
            eventId = arguments?.getInt("id")
            event = arguments?.getSerializable("detail") as Event
        }
        if (event != null){
            setDefaultEvent()
        }
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.detail_bt){
            if(binding.detailTitle.text.toString() == "" || binding.detailContent.text.toString() == "" || binding.detailTime.text.toString() == ""){
                Toast.makeText(getContext(),"请填齐信息！",Toast.LENGTH_SHORT).show();
            }else{
                if (checkDateFormat(binding.detailTime.text.toString())){
                    setUnEditable()
                    val event = Event()
                    event.title = binding.detailTitle.text.toString()
                    event.content = binding.detailContent.text.toString()
                    event.date = binding.detailTime.text.toString()
                    when(choseTypeId){
                        R.id.detail_type_life -> event.type = Type.LIFE
                        R.id.detail_type_work -> event.type = Type.WORK
                        R.id.detail_type_learn -> event.type = Type.LEARN
                        R.id.detail_type_other -> event.type = Type.OTHER
                    }
                    if(status == "add"){
                        viewModel().addEvent(event)
                    }else {
                        eventId?.let { viewModel().updateEvent(it,event) }
                    }
                }
            }
        }
    }

    private fun checkDateFormat(date:String):Boolean{
        val strs = date.split("-");
        if(strs.size == 3 && strs[0].length == 4 && strs[1].length == 2 && strs[2].length == 2){
            val dateNow = Date()
            val df = SimpleDateFormat("yyyy-MM-dd")
            val strNow = df.format(dateNow)
            if(date == strNow){
                return true
            }
            return try {
                val now = df.parse(strNow)
                val set = df.parse(date)
                if(set.time < now.time){
                    Toast.makeText(context,"预计完成日期不能小于当前日期",Toast.LENGTH_SHORT).show()
                    false
                }else {
                    true
                }
            }catch (e:ParseException){
                e.printStackTrace()
                Toast.makeText(getContext(),"预计完成日期格式错误",Toast.LENGTH_SHORT).show()
                false
            }
        }
        Toast.makeText(getContext(),"预计完成日期格式错误",Toast.LENGTH_SHORT).show()
        return false
    }

    private fun setUnEditable(){
        binding.detailBt.visibility = View.GONE
        binding.detailContent.inputType = InputType.TYPE_NULL
        binding.detailTitle.inputType = InputType.TYPE_NULL
        binding.detailTime.inputType = InputType.TYPE_NULL
        for (i in 0..binding.detailType.childCount) {
            binding.detailType.getChildAt(i).isEnabled = false
        }
    }

    private fun setEditable(){
        binding.detailBt.visibility = View.VISIBLE
        binding.detailContent.inputType = InputType.TYPE_CLASS_TEXT
        binding.detailTitle.inputType = InputType.TYPE_CLASS_TEXT
        binding.detailTime.inputType = InputType.TYPE_CLASS_TEXT
        for (i in 0..binding.detailType.childCount) {
            binding.detailType.getChildAt(i).isEnabled = true
        }
    }

    private fun setDefaultEvent(){
        event?.let {
            binding.detailTime.setText(it.date)
            binding.detailTitle.setText(it.title)
            binding.detailContent.setText(it.content)
            when(it.type){
                Type.LIFE ->
                    binding.detailTypeLife.isChecked = true
                Type.WORK ->
                    binding.detailTypeWork.isChecked = true
                Type.LEARN ->
                    binding.detailTypeLearn.isChecked = true
                Type.OTHER ->
                    binding.detailTypeOther.isChecked = true
            }
        }

    }

    private fun addResponse(result:Boolean){
        if (result){
            Toast.makeText(context,"添加成功！",Toast.LENGTH_SHORT).show()
            activity?.finish()
        }else{
            setEditable()
            Toast.makeText(context,"添加失败！",Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateResponse(result:Boolean){
        if (result){
            Toast.makeText(context,"修改成功！",Toast.LENGTH_SHORT).show()
        }else{
            setEditable()
            Toast.makeText(context,"修改失败！",Toast.LENGTH_SHORT).show()
        }
    }


    override fun onChanged(t: Any?) {

    }
}