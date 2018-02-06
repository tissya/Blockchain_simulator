//ブロックチェーンにおけるブロックに相当するクラス
class Block{
    private int data; //ブロックチェーンにおけるブロックのデータの中身
    private Block prev = null; //前のブロックのポインタ

    //data保存のメソッド
    public void setData(int data){
        this.data = data;
    }

    //前ブロックをセットするメソッド
    public void setPrev(Block block){
        this.prev = block;
    }
}