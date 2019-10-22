import java.time.LocalDateTime;

/**
 * @author 陌上丶天琊
 * @date 2019-10-21 19:21
 * 使用场景：在论坛中发表文章，后台要经过处理才可以发表或者进入数据库
 */
public class Main {

    public static void main(String[] args) {
        Message msg = new Message();
        msg.setName("anime");
        msg.setMemo("Hello world.");
        msg.setUpdateTime(LocalDateTime.now());
        System.out.println(msg);
    }
}
