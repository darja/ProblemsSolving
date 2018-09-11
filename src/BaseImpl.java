public class BaseImpl {
    void log(String format, Object... params) {
        System.out.println(String.format(format, params));
    }
}
