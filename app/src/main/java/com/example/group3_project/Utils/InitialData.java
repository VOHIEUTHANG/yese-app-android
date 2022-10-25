package com.example.group3_project.Utils;

import com.example.group3_project.Database.Entity.QuestionPackage;
import com.example.group3_project.Database.Entity.Question;
import com.example.group3_project.Database.Entity.QuestionType;
import com.example.group3_project.Database.Entity.User;
import com.example.group3_project.Database.Entity.Vocab;
import com.example.group3_project.Database.Entity.WordPair;

import java.util.ArrayList;
import java.util.Date;

public class InitialData {
    public static ArrayList<User> getUserList(){
        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User("devostack2k","Thắng Hiếu Võ", Utils.getHashPassword("0123123123"), "hieuthang369@gmail.com", 30 ,1021, "user"));
        userList.add(new User("username_test_1",Utils.getHashPassword("password_test_1"), "DevoStack2k", 308, "user3"));
        userList.add(new User("username_test_2", Utils.getHashPassword("password_test_2"), "Kathy Nguyen", 221,"user1"));
        userList.add(new User("username_test_3", Utils.getHashPassword("password_test_3"), "Orange Tran", 221,"user2"));
        userList.add(new User("username_test_4", Utils.getHashPassword("password_test_4"), "Elis Phan", 92,"user4"));
        userList.add(new User("username_test_5", Utils.getHashPassword("password_test_5"), "DevoXX", 40,"user5"));
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

    public static ArrayList<QuestionType> getQuestionTypeList(){
        ArrayList<QuestionType> questionTypeArrayList = new ArrayList<>();
        questionTypeArrayList.add(new QuestionType(1,"Nhập lại nội dung bạn vừa nghe ?"));
        questionTypeArrayList.add(new QuestionType(2,"Dịch từ ?"));
        questionTypeArrayList.add(new QuestionType(3,"Bạn nghe được gì ?"));
        questionTypeArrayList.add(new QuestionType(4,"Làm sao để nói "));
        return questionTypeArrayList;
    }

    public static ArrayList<QuestionPackage> getPackageList(){
        ArrayList<QuestionPackage> packageList = new ArrayList<>();
        packageList.add(new QuestionPackage(1,"Cơ bản", "ic_home_basic", 1, 0));
        packageList.add(new QuestionPackage(2,"Chào hỏi", "ic_home_greeting", 1,0));
        packageList.add(new QuestionPackage(3,"Giới thiệu", "ic_home_introduce", 1, 0));
        packageList.add(new QuestionPackage(4,"Gia đình", "ic_home_family", 1, 0));

        packageList.add(new QuestionPackage(5,"Gặp gỡ", "ic_home_meet", 2, 1));
        packageList.add(new QuestionPackage(6,"Vận động", "ic_home_activity", 2, 1));
        packageList.add(new QuestionPackage(7,"Du lịch", "ic_home_travel", 2, 1));
        packageList.add(new QuestionPackage(8,"Mua sắm", "ic_home_shopping", 2, 1));
        return packageList;
    }

    public static ArrayList<Question> getQuestionList(){
        ArrayList<Question> questionList = new ArrayList<>();
//        questionList.add(new Question(1,1,"package1_basic_grass", "grass"));
//        questionList.add(new Question(2,1,"ic_home_package1_basic_hand", "Bàn tay", "hand", 0));
//        questionList.add(new Question(3,1,"package1_basic_late","let lay late","late"));
//        questionList.add(new Question(4,1,"lớp học", "class glass crass", "class", true));
//
//        questionList.add(new Question(4,1,"dự án", "project document lesson", "project", true));
//        questionList.add(new Question(2,1,"ic_home_package1_basic_truck", "xe tải", "truck", 0));
//        questionList.add(new Question(1,1,"package1_basic_greeting", "greeting"));
//        questionList.add(new Question(3,1,"package1_basic_create","late create cray","create"));

//        Package 2

        questionList.add(new Question(4,2,"sức khỏe", "health hell heart", "health", true));
        questionList.add(new Question(2,2,"ic_home_package2_handshake", "bắt tay", "handshake", 0));
        questionList.add(new Question(1,2,"package2_greeting_how_are_you", "how are you"));
        questionList.add(new Question(3,2,"package2_greeting_dream","cream dream cry","dream"));
        return questionList;
    }

}
