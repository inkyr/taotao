package com.taotao.mapper;

import com.taotao.common.pojo.DataViewResult;
import com.taotao.pojo.TbOrderItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderItemMapper {
    @Insert("INSERT INTO tborderitem(id, itemId, orderId, num, title, price, totalFee, picPath) VALUE (#{id},#{itemId},#{orderId},#{num},#{title},#{price},#{totalFee},#{picPath})")
    void addOrderItem(TbOrderItem orderItem);

    @Select("SELECT tmo.`month` as `month`, SUM(IFNULL(tor.payment,0)) as totalMoney FROM tbmonth tmo LEFT JOIN tborder tor ON tmo.`month` = DATE_FORMAT(tor.createTime,'%m') and DATE_FORMAT(tor.createTime,'%Y') = #{year} GROUP BY tmo.`month`")
    List<DataViewResult> getDataByYear(Long year);
}