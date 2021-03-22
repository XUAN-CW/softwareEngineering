package Utils;

/**
 * @author XUAN
 * @date 2021/3/22 - 11:58
 * @references
 * @purpose
 * @errors
 */
import java.util.HashMap;
import java.util.Map;

public class RegionMap {

    public static final Map<String, Integer> regionProvMap = new HashMap<String, Integer>();

    static {
        regionProvMap.put("北京",110000);
        regionProvMap.put("天津",120000);
        regionProvMap.put("河北",130000);
        regionProvMap.put("山西",140000);
        regionProvMap.put("内蒙古",150000);
        regionProvMap.put("辽宁",210000);
        regionProvMap.put("吉林",220000);
        regionProvMap.put("黑龙江",230000);
        regionProvMap.put("上海",310000);
        regionProvMap.put("江苏",320000);
        regionProvMap.put("浙江",330000);
        regionProvMap.put("安徽",340000);
        regionProvMap.put("福建",350000);
        regionProvMap.put("江西",360000);
        regionProvMap.put("山东",370000);
        regionProvMap.put("河南",410000);
        regionProvMap.put("湖北",420000);
        regionProvMap.put("湖南",430000);
        regionProvMap.put("广东",440000);
        regionProvMap.put("广西",450000);
        regionProvMap.put("海南",460000);
        regionProvMap.put("重庆",500000);
        regionProvMap.put("四川",510000);
        regionProvMap.put("贵州",520000);
        regionProvMap.put("云南",530000);
        regionProvMap.put("西藏",540000);
        regionProvMap.put("陕西",610000);
        regionProvMap.put("甘肃",620000);
        regionProvMap.put("青海",630000);
        regionProvMap.put("宁夏",640000);
        regionProvMap.put("新疆",650000);
        regionProvMap.put("台湾",710000);
        regionProvMap.put("香港",810000);
        regionProvMap.put("澳门",820000);
    }
}