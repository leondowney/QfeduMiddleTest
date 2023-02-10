package EnumTest;

/**
 * @author Leon Downey
 * @date 2023/2/10 17:24
 * @describe： 定义一些返回的枚举类对象，用来规范返回的结果
 */
public enum ResultEnum implements IResult{

    //定义一些返回的枚举类对象，用来规范返回的结果
    SElECT_SUCCESS(true,1001,"查询成功"),
    SELECT_SUCCESS(true, 1001,"查询成功"),
    SELECT_ERROR(false, 1002,"查询失败"),
    PARAM_ERROR(false, 1003,"参数不合法"),
    DELETE_SUCCESS(true, 1004,"删除成功"),
    DELETE_ERROR(false, 1005,"删除失败"),
    ADD_SUCCESS(true, 1006,"添加成功"),
    ADD_ERROR(false, 1007, "添加失败"),
    UPDATE_SUCCESS(true, 1008,"更新成功"),
    UPDATE_ERROR(false, 1009,"更新失败");

    private boolean success;
    private int code;
    private String msg;

    ResultEnum() {
    }

    ResultEnum(boolean success, int code, String msg) {
        this.success = success;
        this.code = code;
        this.msg = msg;
    }

    @Override
    public boolean success() {
        return this.success;
    }

    @Override
    public int code() {
        return this.code;
    }

    @Override
    public String msg() {
        return this.msg;
    }

    @Override
    public String toString() {
        return "ResultEnum{" +
                "success=" + success +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }


    public static void main(String[] args) {
        ResultEnum[] arr=ResultEnum.values();
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

    }
}
