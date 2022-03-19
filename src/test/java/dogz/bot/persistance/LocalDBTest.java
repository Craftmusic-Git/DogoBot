package dogz.bot.persistance;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class LocalDBTest {

    LocalDB sut;
    static TreeMap<String,String> buttonsDataSet;

    static TreeMap<String,String> buttonsDataSetInit(){
        TreeMap<String,String> buttons = new TreeMap<>();
        String idButton1 = "12431234";
        String descButton1 = "DESC1";
        String idButton2 = "314151617";
        String descButton2 = "118218";
        buttons.put(idButton1,descButton1);
        buttons.put(idButton2,descButton2);
        return buttons;
    }

    void loadButtonData(){
        for (Map.Entry<String,String> entry : LocalDBTest.buttonsDataSet.entrySet()) {
            sut.saveButtonID(entry.getKey(), entry.getValue());
        }
    }

    @BeforeAll
    static void init(){
        buttonsDataSet = buttonsDataSetInit();
    }

    @BeforeEach
    void setUp() {
        sut = new LocalDB();
    }

    @Test
    void saveButtonID() {
        String idTest = "TEST";
        String desc = "DESC TEST";
        sut.saveButtonID(idTest,desc);
        Map<String,String> map = new TreeMap<>();
        map.put(idTest,desc);
        assertEquals(sut.loadButtonID(), map);
    }

    @Test
    void loadButtonID() {
        loadButtonData();
        TreeMap<String,String> map = (TreeMap)sut.loadButtonID();
    }

    @Test
    void saveEventChannel() {
    }

    @Test
    void loadEventChannel() {
    }

    @Test
    void saveEventParameter() {
    }

    @Test
    void loadEventParameter() {
    }
}