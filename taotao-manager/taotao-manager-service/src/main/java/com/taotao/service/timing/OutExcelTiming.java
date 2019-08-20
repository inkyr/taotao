package com.taotao.service.timing;

import com.taotao.mapper.OrderItemMapper;
import com.taotao.pojo.TbOrderItem;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Component
public class OutExcelTiming {

    @Autowired
    private OrderItemMapper orderItemMapper;

    //@Scheduled(cron = "0 0/1 15,* * * ?")
    public void OutExcel(){
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet sheet = hssfWorkbook.createSheet();
        int count = 0;
        HSSFRow row = sheet.createRow(count);
        row.createCell(0).setCellValue("ID");
        row.createCell(1).setCellValue("商品ID");
        row.createCell(2).setCellValue("订单ID");
        row.createCell(3).setCellValue("商品数量");
        row.createCell(4).setCellValue("总金额");

        List<TbOrderItem> allOrderItem = orderItemMapper.findAllOrderItem();
        for (TbOrderItem tbOrderItem : allOrderItem) {
            count++;
            row = sheet.createRow(count);
            row.createCell(0).setCellValue(tbOrderItem.getId());
            row.createCell(1).setCellValue(tbOrderItem.getItemId());
            row.createCell(2).setCellValue(tbOrderItem.getOrderId());
            row.createCell(3).setCellValue(tbOrderItem.getNum());
            row.createCell(4).setCellValue(tbOrderItem.getPrice());
        }
        try {
            //Date date = new Date();
            FileOutputStream fileOutputStream = new FileOutputStream("D:/order.xls");
            hssfWorkbook.write(fileOutputStream);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
