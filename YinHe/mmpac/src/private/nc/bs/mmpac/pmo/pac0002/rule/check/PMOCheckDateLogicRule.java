/**
 * 
 */
package nc.bs.mmpac.pmo.pac0002.rule.check;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.mmpac.pmo.pac0002.entity.PMOAggVO;

/**
 * 日期是否符合自然逻辑大小顺序，日期自然逻辑校验规则，保存前-不再校验
 * @author yuxx
 * @created at 2016-8-8,下午8:23:46
 *
 */
public class PMOCheckDateLogicRule implements IRule<PMOAggVO> {

    @Override
    public void process(PMOAggVO[] vos) {
    	// @edit by yuxx at 2016-8-8,下午8:24:39 银河项目不再进行此校验
/*        if (ArrayUtil.isNull(vos)) {
            return;
        }
        for (PMOAggVO aggvo : vos) {
            PMOItemVO[] items = aggvo.getChildrenVO();
            if (!ArrayUtil.isNull(items)) {
                for (PMOItemVO item : items) {
                    if (MMValueCheck.isEmpty(item.getTplanstarttime())) {
                        ExceptionUtils.wrappBusinessException(PMOConstLang.getMSG_RULE_RESOLVEPLANSTARTTIME_NOT_NULL());
                    }
                    if (MMValueCheck.isEmpty(item.getTplanendtime())) {
                        ExceptionUtils.wrappBusinessException(PMOConstLang.getMSG_RULE_RESOLVEPLANENDTIME_NOT_NULL());
                    }
                    if (item.getTplanstarttime().after(item.getTplanendtime())) {
                        ExceptionUtils.wrappBusinessException(PMOConstLang.getMSG_RULE_START_END_RELATION());
                    }
                    if (item.getTwillendtime() != null) {
                        if (item.getTplanstarttime().after(item.getTwillendtime().getDate())) {
                            ExceptionUtils.wrappBusinessException(PMOConstLang.getMSG_RULE_WILL_START_RELATION());
                        }
                    }
                }
            }
        }*/
    }
}

