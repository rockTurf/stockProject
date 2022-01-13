package com.srj.web.tools.hanlp;


/**
 * Hanlp工具包内的静态常量
 * */
public class HanlpConstant {

    /**
     * 这个是页码标识
     */
    public static final String[] PAGE_TAG = {"1","2","3","4","5","6","7","8","9","10",
                                             "①","②","③","④","⑤","⑥","⑦","⑧","⑨","⑩",
                                            "一","二","三","四","五","六","七","八","九","十"};
    /**
     * 序号标识
     */
    public static final String[] NUMBER_TAG = {"1","2","3","4","5","6","7","8","9","10",
            "①","②","③","④","⑤","⑥","⑦","⑧","⑨","⑩",
            "一","二","三","四","五","六","七","八","九","十"};


    /**
     * 如果包含如下关键词，逗号或句号分隔的短句会被删掉
     * */
    //这个是详见关键词
    public static final String[] USELESS_WORD_DETAIL = {"详见"};
    //这个是根据关键词
    public static final String[] USELESS_WORD_BASE_ON = {"根据","依据"};
    //这个是如果关键词
    public static final String[] USELESS_WORD_IF = {"若","如"};
}
