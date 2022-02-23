import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TestCase {
    private final static int CATEGORY_VAP_START = 1;
    private final static int CATEGORY_VAP_END = 4;
    private final static int CATEGORY_BI_START = 5;
    private final static int CATEGORY_BI_END = 8;
    private final static int CATEGORY_VP_START = 9;
    private final static int CATEGORY_VP_END = 11;
    private final static int CATEGORY_VC_START = 12;
    private final static int CATEGORY_VC_END = 13;
    private final static int CATEGORY_MSC_START = 14;
    private final static int CATEGORY_MSC_END = 15;
    private final static int CATEGORY_BO_START = 16;
    private final static int CATEGORY_BO_END = 19;
    private final static int CATEGORY_VAT_START = 20;
    private final static int CATEGORY_VAT_END = 22;
    private final static int CATEGORY_VBT_START = 24;

    private String[] line, attributeList;
    private Map<Integer, String> categoryMap;


    public TestCase(String[] attributeList) {
        this.attributeList = attributeList;
        categoryMap = new HashMap<>();
        categoryMap.put(CATEGORY_VAP_START, "Vouchers");
        categoryMap.put(CATEGORY_BI_START, "Booking Item");
        categoryMap.put(CATEGORY_VP_START, "Vouchers Purchased");
        categoryMap.put(CATEGORY_VC_START, "Vouchers Count");
    }

    public String buildSentence(String[] line) {
        this.line = line;
        String sentence = "";
        for(int i = 0; i < line.length; i++){
            if(i == 0){
                sentence += String.format("[TC. %s] ", line[i]);
            }
            else {
                sentence += !line[i].equals("") ? attributeList[i] + ", " : "";
            }
        }
        return sentence;
    }



}
