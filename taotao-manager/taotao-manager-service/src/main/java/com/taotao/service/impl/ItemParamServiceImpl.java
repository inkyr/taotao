package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.ItemMapper;
import com.taotao.mapper.ItemParamItemMapper;
import com.taotao.mapper.ItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ItemParamServiceImpl implements ItemParamService {

    @Autowired
    private ItemParamMapper itemParamMapper;

    @Autowired
    private ItemParamItemMapper itemParamItemMapper;

    @Override
    public TaotaoResult getItemParamByCid(Long itemCatId) {
        TbItemParam tbItemParam = itemParamMapper.findItemParamByCatId(itemCatId);
        if (tbItemParam != null) {
            return TaotaoResult.ok(tbItemParam);
        }
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult addItemParam(TbItemParam tbItemParam) {
        Date time = new Date();
        tbItemParam.setCreated(time);
        tbItemParam.setUpdated(time);
        itemParamMapper.addItemParam(tbItemParam);
        return TaotaoResult.ok();
    }

    @Override
    public EasyUIResult findParamList(Integer page, Integer rows) {

        PageHelper.startPage(page, rows);
        List<TbItemParam> tbItemParams = itemParamMapper.findParamAll();
        EasyUIResult result = new EasyUIResult();
        result.setTotal(new PageInfo<TbItemParam>().getTotal());
        result.setRows(tbItemParams);
        return result;
    }

    @Override
    public TaotaoResult deleteParam(Long[] ids) {
        int a = itemParamMapper.deleteParam(ids);
        if(a > 0){
            return TaotaoResult.ok();
        }
        return null;
    }

    @Override
    public String getItemParamByItemId(Long itemId) {
        TbItemParamItem tbItemParamItem = itemParamItemMapper.findItemParamByItemId(itemId);
        String paramData = tbItemParamItem.getParamData();

        //生成html
        // 把规格参数json数据转换成java对象
        List<Map> jsonList = JsonUtils.jsonToList(paramData, Map.class);
        StringBuffer sb = new StringBuffer();
        sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
        sb.append("    <tbody>\n");
        for(Map m1:jsonList) {
            sb.append("        <tr>\n");
            sb.append("            <th class=\"tdTitle\" colspan=\"2\">"+m1.get("group")+"</th>\n");
            sb.append("        </tr>\n");
            List<Map> list2 = (List<Map>) m1.get("params");
            for(Map m2:list2) {
                sb.append("        <tr>\n");
                sb.append("            <td class=\"tdTitle\">"+m2.get("k")+"</td>\n");
                sb.append("            <td>"+m2.get("v")+"</td>\n");
                sb.append("        </tr>\n");
            }
        }
        sb.append("    </tbody>\n");
        sb.append("</table>");
        return sb.toString();
    }

    @Override
    public TaotaoResult findItemParamByItemId(Long itemId) {
        TbItemParamItem itemParamByItemId = itemParamItemMapper.findItemParamByItemId(itemId);
        return TaotaoResult.ok(itemParamByItemId);
    }

//    @Override
//    public TaotaoResult updateParams(Long itemParamId, String itemParams) {
//        Date date = new Date();
//        TbItemParamItem tbItemParamItem = new TbItemParamItem();
//        tbItemParamItem.setId(itemParamId);
//        tbItemParamItem.setUpdated(date);
//        int i = itemParamItemMapper.updateParams(tbItemParamItem);
//        if(i != 0){
//            return TaotaoResult.ok();
//        }
//        return null;
//    }
}
