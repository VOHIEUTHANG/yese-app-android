package com.example.group3_project.Utils;

import com.example.group3_project.Database.Entity.User;
import com.example.group3_project.Database.Entity.Vocab;
import com.example.group3_project.Database.Entity.WordPair;

import java.util.ArrayList;
import java.util.Date;

public class InitialData {
    public static ArrayList<User> getUserList(){
        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User("devostack2k","Thắng Hiếu Võ", "0123123123", "hieuthang369@gmail.com", 30 ,1021, "user"));
        userList.add(new User("username_test_1","password_test_1", "DevoStack2k", 308, "user3"));
        userList.add(new User("username_test_2", "password_test_2", "Kathy Nguyen", 221,"user1"));
        userList.add(new User("username_test_3", "password_test_3", "Orange Tran", 221,"user2"));
        userList.add(new User("username_test_4", "password_test_4", "Elis Phan", 92,"user4"));
        userList.add(new User("username_test_5", "password_test_5", "DevoXX", 40,"user5"));
        return userList;
    }
    public static ArrayList<WordPair> getWordPairList(){
        ArrayList<WordPair> wordPairList = new ArrayList<>();
        wordPairList.add(new WordPair("chồng", "husband"));
        wordPairList.add(new WordPair("vợ", "wife"));
        wordPairList.add(new WordPair("gia đình", "familly"));
        wordPairList.add(new WordPair("mặt trời", "sun"));
        wordPairList.add(new WordPair("trái đất", "earth"));
        wordPairList.add(new WordPair("thực vật", "plant"));

        wordPairList.add(new WordPair("động vật", "animal"));
        wordPairList.add(new WordPair("thiên nhiên", "natural"));
        wordPairList.add(new WordPair("tóc", "hair"));
        wordPairList.add(new WordPair("cô dâu", "bride"));
        wordPairList.add(new WordPair("chú rể", "groom"));
        wordPairList.add(new WordPair("tủ lạnh", "refrigerator"));

        wordPairList.add(new WordPair("con mèo", "cat"));
        wordPairList.add(new WordPair("vui lòng", "please"));
        wordPairList.add(new WordPair("máy tính", "computer"));
        wordPairList.add(new WordPair("thuật toán", "algorithm"));
        wordPairList.add(new WordPair("cái váy", "skirt"));
        wordPairList.add(new WordPair("doanh nhân", "entrepreneur"));

        wordPairList.add(new WordPair("ủng hộ", "support"));
        wordPairList.add(new WordPair("bẩn", "dirty"));
        wordPairList.add(new WordPair("nắng", "sunny"));
        wordPairList.add(new WordPair("tưới", "irrigate"));
        wordPairList.add(new WordPair("tôn trọng", "respect"));
        wordPairList.add(new WordPair("đèn ngủ", "lamp"));

        wordPairList.add(new WordPair("con sói", "wolf"));
        wordPairList.add(new WordPair("sư tử", "lion"));
        wordPairList.add(new WordPair("con voi", "elephant"));
        wordPairList.add(new WordPair("con cừu", "sheep"));
        wordPairList.add(new WordPair("tòa nhà", "tower"));
        wordPairList.add(new WordPair("du lịch", "travel"));

        wordPairList.add(new WordPair("sóng biển", "waves"));
        wordPairList.add(new WordPair("chức năng", "function"));
        wordPairList.add(new WordPair("công ty", "firm"));
        wordPairList.add(new WordPair("xem xét", "consider"));
        wordPairList.add(new WordPair("tự hào", "proud"));
        wordPairList.add(new WordPair("vẻ đẹp", "beauty"));

        wordPairList.add(new WordPair("nhớ", "remember"));
        wordPairList.add(new WordPair("quên", "forget"));
        wordPairList.add(new WordPair("đối tượng", "object"));
        wordPairList.add(new WordPair("vận hành", "operate"));
        wordPairList.add(new WordPair("kinh doanh", "business"));
        wordPairList.add(new WordPair("cái loa", "speaker"));

        return wordPairList;
    }
    public static ArrayList<Vocab> getVocabList(String username){
        ArrayList<Vocab> vocabList = new ArrayList<>();
        vocabList.add(new Vocab("synchronous","adjective","đồng bộ", username,new Date()));
        vocabList.add(new Vocab("anonymous","adjective","vô danh",username,new Date()));
        vocabList.add(new Vocab("entrepreneur","noun","doanh nhân",username, new Date()));
        vocabList.add(new Vocab("shareholder","noun","cổ đông",username, new Date()));
        vocabList.add(new Vocab("variable","noun","biến số",username,new Date()));
        vocabList.add(new Vocab("corporation","noun","tập đoàn",username,new Date()));
        vocabList.add(new Vocab("stock","noun","cổ phần",username,new Date()));

        return vocabList;
    }

}
