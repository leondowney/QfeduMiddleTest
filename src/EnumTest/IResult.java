package EnumTest;

/**
 * @author Leon Downey
 * @date 2023/2/10 17:23
 * @describe：
 */
public interface IResult {

    //是否成功
    public boolean success();
    //返回编码
    public int code();
    //提示信息
    public  String msg();
}
