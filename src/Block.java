/**
 * ブロックチェーンにおけるブロックに相当するクラス
 */
public class Block {

    /**
     * ブロックチェーンにおけるブロックのデータの中身
     */
    private Integer data = null;

    /**
     * 前のブロックのポインタ
     */
    private Block previousData = null;

    /**
     * Constructor
     */
    public Block() {

    }

    /**
     * @return get data.
     */
    public Integer getData() {
        return data;
    }

    /**
     * data保存のメソッド
     *
     * @param data set data.
     */
    public void setData(Integer data) {
        this.data = data;
    }

    /**
     * @return get previousData.
     */
    public Block getPreviousData() {
        return previousData;
    }

    /**
     * 前ブロックをセットするメソッド
     *
     * @param previousData set previousData.
     */
    public void setPreviousData(Block previousData) {
        this.previousData = previousData;
    }
}