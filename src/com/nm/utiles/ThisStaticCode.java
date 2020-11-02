package com.nm.utiles;

/**
 * 状态码
 * @author 容学斌
 */
public class ThisStaticCode {

    /**
     * NO_CODE=1;删除的,不可以用的
     * OK_CODE=0  可用的
     */
    public  static final   Integer  NO_CODE=1,OK_CODE=0;

    /**
     * <option value="0" ${expense.expenseState=='0'?'selected':'' } >未提交审核</option>
     * 	<option value="1" ${expense.expenseState=='1'?'selected':'' }>已提交审核</option>
     * 	<option value="2" ${expense.expenseState=='2'?'selected':'' }>经理审核通过</option>
     * 	<option value="3" ${expense.expenseState=='3'?'selected':'' }>财务审核通过</option>
     * 	<option value="-1" ${ea.expenseState=='-1'?'selected':''}>经理审核未通过</option>
     * 	<option value="-2" ${ea.expenseState=='-2'?'selected':''}>财务审核未通过</option>
     */
    public  static final  String NO_SUBMIT = "0",OK_SUBMIT = "1",
            OK_MANAGER = "2",NO_MANAGER = "-1",
            OK_FINANCE = "3",NO_FINANCE = "-2";
}
