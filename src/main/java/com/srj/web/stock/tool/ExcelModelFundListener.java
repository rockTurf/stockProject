package com.srj.web.stock.tool;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.srj.web.stock.mapper.FundMapper;
import com.srj.web.stock.model.Fund;

import javax.annotation.Resource;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class ExcelModelFundListener extends AnalysisEventListener<Fund> {

    @Resource
    public FundMapper mapper;
    public ExcelModelFundListener(FundMapper mapper){
        this.mapper = mapper;
    }


    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    List<Fund> list = new ArrayList<Fund>();
    private static int count = 1;
    @Override
    public void invoke(Fund data, AnalysisContext context) {
        System.out.println("解析到一条数据:{ "+ data.toString() +" }");
        list.add(data);
        count ++;
        if (list.size() >= BATCH_COUNT) {
            saveData( count );
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData( count );
        System.out.println("所有数据解析完成！");
        System.out.println(" count ：" + count);
    }

    /**
     * 加上存储数据库
     */
    private void saveData(int count){
        for(Fund t:list){
            //找一下日期是否有重复
            Fund item = new Fund();
            item.setDataDate(t.getDataDate());
            List<Fund> list = mapper.select(item);
            if(list.size()==0){//无重复再插入
                mapper.insert(t);
            }
        }
        System.out.println("{ "+ count +" }条数据，开始存储数据库！" + list.size());
        System.out.println("存储数据库成功！");
    }
}
