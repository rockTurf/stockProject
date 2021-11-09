package com.srj.web.stock.tool;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.srj.common.utils.UUIDUtils;
import com.srj.web.stock.mapper.StockMapper;
import com.srj.web.stock.model.Stock;
import com.srj.web.util.StringUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class ExcelModelStockListener extends AnalysisEventListener<Stock> {

    @Resource
    public StockMapper mapper;
    public ExcelModelStockListener(StockMapper mapper){
        this.mapper = mapper;
    }


    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;
    private String DATE = "";//当前期（月和日）
    List<Stock> list = new ArrayList<Stock>();
    private static int count = 1;
    @Override
    public void invoke(Stock data, AnalysisContext context) {
        System.out.println("解析到一条数据:{ "+ data.toString() +" }");
        list.add(data);
        count ++;
        if (list.size() >= BATCH_COUNT) {
            //先拿到具体的日期
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
        for(Stock t:list){
            String code = t.getCode();
            if(StringUtil.isNumeric(code)){
                Long id = Long.parseLong(UUIDUtils.getRandomInteger(12));
                t.setId(id);
                //校验
                Stock temp = new Stock();
                temp.setCode(code);
                Stock one = mapper.selectOne(temp);

                if(one==null){
                    mapper.insert(t);
                }else{
                    t.setId(one.getId());//更新
                    mapper.updateByPrimaryKey(t);
                }
            }
        }
        System.out.println("{ "+ count +" }条数据，开始存储数据库！" + list.size());
        System.out.println("存储数据库成功！");
    }


}
