package EnumTest;

import java.sql.ResultSet;

/**
 * @author Leon Downey
 * @date 2023/2/10 17:23
 * @describe：
 */
public class Result {

    boolean success;
    int code;
    String msg;
    String datas;

    public Result() {
    }

    public Result(ResultEnum resultEnum) {
        this.success = resultEnum.success();
        this.code = resultEnum.code();
        this.msg = resultEnum.msg();
    }

    public Result(ResultEnum resultEnum,String datas) {
        this(resultEnum);
        this.datas = datas;
    }

    public boolean isSuccess(){
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDatas() {
        return datas;
    }

    public void setDatas(String datas) {
        this.datas = datas;
    }

    @Override
    public String toString() {
        return "Result{" +
                "success=" + success +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                ", datas='" + datas + '\'' +
                '}';
    }
}
